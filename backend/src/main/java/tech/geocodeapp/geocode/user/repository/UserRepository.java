package tech.geocodeapp.geocode.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.geocodeapp.geocode.user.model.User;

import java.util.UUID;


/**
 * This class implements the repository for the User Subsystem
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsernameIgnoreCase(String username);

}
