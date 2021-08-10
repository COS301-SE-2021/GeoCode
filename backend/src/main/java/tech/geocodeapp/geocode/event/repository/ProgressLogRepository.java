package tech.geocodeapp.geocode.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.geocodeapp.geocode.event.model.ProgressLog;

import java.util.UUID;

/**
 * This class implements the repository for the Event Subsystem ProgressLog tables
 */
@Repository( "ProgressLogRepository" )
public interface ProgressLogRepository extends JpaRepository<ProgressLog, UUID> {

    @Query("SELECT p from ProgressLog p WHERE p.eventID = ?1 AND p.userID = ?2")
    ProgressLog findProgressLogByEventIDAndUserID(UUID eventID, UUID userID);

}
