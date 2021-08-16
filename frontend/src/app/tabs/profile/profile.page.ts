import { Component, OnInit } from '@angular/core';
import {ModalController} from '@ionic/angular';
import {TrackableLocationsComponent} from './trackable-locations/trackable-locations.component';
import {
  Collectable,
  GetCurrentCollectableResponse,
  GetUserTrackableResponse,
  UserService
} from '../../services/geocode-api';
import {KeycloakService} from 'keycloak-angular';
import {environment} from '../../../environments/environment';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
})
export class ProfilePage implements OnInit {

  isOwnProfile = true;
  currentCollectable: Collectable = null;
  trackable: Collectable = null;
  username='Username';

  constructor(
    private modalController: ModalController,
    private userService: UserService,
    private keycloak: KeycloakService,
    route: ActivatedRoute
  ) {
    let id = route.snapshot.paramMap.get('id');
    if (!id) {
      id = keycloak.getKeycloakInstance().subject;
      this.isOwnProfile = true;
      id = this.keycloak.getKeycloakInstance().subject;
    }
    this.userService.getUserTrackable({userID: id}).subscribe((response: GetUserTrackableResponse) => {
      console.log(response);
      this.trackable = response.trackable;
    });
    this.userService.getCurrentCollectable({userID: id}).subscribe((response: GetCurrentCollectableResponse) => {
      console.log(response);
      this.currentCollectable = response.collectable;
    });

    // @ts-ignore
    this.username=keycloak.getKeycloakInstance().idTokenParsed.preferred_username;
  }

  ngOnInit() {

  }

  async showTrackableLocations() {
    const modal = await this.modalController.create({
      component: TrackableLocationsComponent,
      swipeToClose: true,
      componentProps: {
        trackable: this.trackable
      }
    });
    await modal.present();
  }

  async logout() {
    await this.keycloak.logout(environment.baseRedirectURI+'/welcome');
  }

  async manage() {
    await this.keycloak.getKeycloakInstance().accountManagement();
  }

}
