import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
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

  constructor() {
    this.geocodes = [{id: 1, lat: -25.75625115327836, long: 28.235629260918344,difficulty:'Medium'}, {
      id: 2,
      lat: -25.755678678528565,
      long: 28.243631816539157,
      difficulty:'Easy'
    },
      {
        id: 3,
        lat: -25.75756288427446,
        long: 28.260916229007645,
        difficulty:'Hard'
      }];
  }


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
      const infowindow = new google.maps.InfoWindow({
        content:     '<div style="color: black">' +
          '<p> Difficulty:'+code.difficulty+'</p>' +
          '</div>'
      });
      marker.addListener('click' , ()=> {
        infowindow.open(this.map,marker);
      });
    }


  }

  ngAfterViewInit(): void {
     this.loadMap();
  }

}
