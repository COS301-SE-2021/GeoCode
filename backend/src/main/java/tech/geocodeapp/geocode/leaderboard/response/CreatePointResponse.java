package tech.geocodeapp.geocode.leaderboard.response;

import tech.geocodeapp.geocode.leaderboard.model.Point;

public class CreatePointResponse {

    private Point point;
    private String message;
    private boolean success;

    public CreatePointResponse() {
    }

    public CreatePointResponse(Point point, String message, boolean success) {
        this.point = point;
        this.message = message;
        this.success = success;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
