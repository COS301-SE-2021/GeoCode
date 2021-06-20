import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import { AlertController } from '@ionic/angular';
import { NavController } from '@ionic/angular';
import {NavigationExtras} from '@angular/router';
import {GeoCodeService, GetGeoCodesResponse,UpdateAvailabilityRequest,UpdateAvailabilityResponse} from "../../services/geocode-api";
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
  geocodes;
  selected=[{id: '6726e306-3f47-43d1-9942-42d8024f2a47', lat: -25.75625115327836, long: 28.235629260918344,difficulty:'Medium',available: true}];


  constructor(public alertController: AlertController,public navCtrl: NavController,public geocodeApi:GeoCodeService) {}

  //Create map and add mapmarkers of geocodes
  loadMap(){
    this.mapOptions = {
      center: {lat: -25.75625115327836, lng: 28.235629260918344},
      zoom: 15,
    };
    this.map = new google.maps.Map(this.mapElement.nativeElement,this.mapOptions);
    this.geocodeApi.getGeoCodes().subscribe((response: GetGeoCodesResponse)=>{
      this.geocodes=response.geocodes;
      console.log(this.geocodes);
      for(const code of this.geocodes){
        const marker=new google.maps.Marker({
          position: {lat: code.lat, lng: code.long},
          map: this.map,
          title: '',

        });

        marker.addListener('click' , ()=> {
          this.addToSelected(code);
        });

      }
    },(error)=>{
      console.log(error);
    });
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


}
