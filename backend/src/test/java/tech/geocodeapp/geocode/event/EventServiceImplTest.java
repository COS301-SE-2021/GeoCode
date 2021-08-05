package tech.geocodeapp.geocode.event;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tech.geocodeapp.geocode.event.exceptions.RepoException;
import tech.geocodeapp.geocode.event.service.EventService;
import tech.geocodeapp.geocode.event.service.EventServiceImpl;
import tech.geocodeapp.geocode.leaderboard.LeaderboardMockRepository;
import tech.geocodeapp.geocode.leaderboard.PointMockRepository;
import tech.geocodeapp.geocode.leaderboard.UserMockRepository;
import tech.geocodeapp.geocode.leaderboard.UserMockService;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardServiceImpl;

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
}
