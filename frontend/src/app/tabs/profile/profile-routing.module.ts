import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ProfilePage } from './profile.page';

const routes: Routes = [
  {
    path: '',
    component: ProfilePage
  },
  {
    path: 'events/profile/:userID',
    redirectTo: ':userID'
  },
  {
    path: 'events',
    loadChildren: () => import('./events/events.module').then( m => m.EventsPageModule)
  },
  {
    path: 'collections',
    loadChildren: () => import('./collections/collections.module').then( m => m.CollectionsPageModule)
  },
  {
    path: 'missions',
    loadChildren: () => import('./missions/missions.module').then( m => m.MissionsPageModule)
  },
  {
    path: 'geocodes',
    loadChildren: () => import('./geocodes/geocodes.module').then( m => m.GeocodesPageModule)
  },
  {
    path: ':userID',
    component: ProfilePage
  },
  {
    path: ':userID/events',
    loadChildren: () => import('./events/events.module').then( m => m.EventsPageModule)
  },
  {
    path: ':userID/collections',
    loadChildren: () => import('./collections/collections.module').then( m => m.CollectionsPageModule)
  },
  {
    path: ':userID/missions',
    loadChildren: () => import('./missions/missions.module').then( m => m.MissionsPageModule)
  },
  {
    path: ':userID/geocodes',
    loadChildren: () => import('./geocodes/geocodes.module').then( m => m.GeocodesPageModule)
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProfilePageRoutingModule {}
