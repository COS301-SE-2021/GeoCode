package tech.geocodeapp.geocode.event.model;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

/**
 * The TimeTrial is a special type of Event where the user has to complete
 * the Event in a certain amount of time
 */
@Entity
@Validated
@Table( name = "timetrial" )
public class TimeTrial extends Event {

    /**
     * The amount of time a user has to complete a special Event
     */
    @JsonProperty( "timeLimit" )
    @NotNull( message = "TimeTrial's timeLimit cannot be null." )
    private Double timeLimit;

    /**
     * Default constructor
     */
    public TimeTrial() {

    }

    /**
     * Overloaded Constructor
     *
     * @param id The unique identifier for the TimeTrial
     * @param timeLimit The amount of time a user has to complete a special Event
     */
    public TimeTrial( UUID id, Double timeLimit ) {

        this.id = id;
        this.timeLimit = timeLimit;
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
    public Double getTimeLimit() {

        return timeLimit;
    }

    /**
     * Sets the timeLimit attribute to the specified value
     *
     * @param timeLimit the value the timeLimit should be set to
     */
    public void setTimeLimit( Double timeLimit ) {

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
