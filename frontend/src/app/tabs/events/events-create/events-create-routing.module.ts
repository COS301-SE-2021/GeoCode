import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EventsCreatePage } from './events-create.page';

const routes: Routes = [
  {
    path: '',
    component: EventsCreatePage
  },  {
    path: 'events-create-blockly',
    loadChildren: () => import('./events-create-blockly/events-create-blockly.module').then( m => m.EventsCreateBlocklyPageModule)
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EventsCreatePageRoutingModule {}
