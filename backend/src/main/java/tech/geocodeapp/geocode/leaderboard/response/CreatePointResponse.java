package tech.geocodeapp.geocode.leaderboard.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.general.Response;
import tech.geocodeapp.geocode.leaderboard.model.Point;

public class CreatePointResponse extends Response {
    @JsonProperty("point")
    private Point point;

    public CreatePointResponse() {

    }

    public CreatePointResponse(boolean success, String message, Point point) {
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
