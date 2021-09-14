package tech.geocodeapp.geocode.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tech.geocodeapp.geocode.GeoCodeApplication;
import tech.geocodeapp.geocode.event.blockly.Block;
import tech.geocodeapp.geocode.event.blockly.TestCase;
import tech.geocodeapp.geocode.event.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.event.exceptions.MismatchedParametersException;
import tech.geocodeapp.geocode.event.exceptions.NotFoundException;
import tech.geocodeapp.geocode.event.exceptions.RepoException;
import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.event.model.OrderLevels;
import tech.geocodeapp.geocode.event.pathfinder.Graph;
import tech.geocodeapp.geocode.event.repository.EventRepository;
import tech.geocodeapp.geocode.event.repository.UserEventStatusRepository;
import tech.geocodeapp.geocode.event.request.*;
import tech.geocodeapp.geocode.event.response.CreateEventResponse;
import tech.geocodeapp.geocode.event.response.GetEventResponse;
import tech.geocodeapp.geocode.event.service.EventService;
import tech.geocodeapp.geocode.event.service.EventServiceImpl;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.general.security.CurrentUserDetails;
import tech.geocodeapp.geocode.geocode.model.Difficulty;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.geocode.repository.GeoCodeRepository;
import tech.geocodeapp.geocode.geocode.request.CreateGeoCodeRequest;
import tech.geocodeapp.geocode.geocode.request.GetCollectablesInGeoCodeByQRCodeRequest;
import tech.geocodeapp.geocode.geocode.request.GetGeoCodeRequest;
import tech.geocodeapp.geocode.geocode.service.GeoCodeService;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;
import tech.geocodeapp.geocode.user.repository.UserRepository;
import tech.geocodeapp.geocode.user.request.HandleLoginRequest;
import tech.geocodeapp.geocode.user.service.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * This is the integration testing class for the Event subsystem
 *
 * Testing for if the requests, request attributes
 * and the repository are working in valid order
 * and what exceptions are thrown if not.
 */
@SpringBootTest( classes = GeoCodeApplication.class,
                 webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
class EventServiceImplIT {

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
    @Autowired
    EventRepository eventRepo;

    /**
     * The mock repository for the Event subsystem UserEventStatus repository
     * All the data will be saved here and is used to mock the JPA repository
     */
    @Autowired
    UserEventStatusRepository userEventStatusRepo;

    /**
     * The leaderboard service accessor
     */
    @Autowired
    LeaderboardService leaderboardService;

    /**
     * The repository for the GeoCode subsystem repository
     * All the data will be saved here and is used to mock the JPA repository
     */
    @Autowired
    GeoCodeRepository geoCodeRepo;

    /**
     * The mock repository for the User subsystem repository
     * All the data will be saved here and is used to mock the JPA repository
     */
    UserRepository userRepo;

    /**
     * The GeoCode service accessor
     */
    @Autowired
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
     * This is used to have a static known UUID
     */
    UUID eventID = UUID.fromString( "db91e6ee-f5b6-11eb-9a03-0242ac130003" );

    @Autowired
    UserService userService;

    private CreateEventRequest createBlocklyEventRequest;
    private CreateEventResponse createBlocklyEventResponse;
    private HashMap<String, String> blocklyEventProperties;
    private UUID blocklyEventID;

    private final String eventNotFoundMessage = "Event not found";
    private String notBlocklyEventMsg = "Event is not a Blockly Event";

    private UUID user1ID;
    private GeoCode firstGeoCode;
    private int numBlocklyGeoCodes;
    private List<UUID> blocklyGeoCodeIDs;
    private int numBlocklyBlocks;

    private UUID blocklyGeoCodeID;

    /**
     * Create the EventServiceImpl with the relevant repositories.
     *
     * This is done to ensure a repository with no data is created each time
     * and the service implementation contains fresh code that has not been affected
     * by some other test or data.
     */
    @BeforeEach
    void setup() {

        /* Clear all the repository data */
        eventRepo.deleteAll();

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
    @DisplayName( "Null request handling - createEvent" )
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
    @Transactional
    void createEventTest() {

        try {

            /* Create GeoCodes to add to the event */
            List<CreateGeoCodeRequest> createGeoCodeRequests = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                var createGeoCodeRequest = new CreateGeoCodeRequest( "", new GeoPoint(0, 0), new ArrayList<String>(), Difficulty.EASY, true );
                createGeoCodeRequests.add(createGeoCodeRequest);
            }

            /*
             * Create a request object
             * and assign values to it
             */
            var userId = handleAdminLogin("admin_user");

            CreateEventRequest request = new CreateEventRequest();
            request.setDescription( "Try get as many as possible" );
            request.setLocation( new GeoPoint( 10.2587, 40.336981 ) );
            request.setName( "Super Sport" );
            request.setBeginDate( LocalDate.parse( "2020-01-08" ) );
            request.setEndDate( LocalDate.parse( "2020-05-21" ) );
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
    @DisplayName( "Null request handling - getEvent" )
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
    @Transactional
    void getEventTest() {

        try {

            /* Insert different random Events into the repository */
            var userId = handleAdminLogin("admin_user");
            populate( 3 );

            /* Populate with a known Event to find*/
            var event = new Event( eventID, "Test", "Test description", new GeoPoint(0, 0),
                                   new ArrayList<>(), LocalDate.parse( "2020-01-08" ),
                                   LocalDate.parse( "2020-01-08" ), new ArrayList<>(), new HashMap<>() );

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
            var foundEvent = response.getFoundEvent();
            Assertions.assertEquals(event.getId(), foundEvent.getId());
            Assertions.assertEquals(event.getName(), foundEvent.getName());
            Assertions.assertEquals( event.getDescription(), foundEvent.getDescription() );

            /* Check if the name is not the same as one of the random populated names */
            Assertions.assertNotEquals( "Super Sport", foundEvent.getName() );
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
    @DisplayName( "Null request handling - getCurrentEventStatus" )
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
    @Transactional
    void getCurrentEventStatusTest() {

        try {


            /* Insert different random Events into the repository */
            var user1ID = handleLogin(UUID.randomUUID(), "testUser", false);

            eventID = createEvent();

            joinEvent(eventID, user1ID);

            GetCurrentEventStatusRequest request = new GetCurrentEventStatusRequest();
            request.setEventID( eventID );
            request.setUserID(user1ID);

            var response = eventService.getCurrentEventStatus( request );

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Status returned", response.getMessage());
            Assertions.assertEquals(eventID, response.getStatus().getEventID());
            Assertions.assertEquals(user1ID, response.getStatus().getUserID());

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
    @DisplayName( "Null parameter handling - nextStage" )
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
    @Transactional
    void nextStageTest() {

        try {

            handleLogin(UUID.randomUUID(), "testingUser", true);
            eventID = createEvent();
            var user1ID = handleLogin(UUID.randomUUID(), "user1", false);
            joinEvent(eventID, user1ID);
            try {
                firstGeoCode = geoCodeService.getGeoCode(new GetGeoCodeRequest(eventRepo.findById(eventID).get().getGeocodeIDs().get(0))).getFoundGeoCode();
                eventService.nextStage( firstGeoCode, user1ID );
            } catch (tech.geocodeapp.geocode.geocode.exceptions.InvalidRequestException e) {
                e.printStackTrace();
            }
        } catch ( InvalidRequestException | NotFoundException | MismatchedParametersException e ) {
            Assertions.fail("should not throw an exception");
            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 5 )
    @DisplayName( "Null request handling - getEnteredEvents" )
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
    @Transactional
    void getEnteredEventsTest() {


        try {
            user1ID = handleLogin(UUID.randomUUID(), "userAdmin", true);
            eventID= createEvent();

            GetEnteredEventsRequest request = new GetEnteredEventsRequest();

            user1ID = handleLogin(UUID.randomUUID(), "user1", false);
            joinEvent(eventID, user1ID);

            request.setUserID(user1ID);

            var event = eventService.getEnteredEvents( request );

            Assertions.assertTrue(event.isSuccess());
            Assertions.assertEquals("All entered Events returned", event.getMessage());
            Assertions.assertEquals(1, event.getEntries().size());
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
    @DisplayName( "Null request handling - eventsNearMe" )
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
    @Transactional
    void eventsNearMeTest() {

        try {

            EventsNearMeRequest request = new EventsNearMeRequest();
            GeoPoint location = new GeoPoint( 10.2588, 40.336981 );
            request.setLocation(location);
            request.setRadius( 50.0 );
            handleLogin(UUID.randomUUID(), "test", false);
            populate(1);
            var event = eventService.eventsNearMe( request );

            Assertions.assertTrue(event.isSuccess());
            Assertions.assertEquals("Events returned", event.getMessage());
            Assertions.assertEquals(1, event.getFoundEvents().size());
            Assertions.assertEquals("Super Sport", event.getFoundEvents().get(0).getName());
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
    @DisplayName( "Null request handling - changeAvailability" )
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
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 5 )
    @DisplayName( "Null request handling - getEventsByLocation" )
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

        List< GeoCode > geoCodes = new ArrayList< GeoCode >();
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
    @DisplayName( "create blockly event - empty properties" )
    @Transactional
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
    @DisplayName( "create blockly event - no problem description" )
    void createBlocklyEventNoProblemDescription() {
        createBlocklyEventRequest();

        addBlocklyEventProperty("other", "");

        createBlocklyEventResponse();

        Assertions.assertFalse(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("Problem description not specified for the Blockly Event", createBlocklyEventResponse.getMessage());
    }

    @Test
    @DisplayName( "create blockly event - no testCases" )
    void createBlocklyEventNoTestCases() {
        createBlocklyEventRequest();

        addBlocklyEventProperty("problemDescription", "Print 'Hello World!' to the screen x times (without the quotes)");

        createBlocklyEventResponse();

        Assertions.assertFalse(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("Test cases were not specified for the Blockly Event", createBlocklyEventResponse.getMessage());
    }
    @Test
    @DisplayName( "create blockly event - no blocks" )
    void createBlocklyEventNoBlocks() {
        createBlocklyEventRequest();

        addBlocklyEventProperty("problemDescription", "Print 'Hello World!' to the screen x times (without the quotes)");
        addBlocklyEventProperty("testCases", "");

        createBlocklyEventResponse();

        Assertions.assertFalse(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("Block types were not specified for the Blockly Event", createBlocklyEventResponse.getMessage());
    }

    @Test
    @DisplayName( "create blockly event - empty problem description" )
    void createBlocklyEventEmptyProblemDescription() {
        createBlocklyEventRequest();

        addBlocklyEventProperty("problemDescription", "");
        addBlocklyEventProperty("testCases", "");
        addBlocklyEventProperty("blocks", "");

        createBlocklyEventResponse();

        Assertions.assertFalse(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("An empty problem description was given for the Blockly Event", createBlocklyEventResponse.getMessage());
    }

    @Test
    @DisplayName( "create blockly event - invalid format for the test cases" )
    void createBlocklyEventInvalidTestCaseFormat() {
        createBlocklyEventRequest();

        addBlocklyEventProperty("problemDescription", "Print 'Hello World!' to the screen x times (without the quotes)");
        addBlocklyEventProperty("testCases", "");
        addBlocklyEventProperty("blocks", "");
        addBlocklyEventProperty("", "");

        createBlocklyEventResponse();

        Assertions.assertFalse(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("Invalid format for the test cases", createBlocklyEventResponse.getMessage());
    }

    @Test
    @DisplayName( "create blockly event - invalid format for the blocks" )
    void createBlocklyEventInvalidBlocksFormat() {
        createBlocklyEventRequest();

        addBlocklyEventProperty("problemDescription", "Print 'Hello World!' to the screen x times (without the quotes)");
        setTestCases("valid1");
        addBlocklyEventProperty("blocks", "");

        createBlocklyEventResponse();

        Assertions.assertFalse(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("Invalid format for the blocks", createBlocklyEventResponse.getMessage());
    }

    @Test
    @DisplayName( "create blockly event - fewer blocks than stages (geocodes)" )
    void createBlocklyEventTooFewBlocks() {
        createBlocklyEventRequest();

        addBlocklyEventProperty("problemDescription", "Print 'Hello World!' to the screen x times (without the quotes)");
        setTestCases("valid1");
        setBlocks("too_few_blocks");

        createBlocklyEventResponse();

        Assertions.assertFalse(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("The number of block types must be at least the number of GeoCodes in the Blockly Event", createBlocklyEventResponse.getMessage());
    }

    @Test
    @DisplayName( "create blockly event - inconsistent number of input values" )
    void createBlocklyEventInconsistentNumberOfInputs() {
        createBlocklyEventRequest();

        addBlocklyEventProperty("problemDescription", "Print 'Hello World!' to the screen x times (without the quotes)");
        setTestCases("inconsistent_num_inputs");
        setBlocks("valid1");

        createBlocklyEventResponse();

        Assertions.assertTrue(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("Event created", createBlocklyEventResponse.getMessage());
    }

    @Test
    @DisplayName( "create blockly event - valid properties" )
    @Transactional
    void createBlocklyEventValidProperties() {
        createValidBlocklyEvent();
    }

    @Test
    @DisplayName( "get blockly event" )
    @Transactional
    void getBlocklyEvent() throws InvalidRequestException {
        createValidBlocklyEvent();

        var request = new GetEventRequest(blocklyEventID);
        var response = eventService.getEvent(request);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals("Event found", response.getMessage());

        var event = response.getFoundEvent();
        Assertions.assertNotNull(event);
        Assertions.assertTrue(event.getGeocodeIDs().isEmpty());
        Assertions.assertTrue(event.getProperties().isEmpty());
    }

    @Test
    @DisplayName( "Null request handling - getInputs" )
    void getInputsNullRequest(){
        /* Null request check */
        assertThatThrownBy( () -> eventService.getInputs( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    @Test
    @DisplayName( "get blockly inputs - null event id" )
    void getInputsNullEventID(){
        /* Null parameter check */
        var request = new GetInputsRequest();
        request.setEventID(null);

        assertThatThrownBy( () -> eventService.getInputs( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessage( "The given request is missing parameter/s." );
    }

    @Test
    @DisplayName( "get blockly inputs - invalid event id" )
    void getInputsInvalidEventID() throws InvalidRequestException {
        var request = new GetInputsRequest();
        request.setEventID(UUID.randomUUID());

        var response = eventService.getInputs(request);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(eventNotFoundMessage, response.getMessage());
    }

    @Test
    @DisplayName( "get blockly inputs - not a blockly event" )
    @Transactional
    void getInputsNotBlocklyEvent() throws InvalidRequestException {
        var adminID = handleAdminLogin("adminUser");
        var id = createEvent();

        var request = new GetInputsRequest();
        request.setEventID(id);

        var response = eventService.getInputs(request);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals("Event is not a Blockly Event", response.getMessage());
    }

    @Test
    @DisplayName( "get blockly inputs - user not participating in the blockly event" )
    @Transactional
    void getInputsUserNotInEvent() throws InvalidRequestException {
        createValidBlocklyEvent();
        handleUserLogin("user1");

        var request = new GetInputsRequest();
        request.setEventID(blocklyEventID);

        var response = eventService.getInputs(request);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals("User is not participating in the Blockly Event", response.getMessage());
    }

    @Test
    @DisplayName( "get blockly inputs - user is participating in the blockly event" )
    @Transactional
    void getInputs() throws InvalidRequestException, IOException {
        user1InBlocklyEvent();

        var request = new GetInputsRequest();
        request.setEventID(blocklyEventID);

        var response = eventService.getInputs(request);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals("Inputs successfully returned for the Blockly Event", response.getMessage());

        /* check that the input values were stored correctly and have not been modified since then */
        var objectMapper = new ObjectMapper();
        var testCases = objectMapper.readValue(readFile("testCases", "valid1"), TestCase[].class);

        int numTestCases = testCases.length;
        String[][] inputs = new String[numTestCases][];

        for(int i = 0; i < numTestCases; ++i){
            inputs[i] = testCases[i].getInputs();
        }

        Assertions.assertTrue(Arrays.deepEquals(inputs, response.getInputs()));
    }

    @Test
    @DisplayName("Null request handling - checkOutput")
    @Transactional
    void checkOutputNullRequest(){
        /* Null request check */
        assertThatThrownBy( () -> eventService.checkOutput( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    @Test
    @DisplayName("check output - null event id")
    @Transactional
    void checkOutputNullEventID(){
        /* Null parameter check */
        var request = new CheckOutputRequest();
        request.setEventID(null);

        assertThatThrownBy( () -> eventService.checkOutput( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    @Test
    @DisplayName("check output - null output")
    @Transactional
    void checkOutputNullOutput(){
        /* Null parameter check */
        var request = new CheckOutputRequest();
        request.setEventID(blocklyEventID);
        request.setOutputs(null);

        assertThatThrownBy( () -> eventService.checkOutput( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    @Test
    @DisplayName("check output - invalid event id")
    @Transactional
    void checkOutputInvalidEventID() throws InvalidRequestException {
        var request = new CheckOutputRequest();
        request.setEventID(UUID.randomUUID());
        request.setOutputs(new ArrayList<>());

        var response = eventService.checkOutput(request);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(eventNotFoundMessage, response.getMessage());
    }

    @Test
    @DisplayName("check output - not a blockly event")
    @Transactional
    void checkOutputNotABlocklyEvent() throws InvalidRequestException {
        var adminID = handleAdminLogin("adminUser");
        var id = createEvent();

        var request = new CheckOutputRequest();
        request.setEventID(id);
        request.setOutputs(new ArrayList<>());

        var response = eventService.checkOutput(request);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(notBlocklyEventMsg, response.getMessage());
    }

    @Test
    @DisplayName("check output - too few output test cases")
    @Transactional
    void checkOutputTooFewOutputTestCases() throws InvalidRequestException {
        user1InBlocklyEvent();

        var request = new CheckOutputRequest();
        request.setEventID(blocklyEventID);

        /* only 1 output case instead of the full 2 */
        request.setOutputs(new ArrayList<>(List.of("output1")));

        var response = eventService.checkOutput(request);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals("The number of output test cases provided was incorrect", response.getMessage());
    }

    @Test
    @DisplayName("check output - too many output test cases")
    @Transactional
    void checkOutputTooManyOutputTestCases() throws InvalidRequestException {
        user1InBlocklyEvent();

        var request = new CheckOutputRequest();
        request.setEventID(blocklyEventID);

        /* only 1 output case instead of the full 2 */
        request.setOutputs(new ArrayList<>(Arrays.asList("output1", "output2", "output3")));

        var response = eventService.checkOutput(request);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals("The number of output test cases provided was incorrect", response.getMessage());
    }

    @Test
    @DisplayName("check output - passed no test cases")
    @Transactional
    void checkOutputPassedNone() throws InvalidRequestException {
        user1InBlocklyEvent();

        var request = new CheckOutputRequest();
        request.setEventID(blocklyEventID);

        var outputs = new ArrayList<>(Arrays.asList("output1", "output2"));
        var numTestCases = outputs.size();

        request.setOutputs(outputs);

        var response = eventService.checkOutput(request);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals("You passed 0 out of the "+numTestCases+" test cases", response.getMessage());
    }

    @Test
    @DisplayName("check output - passed some test cases")
    @Transactional
    void checkOutputPassedSome() throws InvalidRequestException, IOException {
        user1InBlocklyEvent();

        var request = new CheckOutputRequest();
        request.setEventID(blocklyEventID);

        var outputs = new ArrayList<>(Arrays.asList("abc", "output2"));
        var numTestCases = outputs.size();

        request.setOutputs(outputs);

        var response = eventService.checkOutput(request);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals("You passed 1 out of the "+numTestCases+" test cases", response.getMessage());
    }

    @Test
    @DisplayName("check output - passed all test cases")
    @Transactional
    void checkOutputPassedAll() throws InvalidRequestException, IOException {
        user1InBlocklyEvent();

        var request = new CheckOutputRequest();
        request.setEventID(blocklyEventID);

        var outputs = new ArrayList<>(Arrays.asList("abc", "test"));
        var numTestCases = outputs.size();

        request.setOutputs(outputs);

        var response = eventService.checkOutput(request);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals("You passed all of the test cases", response.getMessage());
    }

    @Test
    @DisplayName("get blockly event status - just started")
    @Transactional
    void getBlocklyCurrentEventStatusStarted() throws InvalidRequestException, NotFoundException, MismatchedParametersException, tech.geocodeapp.geocode.geocode.exceptions.InvalidRequestException {
        user1InBlocklyEvent();

        firstGeoCode = geoCodeService.getGeoCode(new GetGeoCodeRequest(blocklyGeoCodeIDs.get(0))).getFoundGeoCode();

        geoCodeService.getCollectablesInGeoCodeByQRCode(new GetCollectablesInGeoCodeByQRCodeRequest("GeoCode 1", firstGeoCode.getId()));

        var request = new GetCurrentEventStatusRequest(blocklyEventID, user1ID);
        var response = eventService.getCurrentEventStatus(request);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals("Status returned", response.getMessage());
        Assertions.assertNotNull(response.getTargetGeocode());

        var status = response.getStatus();
        Assertions.assertNotNull(status);
    }

    @Test
    @DisplayName("get blockly event status - found 1 GeoCode")
    @Transactional
    void getBlocklyCurrentEventStatusFoundOneGeoCode() throws NotFoundException, InvalidRequestException, tech.geocodeapp.geocode.geocode.exceptions.InvalidRequestException, MismatchedParametersException {
        getBlocklyCurrentEventStatusStarted();

        eventService.nextStage(firstGeoCode, CurrentUserDetails.getID());

        var request = new GetCurrentEventStatusRequest(blocklyEventID, user1ID);
        var response = eventService.getCurrentEventStatus(request);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals("Status returned", response.getMessage());
        Assertions.assertNotNull(response.getTargetGeocode());

        var status = response.getStatus();
        Assertions.assertNotNull(status);

        var details = status.getDetails();

        Assertions.assertTrue(details.containsKey("blocks"));

        var blockTypes = details.get("blocks").split("#");

        var correctNumBlocks = numBlocklyBlocks/numBlocklyGeoCodes;

        if(numBlocklyBlocks % numBlocklyGeoCodes != 0){
            ++correctNumBlocks;
        }

        Assertions.assertEquals(correctNumBlocks, blockTypes.length);
    }

    @Test
    @DisplayName("get blockly event status - found all GeoCodes")
    @Transactional
    void getBlocklyCurrentEventStatusFoundAllGeoCodes() throws NotFoundException, InvalidRequestException, tech.geocodeapp.geocode.geocode.exceptions.InvalidRequestException, MismatchedParametersException {
        getBlocklyCurrentEventStatusStarted();

        eventService.nextStage(firstGeoCode, CurrentUserDetails.getID());

        var secondGeoCode = geoCodeService.getGeoCode(new GetGeoCodeRequest(blocklyGeoCodeIDs.get(1))).getFoundGeoCode();
        var thirdGeoCode = geoCodeService.getGeoCode(new GetGeoCodeRequest(blocklyGeoCodeIDs.get(2))).getFoundGeoCode();

        eventService.nextStage(secondGeoCode, CurrentUserDetails.getID());
        eventService.nextStage(thirdGeoCode, CurrentUserDetails.getID());

        var request = new GetCurrentEventStatusRequest(blocklyEventID, user1ID);
        var response = eventService.getCurrentEventStatus(request);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals("Status returned", response.getMessage());
        Assertions.assertNull(response.getTargetGeocode());

        var status = response.getStatus();
        Assertions.assertNotNull(status);

        var details = status.getDetails();

        Assertions.assertTrue(details.containsKey("blocks"));

        var blockTypes = details.get("blocks").split("#");

        Assertions.assertEquals(numBlocklyBlocks, blockTypes.length);
    }


    private void user1InBlocklyEvent(){
        var adminID = handleAdminLogin("adminUser");

        createValidBlocklyEvent();
        user1ID = handleUserLogin("user1");

        joinEvent(blocklyEventID, user1ID);
    }

    private void joinEvent(UUID eventID, UUID userID){
        Assertions.assertNotNull(eventID);
        Assertions.assertNotNull(userID);

        /*
        getTheEventStatus to make sure the EventStatus is saved so that when calling swapCollectables
        the User is participating in the Event
        */
        try {
            var getCurrentEventStatusRequest = new GetCurrentEventStatusRequest(eventID, userID);
            var getCurrentEventStatusResponse = eventService.getCurrentEventStatus(getCurrentEventStatusRequest);

            Assertions.assertTrue(getCurrentEventStatusResponse.isSuccess());
            Assertions.assertEquals("Status returned", getCurrentEventStatusResponse.getMessage());
        } catch (tech.geocodeapp.geocode.event.exceptions.InvalidRequestException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mocks the User logging in
     * @param userID The id of the User to be set in setCurrentUserID
     */
    private void setUser(UUID userID, String username, boolean isAdmin){
        CurrentUserDetails.injectUserDetails(userID, username, isAdmin);
    }

    private UUID handleUserLogin(String username){
        return handleLogin(UUID.randomUUID(), username, false);
    }

    private UUID handleAdminLogin(String username){
        return handleLogin(UUID.randomUUID(), username, true);
    }

    private UUID handleLogin(UUID userID, String username, boolean isAdmin){
        try {
            setUser(userID, username, isAdmin);
            var response = userService.handleLogin(new HandleLoginRequest(new GeoPoint(0.0, 0.0)));

            Assertions.assertEquals("New User registered", response.getMessage());
            Assertions.assertTrue(response.isSuccess());

            return userID;
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createValidBlocklyEvent(){
        createBlocklyEventRequest();

        addBlocklyEventProperty("problemDescription", "Print 'Hello World!' to the screen x times (without the quotes)");
        setTestCases("valid1");
        setBlocks("valid1");

        var objectMapper = new ObjectMapper();

        try {
            numBlocklyBlocks = objectMapper.readValue(readFile("blocks", "valid1"), Block[].class).length;
        } catch (IOException e) {
            e.printStackTrace();
        }

        createBlocklyEventResponse();

        Assertions.assertTrue(createBlocklyEventResponse.isSuccess());
        Assertions.assertEquals("Event created", createBlocklyEventResponse.getMessage());
    }

    ////////////////Helper functions////////////////

    /**
     * This function creates numerous Events to be used for testing.
     *
     * NOTE: the create function will need to be working with tests passing for this
     * helper function to be used
     */
    private void populate( int size ) {

        try {
            for(int i = 0 ; i < size; ++i){
                createEvent();
            }
        } catch ( InvalidRequestException e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }

    }

    private UUID createEvent() throws InvalidRequestException {
        var request = new CreateEventRequest();
        request.setDescription( "Try get as many as possible" );
        request.setLocation( new GeoPoint( 10.2587, 40.336981 ) );
        request.setName( "Super Sport" );
        request.setBeginDate( LocalDate.parse( "2020-01-08" ) );
        request.setEndDate( LocalDate.parse( "2020-05-21" ) );

        List< CreateGeoCodeRequest > createGeoCodeRequests = new ArrayList<>();

        createGeoCodeRequests.add(new CreateGeoCodeRequest("", new GeoPoint(0.0, 0.0), new ArrayList<>(List.of("")),
                Difficulty.EASY, true));

        request.setCreateGeoCodesToFind( createGeoCodeRequests );

        request.setOrderBy( OrderLevels.GIVEN );

        request.setProperties( new HashMap<>() );

        var response  = eventService.createEvent( request );

        return response.getEventID();
    }

    /**
     * Constructs a CreateEventRequest for a Blockly Event
     * The properties hash map is set to test data validity checks
     */
    private void createBlocklyEventRequest(){
        /* GeoCodes */
        var adminID = handleAdminLogin("adminUser");

        var location = new GeoPoint(-25.75, 28.23);

        var createBlocklyGeoCodes = new ArrayList<CreateGeoCodeRequest>();
        blocklyGeoCodeIDs = new ArrayList<>();

        numBlocklyGeoCodes = 3;

        for(int i = 0; i < numBlocklyGeoCodes; ++i){
            var createGeoCodeRequest = new CreateGeoCodeRequest();

            blocklyGeoCodeID = UUID.randomUUID();
            blocklyGeoCodeIDs.add(blocklyGeoCodeID);

            createGeoCodeRequest.setId(blocklyGeoCodeID);

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

    /**
     * Sets a Blockly Event's property to a string representation
     * of the provided JSON file's contents
     * @param property The key in the hash map
     * @param fileName Then file name
     */
    private void setPropertyFromFile(String property, String fileName){
        try {
            String contents = readFile(property, fileName);
            addBlocklyEventProperty(property, contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the contents of the file as a String
     * @param property The key in the hash map
     * @param fileName Then file name
     */
    private String readFile(String property, String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/java/tech/geocodeapp/geocode/event/blockly/"+property+"/"+fileName+".json")));
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
