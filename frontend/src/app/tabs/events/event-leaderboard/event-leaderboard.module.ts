import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { EventLeaderboardPageRoutingModule } from './event-leaderboard-routing.module';

import { EventLeaderboardPage } from './event-leaderboard.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    EventLeaderboardPageRoutingModule
  ],
  declarations: [EventLeaderboardPage]
})
export class EventLeaderboardPageModule {}
