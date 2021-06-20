import { Component } from '@angular/core';
import {Platform} from '@ionic/angular';

@Component({
  selector: 'app-tabs',
  templateUrl: 'tabs.page.html',
  styleUrls: ['tabs.page.scss']
})
export class TabsPage {
  tabsPlacement: string='bottom';
  geoCodeIcon = '/assets/images/QRCodeWht.svg';
  //eventsIcon = '/assets/images/CalendarIconWht.svg';
  //eventsIcon = 'trophy-outline';
  collectableIcon = '/assets/images/CoinStackWht.svg';

  constructor(public platform: Platform) {
  if(!this.platform.is('mobile')){
    this.tabsPlacement='top';
  }
  }

}