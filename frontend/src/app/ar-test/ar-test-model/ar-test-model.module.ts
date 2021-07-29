import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ArTestModelPageRoutingModule } from './ar-test-model-routing.module';

import { ArTestModelPage } from './ar-test-model.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ArTestModelPageRoutingModule
  ],
  declarations: [ArTestModelPage]
})
export class ArTestModelPageModule {}
