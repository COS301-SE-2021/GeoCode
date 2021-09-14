import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { EventContentsPage } from './event-contents.page';
import {FormsModule} from '@angular/forms';
import {MockGoogleMapsLoader} from '../../../mocks/MockGoogleMapsLoader';
import {RouterTestingModule} from '@angular/router/testing';
import {Event, EventService, GeoCodeService} from '../../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {MockKeycloak} from '../../../mocks/MockKeycloak';
import {Router} from '@angular/router';
import {Storage} from '@ionic/storage-angular';
import {CustomComponentsModule} from '../../../components/components.module';

describe('EventContentsPage', () => {
  let component: EventContentsPage;
  let fixture: ComponentFixture<EventContentsPage>;

  const event: Event = {
    id: '',
    leaderboards: [],
    description: '',
    available: true,
    beginDate: '',
    endDate: '',
    location: {
      latitude: -25.755918848126488,
      longitude: 28.233110280499492
    },
    name: '',
    geocodeIDs: [],
    properties: {}
  };

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ EventContentsPage ],
      providers: [MockGoogleMapsLoader.provider(), GeoCodeService, EventService, MockKeycloak.provider(), Storage],
      imports: [IonicModule.forRoot(), FormsModule, RouterTestingModule, HttpClientTestingModule, CustomComponentsModule]
    }).compileComponents();

    const router = TestBed.inject(Router);
    // @ts-ignore we do not need the other elements of Navigation for loading the page in tests
    router.getCurrentNavigation = () => ({ extras: {state: {event}} });

    fixture = TestBed.createComponent(EventContentsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
