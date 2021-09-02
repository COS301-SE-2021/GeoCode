import { NgModule } from '@angular/core';
import {MapAndInfoComponent} from './map-and-info/map-and-info.component';
import {CommonModule} from '@angular/common';
import {IonicModule} from '@ionic/angular';
import {GeocodeDetailsComponent} from './geocode-details/geocode-details.component';
import {FormsModule} from '@angular/forms';
import {NavigationComponent} from './navigation/navigation.component';
import {NgxBlocklyModule} from 'ngx-blockly';

@NgModule({
  declarations: [MapAndInfoComponent, GeocodeDetailsComponent, NavigationComponent],
  imports: [
    CommonModule,
    IonicModule,
    FormsModule,
    NgxBlocklyModule
  ],
  exports: [MapAndInfoComponent, GeocodeDetailsComponent, NavigationComponent]
})
export class CustomComponentsModule {}
