import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TabsPage } from './tabs.page';

const routes: Routes = [
  {
    path: '',
    component: TabsPage,
    children: [
      {
        path: 'explore',
        loadChildren: () => import('./geocode/geocode.module').then(m => m.GeocodePageModule)
      },
      {
        path: 'collections',
        loadChildren: () => import('./collectable/collectable.module').then(m => m.CollectablePageModule)
      },
      {
        path: 'events',
        loadChildren: () => import('./events/events.module').then(m => m.EventsPageModule)
      },
      {
        path: 'profile/me',
        loadChildren: () => import('./profile/profile.module').then(m => m.ProfilePageModule)
      },
      {
        path: '',
        redirectTo: '/explore',
        pathMatch: 'full'
      },
      {
        path: 'profile',
        redirectTo: '/profile/me',
        pathMatch: 'full'
      },
    ]
  },
  {
    path: 'mission',
    loadChildren: () => import('./mission/mission.module').then( m => m.MissionPageModule)
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
})
export class TabsPageRoutingModule {}
