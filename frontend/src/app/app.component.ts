import {Component, HostListener, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {KeycloakService} from 'keycloak-angular';
import {App} from '@capacitor/app';
import {WindowMonitor} from './services/WindowMonitor';
import {KeycloakInstance} from 'keycloak-js';
import {environment} from '../environments/environment';
import {GeoPoint} from './services/geocode-api';

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
    private windowMonitor: WindowMonitor
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

  async ngOnInit() {
    if (this.keycloakInstance.authenticated) {
      const location = await this.getLocation();
      
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

  async getLocation(): Promise<GeoPoint> {
    return new Promise<GeoPoint>(resolve => {
      navigator.geolocation.getCurrentPosition((position) => {
        resolve({
          latitude: position.coords.latitude,
          longitude: position.coords.longitude
        });
      }, () => {
        resolve(null);
      });
    });
  }

  @HostListener('window:resize')
  private fireResize() {
    this.windowMonitor.fireResize();
  }
}
