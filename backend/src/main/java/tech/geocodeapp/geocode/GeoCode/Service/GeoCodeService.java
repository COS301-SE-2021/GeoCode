package tech.geocodeapp.geocode.GeoCode.Service;

import io.swagger.model.*;
import tech.geocodeapp.geocode.GeoCode.Response.*;
import tech.geocodeapp.geocode.GeoCode.Request.*;
import tech.geocodeapp.geocode.GeoCode.Exceptions.*;

/**
 * This is the main interface is for the GeoCode subsystem,
 * it is used to call the relevant use cases to create, manipulate and delete GeoCodes.
 */
public interface GeoCodeService {

    CreateGeoCodeResponse createGeoCode( CreateGeoCodeRequest request ) throws InvalidRequestException, QRCodeException, RepoException;

    GetGeoCodesResponse getAllGeoCodes( ) throws RepoException;

    GetCollectablesResponse getCollectables( GetCollectablesRequest request ) throws InvalidRequestException, RepoException;

    GetGeoCodesByDifficultyResponse getGeoCodesByDifficulty( GetGeoCodesByDifficultyRequest request ) throws InvalidRequestException, RepoException;

    GetHintsResponse getHints( GetHintsRequest request ) throws InvalidRequestException, RepoException;

    GetGeoCodeByQRCodeResponse getGeocodeByQRCode( GetGeoCodeByQRCodeRequest request ) throws InvalidRequestException, RepoException;

    GetGeoCodeByLocationResponse getGeoCodesByLocation( GetGeoCodeByLocationRequest request ) throws InvalidRequestException, RepoException;

    GetTrackablesResponse getTrackables( GetTrackablesRequest request ) throws InvalidRequestException, RepoException;

    SwapCollectablesResponse swapCollectables( SwapCollectablesRequest request ) throws InvalidRequestException, RepoException;

    UpdateAvailabilityResponse updateAvailability( UpdateAvailabilityRequest request ) throws RepoException, InvalidRequestException;
}
