import {Injectable} from '@angular/core';
import {Subject, Subscription} from 'rxjs';

class MediatorMessage<T> {

  private subject = new Subject<T>();
  private observable = this.subject.asObservable();

  public onReceive(next: (value: T) => void): Subscription {
    return this.observable.subscribe(next);
  }

  public send(value: T): void {
    this.subject.next(value);
  }
}

@Injectable({ providedIn: 'root' })
export class Mediator {

  /* Add more mediator messages below */
  public readonly windowResized = new MediatorMessage<number>();
  public readonly themeChanged = new MediatorMessage<boolean>();
  //public readonly geocodeCreated = new MediatorMessage<GeoCode>();

}
