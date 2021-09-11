import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { EventsCreateBlocklyPageRoutingModule } from './events-create-blockly-routing.module';

import { EventsCreateBlocklyPage } from './events-create-blockly.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    EventsCreateBlocklyPageRoutingModule
  ],
  declarations: [EventsCreateBlocklyPage]
})
export class EventsCreateBlocklyPageModule {}
