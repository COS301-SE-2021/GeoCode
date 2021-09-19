package tech.geocodeapp.geocode.leaderboard.request;

import java.util.UUID;

public class GetLeaderboardByIDRequest {
    /**
     * The id of the Leaderboard to be searched for
     */
    private UUID leaderboardID;

    public GetLeaderboardByIDRequest() {}

    /**
     * Overloaded Constructor
     *
     * @param leaderboardID The leaderboardID to be searched for
     */
    public GetLeaderboardByIDRequest( UUID leaderboardID ) {

        this.leaderboardID = leaderboardID;
    }

    /**
     *  Gets the saved leaderboardID attribute
     *
     * @return the stored leaderboardID attribute
     */
    public UUID getLeaderboardID() {

        return leaderboardID;
    }
}
