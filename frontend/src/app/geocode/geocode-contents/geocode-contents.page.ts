import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {GeoCodeService, GetHintsRequest,GetHintsResponse} from "../../../swagger/client";
declare let google;
@Component({
  selector: 'app-geocode-contents',
  templateUrl: './geocode-contents.page.html',
  styleUrls: ['./geocode-contents.page.scss'],
})
export class GeocodeContentsPage implements AfterViewInit {
  @ViewChild('mapElement',{static:false}) mapElement;
  @ViewChild('Container',{static:false}) container;
  map;
  geocode;
  mapOptions;
  hints=[];
  isHidden=false;


  constructor(private route: ActivatedRoute,public geocodeApi:GeoCodeService) {
    //Get passed in param from routing
    this.route.queryParams.subscribe(params => {
          //Set the geocode to the passed in geocode
          this.geocode= params.geocode;
          //Create Hint request
          const hintsRequest: GetHintsRequest={
            geoCodeID: this.geocode.id
          };

      //Get the hints for the passed in geocode by id
          this.geocodeApi.getHints(hintsRequest)
            .subscribe((response : GetHintsResponse)=>{
              //log response and set hints array
              console.log(response);
              this.hints=response.hints;

            } ,(error)=>{
              //If error getting hints log error and put error message in hints array
              console.log(error);
              this.hints=["Error loading hints"];
            });
    });

  }

  //Create map and add mapmarkers of geocodes
  loadMap(){
    //Create map and center towards passed in geocode
    this.mapOptions = {
      center: {lat: this.geocode.lat, lng: this.geocode.long},
      zoom: 18,
    };
  //Create map
   this.map = new google.maps.Map(this.mapElement.nativeElement,this.mapOptions);
   //Create map marker at geocode location
   new google.maps.Marker({
      position: {lat: this.geocode.lat, lng: this.geocode.long},
      map: this.map,
      title: '',
    });
  }

  ngAfterViewInit(): void {
    this.loadMap();
  }

  found(){
    this.isHidden=true;
  }

}
