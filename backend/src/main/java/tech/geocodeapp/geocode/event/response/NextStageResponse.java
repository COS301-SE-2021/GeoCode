package tech.geocodeapp.geocode.event.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

import tech.geocodeapp.geocode.event.model.ProgressLog;
import tech.geocodeapp.geocode.geocode.model.GeoCode;

import java.util.Objects;
import java.util.UUID;

/**
 * NextStageResponse object to return the next GeoCode the user needs to search for
 */
@Validated
public class NextStageResponse {

    /**
     * The progress of the User in this Event so far. Includes the id of the next GeoCode they are searching for
     */
    @JsonProperty( "progress" )
    private ProgressLog progress;

    /**
     * Default Constructor
     */
    public NextStageResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param progress The progress of the User in this Event so far
     */
    public NextStageResponse( ProgressLog progress ) {

        this.progress = progress;
    }

    /**
     * Sets the progress attribute to the specified value
     *
     * @param progress the value the attribute should be set to
     *
     * @return the request after the progress has been changed
     */
    public NextStageResponse progress( ProgressLog progress ) {

        this.progress = progress;
        return this;
    }

    /**
     * Gets the saved progress attribute
     *
     * @return the stored progress attribute
     */
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

        return Objects.equals( this.progress, ( ( NextStageResponse ) obj ).progress );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( progress );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class NextStageResponse {\n" +
                "    progress: " + toIndentedString( progress ) + "\n" +
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
