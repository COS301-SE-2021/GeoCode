import {KeycloakService} from 'keycloak-angular';

// Mocks the KeyCloak service
export class MockKeycloak {
  static provider() {
    return { provide: KeycloakService, useValue: new MockKeycloak() };
  }

  getKeycloakInstance() {
    return {
      subject: 'uuid',
      idTokenParsed: {
        // eslint-disable-next-line @typescript-eslint/naming-convention
        preferred_username: 'username'
      }
    };
  }
}
