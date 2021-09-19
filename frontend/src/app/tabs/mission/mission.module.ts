import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { MissionPageRoutingModule } from './mission-routing.module';

import { MissionPage } from './mission.page';
import {CustomComponentsModule} from '../../components/components.module';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        MissionPageRoutingModule,
        CustomComponentsModule
    ],
  declarations: [MissionPage]
})
export class MissionPageModule {}
