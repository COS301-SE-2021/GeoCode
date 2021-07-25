package tech.geocodeapp.geocode.leaderboard.response;

import tech.geocodeapp.geocode.leaderboard.model.Point;

public class UpdatePointResponse {

    private Point point;
    private String message;
    private boolean success;

    public UpdatePointResponse() {
    }

    public UpdatePointResponse(Point point, String message, boolean success) {
        this.point = point;
        this.message = message;
        this.success = success;
    }
}
