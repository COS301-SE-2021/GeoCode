import {ActivatedRoute} from '@angular/router';

// Mocks an ActivatedRoute that includes various URL parameters
export class MockActivatedRoute {

  snapshot = {};

  constructor(params) {
    this.snapshot = {
      paramMap: new MockParamMap(params)
    };
  }

  static provider(urlParams) {
    return { provide: ActivatedRoute, useValue: new MockActivatedRoute(urlParams) };
  }
}

class MockParamMap {

  params = {};

  constructor(params) {
    this.params = params;
  }

  add(key, value) {
    this.params[key] = value;
  }

  get(key) {
    return this.params[key];
  }
}
