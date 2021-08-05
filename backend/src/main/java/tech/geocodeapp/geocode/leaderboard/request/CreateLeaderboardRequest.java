package tech.geocodeapp.geocode.leaderboard.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * CreateLeaderboardRequest
 */
public class CreateLeaderboardRequest   {
    @JsonProperty("name")
    private String name = null;

    public CreateLeaderboardRequest(String name){
        this.name = name;
    }

    /**
     * Get name
     * @return name
     **/
    @Schema(required = true, description = "")
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
