import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';

// Source: https://stackoverflow.com/a/65673222
interface windowWithGoogle extends Window {
  google?: any;
}
declare const window: windowWithGoogle;

@Injectable({ providedIn: 'root' })
export class GoogleMapsLoader {

  // Adapted from https://stackoverflow.com/a/42766146
  load() {
    return new Promise((resolve, reject) => {
      if (window.google) {
        console.log("already loaded google maps");
        resolve(window.google.maps);
      } else {
        console.log("loading google maps");
        const script = document.createElement('script');
        script.src = 'https://maps.googleapis.com/maps/api/js?key='+environment.googleMapsKey;
        script.type = 'text/javascript';
        script.onload = () => {
          resolve(window.google.maps);
        }
        script.onerror = () => {
          reject("Failed to load Google Maps");
        }
        document.getElementsByTagName('head')[0].appendChild(script);
      }
    })
  }
}
