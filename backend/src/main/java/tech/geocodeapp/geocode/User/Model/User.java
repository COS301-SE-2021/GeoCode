package tech.geocodeapp.geocode.User.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name="UserTable" )
public class User {
    @Id
    private String username;

}
