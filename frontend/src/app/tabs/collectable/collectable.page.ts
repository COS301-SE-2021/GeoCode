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
} from '../../services/geocode-api';
import {KeycloakService} from 'keycloak-angular';

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
    private collectableService: CollectableService,
    private keycloak: KeycloakService
  ) {
    this.collectableService.getCollectableSets().subscribe(async (response: GetCollectableSetsResponse) => {
      console.log(response);
      this.sets = [];
      this.types = {};
      for (const set of response.collectableSets) {
        if (set.id === '00000000-0000-0000-0000-000000000000') {continue;}
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
    return this.keycloak.isUserInRole('Admin');
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
    const { data } = await modal.onDidDismiss();
    if (data != null) {
      this.types[selectedSetID].push(data);
    }
  }

  async addSet() {
    const modal = await this.modalController.create({
      component: AddSetComponent,
      swipeToClose: true,
      componentProps: {}
    });
    await modal.present();
    const { data } = await modal.onDidDismiss();
    if (data != null) {
      this.sets.push(data);
      this.types[data.id] = [];
    }
  }

}
