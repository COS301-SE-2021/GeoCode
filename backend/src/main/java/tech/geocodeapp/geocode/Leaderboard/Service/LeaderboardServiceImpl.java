package tech.geocodeapp.geocode.Leaderboard.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tech.geocodeapp.geocode.Leaderboard.Repository.LeaderboardRepository;


/**
 * This class implements the LeaderboardService interface
 */
@Service
public class LeaderboardServiceImpl implements LeaderboardService {

    @Autowired
    private LeaderboardRepository leaderboardRepo;

    public LeaderboardServiceImpl() {

    }


}
