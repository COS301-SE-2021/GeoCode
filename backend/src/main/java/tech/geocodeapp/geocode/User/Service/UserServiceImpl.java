package tech.geocodeapp.geocode.User.Service;

import io.swagger.model.Collectable;
import io.swagger.model.GeoCode;
import org.springframework.stereotype.Service;


import tech.geocodeapp.geocode.Collectable.Repository.CollectableRepository;
import tech.geocodeapp.geocode.GeoCode.Repository.GeoCodeRepository;
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
//import tech.geocodeapp.geocode.User.Service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This class implements the UserService interface
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final CollectableRepository collectableRepo;
    private final GeoCodeRepository geocodeRepo;

    public UserServiceImpl(UserRepository userRepo, CollectableRepository collectableRepo, GeoCodeRepository geocodeRepo) {
        this.userRepo = userRepo;
        this.collectableRepo = collectableRepo;
        this.geocodeRepo = geocodeRepo;
    }

    @Transactional
    public GetCurrentCollectableResponse getCurrentCollectable(GetCurrentCollectableRequest request){
        if (request == null) {
            return new GetCurrentCollectableResponse(false, "The GetCurrentCollectableRequest object passed was NULL", null);
        }

        Optional<User> optionalUser = userRepo.findById(request.getUserID());

        if(!optionalUser.isPresent()){
            return new GetCurrentCollectableResponse(false, "Invalid user id", null);
        }

        Collectable currentUserCollectable = optionalUser.get().getCurrentCollectable();
        return new GetCurrentCollectableResponse(true, "The user's Collectable was successfully returned", currentUserCollectable);
    }

    @Transactional
    public GetUserTrackableResponse getUserTrackable(GetUserTrackableRequest request){
        if (request == null) {
            return new GetUserTrackableResponse(false, "The GetUserTrackableRequest object passed was NULL", null);
        }

        Optional<User> optionalUser = userRepo.findById(request.getUserID());

        if(!optionalUser.isPresent()){
            return new GetUserTrackableResponse(false, "Invalid user id", null);
        }

        Collectable userTrackable = optionalUser.get().getTrackableObject();
        return new GetUserTrackableResponse(true, "The user's Trackable was successfully returned", userTrackable);
    }

    @Transactional
    public SwapCollectableResponse swapCollectable(SwapCollectableRequest request){
        if (request == null) {
            return new SwapCollectableResponse(false, "The SwapCollectableRequest object passed was NULL", null);
        }

        Optional<User> optionalUser = userRepo.findById(request.getUserID());

        if(!optionalUser.isPresent()){
            return new SwapCollectableResponse(false, "Invalid user id", null);
        }

        Optional<GeoCode> optionalGeoCode = geocodeRepo.findById(request.getTargetGeoCodeID());

        if(!optionalGeoCode.isPresent()){
            return new SwapCollectableResponse(false, "Invalid GeoCode id", null);
        }

        //check if targetCollectableID is for an existing Collectable
        UUID targetCollectableID = request.getTargetCollectableID();
        Optional<Collectable> optionalTargetCollectable = collectableRepo.findById(targetCollectableID);

        if(!optionalTargetCollectable.isPresent()){
            return new SwapCollectableResponse(false, "Invalid Collectable id for the targetCollectableID", null);
        }

        //check is targetCollectableID for a Collectable that is in the GeoCode
        GeoCode geoCode = optionalGeoCode.get();
        List<Collectable> geoCodeCollectables = geoCode.getCollectables();

        for(int i=0; i<geoCodeCollectables.size(); ++i){
            Collectable currentCollectable = geoCodeCollectables.get(i);

            if(currentCollectable.getId().equals(targetCollectableID)){
                //swap the user's current Collectable with the given Collectable in the GeoCode
                User currentUser = optionalUser.get();
                geoCodeCollectables.set(i, currentUser.getCurrentCollectable());
                geocodeRepo.save(geoCode);

                return new SwapCollectableResponse(true, "The user's Collectable was successfully swapped with the Collectable in the GeoCode", currentCollectable);
            }
        }

        return new SwapCollectableResponse(false, "The given targetCollectable is not in the targetGeoCode", null);
    }

    @Transactional
    public UpdateLocationResponse updateLocation(UpdateLocationRequest request){
        if (request == null) {
            return new UpdateLocationResponse(false, "The GetUserTrackableRequest object passed was NULL", null);
        }

        Optional<User> optionalUser = userRepo.findById(request.getUserID());

        if(!optionalUser.isPresent()){
            return new UpdateLocationResponse(false, "Invalid user id", null);
        }

        User currentUser = optionalUser.get();
        Collectable trackableObject = currentUser.getTrackableObject();

        //update the trackable's location
        //

        return new UpdateLocationResponse(true, "The trackable object's location was successfully updated", trackableObject);
    }
}
