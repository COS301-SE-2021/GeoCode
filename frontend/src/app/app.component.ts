import {Component, HostListener, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {KeycloakService} from 'keycloak-angular';
import {App} from '@capacitor/app';
import {WindowMonitor} from './services/WindowMonitor';
import {KeycloakInstance} from 'keycloak-js';
import {environment} from '../environments/environment';
import {Locator} from './services/Locator';

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
    private windowMonitor: WindowMonitor,
    private locator: Locator
  ) {
    this.keycloakInstance = this.keycloak.getKeycloakInstance();

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
    this.windowMonitor.fireResize();
  }

  async ngOnInit() {
    if (this.keycloakInstance.authenticated) {
      const location = await this.locator.getCurrentLocation();

      if (location !== null) {
        console.log('call handleLogin here');

      } else {
        alert('Location access is required to use GeoCode.');
        await this.logout();
      }

    } else {
      this.router.navigate(['/welcome']).then().catch();
    }
  }

  async logout() {
    await this.keycloak.logout(environment.baseRedirectURI+'welcome');
  }
}
