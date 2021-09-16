import {KeycloakInitOptions} from 'keycloak-js';

const keycloakWebOptions: KeycloakInitOptions = {
  adapter: 'default',
  onLoad: 'check-sso',
  silentCheckSsoRedirectUri: window.location.origin + '/assets/silent-check-sso.html'
};

const keycloakAndroidOptions: KeycloakInitOptions = {
  adapter: 'capacitor',
  responseMode: 'query'
};

export const environment = {
  production: true,
  backendServerAddress: 'https://geocodeapp.tech:8080',
  keycloakServerAddress: 'https://geocodeapp.tech:8100',
  keycloakClientID: 'geocode-live',
  baseRedirectURI: 'https://geocodeapp.tech/',
  googleMapsKey: '',
  keycloakInitOptions: keycloakWebOptions
};

// Do not add secrets to this file!
