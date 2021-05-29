package tech.geocodeapp.geocode.Trackable.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


import tech.geocodeapp.geocode.Trackable.Service.TrackableServiceImpl;


/**
 * This class implements the functionality of the TrackableAPI interface.
 */
@CrossOrigin("*")
@RestController
public class TrackableController {

    @Autowired
    private TrackableServiceImpl trackableService;

}
