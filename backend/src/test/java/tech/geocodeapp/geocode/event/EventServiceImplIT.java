package tech.geocodeapp.geocode.event;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tech.geocodeapp.geocode.GeoCodeApplication;
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
import tech.geocodeapp.geocode.geocode.GeoCodeMockRepository;
import tech.geocodeapp.geocode.geocode.model.Difficulty;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.geocode.repository.GeoCodeRepository;
import tech.geocodeapp.geocode.geocode.service.GeoCodeService;
import tech.geocodeapp.geocode.leaderboard.LeaderboardMockRepository;
import tech.geocodeapp.geocode.leaderboard.PointMockRepository;
import tech.geocodeapp.geocode.leaderboard.UserMockRepository;
import tech.geocodeapp.geocode.leaderboard.UserMockService;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardServiceImpl;
import tech.geocodeapp.geocode.user.repository.UserRepository;
import tech.geocodeapp.geocode.user.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    EventRepository eventRepo;

    /**
     * The mock repository for the Event subsystem UserEventStatus repository
     * All the data will be saved here and is used to mock the JPA repository
     */
    UserEventStatusRepository userEventStatusRepo;

    /**
     * The leaderboard service accessor
     */
    @Autowired
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
    @Autowired
    GeoCodeService geoCodeService;

    /**
     * The User service accessor
     */
    @Autowired
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

        eventRepo.deleteAll();

        try {

            /* Create a new EventServiceImpl instance to access the different use cases */
            eventService = new EventServiceImpl( eventRepo, userEventStatusRepo, leaderboardService, userService );
            eventService.setGeoCodeService(geoCodeService);
        } catch ( RepoException e ) {

            e.printStackTrace();
        }

    }
}
