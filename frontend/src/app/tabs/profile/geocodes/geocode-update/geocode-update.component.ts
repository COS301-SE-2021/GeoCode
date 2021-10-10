import { Component, OnInit } from '@angular/core';
import {GeoCode, GeoCodeService, UpdateGeoCodeRequest, UpdateGeoCodeResponse} from '../../../../services/geocode-api';
import {AlertController, ModalController, NavParams, ToastController} from '@ionic/angular';

@Component({
  selector: 'app-geocode-update',
  templateUrl: './geocode-update.component.html',
  styleUrls: ['./geocode-update.component.scss'],
})
export class GeocodeUpdateComponent implements OnInit {
  geocode: GeoCode;
  updateRequest: UpdateGeoCodeRequest ={
    available: false,
    description: '',
    difficulty: 'EASY',
    geoCodeID: '',
    hints: [],
    location: {latitude:0,longitude:0}
  };
  constructor(
    private modalController: ModalController,
    private toastController: ToastController,
    private geocodeAPI: GeoCodeService,
    private alertCtrl: AlertController,
    navParams: NavParams
  ) {
    this.geocode = navParams.data.geocode;
  }

  ngOnInit() {
    this.updateRequest.available = this.geocode.available;
    this.updateRequest.difficulty = this.geocode.difficulty;
    this.updateRequest.hints = this.geocode.hints;
    this.updateRequest.geoCodeID= this.geocode.id;
    this.updateRequest.location=this.geocode.location;
    this.updateRequest.description = this.geocode.description;
  }

  updateDescription($event){
    this.updateRequest.description = $event.target.value;
  }

  updateHints($event,hint){
    this.updateRequest.hints[this.updateRequest.hints.indexOf(hint)] = $event.target.value;
  }

  updateDifficulty(diff){
    this.updateRequest.difficulty=diff;
  }

  async updateGeoCode(){
    this.geocodeAPI.updateGeoCode(this.updateRequest).subscribe(async (response: UpdateGeoCodeResponse)=>{
      if(!response.success){
        const toast =  await this.toastController.create({
          message: 'Error updating geocode ',
          duration: 2000
        });
        await toast.present();
      }else{
        const toast =  await this.toastController.create({
          message: 'Succesfully updated geocode',
          duration: 2000
        });
        await toast.present();
        this.modalController.dismiss({
          dismissed: true
        });
      }
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

 async deleteHint(hint){
    if(this.updateRequest.hints.length <2){
      const toast =  await this.toastController.create({
        message: 'Must have at least one hint ',
        duration: 2000
      });
      await toast.present();
    }else{
      if(this.updateRequest.hints.indexOf(hint)>-1){
        this.updateRequest.hints.splice(this.updateRequest.hints.indexOf(hint),1);
      }else {
        const toast = await this.toastController.create({
          message: 'Error deleting hint ',
          duration: 2000
        });
        await toast.present();
      }
    }
  }

  close(){
    this.modalController.dismiss({
      dismissed:true
    });
  }
}
