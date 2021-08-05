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
     * The unique identifier for the TimeTrial
     */
    @Id
    @JsonProperty( "id" )
    @NotNull( message = "TimeTrial's id cannot be null." )
    private UUID id;

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
     * Sets the id attribute to the specified value
     *
     * @param id the unique id to set the TimeTrial to
     *
     * @return the model after changing the id
     */
    @Valid
    public TimeTrial id( UUID id ) {

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

        return Objects.equals( this.timeLimit, ( ( TimeTrial ) obj ).timeLimit );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( timeLimit );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class TimeTrial {\n" +
                "    timeLimit: " + toIndentedString( timeLimit ) + "\n" +
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
