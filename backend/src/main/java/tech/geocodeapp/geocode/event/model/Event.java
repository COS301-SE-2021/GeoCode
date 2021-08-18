package tech.geocodeapp.geocode.event.model;

import org.hibernate.annotations.Cascade;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import javax.validation.Valid;

import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

import java.time.LocalDate;
import java.util.*;

/**
 * The Event model that will be stored in as a table in the db
 */
@Entity
@Validated
public class Event {

    /**
     * The unique identifier for the Event
     */
    @Id
    @JsonProperty( "id" )
    @NotNull( message = "Event's id cannot be null." )
    protected UUID id;

    /**
     * What the Event is called
     */
    @JsonProperty( "name" )
    @NotEmpty( message = "Event's name cannot be empty." )
    protected String name;

    /**
     * A brief description on what the Event is about
     */
    @JsonProperty( "description" )
    @NotEmpty( message = "Event's description cannot be empty." )
    protected String description;

    /**
     * The starting location of the Event
     */
    @Embedded
    @NotNull( message = "Event's location cannot be null." )
    protected GeoPoint location;

    /**
     * The different GeoCodes to find
     */
    @Valid
    @JsonProperty( "geocodeIDs" )
    @ElementCollection( fetch = FetchType.EAGER )
    @NotNull( message = "Event's location cannot be null." )
    protected List< UUID > geocodeIDs;

    /**
     * The starting Date of the Event
     */
    @JsonProperty( "beginDate" )
    @NotNull( message = "CreateEventRequest beginDate attribute cannot be null." )
    protected LocalDate beginDate;

    /**
     * The end Date of the Event
     */
    @JsonProperty( "end" )
    @NotNull( message = "CreateEventRequest endDate attribute cannot be null." )
    protected LocalDate endDate;

    /**
     * The different rankings of the users partaking in the Event
     */
    @Valid
    @ManyToMany
    @JsonProperty( "leaderboards" )
    @NotNull( message = "Event's location cannot be null." )
    protected List< Leaderboard > leaderboards;

    /**
     * If the Event is active in the system for a user to participate
     */
    @JsonProperty( "available" )
    @NotNull( message = "Event's available cannot be null." )
    private Boolean available;

    /**
     * Shows the type of event
     */
    @JsonProperty( "properties" )
    @NotNull( message = "Event's properties cannot be null." )
    @ElementCollection
    private Map<String, String> properties;





    /**
     * Default constructor
     */
    public Event() {

    }

    /**
     * Overloaded Constructor
     *
     * @param id The unique identifier for the Event
     * @param name What the Event is called
     * @param description A brief description on what the Event is about
     * @param location The location of the Event
     * @param geocodeIDs The GeoCodes to find
     * @param beginDate The starting Date of the Event
     * @param endDate The end Date of the Event
     * @param leaderboards The different rankings of the users partaking in the Event
     * @param properties The properties of the Event
     */
    public Event( UUID id, String name, String description, GeoPoint location, List< UUID > geocodeIDs,
                  LocalDate beginDate, LocalDate endDate, List< Leaderboard > leaderboards, Map<String, String> properties) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.geocodeIDs = geocodeIDs;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.leaderboards = leaderboards;
        this.available = true;
        this.properties = properties;
    }

    /**
     * Overloaded Constructor
     *
     * @param id The unique identifier for the Event
     * @param name What the Event is called
     * @param description A brief description on what the Event is about
     * @param location The location of the Event
     * @param geocodeIDs The GeoCodes to find
     * @param beginDate The starting Date of the Event
     * @param endDate The end Date of the Event
     * @param leaderboards The different rankings of the users partaking in the Event
     * @param available If the Event is active in the system for a user to participate
     * @param properties The properties of the Event
     */
    public Event( UUID id, String name, String description, GeoPoint location, List< UUID > geocodeIDs, LocalDate beginDate,
                  LocalDate endDate, List< Leaderboard > leaderboards, Boolean available, Map<String, String> properties ) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.geocodeIDs = geocodeIDs;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.leaderboards = leaderboards;
        this.available = available;
        this.properties = properties;
    }

    /**
     * Sets the id attribute to the specified value
     *
     * @param id the unique id to set the Event to
     *
     * @return the model after changing the id
     */
    @Valid
    public Event id( UUID id ) {

        this.id = id;
        return this;
    }

    /**
     * Gets the saved id attribute
     *
     * @return the stored id attribute
     */
    public UUID getId() {

        return id;
    }

    /**
     * Sets the id attribute to the specified value
     *
     * @param id the value the id should be set to
     */
    public void setId( UUID id ) {

        this.id = id;
    }

    /**
     * Sets the name attribute to the specified value
     *
     * @param name the unique name to set the Event to
     *
     * @return the model after changing the name
     */
    @Valid
    public Event name( String name ) {

        this.name = name;
        return this;
    }

    /**
     * Gets the saved name attribute
     *
     * @return the stored name attribute
     */
    public String getName() {

        return name;
    }

    /**
     * Sets the name attribute to the specified value
     *
     * @param name the value the name should be set to
     */
    public void setName( String name ) {

        this.name = name;
    }

    /**
     * Sets the description attribute to the specified value
     *
     * @param description the unique description to set the Event to
     *
     * @return the model after changing the description
     */
    @Valid
    public Event description( String description ) {

        this.description = description;
        return this;
    }

    /**
     * Gets the saved description attribute
     *
     * @return the stored description attribute
     */
    public String getDescription() {

        return description;
    }

    /**
     * Sets the description attribute to the specified value
     *
     * @param description the value the description should be set to
     */
    public void setDescription( String description ) {

        this.description = description;
    }

    /**
     * Sets the location attribute to the specified value
     *
     * @param location the unique location to set the Event to
     *
     * @return the model after changing the location
     */
    @Valid
    public Event location( GeoPoint location ) {

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
     * @param location the value the location should be set to
     */
    public void setLocation( GeoPoint location ) {

        this.location = location;
    }

    /**
     * Sets the geocodeIDs attribute to the specified value
     *
     * @param geocodeIDs the unique levels to set the Event to
     *
     * @return the model after changing the levels
     */
    @Valid
    public Event geocodeIDs( List< UUID > geocodeIDs ) {

        this.geocodeIDs = geocodeIDs;
        return this;
    }

    /**
     *  A single item to the geocodes list
     *
     * @param geocode the item to add to the geocodes list
     *
     * @return the model after appending to geocodeIDs
     */
    @Valid
    public Event addGeocode( UUID geocode ) {

        if ( this.geocodeIDs == null ) {

            this.geocodeIDs = new ArrayList<>();
        }

        this.geocodeIDs.add( geocode );
        return this;
    }

    /**
     * Gets the saved geocodeIDs attribute
     *
     * @return the stored geocodeIDs attribute
     */
    @Valid
    public List< UUID > getGeocodeIDs() {

        return geocodeIDs;
    }

    /**
     * Sets the geocodeIDs attribute to the specified value
     *
     * @param  geocodeIDs the value the levels should be set to
     */
    public void setGeocodeIDs( List< UUID > geocodeIDs ) {

        this.geocodeIDs = geocodeIDs;
    }

    /**
     * Sets the begin attribute to the specified value
     *
     * @param begin the unique begin to set the Event to
     *
     * @return the model after changing the begin
     */
    @Valid
    public Event begin( LocalDate begin ) {

        this.beginDate = begin;
        return this;
    }

    /**
     * Gets the saved begin attribute
     *
     * @return the stored begin attribute
     */
    public LocalDate getBeginDate() {

        return beginDate;
    }

    /**
     * Sets the begin attribute to the specified value
     *
     * @param begin the value the begin should be set to
     */
    public void setBeginDate( LocalDate begin ) {

        this.beginDate = begin;
    }

    /**
     * Sets the end attribute to the specified value
     *
     * @param end the unique end to set the Event to
     *
     * @return the model after changing the end
     */
    @Valid
    public Event end( LocalDate end ) {

        this.endDate = end;
        return this;
    }

    /**
     * Gets the saved end attribute
     *
     * @return the stored end attribute
     */
    public LocalDate getEndDate() {

        return endDate;
    }

    /**
     * Sets the end attribute to the specified value
     *
     * @param end the value the end should be set to
     */
    public void setEndDate( LocalDate end ) {

        this.endDate = end;
    }

    /**
     * Sets the leaderboards attribute to the specified value
     *
     * @param leaderboards the unique leaderboards to set the Event to
     *
     * @return the model after changing the leaderboards
     */
    @Valid
    public Event leaderboards( List< Leaderboard > leaderboards ) {

        this.leaderboards = leaderboards;
        return this;
    }

    /**
     *  A single item to the leaderboards list
     *
     * @param leaderboardsItem the item to add to the leaderboards list
     *
     * @return the model after appending to leaderboards
     */
    @Valid
    public Event addLeaderboardsItem( Leaderboard leaderboardsItem ) {

        this.leaderboards.add( leaderboardsItem );
        return this;
    }

    /**
     * Gets the saved leaderboards attribute
     *
     * @return the stored leaderboards attribute
     */
    @Valid
    public List< Leaderboard > getLeaderboards() {

        return leaderboards;
    }

    /**
     * Sets the leaderboards attribute to the specified value
     *
     * @param leaderboards the value the leaderboards should be set to
     */
    public void setLeaderboards( List< Leaderboard > leaderboards ) {

        this.leaderboards = leaderboards;
    }

    /**
     * Sets the available attribute to the specified value
     *
     * @param available the value the attribute should be set to
     *
     * @return the model after the available has been changed
     */
    @Valid
    public Event available( Boolean available ) {

        this.available = available;
        return this;
    }

    /**
     * Gets the saved available attribute
     *
     * @return the stored available attribute
     */
    public Boolean isAvailable() {

        return available;
    }

    /**
     * Sets the available attribute to the specified value
     *
     * @param available the value the attribute should be set to
     */
    public void setAvailable( Boolean available ) {

        this.available = available;
    }

    /**
     * Sets the properties attribute to the specified value
     *
     * @param properties the value the attribute should be set to
     *
     * @return the model after the properties has been changed
     */
    @Valid
    public Event properties( Map<String, String> properties ) {

        this.properties = properties;
        return this;
    }

    /**
     * Gets the saved properties attribute
     *
     * @return the stored properties attribute
     */
    public Map<String, String> getProperties() {

        return properties;
    }

    /**
     * Sets the type properties to the specified value
     *
     * @param properties map of event types
     */
    public void setProperties( Map<String, String> properties ) {

        this.properties = properties;
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

        Event event = ( Event ) obj;
        return Objects.equals( this.id, event.id ) &&
                Objects.equals( this.name, event.name ) &&
                Objects.equals( this.description, event.description ) &&
                Objects.equals( this.location, event.location ) &&
                Objects.equals( this.geocodeIDs, event.geocodeIDs ) &&
                Objects.equals( this.beginDate, event.beginDate ) &&
                Objects.equals( this.endDate, event.endDate ) &&
                Objects.equals( this.leaderboards, event.leaderboards ) &&
                Objects.equals( this.available, event.available ) &&
                Objects.equals( this.properties, event.properties );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( id, name, description, location, geocodeIDs, beginDate, endDate, leaderboards,available );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class Event {\n" +
                "    id: " + toIndentedString( id ) + "\n" +
                "    name: " + toIndentedString( name ) + "\n" +
                "    description: " + toIndentedString( description ) + "\n" +
                "    location: " + toIndentedString( location ) + "\n" +
                "    geocodeIDs: " + toIndentedString( geocodeIDs ) + "\n" +
                "    begin: " + toIndentedString( beginDate ) + "\n" +
                "    end: " + toIndentedString( endDate ) + "\n" +
                "    leaderboards: " + toIndentedString( leaderboards ) + "\n" +
                "    available: " + toIndentedString( available ) + "\n" +
                "}";
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    String toIndentedString( java.lang.Object o ) {

        if ( o == null ) {

            return "null";
        }

        return o.toString().replace( "\n", "\n    " );
    }

}
