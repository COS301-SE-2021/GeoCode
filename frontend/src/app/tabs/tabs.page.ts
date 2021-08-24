import {Component, OnDestroy, ViewChild} from '@angular/core';
import {Observable, Subscription} from 'rxjs';
import {IonTabs} from '@ionic/angular';
import {WindowMonitor} from '../services/WindowMonitor';

@Component({
  selector: 'app-tabs',
  templateUrl: 'tabs.page.html',
  styleUrls: ['tabs.page.scss']
})
export class TabsPage implements OnDestroy {

  static subscriber;
  private showTabs: boolean;
  @ViewChild('tabs') tabs: IonTabs;
  tabChanger = new Observable(subscriber => TabsPage.subscriber = subscriber);

  private navigationTypeSubscription: Subscription;

  constructor(windowMonitor: WindowMonitor) {
    this.showTabs = (windowMonitor.getCurrentNavigationLayout() === 'tabs');
    this.tabChanger.subscribe(async (next: string) => {
      await this.tabs.select(next.toLowerCase());
    });

    this.navigationTypeSubscription = windowMonitor.onNavigationLayoutChange(layout => {
      this.showTabs = (layout === 'tabs');
    });
  }

  ngOnDestroy() {
    this.navigationTypeSubscription.unsubscribe();
  }



}
