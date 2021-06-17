package tech.geocodeapp.geocode.GeoCode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
public class GeoCodeServiceImplTest {

    GeoCodeService geoCodeService;

    GeoCodeServiceImplTest() {

    }

    @BeforeEach
    void setup() {
        geoCodeService = new GeoCodeServiceImpl( new GeoCodeMockRepository() );
    }

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

    @Test
    public void createGeoCodeExceptionTest() {

        /* Null request check */
        assertThatThrownBy( ()->geoCodeService.createGeoCode( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining("The given request is empty." );

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
                .hasMessageContaining("The given request is missing parameter/s." );

    }


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

    @Test
    public void getGeoCodesByDifficultyTest() {


    }

    @Test
    public void getHintsTest() {


    }


}
