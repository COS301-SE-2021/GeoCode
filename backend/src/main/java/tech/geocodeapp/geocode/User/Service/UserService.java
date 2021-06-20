package tech.geocodeapp.geocode.User.Service;

import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.User.Exception.NullUserRequestParameterException;
import tech.geocodeapp.geocode.User.Model.User;
import tech.geocodeapp.geocode.User.Request.GetCurrentCollectableRequest;
import tech.geocodeapp.geocode.User.Request.GetUserTrackableRequest;
import tech.geocodeapp.geocode.User.Request.SwapCollectableRequest;
import tech.geocodeapp.geocode.User.Request.UpdateLocationRequest;
import tech.geocodeapp.geocode.User.Response.GetCurrentCollectableResponse;
import tech.geocodeapp.geocode.User.Response.GetUserTrackableResponse;
import tech.geocodeapp.geocode.User.Response.SwapCollectableResponse;
import tech.geocodeapp.geocode.User.Response.UpdateLocationResponse;

import java.util.UUID;

/**
 * This interface is for the service for the User subsystem
 */
@Service
public interface UserService {
    //U1.1 getCurrentCollectable
    GetCurrentCollectableResponse getCurrentCollectable(GetCurrentCollectableRequest request) throws NullUserRequestParameterException;

    //U1.2 getUserTrackable
    GetUserTrackableResponse getUserTrackable(GetUserTrackableRequest request) throws NullUserRequestParameterException;

    //U1.3 updateLocation
    UpdateLocationResponse updateLocation(UpdateLocationRequest request) throws NullUserRequestParameterException;

    //helper functions
    User getUserById(UUID id);
    User getCurrentUser();
    void registerNewUser(UUID id, String username);
}
