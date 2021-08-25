import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import {IonicModule} from '@ionic/angular';

import { EventLocationComponent } from './event-location.component';
import {MockGoogleMapsLoader} from '../../../../mocks/MockGoogleMapsLoader';

describe('EventLocationComponent', () => {
  let component: EventLocationComponent;
  let fixture: ComponentFixture<EventLocationComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ EventLocationComponent ],
      providers: [MockGoogleMapsLoader.provider()],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(EventLocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
