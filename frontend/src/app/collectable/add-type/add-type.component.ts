import {Component, OnInit, ViewChild} from '@angular/core';
import {ModalController, NavParams, ToastController} from '@ionic/angular';
import {
  CollectableService,
  CreateCollectableTypeRequest,
} from '../../../swagger/client';

declare let google;
@Component({
  selector: 'app-add-type',
  templateUrl: './add-type.component.html',
  styleUrls: ['./add-type.component.scss'],
})
export class AddTypeComponent implements OnInit {
  @ViewChild('mapContainer') mapContainer;
  @ViewChild('mapElement',{static:false}) mapElement;
  mapOptions;
  map;
  mapMarker;
  geocodes;

  request: CreateCollectableTypeRequest = {
    name: '',
    image: '',
    properties: {},
    rarity: null,
    setId: ''
  };
  trackable = false;

  showMap = false;
  mapLoaded = false;
  location;

  showDatePicker = false;
  date: Date = null;

  loading: false;

  constructor(
    private modalController: ModalController,
    private collectableService: CollectableService,
    private toastController: ToastController,
    navParams: NavParams
  ) {
    this.request.setId = navParams.data.setID;
  }

  ngOnInit() {}

  validate() {
    const name = (this.request.name.length > 0);
    const rarity = (this.request.rarity != null);
    const date = (!this.showDatePicker || this.date != null);
    return (name && rarity && date);
  }

  async proceed() {
    if (this.loading) { return; }
    if (!this.validate()) {
      return;
    }
    if (this.trackable) { this.request.properties.trackable = 'true'; }
    if (this.showMap) { this.request.properties.geofenced = this.location.getPosition().lat()+' '+this.location.getPosition().lng(); }
    if (this.showDatePicker) { this.request.properties.expiring = this.getDate(); }
    const response = await this.collectableService.createCollectableType(this.request).toPromise();
    console.log(response);
    await this.dismiss();
  }

  async dismiss() {
    await this.modalController.dismiss();
  }

  updateRequest(event: any, field: keyof CreateCollectableTypeRequest) {
    this.request[field] = event.target.value;
  }

  setDate(event) {
    this.date = new Date(event.target.value);
  }

  getDate() {
    return [
      this.date.getFullYear(),
      ('0' + (this.date.getMonth()+1)).slice(-2),
      ('0' + this.date.getDate()).slice(-2)
    ].join('/');
  }

  loadMap() {
    if (this.showMap) {
      this.mapContainer.el.style = 'height: auto;';
    } else {
      this.mapContainer.el.style = 'height: 0px;';
    }
    if (this.mapLoaded) { return; }
    this.mapLoaded = true;
    this.mapOptions = {
      center: {lat: -25.75625115327836, lng: 28.235629260918344},
      title: 'Drag the marker below',
      zoom: 15,
    };
    this.map = new google.maps.Map(this.mapElement.nativeElement,this.mapOptions);
    this.location = new google.maps.Marker({
      position: {lat: -25.75625115327836, lng: 28.235629260918344},
      draggable: true,
      map: this.map,
      title: 'Move this marker to set the location'
    });
    google.maps.event.addListener(this.map, 'click', (event) => {
      this.location.position = event.latLng;
    });
  }

}
