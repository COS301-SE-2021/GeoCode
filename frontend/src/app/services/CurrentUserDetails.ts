import {Injectable} from '@angular/core';
import {KeycloakService} from 'keycloak-angular';
import {KeycloakInstance} from 'keycloak-js';
import {Storage} from '@ionic/storage-angular';
import {environment} from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class CurrentUserDetails {

  private instance: KeycloakInstance;

  constructor(
    private keycloak: KeycloakService,
    private storage: Storage
  ) {
    this.instance = this.keycloak.getKeycloakInstance();
  }

  public getID = () => this.instance.subject;
  public isAdmin = () => this.keycloak.isUserInRole('Admin');
  // @ts-ignore
  public getUsername = () => this.instance.tokenParsed.preferred_username;
  public getToken = () => this.instance.token;

  async logout() {
    await this.storage.clear();
    await this.keycloak.logout(environment.baseRedirectURI+'welcome');
  }
}
