/**
 * Swagger GeoCode
 * This is the swagger documentation and API for the GeoCode project implemented by Peak Performers for the client [5DT](https://5dt.com/).
 *
 * OpenAPI spec version: 1.5.0
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

import { CreateGeoCodeRequest } from '../model/createGeoCodeRequest';
import { CreateGeoCodeResponse } from '../model/createGeoCodeResponse';
import { GetCollectablesRequest } from '../model/getCollectablesRequest';
import { GetCollectablesResponse } from '../model/getCollectablesResponse';
import { GetGeoCodeByLocationRequest } from '../model/getGeoCodeByLocationRequest';
import { GetGeoCodeByLocationResponse } from '../model/getGeoCodeByLocationResponse';
import { GetGeoCodeByQRCodeRequest } from '../model/getGeoCodeByQRCodeRequest';
import { GetGeoCodeByQRCodeResponse } from '../model/getGeoCodeByQRCodeResponse';
import { GetGeoCodeRequest } from '../model/getGeoCodeRequest';
import { GetGeoCodeResponse } from '../model/getGeoCodeResponse';
import { GetGeoCodesByDifficultyListRequest } from '../model/getGeoCodesByDifficultyListRequest';
import { GetGeoCodesByDifficultyListResponse } from '../model/getGeoCodesByDifficultyListResponse';
import { GetGeoCodesByDifficultyRequest } from '../model/getGeoCodesByDifficultyRequest';
import { GetGeoCodesByDifficultyResponse } from '../model/getGeoCodesByDifficultyResponse';
import { GetGeoCodesResponse } from '../model/getGeoCodesResponse';
import { GetHintsRequest } from '../model/getHintsRequest';
import { GetHintsResponse } from '../model/getHintsResponse';
import { GetTrackablesRequest } from '../model/getTrackablesRequest';
import { GetTrackablesResponse } from '../model/getTrackablesResponse';
import { SwapCollectablesRequest } from '../model/swapCollectablesRequest';
import { SwapCollectablesResponse } from '../model/swapCollectablesResponse';
import { UpdateAvailabilityRequest } from '../model/updateAvailabilityRequest';
import { UpdateAvailabilityResponse } from '../model/updateAvailabilityResponse';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class GeoCodeService {

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
     * Creates a new GeoCode
     * Create GeoCode
     * @param body Request to create a new GeoCode
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public createGeoCode(body: CreateGeoCodeRequest, observe?: 'body', reportProgress?: boolean): Observable<CreateGeoCodeResponse>;
    public createGeoCode(body: CreateGeoCodeRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<CreateGeoCodeResponse>>;
    public createGeoCode(body: CreateGeoCodeRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<CreateGeoCodeResponse>>;
    public createGeoCode(body: CreateGeoCodeRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling createGeoCode.');
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

        return this.httpClient.request<CreateGeoCodeResponse>('post',`${this.basePath}/GeoCode/createGeoCode`,
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
     * Get a specific GeoCode
     * Get a GeoCode with a specified ID
     * @param body Request to get a stored GeoCode with teh specified ID
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getGeoCode(body: GetGeoCodeRequest, observe?: 'body', reportProgress?: boolean): Observable<GetGeoCodeResponse>;
    public getGeoCode(body: GetGeoCodeRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetGeoCodeResponse>>;
    public getGeoCode(body: GetGeoCodeRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetGeoCodeResponse>>;
    public getGeoCode(body: GetGeoCodeRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getGeoCode.');
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

        return this.httpClient.request<GetGeoCodeResponse>('post',`${this.basePath}/GeoCode/getGeoCode`,
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
     * Get the GeoCode at or near the given location
     * Get the GeoCode at or near the given location
     * @param body Request to get a GeoCode at or near the given location
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getGeoCodeByLocation(body: GetGeoCodeByLocationRequest, observe?: 'body', reportProgress?: boolean): Observable<GetGeoCodeByLocationResponse>;
    public getGeoCodeByLocation(body: GetGeoCodeByLocationRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetGeoCodeByLocationResponse>>;
    public getGeoCodeByLocation(body: GetGeoCodeByLocationRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetGeoCodeByLocationResponse>>;
    public getGeoCodeByLocation(body: GetGeoCodeByLocationRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getGeoCodeByLocation.');
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

        return this.httpClient.request<GetGeoCodeByLocationResponse>('post',`${this.basePath}/GeoCode/getGeoCodeByLocation`,
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
     * Get the GeoCode associated with the given QR Code
     * Get the GeoCode associated with the given QR Code
     * @param body Request to get a GeoCode&#x27;s associated with the given QR Code
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getGeoCodeByQRCode(body: GetGeoCodeByQRCodeRequest, observe?: 'body', reportProgress?: boolean): Observable<GetGeoCodeByQRCodeResponse>;
    public getGeoCodeByQRCode(body: GetGeoCodeByQRCodeRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetGeoCodeByQRCodeResponse>>;
    public getGeoCodeByQRCode(body: GetGeoCodeByQRCodeRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetGeoCodeByQRCodeResponse>>;
    public getGeoCodeByQRCode(body: GetGeoCodeByQRCodeRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getGeoCodeByQRCode.');
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

        return this.httpClient.request<GetGeoCodeByQRCodeResponse>('post',`${this.basePath}/GeoCode/getGeoCodeByQRCode`,
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
     * Get all Collectables for a certain GeoCode
     * Get a GeoCode&#x27;s Collectables
     * @param body Request to get a GeoCode&#x27;s Collectables
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getGeoCodeCollectables(body: GetCollectablesRequest, observe?: 'body', reportProgress?: boolean): Observable<GetCollectablesResponse>;
    public getGeoCodeCollectables(body: GetCollectablesRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetCollectablesResponse>>;
    public getGeoCodeCollectables(body: GetCollectablesRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetCollectablesResponse>>;
    public getGeoCodeCollectables(body: GetCollectablesRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getGeoCodeCollectables.');
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

        return this.httpClient.request<GetCollectablesResponse>('post',`${this.basePath}/GeoCode/getCollectables`,
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
     * Get all the GeoCodes on the platform
     * Get all the GeoCodes that are stored on the platform
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getGeoCodes(observe?: 'body', reportProgress?: boolean): Observable<GetGeoCodesResponse>;
    public getGeoCodes(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetGeoCodesResponse>>;
    public getGeoCodes(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetGeoCodesResponse>>;
    public getGeoCodes(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

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

        return this.httpClient.request<GetGeoCodesResponse>('get',`${this.basePath}/GeoCode/getGeoCodes`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Get all all the stored GeoCodes by the specified difficulty
     * Get all GeoCodes by difficulty
     * @param body Request to get all the GeoCodes by the specified difficulty
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getGeoCodesByDifficulty(body: GetGeoCodesByDifficultyRequest, observe?: 'body', reportProgress?: boolean): Observable<GetGeoCodesByDifficultyResponse>;
    public getGeoCodesByDifficulty(body: GetGeoCodesByDifficultyRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetGeoCodesByDifficultyResponse>>;
    public getGeoCodesByDifficulty(body: GetGeoCodesByDifficultyRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetGeoCodesByDifficultyResponse>>;
    public getGeoCodesByDifficulty(body: GetGeoCodesByDifficultyRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getGeoCodesByDifficulty.');
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

        return this.httpClient.request<GetGeoCodesByDifficultyResponse>('post',`${this.basePath}/GeoCode/getGeoCodesByDifficulty`,
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
     * Get all all the stored GeoCodes by the specified difficulties
     * Get all GeoCodes by difficulty
     * @param body Request to get all the GeoCodes by the specified difficulties
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getGeoCodesByDifficultyList(body: GetGeoCodesByDifficultyListRequest, observe?: 'body', reportProgress?: boolean): Observable<GetGeoCodesByDifficultyListResponse>;
    public getGeoCodesByDifficultyList(body: GetGeoCodesByDifficultyListRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetGeoCodesByDifficultyListResponse>>;
    public getGeoCodesByDifficultyList(body: GetGeoCodesByDifficultyListRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetGeoCodesByDifficultyListResponse>>;
    public getGeoCodesByDifficultyList(body: GetGeoCodesByDifficultyListRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getGeoCodesByDifficultyList.');
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

        return this.httpClient.request<GetGeoCodesByDifficultyListResponse>('post',`${this.basePath}/GeoCode/getGeoCodesByDifficultyList`,
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
     * Get the hints for the specified GeoCode
     * Get the hints for the specified GeoCode to help locate it
     * @param body Request to get the hints from the specified GeoCode
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getHints(body: GetHintsRequest, observe?: 'body', reportProgress?: boolean): Observable<GetHintsResponse>;
    public getHints(body: GetHintsRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetHintsResponse>>;
    public getHints(body: GetHintsRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetHintsResponse>>;
    public getHints(body: GetHintsRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getHints.');
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

        return this.httpClient.request<GetHintsResponse>('post',`${this.basePath}/GeoCode/getHints`,
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
     * Get the Trackable for a certain GeoCode
     * Get a GeoCode&#x27;s Trackable
     * @param body Request to get a GeoCode&#x27;s Trackable
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getTrackables(body: GetTrackablesRequest, observe?: 'body', reportProgress?: boolean): Observable<GetTrackablesResponse>;
    public getTrackables(body: GetTrackablesRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<GetTrackablesResponse>>;
    public getTrackables(body: GetTrackablesRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<GetTrackablesResponse>>;
    public getTrackables(body: GetTrackablesRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling getTrackables.');
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

        return this.httpClient.request<GetTrackablesResponse>('post',`${this.basePath}/GeoCode/getTrackables`,
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
     * Swap a specific GeoCode&#x27;s Collectable
     * Swap a specific GeoCode&#x27;s Collectable
     * @param body Request to swap a GeoCode&#x27;s Collectables
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public swapCollectables(body: SwapCollectablesRequest, observe?: 'body', reportProgress?: boolean): Observable<SwapCollectablesResponse>;
    public swapCollectables(body: SwapCollectablesRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<SwapCollectablesResponse>>;
    public swapCollectables(body: SwapCollectablesRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<SwapCollectablesResponse>>;
    public swapCollectables(body: SwapCollectablesRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling swapCollectables.');
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

        return this.httpClient.request<SwapCollectablesResponse>('post',`${this.basePath}/GeoCode/swapCollectables`,
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
     * Update the availability for a certain GeoCode
     * Update the availability for a certain GeoCode
     * @param body Request to update a GeoCode&#x27;s availability
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public updateAvailability(body: UpdateAvailabilityRequest, observe?: 'body', reportProgress?: boolean): Observable<UpdateAvailabilityResponse>;
    public updateAvailability(body: UpdateAvailabilityRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<UpdateAvailabilityResponse>>;
    public updateAvailability(body: UpdateAvailabilityRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<UpdateAvailabilityResponse>>;
    public updateAvailability(body: UpdateAvailabilityRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling updateAvailability.');
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

        return this.httpClient.request<UpdateAvailabilityResponse>('post',`${this.basePath}/GeoCode/updateAvailability`,
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
