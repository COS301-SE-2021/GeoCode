package tech.geocodeapp.geocode.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.geocodeapp.geocode.event.model.UserEventStatus;

import java.util.Map;
import java.util.UUID;

/**
 * This class implements the repository for the Event Subsystem UserEventStatus tables
 */
@Repository( "UserEventStatusRepository" )
public interface UserEventStatusRepository extends JpaRepository<UserEventStatus, UUID> {

    @Query("SELECT p from UserEventStatus p WHERE p.eventID = ?1 AND p.userID = ?2")
    UserEventStatus findStatusByEventIDAndUserID(UUID eventID, UUID userID);


}
