import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { UserMissionsPage } from './missions.page';
import {Router, UrlSerializer} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {Event, UserService} from '../../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {MockKeycloak} from '../../../mocks/MockKeycloak';
import {CustomComponentsModule} from '../../../components/components.module';

describe('UserMissionsPage', () => {
  let component: UserMissionsPage;
  let fixture: ComponentFixture<UserMissionsPage>;

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
      declarations: [ UserMissionsPage ],
      providers: [ UserService, MockKeycloak.provider() ],
      imports: [IonicModule.forRoot(), RouterTestingModule, HttpClientTestingModule, CustomComponentsModule]
    }).compileComponents();

    const router = TestBed.inject(Router);
    // @ts-ignore we do not need the other elements of Navigation for loading the page in tests
    router.getCurrentNavigation = () => ({ extras: {state: {event}} });

    fixture = TestBed.createComponent(UserMissionsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
