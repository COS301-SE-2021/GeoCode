import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import {IonicModule, ModalController} from '@ionic/angular';

import { ProfilePage } from './profile.page';
import {UserService} from '../../services/geocode-api';
import {UserInformationService} from '../../services/UserInformationService';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('UserPage', () => {
  let component: ProfilePage;
  let fixture: ComponentFixture<ProfilePage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfilePage ],
      providers: [ModalController, UserService, UserInformationService],
      imports: [IonicModule.forRoot(), HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(ProfilePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
