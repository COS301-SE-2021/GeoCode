package tech.geocodeapp.geocode.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.event.model.TimeTrial;

import java.util.List;
import java.util.UUID;

/**
 * This class implements the repository for the Event Subsystem
 */
@Repository( "EventRepository" )
public interface EventRepository< T extends Event > extends JpaRepository< T, UUID> {

    @Query( value =  "FROM TimeTrial")
    List< TimeTrial > findAllTimeTrials();
}
