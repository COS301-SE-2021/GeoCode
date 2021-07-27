import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {
  GeoCodeService,
  GetHintsRequest,
  GetHintsResponse,
  GetGeoCodeByQRCodeRequest,
  GetCollectablesRequest,
  GetGeoCodeByQRCodeResponse, GetCollectablesResponse, SwapCollectablesRequest, SwapCollectablesResponse
} from '../../../services/geocode-api';
import {AlertController, NavController} from '@ionic/angular';
import {GoogleMapsLoader} from '../../../services/GoogleMapsLoader';
import {QRScanner} from '../../../services/QRScanner';

@Component({
  selector: 'app-geocode-contents',
  templateUrl: './geocode-contents.page.html',
  styleUrls: ['./geocode-contents.page.scss'],
})
export class GeocodeContentsPage implements AfterViewInit {
  @ViewChild('mapElement',{static:false}) mapElement;
  @ViewChild('Container',{static:false}) container;
  googleMaps;
  map;
  geocode;
  mapOptions;
  hints=[];
  isHidden=false;
  collectables =[];


  constructor(
    private route: ActivatedRoute,
    public geocodeApi: GeoCodeService,
    public navCtrl: NavController,
    private alertCtrl: AlertController,
    private mapsLoader: GoogleMapsLoader,
    private qrScanner: QRScanner
  ) {
    //Get passed in param from routing
    this.route.queryParams.subscribe(params => {
          //Set the geocode to the passed in geocode
          this.geocode= params.geocode;
          //Create Hint request
          const hintsRequest: GetHintsRequest={
            geoCodeID: this.geocode.id
          };

      //Get the hints for the passed in geocode by id
          this.geocodeApi.getHints(hintsRequest)
            .subscribe((response: GetHintsResponse)=>{
              //log response and set hints array
              console.log(response);
              this.hints=response.hints;

            } ,(error)=>{
              //If error getting hints log error and put error message in hints array
              console.log(error);
              this.hints=['Error loading hints'];
            });
    });

  }

  //Create map and add mapmarkers of geocodes
  loadMap(){
    //Create map and center towards passed in geocode
    console.log(this.geocode);
    this.mapOptions = {
      center: {lat: parseFloat(this.geocode.latitude), lng: parseFloat(this.geocode.longitude)},
      zoom: 18,
    };
  //Create map
   this.map = new this.googleMaps.Map(this.mapElement.nativeElement,this.mapOptions);
   //Create map marker at geocode location
   new this.googleMaps.Marker({
      position: {lat: parseFloat(this.geocode.latitude), lng: parseFloat(this.geocode.longitude)},
      map: this.map,
      title: '',
    });
  }

  ngAfterViewInit(): void {
    this.mapsLoader.load().then(handle => {
      this.googleMaps = handle;
      this.loadMap();
    }).catch();
  }

  found(code){
    const requestQR: GetGeoCodeByQRCodeRequest={
      // eslint-disable-next-line @typescript-eslint/naming-convention
      QRCode: code.value
    };
    console.log(requestQR);
    this.geocodeApi.getGeoCodeByQRCode(requestQR).subscribe((response: GetGeoCodeByQRCodeResponse) =>{
      console.log(response);

      if(response.id == this.geocode.id){
        this.isHidden=true;
        const requestCollectables: GetCollectablesRequest={
          geoCodeID: response.id
        };
        this.geocodeApi.getGeoCodeCollectables(requestCollectables).subscribe((response2 :GetCollectablesResponse) =>{
          console.log(response2);
          this.collectables= response2.collectables;
        });
      }else{

      }
    });

  }

  async swapCollectable(collectable){


    const alert = await this.alertCtrl.create({
      header: 'Swap',

      message: ' <strong>Your about to swap your collectable for '+collectable.type.name+ '</strong>',
          buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
          handler: data => {
            // console.log('Cancel clicked');
          }
        },
        {
          text: 'Swap',
          handler: data => {
            const request: SwapCollectablesRequest={
              targetCollectableID:collectable.id,
              targetGeoCodeID:this.geocode.id
            };
            console.log(request);
            this.geocodeApi.swapCollectables(request).subscribe((response: SwapCollectablesResponse) =>{
              console.log(response);
            });
            this.navCtrl.navigateBack('/geocode');

          }
        }
      ]
    });
    alert.present();
    }


    async scan() {
      const data = await this.qrScanner.scan();
      console.log(data);
    }
}
