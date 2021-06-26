package tech.geocodeapp.geocode.collectable.service;

import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.collectable.request.*;
import tech.geocodeapp.geocode.collectable.response.*;

/**
 * This interface is for the User subsystem
 */
@Service
public interface CollectableService {
    //Creators
    CreateCollectableSetResponse createCollectableSet(CreateCollectableSetRequest request);
    CreateCollectableTypeResponse createCollectableType(CreateCollectableTypeRequest request);
    CreateCollectableResponse createCollectable(CreateCollectableRequest request);

    //Getters
    GetCollectablesResponse getCollectables();
    GetCollectableTypesResponse getCollectableTypes();
    GetCollectableSetsResponse getCollectableSets();
    GetCollectableTypesResponse getCollectableTypesBySet(GetCollectableTypesBySetRequest request);

    //Deletes
    void deleteCollectables();
    void deleteCollectableTypes();
    void deleteCollectableSets();
}
