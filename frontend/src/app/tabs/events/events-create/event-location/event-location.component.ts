import {AfterViewInit, Component,  ViewChild} from '@angular/core';
import {ModalController, NavParams} from '@ionic/angular';
import {GoogleMapsLoader} from '../../../../services/GoogleMapsLoader';
let map;
let markers=[];
@Component({
  selector: 'app-event-location',
  templateUrl: './event-location.component.html',
  styleUrls: ['./event-location.component.scss'],
})
export class EventLocationComponent implements AfterViewInit {
  @ViewChild('mapElement',{static:false}) mapElement;
  googleMaps;

  marker = null;
  constructor(    private modalController: ModalController,
                  navParams: NavParams,
                  private mapsLoader: GoogleMapsLoader) {}

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
      zoom: 15,
    };
    map = new this.googleMaps.Map(this.mapElement.nativeElement, mapOptions);
    //Add event listeners to map
    this.googleMaps.event.addListener(map, 'click', (event)=>{
      this.placeMarker(event.latLng);
    });

  }

  async dismiss() {
    await this.modalController.dismiss(this.marker);
  }

  convertLocationStringToLatLng(location: string) {
    const coords = location.split(' ');
    const lat = parseFloat(coords[0]);
    const lng = parseFloat(coords[1]);
    return {lat, lng};
  }

  placeMarker(location){
    if(this.marker!= null){
      this.marker.setMap(null);
    }

    this.marker = new this.googleMaps.Marker({
        map,
        animation: this.googleMaps.Animation.DROP,
        position: location
      }
    );
      map.setCenter(location);
  }



}


