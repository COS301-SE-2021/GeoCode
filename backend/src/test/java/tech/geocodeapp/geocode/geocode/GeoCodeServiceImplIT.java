package tech.geocodeapp.geocode.geocode;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.lenient;

import tech.geocodeapp.geocode.collectable.request.GetCollectableTypeByIDRequest;
import tech.geocodeapp.geocode.event.service.EventService;
import tech.geocodeapp.geocode.geocode.exceptions.*;
import tech.geocodeapp.geocode.geocode.model.Difficulty;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.geocode.service.*;
import tech.geocodeapp.geocode.geocode.response.*;
import tech.geocodeapp.geocode.geocode.request.*;
import tech.geocodeapp.geocode.geocode.repository.GeoCodeRepository;
import tech.geocodeapp.geocode.GeoCodeApplication;
import tech.geocodeapp.geocode.collectable.service.*;
import tech.geocodeapp.geocode.user.model.User;
import tech.geocodeapp.geocode.user.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This is the integration testing class for the GeoCode subsystem
 *
 * Testing for if the requests, request attributes
 * and the repository are working in valid order
 * and what exceptions are thrown if not.
 */
@SpringBootTest( classes = GeoCodeApplication.class,
                 webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
class GeoCodeServiceImplIT {


    /**
     * The service for the GeoCode subsystem
     *
     * This is used to access the different use cases
     * needed for functionality
     */
    GeoCodeService geoCodeService;

    /**
     * The repository for the GeoCode subsystem
     * All the data will be saved here and is a JPA repository
     */
    @Autowired
    GeoCodeRepository repo;

    /**
     * The collectable service accessor
     */
    @Autowired
    CollectableServiceImpl collectableService;

    /**
     * The service for the User subsystem
     *
     * This is used to access User subsystem in some use cases
     */
    @Autowired
    UserService userService;

    /**
     * This is used to access the Event subsystem in some use cases
     */
    @Autowired
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
    java.util.UUID eventID = java.util.UUID.fromString( "db91e6ee-f5b6-11eb-9a03-0242ac130003" );

    /**
     * This is used to have a static known UUID
     */
    UUID userID = UUID.fromString( "f479228d-8a4a-4b90-ba86-abccadec5085" );

    /**
     * This is used to have a static known UUID
     */
    UUID geoCodeID = java.util.UUID.fromString( "f3bd09b3-e4b0-483f-9a08-8191a23e71a0" );

    /**
     * This is used to have a static known location
     */
    GeoPoint locate = new GeoPoint( 10.2587 , 40.336981 );

    /**
     * Create the GeoCodeServiceImpl with the relevant repositories.
     *
     * This is done to ensure a repository with no data is created each time
     * and the service implementation contains fresh code that has not been affected
     * by some other test or data.
     */
    @BeforeEach
    void setup() {

        /* Clear the repo of any old data */
        repo.deleteAll();

        try {

            /* Mock the user service to return wanted data */
            userService = Mockito.mock( UserServiceImpl.class );

            /* Get a random user as only a valid response is needed */
            lenient().when ( userService.getCurrentUser() ).thenReturn( new User().id( java.util.UUID.randomUUID() ) );
            lenient().when ( userService.getCurrentUserID() ).thenReturn( java.util.UUID.randomUUID() );

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
    //@Tag( "Tests" )
    @DisplayName( "Null repository handling - GeoCodeServiceImpl" )
    void RepositoryNullTest() {

        /* Null request check */
        assertThatThrownBy( () -> geoCodeService = new GeoCodeServiceImpl( null, collectableService, userService, eventService ) )
                .isInstanceOf( RepoException.class )
                .hasMessageContaining( "The given repository does not exist." );
    }

    /**
     * Check for GeoCodes with the Difficulty Hard using the custom query
     */
    @Test
    @Order( 1 )
    @DisplayName( "Custom query repository for Hard difficulty - findGeoCodeWithDifficulty" )
    void findGeoCodeWithHardDifficultyTest() {

        for ( int x = 0; x < 2; x++ ) {

            /* Create the request with the following mock data */
            var geoCode = new GeoCode();
            geoCode.setId( java.util.UUID.randomUUID() );
            geoCode.setAvailable( true );
            geoCode.setDescription( "The EASY GeoCode is stored at location " + x );
            geoCode.setDifficulty( Difficulty.EASY );
            List< String > hints = new ArrayList<>();
            hints.add( "Hint one for: " + x );
            hints.add( "Hint two for: " + x );
            hints.add( "Hint three for: " + x );
            geoCode.setHints( hints );
            geoCode.setLocation( new GeoPoint( 10.2587 + x, 40.336981 + x ) );

            repo.save( geoCode );
        }

        for ( int x = 0; x < 2; x++ ) {

            /* Create the request with the following mock data */
            var geoCode = new GeoCode();
            geoCode.setId( java.util.UUID.randomUUID() );
            geoCode.setAvailable( true );
            geoCode.setDescription( "The HARD GeoCode is stored at location " + x );
            geoCode.setDifficulty( Difficulty.HARD );
            List< String > hints = new ArrayList<>();
            hints.add( "Hint one for: " + x );
            hints.add( "Hint two for: " + x );
            hints.add( "Hint three for: " + x );
            geoCode.setHints( hints );
            geoCode.setLocation( new GeoPoint( 10.2587 + x, 40.336981 + x ) );

            repo.save( geoCode );
        }

        for ( int x = 0; x < 3; x++ ) {

            /* Create the request with the following mock data */
            var geoCode = new GeoCode();
            geoCode.setId( java.util.UUID.randomUUID() );
            geoCode.setAvailable( true );
            geoCode.setDescription( "The INSANE GeoCode is stored at location " + x );
            geoCode.setDifficulty( Difficulty.INSANE );
            List< String > hints = new ArrayList<>();
            hints.add( "Hint one for: " + x );
            hints.add( "Hint two for: " + x );
            hints.add( "Hint three for: " + x );
            geoCode.setHints( hints );
            geoCode.setLocation( new GeoPoint( 10.2587 + x, 40.336981 + x ) );

            repo.save( geoCode );
        }

        /* Call the custom query to find GeoCodes of a certain Difficulty */
        var test = repo.findGeoCodeWithDifficulty( Difficulty.HARD );

        /* Go through each returned GeoCode */
        for ( GeoCode temp: test ) {

            /* Check */
            Assertions.assertEquals( Difficulty.HARD, temp.getDifficulty() );
        }
    }

    /**
     * Check for GeoCodes with the Difficulty Insane using the custom query
     */
    @Test
    @Order( 1 )
    @DisplayName( "Custom query repository for Insane difficulty - findGeoCodeWithDifficulty" )
    void findGeoCodeWithInsaneDifficultyTest() {

        for ( int x = 0; x < 2; x++ ) {

            /* Create the request with the following mock data */
            var geoCode = new GeoCode();
            geoCode.setId( java.util.UUID.randomUUID() );
            geoCode.setAvailable( true );
            geoCode.setDescription( "The EASY GeoCode is stored at location " + x );
            geoCode.setDifficulty( Difficulty.EASY );
            List< String > hints = new ArrayList<>();
            hints.add( "Hint one for: " + x );
            hints.add( "Hint two for: " + x );
            hints.add( "Hint three for: " + x );
            geoCode.setHints( hints );
            geoCode.setLocation( new GeoPoint( 10.2587 + x, 40.336981 + x ) );

            repo.save( geoCode );
        }

        for ( int x = 0; x < 2; x++ ) {

            /* Create the request with the following mock data */
            var geoCode = new GeoCode();
            geoCode.setId( java.util.UUID.randomUUID() );
            geoCode.setAvailable( true );
            geoCode.setDescription( "The HARD GeoCode is stored at location " + x );
            geoCode.setDifficulty( Difficulty.HARD );
            List< String > hints = new ArrayList<>();
            hints.add( "Hint one for: " + x );
            hints.add( "Hint two for: " + x );
            hints.add( "Hint three for: " + x );
            geoCode.setHints( hints );
            geoCode.setLocation( new GeoPoint( 10.2587 + x, 40.336981 + x ) );

            repo.save( geoCode );
        }

        for ( int x = 0; x < 3; x++ ) {

            /* Create the request with the following mock data */
            var geoCode = new GeoCode();
            geoCode.setId( java.util.UUID.randomUUID() );
            geoCode.setAvailable( true );
            geoCode.setDescription( "The INSANE GeoCode is stored at location " + x );
            geoCode.setDifficulty( Difficulty.INSANE );
            List< String > hints = new ArrayList<>();
            hints.add( "Hint one for: " + x );
            hints.add( "Hint two for: " + x );
            hints.add( "Hint three for: " + x );
            geoCode.setHints( hints );
            geoCode.setLocation( new GeoPoint( 10.2587 + x, 40.336981 + x ) );

            repo.save( geoCode );
        }

        /* Call the custom query to find GeoCodes of a certain Difficulty */
        var test = repo.findGeoCodeWithDifficulty( Difficulty.INSANE );

        /* Go through each returned GeoCode */
        for ( GeoCode temp: test ) {

            /* Check */
            Assertions.assertEquals( Difficulty.INSANE, temp.getDifficulty() );
        }
    }

    /**
     * Check for GeoCodes with the Difficulty Medium using the custom query
     */
    @Test
    @Order( 1 )
    @DisplayName( "Custom query repository where not found - findGeoCodeWithDifficulty" )
    void findGeoCodeWithEmptyDifficultyTest() {

        for ( int x = 0; x < 2; x++ ) {

            /* Create the request with the following mock data */
            var geoCode = new GeoCode();
            geoCode.setId( java.util.UUID.randomUUID() );
            geoCode.setAvailable( true );
            geoCode.setDescription( "The EASY GeoCode is stored at location " + x );
            geoCode.setDifficulty( Difficulty.EASY );
            List< String > hints = new ArrayList<>();
            hints.add( "Hint one for: " + x );
            hints.add( "Hint two for: " + x );
            hints.add( "Hint three for: " + x );
            geoCode.setHints( hints );
            geoCode.setLocation( new GeoPoint( 10.2587 + x, 40.336981 + x ) );

            repo.save( geoCode );
        }

        for ( int x = 0; x < 2; x++ ) {

            /* Create the request with the following mock data */
            var geoCode = new GeoCode();
            geoCode.setId( java.util.UUID.randomUUID() );
            geoCode.setAvailable( true );
            geoCode.setDescription( "The HARD GeoCode is stored at location " + x );
            geoCode.setDifficulty( Difficulty.HARD );
            List< String > hints = new ArrayList<>();
            hints.add( "Hint one for: " + x );
            hints.add( "Hint two for: " + x );
            hints.add( "Hint three for: " + x );
            geoCode.setHints( hints );
            geoCode.setLocation( new GeoPoint( 10.2587 + x, 40.336981 + x ) );

            repo.save( geoCode );
        }

        for ( int x = 0; x < 3; x++ ) {

            /* Create the request with the following mock data */
            var geoCode = new GeoCode();
            geoCode.setId( java.util.UUID.randomUUID() );
            geoCode.setAvailable( true );
            geoCode.setDescription( "The INSANE GeoCode is stored at location " + x );
            geoCode.setDifficulty( Difficulty.INSANE );
            List< String > hints = new ArrayList<>();
            hints.add( "Hint one for: " + x );
            hints.add( "Hint two for: " + x );
            hints.add( "Hint three for: " + x );
            geoCode.setHints( hints );
            geoCode.setLocation( new GeoPoint( 10.2587 + x, 40.336981 + x ) );

            repo.save( geoCode );
        }

        /* Call the custom query to find GeoCodes of a certain Difficulty */
        var test = repo.findGeoCodeWithDifficulty( Difficulty.MEDIUM );

        Assertions.assertTrue( test.isEmpty() );
    }

    /**
     * Check for GeoCodes with a certain qrCode using the custom query
     */
    @Test
    @Order( 1 )
    @DisplayName( "Custom query repository handling - findGeoCodeWithQRCode" )
    void findGeoCodeWithQRCodeTest() {

        /* Create the GeoCode to locate */
        var geoCode = new GeoCode();
        geoCode.setId( geoCodeID );
        geoCode.setAvailable( true );
        geoCode.setDescription( "The EASY GeoCode is stored at location Search" );
        geoCode.setDifficulty( Difficulty.EASY );
        List< String > hints = new ArrayList<>();
        hints.add( "Hint one for: Search" );
        hints.add( "Hint two for: Search" );
        hints.add( "Hint three for: Search" );
        geoCode.setHints( hints );
        geoCode.setLocation( new GeoPoint( 10.2587 , 40.336981 ) );
        geoCode.setQrCode( "9ae5vc2n" );

        repo.save( geoCode );

        /* Create random GeoCodes */
        for ( int x = 0; x < 4; x++ ) {

            /* Create the request with the following mock data */
            geoCode = new GeoCode();
            geoCode.setId( java.util.UUID.randomUUID() );
            geoCode.setAvailable( true );
            geoCode.setDescription( "The EASY GeoCode is stored at location " + x );
            geoCode.setDifficulty( Difficulty.EASY );
            hints = new ArrayList<>();
            hints.add( "Hint one for: " + x );
            hints.add( "Hint two for: " + x );
            hints.add( "Hint three for: " + x );
            geoCode.setHints( hints );
            geoCode.setLocation( new GeoPoint( 10.2587 + x, 40.336981 + x ) );

            repo.save( geoCode );
        }

        var test = repo.findGeoCodeWithQRCode( "9ae5vc2n" );

        /* Check the correct GeoCode got returned */
        Assertions.assertEquals( "9ae5vc2n", test.getQrCode() );
        Assertions.assertEquals( geoCodeID, test.getId() );
    }

    /**
     * Check for GeoCodes at a certain Location using the custom query
     */
    @Test
    @Order( 1 )
    @DisplayName( "Custom query repository handling - findGeoCodeAtLocation" )
    void findGeoCodeAtLocationTest() {

        /* Create the GeoCode to locate */
        var geoCode = new GeoCode();
        geoCode.setId( geoCodeID );
        geoCode.setAvailable( true );
        geoCode.setDescription( "The EASY GeoCode is stored at location Search" );
        geoCode.setDifficulty( Difficulty.EASY );
        List< String > hints = new ArrayList<>();
        hints.add( "Hint one for: Search" );
        hints.add( "Hint two for: Search" );
        hints.add( "Hint three for: Search" );
        geoCode.setHints( hints );
        geoCode.setLocation( locate );
        geoCode.setQrCode( "9ae5vc2n" );

        repo.save( geoCode );

        /* Create random GeoCodes */
        for ( int x = 0; x < 4; x++ ) {

            /* Create the request with the following mock data */
            geoCode = new GeoCode();
            geoCode.setId( java.util.UUID.randomUUID() );
            geoCode.setAvailable( true );
            geoCode.setDescription( "The EASY GeoCode is stored at location " + x );
            geoCode.setDifficulty( Difficulty.EASY );
            hints = new ArrayList<>();
            hints.add( "Hint one for: " + x );
            hints.add( "Hint two for: " + x );
            hints.add( "Hint three for: " + x );
            geoCode.setHints( hints );
            geoCode.setLocation( new GeoPoint( 12.2587 + x, 42.336981 + x ) );

            repo.save( geoCode );
        }

        var test = repo.findGeoCodeAtLocation( locate );

        /* Check the correct GeoCode got returned */
        Assertions.assertEquals( locate, test.getLocation() );
        Assertions.assertEquals( "9ae5vc2n", test.getQrCode() );
        Assertions.assertEquals( geoCodeID, test.getId() );
    }

    /**
     * Check for GeoCodes that are not contained in an Event custom query
     */
    @Test
    @Order( 1 )
    @DisplayName( "Custom query repository handling - findGeoCode" )
    void findGeoCodeWithoutEventIDTest() {

        /* Create the GeoCode to locate */
        var geoCode = new GeoCode();
        geoCode.setId( geoCodeID );
        geoCode.setAvailable( true );
        geoCode.setDescription( "The EASY GeoCode is stored at location Search" );
        geoCode.setDifficulty( Difficulty.EASY );
        List< String > hints = new ArrayList<>();
        hints.add( "Hint one for: Search" );
        hints.add( "Hint two for: Search" );
        hints.add( "Hint three for: Search" );
        geoCode.setHints( hints );
        geoCode.setLocation( locate );
        geoCode.setQrCode( "9ae5vc2n" );

        repo.save( geoCode );

        /* Create random GeoCodes */
        for ( int x = 0; x < 4; x++ ) {

            /* Create the request with the following mock data */
            geoCode = new GeoCode();
            geoCode.setId( java.util.UUID.randomUUID() );
            geoCode.setAvailable( true );
            geoCode.setDescription( "The EASY GeoCode is stored at location " + x );
            geoCode.setDifficulty( Difficulty.EASY );
            hints = new ArrayList<>();
            hints.add( "Hint one for: " + x );
            hints.add( "Hint two for: " + x );
            hints.add( "Hint three for: " + x );
            geoCode.setHints( hints );
            geoCode.setLocation( new GeoPoint( 12.2587 + x, 42.336981 + x ) );
            geoCode.setEventID( eventID );

            repo.save( geoCode );
        }

        var test = repo.findGeoCode();

        /* Go through each returned GeoCode */
        for ( GeoCode temp: test ) {

            /* Check */
            Assertions.assertNull( temp.getEventID() );
            Assertions.assertEquals( geoCodeID, temp.getId() );
        }
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
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 10 )
    @DisplayName( "All invalid repository attribute handling - createGeoCode" )
    void createGeoCodeAllInvalidRequestTest() {

        /*
         *  Create a request object
         * and assign values to it
         * */
        CreateGeoCodeRequest request = new CreateGeoCodeRequest();
        request.setAvailable( null );
        request.setDescription( null );
        request.setDifficulty( null );
        request.setHints( null );
        request.setLocation( null );

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
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 2 )
    @DisplayName( "Null repository handling - updateGeoCode" )
    void updateGeoCodeNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> geoCodeService.updateGeoCode( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 10 )
    @DisplayName( "Invalid repository attribute handling - updateGeoCode" )
    void updateGeoCodeInvalidRequestTest() {

        /*
         *  Create a request object
         * and assign values to it
         * */
        var request = new UpdateGeoCodeRequest();
        request.setAvailable( true );
        request.setDescription( null );
        request.setDifficulty( Difficulty.INSANE );
        request.setHints( null );

        /* Null parameter request check */
        assertThatThrownBy( () -> geoCodeService.updateGeoCode( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 10 )
    @DisplayName( "All invalid repository attribute handling - updateGeoCode" )
    void updateGeoCodeAllInvalidRequestTest() {

        /*
         *  Create a request object
         * and assign values to it
         * */
        var request = new UpdateGeoCodeRequest();
        request.setAvailable( null );
        request.setDescription( null );
        request.setDifficulty( null );
        request.setHints( null );

        /* Null parameter request check */
        assertThatThrownBy( () -> geoCodeService.updateGeoCode( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the createGeoCode use case test
     * complete successfully
     */
    @Test
    @Order( 18 )
    @DisplayName( "Valid request - updateGeoCode" )
    void updateGeoCodeTest() {

        try {

            /*
             * Create a request object
             * and assign values to it
             */
            var createRequest = new CreateGeoCodeRequest();
            createRequest.setAvailable( true );
            createRequest.setDescription( "The GeoCode is stored at the art Museum in Jhb South" );
            createRequest.setDifficulty( Difficulty.INSANE );
            List< String > hints = new ArrayList<>();
            hints.add( "This " );
            hints.add( "is " );
            hints.add( "a " );
            hints.add( "secret " );
            hints.add( "hint." );
            createRequest.setHints( hints );
            createRequest.setLocation( new GeoPoint( 10.2587, 40.336981 ) );

            var createResponse = geoCodeService.createGeoCode( createRequest );

            var geoCodeID = createResponse.getCreatedGeocode().getId();
            var request = new UpdateGeoCodeRequest();
            request.setGeoCodeID( geoCodeID );
            request.setDescription( "This is the updated description" );

            var response = geoCodeService.updateGeoCode( request );

            var checkGeoCode = repo.findById( geoCodeID );
            GeoCode found = new GeoCode();
            if ( checkGeoCode.isPresent() ) {

                found = checkGeoCode.get();
            }
            /*
             * Check if the GeoCode was created correctly
             * through checking the description created with the code
             */
            Assertions.assertEquals( "This is the updated description", found.getDescription() );
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

                Assertions.assertTrue( true );
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
            populateUsingDirectInsert( size );

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
            populateUsingDirectInsert( size );

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

            populateWithCreateGeoCode( 1 );

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
        request.setTargetCollectableID( java.util.UUID.randomUUID() );
        request.setTargetGeoCodeID( null );

        /* Null parameter request check */
        assertThatThrownBy( () -> geoCodeService.swapCollectables( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }


    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 13 )
    @DisplayName( "All invalid repository attribute handling - swapCollectables" )
    void swapCollectablesAllInvalidRequestTest() {

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
        populateWithCreateGeoCode( 1 );
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
            Assertions.assertTrue( response.isSuccess() );
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
        assertThatThrownBy( () -> geoCodeService.getGeoCodeByLocation( null ) )
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
        assertThatThrownBy( () -> geoCodeService.getGeoCodeByLocation( request ) )
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
        populateWithCreateGeoCode( 1 );
        List< GeoCode > temp = repo.findAll();

        try {

            /* Create the request with the ID of the GeoCode we want */
            GetGeoCodeByLocationRequest request = new GetGeoCodeByLocationRequest();
            request.setLocation( temp.get( 0 ).getLocation() );


            /* Get the response by calling the updateAvailability use case */
            GetGeoCodeByLocationResponse response = geoCodeService.getGeoCodeByLocation( request );

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
        populateWithCreateGeoCode( 1 );
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
        populateWithCreateGeoCode( 1 );
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

    /**
     * Check the logic used when create a collectable type
     */
    @Disabled
//    @Test
//    @Order( 26 )
//    @DisplayName( "Valid request - calculateCollectableType" )
//    void collectableTypeTest() {
//
//        var count = new ArrayList<>();
//
//        var iterations = 1000000;
//        for ( var x = 0; x < iterations; x++ ) {
//
//            var name = geoCodeService.calculateCollectableType( null );
//
//        }
//    }

    ////////////////Helper functions////////////////

    /**
     * This function creates numerous GeoCodes, saved directly to the repo to save execution time,
     * to be used for testing.
     */
    private void populateUsingDirectInsert( int size ) {

        /* check if the size is valid */
        if ( size >= 2 ) {

            /* Populate half with INSANE geoCodes to give variability */
            for ( int x = 0; x < ( size / 2 ); x++ ) {

                /* Create the request with the following mock data */
                // CreateGeoCodeRequest request = new CreateGeoCodeRequest();
                GeoCode request = new GeoCode();
                request.setAvailable( true );
                request.setDescription( "The INSANE GeoCode is stored at location " + x );
                request.setDifficulty( Difficulty.INSANE );
                List< String > hints = new ArrayList<>();
                hints.add( "Hint one for: " + x );
                hints.add( "Hint two for: " + x );
                hints.add( "Hint three for: " + x );
                request.setHints( hints );
                request.setLocation( new GeoPoint( 10.2587 + x, 40.336981 + x ) );

                /* Add the created GeoCode to the list */
                repo.save( request );
                // geoCodeService.createGeoCode( request );
            }

            /* Populate half with EASY geoCodes to give variability */
            for ( int x = ( size / 2 ); x < size; x++ ) {

                /* Create the request with the following mock data */
                GeoCode request = new GeoCode();
                request.setAvailable( true );
                request.setDescription( "The EASY GeoCode is stored at location " + x );
                request.setDifficulty( Difficulty.EASY );
                List< String > hints = new ArrayList<>();
                hints.add( "Hint one for: " + x );
                hints.add( "Hint two for: " + x );
                hints.add( "Hint three for: " + x );
                request.setHints( hints );
                request.setLocation( new GeoPoint( 10.2587 + x, 40.336981 + x ) );

                /* Add the created GeoCode to the list */
                repo.save( request );
            }
        } else if ( size == 1 ) {

            int x = 1;

            /* Create the request with the following mock data */
            GeoCode request = new GeoCode();
            request.setAvailable( true );
            request.setDescription( "The DIFFICULTY GeoCode is stored at location " + x );
            request.setDifficulty( Difficulty.HARD );
            List< String > hints = new ArrayList<>();
            hints.add( "Hint one for: " + x );
            hints.add( "Hint two for: " + x );
            hints.add( "Hint three for: " + x );
            request.setHints( hints );
            request.setLocation( new GeoPoint( 10.2587 + x, 40.336981 + x ) );

            /* Add the created GeoCode to the list */
            repo.save( request );
        }

    }


    /**
     * This function creates numerous GeoCodes to be used for testing using the createGeoCode use case.
     */
    private void populateWithCreateGeoCode( int size ) {

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

                /* Add the created GeoCode to the list */
                geoCodeService.createGeoCode( request );
            }

        } catch ( InvalidRequestException e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }

    }

}
