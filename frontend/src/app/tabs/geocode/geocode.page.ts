import {AfterViewInit, Component, ViewChild} from '@angular/core';
import { NavController } from '@ionic/angular';
import {NavigationExtras} from '@angular/router';
import {
  GeoCodeService,
  GetGeoCodesByDifficultyResponse,
  GetGeoCodesResponse,
  UpdateAvailabilityRequest,
  UpdateAvailabilityResponse,
  GetGeoCodesByDifficultyRequest
} from '../../services/geocode-api';

declare let google;
@Component({
  selector: 'app-geocode',
  templateUrl: './geocode.page.html',
  styleUrls: ['./geocode.page.scss'],
})

export class GeocodePage implements AfterViewInit  {
  @ViewChild('mapElement',{static:false}) mapElement;
  mapOptions;
  map;
  mapMarker;
  markers= [];
  geocodes;
  selected=[];


  constructor(public navCtrl: NavController,public geocodeApi: GeoCodeService) {}

  //Create map and add mapmarkers of geocodes
  loadMap(){
    this.markers= [];
    this.mapOptions = {
      center: {lat: -25.75625115327836, lng: 28.235629260918344},
      zoom: 15,
    };
    this.map = new google.maps.Map(this.mapElement.nativeElement,this.mapOptions);

  }

  ngAfterViewInit(): void {
     this.loadMap();
  }


  //Add geocode to selected array to display its contents to user
  addToSelected(geocode){
    this.selected= [];
    this.selected.push(geocode);
  }

  //Navigate to findGeoCode page
  findGeoCode(geocode){
    const navigationExtras: NavigationExtras = {
      queryParams: {
        geocode
      }
    };
   this.navCtrl.navigateForward('/geocode/geocode-contents',navigationExtras);
  }

  //navigate to the create geocode page
  createGeoCode(){
    this.navCtrl.navigateForward('/geocode/geocode-create');
  }

  //Call Geocode service and update Availability
  updateAvailability(geocode){
    //create request object to update the availability
    const request: UpdateAvailabilityRequest={
      geoCodeID: geocode.id,
      isAvailable: geocode.available
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
        const marker=new google.maps.Marker({
          position: {lat: parseFloat(code.latitude), lng:parseFloat( code.longitude)},
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
  difficultMap(){
    this.clearMarkers();
    const request: GetGeoCodesByDifficultyRequest={
      difficulty: 'DIFFICULTY'
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

    this.mapOptions = {
      center: {lat: -25.75625115327836, lng: 28.235629260918344},
      zoom: 15,
    };

    this.map = new google.maps.Map(this.mapElement.nativeElement,this.mapOptions);
    this.geocodeApi.getGeoCodesByDifficulty(request).subscribe((response: GetGeoCodesByDifficultyResponse)=>{

      this.geocodes=response.geocodes;
      this.selected=[];

      //Add all geocodes locations to map
      for(const code of this.geocodes){
        const marker=new google.maps.Marker({
          position: {lat: parseFloat(code.latitude), lng:parseFloat( code.longitude)},
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

}
