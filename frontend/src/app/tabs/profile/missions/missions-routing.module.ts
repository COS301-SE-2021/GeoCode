import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UserMissionsPage } from './missions.page';

const routes: Routes = [
  {
    path: '',
    component: UserMissionsPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserMissionsPageRoutingModule {}
