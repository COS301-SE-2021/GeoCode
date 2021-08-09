import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EventsPage } from './events.page';

const routes: Routes = [
  {
    path: '',
    component: EventsPage
  },
  {
    path: ':id/leaderboard',
    loadChildren: () => import('./event-leaderboard/event-leaderboard.module').then( m => m.EventLeaderboardPageModule)
  },
  {
    path: ':id',
    loadChildren: () => import('./event-contents/event-contents.module').then(m => m.EventContentsModule)
  },
  {
    path: 'create',
    loadChildren: () => import('./events-create/events-create.module').then( m => m.EventsCreatePageModule)
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EventsPageRoutingModule {}
