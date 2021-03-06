package tech.geocodeapp.geocode.leaderboard;

import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.general.security.CurrentUserDetails;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.user.model.User;
import tech.geocodeapp.geocode.user.repository.UserRepository;
import tech.geocodeapp.geocode.user.request.*;
import tech.geocodeapp.geocode.user.response.*;
import tech.geocodeapp.geocode.user.service.UserService;

import java.util.Optional;

public class UserMockService implements UserService {

    private final UserRepository userRepo;

    public UserMockService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

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
    public GetMyMissionsResponse getMyMissions(GetMyMissionsRequest request) throws NullRequestParameterException {
        return null;
    }

    @Override
    public AddToOwnedGeoCodesResponse addToOwnedGeoCodes(AddToOwnedGeoCodesRequest request) throws NullRequestParameterException {
        return null;
    }

    @Override
    public AddToFoundGeoCodesResponse addToFoundGeoCodes(AddToFoundGeoCodesRequest request) throws NullRequestParameterException {
        return null;
    }

    @Override
    public AddToFoundCollectableTypesResponse addToFoundCollectableTypes(AddToFoundCollectableTypesRequest request) throws NullRequestParameterException {
        return null;
    }

    @Override
    public GetUserByIdResponse getUserById(GetUserByIdRequest request) throws NullRequestParameterException {
        Optional<User> foundUser = userRepo.findById(request.getUserID());
        if(foundUser.isEmpty()){
            return new GetUserByIdResponse(false, "The User was not found", null);
        }else{
            return new GetUserByIdResponse(true, "The User was found", foundUser.get());
        }
    }

    @Override
    public User getCurrentUser() {
        return new User(CurrentUserDetails.getID(), CurrentUserDetails.getUsername());
    }

    @Override
    public java.util.UUID getCurrentUserID() {
        return CurrentUserDetails.getID();
    }

    /**
     * Only set userId and username for the purpose of this mock as nothing else is required by the leaderboard subsystems unit tests
     * @param request a request with the userId and username of the new user
     * @return the response indicating success or failure
     */
    @Override
    public Response handleLogin(HandleLoginRequest request) throws NullRequestParameterException {
        User user = new User(CurrentUserDetails.getID(), CurrentUserDetails.getUsername());
        userRepo.save(user);
        return new Response(true, "New User registered");
    }

    @Override
    public SwapCollectableResponse swapCollectable(SwapCollectableRequest request) throws NullRequestParameterException {
        return null;
    }

    @Override
    public AddToMyMissionsResponse addToMyMissions(AddToMyMissionsRequest request) throws NullRequestParameterException {
        return null;
    }
}
