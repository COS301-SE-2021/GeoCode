import {Component, HostListener, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {KeycloakService} from 'keycloak-angular';
import {App} from '@capacitor/app';
import {KeycloakInstance} from 'keycloak-js';
import {Locator} from './services/Locator';
import {Mediator} from './services/Mediator';
import {Storage} from '@ionic/storage-angular';
import {Platform} from '@ionic/angular';
import {CurrentUserDetails} from './services/CurrentUserDetails';
import {UserService} from './services/geocode-api';

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
    private locator: Locator,
    private storage: Storage,
    private platform: Platform,
    private currentUser: CurrentUserDetails,
    private userService: UserService
  ) {
    this.keycloakInstance = this.keycloak.getKeycloakInstance();

    this.keycloakInstance.onAuthSuccess = () => {
      /* Called by native app */
      this.handleLogin().then().catch();
    };

    this.keycloakInstance.onAuthRefreshSuccess = () => {
      this.saveCredentials().then().catch();
    };

    this.keycloakInstance.onTokenExpired = () => {
      console.log('token expired');
    };

    this.keycloakInstance.onAuthLogout = () => {
      /* Force close to reset keycloak and prevent issues when logging in again */
      App.exitApp();
    };

    App.addListener('appUrlOpen', data => {
      console.log('App opened with URL: ' + data.url);
      if (data.url.includes('geocode://')) {
        // Remove 'geocode:/' from URL to get the address that the router should use. Also strip any parameters from the end
        const target = data.url.substring(9).split('?')[0];
        this.router.navigate([target]).then().catch();
      }
    });

    window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', (event) => {
      this.mediator.themeChanged.send(event.matches);
    });
  }

  async saveCredentials() {
    if (this.platform.is('capacitor')) {
      await this.storage.set('token', this.keycloakInstance.token);
      await this.storage.set('refreshToken', this.keycloakInstance.refreshToken);
    }
  }

  async handleLogin() {
    const location = await this.locator.getCurrentLocation();

    if (location !== null) {
      await this.saveCredentials();

      try {
        const response = await this.userService.handleLogin({location}).toPromise();
        if (!response.success) {
          console.log('Failed handleLogin. Logging out...');
          await this.logout();
        }
      } catch(e) {
        console.log('Failed handleLogin. Logging out...');
        await this.logout();
      }

    } else {
      alert('Location access is required to use GeoCode.');
      await this.logout();
    }
  }

  async logout() {
    await this.currentUser.logout();
  }

  async ngOnInit() {
    if (this.keycloakInstance.authenticated) {
      /* Called by web app */
      await this.handleLogin();

    } else {
      this.router.navigate(['/welcome']).then().catch();
    }
  }
}
