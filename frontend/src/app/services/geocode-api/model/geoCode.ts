/**
 * Swagger GeoCode
 * This is the swagger documentation and API for the GeoCode project implemented by Peak Performers for the client [5DT](https://5dt.com/).
 *
 * OpenAPI spec version: 1.5.4
 * Contact: peakperformers@geocodeapp.tech
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { Difficulty } from './difficulty';
import { GeoPoint } from './geoPoint';

export interface GeoCode { 
    id: string;
    difficulty: Difficulty;
    available: boolean;
    description: string;
    hints: Array<string>;
    collectables?: Array<string>;
    qrCode: string;
    location: GeoPoint;
    createdBy?: string;
    eventID?: string;
}