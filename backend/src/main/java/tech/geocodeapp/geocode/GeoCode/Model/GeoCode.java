package tech.geocodeapp.geocode.GeoCode.Model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table( name="GeoCode" )
public class GeoCode {

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    public GeoCode() {

    }

    public void setId( Long id ) {

        this.id = id;
    }

    @Id
    public Long getId() {

        return id;
    }

}
