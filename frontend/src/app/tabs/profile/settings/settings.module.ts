import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { UserSettingsPageRoutingModule } from './settings-routing.module';

import { UserSettingsPage } from './settings.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    UserSettingsPageRoutingModule
  ],
  declarations: [UserSettingsPage]
})
export class SettingsPageModule {}
