import { Component, OnInit } from '@angular/core';
import {ModalController} from '@ionic/angular';
import {KeycloakService} from 'keycloak-angular';
import {ActivatedRoute} from '@angular/router';
import {Collection, CollectionFacade} from '../../../services/CollectionFacade';

@Component({
  selector: 'app-collections',
  templateUrl: './collections.page.html',
  styleUrls: ['./collections.page.scss'],
})
export class UserCollectionsPage implements OnInit {

  collections: Collection[] = [];
  found: string[] = [];

  constructor(
    private modalController: ModalController,
    private collectionHandler: CollectionFacade,
    keycloak: KeycloakService,
    route: ActivatedRoute
  ) {
    let id = route.snapshot.paramMap.get('userID');
    if (!id) {
      id = keycloak.getKeycloakInstance().subject;
    }
    this.collectionHandler.getCollections(this.collections);
    this.collectionHandler.getFoundCollectables(this.found, id);
  }

  ngOnInit() {
  }

  foundID(id){
    if(this.found.includes(id)){
      return true;
    }
    return false;
  }

  foundSet(types){

    for(let i =0; i<types.length;i++){
      if(this.found.includes(types[i].id)){
        return true;
      }

    }
    return false;

  }

}
