package tech.geocodeapp.geocode.GeoCode;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.mockito.Mock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import tech.geocodeapp.geocode.Collectable.*;
import tech.geocodeapp.geocode.Collectable.Model.*;
import tech.geocodeapp.geocode.Collectable.Service.*;
import tech.geocodeapp.geocode.User.Service.*;
import tech.geocodeapp.geocode.GeoCode.Exceptions.*;
import tech.geocodeapp.geocode.GeoCode.Model.GeoCode;
import tech.geocodeapp.geocode.GeoCode.Service.*;
import tech.geocodeapp.geocode.GeoCode.Response.*;
import tech.geocodeapp.geocode.GeoCode.Request.*;

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
     * A mock service for the User subsystem
     *
     * This is used to access User subsystem in some use cases
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
     * Create the GeoCodeServiceImpl with the relevant repositories.
     *
     * This is done to ensure a repository with no data is created each time
     * and the service implementation contains fresh code that has not been affected
     * by some other test or data.
     */
    @BeforeEach
    void setup() {

        /* Create a new repository instance and make sure there is no data in it */
        repo = new GeoCodeMockRepository();
        repo.deleteAll();

        /* Ensure a collectable can be made */
        CollectableTypeMockRepository typeMockRepo = new CollectableTypeMockRepository();

            CollectableType type = new CollectableType();
            type.setId( UUID.fromString( "f94d35a2-ca09-49fc-9fdd-ad0bac0b8dd0" ) );
            type.setName( "name" );
            type.setImage( "Image" );
            type.setRarity( Rarity.RARE );
            type.setSet( new CollectableSet() );

            typeMockRepo.save( type );

        /*  */
        collectableService = new CollectableServiceImpl( new CollectableMockRepository(),
                                                         new CollectableSetMockRepository(),
                                                         typeMockRepo );

        try {

            /* Create a new GeoCodeServiceImpl instance to access the different use cases */
            geoCodeService = new GeoCodeServiceImpl( repo, collectableService, userService );
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
    @DisplayName( "Test null repository handling" )
    void RepositoryNullTest() {

        /* Null request check */
        assertThatThrownBy( () -> geoCodeService = new GeoCodeServiceImpl( null, collectableService, userService ) )
                .isInstanceOf( RepoException.class )
                .hasMessageContaining( "The given repository does not exist." );
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    @Order( 2 )
    @DisplayName( "Test createGeoCode null repository handling" )
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
    @DisplayName( "Test createGeoCode invalid repository attribute handling" )
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
        request.setLatitude( "Jhb" );
        request.setLongitude( "Jhb" );

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
    @DisplayName( "Test valid createGeoCode request" )
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
            request.setLatitude( "Jhb" );
            request.setLongitude( "Jhb" );

            CreateGeoCodeResponse response = geoCodeService.createGeoCode( request );

            /*
             * Check if the GeoCode was created correctly
             * through checking the description created with the code
             */
            Assertions.assertTrue( response.isIsSuccess() );

        } catch ( InvalidRequestException | RepoException e ) {

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
    @DisplayName( "Test valid getAllGeoCode request" )
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
            request.setLatitude( "Jhb" );
            request.setLongitude( "Jhb" );

            /* create the GeoCode in the repository */
            geoCodeService.createGeoCode( request );

            /* Get the response by calling the getAllGeoCodes use case */
            GetGeoCodesResponse response = geoCodeService.getAllGeoCodes();

            /* Get a geocode from the response */
            List< GeoCode > geocodes = response.getGeocodes();

            /*
             * Check if all the GeoCodes were returned correctly
             * through checking the description created with the code
             */
            Assertions.assertEquals( "The GeoCode is stored at the art Museum in Jhb South", geocodes.get( 0 ).getDescription() );
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
    @DisplayName( "Test getGeoCodesByDifficulty null repository handling" )
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
    @DisplayName( "Test getGeoCodesByDifficulty invalid repository attribute handling" )
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
    @DisplayName( "Test valid getGeoCodesByDifficulty request" )
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
    @Order( 4 )
    @DisplayName( "Test getHints null repository handling" )
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
    @DisplayName( "Test getHints invalid repository attribute handling" )
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
    @DisplayName( "Test valid getHints request" )
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
    @DisplayName( "Test swapCollectables null repository handling" )
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
    @DisplayName( "Test swapCollectables invalid repository attribute handling" )
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
    @DisplayName( "Test updateAvailability null repository handling" )
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
    @DisplayName( "Test updateAvailability invalid repository attribute handling" )
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
    @DisplayName( "Test valid updateAvailability request" )
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
    @DisplayName( "Test getGeoCodesByLocation null repository handling" )
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
    @DisplayName( "Test getGeoCodesByLocation invalid repository attribute handling" )
     void getGeoCodesByLocationInvalidRequestTest() {

        /*
         *  Create a request object
         * and assign values to it
         */
        GetGeoCodeByLocationRequest request = new GetGeoCodeByLocationRequest();
        request.setLatitude( null );
        request.setLongitude( null );

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
    @DisplayName( "Test valid getGeoCodesByLocation request" )
     void getGeoCodesByLocationTest() {

        /* Create a GeoCode */
        populate( 1 );
        List< GeoCode > temp = repo.findAll();

        try {

            /* Create the request with the ID of the GeoCode we want */
            GetGeoCodeByLocationRequest request = new GetGeoCodeByLocationRequest();
            request.setLatitude( temp.get( 0 ).getLatitude() );
            request.setLongitude( temp.get( 0 ).getLongitude() );


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
    @DisplayName( "Test getGeoCodesByQRCode null repository handling" )
     void getGeoCodesByQRCodeNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( () -> geoCodeService.getGeocodeByQRCode( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqEmptyError );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    @Order( 16 )
    @DisplayName( "Test getGeoCodesByQRCode invalid repository attribute handling" )
     void getGeoCodesByQRCodeInvalidRequestTest() {

        /*
         *  Create a request object
         * and assign values to it
         */
        GetGeoCodeByQRCodeRequest request = new GetGeoCodeByQRCodeRequest();
        request.setQrCode( null );

        /* Null parameter request check */
        assertThatThrownBy( () -> geoCodeService.getGeocodeByQRCode( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( reqParamError );
    }

    /**
     * Using valid data does the getGeoCodesByLocation use case test
     * complete successfully
     */
    @Test
    @Order( 25 )
    @DisplayName( "Test valid getGeoCodesByQRCode request" )
     void getGeoCodesByQRCodeTest() {

        /* Create a GeoCode */
        populate( 1 );
        List< GeoCode > temp = repo.findAll();

        try {

            /* Create the request with the ID of the GeoCode we want */
            GetGeoCodeByQRCodeRequest request = new GetGeoCodeByQRCodeRequest();
            request.setQrCode( temp.get( 0 ).getQrCode() );

            /* Get the response by calling the updateAvailability use case */
            GetGeoCodeByQRCodeResponse response = geoCodeService.getGeocodeByQRCode( request );

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
    @DisplayName( "Test getCollectables null repository handling" )
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
    @DisplayName( "Test getCollectables invalid repository attribute handling" )
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
    @DisplayName( "Test valid getCollectables request" )
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

            Assertions.assertEquals( "name", response.getCollectables().get( 0 ).getType().getName() );
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
                    request.setLatitude( "Lat " + x );
                    request.setLongitude( "Long " + x );


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
                    request.setLatitude( "Lat " + x );
                    request.setLongitude( "Long " + x );

                    /* Add the created GeoCode to the list */
                    geoCodeService.createGeoCode( request );
                }
            } else if ( size == 1 ) {

                int x = 1;

                /* Create the request with the following mock data */
                CreateGeoCodeRequest request = new CreateGeoCodeRequest();
                request.setAvailable( true );
                request.setDescription( "The DIFFICULTY GeoCode is stored at location " + x );
                request.setDifficulty( Difficulty.DIFFICULTY );
                List< String > hints = new ArrayList<>();
                    hints.add( "Hint one for: " + x );
                    hints.add( "Hint two for: " + x );
                    hints.add( "Hint three for: " + x );
                request.setHints( hints );
                request.setLatitude( "Lat " + x );
                request.setLongitude( "Long " + x );

                /* Add the created GeoCode to the list */
                geoCodeService.createGeoCode( request );
            }

        } catch ( InvalidRequestException | RepoException e ) {

            /* An error occurred, print the stack to identify */
            e.printStackTrace();
        }

    }

}
