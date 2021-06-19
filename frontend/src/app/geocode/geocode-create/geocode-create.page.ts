import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {GeoCodeService, CreateGeoCodeRequest, CreateGeoCodeResponse} from "../../../swagger/client";
import {NavController} from "@ionic/angular";
import {closestNode} from "@angular/core/schematics/utils/typescript/nodes";

declare let google;
let map;

let mapMarker;
@Component({
  selector: 'app-geocode-create',
  templateUrl: './geocode-create.page.html',
  styleUrls: ['./geocode-create.page.scss'],
})



export class GeocodeCreatePage implements AfterViewInit {
@ViewChild('mapElement',{static:false}) mapElement;
//@ViewChild('location',{static:false}) location;
locations;
mapOptions;
hints = [];
difficulty;
request : CreateGeoCodeRequest= {
  description: 'Testing insert',
    available: true,
    difficulty:"EASY",
    hints:["Hint1","Hint2"],
    location:"",
    id:""

};

  constructor(public geocodeAPI: GeoCodeService,public navCtrl:NavController) { }


  // AfterViewInit() {
  //   initMap();
  // }

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

  ngAfterViewInit(): void {
    this.initMap();
  }

  updateRequest(e: any, field: ('location'| 'description')){
    this.request[field] = e.target.value;
  }

  createGeoCode(){
    this.request['location']=this.locations.lat + ',' + this.locations.lng;
    this.request['hints']=this.hints;
    this.request['difficulty'] = this.difficulty;
    console.log(this.request);
    this.geocodeAPI.createGeoCode(this.request)
      .subscribe((response:CreateGeoCodeResponse) =>{
        console.log(response);
        this.navCtrl.navigateForward('/geocode');
      });

  }

  updateLocation(location){
    this.locations=location;
  }

  updateDifficulty(event){
    this.difficulty=event;
  }

  updateHints(event){
     this.hints.push(event.target.value);
  }

}


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




