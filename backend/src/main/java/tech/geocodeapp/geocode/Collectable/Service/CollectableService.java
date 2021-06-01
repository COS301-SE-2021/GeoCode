package tech.geocodeapp.geocode.Collectable.Service;

import org.springframework.stereotype.Service;

/**
 * This interface is for the User subsystem
 */
@Service
public interface CollectableService {
    //U1.1 createCollectable
    CreateCollectableResponse createCollectable(CreateCollectableRequest request);

    //U1.2 getCollectables
    GetCollectablesResponse getCollectables(GetCollectablesRequest request);
}
