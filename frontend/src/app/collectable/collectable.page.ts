import { Component, OnInit } from '@angular/core';
import {ModalController} from '@ionic/angular';
import {AddTypeComponent} from './add-type/add-type.component';
import {AddSetComponent} from './add-set/add-set.component';

@Component({
  selector: 'app-collectable',
  templateUrl: './collectable.page.html',
  styleUrls: ['./collectable.page.scss'],
})
export class CollectablePage implements OnInit {

  constructor(
    private modalController: ModalController
  ) { }

  ngOnInit() {
  }

  isAdmin() {
    return true;
  }

  async addType(selectedSetID) {
    const modal = await this.modalController.create({
      component: AddTypeComponent,
      swipeToClose: true,
      componentProps: {
        setID: selectedSetID
      }
    });
    await modal.present();
  }

  async addSet() {
    const modal = await this.modalController.create({
      component: AddSetComponent,
      swipeToClose: true,
      componentProps: {}
    });
    await modal.present();
  }

}
