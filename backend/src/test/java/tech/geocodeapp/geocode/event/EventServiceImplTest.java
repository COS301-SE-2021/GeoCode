package tech.geocodeapp.geocode.event;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import tech.geocodeapp.geocode.event.exceptions.*;
import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.event.request.*;
import tech.geocodeapp.geocode.event.response.*;
import tech.geocodeapp.geocode.event.service.*;
import tech.geocodeapp.geocode.geocode.model.*;
import tech.geocodeapp.geocode.geocode.service.GeoCodeService;
import tech.geocodeapp.geocode.leaderboard.LeaderboardMockRepository;
import tech.geocodeapp.geocode.leaderboard.PointMockRepository;
import tech.geocodeapp.geocode.leaderboard.UserMockRepository;
import tech.geocodeapp.geocode.leaderboard.UserMockService;
import tech.geocodeapp.geocode.leaderboard.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
    EventMockRepository repo;

    /**
     * The leaderboard service accessor
     */
    @Mock( name = "leaderboardServiceImpl" )
    LeaderboardService leaderboardService;

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
        repo = new EventMockRepository();
        repo.deleteAll();

        var leaderboardMockRepo = new LeaderboardMockRepository();
        var userRepository = new UserMockRepository();
        var userService = new UserMockService( userRepository);
        var pointMockRepository = new PointMockRepository();

        leaderboardService = new LeaderboardServiceImpl(leaderboardMockRepo, pointMockRepository, null, userService);

        try {

            /* Create a new EventServiceImpl instance to access the different use cases */
            eventService = new EventServiceImpl( repo, leaderboardService );
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
        assertThatThrownBy( () -> eventService = new EventServiceImpl( null, leaderboardService ) )
                .isInstanceOf( RepoException.class )
                .hasMessageContaining( "The given repository does not exist." );
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 2 )
    @DisplayName( "Null repository handling - createEvent" )
    void createGeoCodeNullRequestTest() {

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
                geoCodesToFind.add( UUID.randomUUID() );
                geoCodesToFind.add( UUID.randomUUID() );
                geoCodesToFind.add( UUID.randomUUID() );
            request.setGeoCodesToFind( geoCodesToFind );

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
                                   LocalDate.parse("2020-01-08"), null);
            repo.save( event );

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

    @Test
    @DisplayName( "check" )
    void check() {

        /* Get the order of difficulties */
        /* Get the order of difficulties */
        List< Difficulty > difficultyOrder = Difficulty.getDifficultyOrder();

        System.out.println( difficultyOrder );
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


            CreateEventRequest request = new CreateEventRequest();
            request.setDescription( "Try get as many as possible" );
            request.setLocation( new GeoPoint( 10.2587, 40.336981 ) );
            request.setName( "Super Sport" );
            request.setBeginDate( LocalDate.parse("2020-01-08") );
            request.setEndDate(  LocalDate.parse("2020-05-21") );
            List< UUID > geoCodesToFind = new ArrayList<>();
            geoCodesToFind.add( UUID.randomUUID() );
            geoCodesToFind.add( UUID.randomUUID() );
            geoCodesToFind.add( UUID.randomUUID() );
            request.setGeoCodesToFind( geoCodesToFind );

            eventService.createEvent( request );

        } catch ( InvalidRequestException e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }

    }

}
