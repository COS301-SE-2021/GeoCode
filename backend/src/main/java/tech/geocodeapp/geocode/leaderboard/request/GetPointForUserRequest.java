package tech.geocodeapp.geocode.leaderboard.request;

import java.util.UUID;

/**
 * Get the Point object for the given User ID and Leaderboard ID
 */
public class GetPointForUserRequest {
    /**
     * The User ID
     */
    private UUID userID;

    /**
     * The Leaderboard ID
     */
    private final UUID leaderboardID;

    public GetPointForUserRequest(UUID userID, UUID leaderboardID) {
        this.userID = userID;
        this.leaderboardID = leaderboardID;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public UUID getLeaderboardID() {
        return leaderboardID;
    }
}
