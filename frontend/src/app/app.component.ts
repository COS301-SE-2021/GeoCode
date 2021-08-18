import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {KeycloakService} from 'keycloak-angular';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {
  constructor(
    private keycloak: KeycloakService,
    private router: Router
  ) {
    if (!this.keycloak.getKeycloakInstance().authenticated) {
      this.router.navigate(['welcome']).then().catch();
    }
  }
}
