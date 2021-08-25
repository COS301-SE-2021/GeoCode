import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EventsCreatePage } from './events-create.page';

const routes: Routes = [
  {
    path: '',
    component: EventsCreatePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EventsCreatePageRoutingModule {}
