import {AfterViewInit, Component, ViewChild} from '@angular/core';
import { NavController } from '@ionic/angular';
import {GeoCodeService, GeoCode, Difficulty} from '../../services/geocode-api';
import {MapAndInfoComponent} from '../../components/map-and-info/map-and-info.component';
import {CurrentUserDetails} from '../../services/CurrentUserDetails';

@Component({
  selector: 'app-geocode',
  templateUrl: './geocode.page.html',
  styleUrls: ['./geocode.page.scss'],
})

export class GeocodePage implements AfterViewInit {

  @ViewChild('mapAndInfo', {static: false}) mapAndInfo: MapAndInfoComponent;

  geocodes: GeoCode[] = [];
  selectedGeocode: GeoCode = null;
  listView = false;

  selectedDifficulties: {[diff: string]: boolean} = {
    /* eslint-disable @typescript-eslint/naming-convention */
    EASY: true,
    MEDIUM: true,
    HARD: true,
    INSANE: true
    /* eslint-enable @typescript-eslint/naming-convention */
  };

  constructor(
    private navCtrl: NavController,
    private geocodeApi: GeoCodeService,
    private currentUser: CurrentUserDetails,
  ) {}

  async ngAfterViewInit() {
    await this.mapAndInfo.load();
    this.mapAndInfo.getMap().setZoom(10);

    const unsortedGeocodes = (await this.geocodeApi.getGeoCodes().toPromise()).geocodes;
    this.geocodes = this.sortGeocodes(unsortedGeocodes);
    this.placeGeocodes();
  }

  showDetails(g: GeoCode) {
    this.selectedGeocode = g;
    this.mapAndInfo.setInfoVisible(g != null);
  }

  closeDetails = () => this.showDetails(null);

  toggleList(){
    this.listView= !this.listView;
  }

  getDifficulties = () => Object.keys(Difficulty);

  toggleDifficulty(diff: string) {
    this.selectedDifficulties[diff] = !this.selectedDifficulties[diff];
    this.placeGeocodes();
  }

  selectedDifficultyGeoCodes() {
    return this.geocodes.filter(g => this.selectedDifficulties[g.difficulty]);
  }

  placeGeocodes() {
    const filtered = this.selectedDifficultyGeoCodes();
    this.mapAndInfo.placeGeoCodes(filtered, geocode => {
      this.showDetails(geocode);
    });
  }

  sortGeocodes(geocodes: GeoCode[]): GeoCode[] {
    class Temp {
      geocode: GeoCode;
      distance: number;
    }
    const radians = (degrees: number) => degrees * Math.PI/180;

    const location = this.mapAndInfo.getLocation();
    const locLat = radians(location.latitude);
    const locLong = radians(location.longitude);

    const geocodesWithDistances: Temp[] = [];
    for (const geocode of geocodes) {
      const gcLat = radians(geocode.location.latitude);
      const gcLong = radians(geocode.location.longitude);
      const dLat = locLat - gcLat;
      const dLong = locLong - gcLong;
      const a = Math.pow(Math.sin(dLat/2), 2) + Math.cos(locLat)*Math.cos(gcLat)*Math.pow(Math.sin(dLong/2), 2);
      const c = 2 * Math.asin(Math.sqrt(a));
      const distance = c*6371;
      geocodesWithDistances.push({geocode, distance});
    }
    const sorted = geocodesWithDistances.sort((a, b) => a.distance - b.distance);

    const output: GeoCode[] = [];
    for (const item of sorted) {
      output.push(item.geocode);
    }
    return output;
  }
}
