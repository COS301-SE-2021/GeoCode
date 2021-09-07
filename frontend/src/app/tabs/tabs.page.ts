import {Component, ViewChild} from '@angular/core';
import {IonTabs} from '@ionic/angular';
import {NavigationLayout} from '../services/NavigationLayout';

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
export class TabsPage {

  @ViewChild('tabs') tabs: IonTabs;

  tabList: TabDefinition[] = [
    { name: 'Explore', internalName: 'explore', iconName: 'qr-code-outline' },
    { name: 'Collections', internalName: 'collections', iconName: 'diamond-outline' },
    { name: 'Events', internalName: 'events', iconName: 'trophy-outline' },
    { name: 'Profile', internalName: 'profile', iconName: 'person-circle-outline' }
  ];

  constructor(public navigationLayout: NavigationLayout) { }

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
