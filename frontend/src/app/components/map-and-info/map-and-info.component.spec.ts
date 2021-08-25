import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { MapAndInfoComponent } from './map-and-info.component';
import {MockGoogleMapsLoader} from '../../mocks/MockGoogleMapsLoader';

describe('MapAndInfoComponent', () => {
  let component: MapAndInfoComponent;
  let fixture: ComponentFixture<MapAndInfoComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ MapAndInfoComponent ],
      providers: [ MockGoogleMapsLoader.provider() ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(MapAndInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
