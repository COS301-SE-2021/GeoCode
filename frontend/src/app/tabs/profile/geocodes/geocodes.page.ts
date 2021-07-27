import {Component, OnInit, ViewChild} from '@angular/core';
import {Collectable, GeoCode} from '../../../services/geocode-api';
import {GoogleMapsLoader} from '../../../services/GoogleMapsLoader';

@Component({
  selector: 'app-geocodes',
  templateUrl: './geocodes.page.html',
  styleUrls: ['./geocodes.page.scss'],
})
export class GeocodesPage implements OnInit {

  @ViewChild('mapElement',{static:false}) mapElement;
  googleMaps;
  map;

  created: GeoCode[];
  found: GeoCode[];

  constructor(
    private mapsLoader: GoogleMapsLoader
  ) {
    this.created = [null];
  }

  async ngOnInit() {
    this.googleMaps = await this.mapsLoader.load();
    navigator.geolocation.getCurrentPosition((position) => {
      this.loadMap(position.coords.latitude, position.coords.longitude);
    }, (positionError) => {
      this.loadMap(0, 0);
    });
  }

  async loadMap(centreLat, centreLong) {
    const mapOptions = {
      center: {lat: centreLat, lng: centreLong},
      zoom: 5,
    };
    this.map = new this.googleMaps.Map(this.mapElement.nativeElement, mapOptions);
    await this.loadFound();
  }

  async segmentChanged(event) {
    if (event.target.value === 'found') {
      await this.loadFound();
    } else if (event.target.value === 'created') {
      await this.loadCreated();
    }
  }

  async loadFound() {
    console.log('Showing found geocodes');
  }

  async loadCreated() {
    console.log('Showing created geocodes');
  }
}
