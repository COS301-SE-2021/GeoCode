import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { OpenGeocodePage } from './open-geocode.page';
import {GeoCodeService} from '../../../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {MockActivatedRoute} from '../../../../mocks/MockActivatedRoute';
import {Router} from '@angular/router';
import {CustomComponentsModule} from '../../../../components/components.module';

describe('OpenGeocodePage', () => {
  let component: OpenGeocodePage;
  let fixture: ComponentFixture<OpenGeocodePage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ OpenGeocodePage ],
      providers: [MockActivatedRoute.provider({geocodeID: 'random'}), GeoCodeService],
      imports: [IonicModule.forRoot(), RouterTestingModule, HttpClientTestingModule, CustomComponentsModule]
    }).compileComponents();

    const router = TestBed.inject(Router);
    // @ts-ignore we do not need the other elements of Navigation for loading the page in tests
    router.getCurrentNavigation = () => ({ extras: {state: {qrCode: 'random'}} });

    fixture = TestBed.createComponent(OpenGeocodePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
