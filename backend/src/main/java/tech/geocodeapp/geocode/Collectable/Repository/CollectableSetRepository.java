package tech.geocodeapp.geocode.Collectable.Repository;

import io.swagger.model.CollectableSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * This class implements the repository for the CollectableSet Class
 */
@Repository
public interface CollectableSetRepository extends JpaRepository<CollectableSet, UUID> {
}
