import {Injectable} from '@angular/core';
import {GeoPoint} from './geocode-api';

@Injectable({ providedIn: 'root' })
export class Locator {

  async getCurrentLocation(): Promise<GeoPoint> {
    return new Promise<GeoPoint>(resolve => {
      navigator.geolocation.getCurrentPosition((position) => {
        resolve({
          latitude: position.coords.latitude,
          longitude: position.coords.longitude
        });
      }, () => {
        resolve(null);
      });
    });
  }
}
