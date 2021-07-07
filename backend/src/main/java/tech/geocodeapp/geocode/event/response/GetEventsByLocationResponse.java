package tech.geocodeapp.geocode.event.response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.event.model.Event;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetEventsByLocationResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-07T10:35:03.795Z[GMT]")


public class GetEventsByLocationResponse {

    @JsonProperty( "events" )
    @Valid
    private List< Event > events = new ArrayList< Event >();

    public GetEventsByLocationResponse events( List< Event > events ) {

        this.events = events;
        return this;
    }

    public GetEventsByLocationResponse addEventsItem( Event eventsItem ) {

        this.events.add( eventsItem );
        return this;
    }

    /**
     * Get events
     *
     * @return events
     **/
    @Schema( required = true, description = "" )
    @NotNull
    @Valid
    public List< Event > getEvents() {

        return events;
    }

    public void setEvents( List< Event > events ) {

        this.events = events;
    }


    @Override
    public boolean equals( java.lang.Object o ) {

        if ( this == o ) {

            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {

            return false;
        }
        GetEventsByLocationResponse getEventsByLocationResponse = ( GetEventsByLocationResponse ) o;
        return Objects.equals( this.events, getEventsByLocationResponse.events );
    }

    @Override
    public int hashCode() {

        return Objects.hash( events );
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append( "class GetEventsByLocationResponse {\n" );

        sb.append( "    events: " ).append( toIndentedString( events ) ).append( "\n" );
        sb.append( "}" );
        return sb.toString();
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
