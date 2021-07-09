package tech.geocodeapp.geocode.event.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetEventsByLocationRequest
 */
@Validated
public class GetEventsByLocationRequest {

    @JsonProperty( "location" )
    private GeoPoint location = null;

    public GetEventsByLocationRequest location( GeoPoint location ) {

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


    @Override
    public boolean equals( java.lang.Object o ) {

        if ( this == o ) {

            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {

            return false;
        }
        GetEventsByLocationRequest getEventsByLocationRequest = ( GetEventsByLocationRequest ) o;
        return Objects.equals( this.location, getEventsByLocationRequest.location );
    }

    @Override
    public int hashCode() {

        return Objects.hash( location );
    }

    @Override
    public String toString() {

        return "class GetEventsByLocationRequest {\n" +
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