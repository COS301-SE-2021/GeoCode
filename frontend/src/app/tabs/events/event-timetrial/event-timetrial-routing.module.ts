import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EventTimetrialPage } from './event-timetrial.page';

const routes: Routes = [
  {
    path: '',
    component: EventTimetrialPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EventTimetrialPageRoutingModule {}
