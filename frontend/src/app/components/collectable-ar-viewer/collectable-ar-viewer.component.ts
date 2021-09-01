import {AfterViewInit, Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {Collectable} from '../../services/geocode-api';
import {CollectableData, Position} from './helper-classes';

@Component({
  selector: 'app-collectable-ar-viewer',
  templateUrl: './collectable-ar-viewer.component.html',
  styleUrls: ['./collectable-ar-viewer.component.scss'],
})
export class CollectableArViewerComponent implements AfterViewInit, OnInit {

  @Input() collectables: Collectable[] = [];
  @ViewChild('iframe',{static:false}) iframe: ElementRef<HTMLIFrameElement>;
  iFrameDocument: Document;
  collectableData: CollectableData[] = [];

  loadPromises: Promise<any>[] = [];

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

  constructor() {
  }

  ngOnInit() {

  }

  async loadCollectables() {
    const promises = [];
    for (let i = 0; i < this.collectables.length && i < this.positions.length; i++) {
      const image = new CollectableData(this.collectables[i], this.positions[i]);
      this.collectableData.push(image);
      promises.push(image.loadDimensions());
    }
    await Promise.all(promises);
  }

  async loadIframeDocument(): Promise<Document> {
    return new Promise<Document>(resolve => {
      this.iframe.nativeElement.onload = () => {
        resolve(this.iframe.nativeElement.contentDocument);
      };
    });
  }

  async ngAfterViewInit() {
    this.iFrameDocument = await this.loadIframeDocument();

    const scene = this.iFrameDocument.getElementById('scene');

    const marker = this.createElement('a-marker', {
      pattern: 'hiro',
      cursor: 'rayOrigin: mouse'
    });

    for (const collectable of this.collectableData) {
      const background = this.createElement('a-plane', {
        position: collectable.position.backgroundPos(),
        rotation: '-90 0 0',
        width: '0.75',
        height: '0.75',
        color: '#17252A'
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
      touchTarget.onclick = (event) => {
        alert('Yeet');
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

  async loadCollectable(index: number) {

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
}
