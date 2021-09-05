import {Component} from '@angular/core';
import {NavigationLayout} from '../../services/NavigationLayout';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss'],
})
export class NavComponent {

  constructor(public navigationLayout: NavigationLayout) { }

}
