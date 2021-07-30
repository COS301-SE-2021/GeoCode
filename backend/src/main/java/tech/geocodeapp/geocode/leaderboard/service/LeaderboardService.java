package tech.geocodeapp.geocode.leaderboard.service;

import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.leaderboard.exception.NullLeaderboardRequestParameterException;
import tech.geocodeapp.geocode.leaderboard.request.*;
import tech.geocodeapp.geocode.leaderboard.response.*;

/**
 * This interface is for the GeoCode subsystem
 */
@Service
public interface LeaderboardService {

    //U5.1 createLeaderboard
    CreateLeaderboardResponse createLeaderboard(CreateLeaderboardRequest request) throws NullLeaderboardRequestParameterException;

    //U5.2 getEventLeaderboard
    GetEventLeaderboardResponse getEventLeaderboard(GetEventLeaderboardRequest request) throws NullLeaderboardRequestParameterException;

    //helper functions
    public GetLeaderboardByIDResponse getLeaderboardByID(GetLeaderboardByIDRequest request) throws NullLeaderboardRequestParameterException;

    public GetPointsByLeaderboardResponse getPointsByLeaderboard(GetMyRankRequest request) throws NullLeaderboardRequestParameterException;

    public GetMyRankResponse getMyRank(GetMyRankRequest request) throws NullLeaderboardRequestParameterException;

    public PointResponse createPoint(CreatePointRequest request) throws NullLeaderboardRequestParameterException;

    public DeletePointResponse deletePoint(DeletePointRequest request) throws NullLeaderboardRequestParameterException;

    public PointResponse updatePoint(UpdatePointRequest request) throws NullLeaderboardRequestParameterException;
}
