import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ModalController, NavParams} from '@ionic/angular';
import {Collectable} from '../../../swagger/client';

declare let google;
@Component({
  selector: 'app-trackable-locations',
  templateUrl: './trackable-locations.component.html',
  styleUrls: ['./trackable-locations.component.scss'],
})
export class TrackableLocationsComponent implements AfterViewInit {
  @ViewChild('mapElement',{static:false}) mapElement;
  map;
  trackable: Collectable;

  constructor(
    private modalController: ModalController,
    navParams: NavParams
  ) {
    this.trackable = navParams.data.trackable;
  }

  ngAfterViewInit() {
    navigator.geolocation.getCurrentPosition((position) => {
      this.loadMap(position.coords.latitude, position.coords.longitude);
    }, (positionError) => {
      this.loadMap(0, 0);
    });
  }

  loadMap(centreLat, centreLong) {
    const mapOptions = {
      center: {lat: centreLat, lng: centreLong},
      zoom: 5,
    };
    this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);
    for (let i = 0; i < this.trackable.pastLocations.length; i++) {
      const marker = new google.maps.Marker({
        position: this.convertLocationStringToLatLng(this.trackable.pastLocations[i]),
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
