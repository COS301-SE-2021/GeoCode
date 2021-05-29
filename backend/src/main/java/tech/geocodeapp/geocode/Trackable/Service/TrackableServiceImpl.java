package tech.geocodeapp.geocode.Trackable.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tech.geocodeapp.geocode.Trackable.Repository.TrackableRepository;


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
