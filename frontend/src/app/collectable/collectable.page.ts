import { Component, OnInit } from '@angular/core';
import {ModalController} from '@ionic/angular';
import {AddTypeComponent} from './add-type/add-type.component';
import {AddSetComponent} from './add-set/add-set.component';
import {
  CollectableService,
  CollectableSet,
  CollectableTypeComponent,
  GetCollectableSetsResponse,
  GetCollectableTypesResponse
} from '../../swagger/client';

@Component({
  selector: 'app-collectable',
  templateUrl: './collectable.page.html',
  styleUrls: ['./collectable.page.scss'],
})
export class CollectablePage implements OnInit {

  sets: CollectableSet[];
  types: {
    [key: string]: CollectableTypeComponent[];
  };

  constructor(
    private modalController: ModalController,
    private collectableService: CollectableService
  ) {
    this.collectableService.getCollectableSets().subscribe(async (response: GetCollectableSetsResponse) => {
      console.log(response);
      this.sets = [];
      this.types = {};
      for (const set of response.collectableSets) {
        if (set.id === 'e3297aff-9c10-4a58-8e5f-a55e8f723066') {continue;}
        this.collectableService.getCollectableTypeBySet({setId: set.id}).subscribe((response2: GetCollectableTypesResponse) => {
          console.log(response2);
          this.types[set.id] = response2.collectableTypes;
          this.sets.push(set);
        }, (error) => {
          console.log(error);
          this.types[set.id] = [];
          this.sets.push(set);
        });
      }
    });
  }

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
