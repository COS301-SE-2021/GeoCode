import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { GeocodeContentsPageRoutingModule } from './geocode-contents-routing.module';
import { GeocodeContentsPage } from './geocode-contents.page';
import {CustomComponentsModule} from '../../../components/components.module';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        GeocodeContentsPageRoutingModule,
        CustomComponentsModule
    ],
  declarations: [GeocodeContentsPage]
})
export class GeocodeContentsPageModule {}
