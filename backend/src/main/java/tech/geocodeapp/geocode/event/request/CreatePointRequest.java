package tech.geocodeapp.geocode.event.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.Objects;

/**
 * CreatePointRequest
 */
@Validated
public class CreatePointRequest {

    /**
     * The value to set a single point to when it is created
     */
    @JsonProperty( "amount" )
    @NotNull( message = "CreatePointRequest success attribute cannot be null." )
    private Double amount;

    /**
     * Overloaded Constructor
     *
     * @param amount the value the created Point object should store
     */
    public CreatePointRequest( @Valid Double amount ) {

        this.amount = amount;
    }

    /**
     * Sets the amount attribute to the specified value
     *
     * @param amount the value the attribute should be set to
     *
     * @return the request after the amount has been changed
     */
    public CreatePointRequest amount( @Valid Double amount ) {

        this.amount = amount;
        return this;
    }

    /**
     * Gets the saved amount attribute
     *
     * @return the stored amount attribute
     */
    @Valid
    public Double getAmount() {

        return amount;
    }

    /**
     * Sets the amount attribute to the specified value
     *
     * @param amount the value the attribute should be set to
     */
    public void setAmount( @Valid Double amount ) {

        this.amount = amount;
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

        return Objects.equals( this.amount, ( ( CreatePointRequest ) obj ).amount );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( amount );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class CreatePointRequest {\n" +
                "    amount: " + toIndentedString( amount ) + "\n" +
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
