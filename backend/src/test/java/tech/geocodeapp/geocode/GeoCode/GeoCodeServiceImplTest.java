package tech.geocodeapp.geocode.GeoCode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tech.geocodeapp.geocode.GeoCode.Exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.GeoCode.Exceptions.QRCodeException;
import tech.geocodeapp.geocode.GeoCode.Exceptions.RepoException;
import tech.geocodeapp.geocode.GeoCode.Repository.GeoCodeRepository;
import tech.geocodeapp.geocode.GeoCode.Request.CreateGeoCodeRequest;
import tech.geocodeapp.geocode.GeoCode.Request.GetAllGeoCodesRequest;
import tech.geocodeapp.geocode.GeoCode.Service.*;


public class GeoCodeServiceImplTest {

    @Autowired
    private GeoCodeRepository geoCodeRepo;

    GeoCodeService geoCodeService;

    GeoCodeServiceImplTest() {

        geoCodeService = new GeoCodeServiceImpl( geoCodeRepo );
    }

    @BeforeEach
    void setup() {

        geoCodeService = new GeoCodeServiceImpl( geoCodeRepo );
    }

    @Test
    public void createGeoCodeTest() {

        try {

            geoCodeService.createGeoCode( new CreateGeoCodeRequest() );
        } catch ( InvalidRequestException | QRCodeException e ) {

            e.printStackTrace();
        }
    }

    @Test
    public void getAllGeoCodeTest() {

        try {

            geoCodeService.getAllGeoCode( new GetAllGeoCodesRequest() );
        } catch ( RepoException e ) {

            e.printStackTrace();
        }
    }
}
