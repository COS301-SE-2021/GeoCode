import {Injectable} from '@angular/core';
import {CurrentUserDetails} from '../services/CurrentUserDetails';

@Injectable({ providedIn: 'root' })
export class MockCurrentUserDetails {

  static provider() {
    return { provide: CurrentUserDetails, useValue: new MockCurrentUserDetails() };
  }

  public getID = () => '';
  public isAdmin = () => false;
  public getUsername = () => '';
  public getToken = () => '';

  async logout() {}

}
