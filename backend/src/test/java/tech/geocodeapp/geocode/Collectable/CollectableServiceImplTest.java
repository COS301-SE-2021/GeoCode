package tech.geocodeapp.geocode.Collectable;

import io.swagger.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableRepository;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableSetRepository;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableTypeRepository;
import tech.geocodeapp.geocode.Collectable.Response.CreateCollectableResponse;
import tech.geocodeapp.geocode.Collectable.Response.CreateCollectableSetResponse;
import tech.geocodeapp.geocode.Collectable.Response.CreateCollectableTypeResponse;
import tech.geocodeapp.geocode.Collectable.Service.CollectableService;
import tech.geocodeapp.geocode.Collectable.Service.CollectableServiceImpl;

import java.util.UUID;

@ExtendWith( MockitoExtension.class )
public class CollectableServiceImplTest {
    @Mock
    private CollectableRepository collectableRepo;

    @Mock
    private CollectableSetRepository collectableSetRepo;

    @Mock
    private CollectableTypeRepository collectableTypeRepo;

    private CollectableService collectableService;

    CollectableServiceImplTest (){

    }

    @BeforeEach
    void setup() {
        collectableService = new CollectableServiceImpl(collectableRepo, collectableSetRepo, collectableTypeRepo);
        CreateCollectableSetRequest setRequest = new CreateCollectableSetRequest();
        setRequest.setName("Test");
        setRequest.setDescription("Test Set");
        CreateCollectableSetResponse setResponse = collectableService.createCollectableSet(setRequest);
        setResponse.getCollectableSet().setId(UUID.fromString("dc40c921-ca14-414f-8332-2493c8b351ff"));

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
        CreateCollectableSetResponse response = collectableService.createCollectableSet(request);

        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    public void createCollectableTypeTestInvalid(){
        /**
         *  Create a request object
         * and assign values to it
         * */
        CreateCollectableTypeRequest request = new CreateCollectableTypeRequest();
        request.setName("Santa");
        request.setImage("dgergergnhtfhjhg");
        request.setRarity(Rarity.RARE);
        request.setId(UUID.fromString("31d72621-091c-49ad-9c28-8abda8b8f055"));
        CreateCollectableTypeResponse response = collectableService.createCollectableType(request);

        Assertions.assertFalse(response.isSuccess());
    }

    @Test
    public void createCollectableTypeTestValid(){
        /**
         *  Create a request object
         * and assign values to it
         * */
        CreateCollectableTypeRequest request = new CreateCollectableTypeRequest();
        request.setName("Santa");
        request.setImage("dgergergnhtfhjhg");
        request.setRarity(Rarity.RARE);
        request.setId(UUID.fromString("dc40c921-ca14-414f-8332-2493c8b351ff"));
        CreateCollectableTypeResponse response = collectableService.createCollectableType(request);

        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    public void createCollectableTestInvalid(){
        /**
         *  Create a request object
         * and assign values to it
         * */
        CreateCollectableRequest request = new CreateCollectableRequest();
        request.setCollectableTypeId(UUID.fromString("de65c61f-c657-4c76-9e58-0830cc13dd0f"));
        CreateCollectableResponse response = collectableService.createCollectable(request);

        Assertions.assertFalse(response.isSuccess());
    }

    @Test
    public void createCollectableTestValid(){
        /**
         *  Create a request object
         * and assign values to it
         * */
        CreateCollectableRequest request = new CreateCollectableRequest();
        request.setCollectableTypeId(UUID.fromString(""));//
        CreateCollectableResponse response = collectableService.createCollectable(request);

        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    public void getCollectableSetsTest(){
        /**
         *  Create a request object
         * and assign values to it
         * */
        GetCollectableSetsResponse response = collectableService.getCollectableSets();
        Assertions.assertTrue(!response.getCollectableSets().isEmpty());
    }

    @Test
    public void getCollectableTypesTest(){
        /**
         *  Create a request object
         * and assign values to it
         * */
        GetCollectableTypesResponse response = collectableService.getCollectableTypes();
        Assertions.assertTrue(!response.getCollectableTypes().isEmpty());
    }

    @Test
    public void getCollectablesTest(){
        /**
        *  Create a request object
        * and assign values to it
        * */
        GetCollectablesResponse response = collectableService.getCollectables();
        Assertions.assertTrue(!response.getCollectables().isEmpty());
    }
}
