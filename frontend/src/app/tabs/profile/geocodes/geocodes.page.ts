import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {GeoCode, GeoCodeService, GetGeoCodeResponse, UserService} from '../../../services/geocode-api';
import {ActivatedRoute} from '@angular/router';
import {zip} from 'rxjs';
import {MapAndInfoComponent} from '../../../components/map-and-info/map-and-info.component';
import {CurrentUserDetails} from '../../../services/CurrentUserDetails';

@Component({
  selector: 'app-geocodes',
  templateUrl: './geocodes.page.html',
  styleUrls: ['./geocodes.page.scss'],
})
export class UserGeocodesPage implements AfterViewInit {

  @ViewChild('mapAndInfo', {static: false}) mapAndInfo: MapAndInfoComponent;

  userID;
  loggedInUserID;
  createdIDs: string[] = [];
  created: GeoCode[] = null;
  foundIDs: string[] = [];
  found: GeoCode[] = null;
  markers = [];
  detailedGeoCode: GeoCode = null;
  showing: 'found'|'created' = null;

  constructor(
    private geocodeService: GeoCodeService,
    private userService: UserService,
    currentUser: CurrentUserDetails,
    route: ActivatedRoute
  ) {
    this.userID = route.snapshot.paramMap.get('userID');
    this.loggedInUserID = currentUser.getID();
    if (!this.userID) {
      this.userID = this.loggedInUserID;
    }
  }

  async ngAfterViewInit() {
    zip(
      this.mapAndInfo.loadObservable(),
      this.userService.getFoundGeoCodes({ userID: this.userID }),
      this.userService.getOwnedGeoCodes({ userID: this.userID })

    ).subscribe(async responses => {
      console.log(responses);

      this.foundIDs = responses[1].geocodeIDs;
      if (this.userID === this.loggedInUserID) {
        this.createdIDs = responses[2].geocodeIDs;
      }

      await this.loadFound();
    });
  }

  async segmentChanged(event) {
    this.showing = event.target.value;
    this.closeDetails();
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
    this.showing = 'found';
  }

  async loadCreated() {
    if (!(this.created)) {
      this.created = [];
      await this.loadGeoCodes(this.createdIDs, this.created);
    }
    this.placeMarkers(this.created);
    this.showing = 'created';
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
    this.mapAndInfo.placeGeoCodes(locations, geocode => {
      this.showDetails(geocode);
    });
  }

  showDetails(g: GeoCode) {
    this.detailedGeoCode = g;
    this.mapAndInfo.setInfoVisible(g != null);
  }

  closeDetails = () => this.showDetails(null);

  createdGeoCode(){
    return (this.showing === 'created');
  }
  shouldShowQR() {
    return (this.showing === 'created');
  }
}
