import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { UserEventsPage } from './events.page';
import {UrlSerializer} from '@angular/router';

describe('EventsPage', () => {
  let component: UserEventsPage;
  let fixture: ComponentFixture<UserEventsPage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ UserEventsPage ],
      providers: [ UrlSerializer ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(UserEventsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
