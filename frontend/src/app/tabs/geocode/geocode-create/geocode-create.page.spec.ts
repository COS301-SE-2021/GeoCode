import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import {IonicModule, NavController} from '@ionic/angular';

import { GeocodeCreatePage } from './geocode-create.page';
import {GeoCodeService} from '../../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {GoogleMapsLoader} from '../../../services/GoogleMapsLoader';

describe('GeocodeCreatePage', () => {
  let component: GeocodeCreatePage;
  let fixture: ComponentFixture<GeocodeCreatePage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ GeocodeCreatePage ],
      providers: [GeoCodeService, NavController, GoogleMapsLoader],
      imports: [IonicModule.forRoot(), RouterTestingModule, HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(GeocodeCreatePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
