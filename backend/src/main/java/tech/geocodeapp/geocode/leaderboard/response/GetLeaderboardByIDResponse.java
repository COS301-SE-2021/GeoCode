package tech.geocodeapp.geocode.leaderboard.response;

import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

public class GetLeaderboardByIDResponse extends Response {
    /**
     * The found leaderboard with the given leaderboardID
     */
    private Leaderboard leaderboard;

    /**
     * Overloaded Constructor
     *
     * @param leaderboard The leaderboard from the specified leaderboard
     */
    public GetLeaderboardByIDResponse(boolean success, String message, Leaderboard leaderboard ) {
        super(success, message);
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
