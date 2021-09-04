import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import {AlertController, IonicModule, NavController} from '@ionic/angular';

import { GeocodePage } from './geocode.page';
import {GeoCodeService} from '../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {FormsModule} from '@angular/forms';
import {MockGoogleMapsLoader} from '../../mocks/MockGoogleMapsLoader';
import {MockKeycloak} from '../../mocks/MockKeycloak';
import {CustomComponentsModule} from '../../components/components.module';
import {MockCurrentUserDetails} from '../../mocks/MockCurrentUserDetails';

describe('GeocodePage', () => {
  let component: GeocodePage;
  let fixture: ComponentFixture<GeocodePage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ GeocodePage ],
      providers: [NavController, GeoCodeService, MockCurrentUserDetails.provider()],
      imports: [IonicModule.forRoot(), RouterTestingModule, HttpClientTestingModule, FormsModule, CustomComponentsModule]
    }).compileComponents();

    fixture = TestBed.createComponent(GeocodePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
