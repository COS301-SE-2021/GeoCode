package tech.geocodeapp.geocode.Collectable.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.Collectable.Model.Collectable;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableRepository;
import tech.geocodeapp.geocode.Collectable.Request.CreateCollectableRequest;
import tech.geocodeapp.geocode.Collectable.Request.GetCollectablesRequest;
import tech.geocodeapp.geocode.Collectable.Response.CreateCollectableResponse;
import tech.geocodeapp.geocode.Collectable.Response.GetCollectablesResponse;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * This class implements the UserService interface
 */
@Service
public class CollectableServiceImpl implements CollectableService {
    private CollectableRepository collectableRepository;

    public CollectableServiceImpl(CollectableRepository collectableRepository) {

    }

    @Transactional
    public CreateCollectableResponse createCollectable(CreateCollectableRequest request){
        if (request == null) {
            return new CreateCollectableResponse(false, "The CreateCollectableRequest object was null");
        }

        Optional<Collectable> collectableName = collectableRepository.getCollectableByName(request.getName());

        if(collectableName.isPresent()){
            return new CreateCollectableResponse(false, "There already exists a Collectable with that name");
        }

        Collectable newCollectable = new Collectable(request.getImage(), request.getName(), request.getRarity());
        Collectable checkIfSaved = collectableRepository.save(newCollectable);

        if(checkIfSaved != newCollectable){
            return new CreateCollectableResponse(false, "The Collectable was not saved.");
        }else{
            return new CreateCollectableResponse(true, "The Collectable was successfully created.");
        }
    }

    @Transactional
    public GetCollectablesResponse getCollectables(GetCollectablesRequest request){
        if (request == null) {
            return new GetCollectablesResponse(false, "The GetCollectablesRequest object was null");
        }



        return null;
    }
}
