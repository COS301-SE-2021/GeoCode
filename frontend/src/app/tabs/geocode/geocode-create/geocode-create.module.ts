import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { GeocodeCreatePageRoutingModule } from './geocode-create-routing.module';

import { GeocodeCreatePage } from './geocode-create.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    GeocodeCreatePageRoutingModule
  ],
  declarations: [GeocodeCreatePage]
})
export class GeocodeCreatePageModule {}
