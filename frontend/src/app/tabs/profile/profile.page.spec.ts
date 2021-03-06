import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import {IonicModule, ModalController} from '@ionic/angular';

import { ProfilePage } from './profile.page';
import {UserService} from '../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {MockKeycloak} from '../../mocks/MockKeycloak';
import {CustomComponentsModule} from '../../components/components.module';
import {MockCurrentUserDetails} from '../../mocks/MockCurrentUserDetails';
import {Storage} from '@ionic/storage-angular';

describe('ProfilePage', () => {
  let component: ProfilePage;
  let fixture: ComponentFixture<ProfilePage>;

  const mockRouter = {
    navigate: (args) => new Promise((resolve, reject) => {
      resolve({
        subscribe: () => new Promise((x, y) => {
          x(null);
        })
      });
    })
  };

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfilePage ],
      providers: [
        ModalController,
        UserService,
        MockKeycloak.provider(),
        MockCurrentUserDetails.provider()
      ],
      imports: [IonicModule.forRoot(), RouterTestingModule.withRoutes([]), HttpClientTestingModule, CustomComponentsModule]
    }).compileComponents();

    fixture = TestBed.createComponent(ProfilePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
