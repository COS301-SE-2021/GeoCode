import { Component, OnInit } from '@angular/core';
import {ModalController} from '@ionic/angular';

@Component({
  selector: 'app-ar-test',
  templateUrl: './ar-test.page.html',
  styleUrls: ['./ar-test.page.scss'],
})
export class ArTestPage implements OnInit {

  constructor(private modalCtrl: ModalController) { }

  ngOnInit() {

  }

  close(){
    this.modalCtrl.dismiss();
  }

}
