import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import {IonicModule, ModalController} from '@ionic/angular';

import { ProfilePage } from './profile.page';
import {UserService} from '../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {KeycloakService} from 'keycloak-angular';
import {
  ChildrenOutletContexts, provideRoutes,
  Router,
  ROUTER_CONFIGURATION,
  ROUTES,
  UrlHandlingStrategy,
  UrlSerializer
} from '@angular/router';
import {RouterTestingModule, setupTestingRouter} from '@angular/router/testing';
import {Compiler, Injector, NgModuleFactoryLoader, Optional } from '@angular/core';

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

  const mockKeycloak = {
    getKeycloakInstance: () => ({
      subject: 'uuid'
    })
  };

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfilePage ],
      providers: [
        ModalController,
        UserService,
        { provide: KeycloakService, useValue: mockKeycloak }
      ],
      imports: [IonicModule.forRoot(), RouterTestingModule.withRoutes([]), HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(ProfilePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
