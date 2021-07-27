import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { GeocodesPageRoutingModule } from './geocodes-routing.module';

import { GeocodesPage } from './geocodes.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    GeocodesPageRoutingModule
  ],
  declarations: [GeocodesPage]
})
export class GeocodesPageModule {}
