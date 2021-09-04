import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { UserGeocodesPage } from './geocodes.page';
import {GeoCodeService, UserService} from '../../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {MockKeycloak} from '../../../mocks/MockKeycloak';
import {CustomComponentsModule} from '../../../components/components.module';
import {MockGoogleMapsLoader} from '../../../mocks/MockGoogleMapsLoader';

describe('UserGeocodesPage', () => {
  let component: UserGeocodesPage;
  let fixture: ComponentFixture<UserGeocodesPage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ UserGeocodesPage ],
      providers: [
        GeoCodeService,
        UserService,
        MockKeycloak.provider(),
        MockGoogleMapsLoader.provider()
      ],
      imports: [IonicModule.forRoot(), RouterTestingModule, HttpClientTestingModule, CustomComponentsModule]
    }).compileComponents();

    fixture = TestBed.createComponent(UserGeocodesPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
