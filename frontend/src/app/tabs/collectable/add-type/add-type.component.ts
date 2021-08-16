import {Component, OnInit} from '@angular/core';
import {ModalController, NavParams, ToastController} from '@ionic/angular';
import {
  CollectableService, CollectableTypeComponent,
  CreateCollectableTypeRequest, MissionType,
} from '../../../services/geocode-api';

@Component({
  selector: 'app-add-type',
  templateUrl: './add-type.component.html',
  styleUrls: ['./add-type.component.scss'],
})
export class AddTypeComponent implements OnInit {

  request: CreateCollectableTypeRequest = {
    name: '',
    image: '',
    properties: {},
    rarity: null,
    setId: ''
  };
  trackable = false;
  mission: MissionType = null;

  loading = false;

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
    return (name && rarity);
  }

  async proceed() {
    if (this.loading) { return; }
    if (!this.validate()) {
      return;
    }
    this.loading = true;
    if (this.trackable) { this.request.properties.trackable = 'true'; }
    if (this.mission) { this.request.properties.missionType = this.mission; }
    const response = await this.collectableService.createCollectableType(this.request).toPromise();
    console.log(response);
    await this.dismiss(response.collectableType);
  }

  async dismiss(createdType: CollectableTypeComponent = null) {
    await this.modalController.dismiss(createdType);
  }

  updateRequest(event: any, field: keyof CreateCollectableTypeRequest) {
    this.request[field] = event.target.value;
  }

  updateMission(event) {
    if (event.target.value === 'None') {
      this.mission = null;
    } else {
      this.mission = event.target.value;
    }
  }

}
