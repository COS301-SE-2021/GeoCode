package tech.geocodeapp.geocode.Leaderboard.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.geocodeapp.geocode.Leaderboard.Model.Leaderboard;


import java.util.UUID;

/**
 * This class implements the repository for the Leaderboard Subsystem
 */
@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {

    //SELECT

    //UPDATE

    //DELETE
}
