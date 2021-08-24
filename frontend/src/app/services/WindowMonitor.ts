import {Injectable} from '@angular/core';
import {Subject, Subscription} from 'rxjs';
import {distinctUntilChanged} from 'rxjs/operators';

export type NavigationLayout = 'sidebar'|'tabs';

@Injectable({ providedIn: 'root' })
export class WindowMonitor {

  private windowSize = new Subject<number>();
  private windowSizeObserver = this.windowSize.asObservable();

  private navigationLayout = new Subject<NavigationLayout>();
  private navigationLayoutObserver = this.navigationLayout.asObservable();

  public onWindowResize(next: (value: number) => void): Subscription {
    return this.windowSizeObserver.subscribe(next);
  }

  public onNavigationLayoutChange(next: (value: NavigationLayout) => void): Subscription {
    return this.navigationLayoutObserver.pipe(distinctUntilChanged()).subscribe(next);
  }

  public fireResize(): void {
    this.windowSize.next(window.innerWidth);
    this.navigationLayout.next(this.getCurrentNavigationLayout());
  }

  public getCurrentNavigationLayout(): NavigationLayout {
    if (window.innerWidth > 1280) {
      return 'sidebar';
    } else {
      return 'tabs';
    }
  }
}
