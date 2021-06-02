package tech.geocodeapp.geocode.Collectable;

import io.swagger.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableRepository;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableSetRepository;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableTypeRepository;
import tech.geocodeapp.geocode.Collectable.Response.CreateCollectableResponse;
import tech.geocodeapp.geocode.Collectable.Response.CreateCollectableSetResponse;
import tech.geocodeapp.geocode.Collectable.Response.CreateCollectableTypeResponse;
import tech.geocodeapp.geocode.Collectable.Service.CollectableService;
import tech.geocodeapp.geocode.Collectable.Service.CollectableServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith( MockitoExtension.class )
public class CollectableServiceImplTest {

    private CollectableService collectableService;

    CollectableServiceImplTest (){

    }

    @BeforeEach
    void setup() {
        collectableService = new CollectableServiceImpl(new CollectableMockRepository(), new CollectableSetMockRepository(), new CollectableTypeRepository() {
            @Override
            public List<CollectableType> getCollectableTypesByRarity(Rarity rarity) {
                return null;
            }

            @Override
            public List<CollectableType> getCollectableTypesBySet(CollectableSet set) {
                return null;
            }

            @Override
            public long deleteCollectableTypesBySet(CollectableSet set) {
                return 0;
            }

            @Override
            public List<CollectableType> findAll() {
                return null;
            }

            @Override
            public List<CollectableType> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<CollectableType> findAllById(Iterable<UUID> iterable) {
                return null;
            }

            @Override
            public <S extends CollectableType> List<S> saveAll(Iterable<S> iterable) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends CollectableType> S saveAndFlush(S s) {
                return null;
            }

            @Override
            public void deleteInBatch(Iterable<CollectableType> iterable) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public CollectableType getOne(UUID uuid) {
                return null;
            }

            @Override
            public <S extends CollectableType> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends CollectableType> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<CollectableType> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends CollectableType> S save(S s) {
                return null;
            }

            @Override
            public Optional<CollectableType> findById(UUID uuid) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(UUID uuid) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(UUID uuid) {

            }

            @Override
            public void delete(CollectableType collectableType) {

            }

            @Override
            public void deleteAll(Iterable<? extends CollectableType> iterable) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends CollectableType> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends CollectableType> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends CollectableType> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends CollectableType> boolean exists(Example<S> example) {
                return false;
            }
        });
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
