import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { GeocodePage } from './geocode.page';

const routes: Routes = [
  {
    path: '',
    component: GeocodePage
  },
  {
    path: 'geocode-contents',
    loadChildren: () => import('./geocode-contents/geocode-contents.module').then(m => m.GeocodeContentsPageModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class GeocodePageRoutingModule {}
