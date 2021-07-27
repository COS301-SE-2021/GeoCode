import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { GeocodesPage } from './geocodes.page';
import {GoogleMapsLoader} from '../../../services/GoogleMapsLoader';
import {UrlSerializer} from '@angular/router';

describe('GeocodesPage', () => {
  let component: GeocodesPage;
  let fixture: ComponentFixture<GeocodesPage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ GeocodesPage ],
      providers: [ UrlSerializer, GoogleMapsLoader ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(GeocodesPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
