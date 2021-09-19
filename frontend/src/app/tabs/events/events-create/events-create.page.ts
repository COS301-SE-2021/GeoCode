import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {
  CreateEventRequest,
  CreateEventResponse,
  EventService,
  GeoCodeService
} from '../../../services/geocode-api';
import {AlertController, ModalController, NavController, PickerController, ToastController} from '@ionic/angular';
import {GoogleMapsLoader} from '../../../services/GoogleMapsLoader';
import {CreateGeocodeComponent} from '../../../components/create-geocode/create-geocode.component';
import {EventLocationComponent} from './event-location/event-location.component';
import {EventsCreateBlocklyComponent} from './events-create-blockly/events-create-blockly.component';
import {QRGenerator} from '../../../services/QRGenerator';

@Component({
  selector: 'app-events-create',
  templateUrl: './events-create.page.html',
  styleUrls: ['./events-create.page.scss'],
})
export class EventsCreatePage implements AfterViewInit  {
  @ViewChild('mapElement',{static:false}) mapElement;
  googleMaps;
  mapOptions;
  map;
  mapMarker;
  markers= [];
  geocodes= [];
  selected=[];
  isBlockly = false;
  challengeHidden=true;
  height='0%';
  minDate;
  minEndDate;
  timeLimit=null;
  blockly ={
    testCases:[],
    blocks:[],
    problemDescription:''
  };
  request: CreateEventRequest = {
    beginDate: '',
    description: '',
    geoCodesToFind: [],
    location: {latitude: 0,longitude: 0},
    name: '',
    orderBy: 'GIVEN',
    endDate:null,
    properties:{}
  };
  constructor(
    private modalController: ModalController,
    private navCtrl: NavController,
    private geocodeApi: GeoCodeService,
    private mapsLoader: GoogleMapsLoader,
    private toastController: ToastController,
    private eventApi: EventService,
    private qrGenerator: QRGenerator,
    private alertCtrl: AlertController,
    private pickerCtrl: PickerController
  ) {
  }

  //Create map and add mapmarkers of geocodes
  loadMap(){
    this.markers= [];
    this.mapOptions = {
      center: {lat: -25.75625115327836, lng: 28.235629260918344},
      zoom: 15,
    };
    this.map = new this.googleMaps.Map(this.mapElement.nativeElement,this.mapOptions);

  }

  async ngAfterViewInit() {
    this.googleMaps = await this.mapsLoader.load();
    this.loadMap();
    this.minDate= new Date().toISOString();
    this.minEndDate= new Date().toISOString();

  }

  async createGeoCode() {
    const modal = await this.modalController.create({
      component: CreateGeocodeComponent,
      swipeToClose: true,
      componentProps: {eventGeoCode:true}
    });
    await modal.present();
    const { data } = await modal.onDidDismiss();
    if (data != null) {
      this.geocodes.push(data);
      this.request.geoCodesToFind.push(data);
    }
  }

  async selectLocation(){
    const modal = await this.modalController.create({
      component: EventLocationComponent,
      swipeToClose: true,
      componentProps: {}
    });
    await modal.present();
    const { data } = await modal.onDidDismiss();
    if (data != null) {
      this.request.location.latitude=data.getPosition().lat();
      this.request.location.longitude=data.getPosition().lng();
    }
  }

  orderBy($event){
    this.request.orderBy=$event.detail.value;
  }

  startDate($event){
    const date = new Date($event.detail.value);
    this.request.beginDate=date.toISOString().split('T')[0];
    this.minEndDate=$event.detail.value;
  }

  endDate($event){
    const date = new Date($event.detail.value);
    this.request.endDate=date.toISOString().split('T')[0];
  }

  async createEvent(){

    if(this.isBlockly){
      this.request.properties.testCases = JSON.stringify(this.blockly.testCases);
      this.request.properties.problemDescription = this.blockly.problemDescription;
      this.request.properties.blocks = JSON.stringify(this.blockly.blocks);
    }
    const day = this.timeLimit.days;
    const hour = this.timeLimit.hours;
    const min = this.timeLimit.minutes;
    this.request.properties.timeLimit = ''+(day*24*60+hour*60+min);
    console.log(this.request);
    const response = await this.eventApi.createEvent(this.request).toPromise();
    if (response.success) {
      for (const geocode of response.geocodes) {
        await this.qrGenerator.download(geocode.qrCode, geocode.description);
      }
      this.navCtrl.navigateBack('/events').then().catch();
    } else {
      console.log(response);
      const alert = await this.alertCtrl.create({
        header: 'Error',
        message: response.message,
        buttons: ['OK']
      });
      await alert.present();
    }

  }

  setName($event){
    this.request.name=$event.detail.value;
  }

  setDescription($event){
    this.request.description=$event.detail.value;
  }

 async createBlockly(){
    const modal = await this.modalController.create({
      component: EventsCreateBlocklyComponent,
      swipeToClose: true,
      componentProps: {}
    });
    await modal.present();
    const { data } = await modal.onDidDismiss();
    if (data) {
      this.blockly.blocks = data.blocks;
      this.blockly.testCases = data.testCases;
    }
    console.log(this.blockly);
  }

  updateProblemDescription($event){
    this.blockly.problemDescription=$event.detail.value;
  }

  async selectTimeLimit() {
    const daysColumn = { name: 'days', options: [] };
    for (let i = 0; i < 7; i++) { daysColumn.options.push({ text: i+'d', value: i }); }
    const hoursColumn = { name: 'hours', options: [] };
    for (let i = 0; i < 24; i++) { hoursColumn.options.push({ text: i+'h', value: i }); }
    const minutesColumn = { name: 'minutes', options: [] };
    for (let i = 0; i < 60; i++) { minutesColumn.options.push({ text: i+'m', value: i }); }
    const picker = await this.pickerCtrl.create({
      columns: [daysColumn, hoursColumn, minutesColumn],
      buttons: [{
        text: 'Cancel'
      }, {
        text: 'Confirm',
        handler: (value) => {
          this.timeLimit = {
            days: value.days.value,
            hours: value.hours.value,
            minutes: value.minutes.value
          };
        },
      }],
    });

    await picker.present();
  }


}
