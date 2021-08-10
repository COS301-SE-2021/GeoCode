package tech.geocodeapp.geocode.event.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import tech.geocodeapp.geocode.geocode.model.GeoCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * GetCurrentEventGeoCodeResponse object to return the found GeoCode
 */
@Validated
public class GetCurrentEventLevelResponse {

    /**
     * Determines if an GeoCode was found or not
     */
    @JsonProperty( "found" )
    @NotNull( message = "GetCurrentEventGeoCodeResponse eventID attribute cannot be null." )
    private Boolean found;

    /**
     * The found GeoCode
     */
    @JsonProperty( "foundGeoCode" )
    private GeoCode foundGeoCode = null;

    /**
     * Default Constructor
     */
    public GetCurrentEventLevelResponse() {

    }

    /**
     *  Overloaded Constructor
     *
     * @param found Determines if an GeoCode was found or not
     */
    public GetCurrentEventLevelResponse( Boolean found ) {

        this.found = found;

        if ( !found ) {

            this.foundGeoCode = null;
        }

    }

    /**
     *  Overloaded Constructor
     *
     * @param found Determines if an GeoCode was found or not
     * @param foundGeoCode The found GeoCode
     */
    public GetCurrentEventLevelResponse( Boolean found, GeoCode foundGeoCode ) {

        this.found = found;
        this.foundGeoCode = foundGeoCode;
    }

    /**
     * Sets the found attribute to the specified value
     *
     * @param found the value the attribute should be set to
     *
     * @return the request after the found has been changed
     */
    public GetCurrentEventLevelResponse found( Boolean found ) {

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
     * Sets the foundGeoCode attribute to the specified value
     *
     * @param foundEvent the value the attribute should be set to
     *
     * @return the request after the foundGeoCode has been changed
     */
    public GetCurrentEventLevelResponse foundEvent( GeoCode foundEvent ) {

        this.foundGeoCode = foundEvent;
        return this;
    }

    /**
     * Gets the saved foundGeoCode attribute
     *
     * @return the stored foundGeoCode attribute
     */
    @Valid
    public GeoCode getFoundGeoCode() {

        return foundGeoCode;
    }

    /**
     * Sets the foundGeoCode attribute to the specified value
     *
     * @param foundGeoCode the value the attribute should be set to
     */
    public void setFoundGeoCode( GeoCode foundGeoCode ) {

        this.foundGeoCode = foundGeoCode;
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

        GetCurrentEventLevelResponse getCurrentEventResponse = ( GetCurrentEventLevelResponse ) obj;
        return Objects.equals( this.found, getCurrentEventResponse.found ) &&
                Objects.equals( this.foundGeoCode, getCurrentEventResponse.foundGeoCode );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( found, foundGeoCode );
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
                "    foundGeoCode: " + toIndentedString( foundGeoCode ) + "\n" +
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
