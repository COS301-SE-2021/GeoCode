import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { EventContentsPage } from './event-contents.page';
import {FormsModule} from '@angular/forms';
import {MockGoogleMapsLoader} from '../../../mocks/MockGoogleMapsLoader';
import {RouterTestingModule} from '@angular/router/testing';
import {GeoCodeService} from '../../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('EventContentsPage', () => {
  let component: EventContentsPage;
  let fixture: ComponentFixture<EventContentsPage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ EventContentsPage ],
      providers: [MockGoogleMapsLoader.provider(), GeoCodeService],
      imports: [IonicModule.forRoot(), FormsModule, RouterTestingModule, HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(EventContentsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
