import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { WelcomePage } from './welcome.page';
import {Router} from '@angular/router';
import {KeycloakService} from 'keycloak-angular';
import {RouterTestingModule} from '@angular/router/testing';

describe('WelcomePage', () => {
  let component: WelcomePage;
  let fixture: ComponentFixture<WelcomePage>;

  const mockRouter = {
    navigate: (args) => new Promise((resolve, reject) => {
      resolve(null);
    })
  };

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ WelcomePage ],
      providers: [
        KeycloakService,
        { provide: Router, useValue: mockRouter }
      ],
      imports: [IonicModule.forRoot(), RouterTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(WelcomePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
