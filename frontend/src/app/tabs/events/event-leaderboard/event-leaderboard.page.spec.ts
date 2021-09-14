import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { EventLeaderboardPage } from './event-leaderboard.page';
import {RouterTestingModule} from '@angular/router/testing';
import {Event, EventService, LeaderboardService} from '../../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {Router} from '@angular/router';
import {CustomComponentsModule} from '../../../components/components.module';

describe('EventLeaderboardPage', () => {
  let component: EventLeaderboardPage;
  let fixture: ComponentFixture<EventLeaderboardPage>;

  const event: Event = {
    id: '',
    leaderboards: [{
      id: '',
      name: ''
    }],
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
      declarations: [ EventLeaderboardPage ],
      providers: [ LeaderboardService, EventService ],
      imports: [IonicModule.forRoot(), RouterTestingModule, HttpClientTestingModule, CustomComponentsModule]
    }).compileComponents();

    const router = TestBed.inject(Router);
    // @ts-ignore we do not need the other elements of Navigation for loading the page in tests
    router.getCurrentNavigation = () => ({ extras: {state: {event}} });

    fixture = TestBed.createComponent(EventLeaderboardPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
