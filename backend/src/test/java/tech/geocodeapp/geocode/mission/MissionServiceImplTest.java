package tech.geocodeapp.geocode.mission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.geocodeapp.geocode.collectable.CollectableMockRepository;
import tech.geocodeapp.geocode.collectable.CollectableSetMockRepository;
import tech.geocodeapp.geocode.collectable.CollectableTypeMockRepository;
import tech.geocodeapp.geocode.collectable.service.CollectableService;
import tech.geocodeapp.geocode.collectable.service.CollectableServiceImpl;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.mission.model.Mission;
import tech.geocodeapp.geocode.mission.model.MissionType;
import tech.geocodeapp.geocode.mission.service.MissionService;
import tech.geocodeapp.geocode.mission.service.MissionServiceImpl;

import java.util.UUID;

@ExtendWith( MockitoExtension.class )
public class MissionServiceImplTest {
    private MissionService missionService;
    private MissionMockRepository missionMockRepo;

    private Mission validMission;
    private final UUID validMissionID = UUID.fromString("7e1d1d27-8370-4ba8-bce4-315803368232");

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
        validMission.setLocation(new GeoPoint(10.0, 10.0));

        missionMockRepo.save(validMission);
    }


}
