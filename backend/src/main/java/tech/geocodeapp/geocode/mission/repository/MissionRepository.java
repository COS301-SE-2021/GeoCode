package tech.geocodeapp.geocode.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.geocodeapp.geocode.mission.model.Mission;

import java.util.UUID;

public interface MissionRepository extends JpaRepository<Mission, UUID> {

}
