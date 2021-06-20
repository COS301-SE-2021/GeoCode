import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {GeoCodeService, CreateGeoCodeRequest, CreateGeoCodeResponse} from "../../../services/geocode-api";
import {NavController} from "@ionic/angular";

declare let google;
let map;
@Component({
  selector: 'app-geocode-create',
  templateUrl: './geocode-create.page.html',
  styleUrls: ['./geocode-create.page.scss'],
})



export class GeocodeCreatePage implements AfterViewInit {
@ViewChild('mapElement',{static:false}) mapElement;
locations;
mapOptions;
hints = [];
difficulty;
request : CreateGeoCodeRequest= {
  description: 'Testing insert',
    available: true,
    difficulty:"EASY",
    hints:["Hint1","Hint2"],
    latitude:"",
  longitude:"",
    id:""

};

  constructor(public geocodeAPI: GeoCodeService,public navCtrl:NavController) { }

  //create map
  initMap() {
    // Create a map after the view is ready and the native platform is ready.
    this.mapOptions = {
      center: {lat: -25.75625115327836, lng: 28.235629260918344},
      zoom: 15,
    };
    map = new google.maps.Map(this.mapElement.nativeElement, this.mapOptions);

    //Load the markers
    const markerIcon = {

      scaledSize: new google.maps.Size(60, 60),
      origin: new google.maps.Point(0, 0), // used if icon is a part of sprite, indicates image position in sprite
      anchor: new google.maps.Point(20,40) // lets offset the marker image
    };

    // eslint-disable-next-line prefer-arrow/prefer-arrow-functions
    google.maps.event.addListener(map, 'click', function(event){
      placeMarker(event.latLng);

    });
    google.maps.event.addListener(map, 'click', ()=> {

      this.updateLocation(this.mapOptions.center);
    });

  };

  //create map
  ngAfterViewInit(): void {
    this.initMap();
  }

  //update the description field for the request
  updateRequest(e: any, field: ('description')){
    this.request[field] = e.target.value;
  }

  //create the geocode and update the remaining fields
  createGeoCode(){
    this.request['latitude']=this.locations.lat;
    this.request['longitude']=this.locations.lng;
    this.request['hints']=this.hints;
    this.request['difficulty'] = this.difficulty;
    console.log(this.request);
    this.geocodeAPI.createGeoCode(this.request)
      .subscribe((response:CreateGeoCodeResponse) =>{
        console.log(response);
        this.navCtrl.navigateForward('/geocode');
      });

  }

  //update location for the request
  updateLocation(location){
    this.locations=location;
  }

  //update difficulty field for request
  updateDifficulty(event){
    this.difficulty=event;
  }

  //update hints array with profile input
  updateHints(event){
     this.hints.push(event.target.value);
  }

}

//Place the marker in the users selected location and update that maps center
function placeMarker(location){
  const marker = new google.maps.Marker({
    map,
    animation: google.maps.Animation.DROP,
    position: location
  }
  );


  const infoWindow = new google.maps.InfoWindow({
    content: 'Help Me!'
  });

  map.setCenter(location);

}




