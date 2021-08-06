package tech.geocodeapp.geocode.event.model;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * The TimeTrial is a special type of Event where the user has to complete
 * the Event in a certain amount of time
 */
@Entity
@Validated
@DiscriminatorValue( "1" )
public class TimeTrial extends Event {

    /**
     * The amount of time a user has to complete a special Event
     */
    @JsonProperty( "timeLimit" )
    private double timeLimit;

    /**
     * Default constructor
     */
    public TimeTrial() {

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
     * @param timeLimit The amount of time a user has to complete a special Event
     */
    public TimeTrial( UUID id, String name, String description, GeoPoint location, List< Level > levels,
                  LocalDate beginDate, LocalDate endDate, List< Leaderboard > leaderboards, double timeLimit ) {

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
     * Sets the timeLimit attribute to the specified value
     *
     * @param timeLimit the unique timeLimit to set the TimeTrial to
     *
     * @return the model after changing the timeLimit
     */
    public TimeTrial timeLimit( Double timeLimit ) {

        this.timeLimit = timeLimit;
        return this;
    }

    /**
     * Gets the saved timeLimit attribute
     *
     * @return the stored timeLimit attribute
     */
    public double getTimeLimit() {

        return timeLimit;
    }

    /**
     * Sets the timeLimit attribute to the specified value
     *
     * @param timeLimit the value the timeLimit should be set to
     */
    public void setTimeLimit( double timeLimit ) {

        this.timeLimit = timeLimit;
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

        TimeTrial timeTrial = ( TimeTrial ) obj;
        return Objects.equals( this.id, timeTrial.id ) &&
                Objects.equals( this.name, timeTrial.name ) &&
                Objects.equals( this.description, timeTrial.description ) &&
                Objects.equals( this.location, timeTrial.location ) &&
                Objects.equals( this.levels, timeTrial.levels ) &&
                Objects.equals( this.beginDate, timeTrial.beginDate ) &&
                Objects.equals( this.endDate, timeTrial.endDate ) &&
                Objects.equals( this.leaderboards, timeTrial.leaderboards ) &&
                Objects.equals( this.timeLimit, timeTrial.timeLimit );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( id, name, description, location, levels, beginDate, endDate, leaderboards, timeLimit );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class TimeTrial {\n" +
                "    id: " + toIndentedString( id ) + "\n" +
                "    name: " + toIndentedString( name ) + "\n" +
                "    description: " + toIndentedString( description ) + "\n" +
                "    location: " + toIndentedString( location ) + "\n" +
                "    levels: " + toIndentedString( levels ) + "\n" +
                "    begin: " + toIndentedString( beginDate ) + "\n" +
                "    end: " + toIndentedString( endDate ) + "\n" +
                "    leaderboards: " + toIndentedString( leaderboards ) + "\n" +
                "    timeLimit: " + toIndentedString( timeLimit ) + "\n" +
                "}";
    }

}
