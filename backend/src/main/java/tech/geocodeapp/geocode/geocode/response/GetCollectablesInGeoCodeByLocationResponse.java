package tech.geocodeapp.geocode.geocode.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.collectable.model.Collectable;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Objects;

/**
 * GetCollectablesInGeoCodesByLocationResponse used to access the attributes received from the response
 * to get the specified GeoCode from the given location
 */
@Validated
public class GetCollectablesInGeoCodeByLocationResponse {

    /**
     * The unique storedCollectable of the found GeoCode with the specified QR Code
     */
    @JsonProperty( "storedCollectable" )
    private ArrayList< Collectable > storedCollectable;

    /**
     * Default constructor
     */
    public GetCollectablesInGeoCodeByLocationResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param storedCollectable The unique storedCollectable of the found GeoCode with the specified QR Code
     */
    public GetCollectablesInGeoCodeByLocationResponse(ArrayList< Collectable > storedCollectable ) {

        this.storedCollectable = storedCollectable;
    }

    /**
     * Sets the storedCollectable attribute to the specified value
     *
     * @param storedCollectable the value the attribute should be set to
     *
     * @return the response after the storedCollectable has been changed
     */
    public GetCollectablesInGeoCodeByLocationResponse storedCollectable(ArrayList< Collectable > storedCollectable ) {

        this.storedCollectable = storedCollectable;
        return this;
    }

    /**
     * Gets the saved storedCollectable attribute
     *
     * @return the stored storedCollectable attribute
     */
    @Valid
    public ArrayList< Collectable > getStoredCollectable() {

        return storedCollectable;
    }

    /**
     * Sets the storedCollectable attribute to the specified value
     *
     * @param storedCollectable the value the attribute should be set to
     */
    public void setStoredCollectable( ArrayList< Collectable > storedCollectable ) {

        this.storedCollectable = storedCollectable;
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

        return Objects.equals( this.storedCollectable, ( (GetCollectablesInGeoCodeByLocationResponse) obj ).storedCollectable );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( storedCollectable );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GetCollectablesInGeoCodesByLocationResponse {\n" +
                "    storedCollectable: " + toIndentedString( storedCollectable ) + "\n" +
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
