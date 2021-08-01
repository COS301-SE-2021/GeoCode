package tech.geocodeapp.geocode.event.model;

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
@Table( name = "event" )
public class Event {

    /**
     * The unique identifier for the Event
     */
    @Id
    @JsonProperty( "id" )
    @NotNull( message = "Event's id cannot be null." )
    private UUID id;

    /**
     * What the Event is called
     */
    @JsonProperty( "name" )
    @NotEmpty( message = "Event's name cannot be empty." )
    private String name;

    /**
     * A brief description on what the Event is about
     */
    @JsonProperty( "description" )
    @NotEmpty( message = "Event's description cannot be empty." )
    private String description;

    /**
     * The starting location of the Event
     */
    @Embedded
    @NotNull( message = "Event's location cannot be null." )
    private GeoPoint location;

    /**
     * The different Levels with what GeoCode to find and which Users
     * are searching are on the different Levels
     */
    @Valid
    @ManyToMany
    @JsonProperty( "levels" )
    @NotNull( message = "Event's location cannot be null." )
    private List< Level > levels;

    /**
     * The starting Date of the Event
     */
    @JsonProperty( "beginDate" )
    private LocalDate beginDate;

    /**
     * The end Date of the Event
     */
    @JsonProperty( "end" )
    private LocalDate endDate;

    /**
     * The different rankings of the users partaking in the Event
     */
    @Valid
    @ManyToMany
    @JsonProperty( "leaderboard" )
    @NotNull( message = "Event's location cannot be null." )
    private List< Leaderboard > leaderboards;

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
     * @param levels The different Levels with what GeoCode to find and which Users are searching are on the different Levels
     * @param beginDate The starting Date of the Event
     * @param endDate The end Date of the Event
     * @param leaderboards The different rankings of the users partaking in the Event
     */
    public Event( UUID id, String name, String description, GeoPoint location, List< Level > levels,
                  LocalDate beginDate, LocalDate endDate, List< Leaderboard > leaderboards ) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.levels = levels;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.leaderboards = leaderboards;
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
     * Sets the levels attribute to the specified value
     *
     * @param levels the unique levels to set the Event to
     *
     * @return the model after changing the levels
     */
    @Valid
    public Event levels( List< Level > levels ) {

        this.levels = levels;
        return this;
    }

    /**
     *  A single item to the levels list
     *
     * @param levelsItem the item to add to the levels list
     *
     * @return the model after appending to levels
     */
    @Valid
    public Event addLevelsItem( Level levelsItem ) {

        if ( this.levels == null ) {

            this.levels = new ArrayList<>();
        }

        this.levels.add( levelsItem );
        return this;
    }

    /**
     * Gets the saved levels attribute
     *
     * @return the stored levels attribute
     */
    @Valid
    public List< Level > getLevels() {

        return levels;
    }

    /**
     * Sets the levels attribute to the specified value
     *
     * @param levels the value the levels should be set to
     */
    public void setLevels( List< Level > levels ) {

        this.levels = levels;
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
                Objects.equals( this.levels, event.levels ) &&
                Objects.equals( this.beginDate, event.beginDate ) &&
                Objects.equals( this.endDate, event.endDate ) &&
                Objects.equals( this.leaderboards, event.leaderboards );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( id, name, description, location, levels, beginDate, endDate, leaderboards );
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
                "    levels: " + toIndentedString( levels ) + "\n" +
                "    begin: " + toIndentedString( beginDate ) + "\n" +
                "    end: " + toIndentedString( endDate ) + "\n" +
                "    leaderboards: " + toIndentedString( leaderboards ) + "\n" +
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
