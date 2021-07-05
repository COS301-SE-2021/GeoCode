package tech.geocodeapp.geocode.leaderboard.service;

import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.event.service.EventService;
import tech.geocodeapp.geocode.leaderboard.exception.NullLeaderboardRequestParameterException;
import tech.geocodeapp.geocode.leaderboard.model.EventLeaderboardDetails;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.repository.LeaderboardRepository;
import tech.geocodeapp.geocode.leaderboard.request.GetEventLeaderboardRequest;
import tech.geocodeapp.geocode.leaderboard.response.GetEventLeaderboardResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
       if(request!=null) {
           if(request.getEventID()==null || request.getStarting()==null || request.getCount()==null) {
               throw new NullLeaderboardRequestParameterException();
           }else{
               boolean success = false;
               String message = "";
               List<EventLeaderboardDetails> leaderboardDetails = new ArrayList<>();

               //find the event if it exists
               Optional<Event> event=Optional.empty(); //ToDo change to find by provided eventID

               if(request.getStarting()<1) {
                   message = "starting is lower than the minimum value allowed";
               }else if(request.getCount()<1) {
                   message = "count is lower than the minimum value allowed";
               }else if(event.isEmpty()){
                   message = "No event with the provided eventID exists";
               }else{
                   Optional<Leaderboard> leaderboard = leaderboardRepo.getLeaderboardByEvent(event.get());
                   if(leaderboard.isEmpty()){
                       message = "No leaderboard exists for the provided event";
                   }else{
                       //ToDo finish method
                   }
               }
               return new GetEventLeaderboardResponse(success, message, leaderboardDetails);
           }
       }else{
           throw new NullLeaderboardRequestParameterException();
       }
    }
}
