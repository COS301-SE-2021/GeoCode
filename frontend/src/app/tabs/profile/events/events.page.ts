import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-events',
  templateUrl: './events.page.html',
  styleUrls: ['./events.page.scss'],
})
export class UserEventsPage implements OnInit {

  events = [{
    name: 'Event Name',
    position: 1,
    id: 'random'
  }];

  constructor() { }

  ngOnInit() {
  }

}
