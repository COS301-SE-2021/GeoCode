import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-event-leaderboard',
  templateUrl: './event-leaderboard.page.html',
  styleUrls: ['./event-leaderboard.page.scss'],
})
export class EventLeaderboardPage implements OnInit {
points = [{username:'person',pos:1,points:100},{username:'a',pos:1,points:100},{username:'a',pos:1,points:100},{username:'a',pos:1,points:100},{username:'a',pos:1,points:100},
  {username:'a',pos:1,points:100},{username:'a',pos:1,points:100},{username:'a',pos:1,points:100},{username:'a',pos:1,points:100},{username:'a',pos:1,points:100}];
  constructor() { }

  ngOnInit() {
  }

}
