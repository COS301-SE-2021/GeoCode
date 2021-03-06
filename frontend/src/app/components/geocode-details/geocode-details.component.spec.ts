import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { GeocodeDetailsComponent } from './geocode-details.component';
import {FormsModule} from '@angular/forms';
import {GeoCodeService} from '../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {SocialSharing} from '@ionic-native/social-sharing/ngx';

describe('GeocodeDetailsComponent', () => {
  let component: GeocodeDetailsComponent;
  let fixture: ComponentFixture<GeocodeDetailsComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ GeocodeDetailsComponent ],
      providers: [ GeoCodeService, SocialSharing ],
      imports: [IonicModule.forRoot(), FormsModule, RouterTestingModule, HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(GeocodeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
