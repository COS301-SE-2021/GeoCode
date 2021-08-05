package tech.geocodeapp.geocode.event;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tech.geocodeapp.geocode.event.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.event.exceptions.RepoException;
import tech.geocodeapp.geocode.event.request.CreateEventRequest;
import tech.geocodeapp.geocode.event.response.CreateEventResponse;
import tech.geocodeapp.geocode.event.service.EventService;
import tech.geocodeapp.geocode.event.service.EventServiceImpl;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.leaderboard.LeaderboardMockRepository;
import tech.geocodeapp.geocode.leaderboard.PointMockRepository;
import tech.geocodeapp.geocode.leaderboard.UserMockRepository;
import tech.geocodeapp.geocode.leaderboard.UserMockService;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


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
     * The expected exception message for if the given request has invalid attributes
     */
    String reqParamError = "The given request is missing parameter/s.";

    /**
     * The expected exception message for if the given request has invalid attributes
     */
    String reqEmptyError = "The given request is empty.";

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
     * Using valid data does the createGeoCode use case test
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
                List< GeoCode > geoCodesToFind = new ArrayList<>();
                geoCodesToFind.add( new GeoCode() );
                geoCodesToFind.add( new GeoCode() );
                geoCodesToFind.add( new GeoCode() );
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
}
