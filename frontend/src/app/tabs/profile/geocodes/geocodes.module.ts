import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { UserGeocodesPageRoutingModule } from './geocodes-routing.module';

import { UserGeocodesPage } from './geocodes.page';
import {CustomComponentsModule} from '../../../components/components.module';
import {GeocodeUpdateComponent} from './geocode-update/geocode-update.component';

@NgModule({
  imports: [
      CommonModule,
      FormsModule,
      IonicModule,
      UserGeocodesPageRoutingModule,
      CustomComponentsModule
  ],
  declarations: [UserGeocodesPage, GeocodeUpdateComponent]
})
export class GeocodesPageModule {}
