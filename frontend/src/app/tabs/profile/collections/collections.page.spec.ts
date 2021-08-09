import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { UserCollectionsPage } from './collections.page';
import {UrlSerializer} from '@angular/router';
import {CollectableService, UserService} from '../../../services/geocode-api';
import {RouterTestingModule} from '@angular/router/testing';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {KeycloakService} from 'keycloak-angular';

describe('UserCollectionsPage', () => {
  let component: UserCollectionsPage;
  let fixture: ComponentFixture<UserCollectionsPage>;

  const mockKeycloak = {
    getKeycloakInstance: () => ({
      subject: 'uuid'
    })
  };

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ UserCollectionsPage ],
      providers: [
        CollectableService,
        UserService,
        { provide: KeycloakService, useValue: mockKeycloak }
      ],
      imports: [IonicModule.forRoot(), RouterTestingModule, HttpClientTestingModule]
    }).compileComponents();

    fixture = TestBed.createComponent(UserCollectionsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
