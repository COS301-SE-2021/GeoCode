package tech.geocodeapp.geocode.leaderboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.model.MyLeaderboardDetails;
import tech.geocodeapp.geocode.leaderboard.model.Point;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This class implements the repository for Points
 */
@Repository
public interface PointRepository extends JpaRepository<Point, UUID> {
    /**
     * Returns all points on the given Leaderboard
     * @param leaderboardID UUID of the Leaderboard
     * @return The points on the Leaderboard
     */
    @Query(value = "SELECT * FROM point WHERE leaderboard_id = ?1", nativeQuery = true)
    List<Point> findAllByLeaderboardID(UUID leaderboardID);

    @Query(value = "SELECT Cast(event_id AS varchar) eventID, name, amount as points, rank FROM (" +
            "SELECT event_id, leaderboards.name, amount, DENSE_RANK() OVER (PARTITION BY leaderboards.name ORDER BY amount DESC) AS rank, user_id" +
            " FROM point JOIN leaderboards ON point.leaderboard_id = leaderboards.id LEFT JOIN event_leaderboards ON leaderboards.id = event_leaderboards.leaderboards_id" +
            " ORDER BY leaderboards.name" +
            ") AS all_ranks" +
            " WHERE user_id = ?1", nativeQuery = true)
    List<MyLeaderboardDetails> getMyLeaderboards(UUID userID);

    @Query(value = "SELECT DENSE_RANK() OVER (ORDER BY amount) AS rank FROM point WHERE leaderboard_id = ?1 AND amount = ?2 ORDER BY rank", nativeQuery = true)
    int getMyRank(UUID leaderboardID, int amount);

    int countByLeaderboard(Leaderboard leaderboard);

    @Query(value = "SELECT * FROM point WHERE leaderboard_id = ?1 ORDER BY amount DESC OFFSET ?2 ROWS FETCH NEXT ?3 ROWS ONLY ", nativeQuery = true)
    List<Point> findPointsByLeaderboardBetween(UUID leaderboardId, int offset, int next);

    @Query(value = "SELECT * FROM point WHERE user_id = ?1 AND leaderboard_id = ?2", nativeQuery = true)
    Optional<Point> getPointForUser(UUID userID, UUID leaderboardID);
}
