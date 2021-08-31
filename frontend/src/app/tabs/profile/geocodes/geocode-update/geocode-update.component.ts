import { Component, OnInit } from '@angular/core';
import {GeoCodeService, UpdateGeoCodeRequest} from '../../../../services/geocode-api';

@Component({
  selector: 'app-geocode-update',
  templateUrl: './geocode-update.component.html',
  styleUrls: ['./geocode-update.component.scss'],
})
export class GeocodeUpdateComponent implements OnInit {
  updateRequest : UpdateGeoCodeRequest ={
    available: false,
    description: '',
    difficulty: undefined,
    geoCodeID: '',
    hints: undefined,
    location: undefined
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
    this.geocodeAPI.updateGeoCode(this.updateRequest).subscribe((response: UpdateGeoCodeRequest)=>{
      console.log(response);
    });
  }

}
