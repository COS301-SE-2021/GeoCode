import { NgModule } from '@angular/core';
import {CommonModule} from '@angular/common';
import {IonicModule} from '@ionic/angular';
import {FormsModule} from '@angular/forms';
import {TabLayoutComponent} from './tab-layout/tab-layout.component';
import {SidebarLayoutComponent} from './sidebar-layout/sidebar-layout.component';

@NgModule({
  declarations: [TabLayoutComponent, SidebarLayoutComponent],
  imports: [
    CommonModule,
    IonicModule,
    FormsModule
  ],
  exports: [TabLayoutComponent, SidebarLayoutComponent]
})
export class NavigationLayoutsModule {}
