package tech.geocodeapp.geocode.Trackable.Model;

import javax.persistence.*;

@Entity
@Table( name="Trackable" )
public class Trackable {

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    public Trackable() {

    }

    public void setId( Long id ) {

        this.id = id;
    }

    @Id
    public Long getId() {

        return id;
    }

}
