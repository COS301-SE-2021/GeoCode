import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ArTestPageRoutingModule } from './ar-test-routing.module';

import { ArTestPage } from './ar-test.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ArTestPageRoutingModule
  ],
  declarations: [ArTestPage]
})
export class ArTestPageModule {}
