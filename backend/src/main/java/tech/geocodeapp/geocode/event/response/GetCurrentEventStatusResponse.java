package tech.geocodeapp.geocode.event.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import tech.geocodeapp.geocode.event.model.UserEventStatus;
import tech.geocodeapp.geocode.geocode.model.GeoCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * GetCurrentEventGeoCodeResponse object to return a User's current progress and target in an Event
 */
@Validated
public class GetCurrentEventStatusResponse {

    /**
     * Determines if an GeoCode was found or not
     */
    @JsonProperty( "found" )
    @NotNull( message = "GetCurrentEventGeoCodeResponse eventID attribute cannot be null." )
    private Boolean found;

    /**
     * The status of the User in this Event so far
     */
    @JsonProperty( "status" )
    private UserEventStatus status = null;

    /**
     * The found GeoCode
     */
    @JsonProperty( "targetGeocode" )
    private GeoCode targetGeocode = null;

    /**
     * Default Constructor
     */
    public GetCurrentEventStatusResponse() {

    }

    /**
     *  Overloaded Constructor
     *
     * @param found Determines if an GeoCode was found or not
     */
    public GetCurrentEventStatusResponse(Boolean found ) {

        this.found = found;

    }

    /**
     *  Overloaded Constructor
     *
     * @param found Determines if an GeoCode was found or not
     * @param status The status of the User in this Event so far
     * @param targetGeocode The target GeoCode
     */
    public GetCurrentEventStatusResponse(Boolean found, UserEventStatus status, GeoCode targetGeocode ) {

        this.found = found;
        this.status = status;
        this.targetGeocode = targetGeocode;
    }

    /**
     * Sets the found attribute to the specified value
     *
     * @param found the value the attribute should be set to
     *
     * @return the request after the found has been changed
     */
    public GetCurrentEventStatusResponse found(Boolean found ) {

        this.found = found;
        return this;
    }

    /**
     * Gets the saved found attribute
     *
     * @return the stored found attribute
     */
    public Boolean isFound() {

        return found;
    }

    /**
     * Sets the found attribute to the specified value
     *
     * @param found the value the attribute should be set to
     */
    public void setFound( Boolean found ) {

        this.found = found;
    }

    /**
     * Sets the status attribute to the specified value
     *
     * @param status the value the attribute should be set to
     *
     * @return the request after the status has been changed
     */
    public GetCurrentEventStatusResponse status(UserEventStatus status ) {

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
    public GetCurrentEventStatusResponse targetGeocode(GeoCode targetGeocode ) {

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

    /**
     * Determines if the specified object is the same as the current object
     *
     * @param obj the object we want to compare with the specific attributes of this class
     *
     * @return if the object is the same or not
     */
    @Override
    public boolean equals( Object obj ) {

        if ( this == obj ) {

            return true;
        }
        if ( obj == null || getClass() != obj.getClass() ) {

            return false;
        }

        GetCurrentEventStatusResponse getCurrentEventResponse = (GetCurrentEventStatusResponse) obj;
        return Objects.equals( this.found, getCurrentEventResponse.found ) &&
                Objects.equals( this.status, getCurrentEventResponse.status ) &&
                Objects.equals( this.targetGeocode, getCurrentEventResponse.targetGeocode );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( found, status, targetGeocode );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GetCurrentEventGeoCodeResponse {\n" +
                "    found: " + toIndentedString( found ) + "\n" +
                "    status: " + toIndentedString( status ) + "\n" +
                "    targetGeoCode: " + toIndentedString( targetGeocode ) + "\n" +
                "}";
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString( Object o ) {

        if ( o == null ) {

            return "null";
        }

        return o.toString().replace( "\n", "\n    " );
    }

}
