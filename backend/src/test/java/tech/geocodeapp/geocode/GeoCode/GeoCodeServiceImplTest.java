package tech.geocodeapp.geocode.GeoCode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.model.*;
import tech.geocodeapp.geocode.GeoCode.Exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.GeoCode.Exceptions.QRCodeException;
import tech.geocodeapp.geocode.GeoCode.Exceptions.RepoException;
import tech.geocodeapp.geocode.GeoCode.Repository.GeoCodeRepository;
import tech.geocodeapp.geocode.GeoCode.Service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith( MockitoExtension.class )
public class GeoCodeServiceImplTest {

    @Mock
    private GeoCodeRepository geoCodeRepo;

    GeoCodeService geoCodeService;

    GeoCodeServiceImplTest() {

    }

    @BeforeEach
    void setup() {

        geoCodeService = new GeoCodeServiceImpl( geoCodeRepo );
    }

    @Test
    public void createGeoCodeTest() {

        try {

            /**
             *  Create a request object
             * and assign values to it
             * */
            CreateGeoCodeRequest request = new CreateGeoCodeRequest();
            request.setId( UUID.randomUUID() );
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

            geoCodeService.createGeoCode( request );
        } catch ( InvalidRequestException | QRCodeException | RepoException e ) {

            e.printStackTrace();
        }
    }

    @Test
    public void createGeoCodeExceptionTest() {

        /** Null request check */
        assertThatThrownBy( ()->geoCodeService.createGeoCode( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining("The given request is empty." );

        /**
         *  Create a request object
         * and assign values to it
         * */
        CreateGeoCodeRequest request = new CreateGeoCodeRequest();
        request.setId( UUID.randomUUID() );
        request.setAvailable( true );
        request.setDescription( null );
        request.setDifficulty( Difficulty.INSANE );
        request.setHints( null );
        request.setLocation( "Jhb" );

        /** Null parameter request check */
        assertThatThrownBy( ()->geoCodeService.createGeoCode( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining("The given request is missing parameter/s." );

    }


    @Test
    public void getAllGeoCodeTest() {

        try {

            geoCodeService.getAllGeoCode( );
        } catch ( RepoException e ) {

            e.printStackTrace();
        }
    }
}
