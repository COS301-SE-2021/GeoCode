import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AddSetPage } from './add-set.page';

const routes: Routes = [
  {
    path: '',
    component: AddSetPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AddSetPageRoutingModule {}
