import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { EventsCreatePageRoutingModule } from './events-create-routing.module';

import { EventsCreatePage } from './events-create.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    EventsCreatePageRoutingModule
  ],
  declarations: [EventsCreatePage]
})
export class EventsCreatePageModule {}
