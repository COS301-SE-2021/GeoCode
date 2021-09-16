import {Component} from '@angular/core';
import {ModalController, NavParams, ToastController} from '@ionic/angular';
import {
  CollectableService, CollectableTypeComponent,
  CreateCollectableTypeRequest, ImageService, MissionType,
} from '../../../services/geocode-api';
import {environment} from '../../../../environments/environment';

@Component({
  selector: 'app-add-type',
  templateUrl: './add-type.component.html',
  styleUrls: ['./add-type.component.scss'],
})
export class AddTypeComponent {

  fileName = '';

  request: CreateCollectableTypeRequest = {
    name: '',
    image: null,
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
    private imageService: ImageService,
    navParams: NavParams
  ) {
    this.request.setId = navParams.data.setID;
  }

  validate() {
    const name = (this.request.name.length > 0);
    const rarity = (this.request.rarity != null);
    const image = (this.request.image != null);
    return (name && image && rarity);
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
    if (response.success) {
      await this.dismiss(response.collectableType);
    } else {
      alert(response.message);
    }
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

  async uploadImage(event) {
    const file: File = event.target.files[0];
    if (file) {
      const response = await this.imageService.uploadImage(file).toPromise();
      if (response.success) {
        this.request.image = environment.serverAddress+'/api/Image/getImage/'+response.fileName;
      } else {
        this.request.image = null;
      }
    }
  }

}
