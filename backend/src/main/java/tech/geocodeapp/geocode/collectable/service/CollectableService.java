package tech.geocodeapp.geocode.collectable.service;

import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.collectable.request.*;
import tech.geocodeapp.geocode.collectable.response.*;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;

/**
 * This interface is for the User subsystem
 */
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
    GetCollectableByIDResponse getCollectableByID( GetCollectableByIDRequest request ) throws NullRequestParameterException;
    GetCollectableTypeByIDResponse getCollectableTypeByID( GetCollectableTypeByIDRequest request ) throws NullRequestParameterException;

    //Deletes
    void deleteCollectables();
    void deleteCollectableTypes();
    void deleteCollectableSets();
}
