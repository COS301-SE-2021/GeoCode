import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { CollectablePageRoutingModule } from './collectable-routing.module';

import { CollectablePage } from './collectable.page';
import {AddTypeComponent} from './add-type/add-type.component';
import {AddSetComponent} from './add-set/add-set.component';
import {CustomComponentsModule} from '../../components/components.module';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        IonicModule,
        CollectablePageRoutingModule,
        CustomComponentsModule,
    ],
  declarations: [CollectablePage, AddTypeComponent, AddSetComponent]
})
export class CollectablePageModule {}
