package tech.geocodeapp.geocode.GeoCode.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.GeoCode.Repository.GeoCodeRepository;


/**
 * This class implements the UserService interface
 */
@Service
public class GeoCodeServiceImpl implements GeoCodeService {

    @Autowired
    private GeoCodeRepository geoCodeRepo;

    public GeoCodeServiceImpl() {

    }


}
