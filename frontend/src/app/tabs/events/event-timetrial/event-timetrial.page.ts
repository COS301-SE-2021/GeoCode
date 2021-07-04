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
  geocodes=[{stage:1,description:'code',difficulty:'Easy',found:true},
    {stage:2,description:'code',difficulty:'Easy',found:false},{stage:3,description:'code',difficulty:'Easy',found:false},
    {stage:4,description:'code',difficulty:'Medium',found:false},{stage:5,description:'code',difficulty:'Insane',found:false}];
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
