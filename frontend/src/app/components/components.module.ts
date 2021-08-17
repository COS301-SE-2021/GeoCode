import { NgModule } from '@angular/core';
import {MapAndInfoComponent} from './map-and-info/map-and-info.component';
import {CommonModule} from '@angular/common';
import {IonicModule} from '@ionic/angular';

@NgModule({
  declarations: [MapAndInfoComponent],
  imports: [
    CommonModule,
    IonicModule
  ],
  exports: [MapAndInfoComponent]
})
export class CustomComponentsModule {}
