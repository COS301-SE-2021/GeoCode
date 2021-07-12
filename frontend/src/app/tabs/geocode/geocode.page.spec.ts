import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import {AlertController, IonicModule, NavController} from '@ionic/angular';

import { GeocodePage } from './geocode.page';
import {GeoCodeService} from '../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {GoogleMapsLoader} from '../../services/GoogleMapsLoader';
import {FormsModule} from '@angular/forms';

describe('GeocodePage', () => {
  let component: GeocodePage;
  let fixture: ComponentFixture<GeocodePage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ GeocodePage ],
      providers: [AlertController, NavController, GeoCodeService, GoogleMapsLoader],
      imports: [IonicModule.forRoot(), RouterTestingModule, HttpClientTestingModule, FormsModule]
    }).compileComponents();

    fixture = TestBed.createComponent(GeocodePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
