package tech.geocodeapp.geocode.leaderboard.response;

import tech.geocodeapp.geocode.leaderboard.model.Point;

import java.util.List;

public class GetPointsByLeaderboardResponse {
    /**
     * The found points with the given leaderboard
     */
    private List<Point> points;

    /**
     * Default constructor
     */
    public GetPointsByLeaderboardResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param points The points from the specified Leaderboard
     */
    public GetPointsByLeaderboardResponse( List<Point> points ) {

        this.points = points;
    }

    /**
     *  Gets the saved points attribute
     *
     * @return the stored points attribute
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     * Sets the points attribute to the specified value
     *
     * @param points the value the attribute should be set to
     */
    public void setPoints(List<Point> points) {

        this.points = points;
    }
}
