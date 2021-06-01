package tech.geocodeapp.geocode.Collectable.Service;

import io.swagger.model.CollectableSet;
import io.swagger.model.CreateCollectableSetRequest;
import org.springframework.stereotype.Service;

/**
 * This interface is for the User subsystem
 */
@Service
public interface CollectableService {
    CollectableSet createCollectableSet(CreateCollectableSetRequest request);


}
