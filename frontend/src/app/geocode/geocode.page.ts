import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
declare let google;
@Component({
  selector: 'app-geocode',
  templateUrl: './geocode.page.html',
  styleUrls: ['./geocode.page.scss'],
})
export class GeocodePage implements AfterViewInit  {
  @ViewChild('mapElement',{static:false}) mapElement;

  map;

  mapOptions = {
    center: { lat: -34.397, lng: 150.644 },
    zoom: 8,
  };
  constructor() { }

  loadMap(){
    this.map = new google.maps.Map(this.mapElement.nativeElement,this.mapOptions);
  }

  ngAfterViewInit(): void {
     this.loadMap();
  }

}
