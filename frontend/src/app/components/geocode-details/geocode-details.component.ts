import {Component, Input, OnInit} from '@angular/core';
import {KeycloakService} from 'keycloak-angular';
import {
  GeoCode,
  GeoCodeService,
  UpdateAvailabilityRequest,
  UpdateAvailabilityResponse
} from '../../services/geocode-api';
import {NavController} from '@ionic/angular';
import {QRGenerator} from '../../services/QRGenerator';

@Component({
  selector: 'app-geocode-details',
  templateUrl: './geocode-details.component.html',
  styleUrls: ['./geocode-details.component.scss'],
})
export class GeocodeDetailsComponent implements OnInit {

  @Input() geocode: GeoCode;
  @Input() parent: Component;
  @Input() showQR = false;

  constructor(
    private geocodeService: GeoCodeService,
    private keycloak: KeycloakService,
    private navCtrl: NavController,
    private qrGenerator: QRGenerator
  ) { }

  ngOnInit() {}

  //Navigate to findGeoCode page
  async findGeoCode(){
    await this.navCtrl.navigateForward('/explore/open/'+this.geocode.id,{ state: {geocode: this.geocode} });
  }

  //Call Geocode service and update Availability
  updateAvailability(){
    //create request object to update the availability
    const request: UpdateAvailabilityRequest={
      geoCodeID: this.geocode.id,
      available: this.geocode.available
    };

    //Call the geocodeService and send request to controller and log any errors
    this.geocodeService.updateAvailability(request).subscribe((response: UpdateAvailabilityResponse)=>{},(error)=>{
      console.log(error);
    });
  }

  isAdmin() {
    return this.keycloak.isUserInRole('Admin');
  }

  openInMaps() {
    window.open('https://www.google.com/maps/search/?api=1&query='+this.geocode.location.latitude+'%2C'+this.geocode.location.longitude);
  }

  getQR() {
    this.qrGenerator.generate(this.geocode.qrCode);
  }

}
