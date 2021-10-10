import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {NavController} from '@ionic/angular';
import {
  Event,
  EventService,
  EventsNearMeRequest,
  EventsNearMeResponse,
  GetAllEventsResponse
} from '../../services/geocode-api';
import {MapAndInfoComponent} from '../../components/map-and-info/map-and-info.component';
import {Locator} from '../../services/Locator';

@Component({
  selector: 'app-events',
  templateUrl: './events.page.html',
  styleUrls: ['./events.page.scss'],
})
export class EventsPage implements AfterViewInit {

  @ViewChild('mapAndInfo', {static: false}) mapAndInfo: MapAndInfoComponent;

  googleMaps;
  map;
  markers = [];
  selected: Event = null;
  events: Event[] = [];
  listView = false;
  isHidden = false;
  height = '93%';

  constructor(private navCtrl: NavController,private eventApi: EventService, private locator: Locator) { }

  async ngAfterViewInit() {
    await this.mapAndInfo.load();
    this.googleMaps = this.mapAndInfo.getGoogleMaps();
    this.map = this.mapAndInfo.getMap();
    this.map.setZoom(10);
    await this.loadAll();
  }

  select(event) {
    this.selected = event;
    this.mapAndInfo.setInfoVisible(true);
  }

  close() {
    this.mapAndInfo.setInfoVisible(false);
  }

  async goToEvent(event: Event) {
    await this.navCtrl.navigateForward('/events/'+event.id, {state: {event}});
  }

  async goToLeaderBoard(event: Event) {
    await this.navCtrl.navigateForward('/events/'+event.id+'/leaderboard', {state: {event}});
  }

  async radius($event) {
    const rad = $event.detail.value;
    if (rad === 0) {
      await this.loadAll();
    } else {
      await this.loadDistance(rad);
    }
  }

  async loadDistance(distance: number){
    const position = await this.locator.getCurrentLocation();
    const req: EventsNearMeRequest = {
      radius: distance,
      location: { latitude: position.latitude, longitude: position.longitude }
    };
    this.eventApi.getEventsNearMe(req).subscribe((response: EventsNearMeResponse) =>{
      console.log(response);
      this.events = response.foundEvents;
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

  placeMarkers(array: Event[]) {
    for (const code of array) {
      const marker = new this.googleMaps.Marker({
        position: {lat: code.location.latitude, lng: code.location.longitude},
        map: this.map,
        title: '',

      });
      this.markers.push(marker);
      //Add listener to marker to display marker contents when clicked
      marker.addListener('click', () => {
        this.select(code);
      });
    }
  }

  clear(){
    for (const mark of this.markers) {
      mark.setMap(null);
    }
    this.markers = [];
  }

  toggleList(){
    this.listView= !this.listView;
  }

}
