import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { EventTimetrialPage } from './event-timetrial.page';
import {FormsModule} from '@angular/forms';
import {MockGoogleMapsLoader} from '../../../mocks/MockGoogleMapsLoader';
import {RouterTestingModule} from '@angular/router/testing';
import {GeoCodeService} from '../../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('EventTimetrialPage', () => {
  let component: EventTimetrialPage;
  let fixture: ComponentFixture<EventTimetrialPage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ EventTimetrialPage ],
      providers: [MockGoogleMapsLoader.provider(), GeoCodeService],
      imports: [IonicModule.forRoot(), FormsModule, RouterTestingModule, HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(EventTimetrialPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
