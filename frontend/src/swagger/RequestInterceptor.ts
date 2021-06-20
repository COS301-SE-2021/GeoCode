import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../environments/environment';

@Injectable()
export class RequestInterceptor implements HttpInterceptor {

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const jwtToken = localStorage.getItem('accessToken');

    if (jwtToken && request.url.includes(environment.serverAddress)) {
      const newRequest = request.clone({
        headers: request.headers.set('Authorization', 'Bearer '+jwtToken),
      });
      return next.handle(newRequest);

    } else {
      return next.handle(request);
    }


  }

}
