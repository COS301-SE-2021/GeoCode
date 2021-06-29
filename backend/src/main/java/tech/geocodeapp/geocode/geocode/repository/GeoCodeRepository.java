package tech.geocodeapp.geocode.geocode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.geocodeapp.geocode.geocode.model.GeoCode;

import java.util.UUID;

/**
 * This class implements the repository for the GeoCode Subsystem
 */
@Repository( "GeoCodeRepository" )
public interface GeoCodeRepository extends JpaRepository< GeoCode, UUID> {

    //SELECT

    //UPDATE

    //DELETE
}
