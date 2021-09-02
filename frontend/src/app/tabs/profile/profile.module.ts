import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ProfilePageRoutingModule } from './profile-routing.module';

import { ProfilePage } from './profile.page';
import {TrackableLocationsComponent} from './trackable-locations/trackable-locations.component';
import {CustomComponentsModule} from '../../components/components.module';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        ProfilePageRoutingModule,
        CustomComponentsModule,
    ],
  declarations: [ProfilePage, TrackableLocationsComponent]
})
export class ProfilePageModule {}
