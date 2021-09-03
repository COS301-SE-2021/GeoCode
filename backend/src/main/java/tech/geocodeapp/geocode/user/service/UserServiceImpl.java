package tech.geocodeapp.geocode.user.service;

import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.collectable.repository.CollectableRepository;
import tech.geocodeapp.geocode.collectable.request.CreateCollectableRequest;
import tech.geocodeapp.geocode.collectable.request.GetCollectableByIDRequest;
import tech.geocodeapp.geocode.collectable.service.CollectableService;
import tech.geocodeapp.geocode.general.CheckNullRequestParameters;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.general.security.CurrentUserDetails;
import tech.geocodeapp.geocode.leaderboard.repository.PointRepository;
import tech.geocodeapp.geocode.mission.request.GetMissionByIdRequest;
import tech.geocodeapp.geocode.mission.request.UpdateCompletionRequest;
import tech.geocodeapp.geocode.mission.service.MissionService;
import tech.geocodeapp.geocode.user.model.User;
import tech.geocodeapp.geocode.user.repository.UserRepository;
import tech.geocodeapp.geocode.user.request.*;
import tech.geocodeapp.geocode.user.response.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This class implements the UserService interface
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final CollectableRepository collectableRepo;
    private final PointRepository pointRepo;

    @NotNull(message = "Collectable Service Implementation may not be null.")
    private final CollectableService collectableService;

    @NotNull(message = "Mission Service Implementation may not be null.")
    private final MissionService missionService;

    private final String invalidUserIdMessage = "Invalid User id";
    private final UUID trackableTypeUUID = new UUID(0, 0);
    private final CheckNullRequestParameters checkNullRequestParameters = new CheckNullRequestParameters();

    public UserServiceImpl(UserRepository userRepo, CollectableRepository collectableRepo, PointRepository pointRepo, CollectableService collectableService, MissionService missionService) {
        this.userRepo = userRepo;
        this.collectableRepo = collectableRepo;
        this.pointRepo = pointRepo;
        this.collectableService = collectableService;
        this.missionService = missionService;
    }

    /**
     * Gets the Collectable that the User is currently holding
     * @param request The GetCurrentCollectableRequest object
     * @return A GetCurrentCollectableResponse object: (success, message, object)
     */
    public GetCurrentCollectableResponse getCurrentCollectable(GetCurrentCollectableRequest request) throws NullRequestParameterException{
        if (request == null) {
            return new GetCurrentCollectableResponse(false, "The GetCurrentCollectableRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        var optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetCurrentCollectableResponse(false, invalidUserIdMessage, null);
        }

        var currentUserCollectable = optionalUser.get().getCurrentCollectable();
        return new GetCurrentCollectableResponse(true, "The user's Collectable was successfully returned", currentUserCollectable);
    }

    /**
     * Gets the User's trackableObject
     * @param request The GetUserTrackableRequest object
     * @return A GetUserTrackableResponse object: (success, message, object)
     */
    public GetUserTrackableResponse getUserTrackable(GetUserTrackableRequest request) throws NullRequestParameterException{
        if (request == null) {
            return new GetUserTrackableResponse(false, "The GetUserTrackableRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        var optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetUserTrackableResponse(false, invalidUserIdMessage, null);
        }

        var userTrackable = optionalUser.get().getTrackableObject();
        return new GetUserTrackableResponse(true, "The user's Trackable was successfully returned", userTrackable);
    }

    /**
     * Updates the location of the User's trackable
     * @param request The UpdateLocationRequest object
     * @return A UpdateLocationResponse object: (success, message, object)
     */
    public UpdateLocationResponse updateLocation(UpdateLocationRequest request) throws NullRequestParameterException {
        if (request == null) {
            return new UpdateLocationResponse(false, "The UpdateLocationRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        var optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new UpdateLocationResponse(false, invalidUserIdMessage, null);
        }

        var currentUser = optionalUser.get();
        var trackableObject = currentUser.getTrackableObject();

        //update the trackable's location
        trackableObject.changeLocation(request.getLocation());
        collectableRepo.save(trackableObject);

        return new UpdateLocationResponse(true, "The trackable object's location was successfully updated", trackableObject);
    }

    /**
     * Gets the IDs of the CollectableTypes that the User has found so far
     * @param request The GetFoundCollectableTypesRequest object
     * @return A GetCollectableTypesResponse object: (success, message, object)
     */
    public GetFoundCollectableTypesResponse getFoundCollectableTypes(GetFoundCollectableTypesRequest request) throws NullRequestParameterException{
        if (request == null) {
            return new GetFoundCollectableTypesResponse(false, "The GetFoundCollectableTypesRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        var optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetFoundCollectableTypesResponse(false, invalidUserIdMessage, null);
        }

        //get IDs for all the found CollectableTypes for the current User
        var currentUser = optionalUser.get();
        var foundCollectableTypes = currentUser.getFoundCollectableTypes();

        List<UUID> foundCollectableTypeIDs = new ArrayList<>();
        foundCollectableTypes.forEach(collectableType -> foundCollectableTypeIDs.add(collectableType.getId()));

        return new GetFoundCollectableTypesResponse(true, "The IDs of the User's found CollectableTypes was successfully returned", foundCollectableTypeIDs);
    }

    /**
     * Gets the IDs of the GeoCodes that the User has found so far
     * @param request The GetFoundGeoCodesRequest object
     * @return A GetFoundGeoCodesResponse object: (success, message, object)
     */
    public GetFoundGeoCodesResponse getFoundGeoCodes(GetFoundGeoCodesRequest request) throws NullRequestParameterException {
        if (request == null) {
            return new GetFoundGeoCodesResponse(false, "The GetFoundGeoCodesRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        var optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetFoundGeoCodesResponse(false, invalidUserIdMessage, null);
        }

        //get IDs for all the found GeoCodes for the current User
        var currentUser = optionalUser.get();
        var foundGeoCodes = currentUser.getFoundGeocodes();

        List<UUID> foundGeoCodeIDs = new ArrayList<>();
        foundGeoCodes.forEach(foundGeoCode -> foundGeoCodeIDs.add(foundGeoCode.getId()));

        return new GetFoundGeoCodesResponse(true, "The IDs of the User's found GeoCodes was successfully returned", foundGeoCodeIDs);
    }

    /**
     * Gets the IDs of the GeoCodes that the User owns
     * @param request The GetOwnedGeoCodesRequest object
     * @return A GetOwnedGeoCodesResponse object: (success, message, object)
     * @throws NullRequestParameterException Exception for 1 or more NULL parameters when making a User request
     */
    public GetOwnedGeoCodesResponse getOwnedGeoCodes(GetOwnedGeoCodesRequest request) throws NullRequestParameterException {
        if (request == null) {
            return new GetOwnedGeoCodesResponse(false, "The GetOwnedGeoCodesRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        var optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetOwnedGeoCodesResponse(false, invalidUserIdMessage, null);
        }

        //get IDs for all the GeoCodes owned by the current User
        var currentUser = optionalUser.get();
        var ownedGeocodes = currentUser.getOwnedGeocodes();

        List<UUID> ownedGeoCodeIDs = new ArrayList<>();
        ownedGeocodes.forEach(ownedGeocode -> ownedGeoCodeIDs.add(ownedGeocode.getId()));

        return new GetOwnedGeoCodesResponse(true, "The IDs of the User's owned GeoCodes was successfully returned", ownedGeoCodeIDs);
    }

    /**
     * Gets the Leaderboard details for all Leaderboards that a given User is on
     * @param request The GetMyLeaderboardsRequest object
     * @return A GetMyLeaderboardsResponse object: (success, message, object)
     * @throws NullRequestParameterException Exception for 1 or more NULL parameters when making a User request
     */
    public GetMyLeaderboardsResponse getMyLeaderboards(GetMyLeaderboardsRequest request) throws NullRequestParameterException{
        if (request == null) {
            return new GetMyLeaderboardsResponse(false, "The GetMyLeaderboardsRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        /* check if user ID is invalid */
        var optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetMyLeaderboardsResponse(false, invalidUserIdMessage, null);
        }

        var currentUser = optionalUser.get();
        var leaderboardDetailsList = pointRepo.getMyLeaderboards(currentUser.getId());

        return new GetMyLeaderboardsResponse(true, "The details for the User's Leaderboards were successfully returned", leaderboardDetailsList);
    }

    /**
     * Gets the User's Missions
     * @param request GetMyMissionsRequest object
     * @return GetMyMissionsResponse object
     */
    public GetMyMissionsResponse getMyMissions(GetMyMissionsRequest request) throws NullRequestParameterException {
        if(request == null){
            return new GetMyMissionsResponse(false, "The GetMyMissionsRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        //check if the UserID is invalid
        var optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetMyMissionsResponse(false, invalidUserIdMessage, null);
        }

        var user = optionalUser.get();

        return new GetMyMissionsResponse(true, "User Missions returned", user.getMissions());
    }

    /**
     * Adds the given GeoCode to the User's list of Owned GeoCodes
     * @param request AddToOwnedGeoCodesRequest object
     * @return AddToOwnedGeoCodesResponse object
     */
    public AddToOwnedGeoCodesResponse addToOwnedGeoCodes(AddToOwnedGeoCodesRequest request) throws NullRequestParameterException{
        if(request == null){
            return new AddToOwnedGeoCodesResponse(false, "The AddToOwnedGeoCodesRequest object passed was NULL");
        }

        checkNullRequestParameters.checkRequestParameters(request);

        //add the GeoCodeID to the User's list of owned GeoCodes
        var user = request.getUser();
        var geoCode = request.getGeocode();

        user.addOwnedGeocodesItem(geoCode);
        userRepo.save(user);

        return new AddToOwnedGeoCodesResponse(true, "GeoCode added to the owned GeoCodes");
    }

    /**
     * Adds the given GeoCode to the User's list of Found GeoCodes
     * @param request AddToFoundGeoCodesRequest object
     * @return AddToFoundGeoCodesResponse object
     */
    public AddToFoundGeoCodesResponse addToFoundGeoCodes(AddToFoundGeoCodesRequest request) throws NullRequestParameterException{
        if(request == null){
            return new AddToFoundGeoCodesResponse(false, "The AddToFoundGeoCodesRequest object passed was NULL");
        }

        checkNullRequestParameters.checkRequestParameters(request);

        var user = request.getUser();
        var geoCode = request.getGeocode();

        //add the GeoCodeID to the User's list of owned GeoCodes
        user.addFoundGeocodesItem(geoCode);
        userRepo.save(user);

        return new AddToFoundGeoCodesResponse(true, "GeoCode added to the found GeoCodes");
    }

    /**
     * Adds the CollectableType to the User's found CollectableTypes list
     * @param request AddToFoundCollectableTypesRequest object
     * @return AddToFoundCollectableTypesResponse object
     */
    public AddToFoundCollectableTypesResponse addToFoundCollectableTypes(AddToFoundCollectableTypesRequest request) throws NullRequestParameterException{
        if(request == null){
            return new AddToFoundCollectableTypesResponse(false, "The AddToFoundCollectableTypesRequest object passed was NULL");
        }

        checkNullRequestParameters.checkRequestParameters(request);

        var collectableType = request.getCollectableType();
        var user = request.getUser();

        user.addFoundCollectableTypesItem(collectableType);
        userRepo.save(user);

        return new AddToFoundCollectableTypesResponse(true, "CollectableType added to the found CollectableTypes");
    }

    /**
     * Gets the User for the given id if they exist
     * @param request The GetUserByIdRequest object
     * @return The User if they exist, else NULL contained in a GetUserByIdResponse object
     */
    public GetUserByIdResponse getUserById(GetUserByIdRequest request) throws NullRequestParameterException {
        if(request == null){
            return new GetUserByIdResponse(false, "The GetUserByIdRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        var id = request.getUserID();
        var optionalUser = userRepo.findById(id);

        return optionalUser.map(user -> new GetUserByIdResponse(true, "The User was found", user)).orElseGet(
                () -> new GetUserByIdResponse(false, invalidUserIdMessage, null));
    }

    /**
     *  Gets the current User using the Keycloak details
     * @return The current User
     */
    public User getCurrentUser(){
        /* make request to get the current User*/
        var request = new GetUserByIdRequest(CurrentUserDetails.getID());

        try{
            return getUserById(request).getUser();
        }catch(NullRequestParameterException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @deprecated use CurrentUserDetails.getID()
     *  Gets the current user ID using the Keycloak details
     * @return The current user ID
     */
    public UUID getCurrentUserID(){
        return CurrentUserDetails.getID();
    }

    /**
     * Registers a new user
     * @param request The id for the User
     */
    public Response handleLogin(HandleLoginRequest request) throws NullRequestParameterException{
        if(request == null){
            return new Response(false, "The HandleLoginRequest object passed was NULL");
        }

        checkNullRequestParameters.checkRequestParameters(request);

        //check if the User already exists
        System.out.println("current user id: "+CurrentUserDetails.getID());
        boolean exists = userRepo.existsById(CurrentUserDetails.getID());

        if(exists){
            return new Response(true, "User ID already exists");
        }

        //the User is a new User
        var newUser = new User(CurrentUserDetails.getID(), CurrentUserDetails.getUsername());

        //create the user's trackable object which will always have a Mission
        var createCollectableRequest = new CreateCollectableRequest(trackableTypeUUID, request.getLocation());
        var createCollectableResponse = collectableService.createCollectable(createCollectableRequest);

        if(!createCollectableResponse.isSuccess()){
            return new Response(false, createCollectableResponse.getMessage());
        }

        var collectableResponse = createCollectableResponse.getCollectable();

        //get the trackable object
        var getCollectableIdRequest = new GetCollectableByIDRequest(collectableResponse.getId());
        var getCollectableByIDResponse = collectableService.getCollectableByID(getCollectableIdRequest);

        var trackableObject = getCollectableByIDResponse.getCollectable();

        newUser.setTrackableObject(trackableObject);
        newUser.setCurrentCollectable(trackableObject);

        //add trackable object's Mission to the User's Missions
        var getMissionByIDRequest = new GetMissionByIdRequest(trackableObject.getMissionID());
        var mission = missionService.getMissionById(getMissionByIDRequest).getMission();

        this.addToMyMissions(new AddToMyMissionsRequest(newUser, mission));

        /* check that the new User was successfully saved */
        var check = userRepo.save(newUser);

        if(!newUser.equals(check)){
            return new Response(false, "New User registration failed");
        }

        System.out.println("new user is registered");
        return new Response(true, "New User registered xxxxxx");
    }

    //GeoCode helper functions

    /**
     * Swaps the currentCollectable of the current user with the given Collectable
     * @param request The UUID identifying the Collectable to swap with the currentCollectable
     * @return The original currentCollectable
     */
    public SwapCollectableResponse swapCollectable( SwapCollectableRequest request ) throws NullRequestParameterException {
        if(request == null){
            return new SwapCollectableResponse(false, "The SwapCollectableRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        var user = request.getUser();
        var geoCode = request.getGeoCode();

        //swap out the currentCollectable
        var oldCurrentCollectable = user.getCurrentCollectable();
        var newCurrentCollectable = request.getCollectable();
        user.setCurrentCollectable(newCurrentCollectable);

        //add the GeoCode to the User's found GeoCodes
        this.addToFoundGeoCodes(new AddToFoundGeoCodesRequest(user, geoCode));

        //add the CollectableType to the User's found CollectableTypes
        this.addToFoundCollectableTypes(new AddToFoundCollectableTypesRequest(user, newCurrentCollectable.getType()));
        
        //add the Collectable's Mission to the User's Missions
        var newCurrentCollectableMissionID = newCurrentCollectable.getMissionID();

        if(newCurrentCollectableMissionID != null){
            var newCurrentCollectableMission  = missionService.getMissionById(new GetMissionByIdRequest(newCurrentCollectableMissionID)).getMission();

            this.addToMyMissions(new AddToMyMissionsRequest(user, newCurrentCollectableMission));
        }else{
            //save() called in addToMyMissions
            userRepo.save(user);
        }

        var oldCurrentCollectableMissionID = oldCurrentCollectable.getMissionID();

        if(oldCurrentCollectableMissionID != null){
            var oldCurrentCollectableMission = missionService.getMissionById(new GetMissionByIdRequest(oldCurrentCollectableMissionID)).getMission();

            //update the completion of the Mission
            var updateCompletionRequest = new UpdateCompletionRequest(oldCurrentCollectableMission, geoCode.getLocation());
            missionService.updateCompletion(updateCompletionRequest);
        }

        return new SwapCollectableResponse(true, "The User's Collectable was swapped with the Collectable in the GeoCode", oldCurrentCollectable );
    }

    /**
     * Add the given Mission to the User's list of missions
     * @param request AddToMyMissionsRequest object
     * @return AddToMyMissionsResponse object
     */
    public AddToMyMissionsResponse addToMyMissions(AddToMyMissionsRequest request) throws NullRequestParameterException {
        if(request == null){
            return new AddToMyMissionsResponse(false, "The AddToMyMissionsRequest object passed was NULL");
        }

        checkNullRequestParameters.checkRequestParameters(request);

        var user = request.getUser();
        var mission = request.getMission();

        user.addMissionsItem(mission);
        userRepo.save(user);

        return new AddToMyMissionsResponse(true, "Mission added to the User's Missions");
    }
}