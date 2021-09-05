import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { TestBed, waitForAsync } from '@angular/core/testing';

import { AppComponent } from './app.component';
import {environment} from '../environments/environment';
import {KeycloakService} from 'keycloak-angular';
import {Router} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {CustomComponentsModule} from './components/components.module';
import {Storage} from '@ionic/storage-angular';
import {MockCurrentUserDetails} from './mocks/MockCurrentUserDetails';

describe('AppComponent', () => {

  const mockKeycloak = {
    getKeycloakInstance: () => ({
      authenticated: true
    })
  };

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [AppComponent],
      providers: [
        { provide: KeycloakService, useValue: mockKeycloak },
        Storage,
        MockCurrentUserDetails.provider()
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
      imports: [RouterTestingModule, CustomComponentsModule]
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
