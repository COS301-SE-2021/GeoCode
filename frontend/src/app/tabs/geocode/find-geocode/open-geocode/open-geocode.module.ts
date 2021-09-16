import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { OpenGeocodePageRoutingModule } from './open-geocode-routing.module';

import { OpenGeocodePage } from './open-geocode.page';
import {CustomComponentsModule} from '../../../../components/components.module';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        OpenGeocodePageRoutingModule,
        CustomComponentsModule
    ],
  declarations: [OpenGeocodePage]
})
export class OpenGeocodePageModule {}
