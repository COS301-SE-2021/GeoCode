import { NgModule } from '@angular/core';
import {MapAndInfoComponent} from './map-and-info/map-and-info.component';
import {CommonModule} from '@angular/common';
import {IonicModule} from '@ionic/angular';
import {GeocodeDetailsComponent} from './geocode-details/geocode-details.component';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [MapAndInfoComponent, GeocodeDetailsComponent],
  imports: [
    CommonModule,
    IonicModule,
    FormsModule
  ],
  exports: [MapAndInfoComponent, GeocodeDetailsComponent]
})
export class CustomComponentsModule {}
