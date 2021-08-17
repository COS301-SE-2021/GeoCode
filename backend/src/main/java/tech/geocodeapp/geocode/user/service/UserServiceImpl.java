package tech.geocodeapp.geocode.user.service;

import java.util.*;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tech.geocodeapp.geocode.collectable.model.*;
import tech.geocodeapp.geocode.collectable.repository.CollectableRepository;
import tech.geocodeapp.geocode.collectable.request.CreateCollectableRequest;
import tech.geocodeapp.geocode.collectable.request.GetCollectableByIDRequest;
import tech.geocodeapp.geocode.collectable.response.CollectableResponse;
import tech.geocodeapp.geocode.collectable.response.CreateCollectableResponse;
import tech.geocodeapp.geocode.collectable.response.GetCollectableByIDResponse;
import tech.geocodeapp.geocode.collectable.service.CollectableService;
import tech.geocodeapp.geocode.general.CheckNullRequestParameters;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.service.GeoCodeService;
import tech.geocodeapp.geocode.leaderboard.model.MyLeaderboardDetails;
import tech.geocodeapp.geocode.leaderboard.repository.PointRepository;
import tech.geocodeapp.geocode.mission.model.Mission;
import tech.geocodeapp.geocode.mission.request.GetMissionByIdRequest;
import tech.geocodeapp.geocode.mission.request.UpdateCompletionRequest;
import tech.geocodeapp.geocode.mission.response.GetMissionByIdResponse;
import tech.geocodeapp.geocode.mission.service.MissionService;
import tech.geocodeapp.geocode.user.model.User;
import tech.geocodeapp.geocode.user.repository.UserRepository;
import tech.geocodeapp.geocode.user.request.*;
import tech.geocodeapp.geocode.user.response.*;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

/**
 * This class implements the UserService interface
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final CollectableRepository collectableRepo;
    private final PointRepository pointRepo;

    private final CheckNullRequestParameters checkNullRequestParameters = new CheckNullRequestParameters();

    @NotNull(message = "Collectable Service Implementation may not be null.")
    private final CollectableService collectableService;

    @NotNull(message = "Mission Service Implementation may not be null.")
    private final MissionService missionService;

    private final String invalidUserIdMessage = "Invalid User id";
    private final String invalidGeoCodeIdMessage = "Invalid GeoCode id";
    private final String invalidCollectableTypeIDMessage = "Invalid CollectableType ID";

    private final String existingUserIdMessage = "User ID already exists";

    private final UUID trackableUUID = new UUID(0, 0);

    @NotNull(message = "GeoCode Service Implementation may not be null.")
    private GeoCodeService geoCodeService;

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
    @Transactional
    public GetCurrentCollectableResponse getCurrentCollectable(GetCurrentCollectableRequest request) throws NullRequestParameterException{
        if (request == null) {
            return new GetCurrentCollectableResponse(false, "The GetCurrentCollectableRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

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
    @Transactional
    public GetUserTrackableResponse getUserTrackable(GetUserTrackableRequest request) throws NullRequestParameterException{
        if (request == null) {
            return new GetUserTrackableResponse(false, "The GetUserTrackableRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

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
    @Transactional
    public UpdateLocationResponse updateLocation(UpdateLocationRequest request) throws NullRequestParameterException {
        if (request == null) {
            return new UpdateLocationResponse(false, "The UpdateLocationRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

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
     * @throws NullRequestParameterException Exception for 1 or more NULL parameters when making a User request
     */
    @Transactional
    public GetFoundCollectableTypesResponse getFoundCollectableTypes(GetFoundCollectableTypesRequest request) throws NullRequestParameterException{
        if (request == null) {
            return new GetFoundCollectableTypesResponse(false, "The GetFoundCollectableTypesRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        Optional<User> optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetFoundCollectableTypesResponse(false, invalidUserIdMessage, null);
        }

        //get IDs for all the found CollectableTypes for the current User
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
     * @throws NullRequestParameterException Exception for 1 or more NULL parameters when making a User request
     */
    @Transactional
    public GetFoundGeoCodesResponse getFoundGeoCodes(GetFoundGeoCodesRequest request) throws NullRequestParameterException {
        if (request == null) {
            return new GetFoundGeoCodesResponse(false, "The GetFoundGeoCodesRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        Optional<User> optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetFoundGeoCodesResponse(false, invalidUserIdMessage, null);
        }

        //get IDs for all the found GeoCodes for the current User
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
     * @throws NullRequestParameterException Exception for 1 or more NULL parameters when making a User request
     */
    @Transactional
    public GetOwnedGeoCodesResponse getOwnedGeoCodes(GetOwnedGeoCodesRequest request) throws NullRequestParameterException {
        if (request == null) {
            return new GetOwnedGeoCodesResponse(false, "The GetOwnedGeoCodesRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        Optional<User> optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetOwnedGeoCodesResponse(false, invalidUserIdMessage, null);
        }

        //get IDs for all the GeoCodes owned by the current User
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
     * @throws NullRequestParameterException Exception for 1 or more NULL parameters when making a User request
     */
    @Transactional
    public GetMyLeaderboardsResponse getMyLeaderboards(GetMyLeaderboardsRequest request) throws NullRequestParameterException{
        if (request == null) {
            return new GetMyLeaderboardsResponse(false, "The GetMyLeaderboardsRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        /* check if user ID is invalid */
        Optional<User> optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetMyLeaderboardsResponse(false, invalidUserIdMessage, null);
        }

        User currentUser = optionalUser.get();

        List<MyLeaderboardDetails> leaderboardDetailsList = pointRepo.getMyLeaderboards(currentUser.getId());
        return new GetMyLeaderboardsResponse(true, "The details for the User's Leaderboards were successfully returned", leaderboardDetailsList);
    }

    /**
     * Gets the User's Missions
     * @param request GetMyMissionsRequest object
     * @return GetMyMissionsResponse object
     */
    @Transactional
    public GetMyMissionsResponse getMyMissions(GetMyMissionsRequest request) throws NullRequestParameterException {
        if(request == null){
            return new GetMyMissionsResponse(false, "The GetMyMissionsRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        //check if the UserID is invalid
        Optional<User> optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetMyMissionsResponse(false, invalidUserIdMessage, null);
        }

        User user = optionalUser.get();

        return new GetMyMissionsResponse(true, "User Missions returned", user.getMissions());
    }

    /**
     * Adds the given GeoCode to the User's list of Owned GeoCodes
     * @param request AddToOwnedGeoCodesRequest object
     * @return AddToOwnedGeoCodesResponse object
     */
    @Transactional
    public AddToOwnedGeoCodesResponse addToOwnedGeoCodes(AddToOwnedGeoCodesRequest request) throws NullRequestParameterException{
        if(request == null){
            return new AddToOwnedGeoCodesResponse(false, "The AddToOwnedGeoCodesRequest object passed was NULL");
        }

        checkNullRequestParameters.checkRequestParameters(request);

        //add the GeoCodeID to the User's list of owned GeoCodes
        User user = request.getUser();
        GeoCode geoCode = request.getGeocode();

        user.addOwnedGeocodesItem(geoCode);
        userRepo.save(user);

        return new AddToOwnedGeoCodesResponse(true, "GeoCode added to the owned GeoCodes");
    }

    /**
     * Adds the given GeoCode to the User's list of Found GeoCodes
     * @param request AddToFoundGeoCodesRequest object
     * @return AddToFoundGeoCodesResponse object
     */
    @Transactional
    public AddToFoundGeoCodesResponse addToFoundGeoCodes(AddToFoundGeoCodesRequest request) throws NullRequestParameterException{
        if(request == null){
            return new AddToFoundGeoCodesResponse(false, "The AddToFoundGeoCodesRequest object passed was NULL");
        }

        checkNullRequestParameters.checkRequestParameters(request);

        User user = request.getUser();
        GeoCode geoCode = request.getGeocode();

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
    @Transactional
    public AddToFoundCollectableTypesResponse addToFoundCollectableTypes(AddToFoundCollectableTypesRequest request) throws NullRequestParameterException{
        if(request == null){
            return new AddToFoundCollectableTypesResponse(false, "The AddToFoundCollectableTypesRequest object passed was NULL");
        }

        checkNullRequestParameters.checkRequestParameters(request);

        CollectableType collectableType = request.getCollectableType();
        User user = request.getUser();

        user.addFoundCollectableTypesItem(collectableType);
        userRepo.save(user);

        return new AddToFoundCollectableTypesResponse(true, "CollectableType added to the found CollectableTypes");
    }

    /**
     * Gets the User for the given id if they exist
     * @param request The GetUserByIdRequest object
     * @return The User if they exist, else NULL contained in a GetUserByIdResponse object
     */
    @Transactional
    public GetUserByIdResponse getUserById(GetUserByIdRequest request) throws NullRequestParameterException {
        if(request == null){
            return new GetUserByIdResponse(false, "The GetUserByIdRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        UUID id = request.getUserID();
        Optional<User> optionalUser = userRepo.findById(id);

        return optionalUser.map(user -> new GetUserByIdResponse(true, "The User was found", user)).orElseGet(
                () -> new GetUserByIdResponse(false, invalidUserIdMessage, null));
    }

    /**
     *  Gets the current User using the Keycloak details
     * @return The current User
     */
    @Transactional
    public User getCurrentUser(){
        /* make request to get the current User*/
        GetUserByIdRequest request = new GetUserByIdRequest(getCurrentUserID());

        try{
            return getUserById(request).getUser();
        }catch(NullRequestParameterException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  Gets the current user ID using the Keycloak details
     * @return The current user ID
     */
    public UUID getCurrentUserID(){
        String uuid = SecurityContextHolder.getContext().getAuthentication().getName();
        return UUID.fromString(uuid);
    }

    /**
     * Registers a new user
     * @param request The id for the User
     */
    @Transactional
    public RegisterNewUserResponse registerNewUser(RegisterNewUserRequest request) throws NullRequestParameterException{
        if(request == null){
            return new RegisterNewUserResponse(false, "The RegisterNewUserRequest object passed was NULL");
        }

        checkNullRequestParameters.checkRequestParameters(request);

        //check if the User already exists
        boolean exists = userRepo.existsById(request.getUserID());
        if(exists){
            return new RegisterNewUserResponse(false, existingUserIdMessage);
        }

        User newUser = new User(request.getUserID(), request.getUsername());

        //create the user's trackable object which will always have a Mission
        CreateCollectableRequest createCollectableRequest = new CreateCollectableRequest(trackableUUID, true);
        CreateCollectableResponse createCollectableResponse = collectableService.createCollectable(createCollectableRequest);

        if(!createCollectableResponse.isSuccess()){
            return new RegisterNewUserResponse(false, createCollectableResponse.getMessage());
        }

        CollectableResponse collectableResponse = createCollectableResponse.getCollectable();

        GetCollectableByIDRequest getCollectableIdRequest = new GetCollectableByIDRequest(collectableResponse.getId());
        GetCollectableByIDResponse getCollectableByIDResponse = collectableService.getCollectableByID(getCollectableIdRequest);

        Collectable trackableObject = getCollectableByIDResponse.getCollectable();

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
    @Transactional
    public SwapCollectableResponse swapCollectable( SwapCollectableRequest request ) throws NullRequestParameterException {
        if(request == null){
            return new SwapCollectableResponse(false, "The SwapCollectableRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        User currentUser = request.getUser();
        GeoCode geoCode = request.getGeoCode();

        //swap out the currentCollectable
        Collectable oldCurrentCollectable = currentUser.getCurrentCollectable();
        Collectable newCurrentCollectable = request.getCollectable();
        currentUser.setCurrentCollectable(newCurrentCollectable);

        //add the GeoCode to the User's found GeoCodes
        AddToFoundGeoCodesRequest addToFoundGeoCodesRequest = new AddToFoundGeoCodesRequest(currentUser, geoCode);
        this.addToFoundGeoCodes(addToFoundGeoCodesRequest);

        //add the CollectableType to the User's found CollectableTypes
        AddToFoundCollectableTypesRequest addToFoundCollectableTypesRequest = new AddToFoundCollectableTypesRequest(currentUser, newCurrentCollectable.getType());
        this.addToFoundCollectableTypes(addToFoundCollectableTypesRequest);
        
        //add the Collectable's Mission to the User's Missions
        UUID missionID = newCurrentCollectable.getMissionID();
        
        if(missionID != null){
            GetMissionByIdRequest getMissionByIdRequest = new GetMissionByIdRequest(missionID);
            GetMissionByIdResponse getMissionByIdResponse = missionService.getMissionById(getMissionByIdRequest);

            Mission mission = getMissionByIdResponse.getMission();
            currentUser.addMissionsItem(mission);

            //update the completion for the Collectable's Mission
            UpdateCompletionRequest updateCompletionRequest = new UpdateCompletionRequest(mission, geoCode.getLocation());
            UpdateCompletionResponse updateCompletionResponse = missionService.updateCompletion(updateCompletionRequest);
        }

        userRepo.save(currentUser);

        return new SwapCollectableResponse(true, "The User's Collectable was swapped with the Collectable in the GeoCode", oldCurrentCollectable );
    }

    /**
     * Post construct the GeoCode service, this avoids a circular dependency
     *
     * @param geoCodeService the service to be set
     */
    public void setGeoCodeService( GeoCodeService geoCodeService ){
        this.geoCodeService = geoCodeService;
    }
}