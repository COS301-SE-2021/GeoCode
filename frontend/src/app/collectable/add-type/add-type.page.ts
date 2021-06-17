import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-add-type',
  templateUrl: './add-type.page.html',
  styleUrls: ['./add-type.page.scss'],
})
export class AddTypePage implements OnInit {

  constructor(route: ActivatedRoute) {
    let setID = route.snapshot.paramMap.get('setID');
    console.log(setID);
  }

  ngOnInit() {
  }

}
