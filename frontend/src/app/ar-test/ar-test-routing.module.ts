import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ArTestPage } from './ar-test.page';

const routes: Routes = [
  {
    path: '',
    component: ArTestPage
  },  {
    path: 'ar-test-model',
    loadChildren: () => import('./ar-test-model/ar-test-model.module').then( m => m.ArTestModelPageModule)
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ArTestPageRoutingModule {}
