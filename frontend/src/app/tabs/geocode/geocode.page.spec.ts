import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import {AlertController, IonicModule, NavController} from '@ionic/angular';

import { GeocodePage } from './geocode.page';
import {GeoCodeService} from '../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';

describe('GeocodePage', () => {
  let component: GeocodePage;
  let fixture: ComponentFixture<GeocodePage>;
  beforeAll(() => {
    jasmine.DEFAULT_TIMEOUT_INTERVAL = 30000;
  });

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ GeocodePage ],
      providers: [AlertController, NavController, GeoCodeService],
      imports: [IonicModule.forRoot(), RouterTestingModule, HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(GeocodePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
