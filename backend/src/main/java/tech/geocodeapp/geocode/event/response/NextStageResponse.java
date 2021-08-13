package tech.geocodeapp.geocode.event.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

import tech.geocodeapp.geocode.geocode.model.GeoCode;

import java.util.Objects;
import java.util.UUID;

/**
 * NextStageResponse object to return the next GeoCode the user needs to search for
 */
@Validated
public class NextStageResponse {

    /**
     * The next GeoCode the User needs to search for
     */
    @JsonProperty( "nextGeoCode" )
    private UUID nextGeoCode;

    /**
     * Default Constructor
     */
    public NextStageResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param nextGeoCode The next GeoCode the User needs to search for
     */
    public NextStageResponse( UUID nextGeoCode ) {

        this.nextGeoCode = nextGeoCode;
    }

    /**
     * Sets the nextGeoCode attribute to the specified value
     *
     * @param nextGeoCode the value the attribute should be set to
     *
     * @return the request after the nextGeoCode has been changed
     */
    public NextStageResponse nextGeoCode( UUID nextGeoCode ) {

        this.nextGeoCode = nextGeoCode;
        return this;
    }

    /**
     * Gets the saved nextGeoCode attribute
     *
     * @return the stored nextGeoCode attribute
     */
    public UUID getNextGeoCode() {

        return nextGeoCode;
    }

    /**
     * Sets the nextGeoCode attribute to the specified value
     *
     * @param nextGeoCode the value the attribute should be set to
     */
    public void setNextGeoCode( UUID nextGeoCode ) {

        this.nextGeoCode = nextGeoCode;
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

        return Objects.equals( this.nextGeoCode, ( ( NextStageResponse ) obj ).nextGeoCode );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( nextGeoCode );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class NextStageResponse {\n" +
                "    nextGeoCode: " + toIndentedString( nextGeoCode ) + "\n" +
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
