import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { GeocodePageRoutingModule } from './geocode-routing.module';

import { GeocodePage } from './geocode.page';
import {CustomComponentsModule} from '../../components/components.module';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        GeocodePageRoutingModule,
        CustomComponentsModule,
    ],
  declarations: [GeocodePage]
})
export class GeocodePageModule {}
