package tech.geocodeapp.geocode.GeoCode.Service;

import org.springframework.stereotype.Service;


import tech.geocodeapp.geocode.GeoCode.Repository.GeoCodeRepository;
import tech.geocodeapp.geocode.GeoCode.Request.CreateGeoCodeRequest;
import tech.geocodeapp.geocode.GeoCode.Response.CreateGeoCodeResponse;


/**
 * This class implements the UserService interface
 */
@Service
public class GeoCodeServiceImpl implements GeoCodeService {

    private final GeoCodeRepository geoCodeRepo;

    public GeoCodeServiceImpl( GeoCodeRepository geoCodeRepo ) {

        this.geoCodeRepo = geoCodeRepo;
    }

    @Override
    public CreateGeoCodeResponse createGeoCode( CreateGeoCodeRequest request ) {

        return null;
    }

}