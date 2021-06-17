import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
declare let google;
@Component({
  selector: 'app-geocode-contents',
  templateUrl: './geocode-contents.page.html',
  styleUrls: ['./geocode-contents.page.scss'],
})
export class GeocodeContentsPage implements AfterViewInit {
  @ViewChild('mapElement',{static:false}) mapElement;
  map;
  geocode;
  mapOptions;


  constructor(private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => {
          this.geocode= params.geocode;
        });
  }


  // ngOnInit() {
  //   this.route.queryParams.subscribe(params => {
  //     console.log(params);
  //   });
  // }

  //Create map and add mapmarkers of geocodes
  loadMap(){

    this.mapOptions = {
      center: {lat: this.geocode.lat, lng: this.geocode.long},
      zoom: 18,
    };

   this.map = new google.maps.Map(this.mapElement.nativeElement,this.mapOptions);

   new google.maps.Marker({
      position: {lat: this.geocode.lat, lng: this.geocode.long},
      map: this.map,
      title: '',

    });

  }

  ngAfterViewInit(): void {
    this.loadMap();
  }

}
