import {Component, OnDestroy} from '@angular/core';
import {TabsPage} from '../../tabs/tabs.page';
import {WindowMonitor, NavigationLayout} from '../../services/WindowMonitor';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss'],
})
export class NavComponent implements OnDestroy {

  private navigationLayout: NavigationLayout;
  private navigationLayoutSubscription: Subscription;

  constructor(windowMonitor: WindowMonitor) {
    this.navigationLayout = windowMonitor.getCurrentNavigationLayout();

    this.navigationLayoutSubscription = windowMonitor.onNavigationLayoutChange(type => {
      this.navigationLayout = type;
    });
  }

  ngOnDestroy() {
    this.navigationLayoutSubscription.unsubscribe();
  }

  changeTab(tabName: string) {
    const obs = TabsPage.subscriber;
    obs.next(tabName);
  }
}
