package tech.geocodeapp.geocode.event.service;


import tech.geocodeapp.geocode.event.request.*;
import tech.geocodeapp.geocode.event.response.*;

/**
 * This is the main interface is for the Event subsystem,
 * it is used to call the relevant use cases to create, manipulate and delete Event.
 */
public interface EventService {

    ChangeAvailabilityResponse changeAvailability( ChangeAvailabilityRequest request );

    GetAllEventsResponse getAllEvents();

    CreateEventResponse createEvent( CreateEventRequest request );

    GetEventsByLocationResponse getEventsByLocation( GetEventsByLocationRequest request );

    CreatePointResponse createPoint( CreatePointRequest request );

    CreateTimeTrialResponse createTimeTrial( CreateTimeTrialRequest request );

    GetPointsByUserResponse getPointsByUser( GetPointsByUserRequest request );

    GetPointsByLeaderBoardResponse getPointsByLeaderboard( GetPointsByLeaderBoardRequest request );

    GetPointsResponse getPoints();

    GetLeaderBoardByTimeTrialResponse getLeaderBoardByTimeTrial( GetLeaderBoardByTimeTrialRequest request );

    CreateLeaderboardResponse createLeaderBoard( CreateLeaderboardRequest request );

}
