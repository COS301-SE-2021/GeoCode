package tech.geocodeapp.geocode.GeoCode.Service;

import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.GeoCode.Exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.GeoCode.Exceptions.QRCodeException;
import tech.geocodeapp.geocode.GeoCode.Request.CreateGeoCodeRequest;
import tech.geocodeapp.geocode.GeoCode.Request.GetAllGeoCodesRequest;
import tech.geocodeapp.geocode.GeoCode.Response.CreateGeoCodeResponse;
import tech.geocodeapp.geocode.GeoCode.Response.GetAllGeoCodesResponse;

/**
 * This is the main interface is for the GeoCode subsystem,
 * it is used to call the relevant use cases to create, manipulate and delete GeoCodes.
 */
@Service
public interface GeoCodeService {

    CreateGeoCodeResponse createGeoCode( CreateGeoCodeRequest request ) throws InvalidRequestException, QRCodeException;

    GetAllGeoCodesResponse getAllGeoCode( GetAllGeoCodesRequest request );
}
