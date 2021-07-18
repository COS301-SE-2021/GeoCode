import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {KeycloakService} from 'keycloak-angular';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent implements OnInit {
  constructor(
    private router: Router,
    private keycloak: KeycloakService
  ) {}

  async ngOnInit() {
    if (!(await this.keycloak.isLoggedIn())) {
      await this.router.navigate(['welcome']);
    }
  }
}
