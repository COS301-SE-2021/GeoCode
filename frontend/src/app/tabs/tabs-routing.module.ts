import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TabsPage } from './tabs.page';

const routes: Routes = [
  {
    path: 'tabs',
    component: TabsPage,
    children: [
      {
        path: 'geocode',
        loadChildren: () => import('../geocode/geocode.module').then(m => m.GeocodePageModule)
      },
      {
        path: 'collectable',
        loadChildren: () => import('../collectable/collectable.module').then(m => m.CollectablePageModule)
      },
      {
        path: 'events',
        loadChildren: () => import('../events/events.module').then(m => m.EventsPageModule)
      },
      {
        path: 'user',
        loadChildren: () => import('../user/user.module').then(m => m.UserPageModule)
      },
      {
        path: 'geocode-contents',
        loadChildren: () => import('../geocode/geocode-contents/geocode-contents.module').then(m => m.GeocodeContentsPageModule)
      },
      {
        path: '',
        redirectTo: '/tabs/geocode',
        pathMatch: 'full'
      }
    ]
  },
  {
    path: '',
    redirectTo: '/tabs/geocode',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
})
export class TabsPageRoutingModule {}
