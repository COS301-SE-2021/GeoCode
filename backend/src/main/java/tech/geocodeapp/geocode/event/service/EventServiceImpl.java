package tech.geocodeapp.geocode.event.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.event.blockly.Block;
import tech.geocodeapp.geocode.event.blockly.TestCase;
import tech.geocodeapp.geocode.event.decorator.EventComponent;
import tech.geocodeapp.geocode.event.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.event.exceptions.MismatchedParametersException;
import tech.geocodeapp.geocode.event.exceptions.NotFoundException;
import tech.geocodeapp.geocode.event.exceptions.RepoException;
import tech.geocodeapp.geocode.event.manager.EventManager;
import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.event.model.OrderLevels;
import tech.geocodeapp.geocode.event.model.UserEventStatus;
import tech.geocodeapp.geocode.event.pathfinder.Graph;
import tech.geocodeapp.geocode.event.repository.EventRepository;
import tech.geocodeapp.geocode.event.repository.UserEventStatusRepository;
import tech.geocodeapp.geocode.event.request.*;
import tech.geocodeapp.geocode.event.response.*;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.general.security.CurrentUserDetails;
import tech.geocodeapp.geocode.geocode.model.Difficulty;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.geocode.request.CreateGeoCodeRequest;
import tech.geocodeapp.geocode.geocode.request.GetGeoCodeRequest;
import tech.geocodeapp.geocode.geocode.response.CreateGeoCodeResponse;
import tech.geocodeapp.geocode.geocode.response.GetGeoCodeResponse;
import tech.geocodeapp.geocode.geocode.service.GeoCodeService;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.model.Point;
import tech.geocodeapp.geocode.leaderboard.request.CreateLeaderboardRequest;
import tech.geocodeapp.geocode.leaderboard.request.CreatePointRequest;
import tech.geocodeapp.geocode.leaderboard.request.GetPointForUserRequest;
import tech.geocodeapp.geocode.leaderboard.response.PointResponse;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * This class implements the EventService interface
 */
@Validated
@Service( "EventService" )
public class EventServiceImpl implements EventService {

    /**
     * The repository the Event class interacts with
     */
    @NotNull( message = "Events repository may not be null." )
    private final EventRepository eventRepo;

    /**
     * The repository the Event class interacts with
     */
    @NotNull( message = "TimeLog repository may not be null." )
    private final UserEventStatusRepository userEventStatusRepo;


    /**
     * The Leaderboard service to access the use cases and
     * Leaderboard repository
     */
    @NotNull( message = "GeoCodeService: Leaderboard Service Implementation may not be null." )
    private final LeaderboardService leaderboardService;

    /**
     * The GeoCode service to access the use cases and
     * GeoCode repository
     */
    @NotNull( message = "GeoCode Service Implementation may not be null." )
    private GeoCodeService geoCodeService;

    private final String eventNotFoundMessage = "Event not found";

    /**
     * Overloaded Constructor
     *
     * @param eventRepo the repo the created response attributes should save to
     * @param leaderboardService access to the Leaderboard use cases and repository
     */
    public EventServiceImpl( EventRepository eventRepo,
                             UserEventStatusRepository userEventStatusRepo,
                             @Qualifier( "LeaderboardService" ) @Lazy LeaderboardService leaderboardService ) throws RepoException {

        if ( ( eventRepo != null ) && ( userEventStatusRepo != null ) ) {

            /* The repo exists therefore it can be set for the class */
            this.eventRepo = eventRepo;
            this.userEventStatusRepo = userEventStatusRepo;

            this.leaderboardService = Objects.requireNonNull( leaderboardService, "EventService: Leaderboard service must not be null." );
        } else {

            /* The repo does not exist throw an error */
            throw new RepoException();
        }
    }

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
    @Override
    @Transactional
    public CreateEventResponse createEvent( CreateEventRequest request ) throws InvalidRequestException {
        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( ( request.getDescription() == null ) || ( request.getLocation() == null ) ||
                ( request.getName() == null ) || ( request.getBeginDate() == null ) ||
                ( request.getEndDate() == null ) || ( request.getCreateGeoCodesToFind() == null ) ||
                ( request.getOrderBy() == null ) ) {

            throw new InvalidRequestException();
        }

        /* Create the new Event object with the specified attributes */
        UUID eventID = UUID.randomUUID();

        /* set the geoCodeIDs to null for now, set it once the GeoCodeIDs have been stored */
        var event = new Event( eventID, request.getName(), request.getDescription(),
                request.getLocation(), null, request.getBeginDate(), request.getEndDate(),
                null, request.getProperties() );

        /* Check the availability of the Event */
        if ( request.isAvailable() == null ) {

            /* None is set so make it default available */
            event.setAvailable( true );
        } else {

            /* Set to the given */
            event.setAvailable( request.isAvailable() );
        }

        /* check if the Event is a Blockly Event */
        var properties = request.getProperties();

        if(!properties.isEmpty()){
            /* check if all properties were specified */
            if( !properties.containsKey("problem_description") ){
                return new CreateEventResponse( false, " not specified for the Blockly Event");
            }

            if( !properties.containsKey("testCases") ){
                return new CreateEventResponse( false, "Test cases were not specified for the Blockly Event" );
            }

            if( !properties.containsKey("blocks") ){
                return new CreateEventResponse( false, "Block types were not specified for the Blockly Event" );
            }

            if(properties.get("problem_description").strip().equals("")){
                return new CreateEventResponse(false, "An empty problem description was given for the Blockly Event");
            }

            /* check the test cases and block information are in the correct format */
            try {
                final ObjectMapper objectMapper = new ObjectMapper();

                var testCases = objectMapper.readValue(properties.get("testCases"), TestCase[].class);
                var blocks = objectMapper.readValue(properties.get("blocks"), Block[].class);

                /* check that the number of block types is at least the number stages in the event
                * so that at least 1 block type can be allocated at each stage of the event
                *  */
                if(blocks.length < request.getCreateGeoCodesToFind().size()){
                    return new CreateEventResponse(false, "The number of block types must be at least the number of GeoCodes in the Blockly Event");
                }

                /* check the input arrays are of the same size */
                int numInputValues = testCases[0].getInputs().length;

                for(int i = 1; i < testCases.length; ++i){
                    if(testCases[i].getInputs().length != numInputValues){
                        return new CreateEventResponse(false, "The number of input values for each test case must be the same");
                    }
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return new CreateEventResponse(false, "Invalid format provided for the Blockly Event's properties: "+e.getMessage());
            }
        }

        /* Create the EventComponent to pass to the createGeoCode function */
        EventManager eventManager = new EventManager();
        EventComponent eventComponent = eventManager.buildEvent(event);

        /* Store the list of GeoCode UUIDs to create a Level on */
        List<CreateGeoCodeRequest> createGeoCodeRequests = request.getCreateGeoCodesToFind();

        List<UUID> geoCodeIDs = new ArrayList<>();
        List< GeoCode > geoCodes = new ArrayList<>();

        /*
         * Go through each GeoCode creation request
         * and get the GeoCode object
         */
        for ( CreateGeoCodeRequest createGeoCodeRequest : createGeoCodeRequests ) {
            try {
                /*
                 * Call the GeoCode service to create the GeoCode Object
                 * Pass eventComponent to createGeoCode to use to check if Collectables
                 * should be added to the GeoCode
                 * */
                createGeoCodeRequest.setEventComponent(eventComponent);

                var createGeoCodeResponse = geoCodeService.createGeoCode( createGeoCodeRequest );

                if ( !createGeoCodeResponse.isSuccess() ) {
                    return new CreateEventResponse( false, "Failed to create a GeoCode" );
                }

                /*
                 * add the created object to the list
                 * */
                var created = createGeoCodeResponse.getCreatedGeocode();
                geoCodes.add( created );

                /* add the current GeoCode's id to the list of GeoCode ids, so that if the
                * GeoCode id list is not empty when setting it for the created Event
                *  */
                geoCodeIDs.add( created.getId() );

            } catch ( tech.geocodeapp.geocode.geocode.exceptions.InvalidRequestException e ) {
                return new CreateEventResponse( false, e.getMessage());
            }
        }

        /*
         * Determine which order to set the GeoCodes in
         * the default is OrderLevels.GIVEN
         */
        if ( request.getOrderBy().equals( OrderLevels.DIFFICULTY ) ) {

            /* Set the list to go from easiest to most difficult on finding the GeoCode */
            geoCodeIDs = sortByDifficulty( geoCodes );
        } else if ( request.getOrderBy().equals( OrderLevels.DISTANCE ) ) {

            /* Set the list to go from least to most distance with where the GeoCode is located */
            geoCodeIDs = sortByDistance( geoCodes, request.getLocation() );
        }

        /* Check if the GeoCodes are still valid after sorting */
        if ( geoCodeIDs == null ) {

            /* The GeoCodes are no longer valid so stop */
            return new CreateEventResponse( false, "Sorting failed" );
        }

        /* set the GeoCode ids now that we have them after the GeoCodes have been created */
        event.setGeocodeIDs(geoCodeIDs);

        /* Hold the created leaderboards */
        var leaderboards = new ArrayList< Leaderboard >();

        try {

            /*
             * Create the request to the leaderboard service
             * and store the response
             */
            var leaderboardRequest = new CreateLeaderboardRequest( request.getName() );
            var hold = leaderboardService.createLeaderboard( leaderboardRequest ).getLeaderboard();

            leaderboards.add( hold );
        } catch ( NullRequestParameterException e ) {

            return new CreateEventResponse( false, e.getMessage() );
        }

        /* set the leaderboard now that we now the details of the Event are correct
        * and the details for creating the GeoCodes are also correct
        * */
        event.setLeaderboards(leaderboards);

        /*
         * Save the newly create Event
         * Validate if the Event was saved properly
         */
        try {

            /* Save the newly created entry to the repository */
            eventRepo.save( event );
            return new CreateEventResponse( true, "Event created", event.getId(), geoCodes );
        } catch ( IllegalArgumentException error ) {

            return new CreateEventResponse( false, error.getMessage() );
        }
    }

    /**
     * Get a specified Event that is stored in the repository
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetEventRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetEventResponse getEvent( GetEventRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getEventID() == null ) {

            throw new InvalidRequestException();
        }

        /* Create the response to return */
        GetEventResponse response;
        try {

            /*
             * Query the repository for the Event object
             * and set the response to true with the found Event
             */
            Optional< Event > temp = eventRepo.findById( request.getEventID() );
            response = temp.map(

                    /* Indicate the Event was found and return it */
                    event -> new GetEventResponse( true, "Event found", event.removePrivateDetails() )
                               ).orElseGet(

                    /* Indicate the Event was not found */
                    () -> new GetEventResponse( false, eventNotFoundMessage, null )
                                          );

        } catch ( EntityNotFoundException error ) {

            /* No Event found so set the response to false */
            response = new GetEventResponse( false, eventNotFoundMessage, null );
        }

        return response;
    }

    /**
     * Get the current status of a User participating in an event, as well as the target GeoCode that they need to locate
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetCurrentEventStatusResponse
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetCurrentEventStatusResponse getCurrentEventStatus( GetCurrentEventStatusRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( ( request.getEventID() == null ) || ( request.getUserID() == null ) ) {

            throw new InvalidRequestException();
        }

        /* Get the UserEventStatus object from the repository */
        UserEventStatus status = userEventStatusRepo.findStatusByEventIDAndUserID( request.getEventID(), request.getUserID() );

        /* Check whether the current user is requesting for themselves */
        UUID currentUserID = CurrentUserDetails.getID();
        if ( !currentUserID.equals( request.getUserID() ) ) {

            /* The userID passed in does not match the current user ID. Just return the passed-in user's status */
            return new GetCurrentEventStatusResponse( true, "Status returned for another user", status, null );
        }

        /* from this point the currentUserID is the same as the request user ID */

        if ( status == null ) {
            /* User is not participating in an event with the provided id. Enter them into it. */

            /* Find the event with the provided id */
            Optional< Event > temp = eventRepo.findById( request.getEventID() );
            if ( temp.isPresent() ) {

                /* Create the decorated event */
                EventManager manager = new EventManager();
                EventComponent eventComponent = manager.buildEvent( temp.get() );

                /* Get the first geocode of the event */
                UUID nextGeocodeID = eventComponent.getGeocodeIDs().get( 0 );

                /* Create a new status object with the geocodeID set to the first geocode */
                Map< String, String > details = new HashMap<>();

                /*
                 * if the event is a Blockly event,
                 * add the block ids for the Event and set all the found flags to false
                 */
                if( eventComponent.isBlocklyEvent() ){
                    details.put("blocks", "");
                }

                status = new UserEventStatus( UUID.randomUUID(), eventComponent.getID(), currentUserID, nextGeocodeID, details );
                eventComponent.handleEventStart( status );

                /* Create the user's points if the event has a leaderboard */
                if ( eventComponent.getLeaderboards().size() > 0 ) {

                    try {

                        UUID leaderboardID = eventComponent.getLeaderboards().get( 0 ).getId();
                        CreatePointRequest pointRequest = new CreatePointRequest( 0, currentUserID, leaderboardID );
                        PointResponse pointResponse = leaderboardService.createPoint( pointRequest );

                        if ( !pointResponse.isSuccess() ) {

                            return new GetCurrentEventStatusResponse( false, pointResponse.getMessage(), null, null );
                        }

                    } catch ( NullRequestParameterException ignored ) {

                        return new GetCurrentEventStatusResponse( false, "Failed to create Point", null, null );
                    }
                }

                /* Save the event status in the repo once points have been made */
                userEventStatusRepo.save( status );

            } else {

                /* An event with the provided id was not found */
                return new GetCurrentEventStatusResponse( false, eventNotFoundMessage, null, null );
            }
        }

        UUID geocodeID = status.getGeocodeID();
        if ( geocodeID != null ) {

            try {

                GetGeoCodeResponse getGeoCodeResponse = geoCodeService.getGeoCode( new GetGeoCodeRequest( geocodeID ) );
                GeoCode geocode = getGeoCodeResponse.getFoundGeoCode();
                geocode.setCollectables(null);
                geocode.setHints(null);
                geocode.setCreatedBy(null);
                geocode.setQrCode(null);
                return new GetCurrentEventStatusResponse( true, "Status returned", status, geocode );
            } catch ( tech.geocodeapp.geocode.geocode.exceptions.InvalidRequestException e ) {

                return new GetCurrentEventStatusResponse( false, e.getMessage(), null, null );
            }

        } else {

            return new GetCurrentEventStatusResponse( true, "Status returned", status, null );
        }
    }

    /**
     * A function that moves a user to the next stage of an event, and updates their status and points accordingly.
     * This function will be called by other subsystems when an event geocode is found.
     *
     * @param foundGeocode the geocode that was just found, triggering the event change
     * @param userID the unique ID of the user entering the event
     *
     * @throws InvalidRequestException any of the provided parameters are null
     */
    @Override
    public void nextStage( GeoCode foundGeocode, UUID userID ) throws InvalidRequestException, NotFoundException, MismatchedParametersException {

        /* Validate the parameters */
        if ( ( foundGeocode == null ) || ( userID == null ) || ( foundGeocode.getEventID() == null ) ) {

            throw new InvalidRequestException();
        }

        /* Extract the event ID from the provided geocode */
        UUID eventID = foundGeocode.getEventID();

        /* Find the event with the id */
        Optional< Event > temp = eventRepo.findById( eventID );
        if ( temp.isEmpty() ) {

            /* An event with the provided id was not found */
            throw new NotFoundException( eventNotFoundMessage );
        }

        /* Create the decorated event */
        EventManager manager = new EventManager();
        EventComponent event = manager.buildEvent( temp.get() );

        /* Get the UserEventStatus object from the repository */
        UserEventStatus status = userEventStatusRepo.findStatusByEventIDAndUserID( event.getID(), userID );
        if ( status == null ) {

            throw new NotFoundException( "User is not participating in this event." );
        }

        if ( status.getGeocodeID() == null ) {

            /* User has already finished this event */
            return;

        } else if ( !status.getGeocodeID().equals( foundGeocode.getId() ) ) {

            /* The provided GeoCode is not the user's target */
            throw new MismatchedParametersException( "User was not searching for the given GeoCode" );
        }

        /* Find the user's current geocode in the list */
        List< UUID > ids = event.getGeocodeIDs();
        for ( int i = 0; i < ids.size(); i++ ) {

            if ( ids.get( i ).equals( status.getGeocodeID() ) ) {

                /* Edit the status and points using the event decorator */
                event.handleStageCompletion( i + 1, status );

                /* Set the next geocode id */
                if ( i == ids.size() - 1 ) {

                    /* User has just completed the final stage, end the event */
                    event.handleEventEnd( status );
                    status.setGeocodeID( null );
                } else {

                    /* Send the user to the next geocode */
                    UUID nextGeocodeID = ids.get( i + 1 );
                    status.setGeocodeID( nextGeocodeID );
                }

                /* Update the user's points if the event has a leaderboard */
                if ( event.getLeaderboards().size() > 0 ) {

                    try {

                        UUID leaderboardID = event.getLeaderboards().get( 0 ).getId();
                        GetPointForUserRequest userPointRequest = new GetPointForUserRequest( userID, leaderboardID );
                        PointResponse userPointResponse = leaderboardService.getPointForUser( userPointRequest );
                        Point point = userPointResponse.getPoint();

                        if ( point != null ) {

                            var pointsAchieved = event.calculatePoints( foundGeocode, i + 1, status );
                            point.setAmount( point.getAmount() + pointsAchieved );
                            leaderboardService.savePoint( point );
                        }

                    } catch ( NullRequestParameterException ignored ) {

                        throw new NotFoundException( "Failed to find the user's points" );
                    }
                }

                /* Save the event status in the repo once points have been edited */
                userEventStatusRepo.save( status );
                return;
            }
        }

        throw new NotFoundException( "The target GeoCode is not a part of the event with the provided ID." );
    }

    /**
     * Retrieve a list of Events that a user is participating in
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetEnteredEventsRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    public GetEnteredEventsResponse getEnteredEvents( GetEnteredEventsRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getUserID() == null ) {

            throw new InvalidRequestException();
        }

        List< Object[] > entries = eventRepo.findEnteredEvents( request.getUserID() );

        List< Map< String, Object > > formattedEntries = new ArrayList<>();
        for ( Object[] entry : entries ) {

            Map< String, Object > map = new HashMap<>();
            map.put( "event", entry[ 0 ] );
            map.put( "status", entry[ 1 ] );
            formattedEntries.add( map );
        }

        return new GetEnteredEventsResponse( true, "All entered Events returned", formattedEntries );
    }

    /**
     * Retrieve a list of Events around a certain radius of a location
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified EventsNearMeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public EventsNearMeResponse eventsNearMe( EventsNearMeRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getLocation() == null ) {

            throw new InvalidRequestException();
        }

        /* The list of Events within the radius */
        var foundEvents = new ArrayList< Event >();

        /* All the Events in the repository */
        var temp = eventRepo.findAll();

        /* Go through each Event in the repository */
        for ( Event event : temp ) {

            /* Calculate the distance from the given point to the Event using the Haversine formula */
            var distance = request.getLocation().distanceTo( event.getLocation() );

            /* Check if the event is close enough to the given point */
            if ( distance <= request.getRadius() ) {
                foundEvents.add( event.removePrivateDetails() );
            }
        }

        return new EventsNearMeResponse( true, "Events returned", foundEvents );
    }

    /**
     * Get all the stored Events in the repository
     *
     * @return the newly created response instance
     */
    @Override
    public GetAllEventsResponse getAllEvents() {

        var temp = eventRepo.findAll();

        /* remove the list of GeoCode IDs and the properties map from the events */
        for(var event: temp){
            event.removePrivateDetails();
        }

        return new GetAllEventsResponse( true, "All Events returned", temp );
    }


    /**
     * Change the availability of a specific Event object
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified ChangeAvailabilityRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public ChangeAvailabilityResponse changeAvailability( ChangeAvailabilityRequest request ) throws InvalidRequestException {

        /*

            ToDo maybe this should be called check Availability instead where it checks the date to the current

         */

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getEventID() == null ) {

            throw new InvalidRequestException();
        }

        return null;
    }

    /**
     * Get all the Events at a certain location
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetEventsByLocationResponse getEventsByLocation( GetEventsByLocationRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getLocation() == null ) {

            throw new InvalidRequestException();
        }

        /* The list of Events within the radius */
        var foundEvents = new ArrayList< Event >();

        /* All the Events in the repository */
        var temp = eventRepo.findAll();


        /* The location to look for */
        GeoPoint locate = request.getLocation();

        /* Go through each Event in the repository */
        for ( Event event : temp ) {

            /* Check if the value is within the max radius */
            if ( locate.equals( event.getLocation() ) ) {

                /*
                 * The current Event is the same
                 * therefore add it to the list
                 */
                foundEvents.add( event.removePrivateDetails() );
            }
        }

        return new GetEventsByLocationResponse( true, "Events returned", foundEvents );
    }

    /**
     * Getting the Blockly blocks for the current User for a Blockly Event
     *
     * @param request the attributes the response should be created from
     *
     * @return The Blockly blocks for the current User
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetBlocksResponse getBlocks(GetBlocksRequest request) throws InvalidRequestException {
        var checkEventAndUserResponse = checkEventAndUser(request, true, false);

        if(!checkEventAndUserResponse.isSuccess()){
            return new GetBlocksResponse(false, checkEventAndUserResponse.getMessage());
        }

        /* get the blocks that the User has from the UserEventStatus */
        var status = checkEventAndUserResponse.getStatus();
        var details = status.getDetails();
        var blocks = details.get("blocks");

        List< String > blockNames = new ArrayList<>(Arrays.asList(blocks.split("#")));//TODO: change to get w/ correct format (if format changes)
        return new GetBlocksResponse( true, "Blocks successfully returned", blockNames );
    }

    /**
     * Gets the input cases (the variables and their values) for the given Blockly Event
     *
     * @param request the attributes the response should be created from
     * @return The input cases for the Blockly Event
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetInputsResponse getInputs(GetInputsRequest request) throws InvalidRequestException {
        var checkEventAndUserResponse = checkEventAndUser(request, false, true);

        if(!checkEventAndUserResponse.isSuccess()){
            return new GetInputsResponse(false, checkEventAndUserResponse.getMessage());
        }

        /* return the inputs for the Event */
        var eventComponent = checkEventAndUserResponse.getEventComponent();
        var inputs = eventComponent.getInputs();

        return new GetInputsResponse(true, "Inputs successfully returned for the Blockly Event", inputs);
    }

    /**
     * Checks the output that the User's code generated (i.e the JavaScript code that was converted from the BLockly blocks)
     *
     * @param request the attributes the response should be created from
     *
     * @return The number of test cases that the User passed
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public CheckOutputResponse checkOutput(CheckOutputRequest request) throws InvalidRequestException {
        /* validate the request */
        if(request == null){
            throw new InvalidRequestException(true);
        }

        if(request.getEventID() == null || request.getOutputs() == null){
            throw new InvalidRequestException();
        }

        try{
            var event = eventRepo.findById(request.getEventID()).get();

            var eventManager = new EventManager();
            var eventComponent = eventManager.buildEvent(event);

            var userOutputs = request.getOutputs();
            var correctOutputs = eventComponent.getOutputs();

            /* count the number of test cases that the output matched on */
            var count = 0;
            var numTestCases = correctOutputs.length;

            for(int i = 0; i < numTestCases; ++i){
                if(!userOutputs.get(i).equals(correctOutputs[i])){
                    ++count;
                }
            }

            if(count < numTestCases){
                return new CheckOutputResponse(true, "You passed "+count+" out of the "+numTestCases+" test cases");
            }

            return new CheckOutputResponse(true, "You passed all of the test cases");
        } catch (Exception e) {
            e.printStackTrace();
            return new CheckOutputResponse(false, "Invalid request");
        }
    }

    //// Blockly Helper Functions ////

    /**
     * checks the given request is valid, the event is valid and that the user is participating in the given event
     *
     * @param request The request to check
     * @param needStatus Whether the calling function needs the UserEventStatus to be returned
     */
    private CheckEventAndUserResponse checkEventAndUser(GetEventDetailsByIDRequest request, boolean needStatus, boolean needEventComponent) throws InvalidRequestException {
        /* Validate the request */
        if( request == null ){
            throw new InvalidRequestException( true );
        }

        if( request.getEventID() == null ){
            throw new InvalidRequestException();
        }

        /* check if the eventID is invalid */
        boolean eventExists = eventRepo.existsById( request.getEventID() );

        if( !eventExists ){
            return new CheckEventAndUserResponse( false, eventNotFoundMessage );
        }

        /* check if the Event is not a BlocklyEvent */
        Event event = eventRepo.findById( request.getEventID() ).get();

        EventManager eventManager = new EventManager();
        EventComponent eventComponent = eventManager.buildEvent( event );

        if( !eventComponent.isBlocklyEvent() ){
            return new CheckEventAndUserResponse( false, "Event is not a Blockly Event" );
        }

        /* check if the User is not participating in the Blockly Event */

        /* Get the UserEventStatus object from the repository */
        UserEventStatus status = userEventStatusRepo.findStatusByEventIDAndUserID( request.getEventID(), CurrentUserDetails.getID() );

        if( status == null ){
            return new CheckEventAndUserResponse( false, "User is not participating in the Blockly Event" );
        }

        var response = new CheckEventAndUserResponse(true, "Valid request");

        /* only give the calling function the status if needed */
        if(needStatus){
            response.setStatus(status);
        }

        /* only give the calling function the eventComponent if needed */
        if(needEventComponent){
            response.setEventComponent(eventComponent);
        }

        return response;
    }

    /*---------- Post Construct services ----------*/

    /**
     * Post construct the GeoCode service, this avoids a circular dependency
     *
     * @param geoCodeService the service to be set
     */
    public void setGeoCodeService( GeoCodeService geoCodeService ) {

        this.geoCodeService = geoCodeService;
    }

    /*-------------------------------------*/


    /*---------- Helper Functions for creating an Event ----------*/

    /**
     * Gets a list of GeoCodes and sorts them according to their Difficulty
     *
     * @param geoCodes the list of GeoCodes to sort
     *
     * @return the sorted IDs of the provided GeoCodes in order of Difficulty
     */
    private List< UUID > sortByDifficulty( List< GeoCode > geoCodes ) {

        List< UUID > hold = null;

        if ( geoCodes != null ) {

            /* Apply Bubble sorting algorithm to get the objects in the correct order */

            /* Size of the list to sort */
            int n = geoCodes.size();

            for ( int i = 0; i < n - 1; i++ ) {

                for ( int j = 0; j < n - i - 1; j++ ) {

                    /* Hold the index of Difficulties */
                    var checkOne = getDifficultyIndex( geoCodes.get( j ).getDifficulty() );
                    var checkTwo = getDifficultyIndex( geoCodes.get( j + 1 ).getDifficulty() );

                    /* Check if the two positions needs to be swapped */
                    if ( checkOne > checkTwo ) {

                        /* Perform the swap */
                        var tempSwap = geoCodes.get( j );
                        geoCodes.set( j, geoCodes.get( j + 1 ) );
                        geoCodes.set( j + 1, tempSwap );
                    }
                }
            }

            hold = new ArrayList<>();

            /* Add the sorted GeoCode Objects ID's to the return list */
            for ( GeoCode geoCode : geoCodes ) {

                /* Get the ID from the current object */
                var geoCodeID = geoCode.getId();

                /* Valid the ID */
                if ( geoCodeID != null ) {

                    hold.add( geoCodeID );
                }
            }

        }

        return hold;
    }

    /**
     * Determines the index of importance of a Difficulty
     *
     * @param difficulty the Difficulty to search for
     *
     * @return the index of the Difficulty in the List
     */
    private int getDifficultyIndex( Difficulty difficulty ) {

        /* Current index */
        int x = 0;

        /* Get the order of difficulties */
        List< Difficulty > difficultyOrder = Difficulty.getDifficultyOrder();

        /* Go through each object in the list */
        for ( ; x < difficultyOrder.size(); x++ ) {

            /* Check if the current object matches the required one */
            if ( difficulty.equals( difficultyOrder.get( x ) ) ) {

                /* The current object is the index wanted */
                return x;
            }
        }

        return x;
    }

    /**
     * Gets a list of GeoCodes and sorts them according to their distance from one another
     *
     * @param geoCodes the list of GeoCode ID's to sort
     * @param startLocation the start location of the event
     *
     * @return the IDs of the provided GeoCodes, in the order that minimises the distance to travel between them
     */
    private List< UUID > sortByDistance( List< GeoCode > geoCodes, GeoPoint startLocation ) {

        if ( geoCodes != null ) {

            return Graph.getOptimalGeocodeIDOrder( geoCodes, startLocation );
        } else {

            return null;
        }
    }

    /*-------------------------------------*/

}