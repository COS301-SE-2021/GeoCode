/**
 * Swagger GeoCode
 * This is the swagger documentation and API for the GeoCode project implemented by Peak Performers for the client [5DT](https://5dt.com/).
 *
 * OpenAPI spec version: 1.5.1
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

import { CreateCollectableRequest } from '../model/createCollectableRequest';
import { CreateCollectableResponse } from '../model/createCollectableResponse';
import { CreateCollectableSetRequest } from '../model/createCollectableSetRequest';
import { CreateCollectableSetResponse } from '../model/createCollectableSetResponse';
import { CreateCollectableTypeRequest } from '../model/createCollectableTypeRequest';
import { CreateCollectableTypeResponse } from '../model/createCollectableTypeResponse';
import { GetCollectableByTypeRequest } from '../model/getCollectableByTypeRequest';
import { GetCollectableSetsResponse } from '../model/getCollectableSetsResponse';
import { GetCollectableTypeByRarityRequest } from '../model/getCollectableTypeByRarityRequest';
import { GetCollectableTypesBySetRequest } from '../model/getCollectableTypesBySetRequest';
import { GetCollectableTypesResponse } from '../model/getCollectableTypesResponse';
import { GetCollectablesResponse } from '../model/getCollectablesResponse';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class CollectableService {

    protected basePath = 'http://geocodeapp.tech:8080/api';
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
     * Creates a new Collectable
     * Create Collectable
     * @param body Request to create a new Collectable
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public createCollectable(body: CreateCollectableRequest, observe?: 'body', reportProgress?: boolean): Observable<CreateCollectableResponse>;
    public createCollectable(body: CreateCollectableRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<CreateCollectableResponse>>;
    public createCollectable(body: CreateCollectableRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<CreateCollectableResponse>>;
    public createCollectable(body: CreateCollectableRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling createCollectable.');
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

        return this.httpClient.request<CreateCollectableResponse>('post',`${this.basePath}/Collectable/createCollectable`,
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
     * Create a new Collectable Set
     * Create Collectable Set
     * @param body Request to create a new Collectable Set
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public createCollectableSet(body: CreateCollectableSetRequest, observe?: 'body', reportProgress?: boolean): Observable<CreateCollectableSetResponse>;
    public createCollectableSet(body: CreateCollectableSetRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<CreateCollectableSetResponse>>;
    public createCollectableSet(body: CreateCollectableSetRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<CreateCollectableSetResponse>>;
    public createCollectableSet(body: CreateCollectableSetRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling createCollectableSet.');
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

        return this.httpClient.request<CreateCollectableSetResponse>('post',`${this.basePath}/Collectable/createCollectableSet`,
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
     * Creates a new Collectable Type
     * Create Collectable Type
     * @param body Request to create a new Collectable Type
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public createCollectableType(body: CreateCollectableTypeRequest, observe?: 'body', reportProgress?: boolean): Observable<CreateCollectableTypeResponse>;
    public createCollectableType(body: CreateCollectableTypeRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<CreateCollectableTypeResponse>>;
    public createCollectableType(body: CreateCollectableTypeRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<CreateCollectableTypeResponse>>;
    public createCollectableType(body: CreateCollectableTypeRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling createCollectableType.');
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

        return this.httpClient.request<CreateCollectableTypeResponse>('post',`${this.basePath}/Collectable/createCollectableType`,
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
     * Get all of the Collectables of a certain type
     * Returns all Collectables of the specified type
     * @param body Request Collectables by type
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getCollectableByType(body: GetCollectableByTypeRequest, observe?: 'body', reportProgress?: boolean): Observable<GetCollectablesResponse>;
    public getCollectableByType(body: GetCollectableByTypeRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetCollectablesResponse>>;
    public getCollectableByType(body: GetCollectableByTypeRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetCollectablesResponse>>;
    public getCollectableByType(body: GetCollectableByTypeRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getCollectableByType.');
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

        return this.httpClient.request<GetCollectablesResponse>('post',`${this.basePath}/Collectable/getCollectableByType`,
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
     * Get all of the Collectable Sets
     * Returns all Collectable Sets
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getCollectableSets(observe?: 'body', reportProgress?: boolean): Observable<GetCollectableSetsResponse>;
    public getCollectableSets(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetCollectableSetsResponse>>;
    public getCollectableSets(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetCollectableSetsResponse>>;
    public getCollectableSets(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

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
        ];

        return this.httpClient.request<GetCollectableSetsResponse>('get',`${this.basePath}/Collectable/getCollectableSets`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Get all of the Collectables of a certain rarity
     * Returns all Collectable Types of the specified rarity
     * @param body Request Collectable Types by rarity
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getCollectableTypeByRarity(body: GetCollectableTypeByRarityRequest, observe?: 'body', reportProgress?: boolean): Observable<GetCollectableTypesResponse>;
    public getCollectableTypeByRarity(body: GetCollectableTypeByRarityRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetCollectableTypesResponse>>;
    public getCollectableTypeByRarity(body: GetCollectableTypeByRarityRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetCollectableTypesResponse>>;
    public getCollectableTypeByRarity(body: GetCollectableTypeByRarityRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getCollectableTypeByRarity.');
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

        return this.httpClient.request<GetCollectableTypesResponse>('post',`${this.basePath}/Collectable/getCollectableTypeByRarity`,
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
     * Get all of the Collectables of a certain set
     * Returns all Collectable Types of the specified set
     * @param body Request Collectable Types by set
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getCollectableTypeBySet(body: GetCollectableTypesBySetRequest, observe?: 'body', reportProgress?: boolean): Observable<GetCollectableTypesResponse>;
    public getCollectableTypeBySet(body: GetCollectableTypesBySetRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetCollectableTypesResponse>>;
    public getCollectableTypeBySet(body: GetCollectableTypesBySetRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetCollectableTypesResponse>>;
    public getCollectableTypeBySet(body: GetCollectableTypesBySetRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getCollectableTypeBySet.');
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

        return this.httpClient.request<GetCollectableTypesResponse>('post',`${this.basePath}/Collectable/getCollectableTypesBySet`,
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
     * Get all of the Collectable Types
     * Returns all Collectable Types
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getCollectableTypes(observe?: 'body', reportProgress?: boolean): Observable<GetCollectableTypesResponse>;
    public getCollectableTypes(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetCollectableTypesResponse>>;
    public getCollectableTypes(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetCollectableTypesResponse>>;
    public getCollectableTypes(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

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
        ];

        return this.httpClient.request<GetCollectableTypesResponse>('get',`${this.basePath}/Collectable/getCollectableTypes`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Get all of the Collectables
     * Returns all Collectables
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getCollectables(observe?: 'body', reportProgress?: boolean): Observable<GetCollectablesResponse>;
    public getCollectables(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetCollectablesResponse>>;
    public getCollectables(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetCollectablesResponse>>;
    public getCollectables(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

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
        ];

        return this.httpClient.request<GetCollectablesResponse>('get',`${this.basePath}/Collectable/getCollectables`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

}
