package tech.geocodeapp.geocode.leaderboard.request;

import java.util.UUID;

public class UpdatePointRequest {

    private final UUID pointId;

    /**
     * Increase the User's points by this amount
     */
    private Integer amount;

    private UUID userId;
    private UUID leaderboardId;

    public UpdatePointRequest(UUID pointId, Integer amount, UUID userId, UUID leaderboardId) {
        this.pointId = pointId;
        this.amount = amount;
        this.userId = userId;
        this.leaderboardId = leaderboardId;
    }

    public UUID getPointId() {
        return pointId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getLeaderboardId() {
        return leaderboardId;
    }

    public void setLeaderboardId(UUID leaderboardId) {
        this.leaderboardId = leaderboardId;
    }
}
