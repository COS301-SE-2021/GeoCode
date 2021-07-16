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
import tech.geocodeapp.geocode.leaderboard.exception.NullLeaderboardRequestParameterException;
import tech.geocodeapp.geocode.leaderboard.request.GetEventLeaderboardRequest;
import tech.geocodeapp.geocode.leaderboard.response.GetEventLeaderboardResponse;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardServiceImpl;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-05T09:14:58.803Z[GMT]")
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

    public ResponseEntity<GetEventLeaderboardResponse> getEventLeaderboard(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a subset of the Event's Leaderboard details", required=true, schema=@Schema()) @Valid @RequestBody GetEventLeaderboardRequest body) {
        try{
            GetEventLeaderboardResponse response = leaderboardService.getEventLeaderboard(body);

            if(response.isSuccess()){
                return new ResponseEntity<GetEventLeaderboardResponse>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<GetEventLeaderboardResponse>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (NullLeaderboardRequestParameterException e){
            GetEventLeaderboardResponse response = new GetEventLeaderboardResponse(false, e.getMessage(), null);
            return new ResponseEntity<GetEventLeaderboardResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
