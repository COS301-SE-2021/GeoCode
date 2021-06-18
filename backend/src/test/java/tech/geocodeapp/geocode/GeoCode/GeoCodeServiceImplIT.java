package tech.geocodeapp.geocode.GeoCode;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.geocodeapp.geocode.GeoCode.Service.GeoCodeService;
import tech.geocodeapp.geocode.GeoCode.Service.GeoCodeServiceImpl;



public class GeoCodeServiceImplIT {

    GeoCodeService geoCodeService;

    public GeoCodeServiceImplIT() {

    }

    @BeforeEach
    void setup() {

        geoCodeService = new GeoCodeServiceImpl( new GeoCodeMockRepository() );
    }

    @Test
    public void createGeoCodeTest() {


    }

    @Test
    public void createGeoCodeExceptionTest() {


    }

    @Test
    public void getAllGeoCodeTest() {


    }

    @Test
    public void getGeoCodesByDifficultyTest() {


    }

    @Test
    public void getHintsTest() {


    }

    @Test
    public void swapCollectablesTest() {


    }

    @Test
    public void updateAvailabilityTest() {


    }
}
