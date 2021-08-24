import { Component, OnInit } from '@angular/core';
import {GetMyMissionsResponse, Mission, MissionType, UserService} from '../../../services/geocode-api';
import {KeycloakService} from 'keycloak-angular';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-missions',
  templateUrl: './missions.page.html',
  styleUrls: ['./missions.page.scss'],
})
export class UserMissionsPage implements OnInit {

  missions: Mission[] = [];

  constructor(
    private userService: UserService,
    keycloak: KeycloakService,
    route: ActivatedRoute
  ) {
    let id = route.snapshot.paramMap.get('id');
    if (!id) {
      id = keycloak.getKeycloakInstance().subject;
    }
    this.userService.getMyMissions({userID: id}).subscribe((response: GetMyMissionsResponse) => {
      console.log(response);
      this.missions = response.missions;
    });
  }

  ngOnInit() {
  }

  getMissionDescription(mission: Mission) {
    switch(mission.type) {
      case MissionType.Circumference: return 'Help move this collectable 40 075km which is the distance around the earth.';
      case MissionType.Swap: return 'Help swap this collectable x times';
      case MissionType.GeoCode: return 'Help move this collectable to ' + mission.amount;
      default: return '';
    }
  }

}
