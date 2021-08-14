package tech.geocodeapp.geocode.mission;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.geocodeapp.geocode.mission.service.MissionService;

import java.util.UUID;

@SpringBootTest
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class MissionServiceImplIT {
    @Autowired
    private MissionService missionService;

    private final UUID validMissionID = UUID.fromString("4507f78a-2c0c-4073-9af0-7f50ffe2fa0f");


}
