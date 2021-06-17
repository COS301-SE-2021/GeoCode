package tech.geocodeapp.geocode.Collectable.Service;

import io.swagger.model.*;
import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.Collectable.Response.CreateCollectableResponse;
import tech.geocodeapp.geocode.Collectable.Response.CreateCollectableSetResponse;
import tech.geocodeapp.geocode.Collectable.Response.CreateCollectableTypeResponse;

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
}
