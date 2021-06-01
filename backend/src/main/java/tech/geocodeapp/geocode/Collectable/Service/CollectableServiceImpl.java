package tech.geocodeapp.geocode.Collectable.Service;

import io.swagger.model.CollectableSet;
import io.swagger.model.CollectableType;
import io.swagger.model.CreateCollectableSetRequest;
import io.swagger.model.CreateCollectableTypeRequest;
import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableRepository;

import javax.transaction.Transactional;

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
}
