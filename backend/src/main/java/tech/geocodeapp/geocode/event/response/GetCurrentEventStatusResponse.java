package tech.geocodeapp.geocode.event.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import tech.geocodeapp.geocode.event.model.UserEventStatus;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.geocode.model.GeoCode;

import javax.validation.Valid;

/**
 * GetCurrentEventGeoCodeResponse object to return a User's current progress and target in an Event
 */
@Validated
public class GetCurrentEventStatusResponse extends Response {

    /**
     * The status of the User in this Event so far
     */
    @JsonProperty( "status" )
    private UserEventStatus status;

    /**
     * The found GeoCode
     */
    @JsonProperty( "targetGeocode" )
    private GeoCode targetGeocode;

    /**
     * Overloaded Constructor
     *
     * @param status        The status of the User in this Event so far
     * @param targetGeocode The target GeoCode
     */
    public GetCurrentEventStatusResponse( boolean success, String message, UserEventStatus status, GeoCode targetGeocode ) {

        super( success, message );
        this.status = status;
        this.targetGeocode = targetGeocode;
    }

    /**
     * Sets the status attribute to the specified value
     *
     * @param status the value the attribute should be set to
     *
     * @return the request after the status has been changed
     */
    public GetCurrentEventStatusResponse status( UserEventStatus status ) {

        this.status = status;
        return this;
    }

    /**
     * Gets the saved status attribute
     *
     * @return the stored status attribute
     */
    @Valid
    public UserEventStatus getStatus() {

        return status;
    }

    /**
     * Sets the status attribute to the specified value
     *
     * @param status the value the attribute should be set to
     */
    public void setStatus( UserEventStatus status ) {

        this.status = status;
    }

    /**
     * Sets the targetGeocode attribute to the specified value
     *
     * @param targetGeocode the value the attribute should be set to
     *
     * @return the request after the targetGeocode has been changed
     */
    public GetCurrentEventStatusResponse targetGeocode( GeoCode targetGeocode ) {

        this.targetGeocode = targetGeocode;
        return this;
    }

    /**
     * Gets the saved targetGeocode attribute
     *
     * @return the stored targetGeocode attribute
     */
    @Valid
    public GeoCode getTargetGeocode() {

        return targetGeocode;
    }

    /**
     * Sets the targetGeocode attribute to the specified value
     *
     * @param targetGeocode the value the attribute should be set to
     */
    public void setTargetGeocode( GeoCode targetGeocode ) {

        this.targetGeocode = targetGeocode;
    }

}
