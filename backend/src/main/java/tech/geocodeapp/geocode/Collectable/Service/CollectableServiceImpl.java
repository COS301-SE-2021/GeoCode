package tech.geocodeapp.geocode.Collectable.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableRepository;
import tech.geocodeapp.geocode.Collectable.Request.CreateCollectableRequest;
import tech.geocodeapp.geocode.Collectable.Request.GetCollectablesRequest;
import tech.geocodeapp.geocode.Collectable.Response.CreateCollectableResponse;
import tech.geocodeapp.geocode.Collectable.Response.GetCollectablesResponse;

import javax.transaction.Transactional;

/**
 * This class implements the UserService interface
 */
@Service
public class CollectableServiceImpl implements CollectableService {
    @Autowired
    private CollectableRepository collectableRepository;

    public CollectableServiceImpl() {

    }

    @Transactional
    public CreateCollectableResponse createCollectable(CreateCollectableRequest request){
        if (request == null) {
            return new CreateCollectableResponse();//TODO: pass parameters once class done
        }

        return null;
    }

    @Transactional
    public GetCollectablesResponse getCollectables(GetCollectablesRequest request){
        if (request == null) {
            return new GetCollectablesResponse();//TODO: pass parameters once class done
        }

        return null;
    }
}
