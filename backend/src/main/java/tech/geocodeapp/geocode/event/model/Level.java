package tech.geocodeapp.geocode.event.model;

import org.hibernate.annotations.Cascade;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

/**
 * The Level model involves the GeoCode to find and all the users
 * searching for it withing a specific Event
 */
@Entity
@Validated
@Table( name = "level" )
public class Level {

    /**
     * The unique identifier for the Level
     */
    @Id
    @JsonProperty( "id" )
    @NotNull( message = "Level's id cannot be null." )
    private UUID id;

    /**
     * The GeoCode to find on this Level of an Event
     */
    @JsonProperty( "target" )
    private UUID target;

    /**
     * A map of all the user IDs on this level of the Event
     */
    @Valid
    @JsonProperty( "onLevel" )
    @ElementCollection( fetch = FetchType.EAGER )
    @Cascade( org.hibernate.annotations.CascadeType.ALL )
    @NotNull( message = "Level's onLevel cannot be null." )
    private Collection<  UUID > onLevel;

    /**
     * Default constructor
     */
    public Level() {

    }

    /**
     * Overloaded Constructor
     *
     * @param target The GeoCode to find on this Level of an Event
     */
    public Level( UUID target ) {

        this.id = UUID.randomUUID();
        this.target = target;
    }

    /**
     * Overloaded Constructor
     *
     * @param id The unique identifier for the Level
     * @param target The GeoCode to find on this Level of an Event
     */
    public Level( UUID id, UUID target ) {

        this.id = id;
        this.target = target;
    }

    /**
     * Overloaded Constructor
     *
     * @param id The unique identifier for the Level
     * @param target The GeoCode to find on this Level of an Event
     * @param onLevel A map of all the user IDs on this level of the Event
     */
    public Level( UUID id, UUID target, Collection< UUID > onLevel ) {

        this.id = id;
        this.target = target;
        this.onLevel = onLevel;
    }

    /**
     * Sets the id attribute to the specified value
     *
     * @param id the unique id to set the Level to
     *
     * @return the model after changing the id
     */
    @Valid
    public Level id( UUID id ) {

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
     * Sets the target attribute to the specified value
     *
     * @param target the unique target to set the Level to
     *
     * @return the model after changing the target
     */
    public Level target( UUID target ) {

        this.target = target;
        return this;
    }

    /**
     * Gets the saved target attribute
     *
     * @return the stored target attribute
     */
    public UUID getTarget() {

        return target;
    }

    /**
     * Sets the target attribute to the specified value
     *
     * @param target the value the target should be set to
     */
    public void setTarget( UUID target ) {

        this.target = target;
    }

    /**
     * Sets the onLevel attribute to the specified value
     *
     * @param onLevel the unique id to set the GeoCode to
     *
     * @return the model after changing the onLevel
     */
    public Level onLevel( Collection< UUID > onLevel ) {

        this.onLevel = onLevel;
        return this;
    }

    /**
     * Adds a new entry into the onLevel list
     *
     * @param onLevelItem the uuid to add to the list
     *
     * @return the model after adding a new entry
     */
    public Level putOnLevelItem( UUID onLevelItem ) {

        this.onLevel.add( onLevelItem );
        return this;
    }

    /**
     * Removes a new entry into the onLevel list
     *
     * @param onLevelItem the uuid to add to the HashMap
     *
     * @return the model after adding a new entry
     */
    public Level removeOnLevelItem( UUID onLevelItem ) {

        this.onLevel.remove( onLevelItem );
        return this;
    }

    /**
     * Gets the saved onLevel attribute
     *
     * @return the stored onLevel attribute
     */
    @Valid
    public Collection< UUID > getOnLevel() {

        return onLevel;
    }

    /**
     * Sets the onLevel attribute to the specified value
     *
     * @param onLevel the value the onLevel should be set to
     */
    public void setOnLevel( Collection< UUID > onLevel ) {

        this.onLevel = onLevel;
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

        var level = ( Level ) obj;
        return Objects.equals( this.target, level.target ) &&
                Objects.equals( this.onLevel, level.onLevel );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( target, onLevel );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class Level {\n" +
                "    target: " + toIndentedString( target ) + "\n" +
                "    onLevel: " + toIndentedString( onLevel ) + "\n" +
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
