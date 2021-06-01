package tech.geocodeapp.geocode.GeoCode.Service;

import io.swagger.model.CreateGeoCodeRequest;
import io.swagger.model.CreateGeoCodeResponse;
import io.swagger.model.GetGeoCodesResponse;
import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.GeoCode.Exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.GeoCode.Exceptions.QRCodeException;

/**
 * This is the main interface is for the GeoCode subsystem,
 * it is used to call the relevant use cases to create, manipulate and delete GeoCodes.
 */
@Service
public interface GeoCodeService {

    CreateGeoCodeResponse createGeoCode(CreateGeoCodeRequest request ) throws InvalidRequestException, QRCodeException;

    GetGeoCodesResponse getGeoCodes();
}
