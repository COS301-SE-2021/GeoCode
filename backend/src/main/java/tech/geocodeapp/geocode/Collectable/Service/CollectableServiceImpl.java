package tech.geocodeapp.geocode.Collectable.Service;

import io.swagger.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableRepository;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableSetRepository;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableTypeRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

/**
 * This class implements the UserService interface
 */
@Service
public class CollectableServiceImpl implements CollectableService {
    @Autowired
    private CollectableRepository collectableRepo;

    @Autowired
    private CollectableSetRepository collectableSetRepo;

    @Autowired
    private CollectableTypeRepository collectableTypeRepo;

    public CollectableServiceImpl() {

    }

    public CollectableSet createCollectableSet(CreateCollectableSetRequest request){
        if (request == null) {
            return null;
        }

        CollectableSet collectableSet = new CollectableSet(request.getName(), request.getDescription());
        CollectableSet savedCollectableSet = collectableSetRepo.save(collectableSet);
        return collectableSet;
    }

    public CollectableType createCollectableType(CreateCollectableTypeRequest request){
        if (request == null) {
            return null;
        }

        UUID setID = request.getSetId();
        Optional<CollectableSet> collectableSetOptional = collectableSetRepo.findById(setID);

        if(collectableSetOptional.isPresent()){
            CollectableType collectableType = new CollectableType(request.getName(), request.getImage(), request.getRarity(), collectableSetOptional.get());
            CollectableType savedCollectableType = collectableTypeRepo.save(collectableType);
            return collectableType;
        }else{
            return null;
        }
    }

    public Collectable createCollectable(CreateCollectableRequest request){
        if (request == null) {
            return null;
        }

        UUID typeID = request.getCollectableTypeId();
        Optional<CollectableType> collectableTypeOptional = collectableTypeRepo.findById(typeID);

        if(collectableTypeOptional.isPresent()){
            Collectable collectable = new Collectable(collectableTypeOptional.get());
            Collectable savedCollectable = collectableRepo.save(collectable);
            return collectable;
        }else{
            return null;
        }
    }

    public GetCollectablesResponse getCollectables(){
        GetCollectablesResponse response = new GetCollectablesResponse();
        response.setCollectables(collectableRepo.findAll());
        return response;
    }

    public GetCollectableTypesResponse getCollectableTypes(){
        GetCollectableTypesResponse response = new GetCollectableTypesResponse();
        response.setCollectableTypes(collectableTypeRepo.findAll());
        return response;
    }

    public GetCollectableSetsResponse getCollectableSets(){
        GetCollectableSetsResponse response = new GetCollectableSetsResponse();
        response.setCollectableSets(collectableSetRepo.findAll());
        return response;
    }
}
