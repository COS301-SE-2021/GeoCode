import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { UserGeocodesPage } from './geocodes.page';
import {GoogleMapsLoader} from '../../../services/GoogleMapsLoader';
import {UrlSerializer} from '@angular/router';

describe('UserGeocodesPage', () => {
  let component: UserGeocodesPage;
  let fixture: ComponentFixture<UserGeocodesPage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ UserGeocodesPage ],
      providers: [ UrlSerializer, GoogleMapsLoader ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(UserGeocodesPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
