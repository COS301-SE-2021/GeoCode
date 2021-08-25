import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { WelcomePage } from './welcome.page';
import {Router} from '@angular/router';
import {KeycloakService} from 'keycloak-angular';
import {RouterTestingModule} from '@angular/router/testing';

describe('WelcomePage', () => {
  let component: WelcomePage;
  let fixture: ComponentFixture<WelcomePage>;

  const mockKeycloak = {
    getKeycloakInstance: () => ({
      authenticated: true
    })
  };

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ WelcomePage ],
      providers: [
        { provide: KeycloakService, useValue: mockKeycloak }
      ],
      imports: [IonicModule.forRoot(), RouterTestingModule]
    }).compileComponents();

    const router = TestBed.inject(Router);
    router.navigate = () => new Promise(resolve => {
      resolve(null);
    });

    fixture = TestBed.createComponent(WelcomePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
