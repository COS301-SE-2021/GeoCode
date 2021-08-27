package tech.geocodeapp.geocode.leaderboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.leaderboard.request.*;
import tech.geocodeapp.geocode.leaderboard.response.*;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardServiceImpl;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LeaderboardApiController implements LeaderboardApi {

    private static final Logger log = LoggerFactory.getLogger(LeaderboardApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private LeaderboardServiceImpl leaderboardService;

    @org.springframework.beans.factory.annotation.Autowired
    public LeaderboardApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CreateLeaderboardResponse> createLeaderboard(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateLeaderboardRequest body) {
        CreateLeaderboardResponse response = new CreateLeaderboardResponse();
        try {
            response = leaderboardService.createLeaderboard(body);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return new ResponseEntity<CreateLeaderboardResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (response.isSuccess()) {
            return new ResponseEntity<CreateLeaderboardResponse>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<CreateLeaderboardResponse>(response, HttpStatus.OK);
        }
    }

    public ResponseEntity<PointResponse> createPoint(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreatePointRequest body) {
        PointResponse response = new PointResponse();
        try {
            response = leaderboardService.createPoint(body);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return new ResponseEntity<PointResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (response.isSuccess()) {
            return new ResponseEntity<PointResponse>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<PointResponse>(response, HttpStatus.OK);
        }
    }

    public ResponseEntity<DeletePointResponse> deletePoint(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody DeletePointRequest body) {
        DeletePointResponse response = new DeletePointResponse();
        try {
            response = leaderboardService.deletePoint(body);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return new ResponseEntity<DeletePointResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (response.isSuccess()) {
            return new ResponseEntity<DeletePointResponse>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<DeletePointResponse>(response, HttpStatus.OK);
        }
    }

    public ResponseEntity<GetEventLeaderboardResponse> getEventLeaderboard(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a subset of the Event's Leaderboard details", required=true, schema=@Schema()) @Valid @RequestBody GetEventLeaderboardRequest body) {
        GetEventLeaderboardResponse response = new GetEventLeaderboardResponse();
        try {
            response = leaderboardService.getEventLeaderboard(body);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return new ResponseEntity<GetEventLeaderboardResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (response.isSuccess()) {
            return new ResponseEntity<GetEventLeaderboardResponse>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<GetEventLeaderboardResponse>(response, HttpStatus.OK);
        }
    }

    public ResponseEntity<GetLeaderboardByIDResponse> getLeaderboardByID(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a Leaderboard", required=true, schema=@Schema()) @Valid @RequestBody GetLeaderboardByIDRequest body) {
        GetLeaderboardByIDResponse response = new GetLeaderboardByIDResponse();
        try {
            response = leaderboardService.getLeaderboardByID(body);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return new ResponseEntity<GetLeaderboardByIDResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (response.isSuccess()) {
            return new ResponseEntity<GetLeaderboardByIDResponse>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<GetLeaderboardByIDResponse>(response, HttpStatus.OK);
        }
    }

    public ResponseEntity<PointResponse> getPointForUser(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a subset of the Event's Leaderboard details", required=true, schema=@Schema()) @Valid @RequestBody GetPointForUserRequest body) {
        PointResponse response = new PointResponse();
        try {
            response = leaderboardService.getPointForUser(body);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return new ResponseEntity<PointResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (response.isSuccess()) {
           return new ResponseEntity<PointResponse>(response, HttpStatus.OK);
        }else {
           return new ResponseEntity<PointResponse>(response, HttpStatus.OK);
        }
    }

    public ResponseEntity<PointResponse> updatePoint(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody UpdatePointRequest body) {
        PointResponse response = new PointResponse();
        try {
            response = leaderboardService.updatePoint(body);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return new ResponseEntity<PointResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (response.isSuccess()) {
            return new ResponseEntity<PointResponse>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<PointResponse>(response, HttpStatus.OK);
        }
    }

}
