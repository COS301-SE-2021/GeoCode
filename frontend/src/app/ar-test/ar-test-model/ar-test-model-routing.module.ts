import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ArTestModelPage } from './ar-test-model.page';

const routes: Routes = [
  {
    path: '',
    component: ArTestModelPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ArTestModelPageRoutingModule {}
