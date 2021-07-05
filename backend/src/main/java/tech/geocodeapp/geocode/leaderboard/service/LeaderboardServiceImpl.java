package tech.geocodeapp.geocode.leaderboard.service;

import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import tech.geocodeapp.geocode.event.service.EventService;
import tech.geocodeapp.geocode.leaderboard.exception.NullLeaderboardRequestParameterException;
import tech.geocodeapp.geocode.leaderboard.repository.LeaderboardRepository;
import tech.geocodeapp.geocode.leaderboard.request.GetEventLeaderboardRequest;
import tech.geocodeapp.geocode.leaderboard.response.GetEventLeaderboardResponse;


/**
 * This class implements the LeaderboardService interface
 */
@Service
public class LeaderboardServiceImpl implements LeaderboardService {
    private final LeaderboardRepository leaderboardRepo;

    @NotNull(message = "Event Service Implementation may not be null.")
    private final EventService eventService;

    public LeaderboardServiceImpl(LeaderboardRepository leaderboardRepo, EventService eventService) {
        this.leaderboardRepo = leaderboardRepo;
        this.eventService = eventService;
    }

    /**
     *
     * @param request
     * @return
     * @throws NullLeaderboardRequestParameterException
     */
    public GetEventLeaderboardResponse getEventLeaderboard(GetEventLeaderboardRequest request) throws NullLeaderboardRequestParameterException{
        return null;
    }
}
