package tech.geocodeapp.geocode.geocode.response;

import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.general.response.Response;

/**
 * CreateGeoCodeResponse used to access the attributes received to create the response
 * of if a GeoCode was created
 */
@Validated
public class UpdateGeoCodeResponse extends Response {
    /**
     * Default constructor
     */
    public UpdateGeoCodeResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param success Determines if the creation of a GeoCode with the specified attributes in the request was a success or not
     * @param message A message about the success of updating
     */
    public UpdateGeoCodeResponse( Boolean success, String message ) {
        super(success, message);
    }
}
