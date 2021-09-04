import {Component, OnDestroy} from '@angular/core';
import {Subscription} from 'rxjs';
import {Mediator} from '../../services/Mediator';

export class NavigationLayout {
  layout: 'tabs'|'sidebar';
  compact?: boolean;
}

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss'],
})
export class NavComponent implements OnDestroy {

  public navigationLayout: NavigationLayout;
  private navigationLayoutSubscription: Subscription;

  constructor(mediator: Mediator) {
    this.navigationLayout = NavComponent.getCurrentNavigationLayout();

    this.navigationLayoutSubscription = mediator.navigationLayoutChanged.onReceive(layout => {
      this.navigationLayout = layout;
    });
  }

  public static getCurrentNavigationLayout(): NavigationLayout {
    if (window.innerWidth > 1280) {
      return {layout: 'sidebar', compact: (window.innerHeight < 500)};
    } else {
      return {layout: 'tabs'};
    }
  }

  ngOnDestroy() {
    this.navigationLayoutSubscription.unsubscribe();
  }
}
