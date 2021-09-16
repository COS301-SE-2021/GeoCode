import {Observable, Subject} from 'rxjs';

/* Based on code by @williamsandonz on Stack Overflow (https://stackoverflow.com/a/48231665) */

export class AsynchronouslyInitialisedComponent {

  private loadedState = new Subject<boolean>();
  private loadedState$ = this.loadedState.asObservable();

  constructor() {}

  public load(): Promise<void> {
    return new Promise(resolve => {
      this.loadedState$.subscribe(() => {
        resolve();
      });
    });
  }

  public loadObservable(): Observable<boolean> {
    return this.loadedState$;
  }

  protected componentLoaded() {
    this.loadedState.next(true);
  }
}
