package tech.geocodeapp.geocode.user.service;

import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.user.exception.NullUserRequestParameterException;
import tech.geocodeapp.geocode.user.model.User;
import tech.geocodeapp.geocode.user.request.*;
import tech.geocodeapp.geocode.user.response.*;

import java.util.UUID;

/**
 * This interface is for the service for the User subsystem
 */
@Service
public interface UserService {
    //U1.1 getCurrentCollectable
    GetCurrentCollectableResponse getCurrentCollectable( GetCurrentCollectableRequest request ) throws NullUserRequestParameterException;

    //U1.2 getUserTrackable
    GetUserTrackableResponse getUserTrackable( GetUserTrackableRequest request ) throws NullUserRequestParameterException;

    //U1.3 updateLocation
    UpdateLocationResponse updateLocation(UpdateLocationRequest request) throws NullUserRequestParameterException;

    //User helper functions
    User getUserById(UUID id);
    User getCurrentUser();
    void registerNewUser(UUID id, String username);

    //GeoCode helper functions
    Collectable swapCollectable(Collectable collectable);
}
