import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EventsPage } from './events.page';

const routes: Routes = [
  {
    path: '',
    component: EventsPage
  },
  {
    path: 'create',
    loadChildren: () => import('./events-create/events-create.module').then( m => m.EventsCreatePageModule)
  },
  {
    path: ':eventID/leaderboard',
    loadChildren: () => import('./event-leaderboard/event-leaderboard.module').then( m => m.EventLeaderboardPageModule)
  },
  {
    path: ':eventID',
    loadChildren: () => import('./event-contents/event-contents.module').then(m => m.EventContentsModule)
  },
  {
    path: 'profile/:userID/events/:eventID/leaderboard',
    redirectTo: ':eventID/leaderboard'
  },
  {
    path: 'profile/:userID',
    loadChildren: () => import('../profile/profile.module').then(m => m.ProfilePageModule)
  },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EventsPageRoutingModule {}
