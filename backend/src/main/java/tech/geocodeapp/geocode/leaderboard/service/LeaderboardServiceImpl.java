package tech.geocodeapp.geocode.leaderboard.service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import tech.geocodeapp.geocode.event.service.EventService;
import tech.geocodeapp.geocode.general.CheckNullRequestParameters;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.leaderboard.model.EventLeaderboardDetails;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.model.Point;
import tech.geocodeapp.geocode.leaderboard.repository.LeaderboardRepository;
import tech.geocodeapp.geocode.leaderboard.repository.PointRepository;
import tech.geocodeapp.geocode.leaderboard.request.*;
import tech.geocodeapp.geocode.leaderboard.response.*;
import tech.geocodeapp.geocode.user.model.User;
import tech.geocodeapp.geocode.user.request.GetUserByIdRequest;
import tech.geocodeapp.geocode.user.response.GetUserByIdResponse;
import tech.geocodeapp.geocode.user.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

/**
 * This class implements the LeaderboardService interface
 */
@Service("LeaderboardService")
public class LeaderboardServiceImpl implements LeaderboardService {
    private final LeaderboardRepository leaderboardRepo;
    private final PointRepository pointRepo;

    private final CheckNullRequestParameters checkNullRequestParameters = new CheckNullRequestParameters();

    @NotNull(message = "Event Service Implementation may not be null.")
    private final EventService eventService;

    private final UserService userService;

    public LeaderboardServiceImpl(LeaderboardRepository leaderboardRepo, PointRepository pointRepo, EventService eventService, @Lazy UserService userService) {
        this.leaderboardRepo = leaderboardRepo;
        this.pointRepo = pointRepo;
        this.eventService = eventService;
        this.userService = userService;
    }

    /**
     * Creates a Leaderboard
     * @param request - Contains the name of the Leaderboard to be created
     * @return The created Leaderboard
     * @throws NullRequestParameterException - an exception for when a request parameter is NULL
     */
    public CreateLeaderboardResponse createLeaderboard(CreateLeaderboardRequest request) throws NullRequestParameterException{
        if(request == null){
            return new CreateLeaderboardResponse(false, "The CreateLeaderboardRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        /* create the new Leaderboard */
        Leaderboard leaderboard = new Leaderboard(request.getName());
        leaderboardRepo.save(leaderboard);

        return new CreateLeaderboardResponse(true, "The Leaderboard was successfully created", leaderboard);
    }

    /**
     * A method to retrieve a set number of points from a leaderboard for a provided leaderboardId starting at a specified rank.
     * @param request - Contains the leaderboardId to use, the position to start for points and the number of points to get.
     * @return A list of the details of the requested points
     * @throws NullRequestParameterException - an exception for when a request parameter is NULL
     */
    @Transactional
    public GetEventLeaderboardResponse getEventLeaderboard(GetEventLeaderboardRequest request) throws NullRequestParameterException{
        if (request == null) {
            return new GetEventLeaderboardResponse(false, "The GetEventLeaderboardRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        boolean success = false;
        String message = "";
        List<EventLeaderboardDetails> leaderboardDetails = new ArrayList<>();

        if(request.getStarting()<1) {
            message = "Starting is lower than the minimum value allowed";
        }else if(request.getCount()<1) {
            message = "Count is lower than the minimum value allowed";
        }else{
            Optional<Leaderboard> leaderboard = leaderboardRepo.findById(request.getLeaderboardID());
            if(leaderboard.isEmpty()){
                message = "No leaderboard exists for the provided leaderboardId";
            }else{
                if(pointRepo.countByLeaderboard(leaderboard.get())<request.getStarting()) {
                    message = "Starting is greater than the number of points in the leaderboard";
                }else{
                     List<Point> points = pointRepo.findPointsByLeaderboardBetween(leaderboard.get().getId(), request.getStarting()-1, request.getCount());
                     for(int i = 0; i<points.size(); i++) {
                         User user = points.get(i).getUser();
                         EventLeaderboardDetails details = new EventLeaderboardDetails(user.getId(), user.getUsername(), points.get(i).getAmount(), request.getStarting()+i);
                         leaderboardDetails.add(details);
                    }
                     success = true;
                     message = "Successfully found points for event";
                }
            }
        }

        return new GetEventLeaderboardResponse(success, message, leaderboardDetails);
    }

    /**
     * Gets the Point object for the given User ID and Leaderboard ID
     * @param request Request object containing the user and leaderboard IDs
     * @return The wanted Point object
     */
    public PointResponse getPointForUser(GetPointForUserRequest request) throws NullRequestParameterException{
        if(request == null){
            return new PointResponse(false, "The GetPointForUserRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        /* check if the User has got a Point for the given Leaderboard */
        Optional<Point> optionalPoint = pointRepo.getPointForUser(request.getUserID(), request.getLeaderboardID());

        if(optionalPoint.isEmpty()){
            return new PointResponse(false, "The User does not have any points yet for the given Leaderboard", null);
        }else{
            return new PointResponse(true, "Point object returned successfully", optionalPoint.get());
        }
    }

    /**
     * Gets the Leaderboard identified by the given UUID
     * @param request The GetLeaderboardByIDRequest object
     * @return A GetLeaderboardByIDResponse object containing the Leaderboard object
     * @throws NullRequestParameterException - an exception for when a request parameter is NULL
     */
    @Transactional
    public GetLeaderboardByIDResponse getLeaderboardByID(GetLeaderboardByIDRequest request) throws NullRequestParameterException{
        if(request == null){
            return new GetLeaderboardByIDResponse(false, "The GetLeaderboardByIDRequest object passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        Optional<Leaderboard> optionalLeaderboard = leaderboardRepo.findById(request.getLeaderboardID());

        return optionalLeaderboard.map(leaderboard -> new GetLeaderboardByIDResponse(true, "Leaderboard found", leaderboard)).orElseGet(
                () -> new GetLeaderboardByIDResponse(false, "Leaderboard not found", null));
    }

    /**
     * Gets all the Point objects that are for the given Leaderboard
     * @param request The GetPointsByLeaderboardRequest object
     * @return A GetPointsByLeaderboardResponse object
     * @throws NullRequestParameterException - an exception for when a request parameter is NULL
     */
    @Transactional
    public GetPointsByLeaderboardResponse getPointsByLeaderboard(GetPointsByLeaderboardRequest request) throws NullRequestParameterException{
        if(request == null){
            return new GetPointsByLeaderboardResponse(false, "The GetPointsByLeaderboardRequest passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        /* check if leaderboard is invalid */
        java.util.UUID leaderboardID = request.getLeaderboard().getId();
        Optional<Leaderboard> optionalLeaderboard = leaderboardRepo.findById(leaderboardID);

        if(optionalLeaderboard.isEmpty()){
            return new GetPointsByLeaderboardResponse(false, "Invalid leaderboard ID", null);
        }

        List<Point> points = pointRepo.findAllByLeaderboardID(leaderboardID);
        return new GetPointsByLeaderboardResponse(true, "Leaderboard points returned", points);
    }

    /**
     * Gets the rank for the given point amount on the given leaderboard
     * @param request The GetMyRankRequest object
     * @return A GetMyRankResponse object
     * @throws NullRequestParameterException - an exception for when a request parameter is NULL
     */
    @Transactional
    public GetMyRankResponse getMyRank(GetMyRankRequest request) throws NullRequestParameterException{
        if(request == null){
            return new GetMyRankResponse(false, "The GetMyRankRequest passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        /* check if leaderboard is invalid */
        Optional<Leaderboard> optionalLeaderboard = leaderboardRepo.findById(request.getLeaderboard().getId());

        if(optionalLeaderboard.isEmpty()){
            return new GetMyRankResponse(false, "Invalid leaderboard ID", null);
        }

        int rank = pointRepo.getMyRank(request.getLeaderboard().getId(), request.getAmount());
        return new GetMyRankResponse(true, "Point rank returned", rank);
    }

    /**
     * A method to create a new Point for a user in a leaderboard
     * @param request Contains the amount, leaderboardId and userId to use for creating the Point
     * @return A responses informing of success or failure and containing the created Point.
     * @throws NullRequestParameterException an exception for when a null value is provided for a parameter of the request
     */
    @Transactional
    public PointResponse createPoint(CreatePointRequest request) throws NullRequestParameterException{
        if(request == null){
            return new PointResponse(false, "The CreatePointRequest passed was NULL", null);
        }

        checkNullRequestParameters.checkRequestParameters(request);

        // check if leaderboard is invalid
        Optional<Leaderboard> leaderboard = leaderboardRepo.findById(request.getLeaderboardId());

        if(leaderboard.isEmpty()){
            return new PointResponse(false, "Invalid leaderboard Id provided",null);
        }

        //check if user is invalid
        GetUserByIdRequest userRequest = new GetUserByIdRequest(request.getUserId());
        User foundUser = null;

        try {
            GetUserByIdResponse userResponse = userService.getUserById(userRequest);
            if(!userResponse.isSuccess()){
                return new PointResponse(false, "Invalid user Id provided",null);
            }else{
               foundUser = userResponse.getUser();
            }
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }

        //check if the amount is invalid
        if(request.getAmount() < 0){
            return new PointResponse(false, "The amount for a Point must be at least zero", null);
        }

        Point point= new Point(request.getAmount(), foundUser, leaderboard.get());
        pointRepo.save(point);
        return new PointResponse(true, "The Point was successfully created.", point);
    }

    /**
     * deletes a point based on a provided id
     * @param request Contains the id of the point to be deleted
     * @return A response object informing of success or failure and the reason for failure in the event of it
     * @throws NullRequestParameterException an exception for when the request parameter is null
     */
    @Transactional
    public DeletePointResponse deletePoint(DeletePointRequest request) throws NullRequestParameterException {
        if(request == null){
            return new DeletePointResponse(false, "The DeletePointRequest passed was NULL");
        }

        checkNullRequestParameters.checkRequestParameters(request);

        //check if the point to delete exists
        Optional<Point> point = pointRepo.findById(request.getPointId());
        if(point.isEmpty()){
            return new DeletePointResponse(false,"No Point with the given Id exists");
        }

        pointRepo.delete(point.get());
        return new DeletePointResponse(true, "Successfully deleted the provided point");
    }

    /**
     * updates a point with the given id if it exists
     * @param request contains the id of the point to update and the new values for the fields that should be updated
     * @return A response object informing of success or failure and the reason as well as the updated Point object
     * @throws NullRequestParameterException an exception for when the provided parameters are all null or pointId is null.
     */
    @Transactional
    public PointResponse updatePoint(UpdatePointRequest request) throws NullRequestParameterException {
        if(request == null){
            return new PointResponse(false,"The UpdatePointRequest passed was NULL", null);
        }

        if(request.getPointId() == null){
            throw new NullRequestParameterException();
        }

        if(request.getAmount() == null && request.getLeaderboardId() == null && request.getUserId() == null){
            return new PointResponse(false, "Please provide a value for at least one optional value to update a Point", null);
        }

        //check that the point to update exists
        Optional<Point> point = pointRepo.findById(request.getPointId());
        if(point.isEmpty()){
            return new PointResponse(false, "No point with the provided Id exists", null);
        }

        //check that if a leaderboard id is provided that it exists
        if(request.getLeaderboardId() != null) {
            Optional<Leaderboard> leaderboard = leaderboardRepo.findById(request.getLeaderboardId());
            if(leaderboard.isEmpty()){
                return new PointResponse(false, "Invalid LeaderboardId to update to was provided", null);
            }else{
                point.get().setLeaderBoard(leaderboard.get());
            }
        }

        //check that if a user id is provided that it exists
        if(request.getUserId() != null) {
            GetUserByIdRequest userByIdRequest = new GetUserByIdRequest(request.getUserId());
            try {
                GetUserByIdResponse user = userService.getUserById(userByIdRequest);
                if(!user.isSuccess()){
                    return new PointResponse(false, "Invalid UserId to update to was provided", null);
                }else{
                    point.get().setUser(user.getUser());
                }
            } catch (NullRequestParameterException e) {
                e.printStackTrace();
            }
        }

        //update amount if it was provided
        if(request.getAmount() != null){
            //check if the amount is invalid
            if(request.getAmount() <= 0){
                return new PointResponse(false, "The amount for a Point must be positive", null);
            }

            point.get().setAmount(request.getAmount());
        }

        pointRepo.save(point.get());
        return new PointResponse(true, "Updated point successfully", point.get());
    }

    @Transactional
    public Response savePoint(Point point) throws NullRequestParameterException {
        if(point == null) {
            return new Response(false, "Point provided is null");
        }
        checkNullRequestParameters.checkRequestParameters(point);

        pointRepo.save(point);
        return new Response(true, "Saved point successfully");
    }
}
