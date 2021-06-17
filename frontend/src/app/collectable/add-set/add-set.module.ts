import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { AddSetPageRoutingModule } from './add-set-routing.module';

import { AddSetPage } from './add-set.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    AddSetPageRoutingModule
  ],
  declarations: [AddSetPage]
})
export class AddSetPageModule {}
