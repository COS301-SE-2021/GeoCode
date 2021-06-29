import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import {IonicModule, ModalController} from '@ionic/angular';

import { AddSetComponent } from './add-set.component';
import {CollectableService} from '../../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('AddSetComponent', () => {
  let component: AddSetComponent;
  let fixture: ComponentFixture<AddSetComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ AddSetComponent ],
      providers: [ModalController, CollectableService],
      imports: [IonicModule.forRoot(), HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(AddSetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
