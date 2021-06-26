package tech.geocodeapp.geocode.trackable.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tech.geocodeapp.geocode.trackable.repository.TrackableRepository;


/**
 * This class implements the TrackableService interface
 */
@Service
public class TrackableServiceImpl implements TrackableService {

    @Autowired
    private TrackableRepository trackableRepo;
    
    public TrackableServiceImpl() {

    }


}
