import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {KeycloakService} from 'keycloak-angular';
import {Router} from '@angular/router';
import {environment} from '../../environments/environment';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.page.html',
  styleUrls: ['./welcome.page.scss'],
})
export class WelcomePage implements OnInit {

  constructor(
    private keycloak: KeycloakService,
    private router: Router
  ) {}

  async ngOnInit() {
    if (await this.keycloak.isLoggedIn()) {
      await this.router.navigate(['']);
    }
  }

  async login() {
    await this.keycloak.login({
      redirectUri: environment.baseRedirectURI+'/geocode'
    });
  }

}
