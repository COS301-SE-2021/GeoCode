package tech.geocodeapp.geocode.geocode.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

import tech.geocodeapp.geocode.collectable.model.Collectable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * GetCollectablesResponse used to access the attributes received from the response
 * to get all the collectables in the specified GeoCode
 */
@Validated
public class GetCollectablesResponse {

    /**
     * The list of collectables inside of the stored GeoCode
     */
    @Valid
    @JsonProperty( "collectables" )
    private List< UUID > collectables = new ArrayList<>();

    /**
     * Default constructor
     */
    public GetCollectablesResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param collectables The list of collectables inside of the stored GeoCode
     */
    public GetCollectablesResponse( List< UUID > collectables ) {

        this.collectables = collectables;
    }

    /**
     * Sets the collectables attribute to the specified value
     *
     * @param collectables the value the attribute should be set to
     *
     * @return the request after the collectables has been changed
     */
    public GetCollectablesResponse collectables( List< UUID > collectables ) {

        this.collectables = collectables;
        return this;
    }

    /**
     * Sets a single hint inside of the collectables attribute to the specified value
     *
     * @param collectablesItem the value the attribute should be set to
     *
     * @return the stored collectables attribute
     */
    public GetCollectablesResponse addCollectablesItem( UUID collectablesItem ) {

        this.collectables.add( collectablesItem );
        return this;
    }

    /**
     * Gets the saved collectables attribute
     *
     * @return the stored collectables attribute
     */
    @Valid
    public List< UUID > getCollectables() {

        return collectables;
    }

    /**
     * Sets the collectables attribute to the specified value
     *
     * @param collectables the value the attribute should be set to
     */
    public void setCollectables( List< UUID > collectables ) {

        this.collectables = collectables;
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

        return Objects.equals( this.collectables, ( ( GetCollectablesResponse ) obj ).collectables );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( collectables );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GetCollectablesResponse {\n" +
                "    collectables: " + toIndentedString( collectables ) + "\n" +
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
