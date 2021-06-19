import { Component, OnInit } from '@angular/core';
import {ModalController, ToastController} from '@ionic/angular';
import {CollectableService, CreateCollectableSetRequest} from '../../../swagger/client';

@Component({
  selector: 'app-add-set',
  templateUrl: './add-set.component.html',
  styleUrls: ['./add-set.component.scss'],
})
export class AddSetComponent implements OnInit {

  request: CreateCollectableSetRequest = {
    name: '',
    description: ''
  };

  loading: false;

  constructor(
    private modalController: ModalController,
    private collectableService: CollectableService,
    private toastController: ToastController
  ) {}


  ngOnInit() {}

  validate() {
    const name = (this.request.name.length > 0);
    const description = (this.request.description.length > 0);
    return (name && description);
  }

  async proceed() {
    if (this.loading) { return; }
    if (!this.validate()) {
      return;
    }
    const response = await this.collectableService.createCollectableSet(this.request).toPromise();
    console.log(response);
    await this.dismiss();
  }

  async dismiss() {
    await this.modalController.dismiss();
  }

  updateRequest(event: any, field: keyof CreateCollectableSetRequest) {
    this.request[field] = event.target.value;
  }

}
