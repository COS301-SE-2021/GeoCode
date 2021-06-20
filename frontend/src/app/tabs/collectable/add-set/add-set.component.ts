import { Component, OnInit } from '@angular/core';
import {ModalController} from '@ionic/angular';
import {CollectableService, CollectableSet, CreateCollectableSetRequest} from '../../../services/geocode-api';

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

  loading = false;

  constructor(
    private modalController: ModalController,
    private collectableService: CollectableService
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
    this.loading = true;
    const response = await this.collectableService.createCollectableSet(this.request).toPromise();
    console.log(response);
    await this.dismiss(response.collectableSet);
  }

  async dismiss(createdSet: CollectableSet = null) {
    await this.modalController.dismiss(createdSet);
  }

  updateRequest(event: any, field: keyof CreateCollectableSetRequest) {
    this.request[field] = event.target.value;
  }

}
