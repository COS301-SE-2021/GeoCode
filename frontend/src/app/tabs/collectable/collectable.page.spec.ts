import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import {IonicModule, ModalController} from '@ionic/angular';

import { CollectablePage } from './collectable.page';
import {CollectableService} from '../../services/geocode-api';
import {UserInformationService} from '../../services/UserInformationService';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('CollectablePage', () => {
  let component: CollectablePage;
  let fixture: ComponentFixture<CollectablePage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ CollectablePage ],
      providers: [ModalController, CollectableService, UserInformationService],
      imports: [IonicModule.forRoot(), HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(CollectablePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
