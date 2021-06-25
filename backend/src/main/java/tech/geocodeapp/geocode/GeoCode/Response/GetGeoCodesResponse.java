package tech.geocodeapp.geocode.GeoCode.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

import tech.geocodeapp.geocode.GeoCode.Model.GeoCode;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

/**
 * GetGeoCodesResponse used to access the attributes received to create the response
 * to get all the GeoCodes stored in the db
 */
@Validated
public class GetGeoCodesResponse {

    /**
     * A list of all the GeoCodes
     */
    @JsonProperty( "geocodes" )
    @Valid
    private List< GeoCode > geocodes = new ArrayList<>();

    /**
     * Default constructor
     */
    public GetGeoCodesResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param geocodes A list of all the GeoCodes
     */
    public GetGeoCodesResponse( List< GeoCode > geocodes ) {

        this.geocodes = geocodes;
    }

    /**
     * Sets the geocodes attribute to the specified value
     *
     * @param geocodes the value the attribute should be set to
     *
     * @return the request after the geocodes has been changed
     */
    public GetGeoCodesResponse geocodes( List< GeoCode > geocodes ) {

        this.geocodes = geocodes;
        return this;
    }

    /**
     * Sets a single hint inside of the geocodes attribute to the specified value
     *
     * @param geocodesItem the value the attribute should be set to
     *
     * @return the stored geocodes attribute
     */
    public GetGeoCodesResponse addGeocodesItem( GeoCode geocodesItem ) {

        this.geocodes.add( geocodesItem );
        return this;
    }

    /**
     * Gets the saved geocodes attribute
     *
     * @return the stored geocodes attribute
     */
    @Valid
    public List< GeoCode > getGeocodes() {

        return geocodes;
    }

    /**
     * Sets the geocodes attribute to the specified value
     *
     * @param geocodes the value the attribute should be set to
     */
    public void setGeocodes( List< GeoCode > geocodes ) {

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

        return Objects.equals( this.geocodes, ( ( GetGeoCodesResponse ) obj ).geocodes );
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

        return "class GetGeoCodesResponse {\n" +
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
