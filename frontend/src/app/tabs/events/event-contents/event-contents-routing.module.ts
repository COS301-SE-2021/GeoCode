import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EventContentsPage } from './event-contents.page';

const routes: Routes = [
  {
    path: '',
    component: EventContentsPage
  },
  {
    path: 'geocode/:geocodeID',
    loadChildren: () => import('../../geocode/find-geocode/find-geocode.module').then(m => m.FindGeocodePageModule)
  },
  {
    path: 'blockly',
    loadChildren: () => import('./events-blockly/events-blockly.module').then( m => m.EventsBlocklyPageModule)
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EventContentsRoutingModule {}
