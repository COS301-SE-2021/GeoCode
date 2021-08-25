import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EventLeaderboardPage } from './event-leaderboard.page';

const routes: Routes = [
  {
    path: '',
    component: EventLeaderboardPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EventLeaderboardPageRoutingModule {}
