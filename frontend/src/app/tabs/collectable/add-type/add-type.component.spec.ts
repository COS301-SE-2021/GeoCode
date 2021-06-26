import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import {IonicModule, ModalController, NavParams, ToastController} from '@ionic/angular';

import { AddTypeComponent } from './add-type.component';
import {CollectableService} from '../../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('AddTypeComponent', () => {
  let component: AddTypeComponent;
  let fixture: ComponentFixture<AddTypeComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ AddTypeComponent ],
      providers: [ModalController, CollectableService, ToastController, NavParams],
      imports: [IonicModule.forRoot(), HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(AddTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
