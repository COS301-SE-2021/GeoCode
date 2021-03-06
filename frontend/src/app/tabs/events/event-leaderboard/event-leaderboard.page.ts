import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {
  Event,
  EventLeaderboardDetails, EventService,
  GetEventLeaderboardRequest, GetEventLeaderboardResponse,
  LeaderboardService,
  Point
} from '../../../services/geocode-api';

@Component({
  selector: 'app-event-leaderboard',
  templateUrl: './event-leaderboard.page.html',
  styleUrls: ['./event-leaderboard.page.scss'],
})
export class EventLeaderboardPage implements OnInit {
  eventID: string = null;
  event: Event = null;
  users: EventLeaderboardDetails[] = [];
  leaderboardIndex = 1;
  numToFetch = 20;
  leaderBoardName='';
  constructor(
    router: Router,
    private leaderboardService: LeaderboardService,
    private eventService: EventService,
    route: ActivatedRoute
  ) {
    const state = router.getCurrentNavigation().extras.state;
    console.log(state);
    if (state) {
      this.event = state.event;
      this.leaderBoardName = this.event.leaderboards[0].name;
    } else {
      this.event = null;
      this.eventID = route.snapshot.paramMap.get('eventID');
      console.log(this.eventID);
    }
    this.loadLeaderboard();
  }

  async loadLeaderboard() {
    if (this.event == null) {
      this.event = (await this.eventService.getEvent({eventID: this.eventID}).toPromise()).foundEvent;
      this.leaderBoardName = this.event.leaderboards[0].name;
    }
    if (this.event.leaderboards.length > 0) {
      const req: GetEventLeaderboardRequest ={
        leaderboardID:this.event.leaderboards[0].id,
        count:this.numToFetch,
        starting:this.leaderboardIndex
      };
      this.leaderboardService.getEventLeaderboard(req).subscribe((response: GetEventLeaderboardResponse) =>{
        console.log(response);
        for (const point of response.leaderboard) {
          this.users.push(point);
        }
        this.leaderboardIndex +=response.leaderboard.length;
        return (response.leaderboard.length > 0);
      },error => {
        console.error(error);
      });
    }
    return false;
  }

  ngOnInit() {
  }

  async loadData(event) {
    this.leaderboardIndex=this.leaderboardIndex+this.numToFetch;
    const moreLoaded = await this.loadLeaderboard();
    if (!moreLoaded) {
      event.target.disabled = true;
    }
    event.target.complete();
  }

}
