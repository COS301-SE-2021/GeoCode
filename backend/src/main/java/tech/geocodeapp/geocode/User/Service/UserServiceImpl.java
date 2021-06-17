package tech.geocodeapp.geocode.User.Service;

import org.springframework.stereotype.Service;


import tech.geocodeapp.geocode.User.Repository.UserRepository;
import tech.geocodeapp.geocode.User.Request.GetCurrentCollectableRequest;
import tech.geocodeapp.geocode.User.Request.GetUserTrackableRequest;
import tech.geocodeapp.geocode.User.Request.SwapCollectableRequest;
import tech.geocodeapp.geocode.User.Request.UpdateLocationRequest;
import tech.geocodeapp.geocode.User.Response.GetCurrentCollectableResponse;
import tech.geocodeapp.geocode.User.Response.GetUserTrackableResponse;
import tech.geocodeapp.geocode.User.Response.SwapCollectableResponse;
import tech.geocodeapp.geocode.User.Response.UpdateLocationResponse;
//import tech.geocodeapp.geocode.User.Service.UserService;

import javax.transaction.Transactional;

/**
 * This class implements the UserService interface
 */
@Service
public class UserServiceImpl /*implements UserService*/ {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public GetCurrentCollectableResponse getCurrentCollectable(GetCurrentCollectableRequest request){
        if (request == null) {
            return new ;
        }
    }

    @Transactional
    public GetUserTrackableResponse getUserTrackable(GetUserTrackableRequest request){
        if (request == null) {
            return new ;
        }
    }

    @Transactional
    public SwapCollectableResponse swapCollectable(SwapCollectableRequest request){
        if (request == null) {
            return new ;
        }
    }

    @Transactional
    public UpdateLocationResponse updateLocation(UpdateLocationRequest request){
        if (request == null) {
            return new ;
        }
    }
}
