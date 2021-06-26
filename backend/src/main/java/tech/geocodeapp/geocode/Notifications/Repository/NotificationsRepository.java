package tech.geocodeapp.geocode.notifications.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.geocodeapp.geocode.Notifications.Model.Notifications;


import java.util.UUID;

/**
 * This class implements the repository for the Notifications Subsystem
 */
@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Long> {

    //SELECT

    //UPDATE

    //DELETE
}
