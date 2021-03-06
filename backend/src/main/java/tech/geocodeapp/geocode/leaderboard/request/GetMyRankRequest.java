package tech.geocodeapp.geocode.leaderboard.request;

import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

public class GetMyRankRequest {
    /**
     * The id of the leaderboard to be searched for
     */
    private Leaderboard leaderboard;

    /**
     * The point amount to search for
     */
    private Integer amount;

    /**
     * Overloaded Constructor
     *
     * @param leaderboard The leaderboard to be searched for
     * @param amount The amount to searched for
     */
    public GetMyRankRequest(Leaderboard leaderboard, Integer amount) {
        this.leaderboard = leaderboard;
        this.amount = amount;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
