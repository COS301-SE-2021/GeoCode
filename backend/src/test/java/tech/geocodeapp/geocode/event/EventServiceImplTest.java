package tech.geocodeapp.geocode.event;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import tech.geocodeapp.geocode.event.pathfinder.Graph;
import tech.geocodeapp.geocode.event.exceptions.*;
import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.event.model.OrderLevels;
import tech.geocodeapp.geocode.event.repository.EventRepository;
import tech.geocodeapp.geocode.event.repository.UserEventStatusRepository;
import tech.geocodeapp.geocode.event.request.*;
import tech.geocodeapp.geocode.event.response.*;
import tech.geocodeapp.geocode.event.service.*;

import tech.geocodeapp.geocode.geocode.GeoCodeMockRepository;
import tech.geocodeapp.geocode.geocode.model.*;
import tech.geocodeapp.geocode.geocode.repository.GeoCodeRepository;
import tech.geocodeapp.geocode.geocode.request.CreateGeoCodeRequest;
import tech.geocodeapp.geocode.geocode.service.GeoCodeService;

import tech.geocodeapp.geocode.leaderboard.LeaderboardMockRepository;
import tech.geocodeapp.geocode.leaderboard.PointMockRepository;
import tech.geocodeapp.geocode.leaderboard.UserMockService;
import tech.geocodeapp.geocode.leaderboard.service.*;
import tech.geocodeapp.geocode.user.UserMockRepository;
import tech.geocodeapp.geocode.user.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

/**
 * This is the unit testing class for the Event subsystem
 *
 * Testing for if the requests, request attributes
 * and the repository are working in valid order
 * and what exceptions are thrown if not.
 */
@ExtendWith( MockitoExtension.class )
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
class EventServiceImplTest {

    /**
     * The service for the Event subsystem
     *
     * This is used to access the different use cases
     * needed for functionality
     */
    EventService eventService;

    /**
     * The mock repository for the Event subsystem
     * All the data will be saved here and is used to mock the JPA repository
     */
    EventRepository eventRepo;

    /**
     * The mock repository for the Event subsystem UserEventStatus repository
     * All the data will be saved here and is used to mock the JPA repository
     */
    UserEventStatusRepository userEventStatusRepo;

    /**
     * The leaderboard service accessor
     */
    @Mock( name = "leaderboardServiceImpl" )
    LeaderboardService leaderboardService;

    /**
     * The mock repository for the GeoCode subsystem repository
     * All the data will be saved here and is used to mock the JPA repository
     */
    GeoCodeRepository geoCodeMockRepo;

    /**
     * The mock repository for the User subsystem repository
     * All the data will be saved here and is used to mock the JPA repository
     */
    UserRepository userMockRepo;

    /**
     * The GeoCode service accessor
     */
    @Mock( name = "geoCodeServiceImpl" )
    GeoCodeService geoCodeService;

    /**
     * The expected exception message for if the given request has invalid attributes
     */
    String reqParamError = "The given request is missing parameter/s.";

    /**
     * The expected exception message for if the given request has invalid attributes
     */
    String reqEmptyError = "The given request is empty.";

    /**
     * This is used to have a static known UUID for Event objects
     */
    UUID eventID = UUID.fromString( "db91e6ee-f5b6-11eb-9a03-0242ac130003" );

    /**
     * This is used to have a static known UUID for a user ID
     */
    UUID userID = UUID.fromString( "2a72e0f9-86a7-4f38-9fda-5f31a8f3c421" );

    private CreateEventRequest createBlocklyEventRequest;
    private CreateEventResponse createBlocklyEventResponse;
    private HashMap<String, String> blocklyEventProperties;
    private UUID blocklyEventID;

    /**
     * Create the EventServiceImpl with the relevant repositories.
     *
     * This is done to ensure a repository with no data is created each time
     * and the service implementation contains fresh code that has not been affected
     * by some other test or data.
     */
    @BeforeEach
    void setup() {

        /* Create a new repository instance and make sure there is no data in it */
        eventRepo = new EventMockRepository();
        userEventStatusRepo = new UserEventStatusMockRepository();
        geoCodeMockRepo = new GeoCodeMockRepository();

        /* Clear all the repository data */
        eventRepo.deleteAll();
        userEventStatusRepo.deleteAll();
        geoCodeMockRepo.deleteAll();

        /* Create the leaderboard service with all the relevant repositories */
        var leaderboardMockRepo = new LeaderboardMockRepository();
        var userRepository = new UserMockRepository();
        var userService = new UserMockService( userRepository );
        var pointMockRepository = new PointMockRepository();
        leaderboardService = new LeaderboardServiceImpl( leaderboardMockRepo, pointMockRepository, userService );

        /* Create the GeoCode and User service */
        geoCodeService = new GeoCodeMockService( geoCodeMockRepo );

        try {

            /* Create a new EventServiceImpl instance to access the different use cases */
            eventService = new EventServiceImpl( eventRepo, userEventStatusRepo, leaderboardService );
            eventService.setGeoCodeService( geoCodeService );
        } catch ( RepoException e ) {

            /* An error occurred so print the stack trace */
            e.printStackTrace();
        }

    }

    /**
     * Check how the constructor handles the repository being null
     */
    @Test
    @Order( 1 )
    @Tag( "Tests" )
    @DisplayName( "Null repository handling - EventServiceImpl" )
    void RepositoryNullTest() {

        /* Null request check */
        assertThatThrownBy( () -> eventService = new EventServiceImpl( null, userEventStatusRepo, null ) )
                .isInstanceOf( RepoException.class )
                .hasMessageContaining( "The given repository does not exist." );
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 2 )
    @DisplayName( "Null repository handling - createEvent" )
    void createEventNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> eventService.createEvent( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 3 )
    @DisplayName( "Invalid repository attribute handling - createEvent" )
    void createEventInvalidRequestTest() {

        /*
         * Create a request object
         * and assign values to it
         */
        CreateEventRequest request = new CreateEventRequest();
        request.setDescription( "Try get as many as possible" );
        request.setLocation( null );
        request.setName( "Super Sport" );
        request.setBeginDate( LocalDate.parse( "2020-01-08" ) );
        request.setEndDate( null );

        /* Null parameter request check */
        assertThatThrownBy( () -> eventService.createEvent( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the createEvent use case test
     * complete successfully
     */
    @Test
    @Order( 4 )
    @DisplayName( "Valid request - createEvent" )
    void createEventTest() {

        try {

            /* Create mock geocodes to add to the event */
//            GeoCode gc1 = new GeoCode().id( UUID.randomUUID() );
//            GeoCode gc2 = new GeoCode().id( UUID.randomUUID() );
//            GeoCode gc3 = new GeoCode().id( UUID.randomUUID() );
//            geoCodeMockRepo.save( gc1 );
//            geoCodeMockRepo.save( gc2 );
//            geoCodeMockRepo.save( gc3 );

            /*
             * Create a request object
             * and assign values to it
             */
            CreateEventRequest request = new CreateEventRequest();
            request.setDescription( "Try get as many as possible" );
            request.setLocation( new GeoPoint( 10.2587, 40.336981 ) );
            request.setName( "Super Sport" );
            request.setBeginDate( LocalDate.parse( "2020-01-08" ) );
            request.setEndDate( LocalDate.parse( "2020-05-21" ) );

            List< CreateGeoCodeRequest > createGeoCodeRequests = new ArrayList<>();
            request.setCreateGeoCodesToFind( createGeoCodeRequests );

            request.setOrderBy( OrderLevels.GIVEN );
            request.setProperties( new HashMap<>() );

            /* Call the use case and keep the response */
            CreateEventResponse response = eventService.createEvent( request );

            /*
             * Check if the Event was created correctly
             * through checking the response
             */
            Assertions.assertTrue( response.isSuccess() );

        } catch ( InvalidRequestException e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 5 )
    @DisplayName( "Null repository handling - getEvent" )
    void getEventNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> eventService.getEvent( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 6 )
    @DisplayName( "Invalid repository attribute handling - getEvent" )
    void getEventInvalidRequestTest() {

        /*
         * Create a request object
         * and assign values to it
         */
        GetEventRequest request = new GetEventRequest();
        request.setEventID( null );

        /* Null parameter request check */
        assertThatThrownBy( () -> eventService.getEvent( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the getEvent use case test
     * complete successfully
     */
    @Test
    @Order( 7 )
    @DisplayName( "Valid request - getEvent" )
    void getEventTest() {

        try {

            /* Insert different random Events into the repository */
            populate( 3 );

            /* Populate with a known Event to find*/
            var event = new Event( eventID, "Test", "Test description", null,
                                   null, LocalDate.parse( "2020-01-08" ),
                                   LocalDate.parse( "2020-01-08" ), null, new HashMap<>() );

            /* Add the created Event to the repository */
            eventRepo.save( event );

            /*
             * Create a request object
             * and assign values to it
             */
            GetEventRequest request = new GetEventRequest();
            request.setEventID( eventID );

            /* Call the use case and save the response */
            GetEventResponse response = eventService.getEvent( request );

            /*
             * Check if the Event was created correctly found
             */
            Assertions.assertEquals( event, response.getFoundEvent() );
            Assertions.assertEquals( event.getDescription(), response.getFoundEvent().getDescription() );

            /* Check if the name is not the same as one of the random populated names */
            Assertions.assertNotEquals( "Super Sport", response.getFoundEvent().getName() );
        } catch ( InvalidRequestException e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 5 )
    @DisplayName( "Null repository handling - getCurrentEventStatus" )
    void getCurrentEventStatusNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> eventService.getCurrentEventStatus( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 6 )
    @DisplayName( "Invalid repository attribute handling - getCurrentEventStatus" )
    void getCurrentEventStatusInvalidRequestTest() {

        /*
         * Create a request object
         * and assign values to it
         */
        GetCurrentEventStatusRequest request = new GetCurrentEventStatusRequest();
        request.setEventID( null );
        request.setUserID( UUID.randomUUID() );

        /* Null parameter request check */
        assertThatThrownBy( () -> eventService.getCurrentEventStatus( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the getCurrentEventStatus use case test
     * complete successfully
     */
    @Test
    @Order( 7 )
    @DisplayName( "Valid request - getCurrentEventStatus" )
    void getCurrentEventStatusTest() {

        try {

            //ToDo finish this

            /* Insert different random Events into the repository */
            populate( 3 );

            /* Populate with a known Event to find*/
            var event = new Event( eventID, "Test", "Test description", null,
                                   null, LocalDate.parse( "2020-01-08" ),
                                   LocalDate.parse( "2020-01-08" ), null, new HashMap<>() );

            /* Add the created Event to the repository */
            eventRepo.save( event );

            GetCurrentEventStatusRequest request = new GetCurrentEventStatusRequest();
            request.setEventID( eventID );
            request.setUserID( null );

            var response = eventService.getCurrentEventStatus( request );

        } catch ( InvalidRequestException e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 5 )
    @DisplayName( "Null repository handling - nextStage" )
    void nextStageNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> eventService.nextStage( null, null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the nextStage use case test
     * complete successfully
     */
    @Test
    @Order( 7 )
    @DisplayName( "Valid request - nextStage" )
    void nextStageTest() {

        try {

            //ToDo finish this

            eventService.nextStage( null, null );
        } catch ( InvalidRequestException | NotFoundException | MismatchedParametersException e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 5 )
    @DisplayName( "Null repository handling - getEnteredEvents" )
    void getEnteredEventsNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> eventService.getEnteredEvents( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 6 )
    @DisplayName( "Invalid repository attribute handling - getEnteredEvents" )
    void getEnteredEventsInvalidRequestTest() {

        /*
         * Create a request object
         * and assign values to it
         */
        GetEnteredEventsRequest request = new GetEnteredEventsRequest();
        request.setUserID( null );

        /* Null parameter request check */
        assertThatThrownBy( () -> eventService.getEnteredEvents( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the getEnteredEvents use case test
     * complete successfully
     */
    @Test
    @Order( 7 )
    @DisplayName( "Valid request - getEnteredEvents" )
    void getEnteredEventsTest() {

        try {

            //ToDo finish this

            GetEnteredEventsRequest request = new GetEnteredEventsRequest();
            request.setUserID( null );

            var event = eventService.getEnteredEvents( request );
        } catch ( InvalidRequestException e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 5 )
    @DisplayName( "Null repository handling - eventsNearMe" )
    void eventsNearMeNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> eventService.eventsNearMe( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 6 )
    @DisplayName( "Invalid repository attribute handling - eventsNearMe" )
    void eventsNearMeInvalidRequestTest() {

        /*
         * Create a request object
         * and assign values to it
         */
        EventsNearMeRequest request = new EventsNearMeRequest();
        request.setLocation( null );
        request.setRadius( 0.0 );

        /* Null parameter request check */
        assertThatThrownBy( () -> eventService.eventsNearMe( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the getCurrentEventStatus use case test
     * complete successfully
     */
    @Test
    @Order( 7 )
    @DisplayName( "Valid request - eventsNearMe" )
    void eventsNearMeTest() {

        try {

            //ToDo finish this

            EventsNearMeRequest request = new EventsNearMeRequest();
            request.setLocation( null );
            request.setRadius( 0.0 );

            var event = eventService.eventsNearMe( request );
        } catch ( InvalidRequestException e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 5 )
    @DisplayName( "Null repository handling - getAllEvents" )
    void getAllEventsTest() {
        //ToDo finish this
        var event = eventService.getAllEvents();
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 5 )
    @DisplayName( "Null repository handling - changeAvailability" )
    void changeAvailabilityNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> eventService.changeAvailability( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 6 )
    @DisplayName( "Invalid repository attribute handling - changeAvailability" )
    void changeAvailabilityInvalidRequestTest() {

        /*
         * Create a request object
         * and assign values to it
         */
        ChangeAvailabilityRequest request = new ChangeAvailabilityRequest();
        request.setAvailability( null );
        request.setEventID( null );

        /* Null parameter request check */
        assertThatThrownBy( () -> eventService.changeAvailability( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the changeAvailability use case test
     * complete successfully
     */
    @Test
    @Order( 7 )
    @DisplayName( "Valid request - changeAvailability" )
    void changeAvailabilityTest() {

        try {
            //ToDo finish this
            ChangeAvailabilityRequest request = new ChangeAvailabilityRequest();
            request.setAvailability( null );
            request.setEventID( null );

            var event = eventService.changeAvailability( request );
        } catch ( InvalidRequestException e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 5 )
    @DisplayName( "Null repository handling - getEventsByLocation" )
    void getEventsByLocationNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> eventService.getEventsByLocation( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 6 )
    @DisplayName( "Invalid repository attribute handling - getEventsByLocation" )
    void getEventsByLocationInvalidRequestTest() {

        /*
         * Create a request object
         * and assign values to it
         */
        GetEventsByLocationRequest request = new GetEventsByLocationRequest();
        request.setLocation( null );

        /* Null parameter request check */
        assertThatThrownBy( () -> eventService.getEventsByLocation( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the getEventsByLocation use case test
     * complete successfully
     */
    @Test
    @Order( 7 )
    @DisplayName( "Valid request - getEventsByLocation" )
    void getEventsByLocationTest() {

        try {
            //ToDo finish this
            GetEventsByLocationRequest request = new GetEventsByLocationRequest();
            request.setLocation( null );

            var event = eventService.getEventsByLocation( request );
        } catch ( InvalidRequestException e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }
    }

    /**
     * Using valid data does the setGeoCodeService use case test
     * complete successfully
     */
    @Test
    @Order( 7 )
    @DisplayName( "Valid request - setGeoCodeService" )
    void setGeoCodeServiceTest() {
        //ToDo finish this
        eventService.setGeoCodeService( null );
    }

    @Test
    @Order( 9 )
    @DisplayName( "check sorting order of GeoCodes by distance" )
    void orderGeoCodes() {

        GeoCode gc1 = new GeoCode().id( UUID.randomUUID() ).location( new GeoPoint( 5, 0 ) ); //3rd
        GeoCode gc2 = new GeoCode().id( UUID.randomUUID() ).location( new GeoPoint( 2, 1 ) ); //2nd
        GeoCode gc3 = new GeoCode().id( UUID.randomUUID() ).location( new GeoPoint( 10, -1 ) ); //4th
        GeoCode gc4 = new GeoCode().id( UUID.randomUUID() ).location( new GeoPoint( 0, 0 ) ); //1st

        List< GeoCode > geoCodes = new ArrayList<>();
            geoCodes.add( gc1 );
            geoCodes.add( gc2 );
            geoCodes.add( gc3 );
            geoCodes.add( gc4 );
        GeoPoint start = new GeoPoint( 0, 0 );

        List< UUID > result = Graph.getOptimalGeocodeIDOrder( geoCodes, start );

        List< UUID > expected = new ArrayList<>();
            expected.add( gc4.getId() ); //1st
            expected.add( gc2.getId() ); //2nd
            expected.add( gc1.getId() ); //3rd
            expected.add( gc3.getId() ); //4th

        Assertions.assertEquals( expected, result );

    }

    @Test
    @Order( 10 )
    @DisplayName( "create blockly event - empty properties" )
    void createBlocklyEventEmptyProperties() {
        /*
         * Not specifying an extra properties for an Event means the Event is
         * a normal event
         */
        createBlocklyEventRequest();
        createBlocklyEventResponse();

        Assertions.assertTrue(createBlocklyEventResponse.isSuccess());
    }

    @Test
    @Order( 11 )
    @DisplayName( "create blockly event - no problem description" )
    void createBlocklyEventNoProblemDescription() {
        createBlocklyEventRequest();

        addBlocklyEventProperty("other", "");

        createBlocklyEventResponse();

        Assertions.assertFalse(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("Problem description not specified for the Blockly Event", createBlocklyEventResponse.getMessage());
    }

    @Test
    @Order( 12 )
    @DisplayName( "create blockly event - no testCases" )
    void createBlocklyEvent() {
        createBlocklyEventRequest();

        addBlocklyEventProperty("problem_description", "Print 'Hello World!' to the screen x times (without the quotes)");

        createBlocklyEventResponse();

        Assertions.assertFalse(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("Test cases were not specified for the Blockly Event", createBlocklyEventResponse.getMessage());
    }
    @Test
    @Order( 13 )
    @DisplayName( "create blockly event - no blocks" )
    void createBlocklyEventNoBlocks() {
        createBlocklyEventRequest();

        addBlocklyEventProperty("problem_description", "Print 'Hello World!' to the screen x times (without the quotes)");
        addBlocklyEventProperty("testCases", "");

        createBlocklyEventResponse();

        Assertions.assertFalse(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("Block types were not specified for the Blockly Event", createBlocklyEventResponse.getMessage());
    }

    @Test
    @Order( 14 )
    @DisplayName( "create blockly event - empty problem description" )
    void createBlocklyEventEmptyProblemDescription() {
        createBlocklyEventRequest();

        addBlocklyEventProperty("problem_description", "");
        addBlocklyEventProperty("testCases", "");
        addBlocklyEventProperty("blocks", "");

        createBlocklyEventResponse();

        Assertions.assertFalse(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("An empty problem description was given for the Blockly Event", createBlocklyEventResponse.getMessage());
    }

    @Test
    @Order( 15 )
    @DisplayName( "create blockly event - invalid format for the test cases" )
    void createBlocklyEventInvalidTestCaseFormat() {
        createBlocklyEventRequest();

        addBlocklyEventProperty("problem_description", "Print 'Hello World!' to the screen x times (without the quotes)");
        addBlocklyEventProperty("testCases", "");
        addBlocklyEventProperty("blocks", "");
        addBlocklyEventProperty("", "");

        createBlocklyEventResponse();

        Assertions.assertFalse(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("Invalid format for the test cases", createBlocklyEventResponse.getMessage());
    }

    @Test
    @Order( 16 )
    @DisplayName( "create blockly event - invalid format for the blocks" )
    void createBlocklyEventInvalidBlocksFormat() {
        createBlocklyEventRequest();

        addBlocklyEventProperty("problem_description", "Print 'Hello World!' to the screen x times (without the quotes)");
        setTestCases("valid1");
        addBlocklyEventProperty("blocks", "");

        createBlocklyEventResponse();

        Assertions.assertFalse(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("Invalid format for the blocks", createBlocklyEventResponse.getMessage());
    }

    @Test
    @Order( 17 )
    @DisplayName( "create blockly event - fewer blocks than stages (geocodes)" )
    void createBlocklyEventTooFewBlocks() {
        createBlocklyEventRequest();

        addBlocklyEventProperty("problem_description", "Print 'Hello World!' to the screen x times (without the quotes)");
        setTestCases("valid1");
        setBlocks("too_few_blocks");

        createBlocklyEventResponse();

        Assertions.assertFalse(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("The number of block types must be at least the number of GeoCodes in the Blockly Event", createBlocklyEventResponse.getMessage());
    }

    @Test
    @Order( 18 )
    @DisplayName( "create blockly event - inconsistent number of input values" )
    void createBlocklyEventInconsistentNumberOfInputs() {
        createBlocklyEventRequest();

        addBlocklyEventProperty("problem_description", "Print 'Hello World!' to the screen x times (without the quotes)");
        setTestCases("inconsistent_num_inputs");
        setBlocks("valid1");

        createBlocklyEventResponse();

        Assertions.assertFalse(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("The number of input values for each test case must be the same", createBlocklyEventResponse.getMessage());
    }

    @Test
    @Order( 19 )
    @DisplayName( "create blockly event - valid properties" )
    void createBlocklyEventValidProperties() {
        createValidBlocklyEvent();
    }

    @Test
    @Order( 20 )
    @DisplayName( "get blockly event" )
    void getBlocklyEvent() throws InvalidRequestException {
        createValidBlocklyEvent();

        var request = new GetEventRequest(blocklyEventID);
        var response = eventService.getEvent(request);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals("Event found", response.getMessage());

        var event = response.getFoundEvent();
        Assertions.assertNotNull(event);
        Assertions.assertNull(event.getGeocodeIDs());
        Assertions.assertNull(event.getProperties());
    }



    private void createValidBlocklyEvent(){
        createBlocklyEventRequest();

        addBlocklyEventProperty("problem_description", "Print 'Hello World!' to the screen x times (without the quotes)");
        setTestCases("valid1");
        setBlocks("valid1");

        createBlocklyEventResponse();

        Assertions.assertTrue(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("Event created", createBlocklyEventResponse.getMessage());
    }


    /*
    @Test
    @Order( 1 )
    @DisplayName( "create blockly event - " )
    void createBlocklyEvent() {
        createBlocklyEventRequest();

        addBlocklyEventProperty("problem_description", "Print 'Hello World!' to the screen x times (without the quotes)");
        addBlocklyEventProperty("testCases", "");
        addBlocklyEventProperty("blocks", "");
        addBlocklyEventProperty("", "");

        createBlocklyEventResponse();

        Assertions.assertFalse(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("", createBlocklyEventResponse.getMessage());
    }
     */

    ////////////////Helper functions////////////////

    /**
     * This function creates numerous Events to be used for testing.
     *
     * NOTE: the create function will need to be working with tests passing for this
     * helper function to be used
     */
    private void populate( int size ) {

        try {
            CreateEventRequest request = new CreateEventRequest();
            request.setDescription( "Try get as many as possible" );
            request.setLocation( new GeoPoint( 10.2587, 40.336981 ) );
            request.setName( "Super Sport" );
            request.setBeginDate( LocalDate.parse( "2020-01-08" ) );
            request.setEndDate( LocalDate.parse( "2020-05-21" ) );

            List< CreateGeoCodeRequest > createGeoCodeRequests = new ArrayList<>();
            request.setCreateGeoCodesToFind( createGeoCodeRequests );

            request.setOrderBy( OrderLevels.GIVEN );

            request.setProperties( new HashMap<>() );

            eventService.createEvent( request );

        } catch ( InvalidRequestException e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }

    }

    /**
     * Constructs a CreateEventRequest for a Blockly Event
     * The properties hash map is set to test data validity checks
     */
    private void createBlocklyEventRequest(){
        /* GeoCodes */
        var location = new GeoPoint(-25.75, 28.23);

        var createBlocklyGeoCodes = new ArrayList<CreateGeoCodeRequest>();

        for(int i = 0; i < 3; ++i){
            var createGeoCodeRequest = new CreateGeoCodeRequest();
            createGeoCodeRequest.setId(UUID.randomUUID());
            createGeoCodeRequest.setDescription("GeoCode "+(i+1));
            createGeoCodeRequest.setLocation(location);
            createGeoCodeRequest.setHints(new ArrayList<>(List.of("Map")));
            createGeoCodeRequest.setDifficulty(Difficulty.EASY);
            createGeoCodeRequest.setAvailable(true);

            createBlocklyGeoCodes.add(createGeoCodeRequest);
        }

        createBlocklyEventRequest = new CreateEventRequest();
        createBlocklyEventRequest.setName("Open Day");
        createBlocklyEventRequest.setDescription("UP Open Day");

        createBlocklyEventRequest.setLocation(location);
        createBlocklyEventRequest.setBeginDate(LocalDate.now());
        createBlocklyEventRequest.setEndDate(LocalDate.now().plusDays(1));
        createBlocklyEventRequest.setCreateGeoCodesToFind(createBlocklyGeoCodes);
        createBlocklyEventRequest.setOrderBy(OrderLevels.GIVEN);
        createBlocklyEventRequest.setAvailable(true);

        blocklyEventProperties = new HashMap<>();
    }

    /**
     * Constructs a CreateEventResponse for a Blockly Event
     */
    private void createBlocklyEventResponse(){
        try {
            createBlocklyEventRequest.setProperties(blocklyEventProperties);
            createBlocklyEventResponse = eventService.createEvent(createBlocklyEventRequest);

            blocklyEventID = createBlocklyEventResponse.getEventID();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add to the Blockly Event's properties
     * @param key The property's key
     * @param value The property's value
     */
    private void addBlocklyEventProperty(String key, String value){
        blocklyEventProperties.put(key, value);
    }

    private void setPropertyFromFile(String property, String fileName){
        try {
            String contents = new String(Files.readAllBytes(Paths.get("src/test/java/tech/geocodeapp/geocode/event/blockly/"+property+"/"+fileName+".json")));
            //System.out.println(contents);
            addBlocklyEventProperty(property, contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the Blockly Event's test cases to the given test case file
     * @param fileName The file name (without the .json extension)
     */
    private void setTestCases(String fileName){
        setPropertyFromFile("testCases", fileName);
    }

    /**
     * Sets the Blockly Event's block types
     * @param fileName The file name (without the .json extension)
     */
    private void setBlocks(String fileName){
        setPropertyFromFile("blocks", fileName);
    }


}
