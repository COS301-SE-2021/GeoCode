import { Component, OnInit } from '@angular/core';
import {ModalController} from '@ionic/angular';
import {Collectable} from '../services/geocode-api';

@Component({
  selector: 'app-ar-test',
  templateUrl: './ar-test.page.html',
  styleUrls: ['./ar-test.page.scss'],
})
export class ArTestPage implements OnInit {

  collectable: Collectable = {
    type: {
      rarity: 'COMMON',
      image: 'https://localhost:8080/api/Image/getImage/fb669bb5-023b-4286-92e9-a3e469d367b4.png',
      id: '',
      properties: {},
      name: 'Random',
      set: {
        id: '',
        name: '',
        description: ''
      }
    },
    id: '',
    pastLocations: [],
    mission: ''
  };

  collectables: Collectable[] = [this.collectable, this.collectable, this.collectable, this.collectable, this.collectable];

  constructor(private modalCtrl: ModalController) { }

  ngOnInit() {

  }

  close(){
    this.modalCtrl.dismiss();
  }

}
