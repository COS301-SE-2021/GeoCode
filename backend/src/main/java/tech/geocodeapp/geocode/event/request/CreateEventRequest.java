package tech.geocodeapp.geocode.event.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.event.model.OrderLevels;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.geocode.request.CreateGeoCodeRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * CreateEventRequest object that is used to specify the attributes for a new Event
 */
@Validated
public class CreateEventRequest {

    /**
     * The name of the Event to be created
     */
    @JsonProperty( "name" )
    @NotEmpty( message = "CreateEventRequest name attribute cannot be null." )
    private String name;

    /**
     * The description of what the Event to be created is
     */
    @JsonProperty( "description" )
    @NotEmpty( message = "CreateEventRequest description attribute cannot be null." )
    private String description;

    /**
     * The location where the Event will be held at in the real world
     */
    @JsonProperty( "location" )
    @NotNull( message = "CreateEventRequest location attribute cannot be null." )
    private GeoPoint location;

    /**
     * The starting Date of the Event
     */
    @JsonProperty( "beginDate" )
    @NotNull( message = "CreateEventRequest beginDate attribute cannot be null." )
    private LocalDate beginDate;

    /**
     * The end Date of the Event
     */
    @JsonProperty( "endDate" )
    @NotNull( message = "CreateEventRequest endDate attribute cannot be null." )
    private LocalDate endDate;

    /**
     * The different GeoCodes to find during the Event
     */
    @JsonProperty( "geoCodesToFind" )
    private List< CreateGeoCodeRequest > createGeoCodesToFind;

    /**
     * The order in which the Users will complete the different Levels
     */
    @JsonProperty( "orderBy" )
    private OrderLevels orderBy;

    /**
     * If the Event is active in the system for a user to participate
     */
    @JsonProperty( "available" )
    private Boolean available;

    /**
     * Properties of the Event
     */
    @JsonProperty( "properties" )
    private Map<String, String> properties;

    /**
     * Default constructor
     */
    public CreateEventRequest() {

    }

    /**
     * Overloaded Constructor
     *
     * @param name what the new Event should be called
     * @param description what the Event to be created is going to be about
     * @param location the Event will be held at in the real world's location
     * @param beginDate The starting Date of the Event
     * @param endDate The end Date of the Event
     * @param createGeoCodesToFind The different GeoCodes to find during the Event
     * @param orderBy The order in which the Users will complete the different Levels
     * @param properties Properties of the Event
     */
    public CreateEventRequest(String name, String description, GeoPoint location,
                              LocalDate beginDate, LocalDate endDate,
                              List< CreateGeoCodeRequest > createGeoCodesToFind, OrderLevels orderBy, Map<String, String> properties ) {

        this.name = name;
        this.description = description;
        this.location = location;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.createGeoCodesToFind = createGeoCodesToFind;
        this.orderBy = orderBy;
        this.properties = properties;
    }

    /**
     * Overloaded Constructor
     *
     * @param name what the new Event should be called
     * @param description what the Event to be created is going to be about
     * @param location the Event will be held at in the real world's location
     * @param beginDate The starting Date of the Event
     * @param endDate The end Date of the Event
     * @param createGeoCodesToFind The different GeoCodes to find during the Event
     * @param orderBy The order in which the Users will complete the different Levels
     * @param available If the Event is active in the system for a user to participate
     * @param properties Properties of the Event
     */
    public CreateEventRequest( String name, String description, GeoPoint location,
                               LocalDate beginDate, LocalDate endDate, List< CreateGeoCodeRequest > createGeoCodesToFind,
                               OrderLevels orderBy, Boolean available, Map<String, String> properties ) {

        this.name = name;
        this.description = description;
        this.location = location;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.createGeoCodesToFind = createGeoCodesToFind;
        this.orderBy = orderBy;
        this.available = available;
        this.properties = properties;
    }

    /**
     * Sets the name attribute to the specified value
     *
     * @param name the value the attribute should be set to
     *
     * @return the request after the difficulty has been changed
     */
    public CreateEventRequest name( String name ) {

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
     * @param name the value the attribute should be set to
     */
    public void setName( String name ) {

        this.name = name;
    }

    /**
     * Sets the description attribute to the specified value
     *
     * @param description the value the attribute should be set to
     *
     * @return the request after the description has been changed
     */
    public CreateEventRequest description( String description ) {

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
     * @param description the value the attribute should be set to
     */
    public void setDescription( String description ) {

        this.description = description;
    }

    /**
     * Sets the location attribute to the specified value
     *
     * @param location the value the attribute should be set to
     *
     * @return the request after the location has been changed
     */
    public CreateEventRequest location( GeoPoint location ) {

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
     * @param location the value the attribute should be set to
     */
    public void setLocation( GeoPoint location ) {

        this.location = location;
    }

    /**
     * Sets the beginDate attribute to the specified value
     *
     * @param beginDate the value the attribute should be set to
     *
     * @return the request after the beginDate has been changed
     */
    public CreateEventRequest beginDate( LocalDate beginDate ) {

        this.beginDate = beginDate;
        return this;
    }

    /**
     * Gets the saved beginDate attribute
     *
     * @return the stored beginDate attribute
     */
    public LocalDate getBeginDate() {

        return beginDate;
    }

    /**
     * Sets the beginDate attribute to the specified value
     *
     * @param beginDate the value the attribute should be set to
     */
    public void setBeginDate( LocalDate beginDate ) {

        this.beginDate = beginDate;
    }

    /**
     * Sets the endDate attribute to the specified value
     *
     * @param endDate the value the attribute should be set to
     *
     * @return the request after the endDate has been changed
     */
    public CreateEventRequest endDate( LocalDate endDate ) {

        this.endDate = endDate;
        return this;
    }

    /**
     * Gets the saved endDate attribute
     *
     * @return the stored endDate attribute
     */
    public LocalDate getEndDate() {

        return endDate;
    }

    /**
     * Sets the endDate attribute to the specified value
     *
     * @param endDate the value the attribute should be set to
     */
    public void setEndDate( LocalDate endDate ) {

        this.endDate = endDate;
    }

    /**
     * Sets the geoCodesToFind attribute to the specified value
     *
     * @param geoCodesToFind the value the attribute should be set to
     *
     * @return the request after the geoCodesToFind has been changed
     */
    public CreateEventRequest geoCodesToFind( List< CreateGeoCodeRequest > geoCodesToFind ) {

        this.createGeoCodesToFind = geoCodesToFind;
        return this;
    }

    /**
     * Adds a single entry into the geoCodesToFind list
     *
     * @param createGeoCodesToFindItem the entry to insert into the geoCodesToFind list
     *
     * @return the request after the geoCodesToFind has been changed
     */
    public CreateEventRequest addGeoCodesToFindItem( CreateGeoCodeRequest createGeoCodesToFindItem ) {

        this.createGeoCodesToFind.add( createGeoCodesToFindItem );
        return this;
    }

    /**
     * Gets the saved geoCodesToFind attribute
     *
     * @return the stored geoCodesToFind attribute
     */
    public List< CreateGeoCodeRequest > getCreateGeoCodesToFind() {

        return createGeoCodesToFind;
    }

    /**
     * Sets the geoCodesToFind attribute to the specified value
     *
     * @param createGeoCodesToFind the value the attribute should be set to
     */
    public void setCreateGeoCodesToFind(List<CreateGeoCodeRequest> createGeoCodesToFind) {

        this.createGeoCodesToFind = createGeoCodesToFind;
    }

    /**
     * Sets the orderBy attribute to the specified value
     *
     * @param orderBy the value the attribute should be set to
     *
     * @return the request after the orderBy has been changed
     */
    public CreateEventRequest orderBy( OrderLevels orderBy ) {

        this.orderBy = orderBy;
        return this;
    }

    /**
     * Gets the saved orderBy attribute
     *
     * @return the stored orderBy attribute
     */
    @Valid
    public OrderLevels getOrderBy() {

        return orderBy;
    }

    /**
     * Sets the orderBy attribute to the specified value
     *
     * @param orderBy the value the attribute should be set to
     */
    public void setOrderBy( OrderLevels orderBy ) {

        this.orderBy = orderBy;
    }

    /**
     * Sets the available attribute to the specified value
     *
     * @param available the value the attribute should be set to
     *
     * @return the model after the available has been changed
     */
    @Valid
    public CreateEventRequest available( Boolean available ) {

        this.available = available;
        return this;
    }

    /**
     * Gets the saved available attribute
     *
     * @return the stored available attribute
     */
    public Boolean isAvailable() {

        return available;
    }

    /**
     * Sets the available attribute to the specified value
     *
     * @param available the value the attribute should be set to
     */
    public void setAvailable( Boolean available ) {

        this.available = available;
    }

    /**
     * Sets the properties attribute to the specified value
     *
     * @param properties the value the attribute should be set to
     *
     * @return the model after the properties has been changed
     */
    @Valid
    public CreateEventRequest properties( Map<String, String> properties ) {

        this.properties = properties;
        return this;
    }

    /**
     * Gets the saved properties attribute
     *
     * @return the stored properties attribute
     */
    public Map<String, String> getProperties() {

        return properties;
    }

    /**
     * Sets the properties attribute to the specified value
     *
     * @param properties the value the attribute should be set to
     */
    public void setProperties( Map<String, String> properties ) {

        this.properties = properties;
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

        var createEventRequest = ( CreateEventRequest ) obj;
        return Objects.equals( this.name, createEventRequest.name ) &&
                Objects.equals( this.description, createEventRequest.description ) &&
                Objects.equals( this.location, createEventRequest.location ) &&
                Objects.equals( this.beginDate, createEventRequest.beginDate ) &&
                Objects.equals( this.endDate, createEventRequest.endDate ) &&
                Objects.equals( this.createGeoCodesToFind, createEventRequest.createGeoCodesToFind) &&
                Objects.equals( this.orderBy, createEventRequest.orderBy ) &&
                Objects.equals( this.available, createEventRequest.available ) &&
                Objects.equals( this.properties, createEventRequest.properties );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( name, description, location, beginDate, endDate, createGeoCodesToFind, orderBy, available, properties );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class CreateEventRequest {\n" +
                "    name: " + toIndentedString( name ) + "\n" +
                "    description: " + toIndentedString( description ) + "\n" +
                "    location: " + toIndentedString( location ) + "\n" +
                "    beginDate: " + toIndentedString( beginDate ) + "\n" +
                "    endDate: " + toIndentedString( endDate ) + "\n" +
                "    geoCodesToFind: " + toIndentedString(createGeoCodesToFind) + "\n" +
                "    orderBy: " + toIndentedString( orderBy ) + "\n" +
                "    available: " + toIndentedString( available ) + "\n" +
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
