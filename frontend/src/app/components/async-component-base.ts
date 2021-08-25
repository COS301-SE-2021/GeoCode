import {Subject} from 'rxjs';

/* Based on code by @williamsandonz on Stack Overflow (https://stackoverflow.com/a/48231665) */

export class AsynchronouslyInitialisedComponent {

  loadedState: Subject<boolean> = new Subject<boolean>();
  public loadedState$ = this.loadedState.asObservable();

  constructor() {}

  protected componentLoaded() {
    this.loadedState.next(true);
  }
}
