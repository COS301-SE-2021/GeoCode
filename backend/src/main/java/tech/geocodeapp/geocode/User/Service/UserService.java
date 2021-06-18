package tech.geocodeapp.geocode.User.Service;

import org.springframework.stereotype.Service;
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
    GetCurrentCollectableResponse getCurrentCollectable(GetCurrentCollectableRequest request);
    GetUserTrackableResponse getUserTrackable(GetUserTrackableRequest request);
    SwapCollectableResponse swapCollectable(SwapCollectableRequest request);
    UpdateLocationResponse updateLocation(UpdateLocationRequest request);
    User getUserById(UUID id);
    User getCurrentUser();
    void registerNewUser(UUID id, String username);
}
