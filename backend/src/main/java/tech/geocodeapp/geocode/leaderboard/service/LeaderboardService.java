package tech.geocodeapp.geocode.leaderboard.service;

import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.leaderboard.model.Point;
import tech.geocodeapp.geocode.leaderboard.request.*;
import tech.geocodeapp.geocode.leaderboard.response.*;

/**
 * This interface is for the GeoCode subsystem
 */
public interface LeaderboardService {

    //U5.1 createLeaderboard
    CreateLeaderboardResponse createLeaderboard(CreateLeaderboardRequest request) throws NullRequestParameterException;

    //U5.2 getEventLeaderboard
    GetEventLeaderboardResponse getEventLeaderboard(GetEventLeaderboardRequest request) throws NullRequestParameterException;

    //U5.3 GetPointForUser
    PointResponse getPointForUser(GetPointForUserRequest request) throws NullRequestParameterException;

    //U5.4 getLeaderboardByID
    GetLeaderboardByIDResponse getLeaderboardByID(GetLeaderboardByIDRequest request) throws NullRequestParameterException;

    //U5.5 createPoint
    PointResponse createPoint(CreatePointRequest request) throws NullRequestParameterException;

    //U5.6 deletePoint
    DeletePointResponse deletePoint(DeletePointRequest request) throws NullRequestParameterException;

    //U5.7 updatePoint
    PointResponse updatePoint(UpdatePointRequest request) throws NullRequestParameterException;

    //helper functions

    //U5.8 savePoint
    Response savePoint(Point point) throws NullRequestParameterException;

    //U5.9 getPointsByLeaderboard
    GetPointsByLeaderboardResponse getPointsByLeaderboard(GetPointsByLeaderboardRequest request) throws NullRequestParameterException;

    //U5.10 etMyRank
    GetMyRankResponse getMyRank(GetMyRankRequest request) throws NullRequestParameterException;
}
