import jwtDecode from 'jwt-decode';
import {Injectable} from '@angular/core';

@Injectable({ providedIn: 'root' })
export class UserInformationService {

  rawJWT: string;
  decodedJWT;

  constructor() {
    this.rawJWT = localStorage.getItem('accessToken');
    this.decodedJWT = jwtDecode(this.rawJWT);
  }

  loggedIn() {
    return this.rawJWT !== null;
  }

  getToken() {
    return this.rawJWT;
  }

  getUUID() {
    return this.decodedJWT.sub;
  }

  getUsername() {
    return this.decodedJWT.preferred_username;
  }

  getEmail() {
    return this.decodedJWT.email;
  }

  getRoles() {
    return this.decodedJWT.resource_access[this.decodedJWT.azp].roles;
  }

  getFirstName() {
    return this.decodedJWT.given_name;
  }

  getSurname() {
    return this.decodedJWT.family_name;
  }

  isAdmin() {
    return this.getRoles().includes('Admin');
  }

}
