package tech.geocodeapp.geocode.leaderboard.service;

import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.leaderboard.exception.NullLeaderboardRequestParameterException;
import tech.geocodeapp.geocode.leaderboard.request.GetEventLeaderboardRequest;
import tech.geocodeapp.geocode.leaderboard.request.GetLeaderboardByIDRequest;
import tech.geocodeapp.geocode.leaderboard.request.GetPointsByLeaderboardRequest;
import tech.geocodeapp.geocode.leaderboard.response.GetEventLeaderboardResponse;
import tech.geocodeapp.geocode.leaderboard.response.GetLeaderboardByIDResponse;
import tech.geocodeapp.geocode.leaderboard.response.GetPointsByLeaderboardResponse;

/**
 * This interface is for the GeoCode subsystem
 */
@Service
public interface LeaderboardService {

    //U5.1 getEventLeaderboard
    GetEventLeaderboardResponse getEventLeaderboard(GetEventLeaderboardRequest request) throws NullLeaderboardRequestParameterException;

    //helper functions
    public GetLeaderboardByIDResponse getLeaderboardByID(GetLeaderboardByIDRequest request) throws NullLeaderboardRequestParameterException;

    public GetPointsByLeaderboardResponse getPointsByLeaderboard(GetPointsByLeaderboardRequest request) throws NullLeaderboardRequestParameterException;
}
