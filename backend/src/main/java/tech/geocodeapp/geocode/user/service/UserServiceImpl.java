package tech.geocodeapp.geocode.user.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tech.geocodeapp.geocode.collectable.model.*;
import tech.geocodeapp.geocode.collectable.repository.CollectableRepository;
import tech.geocodeapp.geocode.collectable.request.GetCollectableByIDRequest;
import tech.geocodeapp.geocode.collectable.request.GetCollectableTypeByIDRequest;
import tech.geocodeapp.geocode.collectable.response.GetCollectableByIDResponse;
import tech.geocodeapp.geocode.collectable.response.GetCollectableTypeByIDResponse;
import tech.geocodeapp.geocode.collectable.service.CollectableService;
import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.event.request.GetEventRequest;
import tech.geocodeapp.geocode.event.response.GetEventResponse;
import tech.geocodeapp.geocode.event.service.EventService;
import tech.geocodeapp.geocode.general.CheckNullRequestParameters;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.geocode.model.Difficulty;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.request.GetGeoCodeRequest;
import tech.geocodeapp.geocode.geocode.response.GetGeoCodeResponse;
import tech.geocodeapp.geocode.geocode.service.GeoCodeService;
import tech.geocodeapp.geocode.leaderboard.model.MyLeaderboardDetails;
import tech.geocodeapp.geocode.leaderboard.model.Point;
import tech.geocodeapp.geocode.leaderboard.repository.PointRepository;
import tech.geocodeapp.geocode.leaderboard.request.CreatePointRequest;
import tech.geocodeapp.geocode.leaderboard.request.GetPointForUserRequest;
import tech.geocodeapp.geocode.leaderboard.request.UpdatePointRequest;
import tech.geocodeapp.geocode.leaderboard.response.PointResponse;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;
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

    @NotNull(message = "Leaderboard Service Implementation may not be null.")
    private final LeaderboardService leaderboardService;

    private final String invalidUserIdMessage = "Invalid user id";
    private final UUID trackableUUID = UUID.fromString("0855b7da-bdad-44b7-9c22-18fe266ceaf3");

    @NotNull(message = "GeoCode Service Implementation may not be null.")
    private final GeoCodeService geoCodeService;

    @NotNull(message = "Event Service Implementation may not be null.")
    private final EventService eventService;

    public UserServiceImpl(UserRepository userRepo, CollectableRepository collectableRepo, PointRepository pointRepo, CollectableService collectableService, @Qualifier("LeaderboardService") LeaderboardService leaderboardService, @Qualifier("GeoCodeService") GeoCodeService geoCodeService, @Qualifier("EventService") EventService eventService) {
        this.userRepo = userRepo;
        this.collectableRepo = collectableRepo;
        this.pointRepo = pointRepo;
        this.collectableService = collectableService;
        this.leaderboardService = leaderboardService;
        this.geoCodeService = geoCodeService;
        this.eventService = eventService;
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
                () -> new GetUserByIdResponse(false, "The User was not found", null));
    }

    /**
     *  Gets the current User using the Keycloak details
     * @return The current User
     */
    @Transactional
    public User getCurrentUser(){
        String uuid = SecurityContextHolder.getContext().getAuthentication().getName();

        /* make request to get the current User*/
        GetUserByIdRequest request = new GetUserByIdRequest(UUID.fromString(uuid));

        try{
            return getUserById(request).getUser();
        }catch(NullRequestParameterException e){
            e.printStackTrace();
            return null;
        }
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
    @Transactional
    public SwapCollectableResponse swapCollectable( SwapCollectableRequest request ) throws NullRequestParameterException {
        if(request == null){
            return new SwapCollectableResponse(false, "The SwapCollectableRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        /* assign points to the User for finding the GeoCode */

        //get the GeoCode
        GetGeoCodeRequest getGeoCodeByIDRequest = new GetGeoCodeRequest(request.getGeoCodeID());
        GetGeoCodeResponse getGeoCodeByIDResponse;

        try {
            getGeoCodeByIDResponse = geoCodeService.getGeoCode(getGeoCodeByIDRequest);
        } catch (InvalidRequestException e) {
            e.printStackTrace();
            return new SwapCollectableResponse(false, e.getMessage(), null);
        }

        //check if the ID passed is valid
//        if(!getGeoCodeByIDResponse.success()){
//            return new SwapCollectableResponse(false, "Invalid ID given for the GeoCode", null);
//        }

        GeoCode geoCode = getGeoCodeByIDResponse.getFoundGeoCode();

        //check if the GeoCode is part of an Event
        UUID eventID = geoCode.getEventID();

        if(eventID != null){
            //get the difficulty of the GeoCode
            Difficulty geocodeDifficulty = geoCode.getDifficulty();

            //determine how many points the User should get
            int pointsAmount = 0;

            switch(geocodeDifficulty){
                case EASY:
                    pointsAmount = 1;
                    break;
                case MEDIUM:
                    pointsAmount = 5;
                    break;
                case HARD:
                    pointsAmount = 10;
                    break;
                case INSANE:
                    pointsAmount = 20;
                    break;
            }

            //get the LeaderboardID of the Leaderboard for the Event
            GetEventRequest getEventByIDRequest = new GetEventRequest( eventID);
            GetEventResponse getEventByIDResponse;

            try {
                getEventByIDResponse = eventService.getEvent(getEventByIDRequest);
            } catch (tech.geocodeapp.geocode.event.exceptions.InvalidRequestException e) {
                e.printStackTrace();
                return new SwapCollectableResponse(false, e.getMessage(), null);
            }

            Event event = getEventByIDResponse.getFoundEvent();
            UUID leaderboardID = event.getLeaderboards().get( 0 ).getId();

            //get the ID of the current User
            UUID userID = getCurrentUser().getId();

            //check if the User does not already have points for the Leaderboard
            GetPointForUserRequest getPointForUserRequest = new GetPointForUserRequest(userID, leaderboardID);
            PointResponse getPointForUserResponse = leaderboardService.getPointForUser(getPointForUserRequest);

            //add new Point entry if first time User is scoring on the Leaderboard
            if(!getPointForUserResponse.isSuccess()){
                CreatePointRequest createPointRequest = new CreatePointRequest(pointsAmount, userID, leaderboardID);
                PointResponse pointResponse = leaderboardService.createPoint(createPointRequest);

                //check if the Point was not created successfully
                if(!pointResponse.isSuccess()){
                    return new SwapCollectableResponse(false, "Point could not be created", null);
                }
            }else{
                //update Point entry if already has scored on the Leaderboard
                Point point = getPointForUserResponse.getPoint();

                UpdatePointRequest updatePointRequest = new UpdatePointRequest(point.getId(), point.getAmount()+pointsAmount);
                PointResponse updatePointResponse = leaderboardService.updatePoint(updatePointRequest);

                //check if the Point was not successfully updated
                if(!updatePointResponse.isSuccess()){
                    return new SwapCollectableResponse(false, "Point could not be updated", null);
                }
            }
        }

        /* only swap the Collectables if no errors have occurred before now */
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
