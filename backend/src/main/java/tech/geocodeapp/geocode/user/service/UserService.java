package tech.geocodeapp.geocode.user.service;

import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.user.model.User;
import tech.geocodeapp.geocode.user.request.*;
import tech.geocodeapp.geocode.user.response.*;

/**
 * This interface is for the service for the User subsystem
 */
public interface UserService {
    //U1.1 getCurrentCollectable
    GetCurrentCollectableResponse getCurrentCollectable(GetCurrentCollectableRequest request) throws NullRequestParameterException;

    //U1.2 getUserTrackable
    GetUserTrackableResponse getUserTrackable(GetUserTrackableRequest request) throws NullRequestParameterException;

    //U1.3 updateLocation
    UpdateLocationResponse updateLocation(UpdateLocationRequest request) throws NullRequestParameterException;

    //U1.4 getFoundCollectableTypes
    GetFoundCollectableTypesResponse getFoundCollectableTypes(GetFoundCollectableTypesRequest request) throws NullRequestParameterException;

    //U1.5 getFoundGeoCodes
    GetFoundGeoCodesResponse getFoundGeoCodes(GetFoundGeoCodesRequest request) throws NullRequestParameterException;

    //U1.6 getOwnedGeoCodes
    GetOwnedGeoCodesResponse getOwnedGeoCodes(GetOwnedGeoCodesRequest request) throws NullRequestParameterException;

    //U1.7 getMyLeaderboards
    GetMyLeaderboardsResponse getMyLeaderboards(GetMyLeaderboardsRequest request) throws NullRequestParameterException;

    //U1.8 getMyMissions
    GetMyMissionsResponse getMyMissions(GetMyMissionsRequest request) throws NullRequestParameterException;

    //User helper functions

    //U1.9 addToOwnedGeoCodes
    AddToOwnedGeoCodesResponse addToOwnedGeoCodes(AddToOwnedGeoCodesRequest request) throws NullRequestParameterException;

    //U1.10 addToFoundGeoCodes
    AddToFoundGeoCodesResponse addToFoundGeoCodes(AddToFoundGeoCodesRequest request) throws NullRequestParameterException;

    //U1.11 addToFoundCollectableTypes
    AddToFoundCollectableTypesResponse addToFoundCollectableTypes(AddToFoundCollectableTypesRequest request) throws NullRequestParameterException;

    //U1.12 getUserById
    GetUserByIdResponse getUserById(GetUserByIdRequest request) throws NullRequestParameterException;

    //U1.13 getCurrentUser
    User getCurrentUser();

    //U1.14 getCurrentUserID
    /** @deprecated use CurrentUserDetails.getID() */
    @Deprecated
    java.util.UUID getCurrentUserID();

    //U1.15 handleLogin
    Response handleLogin(HandleLoginRequest request) throws NullRequestParameterException;

    //GeoCode helper functions

    //U1.16 swapCollectable
    SwapCollectableResponse swapCollectable(SwapCollectableRequest request) throws NullRequestParameterException;

    //U1.17 addToMyMissions
    AddToMyMissionsResponse addToMyMissions(AddToMyMissionsRequest request) throws NullRequestParameterException;
}
