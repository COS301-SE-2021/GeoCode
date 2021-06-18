import { Component, OnInit } from '@angular/core';
import {ModalController, NavParams} from '@ionic/angular';

@Component({
  selector: 'app-add-set',
  templateUrl: './add-set.component.html',
  styleUrls: ['./add-set.component.scss'],
})
export class AddSetComponent implements OnInit {

  constructor(
    private modalController: ModalController,
    navParams: NavParams
  ) {
  }

  ngOnInit() {}

  dismiss() {
    this.modalController.dismiss()
      .then()
      .catch();
  }

}
