package tech.geocodeapp.geocode.Leaderboard.Model;

import javax.persistence.*;

@Entity
@Table( name="Leaderboard" )
public class Leaderboard {

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    public Leaderboard() {

    }

    public void setId( Long id ) {

        this.id = id;
    }

    @Id
    public Long getId() {

        return id;
    }

}
