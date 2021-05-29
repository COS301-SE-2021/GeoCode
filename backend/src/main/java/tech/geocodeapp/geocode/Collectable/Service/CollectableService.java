package tech.geocodeapp.geocode.Collectable.Service;

import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.Collectable.Request.CreateCollectableRequest;
import tech.geocodeapp.geocode.Collectable.Request.GetCollectablesRequest;
import tech.geocodeapp.geocode.Collectable.Response.CreateCollectableResponse;
import tech.geocodeapp.geocode.Collectable.Response.GetCollectablesResponse;

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
