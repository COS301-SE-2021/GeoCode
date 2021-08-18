import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ModalController, NavParams} from '@ionic/angular';
import {Collectable} from '../../../services/geocode-api';
import {GoogleMapsLoader} from '../../../services/GoogleMapsLoader';

@Component({
  selector: 'app-trackable-locations',
  templateUrl: './trackable-locations.component.html',
  styleUrls: ['./trackable-locations.component.scss'],
})
export class TrackableLocationsComponent implements AfterViewInit {
  @ViewChild('mapElement',{static:false}) mapElement;
  googleMaps;
  map;
  trackable: Collectable;

  constructor(
    private modalController: ModalController,
    navParams: NavParams,
    private mapsLoader: GoogleMapsLoader
  ) {
    this.trackable = navParams.data.trackable;
  }

  ngAfterViewInit() {
    this.mapsLoader.load().then(handle => {
      this.googleMaps = handle;
      navigator.geolocation.getCurrentPosition((position) => {
        this.loadMap(position.coords.latitude, position.coords.longitude);
      }, (positionError) => {
        this.loadMap(0, 0);
      });
    }).catch();
  }

  loadMap(centreLat, centreLong) {
    const mapOptions = {
      center: {lat: centreLat, lng: centreLong},
      zoom: 5,
    };
    this.map = new this.googleMaps.Map(this.mapElement.nativeElement, mapOptions);
    for (let i = 0; i < this.trackable.pastLocations.length; i++) {
      const marker = new this.googleMaps.Marker({
        // @ts-ignore
        position: {lat:this.trackable.pastLocations[i].latitude , lng:this.trackable.pastLocations[i].longitude},
        map: this.map,
        label: (i+1)+''
      });
    }
  }

  async dismiss() {
    await this.modalController.dismiss();
  }

  convertLocationStringToLatLng(location: string) {
    const coords = location.split(' ');
    const lat = parseFloat(coords[0]);
    const lng = parseFloat(coords[1]);
    return {lat, lng};
  }

}
