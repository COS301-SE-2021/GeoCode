import {Component, HostListener} from '@angular/core';
import {Router} from '@angular/router';
import {KeycloakService} from 'keycloak-angular';
import {App} from '@capacitor/app';
import {WindowMonitor} from './services/WindowMonitor';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {
  constructor(
    private keycloak: KeycloakService,
    private router: Router,
    private windowMonitor: WindowMonitor
  ) {
    const instance = this.keycloak.getKeycloakInstance();

    this.router.navigate(['/ar-test']).then().catch();

    /*if (!instance.authenticated) {
      this.router.navigate(['/welcome']).then().catch();
    }*/

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
}
