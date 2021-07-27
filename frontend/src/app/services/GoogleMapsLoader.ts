import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';

// Source: https://stackoverflow.com/a/65673222
interface windowWithGoogle extends Window {
  google?: any;
}
declare const window: windowWithGoogle;

@Injectable({ providedIn: 'root' })
export class GoogleMapsLoader {

  // A queue of promises that are waiting for the Maps API to load.
  // This was implemented to prevent multiple requests causing the Maps API to load multiple times.
  // If another call to load() is made while an existing one is still executing, the second call will be added to the queue
  //    and will return when the first one finishes.
  private static loadingQueue = [];

  // Script injection is adapted from https://stackoverflow.com/a/42766146
  // Queueing system is original
  load() {
    console.log('calling real load');
    return new Promise((resolve, reject) => {
      if (window.google) { // Maps API has been loaded and is available
        // Return a handle to the API
        resolve(window.google.maps);

      } else {
        // Maps API has not been loaded. Add this promise to the loading queue
        GoogleMapsLoader.loadingQueue.push({resolve, reject});

        // If this promise is the only one in the queue, start loading
        if (GoogleMapsLoader.loadingQueue.length == 1) {
          const script = document.createElement('script');
          script.src = 'https://maps.googleapis.com/maps/api/js?key='+environment.googleMapsKey;
          script.type = 'text/javascript';
          script.onload = () => {
            // Run through all the requests in the queue and return a handle to the Maps API
            for (let request of GoogleMapsLoader.loadingQueue) {
              request.resolve(window.google.maps);
            }
          }
          script.onerror = () => {
            // Run through all the requests in the queue and return an error
            for (let request of GoogleMapsLoader.loadingQueue) {
              request.reject('Failed to load Google Maps');
            }
          }
          document.getElementsByTagName('head')[0].appendChild(script);
        } else {
          // If this promise is not the only one in the queue, do nothing.
          // It will be resolved or rejected by the first promise in the queue.
        }
      }
    })
  }
}
