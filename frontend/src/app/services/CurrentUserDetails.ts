import {Injectable} from '@angular/core';
import {KeycloakService} from 'keycloak-angular';
import {KeycloakInstance} from 'keycloak-js';

@Injectable({ providedIn: 'root' })
export class CurrentUserDetails {

  private instance: KeycloakInstance;

  constructor(private keycloak: KeycloakService) {
    this.instance = this.keycloak.getKeycloakInstance();
  }

  public getID = () => this.instance.subject;
  public isAdmin = () => this.keycloak.isUserInRole('Admin');
  public getUsername = () => this.instance.profile.username;
  public getToken = () => this.instance.token;
}
