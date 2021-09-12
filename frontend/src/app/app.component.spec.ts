import { TestBed, waitForAsync } from '@angular/core/testing';

import { AppComponent } from './app.component';
import {Router} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {CustomComponentsModule} from './components/components.module';
import {MockCurrentUserDetails} from './mocks/MockCurrentUserDetails';
import {UserService} from './services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {IonicModule} from '@ionic/angular';
import {MockStorage} from './mocks/MockStorage';
import {MockKeycloak} from './mocks/MockKeycloak';

describe('AppComponent', () => {

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [AppComponent],
      providers: [
        MockKeycloak.provider(),
        MockStorage.provider(),
        MockCurrentUserDetails.provider(),
        UserService
      ],
      imports: [IonicModule.forRoot(), RouterTestingModule, CustomComponentsModule, HttpClientTestingModule]
    }).compileComponents();

    const router = TestBed.inject(Router);
    router.navigate = () => new Promise(resolve => {
      resolve(null);
    });
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });
  // TODO: add more tests!

});
