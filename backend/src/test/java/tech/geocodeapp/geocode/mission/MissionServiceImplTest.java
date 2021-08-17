package tech.geocodeapp.geocode.mission;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.geocodeapp.geocode.collectable.CollectableMockRepository;
import tech.geocodeapp.geocode.collectable.CollectableSetMockRepository;
import tech.geocodeapp.geocode.collectable.CollectableTypeMockRepository;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.collectable.model.CollectableSet;
import tech.geocodeapp.geocode.collectable.model.CollectableType;
import tech.geocodeapp.geocode.collectable.model.Rarity;
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

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith( MockitoExtension.class )
public class MissionServiceImplTest {
    private MissionService missionService;
    private MissionMockRepository missionMockRepo;

    private Mission validMission;
    private final UUID validMissionID = UUID.fromString("7e1d1d27-8370-4ba8-bce4-315803368232");
    private final UUID invalidMissionID = UUID.fromString("48f92adb-e46b-4466-912a-fdc0cf7280c5");

    private final String invalidMissionIDMessage = "Invalid Mission Id";
    private final String collectableHasMissionMessage = "Collectable already has a Mission";

    private final GeoPoint validMissionLocation = new GeoPoint(10.0, 10.0);
    private final GeoPoint fishLocation = new GeoPoint(10.0, 100.0);

    private final UUID fishCollectableTypeID = UUID.fromString("91216b44-b123-486c-8ba7-1c2da7d0feef");
    private final UUID ballCollectableTypeID = UUID.fromString("f85ebdce-a569-4d47-9274-d4b0245c4713");

    private final UUID fishCollectableID = UUID.fromString("cfb23fdb-7b9e-4f67-ad67-b2bab0e7541a");
    private final UUID ballCollectableID = UUID.fromString("49b544b6-307c-4905-bc5e-f61c2afdd56b");
    private final UUID invalidCollectableID = UUID.fromString("71928fe9-70b5-49bc-95dc-ebbf97343201");
    
    private final String invalidCollectableIdMessage = "Invalid Collectable ID";

    MissionServiceImplTest(){

    }

    @BeforeEach
    void setup() {
        CollectableTypeMockRepository collectableTypeMockRepo = new CollectableTypeMockRepository();
        CollectableMockRepository collectableMockRepo = new CollectableMockRepository();
        CollectableSetMockRepository collectableSetMockRepo = new CollectableSetMockRepository();
        CollectableService collectableService = new CollectableServiceImpl(collectableMockRepo, collectableSetMockRepo, collectableTypeMockRepo, missionService);

        missionMockRepo = new MissionMockRepository();
        missionService = new MissionServiceImpl(missionMockRepo, collectableService);

        //create a Mission
        validMission = new Mission();
        validMission.setId(validMissionID);
        validMission.setType(MissionType.SWAP);
        validMission.setLocation(validMissionLocation);

        missionMockRepo.save(validMission);

        //create Collectables
        CollectableSet collectableSet = new CollectableSet("Test Set", "CollectableSet for testing");
        collectableSetMockRepo.save(collectableSet);

        CollectableType fishType = new CollectableType("fish", "fish_image", Rarity.COMMON, collectableSet, new HashMap<String,String>(){{
            put("missionType", "Swap");
        }});

        fishType.setId(fishCollectableTypeID);
        collectableTypeMockRepo.save(fishType);

        //create a Collectable without a Mission
        Collectable fishCollectable = new Collectable(fishType);
        fishCollectable.setId(fishCollectableID);
        fishCollectable.changeLocation(fishLocation);
        collectableMockRepo.save(fishCollectable);

        CollectableType ballType = new CollectableType("ball", "ball_image", Rarity.UNCOMMON, collectableSet, new HashMap<String,String>(){{
            put("missionType", "GeoCode");
        }});

        ballType.setId(ballCollectableTypeID);
        collectableTypeMockRepo.save(ballType);

        //create a Collectable with a Mission
        Collectable ballCollectable = new Collectable();
        ballCollectable.setId(ballCollectableID);

        Mission ballMission = new Mission();
        ballCollectable.setMissionID(ballMission.getId());
        missionMockRepo.save(ballMission);

        collectableMockRepo.save(ballCollectable);
    }

    @Test
    void getMissionByIdTestNullRequest(){
        try {
            GetMissionByIdResponse response = missionService.getMissionById(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetMissionByIdRequest object passed was NULL", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getMissionByIdTestNullMissionParameter(){
        GetMissionByIdRequest request = new GetMissionByIdRequest(null);

        assertThatThrownBy(() -> missionService.getMissionById(request)).isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    void getMissionByIdTestInvalidMissionID(){
        try {
            GetMissionByIdRequest request = new GetMissionByIdRequest(invalidMissionID);
            GetMissionByIdResponse response = missionService.getMissionById(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidMissionIDMessage, response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getMissionByIdTestValidMissionId(){
        try {
            GetMissionByIdRequest request = new GetMissionByIdRequest(validMissionID);
            GetMissionByIdResponse response = missionService.getMissionById(request);
            Mission mission = response.getMission();

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The Mission was found", response.getMessage());

            Assertions.assertEquals(validMissionID, mission.getId());
            Assertions.assertEquals(MissionType.SWAP, mission.getType());
            Assertions.assertEquals(validMissionLocation, mission.getLocation());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createMissionTestNullRequest(){
        try {
            CreateMissionResponse response = missionService.createMission(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The CreateMissionRequest object passed was NULL", response.getMessage());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void createMissionTestNullCollectableParameter(){
        CreateMissionRequest request = new CreateMissionRequest(null);

        assertThatThrownBy(() -> missionService.createMission(request)).isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    void createMissionTestInvalidCollectableID(){
        try {
            CreateMissionRequest request = new CreateMissionRequest(invalidCollectableID);
            CreateMissionResponse response = missionService.createMission(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidCollectableIdMessage, response.getMessage());

            Mission mission = response.getMission();
            Assertions.assertNull(mission);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createMissionTestCollectableWithMission(){
        try {
            //ballCollectable already has a Mission assigned to it
            CreateMissionRequest request = new CreateMissionRequest(ballCollectableID);
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
    void createMissionTestCollectableWithoutMission(){
        try {
            //fishCollectable does not have a Mission assigned to it
            CreateMissionRequest request = new CreateMissionRequest(fishCollectableID);
            CreateMissionResponse response = missionService.createMission(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Mission created", response.getMessage());

            Mission mission = response.getMission();
            Assertions.assertNotNull(mission);

            //test that the Mission was created correctly
            Assertions.assertEquals(MissionType.SWAP, mission.getType());
            Assertions.assertEquals(fishLocation, mission.getLocation());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }


}
