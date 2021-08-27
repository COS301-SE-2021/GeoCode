package tech.geocodeapp.geocode.leaderboard.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.leaderboard.model.Point;

public class PointResponse extends Response {
    @JsonProperty("point")
    private Point point;

    public PointResponse(boolean success, String message, Point point) {
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
