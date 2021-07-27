package tech.geocodeapp.geocode.event.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

import java.util.UUID;
import java.util.Objects;

/**
 * Event
 */
@Entity
@Validated
@Table( name = "event" )
public class Event {

    @Id
    @JsonProperty( "id" )
    private UUID id;

    @JsonProperty( "name" )
    private String name;

    @JsonProperty( "description" )
    private String description;

    //@OneToOne
    @Embedded
    //@JsonProperty( "location" )
    //@ElementCollection( fetch = FetchType.LAZY )
    private GeoPoint location;

    @ManyToOne
    @JsonProperty( "leaderboard" )
    private Leaderboard leaderboard;

    public Event id( UUID id ) {

        this.id = id;
        return this;
    }

    /**
     * Default constructor
     */
    public Event() {

    }

    /**
     *
     *
     * @param id
     * @param name
     * @param description
     * @param location
     * @param leaderboard
     */
    public Event( UUID id, String name, String description, GeoPoint location, Leaderboard leaderboard ) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.leaderboard = leaderboard;
    }

    @Valid
    public UUID getId() {

        return id;
    }

    public void setId( UUID id ) {

        this.id = id;
    }

    public Event name( String name ) {

        this.name = name;
        return this;
    }


    public String getName() {

        return name;
    }

    public void setName( String name ) {

        this.name = name;
    }

    public Event description( String description ) {

        this.description = description;
        return this;
    }


    public String getDescription() {

        return description;
    }

    public void setDescription( String description ) {

        this.description = description;
    }

    public Event location( GeoPoint location ) {

        this.location = location;
        return this;
    }


    @Valid
    public GeoPoint getLocation() {

        return location;
    }

    public void setLocation( GeoPoint location ) {

        this.location = location;
    }

    public Event leaderboard( Leaderboard leaderboard ) {

        this.leaderboard = leaderboard;
        return this;
    }

    @Valid
    public Leaderboard getLeaderboard() {

        return leaderboard;
    }

    public void setLeaderboard( Leaderboard leaderboard ) {

        this.leaderboard = leaderboard;
    }


    @Override
    public boolean equals( java.lang.Object o ) {

        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Event event = ( Event ) o;
        return Objects.equals( this.id, event.id ) &&
                Objects.equals( this.name, event.name ) &&
                Objects.equals( this.description, event.description ) &&
                Objects.equals( this.location, event.location ) &&
                Objects.equals( this.leaderboard, event.leaderboard );
    }

    @Override
    public int hashCode() {

        return Objects.hash( id, name, description, location, leaderboard );
    }

    @Override
    public String toString() {

        return "class Event {\n" +
                "    id: " + toIndentedString( id ) + "\n" +
                "    name: " + toIndentedString( name ) + "\n" +
                "    description: " + toIndentedString( description ) + "\n" +
                "    location: " + toIndentedString( location ) + "\n" +
                "    leaderboard: " + toIndentedString( leaderboard ) + "\n" +
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
