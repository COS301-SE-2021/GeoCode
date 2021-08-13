package tech.geocodeapp.geocode.mission.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.collectable.model.CollectableType;
import tech.geocodeapp.geocode.collectable.request.GetCollectableByIDRequest;
import tech.geocodeapp.geocode.collectable.response.GetCollectableByIDResponse;
import tech.geocodeapp.geocode.collectable.service.CollectableService;
import tech.geocodeapp.geocode.general.CheckNullRequestParameters;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.mission.model.Mission;
import tech.geocodeapp.geocode.mission.model.MissionType;
import tech.geocodeapp.geocode.mission.repository.MissionRepository;
import tech.geocodeapp.geocode.mission.request.CreateMissionRequest;
import tech.geocodeapp.geocode.mission.request.GetMissionByIdRequest;
import tech.geocodeapp.geocode.mission.request.GetProgressRequest;
import tech.geocodeapp.geocode.mission.response.CreateMissionResponse;
import tech.geocodeapp.geocode.mission.response.GetMissionByIdResponse;
import tech.geocodeapp.geocode.mission.response.GetProgressResponse;

import javax.validation.constraints.NotNull;
import java.util.*;

@Service("MissionService")
public class MissionServiceImpl implements MissionService{
    private final MissionRepository missionRepo;

    @NotNull(message = "Collectable Service Implementation may not be null.")
    private final CollectableService collectableService;

    private final CheckNullRequestParameters checkNullRequestParameters = new CheckNullRequestParameters();

    public MissionServiceImpl(MissionRepository missionRepo, CollectableService collectableService) {
        this.missionRepo = missionRepo;
        this.collectableService = collectableService;
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

        Optional<Mission> optionalMission = missionRepo.findById(request.getMissionID());

        return optionalMission.map(mission -> new GetMissionByIdResponse(true, "Mission found", mission)).orElseGet(
                () -> new GetMissionByIdResponse(false, "Mission not found", null));
    }

    @Transactional
    public GetProgressResponse getProgress(GetProgressRequest request) throws NullRequestParameterException {
        return null;
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

        //check if the CollectableID is invalid
        GetCollectableByIDRequest getCollectableByIDRequest = new GetCollectableByIDRequest(request.getCollectableID());
        GetCollectableByIDResponse getCollectableByIDResponse = collectableService.getCollectableByID(getCollectableByIDRequest);

        if(!getCollectableByIDResponse.isSuccess()){
            return new CreateMissionResponse(false, getCollectableByIDResponse.getMessage(), null);
        }

        Collectable collectable = getCollectableByIDResponse.getCollectable();
        CollectableType collectableType = collectable.getType();

        //get the MissionType
        MissionType missionType = MissionType.fromValue(collectableType.getProperties().get("missionType"));

        //if type is Random - set type to one of the actual types
        if(Objects.requireNonNull(missionType).equals(MissionType.RANDOM)){
            missionType = MissionType.values()[new Random().nextInt(MissionType.values().length-1)];
        }

        //create the Mission
        Mission mission = new Mission();
        mission.setType(missionType);
        mission.setAmount(0);

        //set the location to the Collectables current location
        List<GeoPoint> pastLocations = new ArrayList<>(collectable.getPastLocations());
        GeoPoint location = pastLocations.get(pastLocations.size()-1);
        mission.setLocation(location);

        missionRepo.save(mission);

        return new CreateMissionResponse(true, "Mission created", mission);
    }
}
