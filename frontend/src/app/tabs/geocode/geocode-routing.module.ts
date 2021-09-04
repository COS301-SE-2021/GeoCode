import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GeocodePage } from './geocode.page';

const routes: Routes = [
  {
    path: '',
    component: GeocodePage
  },
  {
    path: 'geocode/create',
    loadChildren: () => import('./geocode-create/geocode-create.module').then(m => m.GeocodeCreatePageModule)
  },
  {
    path: 'geocode/:geocodeID',
    loadChildren: () => import('./find-geocode/find-geocode.module').then( m => m.FindGeocodePageModule)
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class GeocodePageRoutingModule {}
