import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { UserEventsPage } from './events.page';
import {UrlSerializer} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {UserService} from '../../../services/geocode-api';
import {MockKeycloak} from '../../../mocks/MockKeycloak';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('UserEventsPage', () => {
  let component: UserEventsPage;
  let fixture: ComponentFixture<UserEventsPage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ UserEventsPage ],
      providers: [UserService, MockKeycloak.provider()],
      imports: [IonicModule.forRoot(), RouterTestingModule, HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(UserEventsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
