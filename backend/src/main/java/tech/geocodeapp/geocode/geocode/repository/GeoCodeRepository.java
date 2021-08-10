package tech.geocodeapp.geocode.geocode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.geocodeapp.geocode.geocode.model.Difficulty;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;

import java.util.Collection;
import java.util.UUID;

/**
 * This class implements the repository for the GeoCode Subsystem
 */
@Repository( "GeoCodeRepository" )
public interface GeoCodeRepository extends JpaRepository< GeoCode, UUID> {

    /**
     * Finds all of the GeoCode's with a certain level of difficulty
     *
     * @param difficulty the level of difficulty assigned to a GeoCode
     *
     * @return all the GeoCode's with the specified difficulty
     */
    @Query( value = "SELECT r FROM GeoCode r WHERE r.difficulty = :difficulty" )
    Collection< GeoCode > findGeoCodeWithDifficulty( @Param( "difficulty" ) Difficulty difficulty );

    /**
     * Find the GeoCode with the given qrCode characters
     *
     * @param code the unique characters allocated to a certain GeoCode
     *
     * @return the GeoCode with the specified qrCode
     */
    @Query( value = "SELECT r FROM GeoCode r WHERE r.qrCode = :code" )
    GeoCode findGeoCodeWithQRCode( @Param( "code" ) String code );

    /**
     * Find the GeoCode with the given location (longitude and latitude co-ordinates)
     *
     * @param location the longitude and Latitude to look for the GeoCode
     *
     * @return the GeoCode at the given location
     */
    @Query( value = "SELECT r FROM GeoCode r WHERE r.location = :location" )
    GeoCode findGeoCodeAtLocation( @Param( "location" ) GeoPoint location );
}
