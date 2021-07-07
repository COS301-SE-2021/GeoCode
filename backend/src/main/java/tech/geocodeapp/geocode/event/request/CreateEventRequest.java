package tech.geocodeapp.geocode.event.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateEventRequest
 */
@Validated
public class CreateEventRequest {

    @JsonProperty( "id" )
    private UUID id = null;

    @JsonProperty( "name" )
    private String name = null;

    @JsonProperty( "description" )
    private String description = null;

    @JsonProperty( "location" )
    private GeoPoint location = null;

    @JsonProperty( "leaderboard" )
    private Leaderboard leaderboard = null;

    public CreateEventRequest id( UUID id ) {

        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @Schema( required = true, description = "" )
    @NotNull

    @Valid
    public UUID getId() {

        return id;
    }

    public void setId( UUID id ) {

        this.id = id;
    }

    public CreateEventRequest name( String name ) {

        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/
    @Schema( required = true, description = "" )
    @NotNull

    public String getName() {

        return name;
    }

    public void setName( String name ) {

        this.name = name;
    }

    public CreateEventRequest description( String description ) {

        this.description = description;
        return this;
    }

    /**
     * Get description
     *
     * @return description
     **/
    @Schema( required = true, description = "" )
    @NotNull

    public String getDescription() {

        return description;
    }

    public void setDescription( String description ) {

        this.description = description;
    }

    public CreateEventRequest location( GeoPoint location ) {

        this.location = location;
        return this;
    }

    /**
     * Get location
     *
     * @return location
     **/
    @Schema( required = true, description = "" )
    @NotNull

    @Valid
    public GeoPoint getLocation() {

        return location;
    }

    public void setLocation( GeoPoint location ) {

        this.location = location;
    }

    public CreateEventRequest leaderboard( Leaderboard leaderboard ) {

        this.leaderboard = leaderboard;
        return this;
    }

    /**
     * Get leaderboard
     *
     * @return leaderboard
     **/
    @Schema( required = true, description = "" )
    @NotNull

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
        CreateEventRequest createEventRequest = ( CreateEventRequest ) o;
        return Objects.equals( this.id, createEventRequest.id ) &&
                Objects.equals( this.name, createEventRequest.name ) &&
                Objects.equals( this.description, createEventRequest.description ) &&
                Objects.equals( this.location, createEventRequest.location ) &&
                Objects.equals( this.leaderboard, createEventRequest.leaderboard );
    }

    @Override
    public int hashCode() {

        return Objects.hash( id, name, description, location, leaderboard );
    }

    @Override
    public String toString() {

        return "class CreateEventRequest {\n" +
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
