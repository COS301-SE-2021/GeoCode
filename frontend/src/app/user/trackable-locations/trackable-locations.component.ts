import { Component, OnInit } from '@angular/core';
import {ModalController, NavParams} from '@ionic/angular';

@Component({
  selector: 'app-trackable-locations',
  templateUrl: './trackable-locations.component.html',
  styleUrls: ['./trackable-locations.component.scss'],
})
export class TrackableLocationsComponent implements OnInit {

  trackableID = 'test';

  constructor(
    private modalController: ModalController,
    navParams: NavParams
  ) {
    this.trackableID = navParams.data.trackableID;
  }

  ngOnInit() {}

  dismiss() {
    this.modalController.dismiss()
      .then()
      .catch();
  }

}
