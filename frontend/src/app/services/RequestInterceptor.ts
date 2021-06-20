import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../environments/environment';
import {UserInformationService} from './UserInformationService';

@Injectable()
export class RequestInterceptor implements HttpInterceptor {

  constructor(
    private userDetails: UserInformationService
  ) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const jwtToken = localStorage.getItem('accessToken');

    if (this.userDetails.loggedIn() && request.url.includes(environment.serverAddress)) {
      const newRequest = request.clone({
        headers: request.headers.set('Authorization', 'Bearer '+this.userDetails.getToken()),
      });
      return next.handle(newRequest);

    } else {
      return next.handle(request);
    }


  }

}
