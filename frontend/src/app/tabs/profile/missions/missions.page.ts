import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-missions',
  templateUrl: './missions.page.html',
  styleUrls: ['./missions.page.scss'],
})
export class UserMissionsPage implements OnInit {

  missions = [{
    name: 'Mission Name',
    id: 'random',
    progress: 0.42
  },{
    name: 'Mission Name',
    id: 'random',
    progress: 0.42
  }];

  constructor() { }

  ngOnInit() {
  }

}
