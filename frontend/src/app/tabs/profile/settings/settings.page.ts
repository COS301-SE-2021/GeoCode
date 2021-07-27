import { Component, OnInit } from '@angular/core';
import {environment} from '../../../../environments/environment';
import {KeycloakService} from 'keycloak-angular';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.page.html',
  styleUrls: ['./settings.page.scss'],
})
export class SettingsPage implements OnInit {

  constructor(
    private keycloak: KeycloakService
  ) { }

  ngOnInit() {
  }

  async logout() {
    await this.keycloak.logout(environment.baseRedirectURI+'/welcome');
  }

  async manage() {
    await this.keycloak.getKeycloakInstance().accountManagement();
  }

}
