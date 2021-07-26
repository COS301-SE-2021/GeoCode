package tech.geocodeapp.geocode.user.service;

import java.util.*;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tech.geocodeapp.geocode.collectable.model.*;
import tech.geocodeapp.geocode.collectable.repository.CollectableRepository;
import tech.geocodeapp.geocode.collectable.request.GetCollectableByIDRequest;
import tech.geocodeapp.geocode.collectable.request.GetCollectableTypeByIDRequest;
import tech.geocodeapp.geocode.collectable.response.GetCollectableByIDResponse;
import tech.geocodeapp.geocode.collectable.response.GetCollectableTypeByIDResponse;
import tech.geocodeapp.geocode.collectable.service.CollectableService;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.leaderboard.exception.NullLeaderboardRequestParameterException;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.model.MyLeaderboardDetails;
import tech.geocodeapp.geocode.leaderboard.model.Point;
import tech.geocodeapp.geocode.leaderboard.request.GetMyRankRequest;
import tech.geocodeapp.geocode.leaderboard.response.GetMyRankResponse;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;
import tech.geocodeapp.geocode.user.exception.NullUserRequestParameterException;
import tech.geocodeapp.geocode.user.model.User;
import tech.geocodeapp.geocode.user.repository.UserRepository;
import tech.geocodeapp.geocode.user.request.*;
import tech.geocodeapp.geocode.user.response.*;

import javax.validation.constraints.NotNull;

/**
 * This class implements the UserService interface
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final CollectableRepository collectableRepo;

    @NotNull(message = "Collectable Service Implementation may not be null.")
    private final CollectableService collectableService;

    @NotNull(message = "Leaderboard Service Implementation may not be null.")
    private final LeaderboardService leaderboardService;

    private final String invalidUserIdMessage = "Invalid user id";
    private final UUID trackableUUID = UUID.fromString("0855b7da-bdad-44b7-9c22-18fe266ceaf3");

    public UserServiceImpl(UserRepository userRepo, CollectableRepository collectableRepo, CollectableService collectableService, LeaderboardService leaderboardService) {
        this.userRepo = userRepo;
        this.collectableRepo = collectableRepo;
        this.collectableService = collectableService;
        this.leaderboardService = leaderboardService;
    }

    /**
     * Gets the Collectable that the User is currently holding
     * @param request The GetCurrentCollectableRequest object
     * @return A GetCurrentCollectableResponse object: (success, message, object)
     */
    public GetCurrentCollectableResponse getCurrentCollectable(GetCurrentCollectableRequest request) throws NullUserRequestParameterException{
        if (request == null) {
            return new GetCurrentCollectableResponse(false, "The GetCurrentCollectableRequest object passed was NULL", null);
        }

        if(request.getUserID() == null){
            throw new NullUserRequestParameterException();
        }

        Optional<User> optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetCurrentCollectableResponse(false, invalidUserIdMessage, null);
        }

        Collectable currentUserCollectable = optionalUser.get().getCurrentCollectable();
        return new GetCurrentCollectableResponse(true, "The user's Collectable was successfully returned", currentUserCollectable);
    }

    /**
     * Gets the User's trackableObject
     * @param request The GetUserTrackableRequest object
     * @return A GetUserTrackableResponse object: (success, message, object)
     */
    public GetUserTrackableResponse getUserTrackable(GetUserTrackableRequest request) throws NullUserRequestParameterException{
        if (request == null) {
            return new GetUserTrackableResponse(false, "The GetUserTrackableRequest object passed was NULL", null);
        }

        if(request.getUserID() == null){
            throw new NullUserRequestParameterException();
        }

        Optional<User> optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetUserTrackableResponse(false, invalidUserIdMessage, null);
        }

        Collectable userTrackable = optionalUser.get().getTrackableObject();
        return new GetUserTrackableResponse(true, "The user's Trackable was successfully returned", userTrackable);
    }

    /**
     * Updates the location of the User's trackable
     * @param request The UpdateLocationRequest object
     * @return A UpdateLocationResponse object: (success, message, object)
     */
    public UpdateLocationResponse updateLocation(UpdateLocationRequest request) throws NullUserRequestParameterException{
        if (request == null) {
            return new UpdateLocationResponse(false, "The UpdateLocationRequest object passed was NULL", null);
        }

        if(request.getUserID() == null){
            throw new NullUserRequestParameterException();
        }

        Optional<User> optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new UpdateLocationResponse(false, invalidUserIdMessage, null);
        }

        User currentUser = optionalUser.get();
        Collectable trackableObject = currentUser.getTrackableObject();

        //update the trackable's location
        trackableObject.changeLocation(request.getLocation());
        collectableRepo.save(trackableObject);

        return new UpdateLocationResponse(true, "The trackable object's location was successfully updated", trackableObject);
    }

    /**
     * Gets the IDs of the CollectableTypes that the User has found so far
     * @param request The GetFoundCollectableTypesRequest object
     * @return A GetCollectableTypesResponse object: (success, message, object)
     * @throws NullUserRequestParameterException Exception for 1 or more NULL parameters when making a User request
     */
    public GetFoundCollectableTypesResponse getFoundCollectableTypes(GetFoundCollectableTypesRequest request) throws NullUserRequestParameterException{
        if (request == null) {
            return new GetFoundCollectableTypesResponse(false, "The GetFoundCollectableTypesRequest object passed was NULL", null);
        }

        if(request.getUserID() == null){
            throw new NullUserRequestParameterException();
        }

        Optional<User> optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetFoundCollectableTypesResponse(false, invalidUserIdMessage, null);
        }

        //get IDs for all of the found CollectableTypes for the current User
        User currentUser = optionalUser.get();
        Set<CollectableType> foundCollectableTypes = currentUser.getFoundCollectableTypes();

        List<UUID> foundCollectableTypeIDs = new ArrayList<>();
        foundCollectableTypes.forEach(collectableType -> foundCollectableTypeIDs.add(collectableType.getId()));

        return new GetFoundCollectableTypesResponse(true, "The IDs of the User's found CollectableTypes was successfully returned", foundCollectableTypeIDs);
    }

    /**
     * Gets the IDs of the GeoCodes that the User has found so far
     * @param request The GetFoundGeoCodesRequest object
     * @return A GetFoundGeoCodesResponse object: (success, message, object)
     * @throws NullUserRequestParameterException Exception for 1 or more NULL parameters when making a User request
     */
    public GetFoundGeoCodesResponse getFoundGeoCodes(GetFoundGeoCodesRequest request) throws NullUserRequestParameterException {
        if (request == null) {
            return new GetFoundGeoCodesResponse(false, "The GetFoundGeoCodesRequest object passed was NULL", null);
        }

        if(request.getUserID() == null){
            throw new NullUserRequestParameterException();
        }

        Optional<User> optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetFoundGeoCodesResponse(false, invalidUserIdMessage, null);
        }

        //get IDs for all of the found GeoCodes for the current User
        User currentUser = optionalUser.get();
        Set<GeoCode> foundGeoCodes = currentUser.getFoundGeocodes();

        List<UUID> foundGeoCodeIDs = new ArrayList<>();
        foundGeoCodes.forEach(foundGeoCode -> foundGeoCodeIDs.add(foundGeoCode.getId()));

        return new GetFoundGeoCodesResponse(true, "The IDs of the User's found GeoCodes was successfully returned", foundGeoCodeIDs);
    }

    /**
     * Gets the IDs of the GeoCodes that the User owns
     * @param request The GetOwnedGeoCodesRequest object
     * @return A GetOwnedGeoCodesResponse object: (success, message, object)
     * @throws NullUserRequestParameterException Exception for 1 or more NULL parameters when making a User request
     */
    public GetOwnedGeoCodesResponse getOwnedGeoCodes(GetOwnedGeoCodesRequest request) throws NullUserRequestParameterException {
        if (request == null) {
            return new GetOwnedGeoCodesResponse(false, "The GetOwnedGeoCodesRequest object passed was NULL", null);
        }

        if(request.getUserID() == null){
            throw new NullUserRequestParameterException();
        }

        Optional<User> optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetOwnedGeoCodesResponse(false, invalidUserIdMessage, null);
        }

        //get IDs for all of the GeoCodes owned by the current User
        User currentUser = optionalUser.get();
        Set<GeoCode> ownedGeocodes = currentUser.getOwnedGeocodes();

        List<UUID> ownedGeoCodeIDs = new ArrayList<>();
        ownedGeocodes.forEach(ownedGeocode -> ownedGeoCodeIDs.add(ownedGeocode.getId()));

        return new GetOwnedGeoCodesResponse(true, "The IDs of the User's owned GeoCodes was successfully returned", ownedGeoCodeIDs);
    }

    /**
     * Gets the Leaderboard details for all Leaderboards that a given User is on
     * @param request The GetMyLeaderboardsRequest object
     * @return A GetMyLeaderboardsResponse object: (success, message, object)
     * @throws NullUserRequestParameterException Exception for 1 or more NULL parameters when making a User request
     */
    public GetMyLeaderboardsResponse getMyLeaderboards(GetMyLeaderboardsRequest request) throws NullUserRequestParameterException{
        if (request == null) {
            return new GetMyLeaderboardsResponse(false, "The GetMyLeaderboardsRequest object passed was NULL", null);
        }

        if(request.getUserID() == null){
            throw new NullUserRequestParameterException();
        }

        Optional<User> optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetMyLeaderboardsResponse(false, invalidUserIdMessage, null);
        }

        User currentUser = optionalUser.get();

        List<MyLeaderboardDetails> leaderboardDetailsList = new ArrayList<>();

        for(Point point : currentUser.getPoints()){
            //get the Leaderboard that the Point is for
            Leaderboard leaderboard = point.getLeaderBoard();

            /* check if point has been assigned to a Leaderboard*/
            if(leaderboard != null){
                MyLeaderboardDetails leaderboardDetails = new MyLeaderboardDetails();
                leaderboardDetails.setName(leaderboard.getName());

                int pointAmount = point.getAmount();
                leaderboardDetails.setPoints(pointAmount);

                /*//get the rank
                GetPointsByLeaderboardRequest getPointsByLeaderboardRequest = new GetPointsByLeaderboardRequest(leaderboard);

                try{
                    GetPointsByLeaderboardResponse getPointsByLeaderboardResponse = leaderboardService.getPointsByLeaderboard(getPointsByLeaderboardRequest);

                    List<Point> leaderboardPoints = getPointsByLeaderboardResponse.getPoints();

                    //order by the point amount
                    leaderboardPoints.sort(Comparator.comparing(Point::getAmount));

                    int rank = leaderboardPoints.indexOf(point)+1;
                    leaderboardDetails.setRank(rank);
                }catch(NullLeaderboardRequestParameterException e){
                    return new GetMyLeaderboardsResponse(false, e.getMessage(), null);
                }*/

                //get the rank: using ranking function in SQL
                GetMyRankRequest getMyRankRequest = new GetMyRankRequest(leaderboard, pointAmount);

                try{
                    GetMyRankResponse getMyRankResponse = leaderboardService.getMyRank(getMyRankRequest);

                    int rank = getMyRankResponse.getRank();
                    leaderboardDetails.setRank(rank);
                }catch(NullLeaderboardRequestParameterException e){
                    return new GetMyLeaderboardsResponse(false, e.getMessage(), null);
                }

                leaderboardDetailsList.add(leaderboardDetails);
            }
        }

        return new GetMyLeaderboardsResponse(true, "The details for the User's Leaderboards were successfully returned", leaderboardDetailsList);
    }

    /**
     * Gets the User for the given id if they exist
     * @param request The GetUserByIdRequest object
     * @return The User if they exist, else NULL contained in a GetUserByIdResponse object
     */
    public GetUserByIdResponse getUserById(GetUserByIdRequest request) throws NullUserRequestParameterException {
        if(request == null){
            return new GetUserByIdResponse(false, "The GetUserByIdRequest object passed was NULL", null);
        }

        if(request.getUserID() == null){
            throw new NullUserRequestParameterException();
        }

        UUID id = request.getUserID();
        Optional<User> optionalUser = userRepo.findById(id);

        return optionalUser.map(user -> new GetUserByIdResponse(true, "The User was found", user)).orElseGet(
                () -> new GetUserByIdResponse(false, "The User was not found", null));
    }

    /**
     *  Gets the current User using the Keycloak details
     * @return The current User
     */
    public User getCurrentUser(){
        String uuid = SecurityContextHolder.getContext().getAuthentication().getName();

        /* make request to get the current User*/
        GetUserByIdRequest request = new GetUserByIdRequest(UUID.fromString(uuid));

        try{
            return getUserById(request).getUser();
        }catch(NullUserRequestParameterException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Registers a new user
     * @param request The id for the User
     */
    public RegisterNewUserResponse registerNewUser(RegisterNewUserRequest request) throws NullUserRequestParameterException{
        if(request == null){
            return new RegisterNewUserResponse(false, "The RegisterNewUserRequest object passed was NULL");
        }

        if(request.getUserID() == null || request.getUsername() == null){
            throw new NullUserRequestParameterException();
        }

        User newUser = new User();
        newUser.setId(request.getUserID());
        newUser.setUsername(request.getUsername());

        //get the CollectableType object for trackables
        GetCollectableTypeByIDRequest getCollectableTypeByIDRequest = new GetCollectableTypeByIDRequest(trackableUUID);
        GetCollectableTypeByIDResponse getCollectableTypeByIDResponse = collectableService.getCollectableTypeByID( getCollectableTypeByIDRequest );
        CollectableType optionalCollectableType = getCollectableTypeByIDResponse.getCollectableType();

        Collectable trackableObject = new Collectable( optionalCollectableType );
        newUser.setTrackableObject(trackableObject);
        newUser.setCurrentCollectable(trackableObject);
        userRepo.save(newUser);

        return new RegisterNewUserResponse(true, "New User registered");
    }

    //GeoCode helper functions

    /**
     * Swaps the currentCollectable of the current user with the given Collectable
     * @param request The UUID identifying the Collectable to swap with the currentCollectable
     * @return The original currentCollectable
     */
    public SwapCollectableResponse swapCollectable( SwapCollectableRequest request ) throws NullUserRequestParameterException {
        if(request == null){
            return new SwapCollectableResponse(false, "The SwapCollectableRequest object passed was NULL", null);
        }

        if(request.getCollectableID() == null){
            throw new NullUserRequestParameterException();
        }

        //currentCollectable to swap out
        User currentUser = getCurrentUser();
        Collectable oldCurrentCollectable = currentUser.getCurrentCollectable();

        //swap in newCurrentCollectable
        GetCollectableByIDRequest req = new GetCollectableByIDRequest( request.getCollectableID() );
        GetCollectableByIDResponse newCurrentCollectable = collectableService.getCollectableByID( req );
        currentUser.setCurrentCollectable( newCurrentCollectable.getCollectable() );
        userRepo.save(currentUser);

        return new SwapCollectableResponse(true, "The User's Collectable was swapped with the Collectable in the GeoCode", oldCurrentCollectable );
    }
}
