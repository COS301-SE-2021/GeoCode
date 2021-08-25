package tech.geocodeapp.geocode.leaderboard.exception;

public class NullLeaderboardRequestParameterException extends Exception{
    /**
     * Message for if the User request object had any null parameters
     */
    public NullLeaderboardRequestParameterException() {
        super("The given request has NULL parameter(s).");
    }
}
