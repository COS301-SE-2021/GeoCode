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
import {SocialSharing} from '@ionic-native/social-sharing/ngx';

const doInit = async (keycloak: KeycloakService) => {
  try {
    return await keycloak.init({
      config: {
        url: environment.keycloakServerAddress+'/auth',
        realm: 'GeoCode',
        clientId: environment.keycloakClientID,
      },
      initOptions: environment.keycloakInitOptions,
      enableBearerInterceptor: false
    });
  } catch (e) {
    return false;
  }
};

const initializeKeycloak = (keycloak: KeycloakService, storage: Storage) => async () => {

  await storage.create();
  const token = await storage.get('token');
  const refreshToken = await storage.get('refreshToken');

  if (token != null) { environment.keycloakInitOptions.token = token; }
  if (refreshToken != null) { environment.keycloakInitOptions.refreshToken = refreshToken; }

  const success = await doInit(keycloak);

  if (token != null) { delete environment.keycloakInitOptions.token; }
  if (refreshToken != null) { delete environment.keycloakInitOptions.refreshToken; }

  if (!success) {
    console.log('Logging in without saved refresh token');
    await doInit(keycloak);
  }

};

@NgModule({
  declarations: [AppComponent],
  entryComponents: [],
  imports: [
    BrowserModule,
    IonicModule.forRoot({ mode: 'md' }),
    AppRoutingModule,
    HttpClientModule,
    ApiModule,
    KeycloakAngularModule,
    IonicStorageModule.forRoot()
  ],
  providers: [
    { provide: RouteReuseStrategy, useClass: IonicRouteStrategy },
    { provide: BASE_PATH, useValue: environment.backendServerAddress+'/api' },
    { provide: HTTP_INTERCEPTORS, useClass: RequestInterceptor, multi: true },
    { provide: APP_INITIALIZER, useFactory: initializeKeycloak, multi: true, deps: [KeycloakService, Storage] },
    SocialSharing
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
