package tech.geocodeapp.geocode.event.service;

import tech.geocodeapp.geocode.event.request.*;
import tech.geocodeapp.geocode.event.response.*;
import tech.geocodeapp.geocode.event.exceptions.*;

/**
 * This is the main interface is for the Event subsystem,
 * it is used to call the relevant use cases to create, manipulate and delete Event.
 */
public interface EventService {

    /**
     * Create a new Event object with the given attributes and store it
     * in the EventRepository
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateEventRequest
     */
    CreateEventResponse createEvent( CreateEventRequest request ) throws InvalidRequestException;

    /**
     * Get the Event identified by the given ID
     * @param request the attributes the response should be created from
     * @return A GetEventByIDResponse object containing the Event if it exists
     */
    GetEventByIDResponse getEventByID( GetEventByIDRequest request) throws InvalidRequestException;

    /**
     * Get all the stored Events in the repository
     *
     * @return the newly created response instance
     */
    GetAllEventsResponse getAllEvents();

    /**
     * Change the availability of a specific Event object
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified ChangeAvailabilityRequest
     */
    ChangeAvailabilityResponse changeAvailability( ChangeAvailabilityRequest request ) throws InvalidRequestException;

    /**
     * Get all the Events at a certain location
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     */
    GetEventsByLocationResponse getEventsByLocation( GetEventsByLocationRequest request ) throws InvalidRequestException;

    /**
     * Create a new point for a leaderboard
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     */
    CreatePointResponse createPoint( CreatePointRequest request ) throws InvalidRequestException;

    /**
     * Create a new Leaderboard for an Event
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     */
    CreateLeaderboardResponse createLeaderBoard( CreateLeaderboardRequest request ) throws InvalidRequestException;

    /**
     * Create a new TimeTrial for an event, that will be active for a pre-determined
     * amount of time
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     */
    CreateTimeTrialResponse createTimeTrial( CreateTimeTrialRequest request );

    /**
     * Get the points for a specific event that a user has gotten
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     */
    GetPointsByUserResponse getPointsByUser( GetPointsByUserRequest request ) throws InvalidRequestException;

    /**
     * Get the points for a specific leaderboard
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     */
    GetPointsByLeaderBoardResponse getPointsByLeaderboard( GetPointsByLeaderBoardRequest request ) throws InvalidRequestException;

    /**
     * Get the points of a specific Event
     *
     * @return the newly created response instance
     */
    GetPointsResponse getPoints();

    /**
     * Get the Leaderboard of a TimeTrial
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     */
    GetLeaderBoardByTimeTrialResponse getLeaderBoardByTimeTrial( GetLeaderBoardByTimeTrialRequest request ) throws InvalidRequestException;

}
