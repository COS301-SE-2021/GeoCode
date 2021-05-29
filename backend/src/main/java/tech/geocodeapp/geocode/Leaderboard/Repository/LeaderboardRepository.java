package tech.geocodeapp.geocode.Leaderboard.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

/**
 * This class implements the repository for the Leaderboard Subsystem
 */
@Repository
public interface LeaderboardRepository extends JpaRepository<String, UUID> {

    //SELECT

    //UPDATE

    //DELETE
}
