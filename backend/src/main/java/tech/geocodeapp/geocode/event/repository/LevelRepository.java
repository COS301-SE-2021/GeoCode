package tech.geocodeapp.geocode.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.geocodeapp.geocode.event.model.Level;

import java.util.UUID;

/**
 * This class implements the repository for the Event Subsystem levels tables
 */
@Repository( "LevelRepository" )
public interface LevelRepository extends JpaRepository< Level, UUID> {

    //SELECT

    //UPDATE

    //DELETE
}
