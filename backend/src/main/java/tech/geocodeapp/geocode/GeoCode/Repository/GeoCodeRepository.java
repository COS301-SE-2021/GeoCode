package tech.geocodeapp.geocode.GeoCode.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.geocodeapp.geocode.GeoCode.Model.GeoCode;


import java.util.UUID;

/**
 * This class implements the repository for the User Subsystem
 */
@Repository
public interface GeoCodeRepository extends JpaRepository<GeoCode, Long> {

    //SELECT

    //UPDATE

    //DELETE
}
