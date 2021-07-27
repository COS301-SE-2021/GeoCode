import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { EventTimetrialPage } from './event-timetrial.page';
import {FormsModule} from '@angular/forms';

describe('EventTimetrialPage', () => {
  let component: EventTimetrialPage;
  let fixture: ComponentFixture<EventTimetrialPage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ EventTimetrialPage ],
      imports: [IonicModule.forRoot(), FormsModule]
    }).compileComponents();

    fixture = TestBed.createComponent(EventTimetrialPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
