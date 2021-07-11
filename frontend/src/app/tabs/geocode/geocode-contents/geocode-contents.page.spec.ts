import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { GeocodeContentsPage } from './geocode-contents.page';
import {GeoCode, GeoCodeService} from '../../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {ActivatedRoute, convertToParamMap} from '@angular/router';
import {of} from 'rxjs';

describe('GeocodeContentsPage', () => {
  let component: GeocodeContentsPage;
  let fixture: ComponentFixture<GeocodeContentsPage>;

  const geocode: GeoCode = {
    id: null,
    available: true,
    collectables: [],
    description: "",
    hints: [],
    difficulty: 'EASY',
    latitude: '-25.755918848126488',
    longitude: '28.233110280499492',
    qrCode: ""
  }

  const mockActivatedRoute = {
    queryParams: of({ geocode })
  };

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ GeocodeContentsPage ],
      providers: [{provide: ActivatedRoute, useValue: mockActivatedRoute}, GeoCodeService],
      imports: [IonicModule.forRoot(), RouterTestingModule, HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(GeocodeContentsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
