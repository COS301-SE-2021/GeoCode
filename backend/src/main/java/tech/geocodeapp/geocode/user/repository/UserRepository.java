package tech.geocodeapp.geocode.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.user.model.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;


/**
 * This class implements the repository for the User Subsystem
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * Add GeoCode to the User's foundGeoCodes
     * @param userID UserID
     * @param geocodeID GeoCode to add
     */
    @Modifying
    @Query(value="INSERT INTO user_table_found_geocodes VALUES (:userID, :geocodeID)", nativeQuery = true)
    void addFoundGeoCode(@Param("userID") @NotNull @Valid UUID userID, @Param("geocodeID") @NotNull @Valid UUID geocodeID);

    /**
     * Add CollectableType to the User's foundCollectableTypes
     * @param userID UserID
     * @param collectableTypeID CollectableType to add
     */
    @Modifying
    @Query(value="INSERT INTO user_table_found_collectable_types VALUES (:userID, :collectableTypeID)", nativeQuery = true)
    void addFoundCollectableType(@Param("userID") @NotNull @Valid UUID userID, @Param("collectableTypeID") @NotNull @Valid UUID collectableTypeID);

    /**
     * Add GeoCode to the User's ownedGeoCodes
     * @param userID UserID
     * @param geocodeID GeoCode to add
     */
    @Modifying
    @Query(value="INSERT INTO user_table_owned_geocodes VALUES (:userID, :geocodeID)", nativeQuery = true)
    void addOwnedGeoCode(@Param("userID") @NotNull @Valid UUID userID, @Param("geocodeID") @NotNull @Valid UUID geocodeID);
}
