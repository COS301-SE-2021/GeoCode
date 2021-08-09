import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {GoogleMapsLoader} from '../../../services/GoogleMapsLoader';
import {NavController} from '@ionic/angular';
import {GeoCodeService} from '../../../services/geocode-api';



@Component({
  selector: 'app-event-contents',
  templateUrl: './event-contents.page.html',
  styleUrls: ['./event-contents.page.scss'],
})
export class EventContentsPage implements AfterViewInit {
  @ViewChild('mapElement',{static:false}) mapElement;
  selected=[{stage:1,description:'code',difficulty:'Easy',found:true}];
  level="A";
  googleMaps;
  mapOptions;
  map;
  stage=2;
  points=100;
  rank=555555;
  geocodes=[{stage:1,description:'code',difficulty:'Easy',found:true},
    {stage:2,description:'code',difficulty:'Easy',found:false},{stage:3,description:'code',difficulty:'Easy',found:false},
    {stage:4,description:'code',difficulty:'Medium',found:false},{stage:5,description:'code',difficulty:'Insane',found:false}];
  constructor(    private navCtrl: NavController,
                  private geocodeApi: GeoCodeService,
                  private mapsLoader: GoogleMapsLoader) { }



  // ngAfterViewInit(): void {
  //   this.loadMap();
  // }
  ngAfterViewInit(): void {
    this.mapsLoader.load().then(handle => {
      this.googleMaps = handle;
      this.loadMap();
    }).catch();
  }

  //Create map and add mapmarkers of geocodes
  loadMap(){
    //Create map and center towards passed in geocode
    this.mapOptions = {
      center: {lat: -25.75625115327836, lng: 28.235629260918344},
      zoom: 18,
    };
    //Create map
    this.map = new this.googleMaps.Map(this.mapElement.nativeElement,this.mapOptions);
  }

  //Navigate to findGeoCode page
  async findGeoCode(geocode){
    await this.navCtrl.navigateForward('/explore/open/'+geocode.id,{ state: {geocode} });
  }

}
