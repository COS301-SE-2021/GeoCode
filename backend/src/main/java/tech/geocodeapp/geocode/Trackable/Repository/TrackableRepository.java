package tech.geocodeapp.geocode.Trackable.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

/**
 * This class implements the repository for the Trackable Subsystem
 */
@Repository
public interface TrackableRepository extends JpaRepository<String, UUID> {

    //SELECT

    //UPDATE

    //DELETE
}
