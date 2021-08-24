package tech.geocodeapp.geocode.mission.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.geocodeapp.geocode.general.CheckNullRequestParameters;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.mission.model.Mission;
import tech.geocodeapp.geocode.mission.model.MissionType;
import tech.geocodeapp.geocode.mission.repository.MissionRepository;
import tech.geocodeapp.geocode.mission.request.CreateMissionRequest;
import tech.geocodeapp.geocode.mission.request.GetMissionByIdRequest;
import tech.geocodeapp.geocode.mission.request.GetProgressRequest;
import tech.geocodeapp.geocode.mission.request.UpdateCompletionRequest;
import tech.geocodeapp.geocode.mission.response.CreateMissionResponse;
import tech.geocodeapp.geocode.mission.response.GetMissionByIdResponse;
import tech.geocodeapp.geocode.mission.response.GetProgressResponse;
import tech.geocodeapp.geocode.user.response.UpdateCompletionResponse;

import java.util.Random;

@Service("MissionService")
public class MissionServiceImpl implements MissionService{
    private final MissionRepository missionRepo;

    private final CheckNullRequestParameters checkNullRequestParameters = new CheckNullRequestParameters();
    private final String invalidMissionIdMessage = "Invalid Mission Id";

    public MissionServiceImpl(MissionRepository missionRepo) {
        this.missionRepo = missionRepo;
    }

    /**
     * Gets the Mission specified by the given ID
     * @param request GetMissionByIdRequest object
     * @return GetMissionByIdResponse object
     */
    @Transactional
    public GetMissionByIdResponse getMissionById(GetMissionByIdRequest request) throws NullRequestParameterException {
        if(request == null){
            return new GetMissionByIdResponse(false, "The GetMissionByIdRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        var optionalMission = missionRepo.findById(request.getMissionID());

        return optionalMission.map(mission -> new GetMissionByIdResponse(true, "The Mission was found", mission)).orElseGet(
                () -> new GetMissionByIdResponse(false, invalidMissionIdMessage, null));
    }

    @Transactional
    public GetProgressResponse getProgress(GetProgressRequest request) throws NullRequestParameterException {
        if(request == null){
            return new GetProgressResponse(false, "The GetProgressRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        //check if the missionID is invalid
        var optionalMission = missionRepo.findById(request.getMissionID());

        if(optionalMission.isEmpty()){
            return new GetProgressResponse(false, invalidMissionIdMessage, null);
        }

        var mission = optionalMission.get();

        //get the progress
        var progress = ((double) mission.getCompletion()) / mission.getAmount();

        return new GetProgressResponse(true, "Progress returned", progress);
    }

    /**
     * Creates a Mission for the given CollectableType
     * @param request CreateMissionRequest object
     * @return CreateMissionResponse object
     */
    @Transactional
    public CreateMissionResponse createMission(CreateMissionRequest request) throws NullRequestParameterException {
        if(request == null){
            return new CreateMissionResponse(false, "The CreateMissionRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        var collectable = request.getCollectable();

        //check if the Collectable already has a Mission
        if(collectable.getMissionID() != null){
            var collectableHasMissionMessage = "Collectable already has a Mission";
            return new CreateMissionResponse(false, collectableHasMissionMessage, null);
        }

        var collectableType = collectable.getType();

        //get the MissionType
        var missionType = MissionType.fromValue(collectableType.getProperties().get("missionType"));

        /* random allocation of missionType means there is an element of luck involved
        if type is Random - set type to one of the actual types */
        if(missionType == null || missionType.equals(MissionType.RANDOM)){
            missionType = MissionType.values()[new Random().nextInt(MissionType.values().length-1)];
        }

        //create the Mission
        var mission = new Mission();
        mission.setType(missionType);

        var amount = 0;

        switch(missionType){
            case DISTANCE:
                //circumference of the Earth (TODO: set the distance as an option (from front-end) [after Demo 3])
                amount = 40075;
                break;
            case GEOCODE:
                //100% when at the targeted GeoCode's location
                amount = 100;
                break;
            case SWAP:
                //varying difficulty for the number of swaps [1..10]
                amount = new Random().nextInt(10)+1;
                break;
        }

        mission.setCompletion(0);
        mission.setAmount(amount);

        //set the location to the Collectables current location
        var location = request.getLocation();
        mission.setLocation(location);

        missionRepo.save(mission);

        return new CreateMissionResponse(true, "Mission created", mission);
    }

    /**
     * Updates the completion for a Mission
     * @param request UpdateCompletionRequest object
     * @return UpdateCompletionResponse object
     */
    @Transactional
    public UpdateCompletionResponse updateCompletion(UpdateCompletionRequest request) throws NullRequestParameterException {
        if(request == null) {
            return new UpdateCompletionResponse(false, "The UpdateCompletionRequest object passed was NULL");
        }

        checkNullRequestParameters.checkRequestParameters(request);

        var mission = request.getMission();
        var location = request.getLocation();

        //update the completion for the Mission
        var missionType = mission.getType();

        switch(missionType){
            case SWAP:
                mission.setCompletion(mission.getCompletion()+1);
                break;
            case GEOCODE:
                //check if at the correct location
                if(mission.getLocation().equals(location)){
                    mission.setCompletion(100);
                }

                break;
            case DISTANCE:
                var addedDistance = mission.getLocation().distanceTo(location);
                mission.setCompletion((int) (mission.getCompletion()+addedDistance));
                break;
        }

        missionRepo.save(mission);

        return new UpdateCompletionResponse(true, "Completion updated");
    }
}
