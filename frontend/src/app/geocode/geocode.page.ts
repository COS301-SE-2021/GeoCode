import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import { AlertController } from '@ionic/angular';
import { NavController } from '@ionic/angular';
import {NavigationExtras} from '@angular/router';
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
  mapmarker;
  geocodes;
  selected=[{id: 1, lat: -25.75625115327836, long: 28.235629260918344,difficulty:'Medium',isAvailable: true}];


  constructor(public alertController: AlertController,public navCtrl: NavController) {
    this.geocodes = [{id: 1, lat: -25.75625115327836, long: 28.235629260918344,difficulty:'Medium',isAvailable: true}, {
      id: 2,
      lat: -25.755678678528565,
      long: 28.243631816539157,
      difficulty:'Easy',
      isAvailable: true
    },
      {
        id: 3,
        lat: -25.75756288427446,
        long: 28.260916229007645,
        difficulty:'Hard',
        isAvailable: true
      }];
  }

  //Create map and add mapmarkers of geocodes
  loadMap(){
    this.mapmarker = {
      url:'/assets/images/logo.png',
      scaledSize: new google.maps.Size(22,32),

    };
    this.mapOptions = {
      center: {lat: -25.75625115327836, lng: 28.235629260918344},
      zoom: 15,
    };
    this.map = new google.maps.Map(this.mapElement.nativeElement,this.mapOptions);


    for(const code of this.geocodes){
      const marker=new google.maps.Marker({
        position: {lat: code.lat, lng: code.long},
        map: this.map,
        title: '',

      });

      // const infowindow = new google.maps.InfoWindow({
      //   content:     '<div style="color: black">' +
      //     '<p> Difficulty:'+code.difficulty+'</p>' +
      //     '<ion-button (click)="presentAlert()">Find GeoCode</ion-button>' +
      //     '</div>',
      //
      // });


      marker.addListener('click' , ()=> {
       this.addToSelected(code);
      });

    }


  }

  ngAfterViewInit(): void {
     this.loadMap();
  }

  addToSelected(geocode){
    this.selected= [];
    this.selected.push(geocode);

  }

  findGeoCode(geocode){
   console.log(geocode.id);
    const navigationExtras: NavigationExtras = {
      queryParams: {
        geocode
      }
    };
   this.navCtrl.navigateForward('/geocode/geocode-contents',navigationExtras);
  }


}
