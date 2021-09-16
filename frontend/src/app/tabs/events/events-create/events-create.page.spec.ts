import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { EventsCreatePage } from './events-create.page';
import {RouterTestingModule} from '@angular/router/testing';
import {EventService, GeoCodeService} from '../../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {MockGoogleMapsLoader} from '../../../mocks/MockGoogleMapsLoader';
import {CustomComponentsModule} from '../../../components/components.module';
import {SocialSharing} from '@ionic-native/social-sharing/ngx';

describe('EventsCreatePage', () => {
  let component: EventsCreatePage;
  let fixture: ComponentFixture<EventsCreatePage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ EventsCreatePage ],
      providers: [GeoCodeService, EventService, MockGoogleMapsLoader.provider(), SocialSharing],
      imports: [IonicModule.forRoot(), RouterTestingModule, HttpClientTestingModule, CustomComponentsModule]
    }).compileComponents();

    fixture = TestBed.createComponent(EventsCreatePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
