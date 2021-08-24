import { Component, OnInit } from '@angular/core';
import {TabsPage} from '../../../../tabs/tabs.page';

@Component({
  selector: 'app-sidebar-layout',
  templateUrl: './sidebar-layout.component.html',
  styleUrls: ['./sidebar-layout.component.scss'],
})
export class SidebarLayoutComponent implements OnInit {

  constructor() { }

  ngOnInit() {}

  changeTab(tabName: string) {
    TabsPage.subscriber.next(tabName);
  }
}
