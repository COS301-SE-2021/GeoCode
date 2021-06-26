package tech.geocodeapp.geocode.collectable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.geocodeapp.geocode.collectable.model.Rarity;
import tech.geocodeapp.geocode.collectable.Request.*;
import tech.geocodeapp.geocode.collectable.Response.*;
import tech.geocodeapp.geocode.collectable.Service.*;

import java.util.UUID;

@ExtendWith( MockitoExtension.class )
public class CollectableServiceImplTest {

    private CollectableService collectableService;

    CollectableServiceImplTest (){

    }

    @BeforeEach
    void setup() {
        collectableService = new CollectableServiceImpl(new CollectableMockRepository(), new CollectableSetMockRepository(), new CollectableTypeMockRepository());
            collectableService.deleteCollectableSets();
            collectableService.deleteCollectables();
            collectableService.deleteCollectableTypes();
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

        Assertions.assertEquals("The CreateCollectableSetRequest object passed was NULL", response.getMessage());
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
        //create the set
        CreateCollectableSetRequest setRequest = new CreateCollectableSetRequest();
        setRequest.setName("Easter");
        setRequest.setDescription("Themed collectables that can be collected over the Easter weekend");

        CreateCollectableSetResponse setResponse = collectableService.createCollectableSet(setRequest);

        CreateCollectableTypeRequest request = new CreateCollectableTypeRequest();
        request.setName("Santa");
        request.setImage("dgergergnhtfhjhg");
        request.setRarity(Rarity.RARE);
        request.setId(setResponse.getCollectableSet().getId());

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
        /**
         *  Create a request object
         * and assign values to it
         * */

        //create the set
        CreateCollectableSetRequest setRequest = new CreateCollectableSetRequest();
        setRequest.setName("Easter");
        setRequest.setDescription("Themed collectables that can be collected over the Easter weekend");

        CreateCollectableSetResponse setResponse = collectableService.createCollectableSet(setRequest);

        //create the type
        CreateCollectableTypeRequest typeRequest = new CreateCollectableTypeRequest();
        typeRequest.setName("Bunny");
        typeRequest.setImage("kasnvklnvd");
        typeRequest.setRarity(Rarity.RARE);
        typeRequest.setId(setResponse.getCollectableSet().getId());

        CreateCollectableTypeResponse typeResponse = collectableService.createCollectableType(typeRequest);

        //create the Collectable
        CreateCollectableRequest collectableRequest = new CreateCollectableRequest();
        collectableRequest.setCollectableTypeId(typeResponse.getCollectableType().getId());

        CreateCollectableResponse collectableResponse = collectableService.createCollectable(collectableRequest);
        Assertions.assertTrue(collectableResponse.isSuccess());
    }

    @Test
    public void getCollectableSetsTest(){
        /**
         *  Create a request object
         * and assign values to it
         * */
        //create set
        CreateCollectableSetRequest setRequest = new CreateCollectableSetRequest();
        setRequest.setName("Christmas");
        setRequest.setDescription("Christmas themed Collectables");

        CreateCollectableSetResponse setResponse = collectableService.createCollectableSet(setRequest);

        GetCollectableSetsResponse response = collectableService.getCollectableSets();
        Assertions.assertTrue(!response.getCollectableSets().isEmpty());
    }

    @Test
    public void getCollectableTypesTest(){
        /**
         *  Create a request object
         * and assign values to it
         * */
        //create set
        CreateCollectableSetRequest setRequest = new CreateCollectableSetRequest();
        setRequest.setName("Christmas");
        setRequest.setDescription("Christmas themed Collectables");

        CreateCollectableSetResponse setResponse = collectableService.createCollectableSet(setRequest);

        //create type
        CreateCollectableTypeRequest typeRequest = new CreateCollectableTypeRequest();
        typeRequest.setName("Santa");
        typeRequest.setImage("jsilgjnskgndfkjg");
        typeRequest.setRarity(Rarity.RARE);
        typeRequest.setId(setResponse.getCollectableSet().getId());

        CreateCollectableTypeResponse typeResponse = collectableService.createCollectableType(typeRequest);

        GetCollectableTypesResponse response = collectableService.getCollectableTypes();
        Assertions.assertTrue(!response.getCollectableTypes().isEmpty());
    }

    @Test
    public void getCollectableTypesTestEmpty() {
        GetCollectableTypesResponse response = collectableService.getCollectableTypes();
        Assertions.assertTrue(response.getCollectableTypes().isEmpty());
    }

    @Test
    public void getCollectableTypesBySetTest(){
        /**
         *  Create a request object
         * and assign values to it
         * */
        //create set
        CreateCollectableSetRequest setRequest = new CreateCollectableSetRequest();
        setRequest.setName("Christmas");
        setRequest.setDescription("Christmas themed Collectables");

        CreateCollectableSetResponse setResponse = collectableService.createCollectableSet(setRequest);

        //create type
        CreateCollectableTypeRequest typeRequest = new CreateCollectableTypeRequest();
        typeRequest.setName("Santa");
        typeRequest.setImage("jsilgjnskgndfkjg");
        typeRequest.setRarity(Rarity.RARE);
        typeRequest.setId(setResponse.getCollectableSet().getId());

        CreateCollectableTypeResponse typeResponse = collectableService.createCollectableType(typeRequest);

        //create the request
        GetCollectableTypesBySetRequest typesBySetRequest = new GetCollectableTypesBySetRequest();
        typesBySetRequest.setSetId(setResponse.getCollectableSet().getId());

        GetCollectableTypesResponse response = collectableService.getCollectableTypesBySet(typesBySetRequest);
        Assertions.assertTrue(!response.getCollectableTypes().isEmpty());
    }

    @Test
    public void getCollectableTypesBySetTestNoTypeWithSet(){
        /**
         *  Create a request object
         * and assign values to it
         * */
        //create set
        CreateCollectableSetRequest setRequest = new CreateCollectableSetRequest();
        setRequest.setName("Christmas");
        setRequest.setDescription("Christmas themed Collectables");

        CreateCollectableSetResponse setResponse = collectableService.createCollectableSet(setRequest);

        //create type
        CreateCollectableTypeRequest typeRequest = new CreateCollectableTypeRequest();
        typeRequest.setName("Santa");
        typeRequest.setImage("jsilgjnskgndfkjg");
        typeRequest.setRarity(Rarity.RARE);
        typeRequest.setId(setResponse.getCollectableSet().getId());

        CreateCollectableTypeResponse typeResponse = collectableService.createCollectableType(typeRequest);

        //create the request
        GetCollectableTypesBySetRequest typesBySetRequest = new GetCollectableTypesBySetRequest();
        typesBySetRequest.setSetId(UUID.randomUUID());

        GetCollectableTypesResponse response = collectableService.getCollectableTypesBySet(typesBySetRequest);
        Assertions.assertTrue(response.getCollectableTypes().isEmpty());
    }

    @Test
    public void getCollectablesTest(){
        /**
        *  Create a request object
        * and assign values to it
        * */
        //create the set
        CreateCollectableSetRequest setRequest = new CreateCollectableSetRequest();
        setRequest.setName("Easter");
        setRequest.setDescription("Themed collectables that can be collected over the Easter weekend");

        CreateCollectableSetResponse setResponse = collectableService.createCollectableSet(setRequest);

        //create the type
        CreateCollectableTypeRequest typeRequest = new CreateCollectableTypeRequest();
        typeRequest.setName("Bunny");
        typeRequest.setImage("kasnvklnvd");
        typeRequest.setRarity(Rarity.RARE);
        typeRequest.setId(setResponse.getCollectableSet().getId());

        CreateCollectableTypeResponse typeResponse = collectableService.createCollectableType(typeRequest);

        //create the Collectable
        CreateCollectableRequest collectableRequest = new CreateCollectableRequest();
        collectableRequest.setCollectableTypeId(typeResponse.getCollectableType().getId());

        CreateCollectableResponse collectableResponse = collectableService.createCollectable(collectableRequest);

        GetCollectablesResponse response = collectableService.getCollectables();
        Assertions.assertTrue(!response.getCollectables().isEmpty());
    }
}
