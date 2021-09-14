import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { EventsBlocklyPage } from './events-blockly.page';
import {MockActivatedRoute} from '../../../../mocks/MockActivatedRoute';
import {RouterTestingModule} from '@angular/router/testing';
import {EventService} from '../../../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {MockCurrentUserDetails} from '../../../../mocks/MockCurrentUserDetails';
import {Router} from '@angular/router';
import {CustomComponentsModule} from '../../../../components/components.module';

describe('EventsBlocklyPage', () => {
  let component: EventsBlocklyPage;
  let fixture: ComponentFixture<EventsBlocklyPage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ EventsBlocklyPage ],
      providers: [MockActivatedRoute.provider({eventID: 'id'}), EventService, MockCurrentUserDetails.provider()],
      imports: [IonicModule.forRoot(), RouterTestingModule, HttpClientTestingModule, CustomComponentsModule]
    }).compileComponents();

    const router = TestBed.inject(Router);
    // @ts-ignore we do not need the other elements of Navigation for loading the page in tests
    router.getCurrentNavigation = () => ({ extras: {state: null } });

    fixture = TestBed.createComponent(EventsBlocklyPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
