package tech.geocodeapp.geocode.leaderboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.geocodeapp.geocode.leaderboard.repository.LeaderboardRepository;


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
