import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {GoogleMapsLoader} from '../../services/GoogleMapsLoader';
import {AsynchronouslyInitialisedComponent} from '../async-component-base';

@Component({
  selector: 'app-map-and-info',
  templateUrl: './map-and-info.component.html',
  styleUrls: ['./map-and-info.component.scss'],
})
export class MapAndInfoComponent extends AsynchronouslyInitialisedComponent implements OnInit {

  @Input() showInfo = false;

  @ViewChild('mapElement',{static:false}) mapElement;
  googleMaps;
  map;

  constructor(
    private mapsLoader: GoogleMapsLoader
  ) {
    super();
  }

  async ngOnInit() {
    this.googleMaps = await this.mapsLoader.load();
    navigator.geolocation.getCurrentPosition(async (position) => {
      await this.loadMap(position.coords.latitude, position.coords.longitude);
      this.componentLoaded();
    }, async (positionError) => {
      await this.loadMap(0, 0);
      this.componentLoaded();
    });
  }

  async loadMap(centreLat, centreLong) {
    const mapOptions = {
      center: {lat: centreLat, lng: centreLong},
      zoom: 5,
    };
    this.map = new this.googleMaps.Map(this.mapElement.nativeElement, mapOptions);
  }

  public setInfoVisible(visible: boolean) {
    this.showInfo = visible;
  }

  public getMap() {
    return this.map;
  }

  public getGoogleMaps() {
    return this.googleMaps;
  }

  mapSizeMD() {
    if (this.showInfo) {
      return 8;
    } else {
      return 12;
    }
  }

  mapSizeLG() {
    if (this.showInfo) {
      return 9;
    } else {
      return 12;
    }
  }

  getClass() {
    if (this.showInfo && !window.matchMedia('(min-width: 768px)').matches) {
      // Only returns if the geocode details are being shown and the screen is small
      return 'splitScreen';
    } else {
      return '';
    }
  }

}
