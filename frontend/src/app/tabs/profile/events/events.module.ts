import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { UserEventsPageRoutingModule } from './events-routing.module';

import { UserEventsPage } from './events.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    UserEventsPageRoutingModule
  ],
  declarations: [UserEventsPage]
})
export class EventsPageModule {}
