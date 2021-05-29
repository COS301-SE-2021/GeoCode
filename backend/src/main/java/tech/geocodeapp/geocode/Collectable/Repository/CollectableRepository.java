package tech.geocodeapp.geocode.Collectable.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.geocodeapp.geocode.Collectable.Model.Collectable;

public interface CollectableRepository extends JpaRepository<Collectable, String> {
}
