import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import {EventContentsRoutingModule} from './event-contents-routing.module';

import { EventContentsPage } from './event-contents.page';
import {CustomComponentsModule} from '../../../components/components.module';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        EventContentsRoutingModule,
        CustomComponentsModule
    ],
  declarations: [EventContentsPage]
})
export class EventContentsModule {}
