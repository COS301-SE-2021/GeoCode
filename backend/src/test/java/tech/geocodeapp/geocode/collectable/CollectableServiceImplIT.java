package tech.geocodeapp.geocode.collectable;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.geocodeapp.geocode.collectable.model.Rarity;
import tech.geocodeapp.geocode.collectable.request.*;
import tech.geocodeapp.geocode.collectable.response.*;
import tech.geocodeapp.geocode.collectable.service.CollectableServiceImpl;


import java.util.HashMap;
import java.util.UUID;

@SpringBootTest
public class CollectableServiceImplIT {
    @Autowired
    private CollectableServiceImpl collectableService;
    private UUID validSetId;
    private UUID validTypeId;

    @BeforeEach
     void build() {
        CreateCollectableSetRequest request = new CreateCollectableSetRequest();
        request.setName("Test Set");
        request.setDescription("A set for tests");
        CreateCollectableSetResponse response = collectableService.createCollectableSet(request);
        validSetId = response.getCollectableSet().getId();

        CreateCollectableTypeRequest typeRequest = new CreateCollectableTypeRequest();
        typeRequest.setName("Test Type");
        typeRequest.setImage("dgergergnhtfhjhg");
        typeRequest.setRarity(Rarity.RARE);
        typeRequest.setId(validSetId);

//        HashMap<String, String> properties = new HashMap<String, String>();
//        properties.put("missionType", "Swap");
//        typeRequest.setProperties(properties);
        //System.out.println("validSetId: "+validSetId);

        CreateCollectableTypeResponse typeResponse = collectableService.createCollectableType(typeRequest);
        validTypeId = typeResponse.getCollectableType().getId();

        //System.out.println("validTypeId: "+validTypeId);
    }


    @Test
    public void createCollectableSetTest(){
        /*
           Create a request object
          and assign values to it
          */
        CreateCollectableSetRequest request = new CreateCollectableSetRequest();
        request.setName("Christmas");
        request.setDescription("Christmas themed collectables");
        CreateCollectableSetResponse response = collectableService.createCollectableSet(request);

        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    public void createCollectableSetNullRequestTest(){
        CreateCollectableSetResponse response = collectableService.createCollectableSet(null);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals("The CreateCollectableSetRequest object passed was NULL", response.getMessage());
        Assertions.assertNull(response.getCollectableSet());
    }

    @Test
    public void createCollectableTypeTestInvalid(){
        /*
           Create a request object
          and assign values to it
          */
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
        /*
           Create a request object
          and assign values to it
          */

        CreateCollectableTypeRequest request = new CreateCollectableTypeRequest();
        request.setName("Santa");
        request.setImage("dgergergnhtfhjhg");
        request.setRarity(Rarity.RARE);
        request.setId(validSetId);

        CreateCollectableTypeResponse response = collectableService.createCollectableType(request);

        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    public void createCollectableTypeNullRequestTest(){
        CreateCollectableTypeResponse response = collectableService.createCollectableType(null);
        Assertions.assertEquals("The CreateCollectableTypeRequest object passed was NULL", response.getMessage());
    }

    @Test
    public void createCollectableTestInvalid(){
        /*
           Create a request object
          and assign values to it
          */
        CreateCollectableRequest request = new CreateCollectableRequest();
        request.setCollectableTypeId(UUID.fromString("de65c61f-c657-4c76-9e58-0830cc13dd0f"));
        CreateCollectableResponse response = collectableService.createCollectable(request);

        Assertions.assertFalse(response.isSuccess());
    }

    @Test
    public void createCollectableNullRequestTest(){
        CreateCollectableResponse response = collectableService.createCollectable(null);
        Assertions.assertEquals("The CreateCollectableSetRequest object passed was NULL", response.getMessage());
    }

    @Test
    public void createCollectableTestValid(){
        /*
           Create a request object
          and assign values to it
          */


        //create the Collectable
        CreateCollectableRequest collectableRequest = new CreateCollectableRequest();
        collectableRequest.setCollectableTypeId(validTypeId);

        CreateCollectableResponse collectableResponse = collectableService.createCollectable(collectableRequest);
        Assertions.assertTrue(collectableResponse.isSuccess());
    }

    @Test
    public void getCollectableSetsTest(){
        /*
           Create a request object
          and assign values to it
          */

        GetCollectableSetsResponse response = collectableService.getCollectableSets();
        Assertions.assertTrue(!response.getCollectableSets().isEmpty());
    }

    @Test
    public void getCollectableTypesTest(){
        /*
           Create a request object
          and assign values to it
          */

        GetCollectableTypesResponse response = collectableService.getCollectableTypes();
        Assertions.assertTrue(!response.getCollectableTypes().isEmpty());
    }

    @Test
    public void getCollectableTypesBySetTest(){
        /*
           Create a request object
          and assign values to it
          */

        //create the request
        GetCollectableTypesBySetRequest typesBySetRequest = new GetCollectableTypesBySetRequest();
        typesBySetRequest.setSetId(UUID.fromString("f5d7b724-14c1-4344-b190-77071e019b4c"));

        GetCollectableTypesResponse response = collectableService.getCollectableTypesBySet(typesBySetRequest);
        Assertions.assertTrue(!response.getCollectableTypes().isEmpty());
    }

    @Test
    public void getCollectableTypesBySetTestNoTypeWithSet(){
        /*
           Create a request object
          and assign values to it
          */

        GetCollectableTypesBySetRequest typesBySetRequest = new GetCollectableTypesBySetRequest();
        typesBySetRequest.setSetId(UUID.randomUUID());

        GetCollectableTypesResponse response = collectableService.getCollectableTypesBySet(typesBySetRequest);
        Assertions.assertTrue(response.getCollectableTypes().isEmpty());
    }

    @Test
    public void getCollectablesTest(){
        /*
           Create a request object
          and assign values to it
          */

        GetCollectablesResponse response = collectableService.getCollectables();
        Assertions.assertTrue(!response.getCollectables().isEmpty());
    }

}
