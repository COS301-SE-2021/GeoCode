package tech.geocodeapp.geocode.mission;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Transient;
import org.springframework.transaction.annotation.Transactional;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.mission.model.Mission;
import tech.geocodeapp.geocode.mission.model.MissionType;
import tech.geocodeapp.geocode.mission.request.CreateMissionRequest;
import tech.geocodeapp.geocode.mission.request.GetMissionByIdRequest;
import tech.geocodeapp.geocode.mission.response.CreateMissionResponse;
import tech.geocodeapp.geocode.mission.response.GetMissionByIdResponse;
import tech.geocodeapp.geocode.mission.service.MissionService;

import java.util.UUID;

@SpringBootTest
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class MissionServiceImplIT {
    @Autowired
    private MissionService missionService;

    private final UUID validMissionID = UUID.fromString("4507f78a-2c0c-4073-9af0-7f50ffe2fa0f");
    private final UUID invalidMissionId = UUID.fromString("48f92adb-e46b-4466-912a-fdc0cf7280c5");
    private final String invalidMissionIdMessage = "Invalid Mission Id";
    private final String collectableHasMissionMessage = "Collectable already has a Mission";

    private final GeoPoint validMissionLocation = new GeoPoint(10.0, 10.0);

    private final UUID invalidCollectableID = UUID.fromString("71928fe9-70b5-49bc-95dc-ebbf97343201");
    private final String invalidCollectableIdMessage = "Invalid Collectable ID";

    private final UUID collectableWithMissionID = UUID.fromString("74bb3b32-a95e-4aea-b5c7-1e27b56cc240");
    private final UUID collectableWithoutMissionID = UUID.fromString("40f799d9-b2d4-4f35-84af-da226c7c089f");
    private final GeoPoint collectableWithMissionLocation = new GeoPoint(50.0, 50.0);

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
    @Transactional
    void createMissionTestCollectableWithMission(){
        try {
            //this Collectable already has a Mission assigned to it
            CreateMissionRequest request = new CreateMissionRequest(collectableWithMissionID);
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
            //this Collectable does not have a Mission assigned to it
            CreateMissionRequest request = new CreateMissionRequest(collectableWithoutMissionID);
            CreateMissionResponse response = missionService.createMission(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Mission created", response.getMessage());

            Mission mission = response.getMission();
            Assertions.assertNotNull(mission);

            //test that the Mission was created correctly
            Assertions.assertEquals(MissionType.SWAP, mission.getType());
            Assertions.assertEquals(collectableWithMissionLocation, mission.getLocation());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }
}
