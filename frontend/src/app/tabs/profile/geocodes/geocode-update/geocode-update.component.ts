import { Component, OnInit } from '@angular/core';
import {GeoCodeService, UpdateGeoCodeRequest, UpdateGeoCodeResponse} from '../../../../services/geocode-api';

@Component({
  selector: 'app-geocode-update',
  templateUrl: './geocode-update.component.html',
  styleUrls: ['./geocode-update.component.scss'],
})
export class GeocodeUpdateComponent implements OnInit {
  updateRequest: UpdateGeoCodeRequest ={
    available: false,
    description: '',
    difficulty: 'EASY',
    geoCodeID: '',
    hints: [],
    location: {latitude:0,longitude:0}
  };
  constructor(private geocodeAPI: GeoCodeService) { }

  ngOnInit() {}

  updateDescription($event){
    this.updateRequest.description = $event.detail.value;
  }

  updateHints($event){

  }

  updateDifficulty($event){
    this.updateRequest.difficulty=$event.detail.value;
  }

  updateGeoCode(){
    this.geocodeAPI.updateGeoCode(this.updateRequest).subscribe((response: UpdateGeoCodeResponse)=>{
      console.log(response);
    });
  }

}
