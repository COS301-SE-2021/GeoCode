package tech.geocodeapp.geocode.leaderboard.service;

import org.springframework.stereotype.Service;
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

    //helper functions
    GetLeaderboardByIDResponse getLeaderboardByID(GetLeaderboardByIDRequest request) throws NullRequestParameterException;

    GetPointsByLeaderboardResponse getPointsByLeaderboard(GetMyRankRequest request) throws NullRequestParameterException;



    GetMyRankResponse getMyRank(GetMyRankRequest request) throws NullRequestParameterException;

    PointResponse createPoint(CreatePointRequest request) throws NullRequestParameterException;

    DeletePointResponse deletePoint(DeletePointRequest request) throws NullRequestParameterException;

    PointResponse updatePoint(UpdatePointRequest request) throws NullRequestParameterException;

    Response savePoint(Point point) throws NullRequestParameterException;
}
