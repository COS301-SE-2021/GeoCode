package tech.geocodeapp.geocode.event.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * CreateTimeTrialResponse object that determines if the TimeTrial was created successfully
 */
@Validated
public class CreateTimeTrialResponse {

    /**
     * If the TimeTrial was successfully created or not
     */
    @JsonProperty( "success" )
    @NotNull( message = "CreateTimeTrialResponse success attribute cannot be null." )
    private Boolean success;

    /**
     * Default constructor
     */
    public CreateTimeTrialResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param success the status of the TimeTrial created
     */
    public CreateTimeTrialResponse( Boolean success ) {

        this.success = success;
    }

    /**
     * Sets the success attribute to the specified value
     *
     * @param success the value the attribute should be set to
     *
     * @return the request after the success has been changed
     */
    public CreateTimeTrialResponse success( Boolean success ) {

        this.success = success;
        return this;
    }

    /**
     * Gets the saved success attribute
     *
     * @return the stored success attribute
     */
    @Valid
    public Boolean isSuccess() {

        return success;
    }

    /**
     * Sets the success attribute to the specified value
     *
     * @param success the value the attribute should be set to
     */
    public void setSuccess( Boolean success ) {

        this.success = success;
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

        return Objects.equals( this.success, ( ( CreateTimeTrialResponse ) obj ).success );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( success );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class CreateTimeTrialResponse {\n" +
                "    success: " + toIndentedString( success ) + "\n" +
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
