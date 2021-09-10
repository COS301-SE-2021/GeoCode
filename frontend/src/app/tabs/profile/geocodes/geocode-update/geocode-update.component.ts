import { Component, OnInit } from '@angular/core';
import {GeoCodeService, UpdateGeoCodeRequest, UpdateGeoCodeResponse} from '../../../../services/geocode-api';
import {AlertController} from '@ionic/angular';

@Component({
  selector: 'app-geocode-update',
  templateUrl: './geocode-update.component.html',
  styleUrls: ['./geocode-update.component.scss'],
})
export class GeocodeUpdateComponent implements OnInit {
  geocode;
  updateRequest: UpdateGeoCodeRequest ={
    available: false,
    description: '',
    difficulty: 'EASY',
    geoCodeID: '',
    hints: [],
    location: {latitude:0,longitude:0}
  };
  constructor(private geocodeAPI: GeoCodeService, private alertCtrl: AlertController) { }

  ngOnInit() {
    this.updateRequest.available = this.geocode.available;
    this.updateRequest.difficulty = this.geocode.difficulty;
    this.updateRequest.hints = this.geocode.hints;
    this.updateRequest.geoCodeID= this.geocode.id;
    this.updateRequest.location=this.geocode.location;
    this.updateRequest.description = this.geocode.description;
  }

  updateDescription($event){
    this.updateRequest.description = $event.detail.value;
  }

  updateHints($event,hint){
    console.log(this.updateRequest.hints.indexOf(hint));
    this.updateRequest.hints[this.updateRequest.hints.indexOf(hint)] = $event.target.value;
  }

  updateDifficulty($event){
    this.updateRequest.difficulty=$event.detail.value;
  }

  updateGeoCode(){

    this.geocodeAPI.updateGeoCode(this.updateRequest).subscribe((response: UpdateGeoCodeResponse)=>{
      console.log(response);
    });
  }

  async addHint() {

    const alert = this.alertCtrl.create({
      header: 'Add Hint',
      message: 'Please enter a hint description',
      inputs: [
        {
          name: 'hint',
          placeholder: 'hint'
        }
      ],
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
          handler: data => {
            console.log('Cancel clicked');
          }
        },
        {
          text: 'Save',
          handler: data => {
            if (data.hint === '') {

            } else {
              console.log(data.hint);
              this.updateRequest.hints.push(data.hint);
            }
          }
        }
      ]
    });
    (await alert).present();

  }

}
