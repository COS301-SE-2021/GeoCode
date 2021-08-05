package tech.geocodeapp.geocode.event.request;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import tech.geocodeapp.geocode.geocode.model.GeoPoint;

import java.util.Objects;

/**
 * EventsNearMeRequest object to retrieve Events in the vicinity of the user
 */
@Validated
public class EventsNearMeRequest {

    /**
     * The location to look for Events for
     */
    @JsonProperty( "location" )
    @NotNull( message = "EventsNearMeRequest location attribute cannot be null." )
    private GeoPoint location;

    /**
     * The radius to search around the location for Events
     */
    @JsonProperty( "radius" )
    @NotNull( message = "EventsNearMeRequest radius attribute cannot be null." )
    private double radius;

    /**
     * Default Constructor
     */
    public EventsNearMeRequest() {

    }

    /**
     * Overloaded Constructor
     *
     * @param location The location to look for Events for
     * @param radius The radius to search around the location for Events
     */
    public EventsNearMeRequest( GeoPoint location, double radius ) {

        this.location = location;
        this.radius = radius;
    }

    /**
     * Sets the location attribute to the specified value
     *
     * @param location the value the attribute should be set to
     *
     * @return the request after the location has been changed
     */
    public EventsNearMeRequest location( GeoPoint location ) {

        this.location = location;
        return this;
    }

    /**
     * Gets the saved location attribute
     *
     * @return the stored location attribute
     */
    @Valid
    public GeoPoint getLocation() {

        return location;
    }

    /**
     * Sets the location attribute to the specified value
     *
     * @param location the value the attribute should be set to
     */
    public void setLocation( GeoPoint location ) {

        this.location = location;
    }

    /**
     * Sets the radius attribute to the specified value
     *
     * @param radius the value the attribute should be set to
     *
     * @return the request after the radius has been changed
     */
    public EventsNearMeRequest radius( double radius ) {

        this.radius = radius;
        return this;
    }

    /**
     * Gets the saved radius attribute
     *
     * @return the stored eventID attribute
     */
    public double getRadius() {

        return radius;
    }

    /**
     * Sets the radius attribute to the specified value
     *
     * @param radius the value the attribute should be set to
     */
    public void setRadius( double radius ) {

        this.radius = radius;
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

        EventsNearMeRequest eventsNearMeRequest = ( EventsNearMeRequest ) obj;
        return Objects.equals( this.location, eventsNearMeRequest.location ) &&
                Objects.equals( this.radius, eventsNearMeRequest.radius );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( location, radius );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class EventsNearMeRequest {\n" +
                "    location: " + toIndentedString( location ) + "\n" +
                "    radius: " + toIndentedString( radius ) + "\n" +
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
