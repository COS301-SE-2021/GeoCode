import { Component, OnInit } from '@angular/core';
import {ModalController} from '@ionic/angular';
import {TrackableLocationsComponent} from './trackable-locations/trackable-locations.component';
import {
  Collectable,
  GetCurrentCollectableResponse, GetUserByIdResponse,
  GetUserTrackableResponse,
  UserService
} from '../../services/geocode-api';
import {KeycloakService} from 'keycloak-angular';
import {ActivatedRoute, Router} from '@angular/router';
import {CurrentUserDetails} from '../../services/CurrentUserDetails';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
})
export class ProfilePage implements OnInit {

  isOwnProfile = false;
  currentCollectable: Collectable = null;
  trackable: Collectable = null;
  username='Username';
  userID: string;

  constructor(
    private modalController: ModalController,
    private userService: UserService,
    private keycloak: KeycloakService,
    private currentUser: CurrentUserDetails,
    private router: Router,
    route: ActivatedRoute
  ) {
    this.userID = route.snapshot.paramMap.get('userID');
    if (!this.userID) {
      this.isOwnProfile = true;
      this.userID = currentUser.getID();
      this.username = currentUser.getUsername();

      this.userService.getUserTrackable({userID: this.userID}).subscribe((response: GetUserTrackableResponse) => {
        console.log(response);
        this.trackable = response.trackable;
      });
      this.userService.getCurrentCollectable({userID: this.userID}).subscribe((response: GetCurrentCollectableResponse) => {
        console.log(response);
        this.currentCollectable = response.collectable;
      });

    } else {
      this.isOwnProfile = false;
      this.userService.getUserById({userID: this.userID}).subscribe((response: GetUserByIdResponse) => {
        this.username = response.user.username;
        this.currentCollectable = response.user.currentCollectable;
        this.trackable = response.user.trackableObject;
      });
    }

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
    await this.currentUser.logout();
  }

  async manage() {
    await this.keycloak.getKeycloakInstance().accountManagement();
  }

}
