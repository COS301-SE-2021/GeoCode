import {AfterViewInit, Component, ElementRef, Input, ViewChild} from '@angular/core';
import {Collectable} from '../../services/geocode-api';

class Position {
  x: number;
  y: number;

  /* Take x (left-right), y (up-down) with reference to a wall */
  constructor(x: number, y: number) {
    this.x = x;
    this.y = y;
  }

  /* Convert to A-Frame's position format which is based on looking downward */
  imagePos(): string {
    return `${this.x} 0.05 ${this.y+0.05}`;
  }

  backgroundPos(): string {
    return `${this.x} 0 ${this.y}`;
  }

  textPos(): string {
    return `${this.x} 0.05 ${this.y-0.3}`;
  }

  touchPos(): string {
    return `${this.x} 0.1 ${this.y}`;
  }
}

interface AttributeDictionary {
  [attribute: string]: string;
}

@Component({
  selector: 'app-collectable-ar-viewer',
  templateUrl: './collectable-ar-viewer.component.html',
  styleUrls: ['./collectable-ar-viewer.component.scss'],
})
export class CollectableArViewerComponent implements AfterViewInit {

  @Input() collectables: Collectable[] = [];
  @ViewChild('iframe',{static:false}) iframe: ElementRef<HTMLIFrameElement>;
  iFrameDocument: Document;

  positions: Position[] = [
    new Position(0, 0),
    new Position(1, 0),
    new Position(0, 1),
    new Position(0, -1),
    new Position(-1, 0),
    new Position(-1, -1),
    new Position(-1, 1),
    new Position(1, -1),
    new Position(1, 1)
  ];

  constructor() {
  }

  ngAfterViewInit(): void {

    const image = new Image();
    image.onload = (event) => {
      console.log(image.getAnimations());
      // @ts-ignore
      console.log(event.path[0]);
    };
    image.src = 'http://www.google.com/intl/en_ALL/images/logo.gif';

    this.iframe.nativeElement.onload = () => {
      this.iFrameDocument = this.iframe.nativeElement.contentDocument;
      const scene = this.iFrameDocument.getElementById('scene');

      const marker = this.createElement('a-marker', {
        pattern: 'hiro',
        cursor: 'rayOrigin: mouse'
      });

      for (let i = 0; i < this.collectables.length && i < this.positions.length; i++) {

        const text = this.createElement('a-text', {
          value: 'Ender Dragon',
          align: 'center',
          position: this.positions[i].textPos(),
          rotation: '-90 0 0',
          width: '1',
        });
        marker.appendChild(text);

        /*const image = this.createElement('a-image', {
          position: this.positions[i].imagePos(),
          src: 'https://upload.wikimedia.org/wikipedia/en/4/49/Creeper_%28Minecraft%29.png',
          opacity: '0.99',
          rotation: '-90 0 0',
          width: '0.5',
          height: '0.5'
        });
        marker.appendChild(image);*/

        const entity = this.createElement('a-plane', {
          position: this.positions[i].imagePos(),
          material: 'shader:gif;src:url(https://static.wikia.nocookie.net/minecraft_gamepedia/images/0/0a/Ender_Dragon.gif)',
          opacity: '0.99',
          rotation: '-90 0 0',
          width: '0.5',
          height: '0.5'
        });
        marker.appendChild(entity);

        const plane = this.createElement('a-plane', {
          position: this.positions[i].backgroundPos(),
          rotation: '-90 0 0',
          width: '0.75',
          height: '0.75',
          color: '#17252A'
        });
        marker.appendChild(plane);

        const touchTarget = this.createElement('a-plane', {
          position: this.positions[i].touchPos(),
          rotation: '-90 0 0',
          width: '1',
          height: '1',
          opacity: '0'
        });
        touchTarget.onclick = (event) => {
          alert('Yeet '+i);
        };
        marker.appendChild(touchTarget);
      }
      console.log(marker);
      scene.appendChild(marker);
    };
  }

  createElement(tagName: string, attributes: AttributeDictionary): HTMLElement {
    const element = this.iFrameDocument.createElement(tagName);
    for (const [key, value] of Object.entries(attributes)) {
      element.setAttribute(key, value);
    }
    return element;
  }
}
