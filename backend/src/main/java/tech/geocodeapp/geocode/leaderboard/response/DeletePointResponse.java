package tech.geocodeapp.geocode.leaderboard.response;

import tech.geocodeapp.geocode.general.response.Response;

public class DeletePointResponse extends Response {

    public DeletePointResponse() {

    }

    public DeletePointResponse(boolean success, String message) {
        super(success, message);
    }
}
