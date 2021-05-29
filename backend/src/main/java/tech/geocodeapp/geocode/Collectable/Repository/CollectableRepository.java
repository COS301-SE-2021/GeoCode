package tech.geocodeapp.geocode.Collectable.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.geocodeapp.geocode.Collectable.Model.Collectable;

/*
    This class implements the repository for the Collectable Subsystem
 */
@Repository
public interface CollectableRepository extends JpaRepository<Collectable, String> {
}
