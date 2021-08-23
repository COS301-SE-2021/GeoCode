package tech.geocodeapp.geocode.user.controller;

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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.user.request.*;
import tech.geocodeapp.geocode.user.response.*;
import tech.geocodeapp.geocode.user.service.UserServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-09T21:02:56.988Z[GMT]")
@RestController
public class UserApiController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserServiceImpl userService;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<GetUserByIdResponse> getUserById(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user", required=true, schema=@Schema()) @Valid @RequestBody GetUserByIdRequest body) {
        try{
            GetUserByIdResponse response = userService.getUserById(body);

            if(response.isSuccess()){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (NullRequestParameterException e){
            GetUserByIdResponse response = new GetUserByIdResponse(false, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<GetCurrentCollectableResponse> getCurrentCollectable(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user's current Collectable", required=true, schema=@Schema()) @Valid @RequestBody GetCurrentCollectableRequest body) {
        try{
            GetCurrentCollectableResponse response = userService.getCurrentCollectable(body);

            if(response.isSuccess()){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (NullRequestParameterException e){
            GetCurrentCollectableResponse response = new GetCurrentCollectableResponse(false, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<GetUserTrackableResponse> getUserTrackable(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user's trackable", required=true, schema=@Schema()) @RequestBody GetUserTrackableRequest body) {
        try{
            GetUserTrackableResponse response = userService.getUserTrackable(body);

            if(response.isSuccess()){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (NullRequestParameterException e){
            GetUserTrackableResponse response = new GetUserTrackableResponse(false, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<UpdateLocationResponse> updateLocation(@Parameter(in = ParameterIn.DEFAULT, description = "Request to update the location of the user's trackable", required=true, schema=@Schema()) @Valid @RequestBody UpdateLocationRequest body) {
        try{
            UpdateLocationResponse response = userService.updateLocation(body);

            if(response.isSuccess()){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch(NullRequestParameterException e){
            UpdateLocationResponse response = new UpdateLocationResponse(false, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<GetFoundCollectableTypesResponse> getFoundCollectableTypes(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the IDs of the user's found Collectable Types", required=true, schema=@Schema()) @Valid @RequestBody GetFoundCollectableTypesRequest body) {
        try{
            GetFoundCollectableTypesResponse response = userService.getFoundCollectableTypes(body);

            if(response.isSuccess()){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch(NullRequestParameterException e){
            GetFoundCollectableTypesResponse response = new GetFoundCollectableTypesResponse(false, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<GetFoundGeoCodesResponse> getFoundGeoCodes(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user's found GeoCodes", required=true, schema=@Schema()) @Valid @RequestBody GetFoundGeoCodesRequest body) {
        try{
            GetFoundGeoCodesResponse response = userService.getFoundGeoCodes(body);

            if(response.isSuccess()){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch(NullRequestParameterException e){
            GetFoundGeoCodesResponse response = new GetFoundGeoCodesResponse(false, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<GetOwnedGeoCodesResponse> getOwnedGeoCodes(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user's owned GeoCodes", required=true, schema=@Schema()) @Valid @RequestBody GetOwnedGeoCodesRequest body) {
        try{
            GetOwnedGeoCodesResponse response = userService.getOwnedGeoCodes(body);

            if(response.isSuccess()){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch(NullRequestParameterException e){
            GetOwnedGeoCodesResponse response = new GetOwnedGeoCodesResponse(false, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<GetMyLeaderboardsResponse> getMyLeaderboards(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the name, points and ranking for all of the Leaderboards that a User is on", required=true, schema=@Schema()) @Valid @RequestBody GetMyLeaderboardsRequest body) {
        try{
            GetMyLeaderboardsResponse response = userService.getMyLeaderboards(body);

            if(response.isSuccess()){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (NullRequestParameterException e){
            GetMyLeaderboardsResponse response = new GetMyLeaderboardsResponse(false, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<GetMyMissionsResponse> getMyMissions(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the User's Missions", required=true, schema=@Schema()) @Valid @RequestBody GetMyMissionsRequest body) {
        try{
            GetMyMissionsResponse response = userService.getMyMissions(body);

            if(response.isSuccess()){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (NullRequestParameterException e){
            GetMyMissionsResponse response = new GetMyMissionsResponse(false, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
