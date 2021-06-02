package tech.geocodeapp.geocode.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.geocodeapp.geocode.User.Model.User;

import java.util.UUID;

/**
 * This class implements the repository for the User Subsystem
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    //SELECT

    //UPDATE

    //DELETE
}
