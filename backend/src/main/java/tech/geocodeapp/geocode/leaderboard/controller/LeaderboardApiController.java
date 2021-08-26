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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.leaderboard.request.*;
import tech.geocodeapp.geocode.leaderboard.response.*;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-05T10:13:51.670Z[GMT]")
@RestController
public class LeaderboardApiController implements LeaderboardApi {
    private static final Logger log = LoggerFactory.getLogger(LeaderboardApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private LeaderboardServiceImpl leaderboardService;

    @Autowired
    public LeaderboardApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CreateLeaderboardResponse> createLeaderboard(@Parameter(in = ParameterIn.DEFAULT, required=true, schema=@Schema()) @Valid @RequestBody CreateLeaderboardRequest body) {
        CreateLeaderboardResponse response;
        try {
            response = leaderboardService.createLeaderboard(body);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<PointResponse> createPoint(@Parameter(in = ParameterIn.DEFAULT, required=true, schema=@Schema()) @Valid @RequestBody CreatePointRequest body) {
        PointResponse response;

        try {
            response = leaderboardService.createPoint(body);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<DeletePointResponse> deletePoint(@Parameter(in = ParameterIn.DEFAULT, required=true, schema=@Schema()) @Valid @RequestBody DeletePointRequest body) {
        DeletePointResponse response;

        try {
            response = leaderboardService.deletePoint(body);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<GetEventLeaderboardResponse> getEventLeaderboard(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a subset of the Event's Leaderboard details", required=true, schema=@Schema()) @Valid @RequestBody GetEventLeaderboardRequest body) {
        GetEventLeaderboardResponse response;
        try {
            response = leaderboardService.getEventLeaderboard(body);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    public ResponseEntity<GetLeaderboardByIDResponse> getLeaderboardByID(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a Leaderboard", required=true, schema=@Schema()) @Valid @RequestBody GetLeaderboardByIDRequest body) {
        GetLeaderboardByIDResponse response;

        try {
            response = leaderboardService.getLeaderboardByID(body);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    public ResponseEntity<PointResponse> getPointForUser(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a subset of the Event's Leaderboard details", required=true, schema=@Schema()) @Valid @RequestBody GetPointForUserRequest body) {
        PointResponse response;

        try {
            response = leaderboardService.getPointForUser(body);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (response.isSuccess()) {
           return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
           return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    public ResponseEntity<PointResponse> updatePoint(@Parameter(in = ParameterIn.DEFAULT, required=true, schema=@Schema()) @Valid @RequestBody UpdatePointRequest body) {
        PointResponse response;

        try {
            response = leaderboardService.updatePoint(body);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

}
