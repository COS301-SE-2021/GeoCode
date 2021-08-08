import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EventContentsPage } from './event-contents.page';

const routes: Routes = [
  {
    path: '',
    component: EventContentsPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EventTimetrialPageRoutingModule {}
