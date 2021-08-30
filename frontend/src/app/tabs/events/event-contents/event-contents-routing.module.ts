import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EventContentsPage } from './event-contents.page';

const routes: Routes = [
  {
    path: '',
    component: EventContentsPage
  },
  {
    path: 'open/:geocodeID',
    loadChildren: () => import('../../geocode/geocode-contents/geocode-contents.module').then(m => m.GeocodeContentsPageModule)
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EventContentsRoutingModule {}
