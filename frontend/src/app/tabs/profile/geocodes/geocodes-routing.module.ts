import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UserGeocodesPage } from './geocodes.page';

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
