import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FindGeocodePage } from './find-geocode.page';

const routes: Routes = [
  {
    path: '',
    component: FindGeocodePage
  },
  {
    path: 'open',
    loadChildren: () => import('./open-geocode/open-geocode.module').then( m => m.OpenGeocodePageModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FindGeocodePageRoutingModule {}
