import {AfterViewInit, Component, ElementRef, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CollectableData, Position} from './helper-classes';
import {
  CollectableResponse,
  GeoCodeService,
  SwapCollectablesRequest,
  SwapCollectablesResponse
} from '../../../../services/geocode-api';
import jsQR from 'jsqr';
import {AlertController} from '@ionic/angular';

@Component({
  selector: 'app-open-geocode',
  templateUrl: './open-geocode.page.html',
  styleUrls: ['./open-geocode.page.scss'],
})
export class OpenGeocodePage implements AfterViewInit {

  @ViewChild('iframe',{static:false}) iframe: ElementRef<HTMLIFrameElement>;
  iFrameWindow: Window;
  iFrameDocument: Document;
  collectableEntities: CollectableData[] = [];
  collectables: CollectableResponse[] = [];

  geocodeID: string = null;
  qrCode: string = null;
  useAR = true;

  positions: Position[] = [
    new Position(0, 0),
    new Position(0, 1),
    new Position(0, -1),
    new Position(-1, 0),
    new Position(1, 0),
    new Position(-1, 1),
    new Position(1, 1),
    new Position(-1, -1),
    new Position(1, -1)
  ];

  constructor(
    route: ActivatedRoute,
    private geocodeService: GeoCodeService,
    private alertCtrl: AlertController,
    private router: Router,
  ) {
    this.geocodeID = route.snapshot.paramMap.get('geocodeID');
    const state = this.router.getCurrentNavigation().extras.state;
    if (state) {
      this.qrCode = state.qrCode;
      this.useAR = false;
    }
  }

  async loadCollectables(collectables: CollectableResponse[]) {
    const promises = [];
    for (let i = 0; i < collectables.length && i < this.positions.length; i++) {
      const image = new CollectableData(collectables[i], this.positions[i]);
      this.collectableEntities.push(image);
      promises.push(image.loadDimensions());
    }
    await Promise.all(promises);
  }

  async loadIframe(): Promise<void> {
    return new Promise<void>(resolve => {
      this.iframe.nativeElement.onload = () => {
        this.iFrameWindow = this.iframe.nativeElement.contentWindow;
        this.iFrameDocument = this.iframe.nativeElement.contentDocument;
        resolve();
      };
    });
  }

  async ngAfterViewInit() {
    if (this.useAR) {
      await this.loadIframe();
      while (this.qrCode === null) {
        const temp = await this.scanQR();
        console.log('read: '+temp);
        if (temp.startsWith('https://geocodeapp.tech/g/')) {
          this.qrCode = temp.substring(26);
          console.log('code: '+this.qrCode);
        }
      }
    }

    const response = await this.geocodeService.getCollectablesInGeoCodeByQRCode({geoCodeID: this.geocodeID, qrCode: this.qrCode}).toPromise();
    console.log(response);

    if (this.useAR) {
      await this.loadCollectables(response.storedCollectable);
      this.insertCollectables();
    } else {
      this.collectables = response.storedCollectable;
    }
  }

  insertCollectables(): void {
    const scene = this.iFrameDocument.getElementById('scene');

    const marker = this.createElement('a-marker', {
      type: 'pattern',
      url: '/assets/qr-code-ar-marker.patt',
      cursor: 'rayOrigin: mouse',
    });

    for (const collectable of this.collectableEntities) {
      const background = this.createElement('a-plane', {
        position: collectable.position.backgroundPos(),
        rotation: '-90 0 0',
        width: '0.75',
        height: '0.75',
        color: '#24444F'
      });
      marker.appendChild(background);

      const text = this.createElement('a-text', {
        value: collectable.raw.type.name,
        align: 'center',
        position: collectable.position.textPos(),
        rotation: '-90 0 0',
        width: '1.5',
      });
      marker.appendChild(text);

      const image = this.getImage(collectable);
      marker.appendChild(image);

      const touchTarget = this.createElement('a-plane', {
        position: collectable.position.touchPos(),
        rotation: '-90 0 0',
        width: '1',
        height: '1',
        opacity: '0'
      });
      touchTarget.onmouseup = async () => {
        await this.swapCollectable(collectable.raw);
      };
      marker.appendChild(touchTarget);
    }
    console.log(marker);
    scene.appendChild(marker);
  }

  createElement(tagName: string, attributes: { [attribute: string]: string }): HTMLElement {
    const element = this.iFrameDocument.createElement(tagName);
    for (const [key, value] of Object.entries(attributes)) {
      element.setAttribute(key, value);
    }
    return element;
  }

  getImage(image: CollectableData): HTMLElement {
    if (image.raw.type.image.endsWith('.gif')) {
      return this.createElement('a-plane', {
        position: image.position.imagePos(),
        material: 'shader:gif;src:url('+image.raw.type.image+')',
        opacity: '0.99',
        rotation: '-90 0 0',
        width: image.width+'',
        height: image.height+''
      });

    } else {
      return this.createElement('a-image', {
        position: image.position.imagePos(),
        src: image.raw.type.image,
        opacity: '0.99',
        rotation: '-90 0 0',
        width: image.width+'',
        height: image.height+''
      });
    }
  }

  async scanQR(): Promise<string> {
    return new Promise(resolve => {
      const canvas = this.iFrameDocument.createElement('canvas');
      const ctx = canvas.getContext('2d');

      const interval = setInterval(() => {
        const video = this.iFrameDocument.querySelector('video');
        if (video && video.readyState === video.HAVE_ENOUGH_DATA) {
          canvas.height = video.videoHeight;
          canvas.width = video.videoWidth;
          ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
          const imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);

          const qrCode = jsQR(imageData.data, imageData.width, imageData.height);
          if (qrCode && qrCode.data !== '') {
            resolve(qrCode.data);
            clearInterval(interval);
          }
        }
      }, 2000);
    });
  }

  async swapCollectable(collectable: CollectableResponse){
    const alert = await this.alertCtrl.create({
      header: 'Swap',
      message: ' <strong>You are about to swap your collectable for '+collectable.type.name+ '</strong>',
      buttons: [
        { text: 'Cancel', role: 'cancel'},
        {
          text: 'Swap',
          handler: data => {
            const request: SwapCollectablesRequest={
              targetCollectableID:collectable.id,
              targetGeoCodeID:this.geocodeID
            };
            console.log(request);
            this.geocodeService.swapCollectables(request).subscribe((response: SwapCollectablesResponse) =>{
              console.log(response);
              this.router.navigate(['../../..']);
            });
          }
        }
      ]
    });
    await alert.present();
  }
}
