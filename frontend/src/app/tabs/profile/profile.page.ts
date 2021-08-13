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

  showBackButton = true;
  currentCollectable: Collectable = null;
  trackable: Collectable = null;

  constructor(
    private modalController: ModalController,
    private userService: UserService,
    keycloak: KeycloakService,
    route: ActivatedRoute
  ) {
    let id = route.snapshot.paramMap.get('id');
    if (!id) {
      this.showBackButton = false;
      id = keycloak.getKeycloakInstance().subject;
    }
    this.userService.getUserTrackable({userID: id}).subscribe((response: GetUserTrackableResponse) => {
      console.log(response);
      this.trackable = response.trackable;
    });
    this.userService.getCurrentCollectable({userID: id}).subscribe((response: GetCurrentCollectableResponse) => {
      console.log(response);
      this.currentCollectable = response.collectable;
    });
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

}
