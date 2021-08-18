package tech.geocodeapp.geocode.mission;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;
import tech.geocodeapp.geocode.collectable.CollectableMockRepository;
import tech.geocodeapp.geocode.collectable.CollectableSetMockRepository;
import tech.geocodeapp.geocode.collectable.CollectableTypeMockRepository;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.collectable.model.Rarity;
import tech.geocodeapp.geocode.collectable.request.CreateCollectableRequest;
import tech.geocodeapp.geocode.collectable.request.CreateCollectableSetRequest;
import tech.geocodeapp.geocode.collectable.request.CreateCollectableTypeRequest;
import tech.geocodeapp.geocode.collectable.request.GetCollectableByIDRequest;
import tech.geocodeapp.geocode.collectable.response.CreateCollectableResponse;
import tech.geocodeapp.geocode.collectable.response.CreateCollectableSetResponse;
import tech.geocodeapp.geocode.collectable.response.CreateCollectableTypeResponse;
import tech.geocodeapp.geocode.collectable.service.CollectableService;
import tech.geocodeapp.geocode.collectable.service.CollectableServiceImpl;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.mission.model.Mission;
import tech.geocodeapp.geocode.mission.model.MissionType;
import tech.geocodeapp.geocode.mission.request.CreateMissionRequest;
import tech.geocodeapp.geocode.mission.request.GetMissionByIdRequest;
import tech.geocodeapp.geocode.mission.response.CreateMissionResponse;
import tech.geocodeapp.geocode.mission.response.GetMissionByIdResponse;
import tech.geocodeapp.geocode.mission.service.MissionService;
import tech.geocodeapp.geocode.mission.service.MissionServiceImpl;

import java.util.HashMap;
import java.util.UUID;

@ExtendWith( MockitoExtension.class )
public class MissionServiceImplTest {
    @Mock(name="missionService")
    private MissionService missionService;

    @Mock(name="collectableService")
    private CollectableService collectableService;

    private MissionMockRepository missionMockRepo;

    private final UUID invalidMissionId = UUID.fromString("48f92adb-e46b-4466-912a-fdc0cf7280c5");
    private final String invalidMissionIdMessage = "Invalid Mission Id";
    private final String collectableHasMissionMessage = "Collectable already has a Mission";

    private UUID christmasSetId;

    private UUID santaTypeID;
    private Collectable santaCollectable;
    private UUID santaCollectableID;

    private UUID santaCollectableMissionID;
    private Mission santaCollectableMission;

    private UUID presentTypeID;
    private Collectable presentCollectable;
    private UUID presentCollectableID;
    private UUID presentCollectableMissionID;

    UUID createCollectableSet(String name, String description){
        CreateCollectableSetRequest createCollectableSetRequest = new CreateCollectableSetRequest(name, description);

        try {
            CreateCollectableSetResponse createCollectableSetResponse = collectableService.createCollectableSet(createCollectableSetRequest);
            return createCollectableSetResponse.getCollectableSet().getId();
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    UUID createCollectableType(String name, String image, Rarity rarity, UUID setId, HashMap<String, String> properties) {
        CreateCollectableTypeRequest createCollectableTypeRequest = new CreateCollectableTypeRequest(name, image, rarity, setId, properties);

        try {
            CreateCollectableTypeResponse createCollectableTypeResponse = collectableService.createCollectableType(createCollectableTypeRequest);
            return createCollectableTypeResponse.getCollectableType().getId();
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    Collectable createCollectable(UUID typeID, boolean createMission, GeoPoint location){
        CreateCollectableRequest createCollectableRequest = new CreateCollectableRequest();
        createCollectableRequest.setCollectableTypeId(typeID);
        createCollectableRequest.setCreateMission(createMission);
        createCollectableRequest.setLocation(location);

        CreateCollectableResponse createCollectableResponse = null;

        try {
            createCollectableResponse = collectableService.createCollectable(createCollectableRequest);
            Assertions.assertTrue(createCollectableResponse.isSuccess());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }

        UUID id = createCollectableResponse.getCollectable().getId();

        try {
            return collectableService.getCollectableByID(new GetCollectableByIDRequest(id)).getCollectable();
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Mission getMissionByID(UUID collectableMissionID) {
        try {
            return missionService.getMissionById(new GetMissionByIdRequest(collectableMissionID)).getMission();
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    @BeforeEach
    void setup() {
        //initialize the services and mock repos
        missionMockRepo = new MissionMockRepository();
        missionService = new MissionServiceImpl(missionMockRepo);

        collectableService = new CollectableServiceImpl(new CollectableMockRepository(), new CollectableSetMockRepository(),
                new CollectableTypeMockRepository(), missionService);

        //create the Christmas set
        christmasSetId = createCollectableSet("Christmas Set", "Christmas Collectable Set for 2021");

        //create the Santa CollectableType
        HashMap<String, String> santaProperties = new HashMap<>();
        santaProperties.put("missionType", String.valueOf(MissionType.SWAP));
        santaTypeID = createCollectableType("Santa", "img_santa", Rarity.RARE, christmasSetId, santaProperties);

        //create a Santa Collectable with a Mission
        santaCollectable = createCollectable(santaTypeID, true, new GeoPoint(0.0, 0.0));
        santaCollectableID = santaCollectable.getId();

        //get the missionID for the Santa Collectable's Mission
        santaCollectableMissionID = santaCollectable.getMissionID();
        santaCollectableMission = getMissionByID(santaCollectableMissionID);

        //create the Present CollectableType
        HashMap<String, String> presentProperties = new HashMap<>();
        presentProperties.put("missionType", String.valueOf(MissionType.GEOCODE));
        presentTypeID = createCollectableType("Present", "img_present", Rarity.COMMON, christmasSetId, presentProperties);

        //create a Present Collectable without a Mission
        presentCollectable = createCollectable(presentTypeID, false, new GeoPoint(0.0, 0.0));
        presentCollectableID = presentCollectable.getId();
    }

    @Test
    void getMissionByIdTestInvalidMissionId(){
        try {
            GetMissionByIdRequest request = new GetMissionByIdRequest(invalidMissionId);
            GetMissionByIdResponse response = missionService.getMissionById(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidMissionIdMessage, response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getMissionByIdTestValidMissionId(){
        try {
            GetMissionByIdRequest request = new GetMissionByIdRequest(santaCollectableMissionID);
            GetMissionByIdResponse response = missionService.getMissionById(request);
            Mission mission = response.getMission();

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The Mission was found", response.getMessage());
            Assertions.assertEquals(santaCollectableMission, mission);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    void createMissionTestCollectableWithMission(){
        try {
            //this Collectable already has a Mission assigned to it (random location)
            CreateMissionRequest request = new CreateMissionRequest(santaCollectable, new GeoPoint(0.0, 0.0));
            CreateMissionResponse response = missionService.createMission(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(collectableHasMissionMessage, response.getMessage());

            Mission mission = response.getMission();
            Assertions.assertNull(mission);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    void createMissionTestCollectableWithoutMission(){
        try {
            //this Collectable does not have a Mission assigned to it (random location)
            CreateMissionRequest request = new CreateMissionRequest(presentCollectable, new GeoPoint(0.0, 0.0));
            CreateMissionResponse response = missionService.createMission(request);
            Mission mission = response.getMission();

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Mission created", response.getMessage());

        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

}
