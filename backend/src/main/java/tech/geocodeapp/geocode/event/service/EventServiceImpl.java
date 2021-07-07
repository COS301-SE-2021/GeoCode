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

    /**
     * Create a new Event object with the given attributes and store it
     * in the EventRepository
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateEventRequest
     */
    @Override
    public CreateEventResponse createEvent( CreateEventRequest request ) {

        return null;
    }

    /**
     * Get all the stored Events in the repository
     *
     * @return the newly created response instance
     */
    @Override
    public GetAllEventsResponse getAllEvents() {

        return null;
    }

    /**
     * Change the availability of a specific Event object
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified ChangeAvailabilityRequest
     */
    @Override
    public ChangeAvailabilityResponse changeAvailability( ChangeAvailabilityRequest request ) {

        return null;
    }

    /**
     * Get all the Events at a certain location
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     */
    @Override
    public GetEventsByLocationResponse getEventsByLocation( GetEventsByLocationRequest request ) {

        return null;
    }

    /**
     * Create a new Leaderboard for an Event
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     */
    @Override
    public CreateLeaderboardResponse createLeaderBoard( CreateLeaderboardRequest request ) {

        return null;
    }

    /**
     * Create a new point for a leaderboard
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     */
    @Override
    public CreatePointResponse createPoint( CreatePointRequest request ) {

        return null;
    }

    /**
     * Create a new TimeTrial for an event, that will be active for a pre-determined
     * amount of time
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     */
    @Override
    public CreateTimeTrialResponse createTimeTrial( CreateTimeTrialRequest request ) {

        return null;
    }

    /**
     * Get the points for a specific event that a user has gotten
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     */
    @Override
    public GetPointsByUserResponse getPointsByUser( GetPointsByUserRequest request ) {

        return null;
    }

    /**
     * Get the points for a specific leaderboard
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     */
    @Override
    public GetPointsByLeaderBoardResponse getPointsByLeaderboard( GetPointsByLeaderBoardRequest request ) {

        return null;
    }

    /**
     * Get the points of a specific Event
     *
     * @return the newly created response instance
     */
    @Override
    public GetPointsResponse getPoints() {

        return null;
    }

    /**
     * Get the Leaderboard of a TimeTrial
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     */
    @Override
    public GetLeaderBoardByTimeTrialResponse getLeaderBoardByTimeTrial( GetLeaderBoardByTimeTrialRequest request ) {

        return null;
    }

}