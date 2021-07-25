package tech.geocodeapp.geocode.leaderboard.request;

import java.util.UUID;

public class UpdatePointRequest {

    private UUID pointId;
    private Integer amount;
    private UUID userId;
    private UUID leaderboardId;

    public UpdatePointRequest() {
    }

    public UpdatePointRequest(UUID pointId, Integer amount, UUID userId, UUID leaderboardId) {
        this.pointId = pointId;
        this.amount = amount;
        this.userId = userId;
        this.leaderboardId = leaderboardId;
    }

}
