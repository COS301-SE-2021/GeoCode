import {KeycloakService} from 'keycloak-angular';

// Mocks the KeyCloak service
export class MockKeycloak {
  static provider() {
    return { provide: KeycloakService, useValue: new MockKeycloak() };
  }

  getKeycloakInstance() {
    return {
      authenticated: true,
      subject: 'uuid',
      tokenParsed: {
        // eslint-disable-next-line @typescript-eslint/naming-convention
        preferred_username: 'username'
      }
    };
  }
}
