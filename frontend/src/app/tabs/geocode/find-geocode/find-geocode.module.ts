import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { FindGeocodePageRoutingModule } from './find-geocode-routing.module';

import { FindGeocodePage } from './find-geocode.page';
import {CustomComponentsModule} from '../../../components/components.module';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        FindGeocodePageRoutingModule,
        CustomComponentsModule
    ],
  declarations: [FindGeocodePage]
})
export class FindGeocodePageModule {}
