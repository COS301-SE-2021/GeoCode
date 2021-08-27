package tech.geocodeapp.geocode.leaderboard.request;

import java.util.UUID;

public class DeletePointRequest {

    private final UUID pointId;

    public DeletePointRequest(UUID pointId) {
        this.pointId = pointId;
    }

    public UUID getPointId() {
        return pointId;
    }

}
