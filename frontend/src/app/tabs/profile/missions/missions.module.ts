import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { UserMissionsPageRoutingModule } from './missions-routing.module';

import { UserMissionsPage } from './missions.page';
import {CustomComponentsModule} from '../../../components/components.module';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        UserMissionsPageRoutingModule,
        CustomComponentsModule
    ],
  declarations: [UserMissionsPage]
})
export class MissionsPageModule {}
