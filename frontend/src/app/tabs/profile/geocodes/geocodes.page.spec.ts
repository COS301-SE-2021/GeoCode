import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { UserGeocodesPage } from './geocodes.page';
import {MockGoogleMapsLoader} from '../../../mocks/MockGoogleMapsLoader';
import {UrlSerializer} from '@angular/router';
import {GeoCodeService, UserService} from '../../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {KeycloakService} from 'keycloak-angular';
import {RouterTestingModule} from '@angular/router/testing';

describe('UserGeocodesPage', () => {
  let component: UserGeocodesPage;
  let fixture: ComponentFixture<UserGeocodesPage>;

  const mockKeycloak = {
    getKeycloakInstance: () => ({
      subject: 'uuid'
    })
  };

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ UserGeocodesPage ],
      providers: [
        MockGoogleMapsLoader.provider(),
        GeoCodeService,
        UserService,
        { provide: KeycloakService, useValue: mockKeycloak }
      ],
      imports: [IonicModule.forRoot(), RouterTestingModule, HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(UserGeocodesPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
