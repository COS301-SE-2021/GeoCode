import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Event, EventLeaderboardDetails, LeaderboardService, Point} from '../../../services/geocode-api';

@Component({
  selector: 'app-event-leaderboard',
  templateUrl: './event-leaderboard.page.html',
  styleUrls: ['./event-leaderboard.page.scss'],
})
export class EventLeaderboardPage implements OnInit {

  points2 = [
    {username:'person',pos:1,points:100},
    {username:'a',pos:1,points:100},
    {username:'a',pos:1,points:100},
    {username:'a',pos:1,points:100},
    {username:'a',pos:1,points:100},
    {username:'a',pos:1,points:100},
    {username:'a',pos:1,points:100},
    {username:'a',pos:1,points:100},
    {username:'a',pos:1,points:100},
    {username:'a',pos:1,points:100}
  ];

  event: Event = null;
  users: EventLeaderboardDetails[] = [
    {username: 'danielle', points: 123, rank: 1},
    {username: 'samuel', points: 99, rank: 2},
    {username: 'joshua', points: 87, rank: 3},
    {username: 'mandisa', points: 79, rank: 4},
    {username: 'ben', points: 77, rank: 5},
    {username: 'samkele', points: 65, rank: 6},
    {username: 'willem', points: 62, rank: 7},
    {username: 'matthew', points: 59, rank: 8}
  ];
  leaderboardIndex = 0;
  numToFetch = 20;

  constructor(
    router: Router,
    private leaderboardService: LeaderboardService
  ) {
    const state = router.getCurrentNavigation().extras.state;
    if (state) {
      this.event = state.event;
      console.log(this.event);
    }
    this.loadLeaderboard().then().catch();
  }

  async loadLeaderboard() {
    if (this.event.leaderboard.length > 0) {
      const data = await this.leaderboardService.getEventLeaderboard({
        leaderboardId: this.event.leaderboard[0].id,
        count: this.numToFetch,
        starting: this.leaderboardIndex
      }).toPromise();
      console.log(data);
      for (const point of data.leaderboard) {
        this.users.push(point);
      }
      this.leaderboardIndex += data.leaderboard.length;
      return (data.leaderboard.length > 0);
    }
  }

  ngOnInit() {
  }

  async loadFakeLeaderboard() {
    this.users.push({username: 'tristan', points: 53, rank: 9});
    this.users.push({username: 'kayleigh', points: 51, rank: 10});
    this.users.push({username: 'amber', points: 34, rank: 11});
    this.users.push({username: 'nicholas', points: 32, rank: 12});
    this.users.push({username: 'rachel', points: 29, rank: 13});
    this.users.push({username: 'emma', points: 25, rank: 14});
    this.users.push({username: 'victoria', points: 20, rank: 15});
    this.users.push({username: 'joseph', points: 12, rank: 16});
    return false;
  }

  async loadData(event) {
    const moreLoaded = await this.loadFakeLeaderboard();
    if (!moreLoaded) {
      event.target.disabled = true;
    }
    event.target.complete();
  }

}
