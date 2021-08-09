import {Component, OnInit, ViewChild} from '@angular/core';
import {
  GeoCode,
  GeoCodeService,
  GetFoundGeoCodesResponse, GetGeoCodeResponse,
  GetOwnedGeoCodesResponse,
  UserService
} from '../../../services/geocode-api';
import {GoogleMapsLoader} from '../../../services/GoogleMapsLoader';
import {KeycloakService} from 'keycloak-angular';
import {ActivatedRoute} from '@angular/router';
import {ModalController} from '@ionic/angular';

@Component({
  selector: 'app-geocodes',
  templateUrl: './geocodes.page.html',
  styleUrls: ['./geocodes.page.scss'],
})
export class UserGeocodesPage implements OnInit {

  @ViewChild('mapElement',{static:false}) mapElement;
  googleMaps;
  map;

  userID;
  createdIDs: string[] = [];
  created: GeoCode[] = null;
  foundIDs: string[] = [];
  found: GeoCode[] = null;
  markers = [];
  detailedGeoCode: GeoCode = null;

  constructor(
    private mapsLoader: GoogleMapsLoader,
    private geocodeService: GeoCodeService,
    private userService: UserService,
    keycloak: KeycloakService,
    route: ActivatedRoute,
    private modalController: ModalController
  ) {
    this.userID = route.snapshot.paramMap.get('id');
    if (!this.userID) {
      this.userID = keycloak.getKeycloakInstance().subject;
    }
  }

  loadInitialData() {
    return new Promise(resolve => {
      //Only resolve promise when completed === 3
      let completed = 0;

      // Load Maps
      this.mapsLoader.load()
        .then(handle => {
          this.googleMaps = handle;
          completed++;
          if (completed === 3) { resolve(null); }
        }).catch();

      // Get found geocode IDs
      this.userService.getFoundGeoCodes({ userID: this.userID }).subscribe((response: GetFoundGeoCodesResponse) => {
        console.log(response);
        this.foundIDs = response.geocodeIDs;
        completed++;
        if (completed === 3) { resolve(null); }
      });

      // Get created geocode IDs
      this.userService.getOwnedGeoCodes({ userID: this.userID }).subscribe((response: GetOwnedGeoCodesResponse) => {
        console.log(response);
        this.createdIDs = response.geocodeIDs;
        completed++;
        if (completed === 3) { resolve(null); }
      });
    });
  }

  async ngOnInit() {
    await this.loadInitialData();
    navigator.geolocation.getCurrentPosition(async (position) => {
      await this.loadMap(position.coords.latitude, position.coords.longitude);
    }, async (positionError) => {
      await this.loadMap(0, 0);
    });
  }

  async loadMap(centreLat, centreLong) {
    const mapOptions = {
      center: {lat: centreLat, lng: centreLong},
      zoom: 5,
    };
    this.map = new this.googleMaps.Map(this.mapElement.nativeElement, mapOptions);
    await this.loadFound();
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
        this.detailedGeoCode = g;
      });
      this.markers.push(marker);
    }
  }

  closeDetails() {
    this.detailedGeoCode = null;
  }

  mapSizeMD() {
    if (this.detailedGeoCode) {
      return 6;
    } else {
      return 12;
    }
  }

  mapSizeLG() {
    if (this.detailedGeoCode) {
      return 9;
    } else {
      return 12;
    }
  }
}
