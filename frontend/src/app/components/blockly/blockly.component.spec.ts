import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { BlocklyComponent } from './blockly.component';
import {MockStorage} from '../../mocks/MockStorage';

describe('BlocklyComponent', () => {
  let component: BlocklyComponent;
  let fixture: ComponentFixture<BlocklyComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ BlocklyComponent ],
      providers: [MockStorage.provider()],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(BlocklyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
