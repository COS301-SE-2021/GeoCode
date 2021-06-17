import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CollectablePage } from './collectable.page';

const routes: Routes = [
  {
    path: '',
    component: CollectablePage
  },
  {
    path: 'sets/add',
    loadChildren: () => import('./add-set/add-set.module').then( m => m.AddSetPageModule)
  },
  {
    path: 'sets/:setID/addType',
    loadChildren: () => import('./add-type/add-type.module').then( m => m.AddTypePageModule)
  },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CollectablePageRoutingModule {}
