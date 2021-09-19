import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { EventLeaderboardPageRoutingModule } from './event-leaderboard-routing.module';

import { EventLeaderboardPage } from './event-leaderboard.page';
import {CustomComponentsModule} from '../../../components/components.module';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        EventLeaderboardPageRoutingModule,
        CustomComponentsModule
    ],
  declarations: [EventLeaderboardPage]
})
export class EventLeaderboardPageModule {}
