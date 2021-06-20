import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import {IonicModule, ModalController, NavParams} from '@ionic/angular';

import { TrackableLocationsComponent } from './trackable-locations.component';

describe('TrackableLocationsComponent', () => {
  let component: TrackableLocationsComponent;
  let fixture: ComponentFixture<TrackableLocationsComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ TrackableLocationsComponent ],
      providers: [ModalController, NavParams],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(TrackableLocationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});