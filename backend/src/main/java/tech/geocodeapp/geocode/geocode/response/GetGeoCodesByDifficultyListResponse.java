package tech.geocodeapp.geocode.geocode.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;

import tech.geocodeapp.geocode.geocode.model.GeoCode;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

/**
 * GetGeoCodesByDifficultyListResponse used to access the attributes received to create the response
 * to filter all the GeoCodes by a specified difficulty
 */
@Validated
public class GetGeoCodesByDifficultyListResponse {

    /**
     * The list of GeoCodes with the specified difficulty
     */
    @Valid
    @JsonProperty( "geocodes" )
    private List<GeoCode> geocodes;

    /**
     * Default constructor
     */
    public GetGeoCodesByDifficultyListResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param geocodes The list of GeoCodes with the specified difficulty
     */
    public GetGeoCodesByDifficultyListResponse( List<GeoCode> geocodes ) {

        this.geocodes = geocodes;
    }

    /**
     * Sets the hints attribute to the specified value
     *
     * @param geocodes the value the attribute should be set to
     *
     * @return the request after the hints has been changed
     */
    public GetGeoCodesByDifficultyListResponse geocodes( List<GeoCode> geocodes ) {

        this.geocodes = geocodes;
        return this;
    }

    /**
     * Sets a single hint inside of the hints attribute to the specified value
     *
     * @param geocodesItem the value the attribute should be set to
     *
     * @return the stored hints attribute
     */
    public GetGeoCodesByDifficultyListResponse addGeocodesItem( GeoCode geocodesItem ) {

        if ( this.geocodes == null ) {

            this.geocodes = new ArrayList<>();
        }

        this.geocodes.add( geocodesItem );
        return this;
    }

    /**
     * Gets the saved hints attribute
     *
     * @return the stored hints attribute
     */
    @Valid
    public List<GeoCode> getGeocodes() {

        return geocodes;
    }

    /**
     * Sets the hints attribute to the specified value
     *
     * @param geocodes the value the attribute should be set to
     */
    public void setGeocodes( List<GeoCode> geocodes ) {

        this.geocodes = geocodes;
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

        return Objects.equals( this.geocodes, ( ( GetGeoCodesByDifficultyListResponse ) obj ).geocodes );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( geocodes );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GetGeoCodesByDifficultyListResponse {\n" +
                "    geocodes: " + toIndentedString( geocodes ) + "\n" +
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
