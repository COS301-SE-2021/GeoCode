package tech.geocodeapp.geocode.collectable.repository;

import tech.geocodeapp.geocode.collectable.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class implements the repository for the CollectableType Class
 */
@Repository
public interface CollectableTypeRepository extends JpaRepository<CollectableType, java.util.UUID> {
    /**
     *  Gets all CollectableType objects of a given rarity
     * @param rarity the Rarity enum value to search by
     * @return A List of all found CollectableType objects
     */
    List<CollectableType> getCollectableTypesByRarity(Rarity rarity);

    /**
     * Gets all CollectableTypes that are part of the provided CollectableSet
     * @param set the CollectableSet to search by
     * @return a List of CollectableType objects found
     */
    List<CollectableType> getCollectableTypesBySet(CollectableSet set);

    /**
     * This method deletes all CollectableTypes of a provided CollectableSet
     * @param set the CollectableSet to delete by
     * @return returns a long containing the number of records deleted
     */
    long deleteCollectableTypesBySet(CollectableSet set);
}
