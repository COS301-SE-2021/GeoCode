import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {GoogleMapsLoader} from '../../services/GoogleMapsLoader';
import {NavigationExtras} from '@angular/router';
import {NavController} from '@ionic/angular';

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
  selected=[];
  events=[];
  isHidden=false;
  height='60%';
  constructor(    private navCtrl: NavController,private mapsLoader: GoogleMapsLoader) {
    this.events = [{id:'123456789',latitude:-25.75625115327836,longitude:28.235629260918344,name:'Event name',
      description:'This is an event that is happening at a place and you can do stuff',leaderBoardID: '1245766'}];
    this.selected= this.events;
  }

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

  close(){
    this.isHidden=true;
    this.height='93%';
  }

  goToEvent(event){
    const navigationExtras: NavigationExtras = {
      queryParams: {
        event
      }
    };
    this.navCtrl.navigateForward('/events/event-timetrial',navigationExtras);
  }

  goToLeaderBoard(event){
    const navigationExtras: NavigationExtras = {
      queryParams: {
        event
      }
    };
    this.navCtrl.navigateForward('/events/event-leaderboard',navigationExtras);
  }

  async addEvent() {}


}
