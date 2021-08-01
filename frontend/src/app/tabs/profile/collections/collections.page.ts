import { Component, OnInit } from '@angular/core';
import {CollectableSet, CollectableTypeComponent} from '../../../services/geocode-api';

@Component({
  selector: 'app-collections',
  templateUrl: './collections.page.html',
  styleUrls: ['./collections.page.scss'],
})
export class UserCollectionsPage implements OnInit {

  sets: CollectableSet[];
  types: {
    [key: string]: CollectableTypeComponent[];
  };

  constructor() { }

  ngOnInit() {
  }

}
