package tech.geocodeapp.geocode.mission;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.collectable.model.Rarity;
import tech.geocodeapp.geocode.collectable.request.CreateCollectableRequest;
import tech.geocodeapp.geocode.collectable.request.CreateCollectableSetRequest;
import tech.geocodeapp.geocode.collectable.request.CreateCollectableTypeRequest;
import tech.geocodeapp.geocode.collectable.request.GetCollectableByIDRequest;
import tech.geocodeapp.geocode.collectable.response.CreateCollectableResponse;
import tech.geocodeapp.geocode.collectable.service.CollectableService;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.mission.model.Mission;
import tech.geocodeapp.geocode.mission.model.MissionType;
import tech.geocodeapp.geocode.mission.request.CreateMissionRequest;
import tech.geocodeapp.geocode.mission.request.GetMissionByIdRequest;
import tech.geocodeapp.geocode.mission.service.MissionService;

import java.util.HashMap;
import java.util.UUID;

@SpringBootTest
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class MissionServiceImplIT {
    @Autowired
    private MissionService missionService;

    @Autowired
    private CollectableService collectableService;

    private final UUID invalidMissionId = UUID.fromString("48f92adb-e46b-4466-912a-fdc0cf7280c5");

    private Collectable santaCollectable;

    private UUID santaCollectableMissionID;
    private Mission santaCollectableMission;

    private Collectable presentCollectable;

    UUID createCollectableSet(String name, String description){
        var createCollectableSetRequest = new CreateCollectableSetRequest(name, description);

        try {
            var createCollectableSetResponse = collectableService.createCollectableSet(createCollectableSetRequest);
            return createCollectableSetResponse.getCollectableSet().getId();
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    UUID createCollectableType(String name, String image, Rarity rarity, UUID setId, HashMap<String, String> properties) {
        var createCollectableTypeRequest = new CreateCollectableTypeRequest(name, image, rarity, setId, properties);

        try {
            var createCollectableTypeResponse = collectableService.createCollectableType(createCollectableTypeRequest);
            return createCollectableTypeResponse.getCollectableType().getId();
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    Collectable createCollectable(UUID typeID, boolean createMission, GeoPoint location){
        var createCollectableRequest = new CreateCollectableRequest();
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

        var id = createCollectableResponse.getCollectable().getId();

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
        //create the Christmas set
        var christmasSetId = createCollectableSet("Christmas Set", "Christmas Collectable Set for 2021");

        //create the Santa CollectableType
        var santaProperties = new HashMap<String, String>();
        santaProperties.put("missionType", String.valueOf(MissionType.SWAP));
        var santaTypeID = createCollectableType("Santa", "img_santa", Rarity.RARE, christmasSetId, santaProperties);

        //create a Santa Collectable with a Mission
        santaCollectable = createCollectable(santaTypeID, true, new GeoPoint(0.0, 0.0));

        //get the missionID for the Santa Collectable's Mission
        santaCollectableMissionID = santaCollectable.getMissionID();
        santaCollectableMission = getMissionByID(santaCollectableMissionID);

        //create the Present CollectableType
        var presentProperties = new HashMap<String, String>();
        presentProperties.put("missionType", String.valueOf(MissionType.GEOCODE));
        var presentTypeID = createCollectableType("Present", "img_present", Rarity.COMMON, christmasSetId, presentProperties);

        //create a Present Collectable without a Mission
        presentCollectable = createCollectable(presentTypeID, false, new GeoPoint(0.0, 0.0));
    }

    @Test
    void getMissionByIdTestInvalidMissionId(){
        try {
            var request = new GetMissionByIdRequest(invalidMissionId);
            var response = missionService.getMissionById(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Invalid Mission Id", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getMissionByIdTestValidMissionId(){
        try {
            var request = new GetMissionByIdRequest(santaCollectableMissionID);
            var response = missionService.getMissionById(request);
            var mission = response.getMission();

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
            var request = new CreateMissionRequest(santaCollectable, new GeoPoint(0.0, 0.0));
            var response = missionService.createMission(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Collectable already has a Mission", response.getMessage());

            var mission = response.getMission();
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
            var request = new CreateMissionRequest(presentCollectable, new GeoPoint(0.0, 0.0));
            var response = missionService.createMission(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Mission created", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }
}
