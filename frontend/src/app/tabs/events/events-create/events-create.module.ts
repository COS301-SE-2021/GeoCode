import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { EventsCreatePageRoutingModule } from './events-create-routing.module';

import { EventsCreatePage } from './events-create.page';
import {CreateGeocodeComponent} from './create-geocode/create-geocode.component';
import {EventLocationComponent} from './event-location/event-location.component';
import {CustomComponentsModule} from '../../../components/components.module';



@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        EventsCreatePageRoutingModule,
        CustomComponentsModule
    ],
  declarations: [EventsCreatePage,CreateGeocodeComponent,EventLocationComponent]
})
export class EventsCreatePageModule {}
