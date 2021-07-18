package tech.geocodeapp.geocode.leaderboard.service;

import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.event.service.EventService;
import tech.geocodeapp.geocode.leaderboard.exception.NullLeaderboardRequestParameterException;
import tech.geocodeapp.geocode.leaderboard.model.EventLeaderboardDetails;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.model.Point;
import tech.geocodeapp.geocode.leaderboard.repository.LeaderboardRepository;
import tech.geocodeapp.geocode.leaderboard.repository.PointRepository;
import tech.geocodeapp.geocode.leaderboard.request.GetEventLeaderboardRequest;
import tech.geocodeapp.geocode.leaderboard.request.GetLeaderboardByIDRequest;
import tech.geocodeapp.geocode.leaderboard.request.GetMyRankRequest;
import tech.geocodeapp.geocode.leaderboard.response.GetEventLeaderboardResponse;
import tech.geocodeapp.geocode.leaderboard.response.GetLeaderboardByIDResponse;
import tech.geocodeapp.geocode.leaderboard.response.GetMyRankResponse;
import tech.geocodeapp.geocode.leaderboard.response.GetPointsByLeaderboardResponse;

import java.util.List;
import java.util.Optional;

import java.util.ArrayList;


/**
 * This class implements the LeaderboardService interface
 */
@Service
public class LeaderboardServiceImpl implements LeaderboardService {
    private final LeaderboardRepository leaderboardRepo;
    private final PointRepository pointRepo;

    @NotNull(message = "Event Service Implementation may not be null.")
    private final EventService eventService;

    public LeaderboardServiceImpl(LeaderboardRepository leaderboardRepo, PointRepository pointRepo, EventService eventService) {
        this.leaderboardRepo = leaderboardRepo;
        this.pointRepo = pointRepo;
        this.eventService = eventService;
    }

    /**
     * A method to retrieve a set number of points from a leaderboard for a provided event starting at a specified rank.
     * @param request - Contains the event to get a leaderboard form, the position to start for points and the number of points to get.
     * @return A list of the details of the requested points
     * @throws NullLeaderboardRequestParameterException - an exception for when no leaderboard exists for the given event
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
                   message = "Starting is lower than the minimum value allowed";
               }else if(request.getCount()<1) {
                   message = "Count is lower than the minimum value allowed";
               }else if(event.isEmpty()){
                   message = "No event with the provided eventID exists";
               }else{
                   Optional<Leaderboard> leaderboard = leaderboardRepo.getLeaderboardByEvent(event.get());
                   if(leaderboard.isEmpty()){
                       message = "No leaderboard exists for the provided event";
                   }else{
                       if(pointRepo.countByLeaderboard(leaderboard.get())<request.getStarting()) {
                           message = "Starting is greater than the number of points in the leaderboard";
                       }else{
                            List<Point> points = pointRepo.findPointsByLeaderboardBetween(leaderboard.get().getId(), request.getStarting()-1, request.getCount());
                            for(int i = 0; i<points.size(); i++) {
                                EventLeaderboardDetails details = new EventLeaderboardDetails(points.get(i).getUser().getUsername(), points.get(i).getAmount(), request.getStarting()+i);
                                leaderboardDetails.add(details);
                           }
                            success = true;
                            message = "Successfully found points for event";
                       }
                   }
               }
               return new GetEventLeaderboardResponse(success, message, leaderboardDetails);
           }
       }else{
           throw new NullLeaderboardRequestParameterException();
       }
    }

    /**
     * Gets the Leaderboard identified by the given UUID
     * @param request The GetLeaderboardByIDRequest object
     * @return A GetLeaderboardByIDResponse object containing the Leaderboard object
     * @throws NullLeaderboardRequestParameterException
     */
    public GetLeaderboardByIDResponse getLeaderboardByID(GetLeaderboardByIDRequest request) throws NullLeaderboardRequestParameterException{
        /* Find all of the Leader
         */

        if(request == null){
            return new GetLeaderboardByIDResponse(null);
        }

        if(request.getLeaderboardID() == null){
            throw new NullLeaderboardRequestParameterException();
        }

        Optional<Leaderboard> optionalLeaderboard = leaderboardRepo.findById(request.getLeaderboardID());
        return new GetLeaderboardByIDResponse(optionalLeaderboard.get());
    }

    /**
     * Gets all of the Point objects that are for the given Leaderboard
     * @param request The GetPointsByLeaderboardRequest object
     * @return A GetPointsByLeaderboardResponse object
     * @throws NullLeaderboardRequestParameterException
     */
    public GetPointsByLeaderboardResponse getPointsByLeaderboard(GetMyRankRequest request) throws NullLeaderboardRequestParameterException{
        if(request == null){
            return new GetPointsByLeaderboardResponse(null);
        }

        if(request.getLeaderboard() == null){
            throw new NullLeaderboardRequestParameterException();
        }

        List<Point> points = pointRepo.findAllByLeaderboard(request.getLeaderboard());
        return new GetPointsByLeaderboardResponse(points);
    }

    /**
     * Gets the rank for the given point amount on the given leaderboard
     * @param request The GetMyRankRequest object
     * @return A GetMyRankResponse object
     * @throws NullLeaderboardRequestParameterException
     */
    public GetMyRankResponse getMyRank(GetMyRankRequest request) throws NullLeaderboardRequestParameterException{
        if(request == null){
            return new GetMyRankResponse(null);
        }

        if(request.getLeaderboard() == null || request.getAmount() == null){
            throw new NullLeaderboardRequestParameterException();
        }

        int rank = pointRepo.getMyRank(request.getLeaderboard().getId(), request.getAmount());
        return new GetMyRankResponse(rank);
    }
}
