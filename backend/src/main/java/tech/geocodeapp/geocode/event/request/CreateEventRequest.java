package tech.geocodeapp.geocode.event.request;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.Valid;

import tech.geocodeapp.geocode.geocode.model.GeoPoint;

import java.util.Objects;

/**
 * CreateEventRequest object that is used to specify the attributes for a new Event
 */
@Validated
public class CreateEventRequest {

    /**
     * The name of the Event to be created
     */
    @JsonProperty( "name" )
    @NotNull( message = "CreateEventRequest name attribute cannot be null." )
    private String name;

    /**
     * The description of what the Event to be created is
     */
    @JsonProperty( "description" )
    @NotNull( message = "CreateEventRequest description attribute cannot be null." )
    private String description;

    /**
     * The location where the Event will be held at in the real world
     */
    @JsonProperty( "location" )
    @NotNull( message = "CreateEventRequest location attribute cannot be null." )
    private GeoPoint location;

    /**
     * Overloaded Constructor
     *
     * @param name what the new Event should be called
     * @param description what the Event to be created is going to be about
     * @param location the Event will be held at in the real world's location
     */
    public CreateEventRequest( String name, String description, GeoPoint location ) {

        this.name = name;
        this.description = description;
        this.location = location;
    }

    /**
     * Sets the difficulty attribute to the specified value
     *
     * @param name the value the attribute should be set to
     *
     * @return the request after the difficulty has been changed
     */
    public CreateEventRequest name( String name ) {

        this.name = name;
        return this;
    }

    /**
     * Gets the saved difficulty attribute
     *
     * @return the stored difficulty attribute
     */
    @Valid
    public String getName() {

        return name;
    }

    /**
     * Sets the hints attribute to the specified value
     *
     * @param name the value the attribute should be set to
     */
    public void setName( String name ) {

        this.name = name;
    }

    /**
     * Sets the difficulty attribute to the specified value
     *
     * @param description the value the attribute should be set to
     *
     * @return the request after the difficulty has been changed
     */
    public CreateEventRequest description( String description ) {

        this.description = description;
        return this;
    }

    /**
     * Gets the saved difficulty attribute
     *
     * @return the stored difficulty attribute
     */
    @Valid
    public String getDescription() {

        return description;
    }

    /**
     * Sets the difficulty attribute to the specified value
     *
     * @param description the value the attribute should be set to
     */
    public void setDescription( String description ) {

        this.description = description;
    }

    /**
     * Sets the available attribute to the specified value
     *
     * @param location the value the attribute should be set to
     *
     * @return the request after the available has been changed
     */
    public CreateEventRequest location( GeoPoint location ) {

        this.location = location;
        return this;
    }

    /**
     * Gets the saved available attribute
     *
     * @return the stored available attribute
     */
    @Valid
    public GeoPoint getLocation() {

        return location;
    }

    /**
     * Sets the available attribute to the specified value
     *
     * @param location the value the attribute should be set to
     */
    public void setLocation( GeoPoint location ) {

        this.location = location;
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
        CreateEventRequest createEventRequest = ( CreateEventRequest ) obj;
        return  Objects.equals( this.name, createEventRequest.name ) &&
                Objects.equals( this.description, createEventRequest.description ) &&
                Objects.equals( this.location, createEventRequest.location );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( name, description, location );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class CreateEventRequest {\n" +
                "    name: " + toIndentedString( name ) + "\n" +
                "    description: " + toIndentedString( description ) + "\n" +
                "    location: " + toIndentedString( location ) + "\n" +
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
