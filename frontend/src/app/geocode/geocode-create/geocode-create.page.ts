import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';

declare let google;
let map;

let mapOptions;
let mapMarker;
@Component({
  selector: 'app-geocode-create',
  templateUrl: './geocode-create.page.html',
  styleUrls: ['./geocode-create.page.scss'],
})



export class GeocodeCreatePage implements AfterViewInit {
@ViewChild('mapElement',{static:false}) mapElement;


  constructor() { }


  // AfterViewInit() {
  //   initMap();
  // }

  initMap() {
    // Create a map after the view is ready and the native platform is ready.

    mapOptions = {
      center: {lat: -25.75625115327836, lng: 28.235629260918344},
      zoom: 15,
    };
    map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);

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

  };

  ngAfterViewInit(): void {
    this.initMap();
  }


}


function placeMarker(location){
  const marker = new google.maps.Marker({
    map,
    animation: google.maps.Animation.DROP,
    position: location
  });


  const infoWindow = new google.maps.InfoWindow({
    content: 'Help Me!'
  });

  map.setCenter(location);
  console.log(location);
}
