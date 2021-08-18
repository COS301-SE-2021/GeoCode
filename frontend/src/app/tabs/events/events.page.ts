import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {GoogleMapsLoader} from '../../services/GoogleMapsLoader';
import {NavController} from '@ionic/angular';
import {
  EventService,
  EventsNearMeRequest,
  EventsNearMeResponse,
  GetAllEventsResponse
} from '../../services/geocode-api';

@Component({
  selector: 'app-events',
  templateUrl: './events.page.html',
  styleUrls: ['./events.page.scss'],
})
export class EventsPage implements AfterViewInit {
  @ViewChild('mapElement', {static: false}) mapElement;
  googleMaps;
  mapOptions;
  map;
  mapMarker;
  markers ;
  selected = [];
  events = [];
  isHidden = false;
  height = '93%';
  position;

  constructor(private navCtrl: NavController, private mapsLoader: GoogleMapsLoader, private eventApi: EventService) {
    this.markers=[];
  }

  //Create map and add mapmarkers of geocodes
  loadMap(latitude, longitude) {
    this.markers = [];
    this.mapOptions = {
      center: {lat: latitude, lng: longitude},
      zoom: 10,
    };
    this.map = new this.googleMaps.Map(this.mapElement.nativeElement, this.mapOptions);
  }

  ngAfterViewInit(): void {

    this.mapsLoader.load().then(handle => {
      this.googleMaps = handle;
      navigator.geolocation.getCurrentPosition((position) => {
        this.position=position;
        this.loadMap(position.coords.latitude, position.coords.longitude);
      }, (positionError) => {
        this.loadMap(-25.75625115327836, 28.235629260918344);
      });
    }).catch();
      this.loadAll();
  }

  //Add geocode to selected array to display its contents to user
  addToSelected(event) {
    this.selected = [];
    this.selected.push(event);
    this.isHidden=false;
    this.height='60%';
  }

  close() {
    this.isHidden = true;
    this.height = '93%';
  }

  goToEvent(event) {
    this.navCtrl.navigateForward('/events/' + event.id, {state: {event}});
  }

  goToLeaderBoard(event) {
    this.navCtrl.navigateForward('/events/' + event.id + '/leaderboard', {state: {event}});
  }

  radius($event) {
    const rad = $event.detail.value;
    if (rad ==0) {
      this.loadAll();
    } else {
      this.loadDistance(rad);
    }
  }

  async loadDistance(distance){
    const req: EventsNearMeRequest={
      radius:distance,
      location: {latitude:this.position.coords.latitude,longitude:this.position.coords.longitude}
    } ;
    this.eventApi.getEventsNearMe(req).subscribe((response: EventsNearMeResponse) =>{
      console.log(response);
      this.events=response.foundEvents;
      this.clear();
      this.placeMarkers(this.events);
    });
  }

  async loadAll() {
    this.eventApi.getAllEvents().subscribe((response: GetAllEventsResponse) => {
      console.log(response);
      this.events = response.events;
      this.clear();
      this.placeMarkers(this.events);
    });
  }

  placeMarkers(array) {
    for (const code of array) {
      const marker = new this.googleMaps.Marker({
        position: {lat: parseFloat(String(code.location.latitude)), lng: parseFloat(String(code.location.longitude))},
        map: this.map,
        title: '',

      });
      this.markers.push(marker);
      //Add listener to marker to display marker contents when clicked
      marker.addListener('click', () => {
        this.addToSelected(code);
      });
    }
  }

  clear(){
    for (const mark of this.markers) {
      mark.setMap(null);
    }
    this.markers = [];
  }

}
