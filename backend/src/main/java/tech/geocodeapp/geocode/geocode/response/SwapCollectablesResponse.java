package tech.geocodeapp.geocode.geocode.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.general.response.Response;

import java.util.Objects;

/**
 * SwapCollectablesResponse used to access the attributes received to create the response
 * to determine if swapping a collectable in the GeoCode with the user's was a success
 */
@Validated
public class SwapCollectablesResponse extends Response {
    /**
     * Default constructor
     */
    public SwapCollectablesResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param success Determines if the creation of a GeoCode with the specified attributes in the request was a success or not
     */
    public SwapCollectablesResponse( Boolean success, String message ) {
        super(success, message);
    }
}
