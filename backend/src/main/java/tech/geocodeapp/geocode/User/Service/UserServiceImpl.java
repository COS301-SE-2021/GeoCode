package tech.geocodeapp.geocode.User.Service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tech.geocodeapp.geocode.Collectable.Model.Collectable;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableTypeRepository;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableRepository;
import tech.geocodeapp.geocode.User.Exception.NullUserRequestParameterException;
import tech.geocodeapp.geocode.User.Model.User;
import tech.geocodeapp.geocode.User.Repository.UserRepository;
import tech.geocodeapp.geocode.User.Request.*;
import tech.geocodeapp.geocode.User.Response.*;

/**
 * This class implements the UserService interface
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final CollectableRepository collectableRepo;
    private final CollectableTypeRepository collectableTypeRepo;

    private final String invalidUserIdMessage = "Invalid user id";

    public UserServiceImpl(UserRepository userRepo, CollectableRepository collectableRepo, CollectableTypeRepository collectableTypeRepo) {
        this.userRepo = userRepo;
        this.collectableRepo = collectableRepo;
        this.collectableTypeRepo = collectableTypeRepo;
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
    public GetUserTrackableResponse getUserTrackable(GetUserTrackableRequest request) throws NullUserRequestParameterException{
        if (request == null) {
            return new GetUserTrackableResponse(false, "The GetUserTrackableRequest object passed was NULL", null);
        }

        if(request.getUserID() == null){
            throw new NullUserRequestParameterException();
        }

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
    public UpdateLocationResponse updateLocation(UpdateLocationRequest request) throws NullUserRequestParameterException{
        if (request == null) {
            return new UpdateLocationResponse(false, "The UpdateLocationRequest object passed was NULL", null);
        }

        if(request.getUserID() == null){
            throw new NullUserRequestParameterException();
        }

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
     * @throws NullUserRequestParameterException Exception for 1 or more NULL parameters when making a User request
     */
    public GetFoundCollectableTypesResponse getFoundCollectableTypes(GetFoundCollectableTypesRequest request) throws NullUserRequestParameterException{
        if (request == null) {
            return new GetFoundCollectableTypesResponse(false, "The GetFoundCollectableTypesResponseRequest object passed was NULL", null);
        }

        if(request.getUserID() == null){
            throw new NullUserRequestParameterException();
        }

        var optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetFoundCollectableTypesResponse(false, invalidUserIdMessage, null);
        }

        //get IDs for all of the found CollectableTypes for the current User
        var currentUser = optionalUser.get();
        var foundCollectableTypes = currentUser.getFoundCollectableTypes();

        var foundCollectableTypeIDs = new ArrayList<UUID>();
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
            return new GetFoundGeoCodesResponse(false, "The GetFoundGeoCodesResponseRequest object passed was NULL", null);
        }

        if(request.getUserID() == null){
            throw new NullUserRequestParameterException();
        }

        var optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetFoundGeoCodesResponse(false, invalidUserIdMessage, null);
        }

        //get IDs for all of the found GeoCodes for the current User
        var currentUser = optionalUser.get();
        var foundGeoCodes = currentUser.getFoundGeocodes();

        var foundGeoCodeIDs = new ArrayList<UUID>();
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
            return new GetOwnedGeoCodesResponse(false, "The GetOwnedGeoCodesResponseRequest object passed was NULL", null);
        }

        if(request.getUserID() == null){
            throw new NullUserRequestParameterException();
        }

        var optionalUser = userRepo.findById(request.getUserID());

        if(optionalUser.isEmpty()){
            return new GetOwnedGeoCodesResponse(false, invalidUserIdMessage, null);
        }

        //get IDs for all of the GeoCodes owned by the current User
        var currentUser = optionalUser.get();
        var ownedGeocodes = currentUser.getOwnedGeocodes();

        var ownedGeoCodeIDs = new ArrayList<UUID>();
        ownedGeocodes.forEach(ownedGeocode -> ownedGeoCodeIDs.add(ownedGeocode.getId()));

        return new GetOwnedGeoCodesResponse(true, "The IDs of the User's owned GeoCodes was successfully returned", ownedGeoCodeIDs);
    }

    /**
     * Gets the User for the given id if they exist
     * @param id The id for the User
     * @return The User if they exist, else NULL
     */
    public User getUserById(UUID id){
        var optionalUser = userRepo.findById(id);
        return optionalUser.orElse(null);
    }

    /**
     *  Gets the current User using the Keycloak details
     * @return The current User
     */
    public User getCurrentUser(){
        var uuid = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserById(UUID.fromString(uuid));
    }

    /**
     * Registers a new user
     * @param id The id for the User
     */
    public void registerNewUser(UUID id, String username){
        var newUser = new User();
        newUser.setId(id);
        newUser.setUsername(username);

        //get the CollectableType object for trackables
        var optionalCollectableType = collectableTypeRepo.findById(UUID.fromString("0855b7da-bdad-44b7-9c22-18fe266ceaf3"));
        var trackableCollectableType = optionalCollectableType.get();

        var trackableObject = new Collectable(trackableCollectableType);
        newUser.setTrackableObject(trackableObject);
        newUser.setCurrentCollectable(trackableObject);
        userRepo.save(newUser);
    }

    //GeoCode helper functions

    /**
     * Swaps the currentCollectable of the current user with the given Collectable
     * @param newCurrentCollectable The given Collectable to swap in
     * @return The original currentCollectable
     */
    public Collectable swapCollectable(Collectable newCurrentCollectable){
        //currentCollectable to swap out
        var currentUser = getCurrentUser();
        var oldCurrentCollectable = currentUser.getCurrentCollectable();

        //swap in newCurrentCollectable
        currentUser.setCurrentCollectable(newCurrentCollectable);
        userRepo.save(currentUser);

        return oldCurrentCollectable;
    }
}
