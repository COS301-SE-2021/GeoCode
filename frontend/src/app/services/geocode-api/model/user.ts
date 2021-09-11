/**
 * Swagger GeoCode
 * This is the swagger documentation and API for the GeoCode project implemented by Peak Performers for the client [5DT](https://5dt.com/).
 *
 * OpenAPI spec version: 1.5.18
 * Contact: peakperformers@geocodeapp.tech
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { Collectable } from './collectable';
import { CollectableType } from './collectableType';
import { GeoCode } from './geoCode';
import { Mission } from './mission';
import { Point } from './point';

export interface User { 
    id: string;
    username: string;
    trackableObject?: Collectable;
    points?: Array<Point>;
    currentCollectable?: Collectable;
    foundCollectableTypes?: Array<CollectableType>;
    foundGeocodes?: Array<GeoCode>;
    ownedGeocodes?: Array<GeoCode>;
    missions?: Array<Mission>;
}