import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { UserPageRoutingModule } from './user-routing.module';

import { UserPage } from './user.page';
import {TrackableLocationsComponent} from './trackable-locations/trackable-locations.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    UserPageRoutingModule
  ],
  declarations: [UserPage, TrackableLocationsComponent]
})
export class UserPageModule {}
