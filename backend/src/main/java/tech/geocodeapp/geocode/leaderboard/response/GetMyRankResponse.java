package tech.geocodeapp.geocode.leaderboard.response;

public class GetMyRankResponse {
    /**
     * The found rank
     */
    private Integer rank;

    /**
     * Default constructor
     */
    public GetMyRankResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param rank The rank from the specified leaderboard
     */
    public GetMyRankResponse(Integer rank ) {

        this.rank = rank;
    }

    /**
     *  Gets the saved rank attribute
     *
     * @return the stored rank attribute
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * Sets the rank attribute to the specified value
     *
     * @param rank the value the attribute should be set to
     */
    public void setRank(Integer rank) {

        this.rank = rank;
    }


}