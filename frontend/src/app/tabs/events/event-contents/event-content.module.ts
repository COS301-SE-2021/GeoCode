import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { EventTimetrialPageRoutingModule } from './event-contents-routing.module';

import { EventContentsPage } from './event-contents.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    EventTimetrialPageRoutingModule
  ],
  declarations: [EventContentsPage]
})
export class EventTimetrialPageModule {}
