import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { FindGeocodePage } from './find-geocode.page';
import {GeoCode, GeoCodeService} from '../../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {MockActivatedRoute} from '../../../mocks/MockActivatedRoute';
import {Router} from '@angular/router';
import {CustomComponentsModule} from '../../../components/components.module';
import {MockGoogleMapsLoader} from '../../../mocks/MockGoogleMapsLoader';

describe('FindGeocodePage', () => {
  let component: FindGeocodePage;
  let fixture: ComponentFixture<FindGeocodePage>;

  const geocode: GeoCode = {
    id: null,
    available: true,
    collectables: [],
    description: '',
    hints: [],
    difficulty: 'EASY',
    location: {
      latitude: -25.755918848126488,
      longitude: 28.233110280499492
    },
    qrCode: ''
  };

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ FindGeocodePage ],
      providers: [MockActivatedRoute.provider({geocodeID: 'random'}), GeoCodeService, MockGoogleMapsLoader.provider()],
      imports: [IonicModule.forRoot(), RouterTestingModule, HttpClientTestingModule, CustomComponentsModule]
    }).compileComponents();

    const router = TestBed.inject(Router);
    // @ts-ignore we do not need the other elements of Navigation for loading the page in tests
    router.getCurrentNavigation = () => ({ extras: {state: {geocode} } });

    fixture = TestBed.createComponent(FindGeocodePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
