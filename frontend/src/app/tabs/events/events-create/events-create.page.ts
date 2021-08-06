import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {GeoCode, GeoCodeService} from '../../../services/geocode-api';
import {NavController} from '@ionic/angular';
import {GoogleMapsLoader} from '../../../services/GoogleMapsLoader';

@Component({
  selector: 'app-events-create',
  templateUrl: './events-create.page.html',
  styleUrls: ['./events-create.page.scss'],
})
export class EventsCreatePage implements AfterViewInit  {
  @ViewChild('mapElement',{static:false}) mapElement;
  googleMaps;
  mapOptions;
  map;
  mapMarker;
  markers= [];
  geocodes: GeoCode[] = [];
  selected=[];
  isHidden=true;
  height='0%';
  constructor(    private navCtrl: NavController,
                  private geocodeApi: GeoCodeService,
                  private mapsLoader: GoogleMapsLoader) { }

  //Create map and add mapmarkers of geocodes
  loadMap(){
    this.markers= [];
    this.mapOptions = {
      center: {lat: -25.75625115327836, lng: 28.235629260918344},
      zoom: 15,
    };
    this.map = new this.googleMaps.Map(this.mapElement.nativeElement,this.mapOptions);

  }

  async ngAfterViewInit() {
    this.googleMaps = await this.mapsLoader.load();
    this.loadMap();
  }


}
