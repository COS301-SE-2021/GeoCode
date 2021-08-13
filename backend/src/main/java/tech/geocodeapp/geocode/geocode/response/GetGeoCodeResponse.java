package tech.geocodeapp.geocode.geocode.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;

import tech.geocodeapp.geocode.geocode.model.GeoCode;

import java.util.Objects;

/**
 * GetGeoCodeResponse
 */
@Validated
public class GetGeoCodeResponse {

    /**
     * The found GeoCode with the given ID
     */
    @JsonProperty( "foundGeoCode" )
    private GeoCode foundGeoCode;

    /**
     * Default constructor
     */
    public GetGeoCodeResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param foundGeoCode The found GeoCode with the given ID
     */
    public GetGeoCodeResponse( GeoCode foundGeoCode ) {

        this.foundGeoCode = foundGeoCode;
    }

    /**
     * Sets the foundGeoCode attribute to the specified value
     *
     * @param foundGeoCode the value the attribute should be set to
     *
     * @return the request after the foundGeoCode has been changed
     */
    public GetGeoCodeResponse foundGeoCode( GeoCode foundGeoCode ) {

        this.foundGeoCode = foundGeoCode;
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
    public boolean equals( java.lang.Object obj ) {

        if ( this == obj ) {

            return true;
        }
        if ( obj == null || getClass() != obj.getClass() ) {

            return false;
        }

        return Objects.equals( this.foundGeoCode, ( ( GetGeoCodeResponse ) obj ).foundGeoCode );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( foundGeoCode );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GetGeoCodeResponse {\n" +
                "    foundGeoCode: " + toIndentedString( foundGeoCode ) + "\n" +
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
