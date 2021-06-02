package tech.geocodeapp.geocode.Collectable.Service;

import io.swagger.model.*;
import org.springframework.stereotype.Service;

/**
 * This interface is for the User subsystem
 */
@Service
public interface CollectableService {
    //Creators
    CollectableSet createCollectableSet(CreateCollectableSetRequest request);
    CollectableType createCollectableType(CreateCollectableTypeRequest request);
    Collectable createCollectable(CreateCollectableRequest request);

    //Getters
    GetCollectablesResponse getCollectables();
    GetCollectableTypesResponse getCollectableTypes();
    GetCollectableSetsResponse getCollectableSets();
}
