package tech.geocodeapp.geocode.trackable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.geocodeapp.geocode.trackable.model.Trackable;


import java.util.UUID;

/**
 * This class implements the repository for the Trackable Subsystem
 */
@Repository
public interface TrackableRepository extends JpaRepository<Trackable, Long> {

    //SELECT

    //UPDATE

    //DELETE
}
