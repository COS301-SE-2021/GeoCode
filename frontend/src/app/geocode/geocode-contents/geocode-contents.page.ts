import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-geocode-contents',
  templateUrl: './geocode-contents.page.html',
  styleUrls: ['./geocode-contents.page.scss'],
})
export class GeocodeContentsPage implements OnInit {

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      console.log(params);
    });
  }

}
