import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { CreateGeocodeComponent } from './create-geocode.component';
import {MockGoogleMapsLoader} from '../../mocks/MockGoogleMapsLoader';
import {RouterTestingModule} from '@angular/router/testing';
import {GeoCodeService} from '../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {SocialSharing} from '@ionic-native/social-sharing/ngx';

describe('CreateGeocodeComponent', () => {
  let component: CreateGeocodeComponent;
  let fixture: ComponentFixture<CreateGeocodeComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateGeocodeComponent ],
      providers: [ MockGoogleMapsLoader.provider(), GeoCodeService, SocialSharing ],
      imports: [IonicModule.forRoot(), RouterTestingModule, HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(CreateGeocodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
