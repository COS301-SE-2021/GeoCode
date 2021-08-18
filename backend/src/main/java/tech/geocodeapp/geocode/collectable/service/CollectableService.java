package tech.geocodeapp.geocode.collectable.service;

import tech.geocodeapp.geocode.collectable.request.*;
import tech.geocodeapp.geocode.collectable.response.*;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.mission.service.MissionServiceImpl;

/**
 * This interface is for the User subsystem
 */
public interface CollectableService {
    //Creators
    CreateCollectableSetResponse createCollectableSet(CreateCollectableSetRequest request) throws NullRequestParameterException;
    CreateCollectableTypeResponse createCollectableType(CreateCollectableTypeRequest request) throws NullRequestParameterException;
    CreateCollectableResponse createCollectable(CreateCollectableRequest request) throws NullRequestParameterException;

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

    void setMissionService(MissionServiceImpl missionService);
}
