import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MissionsPage } from './missions.page';

const routes: Routes = [
  {
    path: '',
    component: MissionsPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MissionsPageRoutingModule {}
