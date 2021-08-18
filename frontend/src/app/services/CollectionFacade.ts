import {Injectable} from '@angular/core';
import {
  CollectableService,
  CollectableSet, CollectableTypeComponent,
  GetCollectableSetsResponse,
  GetCollectableTypesResponse, GetFoundCollectableTypesResponse, UserService
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
        if (set.id === '00000000-0000-0000-0000-000000000000') {continue;}

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
    this.userService.getFoundCollectableTypes({userID: id}).subscribe((response: GetFoundCollectableTypesResponse) => {
      console.log(response);
      found.splice(0, found.length); //Empty the array
      for (const id of response.collectableTypeIDs) {
        found.push(id);
      }
    });
  }

}
