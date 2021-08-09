import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { EventsCreatePage } from './events-create.page';
import {RouterTestingModule} from '@angular/router/testing';
import {EventService, GeoCodeService} from '../../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {MockGoogleMapsLoader} from '../../../mocks/MockGoogleMapsLoader';

describe('EventsCreatePage', () => {
  let component: EventsCreatePage;
  let fixture: ComponentFixture<EventsCreatePage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ EventsCreatePage ],
      providers: [GeoCodeService, EventService, MockGoogleMapsLoader.provider()],
      imports: [IonicModule.forRoot(), RouterTestingModule, HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(EventsCreatePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
