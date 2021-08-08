package tech.geocodeapp.geocode.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.geocodeapp.geocode.event.model.TimeLog;

import java.util.UUID;

/**
 * This class implements the repository for the Event Subsystem TimeLog tables
 */
@Repository( "TimeLogRepository" )
public interface TimeLogRepository extends JpaRepository< TimeLog, UUID> {

    //SELECT

    //UPDATE

    //DELETE
}
