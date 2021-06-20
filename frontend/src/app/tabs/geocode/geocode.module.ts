import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { GeocodePageRoutingModule } from './geocode-routing.module';

import { GeocodePage } from './geocode.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    GeocodePageRoutingModule
  ],
  declarations: [GeocodePage]
})
export class GeocodePageModule {}
