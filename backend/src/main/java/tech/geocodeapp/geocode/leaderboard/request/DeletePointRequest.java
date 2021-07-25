package tech.geocodeapp.geocode.leaderboard.request;

import java.util.UUID;

public class DeletePointRequest {

    private UUID pointId;

    public DeletePointRequest() {
    }

    public DeletePointRequest(UUID pointId) {
        this.pointId = pointId;
    }
}
