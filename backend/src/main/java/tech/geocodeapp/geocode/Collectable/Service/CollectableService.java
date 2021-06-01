package tech.geocodeapp.geocode.Collectable.Service;

import io.swagger.model.*;
import org.springframework.stereotype.Service;

/**
 * This interface is for the User subsystem
 */
@Service
public interface CollectableService {
    CollectableSet createCollectableSet(CreateCollectableSetRequest request);

    CollectableType createCollectableType(CreateCollectableTypeRequest request);

    Collectable createCollectable(CreateCollectableRequest request);
}
