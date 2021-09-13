import {Injectable} from '@angular/core';
import {Platform} from '@ionic/angular';
import {Mediator} from './Mediator';

@Injectable({ providedIn: 'root' })
export class NavigationLayout {

  layout: 'tabs'|'sidebar';
  compact?: boolean;

  constructor(platform: Platform, mediator: Mediator) {
    this.calculateLayout(platform);

    platform.resize.subscribe(() => {
      mediator.windowResized.send(platform.width());
      this.calculateLayout(platform);
    });
  }

  calculateLayout(platform: Platform) {
    if (platform.width() > 1280) {
      this.layout = 'sidebar';
      this.compact = (platform.height() < 512);
    } else {
      this.layout = 'tabs';
    }
  }
}
