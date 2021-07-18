package tech.geocodeapp.geocode.event.model;

import org.springframework.validation.annotation.Validated;
import javax.persistence.*;

/**
 * The GeoCode model that will be stored as a table in the db
 */
@Validated
@javax.annotation.Generated( value = "io.swagger.codegen.v3.generators.java.SpringCodegen" )

@Entity
@Table( name = "Event" )
public class Event {


    private Long id;

    public void setId( Long id ) {

        this.id = id;
    }

    @Id
    public Long getId() {

        return id;
    }

}
