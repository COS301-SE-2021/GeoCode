package tech.geocodeapp.geocode.User.Service;

import tech.geocodeapp.geocode.Collectable.Model.Collectable;
import tech.geocodeapp.geocode.Collectable.Model.CollectableType;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableTypeRepository;
import tech.geocodeapp.geocode.GeoCode.Model.GeoCode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableRepository;
import tech.geocodeapp.geocode.GeoCode.Repository.GeoCodeRepository;
import tech.geocodeapp.geocode.User.Exception.NullUserRequestParameterException;
import tech.geocodeapp.geocode.User.Model.User;
import tech.geocodeapp.geocode.User.Repository.UserRepository;
import tech.geocodeapp.geocode.User.Request.GetCurrentCollectableRequest;
import tech.geocodeapp.geocode.User.Request.GetUserTrackableRequest;
import tech.geocodeapp.geocode.User.Request.SwapCollectableRequest;
import tech.geocodeapp.geocode.User.Request.UpdateLocationRequest;
import tech.geocodeapp.geocode.User.Response.GetCurrentCollectableResponse;
import tech.geocodeapp.geocode.User.Response.GetUserTrackableResponse;
import tech.geocodeapp.geocode.User.Response.SwapCollectableResponse;
import tech.geocodeapp.geocode.User.Response.UpdateLocationResponse;

import java.util.Optional;
import java.util.UUID;

/**
 * This class implements the UserService interface
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final CollectableRepository collectableRepo;
    private final CollectableTypeRepository collectableTypeRepo;
    private final GeoCodeRepository geocodeRepo;

    private final String invalidUserIdMessage = "Invalid user id";

    public UserServiceImpl(UserRepository userRepo, CollectableRepository collectableRepo, CollectableTypeRepository collectableTypeRepo, GeoCodeRepository geocodeRepo) {
        this.userRepo = userRepo;
        this.collectableRepo = collectableRepo;
        this.collectableTypeRepo = collectableTypeRepo;
        this.geocodeRepo = geocodeRepo;
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
     * Gets the User for the given id if they exist
     * @param id The id for the User
     * @return The User if they exist, else NULL
     */
    public User getUserById(UUID id){
        Optional<User> optionalUser = userRepo.findById(id);
        return optionalUser.orElse(null);
    }

    /**
     *  Gets the current User using the Keycloak details
     * @return The current User
     */
    public User getCurrentUser(){
        String uuid = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserById(UUID.fromString(uuid));
    }

    /**
     * Registers a new user
     * @param id The id for the User
     */
    public void registerNewUser(UUID id, String username){
        User newUser = new User();
        newUser.setId(id);
        newUser.setUsername(username);

        //get the CollectableType object for trackables
        Optional<CollectableType> optionalCollectableType = collectableTypeRepo.findById(UUID.fromString("0855b7da-bdad-44b7-9c22-18fe266ceaf3"));
        CollectableType trackableCollectableType = optionalCollectableType.get();

        Collectable trackableObject = new Collectable(trackableCollectableType);
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
        User currentUser = getCurrentUser();
        Collectable oldCurrentCollectable = currentUser.getCurrentCollectable();

        //swap in newCurrentCollectable
        currentUser.setCurrentCollectable(newCurrentCollectable);
        userRepo.save(currentUser);

        return oldCurrentCollectable;
    }
}
