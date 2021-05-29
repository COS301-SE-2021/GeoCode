package tech.geocodeapp.geocode.GeoCode.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import tech.geocodeapp.geocode.APIObjectMapper;


import tech.geocodeapp.geocode.GeoCode.Service.GeoCodeServiceImpl;

/**
 * This class implements the functionality of the GeoCodeAPI interface.
 */
@CrossOrigin("*")
@RestController
public class GeoCodeController {

    @Autowired
    private GeoCodeServiceImpl geoCodeService;

    @Autowired
    private APIObjectMapper apiObjectMapper;


}
