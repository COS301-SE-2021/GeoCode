import {AfterViewInit, Component, ElementRef, Input, ViewChild} from '@angular/core';
import {Collectable} from '../../services/geocode-api';

@Component({
  selector: 'app-collectable-ar-viewer',
  templateUrl: './collectable-ar-viewer.component.html',
  styleUrls: ['./collectable-ar-viewer.component.scss'],
})
export class CollectableArViewerComponent implements AfterViewInit {

  @Input() collectables: Collectable[] = [];
  @ViewChild('iframe',{static:false}) iframe: ElementRef<HTMLIFrameElement>;

  positions = [
    '0 0 0',
    '1.25 0 0',
    '0 0 1.25',
    '0 0 -1.25',
    '-1.25 0 0',
    '-1.25 0 -1.25',
    '-1.25 0 1.25',
    '1.25 0 -1.25',
    '1.25 0 1.25'
  ];

  constructor() {
  }

  ngAfterViewInit(): void {
    this.iframe.nativeElement.onload = () => {
      const iFrameDocument = this.iframe.nativeElement.contentDocument;
      const scene = iFrameDocument.getElementById('scene');

      const marker = iFrameDocument.createElement('a-marker');
      marker.setAttribute('pattern', 'hiro');
      marker.setAttribute('cursor', 'rayOrigin: mouse');
      for (let i = 0; i < this.collectables.length && i < this.positions.length; i++) {
        const entity = iFrameDocument.createElement('a-plane');
        entity.setAttribute('geometry', 'primitive:plane');
        entity.setAttribute('position', this.positions[i]);
        entity.setAttribute('material', 'shader:gif;src:url(https://static.wikia.nocookie.net/minecraft_gamepedia/images/0/0a/Ender_Dragon.gif);opacity:1');
        entity.setAttribute('rotation', '-90 0 0');
        entity.setAttribute('width', '1');
        entity.setAttribute('height', '1');
        entity.onclick = (event) => {
          alert('Yeet');
        };
        console.log(entity);
        marker.appendChild(entity);
      }
      console.log(marker);
      scene.appendChild(marker);
    }
  }
}
