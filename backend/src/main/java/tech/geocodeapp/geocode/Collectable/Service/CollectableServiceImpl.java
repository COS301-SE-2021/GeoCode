package tech.geocodeapp.geocode.Collectable.Service;

import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.Collectable.Decorator.CollectableTypeComponent;
import tech.geocodeapp.geocode.Collectable.Manager.CollectableTypeManager;
import tech.geocodeapp.geocode.Collectable.Model.Collectable;
import tech.geocodeapp.geocode.Collectable.Model.CollectableSet;
import tech.geocodeapp.geocode.Collectable.Model.CollectableType;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableRepository;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableSetRepository;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableTypeRepository;
import tech.geocodeapp.geocode.Collectable.Request.CreateCollectableRequest;
import tech.geocodeapp.geocode.Collectable.Request.CreateCollectableSetRequest;
import tech.geocodeapp.geocode.Collectable.Request.CreateCollectableTypeRequest;
import tech.geocodeapp.geocode.Collectable.Request.GetCollectableTypesBySetRequest;
import tech.geocodeapp.geocode.Collectable.Response.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This class implements the CollectableService interface
 */
@Service
public class CollectableServiceImpl implements CollectableService {
    private final CollectableRepository collectableRepo;

    private final CollectableSetRepository collectableSetRepo;

    private final CollectableTypeRepository collectableTypeRepo;

    public CollectableServiceImpl(CollectableRepository collectableRepo, CollectableSetRepository collectableSetRepo, CollectableTypeRepository collectableTypeRepo) {
        this.collectableRepo = collectableRepo;
        this.collectableSetRepo = collectableSetRepo;
        this.collectableTypeRepo = collectableTypeRepo;
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

            /*
             * Create CollectableResponse from collectable
             * Use CollectableTypeManager to convert the CollectableType to a CollectableTypeComponent
             */
            CollectableTypeManager manager = new CollectableTypeManager();
            CollectableResponse collectableResponse = new CollectableResponse(collectable.getId(), manager.buildCollectableType(collectable.getType()), collectable.getPastLocations());

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
}
