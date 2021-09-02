import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {GeoCode, GeoCodeService} from '../../../services/geocode-api';
import {AlertController, NavController} from '@ionic/angular';
import {GoogleMapsLoader} from '../../../services/GoogleMapsLoader';
import {QRScanner} from '../../../services/QRScanner';
import {MapAndInfoComponent} from '../../../components/map-and-info/map-and-info.component';
import {zip} from 'rxjs';

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

  ngAfterViewInit() {
    this.mapAndInfo.loadedState$.subscribe(async () => {

      if (this.geocode === null) {
        this.geocode = (await this.geocodeService.getGeoCode({geoCodeID: this.geocodeID}).toPromise()).foundGeoCode;
        console.log(this.geocode);
      }

      this.map = this.mapAndInfo.getMap();
      this.googleMaps = this.mapAndInfo.getGoogleMaps();

      console.log(this.map);
      console.log(this.googleMaps);

      const latLng = {lat: this.geocode.location.latitude, lng: this.geocode.location.longitude}

      this.map.setOptions({
        center: latLng,
        zoom: 18
      });

      new this.googleMaps.Marker({
        position: latLng,
        map: this.map
      });
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
