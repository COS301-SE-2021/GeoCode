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
    //SELECT
    List<Collectable> findCollectablesByType(CollectableType type);
    //UPDATE

    //DELETE
}
