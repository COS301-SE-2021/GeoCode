import { Component, OnInit } from '@angular/core';
import {GetMyLeaderboardsResponse, MyLeaderboardDetails, UserService} from '../../../services/geocode-api';
import {KeycloakService} from 'keycloak-angular';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-events',
  templateUrl: './events.page.html',
  styleUrls: ['./events.page.scss'],
})
export class UserEventsPage implements OnInit {

  leaderboards: MyLeaderboardDetails[] = [];

  constructor(
    private userService: UserService,
    keycloak: KeycloakService,
    route: ActivatedRoute
  ) {
    let id = route.snapshot.paramMap.get('id');
    if (!id) {
      id = keycloak.getKeycloakInstance().subject;
    }
    this.userService.getMyLeaderboards({userID: id}).subscribe((response: GetMyLeaderboardsResponse) => {
      console.log(response);
      this.leaderboards = response.leaderboards;
    });
  }

  ngOnInit() {
  }

}
