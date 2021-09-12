import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {GeoCode, GeoCodeService, GetHintsRequest, GetHintsResponse} from '../../../services/geocode-api';
import {MapAndInfoComponent} from '../../../components/map-and-info/map-and-info.component';

@Component({
  selector: 'app-find-geocode',
  templateUrl: './find-geocode.page.html',
  styleUrls: ['./find-geocode.page.scss'],
})
export class FindGeocodePage implements AfterViewInit {

  @ViewChild('mapAndInfo', {static: false}) mapAndInfo: MapAndInfoComponent;

  map;
  googleMaps;
  geocode: GeoCode = null;
  geocodeID: string = null;
  hints=[];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private geocodeService: GeoCodeService
  ) {
    const state = this.router.getCurrentNavigation().extras.state;
    if (state) {
      //Set the geocode to the passed in geocode
      this.geocode = state.geocode;
    } else {
      this.geocode = null;
      this.geocodeID = this.route.snapshot.paramMap.get('geocodeID');
    }
  }

  async ngAfterViewInit() {
    await this.mapAndInfo.load();

    if (this.geocode === null) {
      this.geocode = (await this.geocodeService.getGeoCode({geoCodeID: this.geocodeID}).toPromise()).foundGeoCode;
    }
    const getHintsRequest: GetHintsRequest ={
      geoCodeID:this.geocode.id
    };

    this.geocodeService.getHints(getHintsRequest).subscribe((response: GetHintsResponse) =>{
      this.geocode.hints = response.hints;
    });

    this.map = this.mapAndInfo.getMap();
    this.googleMaps = this.mapAndInfo.getGoogleMaps();

    const latLng = {lat: this.geocode.location.latitude, lng: this.geocode.location.longitude};

    this.map.setOptions({
      center: latLng,
      zoom: 18
    });

    new this.googleMaps.Marker({
      position: latLng,
      map: this.map
    });
  }

  async openTextQR(code: string) {
    await this.router.navigate(['open'], {
      relativeTo: this.route,
      state: {
        qrCode: code
      }
    });
  }

}
