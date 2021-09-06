package tech.geocodeapp.geocode.event.service;

import tech.geocodeapp.geocode.event.request.*;
import tech.geocodeapp.geocode.event.response.*;
import tech.geocodeapp.geocode.event.exceptions.*;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.service.GeoCodeService;

import java.util.List;

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
     * Get the current status of a User participating in an event, as well as the target GeoCode that they need to locate
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetCurrentEventStatusRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    GetCurrentEventStatusResponse getCurrentEventStatus(GetCurrentEventStatusRequest request ) throws InvalidRequestException;

    /**
     * A function that moves a user to the next stage of an event.
     * This function will be called by other subsystems when an event geocode is found.
     *
     * @param foundGeocode the geocode that was just found, triggering the stage change. It should include an event ID.
     * @param userID the unique ID of the user entering the event
     *
     * @throws InvalidRequestException any of the provided parameters are null
     */
    void nextStage(GeoCode foundGeocode, java.util.UUID userID ) throws InvalidRequestException, NotFoundException, MismatchedParametersException;

    /**
     * Retrieve a list of Events that a user is participating in
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetEnteredEventsRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    GetEnteredEventsResponse getEnteredEvents(GetEnteredEventsRequest request ) throws InvalidRequestException;

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

    /**
     * Submitting an attempt for a Blockly Event.
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified SubmitBlocklyCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    SubmitBlocklyCodeResponse submitBlocklyCode( SubmitBlocklyCodeRequest request ) throws InvalidRequestException;

    /**
     * Getting the Blockly blocks for the current User for a Blockly Event
     *
     * @param request the attributes the response should be created from
     *
     * @return The Blockly blocks for the current User
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    GetBlocksResponse getBlocks( GetBlocksRequest request ) throws InvalidRequestException;

    ///// Blockly helper functions /////

    /**
     * Runs the provided JavaScript code that was generated on front-end from the User's Blockly code
     * @param caseInputs
     * @param correctCaseOutputs
     * @param code The JavaScript code
     * @return
     */
    List<Boolean> runJavaScriptCode(List<String> caseInputs, List<String> correctCaseOutputs, String code);

    /**
     * Post construct the GeoCode service, this avoids a circular dependency
     *
     * @param geoCodeService the service to be set
     */
    void setGeoCodeService( GeoCodeService geoCodeService );
}
