package tech.geocodeapp.geocode.leaderboard.request;

import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

public class GetPointsByLeaderboardRequest {
    /**
     * The id of the collectable to be searched for
     */
    private Leaderboard leaderboard;

    /**
     * Default constructor
     */
    public GetPointsByLeaderboardRequest() {

    }

    /**
     * Overloaded Constructor
     *
     * @param leaderboard The leaderboard to be searched for
     */
    public GetPointsByLeaderboardRequest( Leaderboard leaderboard ) {

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
    public void setLeaderboard( Leaderboard leaderboard ) {

        this.leaderboard = leaderboard;
    }
}
