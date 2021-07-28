package tech.geocodeapp.geocode.leaderboard.response;


import tech.geocodeapp.geocode.general.Response;

public class CreateLeaderboardResponse extends Response {

    public CreateLeaderboardResponse() {

    }

    public CreateLeaderboardResponse(boolean success, String message) {
        super(success, message);
    }

}
