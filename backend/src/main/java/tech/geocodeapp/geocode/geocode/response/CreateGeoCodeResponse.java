package tech.geocodeapp.geocode.geocode.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.geocode.model.GeoCode;

import javax.validation.Valid;

/**
 * CreateGeoCodeResponse used to access the attributes received to create the response
 * of if a GeoCode was created
 */
@Validated
public class CreateGeoCodeResponse extends Response {

    /**
     * The created GeoCode
     */
    @JsonProperty( "createdGeocode" )
    private GeoCode createdGeocode;

    /**
     * Default constructor
     */
    public CreateGeoCodeResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param success Determines if the creation of a GeoCode with the specified attributes in the request was a success or not
     */
    public CreateGeoCodeResponse( Boolean success, String message ) {
        super(success, message);
    }

    /**
     * Overloaded Constructor
     *
     * @param success Determines if the creation of a GeoCode with the specified attributes in the request was a success or not
     * @param createdGeocode The created GeoCode
     */
    public CreateGeoCodeResponse( Boolean success, String message, GeoCode createdGeocode ) {

        super(success, message);

        this.createdGeocode = createdGeocode;
    }

    /**
     * Sets the createdGeocode attribute to the specified value
     *
     * @param createdGeocode the value the attribute should be set to
     *
     * @return the request after the createdGeocode has been changed
     */
    public CreateGeoCodeResponse createdGeocode( GeoCode createdGeocode ) {

        this.createdGeocode = createdGeocode;
        return this;
    }

    /**
     * Gets the saved createdGeocode attribute
     *
     * @return the stored createdGeocode attribute
     */
    @Valid
    public GeoCode getCreatedGeocode() {

        return createdGeocode;
    }

    /**
     * Sets the createdGeocode attribute to the specified value
     *
     * @param createdGeocode the value the attribute should be set to
     */
    public void setCreatedGeocode( GeoCode createdGeocode ) {

        this.createdGeocode = createdGeocode;
    }

}
