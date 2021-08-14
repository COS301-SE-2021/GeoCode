import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {
  CreateEventRequest, CreateEventResponse, CreateGeoCodeRequest,
  CreateGeoCodeResponse,
  EventService,
  GeoCode,
  GeoCodeService
} from '../../../services/geocode-api';
import {ModalController, NavController, ToastController} from '@ionic/angular';
import {GoogleMapsLoader} from '../../../services/GoogleMapsLoader';
import {CreateGeocodeComponent} from './create-geocode/create-geocode.component';
import {EventLocationComponent} from './event-location/event-location.component';

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
  challengeHidden=true;
  height='0%';
  minDate;
  minEndDate;
  timeLimit=0;
  // @ts-ignore
  request: CreateEventRequest = {
    beginDate: '',
    description: '',
    geoCodesToFind: [],
    location: {latitude: 0,longitude: 0},
    name: '',
    orderBy: 'GIVEN',
    endDate:null
  };
  constructor(      private modalController: ModalController,
                    private navCtrl: NavController,
                    private geocodeApi: GeoCodeService,
                    private mapsLoader: GoogleMapsLoader,
                    private toastController: ToastController,
                    private eventApi: EventService) {

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
    const date= new Date();

    this.minDate= new Date().toISOString();
    this.minEndDate= new Date().toISOString();

  }

  async createGeoCode() {
    const modal = await this.modalController.create({
      component: CreateGeocodeComponent,
      swipeToClose: true,
      componentProps: {}
    });
    await modal.present();
    const { data } = await modal.onDidDismiss();
    if (data != null) {
      console.log(data);
      this.geocodes.push(data);
      this.geocodeApi.createGeoCode(data)
        .subscribe(async (response: CreateGeoCodeResponse) =>{
            const toast =  await this.toastController.create({
              message: 'GeoCode Created',
              duration: 2000
            });
            await toast.present();
            this.request.geoCodesToFind.push(response.geoCodeID);
            console.log(response);
            //create QR code image
        });
    }else{
      console.log('Null');
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
      console.log(this.request);
    }else{
      console.log('Null');
    }
  }

  eventType($event){
    console.log($event.detail.value);
    this.type=$event.detail.value;
    if($event.detail.value =='timetrial'){
      this.challengeHidden=true;
      this.timeHidden=false;
    }else if($event.detail.value == 'challenge'){
      this.challengeHidden=false;
      this.timeHidden=true;
    }else{
      this.timeHidden=true;
      this.challengeHidden=true;
    }
  }

  orderBy($event){
    console.log($event.detail.value);
    this.request.orderBy=$event.detail.value;
  }

  startDate($event){
const date = new Date($event.detail.value);
//this.request.beginDate=date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
    this.request.beginDate=date.toISOString().split('T')[0];
console.log(this.request.beginDate);
this.minEndDate=$event.detail.value;
  }

  endDate($event){
    const date = new Date($event.detail.value);
    //this.request.endDate=date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
    this.request.endDate=date.toISOString().split('T')[0];
  }

  showGeoCodes(){

  }

  createEvent(){
    // eslint-disable-next-line eqeqeq
    if(this.type=='event'){
      console.log(this.request);
      this.eventApi.createEvent(this.request).subscribe((response: CreateEventResponse) =>{

        console.log(response);
      });
      // eslint-disable-next-line eqeqeq
    }else if(this.type =='timetrial'){
      // @ts-ignore
      // const timeRequest: CreateTimeTrialRequest={
      //   beginDate: this.request.beginDate,
      //   description: this.request.description,
      //   endDate: this.request.endDate,
      //   geoCodesToFind: this.request.geoCodesToFind,
      //   location: this.request.location,
      //   name: this.request.name,
      //   orderBy: this.request.orderBy,
      //   timeLimit: this.timeLimit
      //
      // };
      // this.eventApi.createTimeTrial(timeRequest).subscribe((response: CreateTimeTrialResponse) =>{
      //   console.log(response);
      // });
    }else{
    //challenge wow factor demo 4
    }

  }

  setName($event){
    this.request.name=$event.detail.value;
  }

  setDescription($event){
    this.request.description=$event.detail.value;
  }

}
