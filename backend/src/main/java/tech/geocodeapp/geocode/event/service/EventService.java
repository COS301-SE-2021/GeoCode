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
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    CreateEventResponse createEvent( CreateEventRequest request ) throws InvalidRequestException;

    /**
     * Create a new TimeTrial for an event, that will be active for a pre-determined
     * amount of time
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    CreateTimeTrialResponse createTimeTrial( CreateTimeTrialRequest request ) throws InvalidRequestException;

    /**
     * Get a specified Event that is stored in the repository
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetEventRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    GetEventResponse getEvent( GetEventRequest request ) throws InvalidRequestException;

    /**
     * Get a specific Event that a User is currently partaking in and the Event stored in the repository
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetCurrentEventRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    GetCurrentEventResponse getCurrentEvent( GetCurrentEventRequest request ) throws InvalidRequestException;

    /**
     * Get the next GeoCode the User has to find for their current Event
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified NextStageRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    NextStageResponse nextStage( NextStageRequest request ) throws InvalidRequestException;

    /**
     * Retrieve a list of Events around a certain radius of a location
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified EventsNearMeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    EventsNearMeResponse eventsNearMe( EventsNearMeRequest request ) throws InvalidRequestException;

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
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    ChangeAvailabilityResponse changeAvailability( ChangeAvailabilityRequest request ) throws InvalidRequestException;

    /**
     * Get all the Events at a certain location
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    GetEventsByLocationResponse getEventsByLocation( GetEventsByLocationRequest request ) throws InvalidRequestException;

    /**
     * Create a new Leaderboard for an Event
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    CreateLeaderboardResponse createLeaderBoard( CreateLeaderboardRequest request ) throws InvalidRequestException;

}
