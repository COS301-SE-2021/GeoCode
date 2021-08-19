package tech.geocodeapp.geocode.collectable.repository;

import tech.geocodeapp.geocode.collectable.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class implements the repository for the Collectable Class
 */
@Repository
public interface CollectableRepository extends JpaRepository<Collectable, java.util.UUID> {
    /**
     * Gets all Collectables of a provided CollectableType
     * @param type The CollectableType to search by
     * @return A List of Collectable objects found
     */
    List<Collectable> findCollectablesByType(CollectableType type);

    /**
     * Deletes all Collectables of a provided CollectableType
     * @param type The CollectableType to delete by
     * @return A long containing the number of deleted records
     */
    long deleteCollectablesByType(CollectableType type);
}
