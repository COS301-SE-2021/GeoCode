import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { UserGeocodesPageRoutingModule } from './geocodes-routing.module';

import { UserGeocodesPage } from './geocodes.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    UserGeocodesPageRoutingModule
  ],
  declarations: [UserGeocodesPage]
})
export class GeocodesPageModule {}
