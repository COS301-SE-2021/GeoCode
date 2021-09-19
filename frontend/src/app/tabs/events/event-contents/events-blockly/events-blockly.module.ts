import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { EventsBlocklyPageRoutingModule } from './events-blockly-routing.module';

import { EventsBlocklyPage } from './events-blockly.page';
import {CustomComponentsModule} from '../../../../components/components.module';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        EventsBlocklyPageRoutingModule,
        CustomComponentsModule
    ],
  declarations: [EventsBlocklyPage]
})
export class EventsBlocklyPageModule {}
