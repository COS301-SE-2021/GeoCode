package tech.geocodeapp.geocode.leaderboard.request;

import java.util.UUID;

public class CreatePointRequest {

    private Integer amount;
    private UUID userId;
    private UUID leaderboardId;

    /**
     * Default Constructor
     */
    public CreatePointRequest() {
    }

    /**
     * Fully parameterised Constructor
     * @param amount The starting value of the point
     * @param userId The UUID that identifies the user that the Point is being created for
     * @param leaderboardId The UUID identifying the Leaderboard to which the Point created belongs to
     */
    public CreatePointRequest(Integer amount, UUID userId, UUID leaderboardId) {
        this.amount = amount;
        this.userId = userId;
        this.leaderboardId = leaderboardId;
    }

    /**
     * Retrieves the value of the amount variable
     * @return the value of amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Sets the amount variable to the given value
     * @param amount the Integer value the variable should be set to.
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * Retrieves the value of the userId variable
     * @return The value of userId
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Sets the userId variable to the given value
     * @param userId the UUID value the variable should be set to
     */
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the value of the leaderboardId variable
     * @return The value of leaderboardId
     */
    public UUID getLeaderboardId() {
        return leaderboardId;
    }

    /**
     * Sets the leaderboardId variable to the given value
     * @param leaderboardId The UUID value the leaderboardId should be set to
     */
    public void setLeaderboardId(UUID leaderboardId) {
        this.leaderboardId = leaderboardId;
    }
}
