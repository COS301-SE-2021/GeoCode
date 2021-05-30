package tech.geocodeapp.geocode.GeoCode.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

/**
 * This class implements the repository for the GeoCode Subsystem
 */
@Repository
public interface GeoCodeRepository extends JpaRepository<String, UUID> {

    //SELECT

    //UPDATE

    //DELETE
}
