package tech.geocodeapp.geocode.leaderboard;

import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.user.model.User;
import tech.geocodeapp.geocode.user.request.*;
import tech.geocodeapp.geocode.user.response.*;
import tech.geocodeapp.geocode.user.service.UserService;

public class UserMockService implements UserService {


    @Override
    public GetCurrentCollectableResponse getCurrentCollectable(GetCurrentCollectableRequest request) throws NullRequestParameterException {
        return null;
    }

    @Override
    public GetUserTrackableResponse getUserTrackable(GetUserTrackableRequest request) throws NullRequestParameterException {
        return null;
    }

    @Override
    public UpdateLocationResponse updateLocation(UpdateLocationRequest request) throws NullRequestParameterException {
        return null;
    }

    @Override
    public GetFoundCollectableTypesResponse getFoundCollectableTypes(GetFoundCollectableTypesRequest request) throws NullRequestParameterException {
        return null;
    }

    @Override
    public GetFoundGeoCodesResponse getFoundGeoCodes(GetFoundGeoCodesRequest request) throws NullRequestParameterException {
        return null;
    }

    @Override
    public GetOwnedGeoCodesResponse getOwnedGeoCodes(GetOwnedGeoCodesRequest request) throws NullRequestParameterException {
        return null;
    }

    @Override
    public GetMyLeaderboardsResponse getMyLeaderboards(GetMyLeaderboardsRequest request) throws NullRequestParameterException {
        return null;
    }

    @Override
    public GetUserByIdResponse getUserById(GetUserByIdRequest request) throws NullRequestParameterException {
        return null;
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public RegisterNewUserResponse registerNewUser(RegisterNewUserRequest request) throws NullRequestParameterException {
        return null;
    }

    @Override
    public SwapCollectableResponse swapCollectable(SwapCollectableRequest request) throws NullRequestParameterException {
        return null;
    }
}
