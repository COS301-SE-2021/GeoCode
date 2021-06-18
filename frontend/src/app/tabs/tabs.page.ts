import { Component } from '@angular/core';
import {Platform} from '@ionic/angular';

@Component({
  selector: 'app-tabs',
  templateUrl: 'tabs.page.html',
  styleUrls: ['tabs.page.scss']
})
export class TabsPage {
  tabsPlacement: string='bottom';
  geoCodeIcon = '/assets/images/GeoCodeLogoWhite.svg';
  eventsIcon = '/assets/images/CalendarIconWht.svg';
  collectableIcon = '/assets/images/coinbagiconWht.svg';

  constructor(public platform: Platform) {
  if(!this.platform.is('mobile')){
    this.tabsPlacement='top';
  }
  }

}
