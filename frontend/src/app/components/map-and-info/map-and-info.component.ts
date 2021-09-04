import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {GoogleMapsLoader} from '../../services/GoogleMapsLoader';
import {AsynchronouslyInitialisedComponent} from '../async-component-base';
import {GeoCode, GeoPoint} from '../../services/geocode-api';
import {Locator} from '../../services/Locator';

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

  location: GeoPoint;
  markers = [];

  constructor(
    private mapsLoader: GoogleMapsLoader,
    private locator: Locator
  ) {
    super();
  }

  async ngOnInit() {
    this.googleMaps = await this.mapsLoader.load();
    this.location = await this.locator.getCurrentLocation();
    if (this.location === null) {
      this.location = { latitude: 0, longitude: 0 };
    }

    this.map = new this.googleMaps.Map(this.mapElement.nativeElement, {
      center: { lat: this.location.latitude, lng: this.location.longitude },
      zoom: 5,
    });
    this.componentLoaded();
  }

  public placeGeoCodes(geocodes: GeoCode[], onSelect: (geocode: GeoCode) => void) {
    for(const marker of this.markers){
      marker.setMap(null);
    }
    this.markers=[];
    for (const geocode of geocodes) {
      const marker = new this.googleMaps.Marker({
        position: { lat: geocode.location.latitude, lng: geocode.location.longitude },
        map: this.map,
        title: '',
      });
      marker.addListener('click', () => {
        console.log('yeet');
        onSelect(geocode);
      });
      this.markers.push(marker);
    }
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

  public getLocation() {
    return this.location;
  }

  private mapSizeMD() {
    if (this.showInfo) {
      return 8;
    } else {
      return 12;
    }
  }

  private mapSizeLG() {
    if (this.showInfo) {
      return 9;
    } else {
      return 12;
    }
  }

  private getClass() {
    if (this.showInfo && !window.matchMedia('(min-width: 768px)').matches) {
      // Only returns if the geocode details are being shown and the screen is small
      return 'splitScreen';
    } else {
      return '';
    }
  }

}
