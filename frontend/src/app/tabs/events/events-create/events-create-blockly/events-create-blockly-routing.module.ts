import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EventsCreateBlocklyPage } from './events-create-blockly.page';

const routes: Routes = [
  {
    path: '',
    component: EventsCreateBlocklyPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EventsCreateBlocklyPageRoutingModule {}
