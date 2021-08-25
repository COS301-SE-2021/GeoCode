import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouteReuseStrategy } from '@angular/router';

import { IonicModule, IonicRouteStrategy } from '@ionic/angular';
import {ApiModule, BASE_PATH} from './services/geocode-api';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {environment} from '../environments/environment';
import {RequestInterceptor} from './services/RequestInterceptor';
import {KeycloakAngularModule, KeycloakService} from 'keycloak-angular';
import {IonicStorageModule, Storage} from '@ionic/storage-angular';

const initializeKeycloak = (keycloak: KeycloakService) => async () => {
  await keycloak.init({
    config: {
      url: 'https://geocodeapp.tech:8100/auth',
      realm: 'GeoCode',
      clientId: environment.keycloakClientID,
    },
    initOptions: environment.keycloakInitOptions,
    enableBearerInterceptor: false
  });
};

@NgModule({
  declarations: [AppComponent],
  entryComponents: [],
  imports: [
    BrowserModule,
    IonicModule.forRoot(),
    AppRoutingModule,
    HttpClientModule,
    ApiModule,
    KeycloakAngularModule,
    IonicStorageModule.forRoot()
  ],
  providers: [
    { provide: RouteReuseStrategy, useClass: IonicRouteStrategy },
    { provide: BASE_PATH, useValue: environment.serverAddress+'/api' },
    { provide: HTTP_INTERCEPTORS, useClass: RequestInterceptor, multi: true },
    { provide: APP_INITIALIZER, useFactory: initializeKeycloak, multi: true, deps: [KeycloakService] },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
