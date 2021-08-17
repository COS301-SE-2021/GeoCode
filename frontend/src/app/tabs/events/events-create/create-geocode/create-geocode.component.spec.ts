import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { CreateGeocodeComponent } from './create-geocode.component';
import {MockGoogleMapsLoader} from '../../../../mocks/MockGoogleMapsLoader';
import {RouterTestingModule} from '@angular/router/testing';

describe('CreateGeocodeComponent', () => {
  let component: CreateGeocodeComponent;
  let fixture: ComponentFixture<CreateGeocodeComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateGeocodeComponent ],
      providers: [ MockGoogleMapsLoader.provider() ],
      imports: [IonicModule.forRoot(), RouterTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(CreateGeocodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
