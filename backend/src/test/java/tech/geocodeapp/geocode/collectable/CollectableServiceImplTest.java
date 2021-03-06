package tech.geocodeapp.geocode.collectable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.geocodeapp.geocode.collectable.model.Rarity;
import tech.geocodeapp.geocode.collectable.request.CreateCollectableRequest;
import tech.geocodeapp.geocode.collectable.request.CreateCollectableSetRequest;
import tech.geocodeapp.geocode.collectable.request.CreateCollectableTypeRequest;
import tech.geocodeapp.geocode.collectable.request.GetCollectableTypesBySetRequest;
import tech.geocodeapp.geocode.collectable.response.*;
import tech.geocodeapp.geocode.collectable.service.CollectableService;
import tech.geocodeapp.geocode.collectable.service.CollectableServiceImpl;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.mission.service.MissionService;

import java.util.UUID;

@ExtendWith( MockitoExtension.class )
public class CollectableServiceImplTest {

    private CollectableService collectableService;
    private MissionService missionService;
    //ToDo intialize mission service

    CollectableServiceImplTest (){

    }

    @BeforeEach
    void setup() {
        collectableService = new CollectableServiceImpl(new CollectableMockRepository(), new CollectableSetMockRepository(), new CollectableTypeMockRepository(), missionService);
        collectableService.deleteCollectableSets();
        collectableService.deleteCollectables();
        collectableService.deleteCollectableTypes();
    }

    @Test
    public void createCollectableSetTest() throws NullRequestParameterException {
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
    public void createCollectableSetNullRequestTest() throws NullRequestParameterException {
        CreateCollectableSetResponse response = collectableService.createCollectableSet(null);

        Assertions.assertEquals("The CreateCollectableSetRequest object passed was NULL", response.getMessage());
    }

    @Test
    public void createCollectableTypeTestInvalid() throws NullRequestParameterException {
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
    public void createCollectableTypeTestValid() throws NullRequestParameterException {
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
    public void createCollectableTypeNullRequestTest() throws NullRequestParameterException {
        CreateCollectableTypeResponse response = collectableService.createCollectableType(null);
        Assertions.assertEquals("The CreateCollectableTypeRequest object passed was NULL", response.getMessage());
    }

    @Test
    public void createCollectableTestInvalid() throws NullRequestParameterException {
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
    public void createCollectableNullRequestTest() throws NullRequestParameterException {
        CreateCollectableResponse response = collectableService.createCollectable(null);
        Assertions.assertEquals("The CreateCollectableSetRequest object passed was NULL", response.getMessage());
    }

    @Test
    public void createCollectableTestValid() throws NullRequestParameterException {
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
    public void getCollectableSetsTest() throws NullRequestParameterException {
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
    public void getCollectableTypesTest() throws NullRequestParameterException {
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
    public void getCollectableTypesBySetTest() throws NullRequestParameterException {
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
    public void getCollectableTypesBySetTestNoTypeWithSet() throws NullRequestParameterException {
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
    public void getCollectablesTest() throws NullRequestParameterException {
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
