package tech.geocodeapp.geocode.leaderboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;


import java.util.Optional;
import java.util.UUID;

/**
 * This class implements the repository for the Leaderboard Subsystem
 */
@Repository
public interface LeaderboardRepository extends JpaRepository< Leaderboard, UUID> {

    /**
     * Returns a leaderboard that corresponds with the provided event if one exists
     * @param event the event to find a leaderboard for
     * @return an Optional containing the Leaderboard found or null if none is found
     */
    Optional<Leaderboard> getLeaderboardByEvent(Event event);
}
