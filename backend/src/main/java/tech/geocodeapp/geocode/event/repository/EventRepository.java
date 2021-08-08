package tech.geocodeapp.geocode.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.geocodeapp.geocode.event.model.Event;

import java.util.UUID;

/**
 * This class implements the repository for the Event Subsystem
 */
@Repository( "EventRepository" )
public interface EventRepository< T extends Event > extends JpaRepository< T, UUID> {

    //SELECT

    //UPDATE

    //DELETE
}
