import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {GoogleMapsLoader} from '../../../services/GoogleMapsLoader';
import {NavController} from '@ionic/angular';
import {
  EventService, GeoCode,
  GeoCodeService,
  GetCurrentEventLevelRequest,
  GetCurrentEventLevelResponse
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
                  private navCtrl: NavController,
                  private geocodeApi: GeoCodeService,
                  private mapsLoader: GoogleMapsLoader,
                  private eventApi: EventService,
                  private keycloak: KeycloakService,) {
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

  ngAfterViewInit(): void {
    if(this.event== null){
    console.log('error');
    }else{
      const levelReq: GetCurrentEventLevelRequest ={
        eventID:this.event.id,
        userID:this.keycloak.getKeycloakInstance().subject
      };
      this.eventApi.getCurrentEventLevel(levelReq).subscribe((response: GetCurrentEventLevelResponse) =>{
        console.log(response);
        if(response.found){
          this.geocodes.push(response.foundGeoCode);
          this.mapsLoader.load().then(handle => {
            this.googleMaps = handle;
            this.loadMap();
          }).catch();
        }

      });

    }



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

}
