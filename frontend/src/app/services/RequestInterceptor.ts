import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import {from, Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {KeycloakService} from 'keycloak-angular';

@Injectable()
export class RequestInterceptor implements HttpInterceptor {

  constructor(
    private keycloak: KeycloakService
  ) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (request.url.includes(environment.serverAddress)) {
      const kc = this.keycloak.getKeycloakInstance()
      from(kc.updateToken(30));
      console.log({
        message: 'Applying token to request:',
        token: kc.token
      });
      const newRequest = request.clone({
        headers: request.headers.set('Authorization', 'Bearer '+kc.token)
      });
      return next.handle(newRequest);
    } else {
      return next.handle(request);
    }
  }

}
