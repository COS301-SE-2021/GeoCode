package tech.geocodeapp.geocode.event.service;

import org.springframework.stereotype.Service;

import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.event.request.*;
import tech.geocodeapp.geocode.event.response.*;


/**
 * This class implements the EventService interface
 */
@Service( "EventService" )
@Validated
public class EventServiceImpl implements EventService {


    @Override
    public ChangeAvailabilityResponse changeAvailability( ChangeAvailabilityRequest request ) {

        return null;
    }

    @Override
    public GetAllEventsResponse getAllEvents() {

        return null;
    }

    @Override
    public CreateEventResponse createEvent( CreateEventRequest request ) {

        return null;
    }

    @Override
    public GetEventsByLocationResponse getEventsByLocation( GetEventsByLocationRequest request ) {

        return null;
    }

    @Override
    public CreatePointResponse createPoint( CreatePointRequest request ) {

        return null;
    }

    @Override
    public CreateTimeTrialResponse createTimeTrial( CreateTimeTrialRequest request ) {

        return null;
    }

    @Override
    public GetPointsByUserResponse getPointsByUser( GetPointsByUserRequest request ) {

        return null;
    }

    @Override
    public GetPointsByLeaderBoardResponse getPointsByLeaderboard( GetPointsByLeaderBoardRequest request ) {

        return null;
    }

    @Override
    public GetPointsResponse getPoints() {

        return null;
    }

    @Override
    public GetLeaderBoardByTimeTrialResponse getLeaderBoardByTimeTrial( GetLeaderBoardByTimeTrialRequest request ) {

        return null;
    }

    @Override
    public CreateLeaderboardResponse createLeaderBoard( CreateLeaderboardRequest request ) {

        return null;
    }

}