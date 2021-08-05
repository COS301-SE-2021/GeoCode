import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {GeoCodeService, CreateGeoCodeRequest, CreateGeoCodeResponse} from '../../../services/geocode-api';
import {NavController} from '@ionic/angular';
import {GoogleMapsLoader} from '../../../services/GoogleMapsLoader';

let map;
@Component({
  selector: 'app-geocode-create',
  templateUrl: './geocode-create.page.html',
  styleUrls: ['./geocode-create.page.scss'],
})

export class GeocodeCreatePage implements AfterViewInit {
@ViewChild('mapElement',{static:false}) mapElement;
googleMaps;
locations;
mapOptions;
hints = [];
difficulty;
marker;

//Request object to be updated as fields change
request: CreateGeoCodeRequest= {
    description: 'Testing insert',
    available: true,
    difficulty:'EASY',
    hints:['Hint1','Hint2'],
    location:'',
    id:''
};

  constructor(public geocodeAPI: GeoCodeService,public navCtrl: NavController, private mapsLoader: GoogleMapsLoader) {}

  //create map
  initMap() {
    // Create a map after the view is ready and the native platform is ready.
    this.mapOptions = {
      center: {lat: -25.75625115327836, lng: 28.235629260918344},
      zoom: 15,
    };

    map = new this.googleMaps.Map(this.mapElement.nativeElement, this.mapOptions);

    //Add event listeners to map
    this.googleMaps.event.addListener(map, 'click', (event)=>{
      this.placeMarker(event.latLng);
    });


  };

  //create map
  ngAfterViewInit(): void {
    this.mapsLoader.load().then(handle => {
      this.googleMaps = handle;
      this.initMap();
    }).catch();
  }

  //update the description field for the request
  updateRequest(e: any, field: ('description')){
    this.request[field] = e.target.value;
  }

  //create the geocode and update the remaining fields
  createGeoCode(){
    this.locations=this.marker.getPosition();
    this.request.location=this.locations.lat()+','+this.locations.lng();
    this.request.hints=this.hints;
    this.request.difficulty = this.difficulty;

    //Call geocode api to send request to controller
    this.geocodeAPI.createGeoCode(this.request)
      .subscribe((response: CreateGeoCodeResponse) =>{
        this.hints=[];
        this.locations=[];
        this.difficulty=[];
        this.navCtrl.navigateBack('/explore').then().catch();
      });

  }

  //update difficulty field for request
  updateDifficulty(event){
    this.difficulty=event;
  }

  //update hints array with profile input
  updateHints(event){
     this.hints.push(event.target.value);
  }

  //Place map marker based on user click listener
  placeMarker(location){
    this.marker = new this.googleMaps.Marker({
        map,
        animation: this.googleMaps.Animation.DROP,
        position: location
      }
    );
    map.setCenter(location);
  }

}






