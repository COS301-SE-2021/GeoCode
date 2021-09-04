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
import tech.geocodeapp.geocode.mission.request.GetProgressRequest;
import tech.geocodeapp.geocode.mission.request.UpdateCompletionRequest;
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

    private final String invalidMissionIdMessage = "Invalid Mission Id";

    private UUID presentCollectableMissionID;
    private Mission presentCollectableMission;

    private Collectable bearCollectable;
    private UUID bearCollectableMissionID;
    private Mission bearCollectableMission;

    private GeoPoint targetLocation;

    private Collectable fishCollectable;
    private UUID fishCollectableMissionID;
    private Mission fishCollectableMission;


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

    Collectable createCollectable(UUID typeID, boolean createMission){
        return createCollectable(typeID, createMission, new GeoPoint(0.0, 0.0));
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

        //create the Present CollectableType (does not have a MissionType)
        var presentProperties = new HashMap<String, String>();
        var presentTypeID = createCollectableType("Present", "img_present", Rarity.COMMON, christmasSetId, presentProperties);

        //create a Present Collectable without a Mission
        presentCollectable = createCollectable(presentTypeID, false);

        //create the Bear CollectableType
        var bearProperties = new HashMap<String, String>();
        bearProperties.put("missionType", String.valueOf(MissionType.GEOCODE));
        var bearTypeID = createCollectableType("Bear", "img_bear", Rarity.RARE, christmasSetId, bearProperties);

        //create a Bear Collectable with a Mission
        targetLocation = new GeoPoint(100.0, 100.0);

        bearCollectable = createCollectable(bearTypeID, true, targetLocation);

        bearCollectableMissionID = bearCollectable.getMissionID();
        bearCollectableMission = getMissionByID(bearCollectableMissionID);

        //create a CollectableType that has a Distance MissionType
        var fishProperties = new HashMap<String, String>();
        fishProperties.put("missionType", String.valueOf(MissionType.DISTANCE));
        var fishTypeID = createCollectableType("Fish", "img_fish", Rarity.RARE, christmasSetId, fishProperties);

        //create a Fish Collectable
        fishCollectable = createCollectable(fishTypeID, true, new GeoPoint(0.0, 0.0));

        fishCollectableMissionID = fishCollectable.getMissionID();
        fishCollectableMission = getMissionByID(fishCollectableMissionID);

    }

    @Test
    void getMissionByIdTestInvalidMissionId(){
        try {
            var request = new GetMissionByIdRequest(invalidMissionId);
            var response = missionService.getMissionById(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidMissionIdMessage, response.getMessage());
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

    @Test
    void getProgressTestInvalidMissionID(){
        try{
            var request = new GetProgressRequest(UUID.randomUUID());
            var response = missionService.getProgress(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidMissionIdMessage, response.getMessage());
            Assertions.assertNull(response.getProgress());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getProgressTestValidMissionID(){
        try{
            var request = new GetProgressRequest(santaCollectableMissionID);
            var response = missionService.getProgress(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Progress returned", response.getMessage());

            Double progress = response.getProgress();
            Assertions.assertNotNull(progress);

            //test that the progress is calculated correctly
            var mission = getMissionByID(santaCollectableMissionID);

            Assertions.assertNotNull(mission);
            Assertions.assertEquals(((double) mission.getCompletion()) / mission.getAmount(), progress);
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void updateCompletionTestSwapType(){
        try{
            var completionBefore = santaCollectableMission.getCompletion();

            //update the completion
            var request = new UpdateCompletionRequest(santaCollectableMission, new GeoPoint(0.0, 0.0));
            var response = missionService.updateCompletion(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Completion updated", response.getMessage());

            //check the completion has been updated to be 1 more swap than before
            Assertions.assertEquals(completionBefore+1, santaCollectableMission.getCompletion());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void updateCompletionTestGeoCodeTypeNotFinalLocation(){
        try{
            //update the completion
            var request = new UpdateCompletionRequest(bearCollectableMission, new GeoPoint(0.0, 0.0));
            var response = missionService.updateCompletion(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Completion updated", response.getMessage());

            //check the completion is still zero since target location was not reached
            Assertions.assertEquals(0, bearCollectableMission.getCompletion());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void updateCompletionTestGeoCodeTypeFinalLocation(){
        try{
            //update the completion
            var request = new UpdateCompletionRequest(bearCollectableMission, targetLocation);
            var response = missionService.updateCompletion(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Completion updated", response.getMessage());

            //check the completion is still zero since target location was not reached
            Assertions.assertEquals(100, bearCollectableMission.getCompletion());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void updateCompletionTestDistanceType(){
        try{
            var completionBefore = fishCollectableMission.getCompletion();
            var locationBefore = fishCollectableMission.getLocation();

            var nextLocation = new GeoPoint(0.0, 0.0);

            //update the completion
            var request = new UpdateCompletionRequest(fishCollectableMission, nextLocation);
            var response = missionService.updateCompletion(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Completion updated", response.getMessage());

            //check the completion has been updated to be the increased distance
            Assertions.assertEquals((int) (completionBefore+locationBefore.distanceTo(fishCollectableMission.getLocation())), fishCollectableMission.getCompletion());
            Assertions.assertEquals(nextLocation, fishCollectableMission.getLocation());
        }catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

}
