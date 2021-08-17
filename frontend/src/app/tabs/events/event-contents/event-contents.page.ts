import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {GoogleMapsLoader} from '../../../services/GoogleMapsLoader';
import {AlertController, NavController, ToastController} from '@ionic/angular';
import {
  EventService, GeoCode,
  GeoCodeService,
  GetCurrentEventStatusRequest,
  GetCurrentEventStatusResponse
} from '../../../services/geocode-api';
import {ActivatedRoute, Router} from "@angular/router";
import {KeycloakService} from "keycloak-angular";



@Component({
  selector: 'app-event-contents',
  templateUrl: './event-contents.page.html',
  styleUrls: ['./event-contents.page.scss'],
})
export class EventContentsPage implements AfterViewInit {
  @ViewChild('mapElement',{static:false}) mapElement;
  event=null;
  eventID=null;
  geocodes=[];
  level='';
  numLevels='';
  googleMaps;
  mapOptions;
  map;
  description='';
  name='';

  constructor(    route: ActivatedRoute,
                  router: Router,
                  private toastController: ToastController,
                  private navCtrl: NavController,
                  private geocodeApi: GeoCodeService,
                  private mapsLoader: GoogleMapsLoader,
                  private eventApi: EventService,
                  private keycloak: KeycloakService,
                  private alertController: AlertController) {
    //Get passed in param from routing
    const state = router.getCurrentNavigation().extras.state;
    console.log(state);
    if (state) {
      //Set the event to the passed in geocode
      this.event = state.event;
      this.name = this.event.name;
      this.description= this.event.description;
      console.log(this.event);
    } else {
      this.event = null;
      this.eventID = route.snapshot.paramMap.get('id');
      console.log(this.eventID);
    }
  }

  async ngAfterViewInit() {
    if(this.event== null){
      this.event = (await this.eventApi.getEvent({eventID: this.eventID}).toPromise()).foundEvent;
      this.name = this.event.name;
      this.description = this.event.description;
    }
    const levelReq: GetCurrentEventStatusRequest ={
      eventID:this.event.id,
      userID:this.keycloak.getKeycloakInstance().subject
    };
    this.eventApi.getCurrentEventStatus(levelReq).subscribe((response: GetCurrentEventStatusResponse) =>{
      console.log(response);
      if(response.success){
        if(response.targetGeocode==null){
        this.presentAlert();
        }else{
          this.geocodes.push(response.targetGeocode);
          this.mapsLoader.load().then(handle => {
            this.googleMaps = handle;
            this.loadMap();
          }).catch();
        }
      }else{
        this.navCtrl.back();
        this.presentToast();
      }
    });
  }
async presentToast(){
  const toast =  await this.toastController.create({
    message: 'Error loading Event GeoCode not found',
    duration: 2000
  });
  await toast.present();
}
  async presentAlert() {
    const alert = await this.alertController.create({
      cssClass: 'my-custom-class',
      header: 'Alert',
      subHeader: 'Subtitle',
      message: 'Congratulation you completed the event',
      buttons: ['OK']
    });

    await alert.present();

    const { role } = await alert.onDidDismiss();
    console.log('onDidDismiss resolved with role', role);
  }

  //Create map and add mapmarkers of geocodes
  loadMap(){
    //Create map and center towards passed in geocode
    this.mapOptions = {
      center: {lat: this.event.location.latitude, lng: this.event.location.longitude},
      zoom: 15,
    };
    //Create map
    this.map = new this.googleMaps.Map(this.mapElement.nativeElement,this.mapOptions);
  }

  //Navigate to findGeoCode page
  async findGeoCode(geocode){
    await this.navCtrl.navigateForward('/explore/open/'+geocode.id,{ state: {geocode} });
  }

  openInMaps(geocode: GeoCode) {
    window.open('https://www.google.com/maps/search/?api=1&query='+geocode.location.latitude+'%2C'+geocode.location.longitude);
  }

  async openLeaderBoard() {
    const event = this.event;
    await this.navCtrl.navigateForward('/events/' + this.event.id+'/leaderboard', {state: {event}});
  }


}
