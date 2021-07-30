package tech.geocodeapp.geocode.leaderboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.model.MyLeaderboardDetails;
import tech.geocodeapp.geocode.leaderboard.model.Point;

import java.util.List;
import java.util.UUID;

/**
 * This class implements the repository for Points
 */
@Repository
public interface PointRepository extends JpaRepository<Point, UUID> {
    List<Point> findAllByLeaderboard(Leaderboard leaderboard);

    @Query(value = "SELECT name, amount as points, rank FROM (" +
            "SELECT leaderboards.name, amount, DENSE_RANK() OVER (ORDER BY amount) AS rank, user_id" +
            " FROM point JOIN leaderboards ON point.leaderboard_id = leaderboards.id" +
            " ORDER BY rank" +
            ") AS all_ranks" +
            " WHERE user_id = ?1", nativeQuery = true)
    List<MyLeaderboardDetails> getMyLeaderboards(UUID userID);

    @Query(value = "SELECT DENSE_RANK() OVER (ORDER BY amount) AS rank FROM point WHERE leaderboard_id = ?1 AND amount = ?2 ORDER BY rank", nativeQuery = true)
    int getMyRank(UUID leaderboardID, int amount);

    int countByLeaderboard(Leaderboard leaderboard);

    @Query(value = "SELECT * FROM point WHERE leaderboard_id = ?1 ORDER BY amount OFFSET ?2 ROWS FETCH NEXT ?3 ROWS ONLY ", nativeQuery = true)
    List<Point> findPointsByLeaderboardBetween(UUID leaderboardId, int offset, int next);
}
