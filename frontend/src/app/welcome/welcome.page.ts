import {Component, OnInit} from '@angular/core';
import {KeycloakService} from 'keycloak-angular';
import {Router} from '@angular/router';
import {environment} from '../../environments/environment';
import {Locator} from '../services/Locator';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.page.html',
  styleUrls: ['./welcome.page.scss'],
})
export class WelcomePage implements OnInit {

  locationFound = false;

  constructor(
    private keycloak: KeycloakService,
    private router: Router,
    private locator: Locator
  ) {
    if (this.keycloak.getKeycloakInstance().authenticated) {
      this.router.navigate(['/explore']).then().catch();
    }
  }

  async ngOnInit() {
    this.locationFound = (await this.locator.getCurrentLocation() != null);
  }

  async login() {
    await this.keycloak.login({
      redirectUri: environment.baseRedirectURI+'explore'
    });
  }

  async register(){
    await this.keycloak.register({
      redirectUri: environment.baseRedirectURI+'explore'
    });
  }
}
