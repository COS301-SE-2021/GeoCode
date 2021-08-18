import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GeocodeCreatePage } from './geocode-create.page';

const routes: Routes = [
  {
    path: '',
    component: GeocodeCreatePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class GeocodeCreatePageRoutingModule {}
