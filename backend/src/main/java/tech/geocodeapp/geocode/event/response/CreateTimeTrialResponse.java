package tech.geocodeapp.geocode.event.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import tech.geocodeapp.geocode.event.model.TimeTrial;

import java.util.Objects;

/**
 * CreateTimeTrialResponse
 */
@Validated
public class CreateTimeTrialResponse {

    /**
     * The newly create timeTrial
     */
    @JsonProperty( "timeTrial" )
    private TimeTrial timeTrial;

    /**
     * Default Constructor
     */
    public CreateTimeTrialResponse() {

    }

    /**
     *  Overloaded Constructor
     *
     * @param timeTrial The newly create timeTrial
     */
    public CreateTimeTrialResponse( TimeTrial timeTrial ) {

        this.timeTrial = timeTrial;
    }

    /**
     * Sets the timeTrial attribute to the specified value
     *
     * @param timeTrial the value the attribute should be set to
     *
     * @return the request after the name has been changed
     */
    public CreateTimeTrialResponse timeTrial( TimeTrial timeTrial ) {

        this.timeTrial = timeTrial;
        return this;
    }

    /**
     * Gets the saved timeTrial attribute
     *
     * @return the stored timeTrial attribute
     */
    public TimeTrial getTimeTrial() {

        return timeTrial;
    }

    /**
     * Sets the timeTrial attribute to the specified value
     *
     * @param timeTrial the value the attribute should be set to
     */
    public void setTimeTrial( TimeTrial timeTrial ) {

        this.timeTrial = timeTrial;
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

        return Objects.equals( this.timeTrial, ( ( CreateTimeTrialResponse ) obj ).timeTrial );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( timeTrial );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class CreateTimeTrialResponse {\n" +
                "    timeTrial: " + toIndentedString( timeTrial ) + "\n" +
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
