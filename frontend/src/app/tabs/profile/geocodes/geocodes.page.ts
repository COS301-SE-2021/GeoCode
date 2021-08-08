import {Component, OnInit, ViewChild} from '@angular/core';
import {
  GeoCode,
  GeoCodeService,
  GetFoundGeoCodesResponse,
  GetOwnedGeoCodesResponse,
  UserService
} from '../../../services/geocode-api';
import {GoogleMapsLoader} from '../../../services/GoogleMapsLoader';
import {KeycloakService} from 'keycloak-angular';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-geocodes',
  templateUrl: './geocodes.page.html',
  styleUrls: ['./geocodes.page.scss'],
})
export class UserGeocodesPage implements OnInit {

  @ViewChild('mapElement',{static:false}) mapElement;
  googleMaps;
  map;

  createdIDs: string[] = null;
  created: GeoCode[] = null;
  foundIDs: string[] = null;
  found: GeoCode[] = null;
  markers = [];

  constructor(
    private mapsLoader: GoogleMapsLoader,
    private geocodeService: GeoCodeService,
    private userService: UserService,
    keycloak: KeycloakService,
    route: ActivatedRoute
  ) {
    let id = route.snapshot.paramMap.get('id');
    if (!id) {
      id = keycloak.getKeycloakInstance().subject;
    }
    this.userService.getFoundGeoCodes({
      userID: id
    }).subscribe((response: GetFoundGeoCodesResponse) => {
      console.log(response);
      // @ts-ignore
      this.foundIDs = response.geocodeIDs;
    });

    this.userService.getOwnedGeoCodes({
      userID: id
    }).subscribe((response: GetOwnedGeoCodesResponse) => {
      console.log(response);
      // @ts-ignore
      this.createdIDs = response.geocodeIDs;
    });
  }

  async ngOnInit() {
    this.googleMaps = await this.mapsLoader.load();
    navigator.geolocation.getCurrentPosition((position) => {
      this.loadMap(position.coords.latitude, position.coords.longitude);
    }, (positionError) => {
      this.loadMap(0, 0);
    });
  }

  async loadMap(centreLat, centreLong) {
    const mapOptions = {
      center: {lat: centreLat, lng: centreLong},
      zoom: 5,
    };
    this.map = new this.googleMaps.Map(this.mapElement.nativeElement, mapOptions);
    await this.loadFound();
    this.placeMarkers(this.found);
  }

  async segmentChanged(event) {
    if (event.target.value === 'found') {
      await this.loadFound();
      this.placeMarkers(this.found);
    } else if (event.target.value === 'created') {
      await this.loadCreated();
      this.placeMarkers(this.created);
    }
  }

  async loadCreated() {
    if (!(this.created)) {
      this.created = [];
      //await this.geocodeService.getGeocodeBy
    }
    return this.created;
  }

  async loadFound() {
    if (!(this.found)) {
      this.found = [];
      //load from backend
    }
    return this.found;
  }




  placeMarkers(locations: GeoCode[]) {
    for (const m of this.markers) {
      m.setMap(null);
    }
    this.markers = [];
    for (const g of locations) {
      this.markers.push(new this.googleMaps.Marker({
        map: this.map,
        position: {
          lat: parseFloat(String(g.location.latitude)),
          lng: parseFloat(String(g.location.longitude))
        }
      }));
    }
  }
}
