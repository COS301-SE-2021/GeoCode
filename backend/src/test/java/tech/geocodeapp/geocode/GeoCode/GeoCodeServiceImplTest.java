package tech.geocodeapp.geocode.GeoCode;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import tech.geocodeapp.geocode.Collectable.Model.Difficulty;
import tech.geocodeapp.geocode.GeoCode.Exceptions.*;
import tech.geocodeapp.geocode.GeoCode.Model.GeoCode;
import tech.geocodeapp.geocode.GeoCode.Service.*;
import tech.geocodeapp.geocode.GeoCode.Response.*;
import tech.geocodeapp.geocode.GeoCode.Request.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith( MockitoExtension.class )
public class GeoCodeServiceImplTest {

    /**
     * The service for the GeoCode subsystem
     *
     * This is used to access the different use cases
     * needed for functionality
     */
    GeoCodeService geoCodeService;

    /**
     * Default Constructor
     */
    GeoCodeServiceImplTest() {

    }

    /**
     * Create the GeoCodeServiceImpl with the relevant repositories.
     *
     * This is done to ensure a repository with no data is created each time
     * and the service implementation contains fresh code that has not been affected
     * by some other test or data.
     */
    @BeforeEach
    void setup() throws RepoException {

        geoCodeService = new GeoCodeServiceImpl( new GeoCodeMockRepository() );
    }

    /**
     * Check how the constructor handles the repository being null
     */
    @Test
    public void RepositoryNullTest() {

        /* Null request check */
        assertThatThrownBy( ()->geoCodeService = new GeoCodeServiceImpl( null ) )
                .isInstanceOf( RepoException.class )
                .hasMessageContaining( "The given repository does not exist." );
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    public void createGeoCodeNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( ()->geoCodeService.createGeoCode( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( "The given request is empty." );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    public void createGeoCodeInvalidRequestTest() {

        /*
         *  Create a request object
         * and assign values to it
         * */
        CreateGeoCodeRequest request = new CreateGeoCodeRequest();
        request.setAvailable( true );
        request.setDescription( null );
        request.setDifficulty( Difficulty.INSANE );
        request.setHints( null );
        request.setLocation( "Jhb" );

        /* Null parameter request check */
        assertThatThrownBy( ()->geoCodeService.createGeoCode( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( "The given request is missing parameter/s." );
    }

    /**
     * Using valid data does the createGeoCode use case test
     * complete successfully
     */
    @Test
    public void createGeoCodeTest() {

        try {

            /*
             *  Create a request object
             * and assign values to it
             */
            CreateGeoCodeRequest request = new CreateGeoCodeRequest();
            request.setAvailable( true );
            request.setDescription( "The GeoCode is stored at the art Museum in Jhb South" );
            request.setDifficulty( Difficulty.INSANE );
            List<String> hints = new ArrayList<>();
                hints.add( "This " );
                hints.add( "is " );
                hints.add( "a " );
                hints.add( "secret " );
                hints.add( "hint." );
            request.setHints( hints );
            request.setLocation( "Jhb" );

            CreateGeoCodeResponse response = geoCodeService.createGeoCode( request );
            Assertions.assertEquals(response.getGeoCode().getDescription(), request.getDescription());

        } catch ( InvalidRequestException | QRCodeException | RepoException e ) {

            e.printStackTrace();
        }
    }

    /**
     * Using valid data does the getAllGeoCode use case test
     * complete successfully
     */
    @Test
    public void getAllGeoCodeTest() {

        try {
            CreateGeoCodeRequest request = new CreateGeoCodeRequest();
            request.setAvailable( true );
            request.setDescription( "The GeoCode is stored at the art Museum in Jhb South" );
            request.setDifficulty( Difficulty.INSANE );
            List<String> hints = new ArrayList<>();
            hints.add( "This " );
            hints.add( "is " );
            hints.add( "a " );
            hints.add( "secret " );
            hints.add( "hint." );
            request.setHints( hints );
            request.setLocation( "Jhb" );

            CreateGeoCodeResponse createResp = geoCodeService.createGeoCode( request );

            GetGeoCodesResponse response = geoCodeService.getAllGeoCodes( );
            List<GeoCode> geocodes = response.getGeocodes();
            Assertions.assertEquals(geocodes.get(0).getDescription(), "The GeoCode is stored at the art Museum in Jhb South");
        } catch ( Exception e ) {

            e.printStackTrace();
        }
    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    public void getGeoCodesByDifficultyNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( ()->geoCodeService.getGeoCodesByDifficulty( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( "The given request is empty." );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    public void getGeoCodesByDifficultyInvalidRequestTest() {

        /*
         *  Create a request object
         * and assign values to it
         * */
        GetGeoCodesByDifficultyRequest request = new GetGeoCodesByDifficultyRequest();
        request.setDifficulty( Difficulty.INSANE );

        /* Null parameter request check */
        assertThatThrownBy( ()->geoCodeService.getGeoCodesByDifficulty( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( "The given request is missing parameter/s." );
    }

    /**
     * Using valid data does the getGeoCodesByDifficulty use case test
     * complete successfully
     */
    @Test
    public void getGeoCodesByDifficultyTest() {


    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    public void getHintsNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( ()->geoCodeService.getHints( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( "The given request is empty." );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    public void getHintsInvalidRequestTest() {

        /*
         *  Create a request object
         * and assign values to it
         * */
        GetHintsRequest request = new GetHintsRequest();

        /* Null parameter request check */
        assertThatThrownBy( ()->geoCodeService.getHints( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( "The given request is missing parameter/s." );
    }

    /**
     * Using valid data does the getHints use case test
     * complete successfully
     */
    @Test
    public void getHintsTest() {


    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    public void swapCollectablesNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( ()->geoCodeService.swapCollectables( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( "The given request is empty." );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    public void swapCollectablesInvalidRequestTest() {

        /*
         *  Create a request object
         * and assign values to it
         * */
        SwapCollectablesRequest request = new SwapCollectablesRequest();

        /* Null parameter request check */
        assertThatThrownBy( ()->geoCodeService.swapCollectables( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( "The given request is missing parameter/s." );
    }

    /**
     * Using valid data does the swapCollectables use case test
     * complete successfully
     */
    @Test
    public void swapCollectablesTest() {


    }

    /**
     * Check how the use case handles the request being null
     */
    @Test
    public void updateAvailabilityNullRequestTest() {

        /* Null request check */
        assertThatThrownBy( ()->geoCodeService.updateAvailability( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( "The given request is empty." );
    }

    /**
     * Check how the use case handles an invalid request
     */
    @Test
    public void updateAvailabilityInvalidRequestTest() {

        /*
         *  Create a request object
         * and assign values to it
         * */
        UpdateAvailabilityRequest request = new UpdateAvailabilityRequest();
        request.setAvailable( true );

        /* Null parameter request check */
        assertThatThrownBy( ()->geoCodeService.updateAvailability( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( "The given request is missing parameter/s." );
    }

    /**
     * Using valid data does the updateAvailability use case test
     * complete successfully
     */
    @Test
    public void updateAvailabilityTest() {


    }
}
