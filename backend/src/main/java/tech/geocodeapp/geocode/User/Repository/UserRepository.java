package tech.geocodeapp.geocode.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.geocodeapp.geocode.User.Model.User;

/**
 * This class implements the repository for the User Subsystem
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    //SELECT

    //UPDATE

    //DELETE
}
