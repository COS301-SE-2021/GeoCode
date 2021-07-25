import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {GoogleMapsLoader} from '../../services/GoogleMapsLoader';

@Component({
  selector: 'app-events',
  templateUrl: './events.page.html',
  styleUrls: ['./events.page.scss'],
})
export class EventsPage implements AfterViewInit {
  @ViewChild('mapElement',{static:false}) mapElement;
  googleMaps;
  mapOptions;
  map;
  mapMarker;
  markers= [];
  isHidden=false;
  height='92%';
  constructor(private mapsLoader: GoogleMapsLoader) { }

  //Create map and add mapmarkers of geocodes
  loadMap(){
    this.markers= [];
    this.mapOptions = {
      center: {lat: -25.75625115327836, lng: 28.235629260918344},
      zoom: 15,
    };
    this.map = new this.googleMaps.Map(this.mapElement.nativeElement,this.mapOptions);

  }

  ngAfterViewInit(): void {
    this.mapsLoader.load().then(handle => {
      this.googleMaps = handle;
      this.loadMap();
    }).catch();
  }

}
