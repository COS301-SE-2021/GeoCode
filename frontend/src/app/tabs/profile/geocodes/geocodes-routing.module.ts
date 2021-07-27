import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { GeocodesPage } from './geocodes.page';

const routes: Routes = [
  {
    path: '',
    component: GeocodesPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class GeocodesPageRoutingModule {}
