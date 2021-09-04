import {Component, HostListener, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {KeycloakService} from 'keycloak-angular';
import {App} from '@capacitor/app';
import {KeycloakInstance} from 'keycloak-js';
import {environment} from '../environments/environment';
import {Locator} from './services/Locator';
import {Mediator} from './services/Mediator';
import {NavComponent} from './components/navigation/nav.component';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent implements OnInit {

  private keycloakInstance: KeycloakInstance;

  constructor(
    private keycloak: KeycloakService,
    private router: Router,
    private mediator: Mediator,
    private locator: Locator
  ) {
    this.keycloakInstance = this.keycloak.getKeycloakInstance();

    this.keycloakInstance.onAuthSuccess = () => {
      /* Called by native app */
      this.callHandleLogin().then().catch();
    };

    App.addListener('appUrlOpen', data => {
      console.log('App opened with URL: ' + data.url);
      if (data.url.includes('geocode://')) {
        // Remove 'geocode:/' from URL to get the address that the router should use. Also strip any parameters from the end
        const target = data.url.substring(9).split('?')[0];
        this.router.navigate([target]).then().catch();
      }
    });
  }

  @HostListener('window:resize')
  private fireResize() {
    this.mediator.windowResized.send(window.innerWidth);
    this.mediator.navigationLayoutChanged.send(NavComponent.getCurrentNavigationLayout());
  }

  async callHandleLogin() {
    const location = await this.locator.getCurrentLocation();

    if (location !== null) {
      console.log('call handleLogin here');

    } else {
      alert('Location access is required to use GeoCode.');
      await this.logout();
    }
  }

  async ngOnInit() {
    if (this.keycloakInstance.authenticated) {
      /* Called by web app */
      await this.callHandleLogin();

    } else {
      this.router.navigate(['/welcome']).then().catch();
    }
  }

  async logout() {
    await this.keycloak.logout(environment.baseRedirectURI+'welcome');
  }
}
