package tech.geocodeapp.geocode.leaderboard.response;

import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.leaderboard.model.Point;

public class GetPointForUserResponse extends Response {
    /**
     * The wanted Point object
     */
    private Point point;

    public GetPointForUserResponse(boolean success, String message, Point point) {
        super(success, message);
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
