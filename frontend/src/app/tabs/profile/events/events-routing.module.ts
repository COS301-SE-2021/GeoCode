import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UserEventsPage } from './events.page';

const routes: Routes = [
  {
    path: '',
    component: UserEventsPage
  },
  {
    path: ':eventID/leaderboard',
    loadChildren: () => import('../../events/event-leaderboard/event-leaderboard.module').then( m => m.EventLeaderboardPageModule)
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserEventsPageRoutingModule {}
