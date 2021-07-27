import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UserEventsPage } from './events.page';

const routes: Routes = [
  {
    path: '',
    component: UserEventsPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserEventsPageRoutingModule {}
