import {AfterViewInit, Component, ViewChild} from '@angular/core';
import { NavController } from '@ionic/angular';
import {NavigationExtras} from '@angular/router';
import {
  GeoCodeService,
  GetGeoCodesByDifficultyResponse,
  GetGeoCodesResponse,
  UpdateAvailabilityRequest,
  UpdateAvailabilityResponse,
  GetGeoCodesByDifficultyRequest, GeoCode
} from '../../services/geocode-api';
import {GoogleMapsLoader} from '../../services/GoogleMapsLoader';
import {KeycloakService} from 'keycloak-angular';

@Component({
  selector: 'app-geocode',
  templateUrl: './geocode.page.html',
  styleUrls: ['./geocode.page.scss'],
})

export class GeocodePage implements AfterViewInit  {
  @ViewChild('mapElement',{static:false}) mapElement;
  googleMaps;
  mapOptions;
  map;
  mapMarker;
  markers= [];
  geocodes: GeoCode[] = [];
  selected=[];
  isHidden=true;
  height='60%';
  listView = false;


  constructor(
    private navCtrl: NavController,
    private geocodeApi: GeoCodeService,
    private mapsLoader: GoogleMapsLoader,
    private keycloak: KeycloakService
  ) {
    this.geocodes = [];
    this.selected= this.geocodes;
    this.close();
  }

  //Create map and add mapmarkers of geocodes
  loadMap(latitude: number, longitude: number){
    this.markers= [];
    this.mapOptions = {
      center: {lat: latitude, lng: longitude},
      zoom: 10,
    };
    this.map = new this.googleMaps.Map(this.mapElement.nativeElement,this.mapOptions);

  }

  async ngAfterViewInit() {
    this.googleMaps = await this.mapsLoader.load();
    navigator.geolocation.getCurrentPosition(async (position) => {
      await this.loadMap(position.coords.latitude, position.coords.longitude);
      this.getAllMap();
    }, async (positionError) => {
      await this.loadMap(0, 0);
      this.getAllMap();
    });
  }


  //Add geocode to selected array to display its contents to user
  addToSelected(geocode){
    this.selected= [];
    this.selected.push(geocode);
    this.isHidden=false;
    this.height='60%';
  }

  //Navigate to findGeoCode page
  async findGeoCode(geocode){
    await this.navCtrl.navigateForward('/explore/open/'+geocode.id,{ state: {geocode} });
  }

  //Call Geocode service and update Availability
  updateAvailability(geocode){
    //create request object to update the availability
    const request: UpdateAvailabilityRequest={
      geoCodeID: geocode.id,
      available: geocode.available
    };

    //Call the geocodeAPI and send request to controller and log any errors
    this.geocodeApi.updateAvailability(request).subscribe((response: UpdateAvailabilityResponse)=>{},(error)=>{
      console.log(error);
    });

  }
  //Get all geocodes no matter the difficulty
  getAllMap(){
    this.geocodeApi.getGeoCodes().subscribe((response: GetGeoCodesResponse)=>{
      this.geocodes=response.geocodes;
      this.selected=[];

      //Add markers to map
      for(const code of this.geocodes){
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

    },(error)=>{
      console.log(error);
    });
  }

  //Get all geocodes that have Easy Difficulty
  easyMap(){
    this.clearMarkers();
    const request: GetGeoCodesByDifficultyRequest={
      difficulty: 'EASY'
    };
    this.loadFilterMap(request);
  }

  //Get all geocodes that are medium difficulty
  mediumMap(){
    this.clearMarkers();
    const request: GetGeoCodesByDifficultyRequest={
      difficulty: 'MEDIUM'
    };
    this.loadFilterMap(request);
  }

  //get all geocodes with difficult difficulty
  hardMap(){
    this.clearMarkers();
    const request: GetGeoCodesByDifficultyRequest={
      difficulty: 'HARD'
    };
    this.loadFilterMap(request);
  }

  //Get all geocodes with insane difficulty
  insaneMap(){
    this.clearMarkers();
    const request: GetGeoCodesByDifficultyRequest={
      difficulty: 'INSANE'
    };
    this.loadFilterMap(request);
  }

  //Load map based on passed in request object created in one of the map functions
  loadFilterMap(request){

    // this.mapOptions = {
    //   center: {lat: -25.75625115327836, lng: 28.235629260918344},
    //   zoom: 15,
    // };

    this.map = new this.googleMaps.Map(this.mapElement.nativeElement,this.mapOptions);
    this.geocodeApi.getGeoCodesByDifficulty(request).subscribe((response: GetGeoCodesByDifficultyResponse)=>{

      this.geocodes=response.geocodes;
      this.selected=[];

      //Add all geocodes locations to map
      for(const code of this.geocodes){
        const marker=new this.googleMaps.Marker({
          position: {lat: parseFloat(String(code.location.latitude)), lng:parseFloat( String(code.location.longitude))},
          map: this.map,
          title: '',
        });

        this.markers.push(marker);

        //Add listener event for when geocode selected to display its contents
        marker.addListener('click' , ()=> {
          this.addToSelected(code);
        });

      }
    },(error)=>{
      console.log(error);
    });
  }

  //Clear all markers from the map
  clearMarkers(){
    for(const marker of this.markers){
      marker.setMap(null);
    }
    this.markers=[];
  }

  close(){
    this.isHidden=true;
    this.height='90%';
  }

  openInMaps(geocode: GeoCode) {
    window.open('https://www.google.com/maps/search/?api=1&query='+geocode.location.latitude+'%2C'+geocode.location.longitude);
  }

  isAdmin() {
    return this.keycloak.isUserInRole('Admin');
  }

  toggleList(){
  this.listView= !this.listView;
  }

}
