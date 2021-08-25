import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { EventsPage } from './events.page';
import {RouterTestingModule} from '@angular/router/testing';
import {MockGoogleMapsLoader} from '../../mocks/MockGoogleMapsLoader';
import {EventService} from '../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('EventsPage', () => {
  let component: EventsPage;
  let fixture: ComponentFixture<EventsPage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ EventsPage ],
      providers: [MockGoogleMapsLoader.provider(), EventService],
      imports: [IonicModule.forRoot(), RouterTestingModule, HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(EventsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
