package tech.geocodeapp.geocode.User.Service;

import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.Collectable.Model.Collectable;
import tech.geocodeapp.geocode.User.Exception.NullUserRequestParameterException;
import tech.geocodeapp.geocode.User.Model.User;
import tech.geocodeapp.geocode.User.Request.*;
import tech.geocodeapp.geocode.User.Response.*;

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

    //U1.4 getFoundCollectableTypes
    GetFoundCollectableTypesResponse getFoundCollectableTypes(GetFoundCollectableTypesRequest request) throws NullUserRequestParameterException;

    //U1.5 getFoundGeoCodes
    GetFoundGeoCodesResponse getFoundGeoCodes(GetFoundGeoCodesRequest request) throws NullUserRequestParameterException;

    //U1.6 getOwnedGeoCodes
    GetOwnedGeoCodesResponse getOwnedGeoCodes(GetOwnedGeoCodesRequest request) throws NullUserRequestParameterException;

    //User helper functions
    User getUserById(UUID id);
    User getCurrentUser();
    void registerNewUser(UUID id, String username);

    //GeoCode helper functions
    Collectable swapCollectable(UUID newCollectableID);
}
