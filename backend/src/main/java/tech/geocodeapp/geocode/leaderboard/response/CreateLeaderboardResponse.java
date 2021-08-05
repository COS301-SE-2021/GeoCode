package tech.geocodeapp.geocode.leaderboard.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateLeaderboardResponse
 */
public class CreateLeaderboardResponse extends Response {
    @JsonProperty("leaderboard")
    private Leaderboard leaderboard = null;

    public CreateLeaderboardResponse(boolean success, String message, Leaderboard leaderboard){
        super(success, message);
        this.leaderboard = leaderboard;
    }

    /**
     * Get leaderboard
     * @return leaderboard
     **/
    @Schema(required = true, description = "")
    @NotNull
    @Valid
    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }
}
