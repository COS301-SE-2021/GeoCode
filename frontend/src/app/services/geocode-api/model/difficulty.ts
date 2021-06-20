/**
 * Swagger GeoCode
 * This is the services documentation and API for the GeoCode project implemented by Peak Performers for the geocode-api [5DT](https://5dt.com/).
 *
 * OpenAPI spec version: 1.0.0
 * Contact: peakperformers@geocodeapp.tech
 *
 * NOTE: This class is auto generated by the services code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

/**
 * The GeoCode's real world locating difficulty
 */
export type Difficulty = 'EASY' | 'MEDIUM' | 'DIFFICULTY' | 'INSANE' | 'null';

export const Difficulty = {
    EASY: 'EASY' as Difficulty,
    MEDIUM: 'MEDIUM' as Difficulty,
    DIFFICULTY: 'DIFFICULTY' as Difficulty,
    INSANE: 'INSANE' as Difficulty,
    Null: 'null' as Difficulty
};