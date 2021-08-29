/**
 * Swagger GeoCode
 * This is the swagger documentation and API for the GeoCode project implemented by Peak Performers for the client [5DT](https://5dt.com/).
 *
 * OpenAPI spec version: 1.5.13
 * Contact: peakperformers@geocodeapp.tech
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *//* tslint:disable:no-unused-variable member-ordering */

import { Inject, Injectable, Optional }                      from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams,
         HttpResponse, HttpEvent }                           from '@angular/common/http';
import { CustomHttpUrlEncodingCodec }                        from '../encoder';

import { Observable }                                        from 'rxjs';

import { GetCurrentCollectableRequest } from '../model/getCurrentCollectableRequest';
import { GetCurrentCollectableResponse } from '../model/getCurrentCollectableResponse';
import { GetFoundCollectableTypesRequest } from '../model/getFoundCollectableTypesRequest';
import { GetFoundCollectableTypesResponse } from '../model/getFoundCollectableTypesResponse';
import { GetFoundGeoCodesRequest } from '../model/getFoundGeoCodesRequest';
import { GetFoundGeoCodesResponse } from '../model/getFoundGeoCodesResponse';
import { GetMyLeaderboardsRequest } from '../model/getMyLeaderboardsRequest';
import { GetMyLeaderboardsResponse } from '../model/getMyLeaderboardsResponse';
import { GetMyMissionsRequest } from '../model/getMyMissionsRequest';
import { GetMyMissionsResponse } from '../model/getMyMissionsResponse';
import { GetOwnedGeoCodesRequest } from '../model/getOwnedGeoCodesRequest';
import { GetOwnedGeoCodesResponse } from '../model/getOwnedGeoCodesResponse';
import { GetUserByIdRequest } from '../model/getUserByIdRequest';
import { GetUserByIdResponse } from '../model/getUserByIdResponse';
import { GetUserTrackableRequest } from '../model/getUserTrackableRequest';
import { GetUserTrackableResponse } from '../model/getUserTrackableResponse';
import { GetUsersRequest } from '../model/getUsersRequest';
import { GetUsersResponse } from '../model/getUsersResponse';
import { RegisterNewUserRequest } from '../model/registerNewUserRequest';
import { RegisterNewUserResponse } from '../model/registerNewUserResponse';
import { UpdateLocationRequest } from '../model/updateLocationRequest';
import { UpdateLocationResponse } from '../model/updateLocationResponse';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class UserService {

    protected basePath = 'https://geocodeapp.tech:8080/api';
    public defaultHeaders = new HttpHeaders();
    public configuration = new Configuration();

    constructor(protected httpClient: HttpClient, @Optional()@Inject(BASE_PATH) basePath: string, @Optional() configuration: Configuration) {
        if (basePath) {
            this.basePath = basePath;
        }
        if (configuration) {
            this.configuration = configuration;
            this.basePath = basePath || configuration.basePath || this.basePath;
        }
    }

    /**
     * @param consumes string[] mime-types
     * @return true: consumes contains 'multipart/form-data', false: otherwise
     */
    private canConsumeForm(consumes: string[]): boolean {
        const form = 'multipart/form-data';
        for (const consume of consumes) {
            if (form === consume) {
                return true;
            }
        }
        return false;
    }


    /**
     * Get the Collectable the User is currently holding
     * Get the user&#x27;s current Collectable
     * @param body Request to get the user&#x27;s current Collectable
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getCurrentCollectable(body: GetCurrentCollectableRequest, observe?: 'body', reportProgress?: boolean): Observable<GetCurrentCollectableResponse>;
    public getCurrentCollectable(body: GetCurrentCollectableRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetCurrentCollectableResponse>>;
    public getCurrentCollectable(body: GetCurrentCollectableRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetCurrentCollectableResponse>>;
    public getCurrentCollectable(body: GetCurrentCollectableRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getCurrentCollectable.');
        }

        let headers = this.defaultHeaders;

        // authentication (bearerAuth) required
        if (this.configuration.accessToken) {
            const accessToken = typeof this.configuration.accessToken === 'function'
                ? this.configuration.accessToken()
                : this.configuration.accessToken;
            headers = headers.set('Authorization', 'Bearer ' + accessToken);
        }
        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.request<GetCurrentCollectableResponse>('post',`${this.basePath}/User/getCurrentCollectable`,
            {
                body: body,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Gets the Collectable Types that the user has ever found
     * Get a user&#x27;s found Collectable Types
     * @param body Request to get the IDs of the user&#x27;s found Collectable Types
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getFoundCollectableTypes(body: GetFoundCollectableTypesRequest, observe?: 'body', reportProgress?: boolean): Observable<GetFoundCollectableTypesResponse>;
    public getFoundCollectableTypes(body: GetFoundCollectableTypesRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetFoundCollectableTypesResponse>>;
    public getFoundCollectableTypes(body: GetFoundCollectableTypesRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetFoundCollectableTypesResponse>>;
    public getFoundCollectableTypes(body: GetFoundCollectableTypesRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getFoundCollectableTypes.');
        }

        let headers = this.defaultHeaders;

        // authentication (bearerAuth) required
        if (this.configuration.accessToken) {
            const accessToken = typeof this.configuration.accessToken === 'function'
                ? this.configuration.accessToken()
                : this.configuration.accessToken;
            headers = headers.set('Authorization', 'Bearer ' + accessToken);
        }
        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.request<GetFoundCollectableTypesResponse>('post',`${this.basePath}/User/getFoundCollectableTypes`,
            {
                body: body,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Gets the GeoCodes that the user has ever found
     * Gets the user&#x27;s found GeoCodes
     * @param body Request to get the user&#x27;s found GeoCodes
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getFoundGeoCodes(body: GetFoundGeoCodesRequest, observe?: 'body', reportProgress?: boolean): Observable<GetFoundGeoCodesResponse>;
    public getFoundGeoCodes(body: GetFoundGeoCodesRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetFoundGeoCodesResponse>>;
    public getFoundGeoCodes(body: GetFoundGeoCodesRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetFoundGeoCodesResponse>>;
    public getFoundGeoCodes(body: GetFoundGeoCodesRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getFoundGeoCodes.');
        }

        let headers = this.defaultHeaders;

        // authentication (bearerAuth) required
        if (this.configuration.accessToken) {
            const accessToken = typeof this.configuration.accessToken === 'function'
                ? this.configuration.accessToken()
                : this.configuration.accessToken;
            headers = headers.set('Authorization', 'Bearer ' + accessToken);
        }
        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.request<GetFoundGeoCodesResponse>('post',`${this.basePath}/User/getFoundGeoCodes`,
            {
                body: body,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Gets the User&#x27;s Leaderboard rankings
     * Gets all the points and ranking for the Leaderboards that the given User is on
     * @param body Request to get the name, points and ranking for all of the Leaderboards that a User is on
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getMyLeaderboards(body: GetMyLeaderboardsRequest, observe?: 'body', reportProgress?: boolean): Observable<GetMyLeaderboardsResponse>;
    public getMyLeaderboards(body: GetMyLeaderboardsRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetMyLeaderboardsResponse>>;
    public getMyLeaderboards(body: GetMyLeaderboardsRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetMyLeaderboardsResponse>>;
    public getMyLeaderboards(body: GetMyLeaderboardsRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getMyLeaderboards.');
        }

        let headers = this.defaultHeaders;

        // authentication (bearerAuth) required
        if (this.configuration.accessToken) {
            const accessToken = typeof this.configuration.accessToken === 'function'
                ? this.configuration.accessToken()
                : this.configuration.accessToken;
            headers = headers.set('Authorization', 'Bearer ' + accessToken);
        }
        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.request<GetMyLeaderboardsResponse>('post',`${this.basePath}/User/getMyLeaderboards`,
            {
                body: body,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Gets the Missions for a User
     * Gets the Missions that a User has been involved in the past
     * @param body Request to get the User&#x27;s Missions
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getMyMissions(body: GetMyMissionsRequest, observe?: 'body', reportProgress?: boolean): Observable<GetMyMissionsResponse>;
    public getMyMissions(body: GetMyMissionsRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetMyMissionsResponse>>;
    public getMyMissions(body: GetMyMissionsRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetMyMissionsResponse>>;
    public getMyMissions(body: GetMyMissionsRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getMyMissions.');
        }

        let headers = this.defaultHeaders;

        // authentication (bearerAuth) required
        if (this.configuration.accessToken) {
            const accessToken = typeof this.configuration.accessToken === 'function'
                ? this.configuration.accessToken()
                : this.configuration.accessToken;
            headers = headers.set('Authorization', 'Bearer ' + accessToken);
        }
        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.request<GetMyMissionsResponse>('post',`${this.basePath}/User/getMyMissions`,
            {
                body: body,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Gets the user&#x27;s owned GeoCodes
     * Get&#x27;s the user&#x27;s owned GeoCodes
     * @param body Request to get the user&#x27;s owned GeoCodes
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getOwnedGeoCodes(body: GetOwnedGeoCodesRequest, observe?: 'body', reportProgress?: boolean): Observable<GetOwnedGeoCodesResponse>;
    public getOwnedGeoCodes(body: GetOwnedGeoCodesRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetOwnedGeoCodesResponse>>;
    public getOwnedGeoCodes(body: GetOwnedGeoCodesRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetOwnedGeoCodesResponse>>;
    public getOwnedGeoCodes(body: GetOwnedGeoCodesRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getOwnedGeoCodes.');
        }

        let headers = this.defaultHeaders;

        // authentication (bearerAuth) required
        if (this.configuration.accessToken) {
            const accessToken = typeof this.configuration.accessToken === 'function'
                ? this.configuration.accessToken()
                : this.configuration.accessToken;
            headers = headers.set('Authorization', 'Bearer ' + accessToken);
        }
        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.request<GetOwnedGeoCodesResponse>('post',`${this.basePath}/User/getOwnedGeoCodes`,
            {
                body: body,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Get a User by their ID
     * Get a User
     * @param body Request to get a User
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getUserById(body: GetUserByIdRequest, observe?: 'body', reportProgress?: boolean): Observable<GetUserByIdResponse>;
    public getUserById(body: GetUserByIdRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetUserByIdResponse>>;
    public getUserById(body: GetUserByIdRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetUserByIdResponse>>;
    public getUserById(body: GetUserByIdRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getUserById.');
        }

        let headers = this.defaultHeaders;

        // authentication (bearerAuth) required
        if (this.configuration.accessToken) {
            const accessToken = typeof this.configuration.accessToken === 'function'
                ? this.configuration.accessToken()
                : this.configuration.accessToken;
            headers = headers.set('Authorization', 'Bearer ' + accessToken);
        }
        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.request<GetUserByIdResponse>('post',`${this.basePath}/User/getUserById`,
            {
                body: body,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Gets the user&#x27;s trackable
     * Get the given user&#x27;s trackable
     * @param body Request to get the user&#x27;s trackable
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getUserTrackable(body: GetUserTrackableRequest, observe?: 'body', reportProgress?: boolean): Observable<GetUserTrackableResponse>;
    public getUserTrackable(body: GetUserTrackableRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetUserTrackableResponse>>;
    public getUserTrackable(body: GetUserTrackableRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetUserTrackableResponse>>;
    public getUserTrackable(body: GetUserTrackableRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getUserTrackable.');
        }

        let headers = this.defaultHeaders;

        // authentication (bearerAuth) required
        if (this.configuration.accessToken) {
            const accessToken = typeof this.configuration.accessToken === 'function'
                ? this.configuration.accessToken()
                : this.configuration.accessToken;
            headers = headers.set('Authorization', 'Bearer ' + accessToken);
        }
        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.request<GetUserTrackableResponse>('post',`${this.basePath}/User/getUserTrackable`,
            {
                body: body,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Get all of the users in the system
     * Get all of the users
     * @param body Request to get all users in the system
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getUsers(body: GetUsersRequest, observe?: 'body', reportProgress?: boolean): Observable<GetUsersResponse>;
    public getUsers(body: GetUsersRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetUsersResponse>>;
    public getUsers(body: GetUsersRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetUsersResponse>>;
    public getUsers(body: GetUsersRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getUsers.');
        }

        let headers = this.defaultHeaders;

        // authentication (bearerAuth) required
        if (this.configuration.accessToken) {
            const accessToken = typeof this.configuration.accessToken === 'function'
                ? this.configuration.accessToken()
                : this.configuration.accessToken;
            headers = headers.set('Authorization', 'Bearer ' + accessToken);
        }
        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.request<GetUsersResponse>('post',`${this.basePath}/User/getUsers`,
            {
                body: body,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Registers a new User
     * Registers a new User with the given username
     * @param body Request to register a new User
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public registerNewUser(body: RegisterNewUserRequest, observe?: 'body', reportProgress?: boolean): Observable<RegisterNewUserResponse>;
    public registerNewUser(body: RegisterNewUserRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<RegisterNewUserResponse>>;
    public registerNewUser(body: RegisterNewUserRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<RegisterNewUserResponse>>;
    public registerNewUser(body: RegisterNewUserRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling registerNewUser.');
        }

        let headers = this.defaultHeaders;

        // authentication (bearerAuth) required
        if (this.configuration.accessToken) {
            const accessToken = typeof this.configuration.accessToken === 'function'
                ? this.configuration.accessToken()
                : this.configuration.accessToken;
            headers = headers.set('Authorization', 'Bearer ' + accessToken);
        }
        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.request<RegisterNewUserResponse>('post',`${this.basePath}/User/registerNewUser`,
            {
                body: body,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Update the location of the user&#x27;s trackable
     * Update the location of the user&#x27;s trackable when they place it
     * @param body Request to update the location of the user&#x27;s trackable
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public updateLocation(body: UpdateLocationRequest, observe?: 'body', reportProgress?: boolean): Observable<UpdateLocationResponse>;
    public updateLocation(body: UpdateLocationRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<UpdateLocationResponse>>;
    public updateLocation(body: UpdateLocationRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<UpdateLocationResponse>>;
    public updateLocation(body: UpdateLocationRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling updateLocation.');
        }

        let headers = this.defaultHeaders;

        // authentication (bearerAuth) required
        if (this.configuration.accessToken) {
            const accessToken = typeof this.configuration.accessToken === 'function'
                ? this.configuration.accessToken()
                : this.configuration.accessToken;
            headers = headers.set('Authorization', 'Bearer ' + accessToken);
        }
        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json',
            'application/xml'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.request<UpdateLocationResponse>('post',`${this.basePath}/User/updateLocation`,
            {
                body: body,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

}
