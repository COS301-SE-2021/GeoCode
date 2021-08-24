import {Injectable} from '@angular/core';
import {Subject, Subscription} from 'rxjs';
import {distinctUntilChanged, distinctUntilKeyChanged} from 'rxjs/operators';

export class NavigationLayout {
  layout: 'tabs'|'sidebar';
  compact?: boolean;
}

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
    const compareFunc = (x: NavigationLayout, y: NavigationLayout) => {
      return ( (x.layout === y.layout) && (x.compact === y.compact) );
    }
    return this.navigationLayoutObserver.pipe(distinctUntilChanged(compareFunc)).subscribe(next);
  }


  public fireResize(): void {
    this.windowSize.next(window.innerWidth);
    this.navigationLayout.next(this.getCurrentNavigationLayout());
  }


  public getCurrentNavigationLayout(): NavigationLayout {
    if (window.innerWidth > 1280) {
      return { layout: 'sidebar', compact: (window.innerHeight < 500) };
    } else {
      return { layout: 'tabs' };
    }
  }
}
