package tech.geocodeapp.geocode.leaderboard.response;

import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

public class GetLeaderboardByIDResponse {
    /**
     * The found leaderboard with the given leaderboardID
     */
    private Leaderboard leaderboard;

    /**
     * Default constructor
     */
    public GetLeaderboardByIDResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param leaderboard The leaderboard from the specified leaderboard
     */
    public GetLeaderboardByIDResponse( Leaderboard leaderboard ) {

        this.leaderboard = leaderboard;
    }

    /**
     *  Gets the saved leaderboard attribute
     *
     * @return the stored leaderboard attribute
     */
    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    /**
     * Sets the leaderboard attribute to the specified value
     *
     * @param leaderboard the value the attribute should be set to
     */
    public void setLeaderboard(Leaderboard leaderboard) {

        this.leaderboard = leaderboard;
    }


}
