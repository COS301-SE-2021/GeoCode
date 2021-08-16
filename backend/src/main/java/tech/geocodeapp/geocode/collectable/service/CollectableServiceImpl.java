package tech.geocodeapp.geocode.collectable.service;

import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.collectable.decorator.CollectableTypeComponent;
import tech.geocodeapp.geocode.collectable.manager.CollectableTypeManager;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.collectable.model.CollectableSet;
import tech.geocodeapp.geocode.collectable.model.CollectableType;
import tech.geocodeapp.geocode.collectable.model.Rarity;
import tech.geocodeapp.geocode.collectable.repository.CollectableRepository;
import tech.geocodeapp.geocode.collectable.repository.CollectableSetRepository;
import tech.geocodeapp.geocode.collectable.repository.CollectableTypeRepository;
import tech.geocodeapp.geocode.collectable.request.*;
import tech.geocodeapp.geocode.collectable.response.*;
import tech.geocodeapp.geocode.general.CheckNullRequestParameters;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.mission.request.CreateMissionRequest;
import tech.geocodeapp.geocode.mission.response.CreateMissionResponse;
import tech.geocodeapp.geocode.mission.service.MissionService;

import javax.transaction.Transactional;
import java.util.*;

/**
 * This class implements the CollectableService interface
 */
@Service( "CollectableService" )
public class CollectableServiceImpl implements CollectableService {
    private final CollectableRepository collectableRepo;

    private final CollectableSetRepository collectableSetRepo;

    private final CollectableTypeRepository collectableTypeRepo;

    private final MissionService missionService;

    private final CheckNullRequestParameters checkNullRequestParameters = new CheckNullRequestParameters();
    private final String invalidCollectableIdMessage = "Invalid Collectable ID";
    private String invalidCollectableTypeIdMessage = "Invalid CollectableType ID";

    public CollectableServiceImpl(CollectableRepository collectableRepo, CollectableSetRepository collectableSetRepo, CollectableTypeRepository collectableTypeRepo, MissionService missionService) {
        this.collectableRepo = collectableRepo;
        this.collectableSetRepo = collectableSetRepo;
        this.collectableTypeRepo = collectableTypeRepo;
        this.missionService = missionService;

        initialiseUserTrackables();
    }

    @Transactional
    public CreateCollectableSetResponse createCollectableSet(CreateCollectableSetRequest request){
        if (request == null) {
            return new CreateCollectableSetResponse(false, "The CreateCollectableSetRequest object passed was NULL", null);
        }

        CollectableSet collectableSet = new CollectableSet(request.getName(), request.getDescription());
        CollectableSet savedCollectableSet = collectableSetRepo.save(collectableSet);
        return new CreateCollectableSetResponse(true, "The CollectableSet was successfully created", collectableSet);
    }

    @Transactional
    public CreateCollectableTypeResponse createCollectableType(CreateCollectableTypeRequest request){
        if (request == null) {
            return new CreateCollectableTypeResponse(false, "The CreateCollectableTypeRequest object passed was NULL", null);
        }

        UUID setID = request.getSetId();
        Optional<CollectableSet> collectableSetOptional = collectableSetRepo.findById(setID);

        if(collectableSetOptional.isPresent()){
            CollectableType collectableType = new CollectableType(request.getName(), request.getImage(), request.getRarity(), collectableSetOptional.get(), request.getProperties());
            CollectableType savedCollectableType = collectableTypeRepo.save(collectableType);

            //create instance of CollectableTypeManager to handle conversion
            CollectableTypeManager manager = new CollectableTypeManager();

            return new CreateCollectableTypeResponse(true, "The CollectableType was successfully created", manager.buildCollectableType(collectableType));
        }else{
            return new CreateCollectableTypeResponse(false, "The given setID was invalid", null);
        }
    }

    @Transactional
    public CreateCollectableResponse createCollectable(CreateCollectableRequest request){
        if (request == null) {
            return new CreateCollectableResponse(false, "The CreateCollectableSetRequest object passed was NULL", null);
        }

        UUID typeID = request.getCollectableTypeId();
        Optional<CollectableType> collectableTypeOptional = collectableTypeRepo.findById(typeID);

        if(collectableTypeOptional.isPresent()){
            Collectable collectable = new Collectable(collectableTypeOptional.get());
            Collectable savedCollectable = collectableRepo.save(collectable);
            if(request.isCreateMission()) {
                CreateMissionRequest createMissionRequest = new CreateMissionRequest(savedCollectable.getId());
                try {
                   CreateMissionResponse missionResponse = missionService.createMission(createMissionRequest);
                   if(missionResponse.isSuccess()) {
                       savedCollectable.setMissionID(missionResponse.getMission().getId());
                       collectableRepo.save(savedCollectable);
                   }
                } catch (NullRequestParameterException e) {
                    e.printStackTrace();
                }
            }
            /*
             * Create CollectableResponse from collectable
             * Use CollectableTypeManager to convert the CollectableType to a CollectableTypeComponent
             */
            CollectableTypeManager manager = new CollectableTypeManager();
            CollectableResponse collectableResponse = new CollectableResponse(savedCollectable.getId(), manager.buildCollectableType(savedCollectable.getType()), savedCollectable.getPastLocations());

            return new CreateCollectableResponse(true, "The Collectable was successfully created", collectableResponse);
        }else{
            return new CreateCollectableResponse(false, "The given collectableTypeId was invalid", null);
        }
    }

    @Transactional
    public GetCollectablesResponse getCollectables(){
        GetCollectablesResponse response = new GetCollectablesResponse();

        //create a list of CollectableResponses
        List<CollectableResponse> collectableResponses = new ArrayList<>();

        //Create CollectableTypeManager to convert CollectableTypes
        CollectableTypeManager manager = new CollectableTypeManager();

        //get all Collectables and build collectableResponses from them
        List<Collectable> collectables = collectableRepo.findAll();
        for (Collectable collectable : collectables) {
            CollectableResponse temp = new CollectableResponse(collectable.getId(), manager.buildCollectableType(collectable.getType()), collectable.getPastLocations());
            collectableResponses.add(temp);
        }

        response.setCollectables(collectableResponses);
        return response;
    }

    /**
     * Creates a response object to return all {@link CollectableType} objects
     * @return the response object containing a list of {@link CollectableTypeComponent} objects
     */
    @Transactional
    public GetCollectableTypesResponse getCollectableTypes(){
        GetCollectableTypesResponse response = new GetCollectableTypesResponse();

        //create CollectableTypeManager instance to handle conversion
        CollectableTypeManager manager = new CollectableTypeManager();

        //retrieve all CollectableTypes
        List<CollectableType> collectableTypes = new ArrayList<>(collectableTypeRepo.findAll());

        //iterate through the collectableTypes list and add converted CollectableTypeComponents to the collectableTypeComponents list
        for (CollectableType collectableType : collectableTypes) {
           response.addCollectableTypesItem(manager.buildCollectableType(collectableType));
        }
        return response;
    }

    @Transactional
    public GetCollectableSetsResponse getCollectableSets(){
        GetCollectableSetsResponse response = new GetCollectableSetsResponse();
        response.setCollectableSets(collectableSetRepo.findAll());
        return response;
    }

    @Transactional
    public GetCollectableTypesResponse getCollectableTypesBySet(GetCollectableTypesBySetRequest request) {
        GetCollectableTypesResponse response = new GetCollectableTypesResponse();

        //create CollectableTypeManager instance to handle conversion
        CollectableTypeManager manager = new CollectableTypeManager();

        //retrieve all CollectableTypes
        List<CollectableType> collectableTypes = new ArrayList<>(collectableTypeRepo.findAll());

        //iterate through the collectableTypes list and add converted CollectableTypeComponents to the collectableTypeComponents list if the set matches
        for (CollectableType collectableType : collectableTypes) {
            if(request.getSetId().equals(collectableType.getSet().getId())) {
                response.addCollectableTypesItem(manager.buildCollectableType(collectableType));
            }
        }
        return response;
    }

    @Transactional
    public GetCollectableByIDResponse getCollectableByID( GetCollectableByIDRequest request ) throws NullRequestParameterException {
        if(request == null){
            return new GetCollectableByIDResponse(false, "The GetCollectableByIDRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        //check if the CollectableID is invalid
        Optional<Collectable> optionalCollectable = collectableRepo.findById(request.getCollectableID());

        if(optionalCollectable.isEmpty()){
            return new GetCollectableByIDResponse(false, invalidCollectableIdMessage, null);
        }

        return new GetCollectableByIDResponse(true, "Collectable found", optionalCollectable.get());
    }

    @Transactional
    public GetCollectableTypeByIDResponse getCollectableTypeByID( GetCollectableTypeByIDRequest request ) throws NullRequestParameterException {
        if(request == null){
            return new GetCollectableTypeByIDResponse(false, "The GetCollectableTypeByIDRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        //check if the CollectableID is invalid
        Optional<CollectableType> optionalCollectableType = collectableTypeRepo.findById(request.getCollectableTypeID());

        if(optionalCollectableType.isEmpty()){
            return new GetCollectableTypeByIDResponse(false, invalidCollectableTypeIdMessage, null);
        }

        return new GetCollectableTypeByIDResponse(true, "CollectableType found", optionalCollectableType.get());
    }

    @Transactional
    public void deleteCollectables() {
        collectableRepo.deleteAll();
    }

    @Transactional
    public void deleteCollectableTypes() {
        collectableTypeRepo.deleteAll();
    }

    @Transactional
    public void deleteCollectableSets() {
        collectableSetRepo.deleteAll();
    }

    private void initialiseUserTrackables() {
        /*
         * The "User Trackables" set and type are required to exist before users can enter the system.
         * Create them on application load if they do not exist.
         */
        UUID userTrackableID = new UUID(0, 0);

        Optional<CollectableSet> temp = collectableSetRepo.findById(userTrackableID);
        CollectableSet userTrackableSet = null;
        if (temp.isPresent()) {
            userTrackableSet = temp.get();
        } else {
            /* Create the User Trackable set */
            userTrackableSet = new CollectableSet();
            userTrackableSet.setId(userTrackableID);
            userTrackableSet.setName("User Trackables");
            userTrackableSet.setDescription("User Trackables");
            collectableSetRepo.save(userTrackableSet);
        }

        if (!collectableTypeRepo.existsById(userTrackableID)) {
            HashMap<String, String> properties = new HashMap<>();
            properties.put("trackable", "true");

            /* Create the User Trackable type */
            CollectableType userTrackableType = new CollectableType();
            userTrackableType.setId(userTrackableID);
            userTrackableType.setName("User Trackables");
            userTrackableType.setImage("https://via.placeholder.com/100");
            userTrackableType.setRarity(Rarity.UNIQUE);
            userTrackableType.setSet(userTrackableSet);
            userTrackableType.setProperties(properties);
            collectableTypeRepo.save(userTrackableType);
        }
    }
}
