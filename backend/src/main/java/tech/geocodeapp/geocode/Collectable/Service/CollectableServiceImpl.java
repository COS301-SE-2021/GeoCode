package tech.geocodeapp.geocode.Collectable.Service;

import io.swagger.model.*;
import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

/**
 * This class implements the UserService interface
 */
@Service
public class CollectableServiceImpl implements CollectableService {
    private CollectableRepository collectableRepo;

    public CollectableServiceImpl(CollectableRepository collectableRepo) {
        this.collectableRepo = collectableRepo;
    }

    @Transactional
    public CollectableSet createCollectableSet(CreateCollectableSetRequest request){
        if (request == null) {
            return null;
        }

        CollectableSet collectableSet = new CollectableSet(request.getName(), request.getDescription());
        return collectableSet;
    }

    @Transactional
    CollectableType createCollectableType(CreateCollectableTypeRequest request){
        if (request == null) {
            return null;
        }

        CollectableType collectableType = new CollectableType(request.getName(), request.getImage(), request.getRarity(), request.getSetId());
        return collectableType;
    }

    @Transactional
    Collectable createCollectable(CreateCollectableRequest request){
        if (request == null) {
            return null;
        }

        UUID typeID = request.getCollectableTypeId();
        Optional<CollectableType> collectableTypeOptional = collectableRepo.getCollectableTypeByID(typeID);

        if(!collectableTypeOptional.isPresent()){
            return null;
        }

        Collectable collectable = new Collectable(typeID);
        return collectable;
    }
}
