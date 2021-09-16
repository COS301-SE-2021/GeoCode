import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import {IonicModule, NavParams} from '@ionic/angular';

import { GeocodeUpdateComponent } from './geocode-update.component';
import {FormsModule} from "@angular/forms";
import {RouterTestingModule} from "@angular/router/testing";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {GeoCode, GeoCodeService} from '../../../../services/geocode-api';

describe('GeocodeUpdateComponent', () => {
  let component: GeocodeUpdateComponent;
  let fixture: ComponentFixture<GeocodeUpdateComponent>;

  const mockNavParams = {
    data: {
      geocode: {
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
      }
    }
  }

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ GeocodeUpdateComponent ],
      providers: [ GeoCodeService, {provide: NavParams, useValue: mockNavParams} ],
      imports: [IonicModule.forRoot(), FormsModule, RouterTestingModule, HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(GeocodeUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
