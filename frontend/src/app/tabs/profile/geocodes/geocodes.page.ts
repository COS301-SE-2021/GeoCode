import {AfterViewInit, Component, OnInit, ViewChild, ViewChildren} from '@angular/core';
import {
  GeoCode,
  GeoCodeService,
  GetFoundGeoCodesResponse, GetGeoCodeResponse,
  GetOwnedGeoCodesResponse,
  UserService
} from '../../../services/geocode-api';
import {KeycloakService} from 'keycloak-angular';
import {ActivatedRoute} from '@angular/router';
import {zip} from 'rxjs';
import {MapAndInfoComponent} from '../../../components/map-and-info/map-and-info.component';

@Component({
  selector: 'app-geocodes',
  templateUrl: './geocodes.page.html',
  styleUrls: ['./geocodes.page.scss'],
})
export class UserGeocodesPage implements AfterViewInit {

  @ViewChild('mapAndInfo', {static: false}) mapAndInfo: MapAndInfoComponent;

  map;
  googleMaps;

  userID;
  loggedInUserID;
  createdIDs: string[] = [];
  created: GeoCode[] = null;
  foundIDs: string[] = [];
  found: GeoCode[] = null;
  markers = [];
  detailedGeoCode: GeoCode = null;

  constructor(
    private geocodeService: GeoCodeService,
    private userService: UserService,
    keycloak: KeycloakService,
    route: ActivatedRoute
  ) {
    this.userID = route.snapshot.paramMap.get('id');
    this.loggedInUserID = keycloak.getKeycloakInstance().subject;
    if (!this.userID) {
      this.userID = this.loggedInUserID;
    }
  }

  async ngAfterViewInit() {
    zip(
      this.mapAndInfo.loadedState$,
      this.userService.getFoundGeoCodes({ userID: this.userID }),
      this.userService.getOwnedGeoCodes({ userID: this.userID })

    ).subscribe(async responses => {
      console.log(responses);

      this.map = this.mapAndInfo.getMap();
      this.googleMaps = this.mapAndInfo.getGoogleMaps();

      this.foundIDs = responses[1].geocodeIDs;
      if (this.userID === this.loggedInUserID) {
        this.createdIDs = responses[2].geocodeIDs;
      }

      await this.loadFound();
    });
  }

  async segmentChanged(event) {
    if (event.target.value === 'found') {
      await this.loadFound();
    } else if (event.target.value === 'created') {
      await this.loadCreated();
    }
  }

  async loadFound() {
    if (!(this.found)) {
      this.found = [];
      await this.loadGeoCodes(this.foundIDs, this.found);
    }
    this.placeMarkers(this.found);
  }

  async loadCreated() {
    if (!(this.created)) {
      this.created = [];
      await this.loadGeoCodes(this.createdIDs, this.created);
    }
    this.placeMarkers(this.created);
  }

  loadGeoCodes(ids: string[], target: GeoCode[]) {
    return new Promise(resolve => {
      for (const id of ids) {
        this.geocodeService.getGeoCode({geoCodeID: id}).subscribe((response: GetGeoCodeResponse) => {
          console.log(response);
          target.push(response.foundGeoCode);
          if (target.length === ids.length) { resolve(null); }
        });
      }
    });
  }

  placeMarkers(locations: GeoCode[]) {
    for (const m of this.markers) {
      m.setMap(null);
    }
    this.markers = [];
    for (const g of locations) {
      const marker = new this.googleMaps.Marker({
        map: this.map,
        title: g.description,
        position: {
          lat: parseFloat(String(g.location.latitude)),
          lng: parseFloat(String(g.location.longitude))
        }
      });
      marker.addListener('click' , (event) => {
        this.showDetails(g);
      });
      this.markers.push(marker);
    }
  }

  showDetails(g: GeoCode) {
    this.detailedGeoCode = g;
    this.mapAndInfo.setInfoVisible(g != null);
  }
}
