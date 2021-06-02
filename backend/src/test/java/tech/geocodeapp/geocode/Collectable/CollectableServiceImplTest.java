package tech.geocodeapp.geocode.Collectable;

import io.swagger.model.CreateCollectableSetRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableRepository;
import tech.geocodeapp.geocode.Collectable.Service.CollectableService;
import tech.geocodeapp.geocode.Collectable.Service.CollectableServiceImpl;

@ExtendWith( MockitoExtension.class )
public class CollectableServiceImplTest {
    @Mock
    @Autowired
    private CollectableRepository collectableRepo;

    private CollectableService collectableService;

    CollectableServiceImplTest (){

    }

    @BeforeEach
    void setup() {
        collectableService = new CollectableServiceImpl();
    }

    @Test
    public void createCollectableSetTest(){
        /**
         *  Create a request object
         * and assign values to it
         * */
        CreateCollectableSetRequest request = new CreateCollectableSetRequest();
        request.setName("Christmas");
        request.setDescription("Christmas themed collectables");
        collectableService.createCollectableSet(request);
    }

    @Test
    public void createCollectableTypeTest(){

    }

    @Test
    public void createCollectableTest(){

    }

    @Test
    public void getCollectableSetsTest(){

    }

    @Test
    public void getCollectableTypesTest(){

    }

    @Test
    public void getCollectablesTest(){

    }
}
