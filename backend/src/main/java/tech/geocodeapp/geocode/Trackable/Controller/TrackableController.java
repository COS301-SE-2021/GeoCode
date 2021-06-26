package tech.geocodeapp.geocode.trackable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import tech.geocodeapp.geocode.trackable.service.TrackableServiceImpl;


/**
 * This class implements the functionality of the TrackableAPI interface.
 */
@CrossOrigin("*")
@RestController
public class TrackableController {

    @Autowired
    private TrackableServiceImpl trackableService;

}
