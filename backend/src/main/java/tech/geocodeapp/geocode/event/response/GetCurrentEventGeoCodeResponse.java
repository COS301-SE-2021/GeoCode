package tech.geocodeapp.geocode.event.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import tech.geocodeapp.geocode.event.model.ProgressLog;
import tech.geocodeapp.geocode.geocode.model.GeoCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * GetCurrentEventGeoCodeResponse object to return a User's current progress and target in an Event
 */
@Validated
public class GetCurrentEventGeoCodeResponse {

    /**
     * Determines if an GeoCode was found or not
     */
    @JsonProperty( "found" )
    @NotNull( message = "GetCurrentEventGeoCodeResponse eventID attribute cannot be null." )
    private Boolean found;

    /**
     * The progress of the User in this Event so far
     */
    @JsonProperty( "progress" )
    private ProgressLog progress = null;

    /**
     * The found GeoCode
     */
    @JsonProperty( "targetGeocode" )
    private GeoCode targetGeocode = null;

    /**
     * Default Constructor
     */
    public GetCurrentEventGeoCodeResponse() {

    }

    /**
     *  Overloaded Constructor
     *
     * @param found Determines if an GeoCode was found or not
     */
    public GetCurrentEventGeoCodeResponse(Boolean found ) {

        this.found = found;

    }

    /**
     *  Overloaded Constructor
     *
     * @param found Determines if an GeoCode was found or not
     * @param targetGeocode The target GeoCode
     */
    public GetCurrentEventGeoCodeResponse(Boolean found, ProgressLog progress, GeoCode targetGeocode ) {

        this.found = found;
        this.progress = progress;
        this.targetGeocode = targetGeocode;
    }

    /**
     * Sets the found attribute to the specified value
     *
     * @param found the value the attribute should be set to
     *
     * @return the request after the found has been changed
     */
    public GetCurrentEventGeoCodeResponse found(Boolean found ) {

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
     * Sets the progress attribute to the specified value
     *
     * @param progress the value the attribute should be set to
     *
     * @return the request after the progress has been changed
     */
    public GetCurrentEventGeoCodeResponse progress(ProgressLog progress ) {

        this.progress = progress;
        return this;
    }

    /**
     * Gets the saved progress attribute
     *
     * @return the stored progress attribute
     */
    @Valid
    public ProgressLog getProgress() {

        return progress;
    }

    /**
     * Sets the progress attribute to the specified value
     *
     * @param progress the value the attribute should be set to
     */
    public void setProgress( ProgressLog progress ) {

        this.progress = progress;
    }

    /**
     * Sets the targetGeocode attribute to the specified value
     *
     * @param targetGeocode the value the attribute should be set to
     *
     * @return the request after the targetGeocode has been changed
     */
    public GetCurrentEventGeoCodeResponse targetGeocode(GeoCode targetGeocode ) {

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

        GetCurrentEventGeoCodeResponse getCurrentEventResponse = (GetCurrentEventGeoCodeResponse) obj;
        return Objects.equals( this.found, getCurrentEventResponse.found ) &&
                Objects.equals( this.progress, getCurrentEventResponse.progress ) &&
                Objects.equals( this.targetGeocode, getCurrentEventResponse.targetGeocode );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( found, progress, targetGeocode );
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
                "    progress: " + toIndentedString( progress ) + "\n" +
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
