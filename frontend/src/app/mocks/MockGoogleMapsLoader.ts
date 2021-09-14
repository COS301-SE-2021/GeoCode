import {GoogleMapsLoader} from '../services/GoogleMapsLoader';

// Mocks the Map class found in Google Maps
class Map {
  // Add methods here as we need to use them
  constructor(...args) {}
  setOptions(...args) {}
  setZoom(...args) {}
};

// Mocks the Marker class found in Google Maps
class Marker {
  // Add methods here as we need to use them
  constructor(...args) {}
};

// Mocks the event listeners found in Google Maps
const event = {
  addListener: (...args) => {}
};

// Mocks the GoogleMapsLoader service that returns an instance of Google Maps
export class MockGoogleMapsLoader {
  static provider() {
    return { provide: GoogleMapsLoader, useValue: new MockGoogleMapsLoader() };
  }

  load() {
    return new Promise((resolve) => {
      // eslint-disable-next-line @typescript-eslint/naming-convention
      resolve({ Map, Marker, event });
    });
  }
}
