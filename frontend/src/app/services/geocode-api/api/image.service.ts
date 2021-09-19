/**
 * Swagger GeoCode
 * This is the swagger documentation and API for the GeoCode project implemented by Peak Performers for the client [5DT](https://5dt.com/).
 *
 * OpenAPI spec version: 1.5.20
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

import { CreateImageResponse } from '../model/createImageResponse';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class ImageService {

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
     * Retrieves an image
     * Retrieves an image with the specified file name
     * @param fileName File name of the image to retrieve
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getImage(fileName: string, observe?: 'body', reportProgress?: boolean): Observable<Blob>;
    public getImage(fileName: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Blob>>;
    public getImage(fileName: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Blob>>;
    public getImage(fileName: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (fileName === null || fileName === undefined) {
            throw new Error('Required parameter fileName was null or undefined when calling getImage.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'image/gif',
            'image/png'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.request<Blob>('get',`${this.basePath}/Image/getImage/${encodeURIComponent(String(fileName))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Uploads an image
     * Uploads an image to the server and returns its ID
     * @param body 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public uploadImage(body?: Object, observe?: 'body', reportProgress?: boolean): Observable<CreateImageResponse>;
    public uploadImage(body?: Object, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<CreateImageResponse>>;
    public uploadImage(body?: Object, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<CreateImageResponse>>;
    public uploadImage(body?: Object, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {


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
            'image/bmp',
            'image/gif',
            'image/jpeg',
            'image/png'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.request<CreateImageResponse>('post',`${this.basePath}/Image/uploadImage`,
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
