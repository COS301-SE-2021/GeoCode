import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {GoogleMapsLoader} from '../../services/GoogleMapsLoader';
import {NavigationExtras} from '@angular/router';
import {NavController} from '@ionic/angular';
import {EventService, GetAllEventsResponse} from '../../services/geocode-api';

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
  constructor(    private navCtrl: NavController,private mapsLoader: GoogleMapsLoader, private eventApi: EventService) {
    // this.events = [{id:'123456789',latitude:-25.75625115327836,longitude:28.235629260918344,name:'Event name',
    //   description:'This is an event that is happening at a place and you can do stuff',leaderBoardID: '1245766'}];
    // this.selected= this.events;
  }

  //Create map and add mapmarkers of geocodes
  loadMap(latitude, longitude){
    this.markers= [];
    this.mapOptions = {
      center: {lat: latitude, lng: longitude},
      zoom: 15,
    };
    this.map = new this.googleMaps.Map(this.mapElement.nativeElement,this.mapOptions);

  }

  ngAfterViewInit(): void {

    this.mapsLoader.load().then(handle => {
      this.googleMaps = handle;
      navigator.geolocation.getCurrentPosition((position) => {
        this.loadMap(position.coords.latitude, position.coords.longitude);
      }, (positionError) => {
        this.loadMap(-25.75625115327836, 28.235629260918344);
      });
    }).catch();
    this.eventApi.getAllEvents().subscribe((response: GetAllEventsResponse) =>{
      console.log(response);
      this.events= response.events;

      //Add markers to map
      for(const code of this.events){
        const marker=new this.googleMaps.Marker({
          position: {lat: parseFloat(String(code.location.latitude)), lng:parseFloat( String(code.location.longitude))},
          map: this.map,
          title: '',

        });

        this.markers.push(marker);
        //Add listener to marker to display marker contents when clicked
        marker.addListener('click' , ()=> {
          this.addToSelected(code);
        });
      }
    });

  }

  //Add geocode to selected array to display its contents to user
  addToSelected(event){
    this.selected= [];
    this.selected.push(event);
    //this.isHidden=false;
  //  this.height='60%';
  }

  close(){
    this.isHidden=true;
    this.height='93%';
  }

  goToEvent(event){
    this.navCtrl.navigateForward('/events/'+event.id,{state: {event}});
  }

  goToLeaderBoard(event){
    this.navCtrl.navigateForward('/events/'+event.id+'/leaderboard', {state: {event}});
  }

  async addEvent() {}

  radius($event){

  }

}
