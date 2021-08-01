import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { UserMissionsPage } from './missions.page';
import {UrlSerializer} from '@angular/router';

describe('UserMissionsPage', () => {
  let component: UserMissionsPage;
  let fixture: ComponentFixture<UserMissionsPage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ UserMissionsPage ],
      providers: [ UrlSerializer ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(UserMissionsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
