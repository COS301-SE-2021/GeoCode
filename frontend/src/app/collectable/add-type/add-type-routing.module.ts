import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AddTypePage } from './add-type.page';

const routes: Routes = [
  {
    path: '',
    component: AddTypePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AddTypePageRoutingModule {}
