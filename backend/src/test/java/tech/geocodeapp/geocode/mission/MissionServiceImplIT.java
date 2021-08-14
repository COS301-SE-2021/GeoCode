package tech.geocodeapp.geocode.mission;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.mission.model.Mission;
import tech.geocodeapp.geocode.mission.model.MissionType;
import tech.geocodeapp.geocode.mission.request.GetMissionByIdRequest;
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

    private final GeoPoint validMissionLocation = new GeoPoint(10.0, 10.0);

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


}
