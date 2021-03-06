import {Component, Input, OnInit} from '@angular/core';
import {
  GeoCode,
  GeoCodeService,
  UpdateAvailabilityRequest,
  UpdateAvailabilityResponse
} from '../../services/geocode-api';
import {ModalController} from '@ionic/angular';
import {QRGenerator} from '../../services/QRGenerator';
import {GeocodeUpdateComponent} from '../../tabs/profile/geocodes/geocode-update/geocode-update.component';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-geocode-details',
  templateUrl: './geocode-details.component.html',
  styleUrls: ['./geocode-details.component.scss'],
})
export class GeocodeDetailsComponent implements OnInit {

  @Input() geocode: GeoCode;
  @Input() closeFunction: (() => void) = null;
  @Input() showFindGeoCode = false;
  @Input() showUpdateAvailability = false;
  @Input() showQR = false;
  @Input() showEdit = false;

  constructor(
    private modalController: ModalController,
    private geocodeService: GeoCodeService,
    private router: Router,
    private route: ActivatedRoute,
    private qrGenerator: QRGenerator
  ) { }

  ngOnInit() {}

  //Navigate to findGeoCode page
  async findGeoCode(){
    await this.router.navigate(['geocode/'+this.geocode.id], {
      relativeTo: this.route,
      state: {
        geocode: this.geocode
      }
    });
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

  openInMaps() {
    window.open('https://www.google.com/maps/search/?api=1&query='+this.geocode.location.latitude+'%2C'+this.geocode.location.longitude);
  }

  async getQR() {
    await this.qrGenerator.download(this.geocode.qrCode, this.geocode.description);
  }

  async updateGeocode() {
    const modal = await this.modalController.create({
      component: GeocodeUpdateComponent,
      swipeToClose: true,
      componentProps: {
        geocode: this.geocode
      }
    });
    await modal.present();
    const {data} = await modal.onDidDismiss();
  }

}
