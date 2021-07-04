import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';

declare let google;
@Component({
  selector: 'app-event-timetrial',
  templateUrl: './event-timetrial.page.html',
  styleUrls: ['./event-timetrial.page.scss'],
})
export class EventTimetrialPage implements AfterViewInit {
  @ViewChild('mapElement',{static:false}) mapElement;
  mapOptions;
  map;
  constructor() { }



  ngAfterViewInit(): void {
    this.loadMap();
  }

  //Create map and add mapmarkers of geocodes
  loadMap(){
    //Create map and center towards passed in geocode
    this.mapOptions = {
      center: {lat: -25.75625115327836, lng: 28.235629260918344},
      zoom: 18,
    };
    //Create map
    this.map = new google.maps.Map(this.mapElement.nativeElement,this.mapOptions);
  }

}
