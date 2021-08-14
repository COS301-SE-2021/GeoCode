package tech.geocodeapp.geocode.mission;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.geocodeapp.geocode.collectable.CollectableMockRepository;
import tech.geocodeapp.geocode.collectable.CollectableSetMockRepository;
import tech.geocodeapp.geocode.collectable.CollectableTypeMockRepository;
import tech.geocodeapp.geocode.collectable.service.CollectableService;
import tech.geocodeapp.geocode.collectable.service.CollectableServiceImpl;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.mission.model.Mission;
import tech.geocodeapp.geocode.mission.model.MissionType;
import tech.geocodeapp.geocode.mission.request.GetMissionByIdRequest;
import tech.geocodeapp.geocode.mission.response.GetMissionByIdResponse;
import tech.geocodeapp.geocode.mission.service.MissionService;
import tech.geocodeapp.geocode.mission.service.MissionServiceImpl;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith( MockitoExtension.class )
public class MissionServiceImplTest {
    private MissionService missionService;
    private MissionMockRepository missionMockRepo;

    private Mission validMission;
    private final UUID validMissionID = UUID.fromString("7e1d1d27-8370-4ba8-bce4-315803368232");
    private final UUID invalidMissionId = UUID.fromString("48f92adb-e46b-4466-912a-fdc0cf7280c5");
    private final String invalidMissionIdMessage = "Invalid Mission Id";

    private final GeoPoint validMissionLocation = new GeoPoint(10.0, 10.0);

    MissionServiceImplTest(){

    }

    @BeforeEach
    void setup() {
        CollectableTypeMockRepository collectableTypeMockRepo = new CollectableTypeMockRepository();
        CollectableMockRepository collectableMockRepo = new CollectableMockRepository();
        CollectableSetMockRepository collectableSetMockRepo = new CollectableSetMockRepository();
        CollectableService collectableService = new CollectableServiceImpl(collectableMockRepo, collectableSetMockRepo, collectableTypeMockRepo);

        missionMockRepo = new MissionMockRepository();
        missionService = new MissionServiceImpl(missionMockRepo, collectableService);

        //create a Mission
        validMission = new Mission();
        validMission.setId(validMissionID);
        validMission.setType(MissionType.SWAP);
        validMission.setLocation(validMissionLocation);

        missionMockRepo.save(validMission);
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
