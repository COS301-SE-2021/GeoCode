package tech.geocodeapp.geocode.event.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.general.response.Response;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * GetAllEventsResponse object returns all the Event objects that the user has entered
 */
@Validated
public class GetEnteredEventsResponse extends Response {

    /**
     * All the Events stored that the user has entered
     */
    @JsonProperty( "entries" )
    private List< Map<String, Object> > entries;

    /**
     * Overloaded Constructor
     *
     * @param entries the list of events to set the events attribute to
     */
    public GetEnteredEventsResponse(boolean success, String message, List<Map<String, Object>> entries ) {
        super(success, message);
        this.entries = entries;
    }

    /**
     * Sets the events attribute to the specified value
     *
     * @param entries the value the attribute should be set to
     *
     * @return the request after the events has been changed
     */
    public GetEnteredEventsResponse entries(List<Map<String, Object>> entries ) {

        this.entries = entries;
        return this;
    }

    /**
     * Gets the saved events attribute
     *
     * @return the stored events attribute
     */
    @Valid
    public List<Map<String, Object>> getEntries() {

        return entries;
    }

    /**
     * Sets the events attribute to the specified value
     *
     * @param entries the value the attribute should be set to
     */
    public void setEntries( @Valid List<Map<String, Object>> entries ) {

        this.entries = entries;
    }

    /**
     * Determines if the specified object is the same as the current object
     *
     * @param obj the object we want to compare with the specific attributes of this class
     *
     * @return if the object is the same or not
     */
    @Override
    public boolean equals( Object obj ) {

        if ( this == obj ) {

            return true;
        }
        if ( obj == null || getClass() != obj.getClass() ) {

            return false;
        }

        return Objects.equals( this.entries, ( (GetEnteredEventsResponse) obj ).entries );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( entries );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GetEnteredEventsResponse {\n" +
                "    entries: " + toIndentedString( entries ) + "\n" +
                "}";
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString( Object o ) {

        if ( o == null ) {

            return "null";
        }
        return o.toString().replace( "\n", "\n    " );
    }

}
