import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Subscription} from 'rxjs';
import {IonTabs} from '@ionic/angular';
import {Mediator} from '../services/Mediator';
import {NavComponent, NavigationLayout} from '../components/navigation/nav.component';

class TabDefinition {
  name: string;
  internalName: string;
  iconName: string;
}

@Component({
  selector: 'app-tabs',
  templateUrl: 'tabs.page.html',
  styleUrls: ['tabs.page.scss']
})
export class TabsPage implements OnDestroy {

  @ViewChild('tabs') tabs: IonTabs;

  private tabList: TabDefinition[] = [
    { name: 'Explore', internalName: 'explore', iconName: 'qr-code-outline' },
    { name: 'Collections', internalName: 'collections', iconName: 'diamond-outline' },
    { name: 'Events', internalName: 'events', iconName: 'trophy-outline' },
    { name: 'Profile', internalName: 'profile', iconName: 'person-circle-outline' }
  ];

  private navigationLayout: NavigationLayout;
  private navigationTypeSubscription: Subscription;

  constructor(mediator: Mediator) {
    this.navigationLayout = NavComponent.getCurrentNavigationLayout();

    this.navigationTypeSubscription = mediator.navigationLayoutChanged.onReceive(layout => {
      this.navigationLayout = layout;
      console.log(layout);
    });
  }

  ngOnDestroy() {
    this.navigationTypeSubscription.unsubscribe();
  }

  async changeTab(tabName: string) {
    await this.tabs.select(tabName);
  }

  isSelected(tab: TabDefinition) {
    try {
      return (this.tabs.getSelected() === tab.internalName);
    } catch {
      return false;
    }
  }

}
