import { Component, OnInit } from '@angular/core';
import {ModalController} from '@ionic/angular';
import {TrackableLocationsComponent} from './trackable-locations/trackable-locations.component';
import {
  Collectable,
  GetCurrentCollectableResponse,
  GetUserTrackableResponse,
  UserService
} from '../../swagger/client';
import {UserInformationService} from '../../swagger/UserInformationService';

@Component({
  selector: 'app-user',
  templateUrl: './user.page.html',
  styleUrls: ['./user.page.scss'],
})
export class UserPage implements OnInit {

  currentCollectable: Collectable = null;
  trackable: Collectable = null;

  constructor(
    private modalController: ModalController,
    private userService: UserService,
    private userDetails: UserInformationService
  ) {
    this.userService.getUserTrackable({userID: userDetails.getUUID()}).subscribe((response: GetUserTrackableResponse) => {
      console.log(response);
      this.trackable = response.trackable;
    });
    this.userService.getCurrentCollectable({userID: userDetails.getUUID()}).subscribe((response: GetCurrentCollectableResponse) => {
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
