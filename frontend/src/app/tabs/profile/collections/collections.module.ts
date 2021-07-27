import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { UserCollectionsPageRoutingModule } from './collections-routing.module';

import { UserCollectionsPage } from './collections.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    UserCollectionsPageRoutingModule
  ],
  declarations: [UserCollectionsPage]
})
export class CollectionsPageModule {}
