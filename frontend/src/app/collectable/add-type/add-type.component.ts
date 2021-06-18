import { Component, OnInit } from '@angular/core';
import {ModalController, NavParams} from '@ionic/angular';

@Component({
  selector: 'app-add-type',
  templateUrl: './add-type.component.html',
  styleUrls: ['./add-type.component.scss'],
})
export class AddTypeComponent implements OnInit {

  setID = 'random';

  constructor(
    private modalController: ModalController,
    navParams: NavParams
  ) {
    this.setID = navParams.data.setID;
  }

  ngOnInit() {}

  dismiss() {
    this.modalController.dismiss()
      .then()
      .catch();
  }

}
