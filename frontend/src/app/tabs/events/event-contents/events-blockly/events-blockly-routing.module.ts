import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EventsBlocklyPage } from './events-blockly.page';

const routes: Routes = [
  {
    path: '',
    component: EventsBlocklyPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EventsBlocklyPageRoutingModule {}
