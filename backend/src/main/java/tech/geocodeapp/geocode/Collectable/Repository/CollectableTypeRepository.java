package tech.geocodeapp.geocode.Collectable.Repository;

import io.swagger.model.CollectableType;
import io.swagger.model.Rarity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * This class implements the repository for the CollectableType Class
 */
@Repository
public interface CollectableTypeRepository extends JpaRepository<CollectableType, UUID> {
    List<CollectableType> getCollectableTypesByRarity(Rarity rarity);
}
