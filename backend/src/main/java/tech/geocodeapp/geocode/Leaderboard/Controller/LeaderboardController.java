package tech.geocodeapp.geocode.Leaderboard.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


import tech.geocodeapp.geocode.Leaderboard.Service.LeaderboardServiceImpl;

/**
 * This class implements the functionality of the LeaderboardAPI interface.
 */
@CrossOrigin("*")
@RestController
public class LeaderboardController {

    @Autowired
    private LeaderboardServiceImpl LeaderboardService;


}
