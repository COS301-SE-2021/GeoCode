package tech.geocodeapp.geocode.leaderboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.model.Point;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This class implements the repository for Points
 */
public interface PointRepository extends JpaRepository<Point, UUID> {
    List<Point> findAllByLeaderboard(Leaderboard leaderboard);
}
