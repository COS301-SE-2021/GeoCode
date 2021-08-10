package tech.geocodeapp.geocode.leaderboard;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;

@SpringBootTest
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class LeaderboardServiceImplIT {
    @Autowired
    private LeaderboardService leaderboardService;


}
