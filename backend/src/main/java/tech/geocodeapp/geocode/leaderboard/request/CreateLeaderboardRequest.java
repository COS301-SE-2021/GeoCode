package tech.geocodeapp.geocode.leaderboard.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CreateLeaderboardRequest
 */
public class CreateLeaderboardRequest   {
    @JsonProperty("name")
    private String name;

    public CreateLeaderboardRequest(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
