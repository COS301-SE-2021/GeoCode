package tech.geocodeapp.geocode.leaderboard.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

/**
 * CreateLeaderboardResponse
 */
public class CreateLeaderboardResponse extends Response {
    @JsonProperty("leaderboard")
    private Leaderboard leaderboard = null;

    public  CreateLeaderboardResponse() {

    }

    public CreateLeaderboardResponse(boolean success, String message, Leaderboard leaderboard){
        super(success, message);
        this.leaderboard = leaderboard;
    }

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }
}
