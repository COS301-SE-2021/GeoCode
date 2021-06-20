import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CollectablePage } from './collectable.page';

const routes: Routes = [
  {
    path: '',
    component: CollectablePage
  },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CollectablePageRoutingModule {}
