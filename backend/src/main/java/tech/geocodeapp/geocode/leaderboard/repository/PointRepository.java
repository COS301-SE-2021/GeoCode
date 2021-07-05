package tech.geocodeapp.geocode.leaderboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.geocodeapp.geocode.leaderboard.model.Point;

import java.util.UUID;

/**
 * This class implements the repository for Points
 */
public interface PointRepository extends JpaRepository<Point, UUID> {

}
