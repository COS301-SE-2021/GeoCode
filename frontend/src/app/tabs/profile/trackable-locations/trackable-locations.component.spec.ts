import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import {IonicModule, ModalController, NavParams} from '@ionic/angular';

import { TrackableLocationsComponent } from './trackable-locations.component';
import {Collectable} from '../../../services/geocode-api';
import {MockGoogleMapsLoader} from '../../../mocks/MockGoogleMapsLoader';

describe('TrackableLocationsComponent', () => {
  let component: TrackableLocationsComponent;
  let fixture: ComponentFixture<TrackableLocationsComponent>;
  const trackable: Collectable = {
    pastLocations: [{latitude: -25.755918848126488, longitude: 28.233110280499492}],
    type: null,
    id: null,
    mission:null
  };
  const navParams = new NavParams({ trackable });

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ TrackableLocationsComponent ],
      providers: [ModalController, { provide: NavParams, useValue: navParams }, MockGoogleMapsLoader.provider()],
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
