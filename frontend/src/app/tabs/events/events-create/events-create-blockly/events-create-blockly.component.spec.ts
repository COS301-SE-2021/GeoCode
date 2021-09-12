import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { EventsCreateBlocklyComponent } from './events-create-blockly.component';

describe('EventsCreateBlocklyComponent', () => {
  let component: EventsCreateBlocklyComponent;
  let fixture: ComponentFixture<EventsCreateBlocklyComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ EventsCreateBlocklyComponent ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(EventsCreateBlocklyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
