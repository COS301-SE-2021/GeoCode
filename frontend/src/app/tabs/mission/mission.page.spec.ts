import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { MissionPage } from './mission.page';
import {MockGoogleMapsLoader} from '../../mocks/MockGoogleMapsLoader';
import {MissionService} from '../../services/geocode-api';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {MockActivatedRoute} from '../../mocks/MockActivatedRoute';

describe('MissionPage', () => {
  let component: MissionPage;
  let fixture: ComponentFixture<MissionPage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ MissionPage ],
      providers: [ MockGoogleMapsLoader.provider(), MissionService, MockActivatedRoute.provider({id: 'randomID'}) ],
      imports: [IonicModule.forRoot(), HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(MissionPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
