package tech.geocodeapp.geocode.leaderboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

import java.util.UUID;

/**
 * This class implements the repository for the Leaderboard Subsystem
 */
@Repository
public interface LeaderboardRepository extends JpaRepository< Leaderboard, UUID> {

}
