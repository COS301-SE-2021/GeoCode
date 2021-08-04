package tech.geocodeapp.geocode.geocode.service;

import tech.geocodeapp.geocode.geocode.exceptions.*;
import tech.geocodeapp.geocode.geocode.response.*;
import tech.geocodeapp.geocode.geocode.request.*;
import tech.geocodeapp.geocode.user.exception.NullUserRequestParameterException;

/**
 * This is the main interface is for the GeoCode subsystem,
 * it is used to call the relevant use cases to create, manipulate and delete GeoCodes.
 */
public interface GeoCodeService {

    /**
     * Create a new GeoCode and store it in the GeoCodeRepository
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    CreateGeoCodeResponse createGeoCode( CreateGeoCodeRequest request ) throws InvalidRequestException;

    /**
     * Get all the stored GeoCodes in the repository
     *
     * @return the newly created response instance
     */
    GetGeoCodesResponse getAllGeoCodes( );

    /**
     * Get the stored Collectables inside of a GeoCode
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetCollectablesRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    GetCollectablesResponse getCollectables( GetCollectablesRequest request ) throws InvalidRequestException;

    /**
     * Get all the GeoCodes with a certain level of difficulty
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetGeoCodesByDifficultyRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    GetGeoCodesByDifficultyResponse getGeoCodesByDifficulty( GetGeoCodesByDifficultyRequest request ) throws InvalidRequestException;

    /**
     * Get the hints of how to locate a GeoCode in the real world
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetHintsRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    GetHintsResponse getHints( GetHintsRequest request ) throws InvalidRequestException;

    /**
     * Finds the stored GeoCode associated with the generated QR Code
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetGeoCodeByQRCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    GetGeoCodeByQRCodeResponse getGeoCodeByQRCode( GetGeoCodeByQRCodeRequest request ) throws InvalidRequestException;

    /**
     * Finds the stored GeoCode associated with the generated QR Code and returns its Collectables
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetCollectablesInGeoCodeByQRCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    GetCollectablesInGeoCodeByQRCodeResponse getCollectablesInGeoCodeByQRCode( GetCollectablesInGeoCodeByQRCodeRequest request ) throws InvalidRequestException;

    /**
     * Finds the stored GeoCode associated at the given location
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetGeoCodeByLocationRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    GetGeoCodeByLocationResponse getGeoCodesByLocation( GetGeoCodeByLocationRequest request ) throws InvalidRequestException;

    /**
     * Finds the stored GeoCode associated at the given location and returns its Collectables
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetCollectablesInGeoCodesByLocationRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    GetCollectablesInGeoCodesByLocationResponse getCollectablesInGeoCodesByLocation( GetCollectablesInGeoCodesByLocationRequest request ) throws InvalidRequestException;

    /**
     * Swaps a stored Collectable in a GeoCode with the Users GeoCode
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified SwapCollectablesRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    SwapCollectablesResponse swapCollectables( SwapCollectablesRequest request ) throws InvalidRequestException;

    /**
     * Updates the availability of a GeoCode
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified UpdateAvailabilityRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    UpdateAvailabilityResponse updateAvailability( UpdateAvailabilityRequest request ) throws InvalidRequestException;

}
