package tech.geocodeapp.geocode.mission.controller;

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
import tech.geocodeapp.geocode.mission.request.CreateMissionRequest;
import tech.geocodeapp.geocode.mission.request.GetMissionByIdRequest;
import tech.geocodeapp.geocode.mission.request.GetProgressRequest;
import tech.geocodeapp.geocode.mission.response.CreateMissionResponse;
import tech.geocodeapp.geocode.mission.response.GetMissionByIdResponse;
import tech.geocodeapp.geocode.mission.response.GetProgressResponse;
import tech.geocodeapp.geocode.mission.service.MissionServiceImpl;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-12T09:55:42.563Z[GMT]")
@RestController
public class MissionApiController implements MissionApi {

    private static final Logger log = LoggerFactory.getLogger(MissionApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private MissionServiceImpl missionService;

    @org.springframework.beans.factory.annotation.Autowired
    public MissionApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<GetMissionByIdResponse> getMissionById(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody GetMissionByIdRequest body) {
        try{
            GetMissionByIdResponse response = missionService.getMissionById(body);

            if(response.isSuccess()){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (NullRequestParameterException e){
            GetMissionByIdResponse response = new GetMissionByIdResponse(false, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<GetProgressResponse> getProgress(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody GetProgressRequest body) {
        try{
            GetProgressResponse response = missionService.getProgress(body);

            if(response.isSuccess()){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (NullRequestParameterException e){
            GetProgressResponse response = new GetProgressResponse(false, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<CreateMissionResponse> setMission(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateMissionRequest body) {
        try{
            CreateMissionResponse response = missionService.createMission(body);

            if(response.isSuccess()){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (NullRequestParameterException e){
            CreateMissionResponse response = new CreateMissionResponse(false, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
