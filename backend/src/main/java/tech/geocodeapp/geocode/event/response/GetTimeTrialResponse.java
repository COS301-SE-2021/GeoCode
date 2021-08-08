package tech.geocodeapp.geocode.event.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import tech.geocodeapp.geocode.event.model.TimeTrial;

import java.util.Objects;

/**
 * GetTimeTrialResponse object to hold the found TimeTrial
 */
@Validated
public class GetTimeTrialResponse {

    /**
     * Determines if an Event was found or not
     */
    @JsonProperty( "found" )
    @NotNull( message = "GetTimeTrialResponse found attribute cannot be null." )
    private Boolean found;

    /**
     * The found TimeTrial
     */
    @JsonProperty( "foundEvent" )
    private TimeTrial foundEvent = null;

    /**
     * Default Constructor
     */
    public GetTimeTrialResponse() {

    }

    /**
     *  Overloaded Constructor
     *
     * @param found Determines if an Event was found or not
     */
    public GetTimeTrialResponse( Boolean found ) {

        this.found = found;
    }

    /**
     *  Overloaded Constructor
     *
     * @param found Determines if an Event was found or not
     * @param foundEvent The found Event
     */
    public GetTimeTrialResponse( Boolean found, TimeTrial foundEvent ) {

        this.found = found;
        this.foundEvent = foundEvent;
    }

    /**
     * Sets the found attribute to the specified value
     *
     * @param found the value the attribute should be set to
     *
     * @return the request after the found has been changed
     */
    public GetTimeTrialResponse found( Boolean found ) {

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
     * Sets the foundEvent attribute to the specified value
     *
     * @param foundEvent the value the attribute should be set to
     *
     * @return the request after the foundEvent has been changed
     */
    public GetTimeTrialResponse foundEvent( TimeTrial foundEvent ) {

        this.foundEvent = foundEvent;
        return this;
    }

    /**
     * Gets the saved foundEvent attribute
     *
     * @return the stored foundEvent attribute
     */
    @Valid
    public TimeTrial getFoundEvent() {

        return foundEvent;
    }

    /**
     * Sets the foundEvent attribute to the specified value
     *
     * @param foundEvent the value the attribute should be set to
     */
    public void setFoundEvent( TimeTrial foundEvent ) {

        this.foundEvent = foundEvent;
    }

    /**
     * Determines if the specified object is the same as the current object
     *
     * @param obj the object we want to compare with the specific attributes of this class
     *
     * @return if the object is the same or not
     */
    @Override
    public boolean equals( java.lang.Object obj ) {

        if ( this == obj ) {

            return true;
        }
        if ( obj == null || getClass() != obj.getClass() ) {

            return false;
        }

        var getTimeTrialResponse = ( GetTimeTrialResponse ) obj;
        return Objects.equals( this.found, getTimeTrialResponse.found ) &&
                Objects.equals( this.foundEvent, getTimeTrialResponse.foundEvent );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( found, foundEvent );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GetTimeTrialResponse {\n" +
                "    found: " + toIndentedString( found ) + "\n" +
                "    foundEvent: " + toIndentedString( foundEvent ) + "\n" +
                "}";
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString( java.lang.Object o ) {

        if ( o == null ) {

            return "null";
        }

        return o.toString().replace( "\n", "\n    " );
    }

}
