package tech.geocodeapp.geocode.geocode;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.mockito.Mock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.util.Assert;
import tech.geocodeapp.geocode.collectable.request.GetCollectableTypeByIDRequest;
import tech.geocodeapp.geocode.event.EventMockRepository;
import tech.geocodeapp.geocode.event.TimeLogMockRepository;
import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.event.model.TimeTrial;
import tech.geocodeapp.geocode.event.service.EventService;
import tech.geocodeapp.geocode.event.service.EventServiceImpl;
import tech.geocodeapp.geocode.geocode.exceptions.*;
import tech.geocodeapp.geocode.geocode.model.Difficulty;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.geocode.service.*;
import tech.geocodeapp.geocode.geocode.response.*;
import tech.geocodeapp.geocode.geocode.request.*;
import tech.geocodeapp.geocode.collectable.*;
import tech.geocodeapp.geocode.collectable.model.*;
import tech.geocodeapp.geocode.collectable.service.*;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;
import tech.geocodeapp.geocode.user.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This is the unit testing class for the GeoCode subsystem
 *
 * Testing for if the requests, request attributes
 * and the repository are working in valid order
 * and what exceptions are thrown if not.
 */
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
@ExtendWith( MockitoExtension.class )
class GeoCodeServiceImplTest {

    /**
     * The service for the GeoCode subsystem
     *
     * This is used to access the different use cases
     * needed for functionality
     */
    GeoCodeService geoCodeService;

    /**
    * The mock repository for the GeoCode subsystem
    * All the data will be saved here and is used to mock the JPA repository
    */
    GeoCodeMockRepository repo;

    /**
     * The collectable service accessor
     */
    @Mock( name = "collectableServiceImpl" )
    CollectableService collectableService;

    /**
     * The LeaderBoard service accessor
     */
    @Mock( name = "leaderboardServiceImpl" )
    LeaderboardService leaderboardService;

    /**
     * A mock service for the User subsystem
     *
     * This is used to access User subsystem in some use cases
     */
    @Mock( name = "userServiceImpl" )
    UserService userService;

    /** A mock service for the Event subsystem
     *
     * This is used to access Event subsystem in some use cases
     */
    @Mock( name = "eventServiceImpl" )
    EventService eventService;

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
     * Create the GeoCodeServiceImpl with the relevant repositories.
     *
     * This is done to ensure a repository with no data is created each time
     * and the service implementation contains fresh code that has not been affected
     * by some other test or data.
     */
    @BeforeEach
    void setup() throws RepoException, tech.geocodeapp.geocode.event.exceptions.RepoException {

        /* Create a new repository instance and make sure there is no data in it */
        repo = new GeoCodeMockRepository();
        repo.deleteAll();

        /* Ensure a collectable can be made */
        CollectableTypeMockRepository typeMockRepo = new CollectableTypeMockRepository();

            CollectableType type = new CollectableType();
            type.setId( UUID.fromString( "333599b9-94c7-403d-8389-83ed48387d13" ) );
            type.setName( "name" );
            type.setImage( "Image" );
            type.setRarity( Rarity.RARE );
            type.setSet( new CollectableSet() );

            typeMockRepo.save( type );

        /* Create a new Collectable Service implementation with the relevant repositories */
        collectableService = new CollectableServiceImpl( new CollectableMockRepository(),
                                                         new CollectableSetMockRepository(),
                                                         typeMockRepo );

        EventMockRepository< Event > eventRepo = new EventMockRepository<>();
        EventMockRepository< TimeTrial > timeTrialRepo = new EventMockRepository<>();
        TimeLogMockRepository timelogRepo = new TimeLogMockRepository();

        eventService = new EventServiceImpl( eventRepo, timeTrialRepo, timelogRepo, leaderboardService );

        /* Populate the Event repository with a known Event to find*/
        var event = new Event( eventID, "Test", "Test description", null,
                               null, LocalDate.parse( "2020-01-08" ),
                               LocalDate.parse("2020-01-08"), null);

        eventRepo.save( event );

        /* Create the mock user repo and insert a new user into it */
        // var userMockRepo = new UserMockRepository();
        // userService = new UserServiceImpl(userMockRepo, new CollectableMockRepository(), collectableService);


       /*
        collectableService = Mockito.mock( CollectableService.class );


        List< UUID > collectables = new ArrayList<>();

        collectables.add( UUID.randomUUID() );
        collectables.add( UUID.randomUUID() );
        collectables.add( UUID.randomUUID() );
        when(collectableService.getCollectables()).thenReturn( new GetCollectablesResponse( collectables ) );
        */

        try {

            /* Create a new GeoCodeServiceImpl instance to access the different use cases */
            geoCodeService = new GeoCodeServiceImpl( repo, collectableService, userService, eventService );
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
    @DisplayName( "Null repository handling - GeoCodeServiceImpl" )
    void RepositoryNullTest() {

        /* Null request check */
        assertThatThrownBy( () -> geoCodeService = new GeoCodeServiceImpl( null, collectableService, userService, eventService ) )
                .isInstanceOf( RepoException.class )
                .hasMessageContaining( "The given repository does not exist." );
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 2 )
    @DisplayName( "Null repository handling - createGeoCode" )
    void createGeoCodeNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> geoCodeService.createGeoCode( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 10 )
    @DisplayName( "Invalid repository attribute handling - createGeoCode" )
    void createGeoCodeInvalidRequestTest() {

        /*
         *  Create a request object
         * and assign values to it
         * */
        CreateGeoCodeRequest request = new CreateGeoCodeRequest();
        request.setAvailable( true );
        request.setDescription( null );
        request.setDifficulty( Difficulty.INSANE );
        request.setHints( null );
        request.setLocation( new GeoPoint( 10.2587, 40.336981 ) );

        /* Null parameter request check */
        assertThatThrownBy( () -> geoCodeService.createGeoCode( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the createGeoCode use case test
     * complete successfully
     */
    @Test
    @Order( 18 )
    @DisplayName( "Valid request - createGeoCode" )
    void createGeoCodeTest() {

        try {

            /*
             * Create a request object
             * and assign values to it
             */
            CreateGeoCodeRequest request = new CreateGeoCodeRequest();
            request.setAvailable( true );
            request.setDescription( "The GeoCode is stored at the art Museum in Jhb South" );
            request.setDifficulty( Difficulty.INSANE );
            List< String > hints = new ArrayList<>();
                hints.add( "This " );
                hints.add( "is " );
                hints.add( "a " );
                hints.add( "secret " );
                hints.add( "hint." );
            request.setHints( hints );
            request.setLocation( new GeoPoint( 10.2587, 40.336981 ) );
            request.setEventID( eventID );

            CreateGeoCodeResponse response = geoCodeService.createGeoCode( request );

            /*
             * Check if the GeoCode was created correctly
             * through checking the description created with the code
             */
            Assertions.assertTrue( response.isSuccess() );

        } catch ( InvalidRequestException e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }
    }

    /**
     * Using valid data does the getAllGeoCode use case test
     * complete successfully
     */
    @Test
    @Order( 19 )
    @DisplayName( "Valid request - getAllGeoCode" )
    void getAllGeoCodeTest() {

        try {

            /*
             * Create a request object
             * and assign values to it
             */
            CreateGeoCodeRequest request = new CreateGeoCodeRequest();
            request.setAvailable( true );
            request.setDescription( "The GeoCode is stored at the art Museum in Jhb South" );
            request.setDifficulty( Difficulty.INSANE );
            List< String > hints = new ArrayList<>();
                hints.add( "This " );
                hints.add( "is " );
                hints.add( "a " );
                hints.add( "secret " );
                hints.add( "hint." );
            request.setHints( hints );
            request.setLocation( new GeoPoint( 10.2587, 40.336981 ) );
            request.setEventID( eventID );

            /* create the GeoCode in the repository */
            geoCodeService.createGeoCode( request );

            /* Get the response by calling the getAllGeoCodes use case */
            GetGeoCodesResponse response = geoCodeService.getAllGeoCodes();

            /* Get a geocode from the response */
            List< GeoCode > geocodes = response.getGeocodes();

            if ( geocodes.size() > 0 ) {

                /*
                 * Check if all the GeoCodes were returned correctly
                 * through checking the description created with the code
                 */
                Assertions.assertEquals( "The GeoCode is stored at the art Museum in Jhb South", geocodes.get( 0 ).getDescription() );
            } else {

                Assert.notEmpty( geocodes );
            }
        } catch ( Exception e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }

    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 3 )
    @DisplayName( "Null repository handling - getGeoCodesByDifficulty" )
    void getGeoCodesByDifficultyNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> geoCodeService.getGeoCodesByDifficulty( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 11 )
    @DisplayName( "Invalid repository attribute handling - getGeoCodesByDifficulty" )
    void getGeoCodesByDifficultyInvalidRequestTest() {

        /*
         * Create a request object
         * and assign values to it
         * */
        GetGeoCodesByDifficultyRequest request = new GetGeoCodesByDifficultyRequest();
        request.setDifficulty( null );

        /* Null parameter request check */
        assertThatThrownBy( () -> geoCodeService.getGeoCodesByDifficulty( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the getGeoCodesByDifficulty use case test
     * complete successfully
     */
    @Test
    @Order( 20 )
    @DisplayName( "Valid request - getGeoCodesByDifficulty" )
    void getGeoCodesByDifficultyTest() {

        try {

            /* Control variables */
            int size = 6;
            Difficulty difficulty = Difficulty.INSANE;

            /* Populate the repo with the given amount of GeoCodes */
            populate( size );

            /*
             * Create a request object
             * and assign values to it
             */
            GetGeoCodesByDifficultyRequest request = new GetGeoCodesByDifficultyRequest();
            request.setDifficulty( difficulty );

            /* Get the response by calling the getGeoCodesByDifficulty use case */
            GetGeoCodesByDifficultyResponse response = geoCodeService.getGeoCodesByDifficulty( request );

            boolean valid = true;
            for ( int x = 0; x < response.getGeocodes().size(); x++ ) {

                /* Check if the GeoCode is of the correct difficulty type */
                if ( !response.getGeocodes().get( x ).getDifficulty().equals( difficulty ) ) {

                    /* The GeoCode was not of the correct difficulty type */
                    valid = false;
                    break;
                }
            }

            Assertions.assertTrue( valid );
        } catch ( Exception e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 3 )
    @DisplayName( "Null repository handling - getGeoCodesByDifficultyList" )
    void getGeoCodesByDifficultyListNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> geoCodeService.getGeoCodesByDifficultyList( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 11 )
    @DisplayName( "Invalid repository attribute handling - getGeoCodesByDifficultyList" )
    void getGeoCodesByDifficultyListInvalidRequestTest() {

        /*
         * Create a request object
         * and assign values to it
         * */
        GetGeoCodesByDifficultyListRequest request = new GetGeoCodesByDifficultyListRequest();
        request.setDifficulty( null );

        /* Null parameter request check */
        assertThatThrownBy( () -> geoCodeService.getGeoCodesByDifficultyList( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the getGeoCodesByDifficultyList use case test
     * complete successfully
     */
    @Test
    @Order( 20 )
    @DisplayName( "Valid request - getGeoCodesByDifficultyList" )
    void getGeoCodesByDifficultyListTest() {

        try {

            /* Control variables */
            int size = 6;

            List< Difficulty > listOfDifficulties = new ArrayList<>();
            listOfDifficulties.add( Difficulty.INSANE );
            listOfDifficulties.add( Difficulty.EASY );

            /* Populate the repo with the given amount of GeoCodes */
            populate( size );

            /*
             * Create a request object
             * and assign values to it
             */
            GetGeoCodesByDifficultyListRequest request = new GetGeoCodesByDifficultyListRequest();
            request.setDifficulty( listOfDifficulties );

            /* Get the response by calling the getGeoCodesByDifficultyList use case */
            GetGeoCodesByDifficultyListResponse response = geoCodeService.getGeoCodesByDifficultyList( request );

            var valid = true;

            /* Go through each GeoCode returned in the response */
            for ( int x = 0; x < response.getGeocodes().size(); x++ ) {

                /* Used to determine if any of the GeoCodes tested valid */
                var checks = 0;

                /* Go through each possibility it could be */
                for ( Difficulty listOfDifficulty : listOfDifficulties ) {

                    /* Check if the GeoCode is of the correct difficulty type */
                    if ( !response.getGeocodes().get( x ).getDifficulty().equals( listOfDifficulty ) ) {

                        checks++;
                    }
                }

                if ( listOfDifficulties.size() == checks ) {

                    /* The GeoCode was not of the correct difficulty type */
                    valid = false;
                    break;
                }
            }

            Assertions.assertTrue( valid );
        } catch ( Exception e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 4 )
    @DisplayName( "Null repository handling - getHints" )
    void getHintsNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> geoCodeService.getHints( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 12 )
    @DisplayName( "Invalid repository attribute handling - getHints" )
    void getHintsInvalidRequestTest() {

        /*
         * Create a request object
         * and assign values to it
         * */
        GetHintsRequest request = new GetHintsRequest();
        request.setGeoCodeID( null );

        /* Null parameter request check */
        assertThatThrownBy( () -> geoCodeService.getHints( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the getHints use case test
     * complete successfully
     */
    @Test
    @Order( 21 )
    @DisplayName( "Valid request - getHints" )
    void getHintsTest() {

        try {

            populate( 1 );

            List< GeoCode > temp = repo.findAll();

            /* Create the request with the ID of the GeoCode we want */
            GetHintsRequest request = new GetHintsRequest();
            request.setGeoCodeID( temp.get( 0 ).getId() );

            /* Get the response by calling the getHints use case */
            GetHintsResponse response = geoCodeService.getHints( request );

            /*
             * Check if the GeoCode was created correctly
             * through checking the returned hints from a known hint
             */
            Assertions.assertEquals( "Hint one for: 1", new ArrayList<>( response.getHints() ).get( 0 ) );
        } catch ( Exception e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 5 )
    @DisplayName( "Null repository handling - swapCollectables" )
    void swapCollectablesNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> geoCodeService.swapCollectables( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 13 )
    @DisplayName( "Invalid repository attribute handling - swapCollectables" )
    void swapCollectablesInvalidRequestTest() {

        /*
         *  Create a request object
         * and assign values to it
         * */
        SwapCollectablesRequest request = new SwapCollectablesRequest();
        request.setTargetCollectableID( null );
        request.setTargetGeoCodeID( null );

        /* Null parameter request check */
        assertThatThrownBy( () -> geoCodeService.swapCollectables( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 6 )
    @DisplayName( "Null repository handling - updateAvailability" )
    void updateAvailabilityNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> geoCodeService.updateAvailability( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 14 )
    @DisplayName( "Invalid repository attribute handling - updateAvailability" )
    void updateAvailabilityInvalidRequestTest() {

        /*
         *  Create a request object
         * and assign values to it
         * */
        UpdateAvailabilityRequest request = new UpdateAvailabilityRequest();
        request.setIsAvailable( true );

        /* Null parameter request check */
        assertThatThrownBy( () -> geoCodeService.updateAvailability( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the updateAvailability use case test
     * complete successfully
     */
    @Test
    @Order( 23 )
    @DisplayName( "Valid request - updateAvailability" )
    void updateAvailabilityTest() {

        /* Create a GeoCode */
        populate( 1 );
        List< GeoCode > temp = repo.findAll();

        try {

            /* Create the request with the ID of the GeoCode we want */
            UpdateAvailabilityRequest request = new UpdateAvailabilityRequest();
            request.setGeoCodeID( temp.get( 0 ).getId() );
            request.setIsAvailable( false );

            /* Get the response by calling the updateAvailability use case */
            UpdateAvailabilityResponse response = geoCodeService.updateAvailability( request );

            /*
             * Check if the GeoCode was created correctly
             * through checking the returned hints from a known hint
             */
            Assertions.assertTrue( response.isIsSuccess() );
        } catch ( Exception e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 7 )
    @DisplayName( "Null repository handling - getGeoCodesByLocation" )
    void getGeoCodesByLocationNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> geoCodeService.getGeoCodesByLocation( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 15 )
    @DisplayName( "Invalid repository attribute handling - getGeoCodesByLocation" )
    void getGeoCodesByLocationInvalidRequestTest() {

        /*
         *  Create a request object
         * and assign values to it
         */
        GetGeoCodeByLocationRequest request = new GetGeoCodeByLocationRequest();
        request.setLocation( null );

        /* Null parameter request check */
        assertThatThrownBy( () -> geoCodeService.getGeoCodesByLocation( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the getGeoCodesByLocation use case test
     * complete successfully
     */
    @Test
    @Order( 24 )
    @DisplayName( "Valid request getGeoCodesByLocation" )
    void getGeoCodesByLocationTest() {

        /* Create a GeoCode */
        populate( 1 );
        List< GeoCode > temp = repo.findAll();

        try {

            /* Create the request with the ID of the GeoCode we want */
            GetGeoCodeByLocationRequest request = new GetGeoCodeByLocationRequest();
            request.setLocation( temp.get( 0 ).getLocation() );


            /* Get the response by calling the updateAvailability use case */
            GetGeoCodeByLocationResponse response = geoCodeService.getGeoCodesByLocation( request );

            /*
             * Check if the GeoCode was created correctly
             * through checking the returned hints from a known hint
             */
            Assertions.assertEquals( "The DIFFICULTY GeoCode is stored at location 1", response.getDescription() );
        } catch ( Exception e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 8 )
    @DisplayName( "Null repository handling - getGeoCodesByQRCode" )
    void getGeoCodesByQRCodeNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> geoCodeService.getGeoCodeByQRCode( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 16 )
    @DisplayName( "Invalid repository attribute handling - getGeoCodesByQRCode" )
    void getGeoCodesByQRCodeInvalidRequestTest() {

        /*
         *  Create a request object
         * and assign values to it
         */
        GetGeoCodeByQRCodeRequest request = new GetGeoCodeByQRCodeRequest();
        request.setQrCode( null );

        /* Null parameter request check */
        assertThatThrownBy( () -> geoCodeService.getGeoCodeByQRCode( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the getGeoCodesByLocation use case test
     * complete successfully
     */
    @Test
    @Order( 25 )
    @DisplayName( "Valid request - getGeoCodesByQRCode" )
    void getGeoCodesByQRCodeTest() {

        /* Create a GeoCode */
        populate( 1 );
        List< GeoCode > temp = repo.findAll();

        try {

            /* Create the request with the ID of the GeoCode we want */
            GetGeoCodeByQRCodeRequest request = new GetGeoCodeByQRCodeRequest();
            request.setQrCode( temp.get( 0 ).getQrCode() );

            /* Get the response by calling the updateAvailability use case */
            GetGeoCodeByQRCodeResponse response = geoCodeService.getGeoCodeByQRCode( request );

            /*
             * Check if the GeoCode was created correctly
             * through checking the returned hints from a known hint
             */
            Assertions.assertEquals( "The DIFFICULTY GeoCode is stored at location 1", response.getDescription() );
        } catch ( Exception e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }
    }


    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 8 )
    @DisplayName( "Null repository handling - getCollectables" )
    void getCollectablesNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> geoCodeService.getCollectables( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 17 )
    @DisplayName( "Invalid repository attribute handling - getCollectables" )
    void getCollectablesInvalidRequestTest() {

        /*
         *  Create a request object
         * and assign values to it
         */
        GetCollectablesRequest request = new GetCollectablesRequest();
        request.setGeoCodeID( null );

        /* Null parameter request check */
        assertThatThrownBy( () -> geoCodeService.getCollectables( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the getGeoCodesByLocation use case test
     * complete successfully
     */
    @Test
    @Order( 26 )
    @DisplayName( "Valid request - getCollectables" )
    void getCollectablesTest() {

        /* Create a GeoCode */
        populate( 1 );
        List< GeoCode > temp = repo.findAll();

        try {

            /* Create the request with the ID of the GeoCode we want */
            GetCollectablesRequest request = new GetCollectablesRequest();
            request.setGeoCodeID( temp.get( 0 ).getId() );

            /* Get the response by calling the updateAvailability use case */
            GetCollectablesResponse response = geoCodeService.getCollectables( request );

            /*
             * Check if the GeoCode was created correctly
             * through checking the returned hints from a known hint
             */

            var typeID = response.getCollectables().get( 0 );
            var name = collectableService.getCollectableTypeByID( new GetCollectableTypeByIDRequest( typeID ) );
            //Assertions.assertEquals( "name", name.getCollectableType().getName() );
        } catch ( Exception e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }
    }

    ////////////////Helper functions////////////////

    /**
     * This function creates numerous GeoCodes to be used for testing.
     */
    private void populate( int size ) {

        try {

            /* check if the size is valid */
            if ( size >= 2 ) {

                /* Populate half with INSANE geoCodes to give variability */
                for ( int x = 0; x < ( size / 2 ); x++ ) {

                    /* Create the request with the following mock data */
                    CreateGeoCodeRequest request = new CreateGeoCodeRequest();
                    request.setAvailable( true );
                    request.setDescription( "The INSANE GeoCode is stored at location " + x );
                    request.setDifficulty( Difficulty.INSANE );
                    List< String > hints = new ArrayList<>();
                        hints.add( "Hint one for: " + x );
                        hints.add( "Hint two for: " + x );
                        hints.add( "Hint three for: " + x );
                    request.setHints( hints );
                    request.setLocation( new GeoPoint( 10.2587 + x, 40.336981 + x ) );
                    request.setEventID( eventID );

                    /* Add the created GeoCode to the list */
                    geoCodeService.createGeoCode( request );
                }

                /* Populate half with EASY geoCodes to give variability */
                for ( int x = ( size / 2 ); x < size; x++ ) {

                    /* Create the request with the following mock data */
                    CreateGeoCodeRequest request = new CreateGeoCodeRequest();
                    request.setAvailable( true );
                    request.setDescription( "The EASY GeoCode is stored at location " + x );
                    request.setDifficulty( Difficulty.EASY );
                    List< String > hints = new ArrayList<>();
                    hints.add( "Hint one for: " + x );
                    hints.add( "Hint two for: " + x );
                    hints.add( "Hint three for: " + x );
                    request.setHints( hints );
                    request.setLocation( new GeoPoint( 10.2587 + x, 40.336981 + x ) );
                    request.setEventID( eventID );

                    /* Add the created GeoCode to the list */
                    geoCodeService.createGeoCode( request );
                }
            } else if ( size == 1 ) {

                int x = 1;

                /* Create the request with the following mock data */
                CreateGeoCodeRequest request = new CreateGeoCodeRequest();
                request.setAvailable( true );
                request.setDescription( "The DIFFICULTY GeoCode is stored at location " + x );
                request.setDifficulty( Difficulty.HARD );
                List< String > hints = new ArrayList<>();
                    hints.add( "Hint one for: " + x );
                    hints.add( "Hint two for: " + x );
                    hints.add( "Hint three for: " + x );
                request.setHints( hints );
                request.setLocation( new GeoPoint( 10.2587 + x, 40.336981 + x ) );
                request.setEventID( eventID );

                /* Add the created GeoCode to the list */
                geoCodeService.createGeoCode( request );
            }

        } catch ( InvalidRequestException e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }

    }

}
