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
import tech.geocodeapp.geocode.event.repository.UserEventStatusRepository;
import tech.geocodeapp.geocode.event.request.*;
import tech.geocodeapp.geocode.event.response.*;
import tech.geocodeapp.geocode.event.service.*;
import tech.geocodeapp.geocode.geocode.GeoCodeMockRepository;
import tech.geocodeapp.geocode.geocode.model.*;
import tech.geocodeapp.geocode.geocode.repository.GeoCodeRepository;
import tech.geocodeapp.geocode.geocode.service.GeoCodeService;
import tech.geocodeapp.geocode.leaderboard.LeaderboardMockRepository;
import tech.geocodeapp.geocode.leaderboard.PointMockRepository;
import tech.geocodeapp.geocode.leaderboard.UserMockRepository;
import tech.geocodeapp.geocode.leaderboard.UserMockService;
import tech.geocodeapp.geocode.leaderboard.service.*;
import tech.geocodeapp.geocode.user.repository.UserRepository;
import tech.geocodeapp.geocode.user.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
    EventMockRepository eventRepo;

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
     * The User service accessor
     */
    @Mock( name = "userServiceImpl" )
    UserService userService;

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
        eventRepo.deleteAll();
        userEventStatusRepo.deleteAll();
        geoCodeMockRepo.deleteAll();

        var leaderboardMockRepo = new LeaderboardMockRepository();
        var userRepository = new UserMockRepository();
        var userService = new UserMockService( userRepository);
        var pointMockRepository = new PointMockRepository();

        leaderboardService = new LeaderboardServiceImpl(leaderboardMockRepo, pointMockRepository, null, userService);
        geoCodeService = new GeoCodeMockService(geoCodeMockRepo);
        userService = new UserMockService(userMockRepo);

        try {

            /* Create a new EventServiceImpl instance to access the different use cases */
            eventService = new EventServiceImpl( eventRepo, userEventStatusRepo, leaderboardService, userService );
            eventService.setGeoCodeService(geoCodeService);
        } catch ( RepoException e ) {

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
        assertThatThrownBy( () -> eventService = new EventServiceImpl( null, null, leaderboardService, userService ) )
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
         *  Create a request object
         * and assign values to it
         */
        CreateEventRequest request = new CreateEventRequest();
        request.setDescription( "Try get as many as possible" );
        request.setLocation( null );
        request.setName( "Super Sport" );
        request.setBeginDate( LocalDate.parse("2020-01-08") );
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
            GeoCode gc1 = new GeoCode().id(UUID.randomUUID());
            GeoCode gc2 = new GeoCode().id(UUID.randomUUID());
            GeoCode gc3 = new GeoCode().id(UUID.randomUUID());
            geoCodeMockRepo.save(gc1);
            geoCodeMockRepo.save(gc2);
            geoCodeMockRepo.save(gc3);

            /*
             * Create a request object
             * and assign values to it
             */


            CreateEventRequest request = new CreateEventRequest();
            request.setDescription( "Try get as many as possible" );
            request.setLocation( new GeoPoint( 10.2587, 40.336981 ) );
            request.setName( "Super Sport" );
            request.setBeginDate( LocalDate.parse("2020-01-08") );
            request.setEndDate(  LocalDate.parse("2020-05-21") );
                List< UUID > geoCodesToFind = new ArrayList<>();
                geoCodesToFind.add( gc1.getId() );
                geoCodesToFind.add( gc2.getId() );
                geoCodesToFind.add( gc3.getId() );
            request.setGeoCodesToFind( geoCodesToFind );
            request.setOrderBy( OrderLevels.GIVEN );
            request.setProperties( new HashMap<String, String>() );

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
    void getEventCodeNullRequestTest() {

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
         *  Create a request object
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
                                   null, LocalDate.parse("2020-01-08"),
                                   LocalDate.parse("2020-01-08"), null, new HashMap<String, String>());
            eventRepo.save( event );

            /*
             * Create a request object
             * and assign values to it
             */
            GetEventRequest request = new GetEventRequest();
            request.setEventID( eventID );

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
         *  Create a request object
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

            GetCurrentEventStatusRequest request = new GetCurrentEventStatusRequest();
            request.setEventID( null );
            request.setUserID( null );

            var event = eventService.getCurrentEventStatus( request  );
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
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 6 )
    @DisplayName( "Invalid repository attribute handling - nextStage" )
    void nextStageInvalidRequestTest() {

        /*
         *  Create a request object
         * and assign values to it
         */
//        GetCurrentEventStatusRequest request = new GetCurrentEventStatusRequest();
//        request.setEventID( null );
//        request.setUserID( UUID.randomUUID() );
//
//        /* Null parameter request check */
//        assertThatThrownBy( () -> eventService.nextStage( request ) )
//                .isInstanceOf( InvalidRequestException.class )
//                .hasMessageContaining( reqParamError );
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

            eventService.nextStage( null, null  );
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
         *  Create a request object
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
     * Using valid data does the getCurrentEventStatus use case test
     * complete successfully
     */
    @Test
    @Order( 7 )
    @DisplayName( "Valid request - getEnteredEvents" )
    void getEnteredEventsTest() {

        try {

            GetEnteredEventsRequest request = new GetEnteredEventsRequest();
            request.setUserID( null );

            var event = eventService.getEnteredEvents( request  );
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
         *  Create a request object
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

            EventsNearMeRequest request = new EventsNearMeRequest();
            request.setLocation( null );
            request.setRadius( 0.0 );

            var event = eventService.eventsNearMe( request  );
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
         *  Create a request object
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

            ChangeAvailabilityRequest request = new ChangeAvailabilityRequest();
            request.setAvailability( null );
            request.setEventID( null );

            var event = eventService.changeAvailability( request  );
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
         *  Create a request object
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

            GetEventsByLocationRequest request = new GetEventsByLocationRequest();
            request.setLocation( null );

            var event = eventService.getEventsByLocation( request  );
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
    @DisplayName( "Null repository handling - createLeaderBoard" )
    void createLeaderBoardNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> eventService.createLeaderBoard( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 6 )
    @DisplayName( "Invalid repository attribute handling - createLeaderBoard" )
    void createLeaderBoardInvalidRequestTest() {

        /*
         *  Create a request object
         * and assign values to it
         */
        CreateLeaderboardRequest request = new CreateLeaderboardRequest();
        request.setEventID( null );

        /* Null parameter request check */
        assertThatThrownBy( () -> eventService.createLeaderBoard( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the createLeaderBoard use case test
     * complete successfully
     */
    @Test
    @Order( 7 )
    @DisplayName( "Valid request - createLeaderBoard" )
    void createLeaderBoardTest() {

        try {

            CreateLeaderboardRequest request = new CreateLeaderboardRequest();
            request.setEventID( null );

            var event = eventService.createLeaderBoard( request  );
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

        eventService.setGeoCodeService( null  );
    }

    @Test
    @Order( 8 )
    @DisplayName( "check Difficulty" )
    void checkDifficulty() {

        /* Get the order of difficulties */
        /* Get the order of difficulties */
        List< Difficulty > difficultyOrder = Difficulty.getDifficultyOrder();

        System.out.println( difficultyOrder );
    }

    @Test
    @Order( 9 )
    @DisplayName( "check sorting order of GeoCodes by distance" )
    void orderGeoCodes() {

        GeoCode gc1 = new GeoCode().id(UUID.randomUUID()).location(new GeoPoint(5, 0)); //3rd
        GeoCode gc2 = new GeoCode().id(UUID.randomUUID()).location(new GeoPoint(2, 1)); //2nd
        GeoCode gc3 = new GeoCode().id(UUID.randomUUID()).location(new GeoPoint(10, -1)); //4th
        GeoCode gc4 = new GeoCode().id(UUID.randomUUID()).location(new GeoPoint(0, 0)); //1st

        List<GeoCode> geoCodes = new ArrayList<GeoCode>();
        geoCodes.add(gc1);
        geoCodes.add(gc2);
        geoCodes.add(gc3);
        geoCodes.add(gc4);
        GeoPoint start = new GeoPoint(0, 0);

        List<UUID> result = Graph.getOptimalGeocodeIDOrder(geoCodes, start);

        List<UUID> expected = new ArrayList<UUID>();
        expected.add(gc4.getId()); //1st
        expected.add(gc2.getId()); //2nd
        expected.add(gc1.getId()); //3rd
        expected.add(gc3.getId()); //4th

        Assertions.assertEquals(expected, result);

    }

    ////////////////Helper functions////////////////

    /**
     * This function creates numerous Events to be used for testing.
     *
     * NOTE: the create function will need to be working with tests passing for this
     *       helper function to be used
     */
    private void populate( int size ) {

        try {
            /* Create mock geocodes */
            GeoCode gc1 = new GeoCode().id(UUID.randomUUID());
            GeoCode gc2 = new GeoCode().id(UUID.randomUUID());
            GeoCode gc3 = new GeoCode().id(UUID.randomUUID());
            geoCodeMockRepo.save(gc1);
            geoCodeMockRepo.save(gc2);
            geoCodeMockRepo.save(gc3);


            CreateEventRequest request = new CreateEventRequest();
            request.setDescription( "Try get as many as possible" );
            request.setLocation( new GeoPoint( 10.2587, 40.336981 ) );
            request.setName( "Super Sport" );
            request.setBeginDate( LocalDate.parse("2020-01-08") );
            request.setEndDate(  LocalDate.parse("2020-05-21") );
            List< UUID > geoCodesToFind = new ArrayList<>();
            geoCodesToFind.add( gc1.getId() );
            geoCodesToFind.add( gc2.getId() );
            geoCodesToFind.add( gc3.getId() );
            request.setGeoCodesToFind( geoCodesToFind );
            request.setOrderBy( OrderLevels.GIVEN );

            eventService.createEvent( request );

        } catch ( InvalidRequestException e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }

    }

}
