import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {
  CreateEventRequest,
  CreateEventResponse,
  EventService,
  GeoCodeService
} from '../../../services/geocode-api';
import {ModalController, NavController, ToastController} from '@ionic/angular';
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
  type='event';
  timeHidden=true;
  blockHidden =true;
  challengeHidden=true;
  height='0%';
  minDate;
  minEndDate;
  timeLimit=0;
  blockly ={testCases:[],
  blocks:[],
    // eslint-disable-next-line @typescript-eslint/naming-convention
  problem_description:''};
  // @ts-ignore
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
  constructor(      private modalController: ModalController,
                    private navCtrl: NavController,
                    private geocodeApi: GeoCodeService,
                    private mapsLoader: GoogleMapsLoader,
                    private toastController: ToastController,
                    private eventApi: EventService,
                    private qrGenerator: QRGenerator
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

  eventType($event){
    this.type=$event.detail.value;
    if($event.detail.value ==='timetrial'){
      this.blockHidden=true;
      this.timeHidden=false;
    }else if($event.detail.value === 'challenge'){
      this.timeHidden=true;
      this.blockHidden=false;
    }else{
      this.timeHidden=true;
      this.blockHidden=true;
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

    if(this.type==='challenge'){
      this.request.properties.testCases = JSON.stringify(this.blockly.testCases);
      this.request.properties.problem_description = this.blockly.problem_description;
      this.request.properties.blocks = JSON.stringify(this.blockly.blocks);
    }else if(this.type ==='timetrial'){
      this.request.properties.timeLimit=this.timeLimit +'';
    }
    console.log(this.request);
    const response = await this.eventApi.createEvent(this.request).toPromise();
    if (response.success) {
      for (const geocode of response.geocodes) {
        this.qrGenerator.download(geocode.qrCode, geocode.description);
      }
      this.navCtrl.navigateBack('/events').then().catch();
    } else {
      console.log(response);
      alert(response.message);
    }

  }

  setName($event){
    this.request.name=$event.detail.value;
  }

  setDescription($event){
    this.request.description=$event.detail.value;
  }

  setTime($event){
    const time = new Date($event.detail.value);
    const day = time.getDate();
    const hour = time.getHours();
    const min = time.getMinutes();
    this.timeLimit = day*24*60+hour*60+min;
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
    this.blockly.problem_description=$event.detail.value;
  }

}
