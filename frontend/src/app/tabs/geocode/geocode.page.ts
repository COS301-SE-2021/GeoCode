import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import { AlertController } from '@ionic/angular';
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
import { RouterModule } from '@angular/router';

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


  constructor(public alertController: AlertController,public navCtrl: NavController,public geocodeApi: GeoCodeService) {

  }

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

  ionViewDidLoad(){

  }

  addToSelected(geocode){
    this.selected= [];
    this.selected.push(geocode);
  }

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

  //Call Geocode service and update Availibility
  updateAvailability(geocode){
    const request: UpdateAvailabilityRequest={
      geoCodeID: geocode.id,
      isAvailable: geocode.available
    };
  console.log(request);
    this.geocodeApi.updateAvailability(request).subscribe((response: UpdateAvailabilityResponse)=>{
      console.log(response);

    },(error)=>{
      console.log(error);
    });


  }

  getAllMap(){
    this.geocodeApi.getGeoCodes().subscribe((response: GetGeoCodesResponse)=>{
      this.geocodes=response.geocodes;
      console.log(this.geocodes);
      this.selected=[];
      for(const code of this.geocodes){
        const marker=new google.maps.Marker({
          position: {lat: parseFloat(code.latitude), lng:parseFloat( code.longitude)},
          map: this.map,
          title: '',

        });

        this.markers.push(marker);

        marker.addListener('click' , ()=> {
          this.addToSelected(code);
        });

      }
    },(error)=>{
      console.log(error);
    });
  }
  easyMap(){
    this.clearMarkers();
    const request: GetGeoCodesByDifficultyRequest={
      difficulty: 'EASY'
    };
    this.loadFilterMap(request);
  }

  mediumMap(){
    this.clearMarkers();
    const request: GetGeoCodesByDifficultyRequest={
      difficulty: 'MEDIUM'
    };
    this.loadFilterMap(request);
  }

  difficultMap(){
    this.clearMarkers();
    const request: GetGeoCodesByDifficultyRequest={
      difficulty: 'DIFFICULTY'
    };
    this.loadFilterMap(request);
  }

  insaneMap(){
    this.clearMarkers();
    const request: GetGeoCodesByDifficultyRequest={
      difficulty: 'INSANE'
    };
    this.loadFilterMap(request);
  }

  loadFilterMap(request){
    this.mapOptions = {
      center: {lat: -25.75625115327836, lng: 28.235629260918344},
      zoom: 15,
    };
    this.map = new google.maps.Map(this.mapElement.nativeElement,this.mapOptions);
    this.geocodeApi.getGeoCodesByDifficulty(request).subscribe((response: GetGeoCodesByDifficultyResponse)=>{
      this.geocodes=response.geocodes;
      console.log(this.geocodes);
      this.selected=[];
      for(const code of this.geocodes){
        const marker=new google.maps.Marker({
          position: {lat: parseFloat(code.latitude), lng:parseFloat( code.longitude)},
          map: this.map,
          title: '',

        });

        this.markers.push(marker);

        marker.addListener('click' , ()=> {
          this.addToSelected(code);
        });

      }
    },(error)=>{
      console.log(error);
    });
  }

  clearMarkers(){
    for(const marker of this.markers){
      marker.setMap(null);
    }
    this.markers=[];
  }


}
