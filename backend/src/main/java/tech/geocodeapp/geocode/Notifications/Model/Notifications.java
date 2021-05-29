package tech.geocodeapp.geocode.Notifications.Model;

import javax.persistence.*;


@Entity
@Table( name="Notifications" )
public class Notifications {

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    public Notifications() {

    }

    public void setId( Long id ) {

        this.id = id;
    }

    @Id
    public Long getId() {

        return id;
    }

}
