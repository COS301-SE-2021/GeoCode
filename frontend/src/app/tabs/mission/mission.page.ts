import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {GoogleMapsLoader} from '../../services/GoogleMapsLoader';
import {
  GetMissionByIdRequest,
  GetMissionByIdResponse,
  GetProgressRequest, GetProgressResponse,
  MissionService,
  MissionType
} from '../../services/geocode-api';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-mission',
  templateUrl: './mission.page.html',
  styleUrls: ['./mission.page.scss'],
})
export class MissionPage implements AfterViewInit {
  @ViewChild('mapElement',{static:false}) mapElement;
  googleMaps;
  mapOptions;
  map;
  mapMarker;
  markers= [];
  selected=[];
  isHidden=false;
  height='60%';
  missionID ;
  title='';
  description='';
  progress =0;
  position ={latitude:0,longitude:0};
  constructor(private mapsLoader: GoogleMapsLoader,private missionAPI: MissionService, route: ActivatedRoute) {
    this.missionID = route.snapshot.paramMap.get('id');

  }

  //Create map and add mapmarkers of geocodes
  loadMap(){
    this.markers= [];
    this.mapOptions = {
      center: {lat: this.position.latitude, lng: this.position.longitude},
      zoom: 15,
    };
    this.map = new this.googleMaps.Map(this.mapElement.nativeElement,this.mapOptions);

  }

  ngAfterViewInit(): void {
    this.mapsLoader.load().then(handle => {
      this.googleMaps = handle;
      this.loadMap();
    }).catch();

    const req: GetMissionByIdRequest={
      missionID: this.missionID
    };

    this.missionAPI.getMissionById(req).subscribe((response: GetMissionByIdResponse)=>{
      console.log(response);
      if(response.success){

        this.position=response.mission.location;
        this.loadMap();
        const marker=new this.googleMaps.Marker({
          position: {lat: parseFloat(String(this.position.latitude)), lng:parseFloat( String(this.position.longitude))},
          map: this.map,
          title: '',
        });
        this.title= response.mission.type;
        // eslint-disable-next-line eqeqeq
        if(this.title==MissionType.Circumference){
          this.description='Help move this collectable 40 075km which is the distance around the earth.';
        }else if(this.title==MissionType.Swap){
          this.description='Help swap this collectable x times';
        }else if(this.title == MissionType.GeoCode){
          this.description='Help move this collectable to ' + response.mission.amount;
        }
      }else{

      }

    });

    const progressReq: GetProgressRequest={
      missionID:this.missionID
    };

    this.missionAPI.getProgress(progressReq).subscribe((response: GetProgressResponse)=>{
      if(response.success){
        this.progress= response.progress;
      }
    });

  }

}
