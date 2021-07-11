import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { EventTimetrialPageRoutingModule } from './event-timetrial-routing.module';

import { EventTimetrialPage } from './event-timetrial.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    EventTimetrialPageRoutingModule
  ],
  declarations: [EventTimetrialPage]
})
export class EventTimetrialPageModule {}
