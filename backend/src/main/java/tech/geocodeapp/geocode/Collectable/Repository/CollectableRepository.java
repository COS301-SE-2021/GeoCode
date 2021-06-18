package tech.geocodeapp.geocode.Collectable.Repository;

import io.swagger.model.Collectable;
import io.swagger.model.CollectableType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * This class implements the repository for the Collectable Class
 */
@Repository
public interface CollectableRepository extends JpaRepository<Collectable, UUID> {
    <S extends Collectable> List<S> saveAllAndFlush(Iterable<S> iterable);

    void deleteAllInBatch(Iterable<Collectable> iterable);

    void deleteAllByIdInBatch(Iterable<UUID> iterable);

    Collectable getById(UUID uuid);

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
