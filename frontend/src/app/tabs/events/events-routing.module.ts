import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EventsPage } from './events.page';

const routes: Routes = [
  {
    path: '',
    component: EventsPage
  },  {
    path: 'event-leaderboard',
    loadChildren: () => import('./event-leaderboard/event-leaderboard.module').then( m => m.EventLeaderboardPageModule)
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EventsPageRoutingModule {}
