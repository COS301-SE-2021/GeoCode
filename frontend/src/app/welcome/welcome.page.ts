import {Component} from '@angular/core';
import {KeycloakService} from 'keycloak-angular';
import {Router} from '@angular/router';
import {environment} from '../../environments/environment';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.page.html',
  styleUrls: ['./welcome.page.scss'],
})
export class WelcomePage {

  constructor(
    private keycloak: KeycloakService,
    private router: Router
  ) {
    if (this.keycloak.getKeycloakInstance().authenticated) {
      this.router.navigate(['explore']).then().catch();
    }
  }

  async login() {
    await this.keycloak.login({
      redirectUri: environment.baseRedirectURI+'/explore'
    });
  }

  async register(){
    await this.keycloak.register({
      redirectUri: environment.baseRedirectURI+'/explore'
    });
  }

}
