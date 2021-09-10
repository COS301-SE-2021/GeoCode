import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UserGeocodesPage } from './geocodes.page';
import {CommonModule} from '@angular/common';

const routes: Routes = [
  {
    path: '',
    component: UserGeocodesPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserGeocodesPageRoutingModule {}
