package tech.geocodeapp.geocode.Notifications.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

/**
 * This class implements the repository for the Notifications Subsystem
 */
@Repository
public interface NotificationsRepository extends JpaRepository<String, UUID> {

    //SELECT

    //UPDATE

    //DELETE
}
