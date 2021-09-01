import { NgModule } from '@angular/core';
import {MapAndInfoComponent} from './map-and-info/map-and-info.component';
import {CommonModule} from '@angular/common';
import {IonicModule} from '@ionic/angular';
import {GeocodeDetailsComponent} from './geocode-details/geocode-details.component';
import {FormsModule} from '@angular/forms';
import {NavComponent} from './navigation/nav.component';
import {NavHeaderComponent} from './navigation/elements/nav-header/nav-header.component';
import {NavToolbarComponent} from './navigation/elements/nav-toolbar/nav-toolbar.component';
import {NavigationLayoutsModule} from './navigation/layouts/navigation-layouts.module';
import {CollectableArViewerComponent} from './collectable-ar-viewer/collectable-ar-viewer.component';

@NgModule({
  declarations: [MapAndInfoComponent, GeocodeDetailsComponent, NavComponent, NavHeaderComponent, NavToolbarComponent, CollectableArViewerComponent],
  imports: [
    CommonModule,
    IonicModule,
    FormsModule,
    NavigationLayoutsModule
  ],
  exports: [MapAndInfoComponent, GeocodeDetailsComponent, NavComponent, NavHeaderComponent, NavToolbarComponent, CollectableArViewerComponent]
})
export class CustomComponentsModule {}
