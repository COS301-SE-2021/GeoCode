import {Injectable} from '@angular/core';
import {
  CollectableService,
  CollectableSet, CollectableType, CollectableTypeComponent,
  GetCollectableSetsResponse,
  GetCollectableTypesResponse, GetFoundCollectablesResponse, UserService
} from './geocode-api';

export interface Collection extends CollectableSet {
  types: CollectableTypeComponent[];
}

@Injectable({ providedIn: 'root' })
export class CollectionFacade {

  constructor(
    private collectableService: CollectableService,
    private userService: UserService
  ) {}

  getCollections(collections: Collection[]) {
    this.collectableService.getCollectableSets().subscribe((response: GetCollectableSetsResponse) => {
      console.log(response);
      collections.splice(0, collections.length); //Empty the array

      for (const set of response.collectableSets) {
        if (set.id === 'ba429fcf-0023-45e8-a0c9-b0b0db7e0582') {continue;}

        this.collectableService.getCollectableTypeBySet({setId: set.id}).subscribe((response2: GetCollectableTypesResponse) => {
          console.log(response2);
          const collection = <Collection> set;
          collection.types = response2.collectableTypes;
          collections.push(collection);
          console.log(collections);

        }, (error) => {
          console.log(error);
          const collection = <Collection> set;
          collection.types = [];
          collections.push(collection);
        });
      }
    });
  }

  getFoundCollectables(found: string[], id: string) {
    this.userService.getFoundCollectables({userID: id}).subscribe((response: GetFoundCollectablesResponse) => {
      console.log(response);
      found.splice(0, found.length); //Empty the array
      for (const collectable of response.collectables) {
        found.push(collectable.type.id);
      }
    });
  }
}
