import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { CollectablePageRoutingModule } from './collectable-routing.module';

import { CollectablePage } from './collectable.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    CollectablePageRoutingModule
  ],
  declarations: [CollectablePage]
})
export class CollectablePageModule {}
