package tech.geocodeapp.geocode.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.geocodeapp.geocode.mission.model.Mission;

import java.util.UUID;

@Repository
public interface MissionRepository extends JpaRepository<Mission, UUID> {

}
