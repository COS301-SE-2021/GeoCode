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
import java.io.IOException;

import tech.geocodeapp.geocode.user.exception.NullUserRequestParameterException;
import tech.geocodeapp.geocode.user.model.User;
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

    public ResponseEntity<GetCurrentCollectableResponse> getCurrentCollectable(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user's current Collectable", required=true, schema=@Schema()) @Valid @RequestBody GetCurrentCollectableRequest body) {
        try{
            GetCurrentCollectableResponse response = userService.getCurrentCollectable(body);

            if(response.isSuccess()){
                return new ResponseEntity<GetCurrentCollectableResponse>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<GetCurrentCollectableResponse>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (NullUserRequestParameterException e){
            GetCurrentCollectableResponse response = new GetCurrentCollectableResponse(false, e.getMessage(), null);
            return new ResponseEntity<GetCurrentCollectableResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<GetUserTrackableResponse> getUserTrackable(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user's trackable", required=true, schema=@Schema()) @RequestBody GetUserTrackableRequest body) {
        try{
            GetUserTrackableResponse response = userService.getUserTrackable(body);

            if(response.isSuccess()){
                return new ResponseEntity<GetUserTrackableResponse>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<GetUserTrackableResponse>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (NullUserRequestParameterException e){
            GetUserTrackableResponse response = new GetUserTrackableResponse(false, e.getMessage(), null);
            return new ResponseEntity<GetUserTrackableResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<UpdateLocationResponse> updateLocation(@Parameter(in = ParameterIn.DEFAULT, description = "Request to update the location of the user's trackable", required=true, schema=@Schema()) @Valid @RequestBody UpdateLocationRequest body) {
        try{
            UpdateLocationResponse response = userService.updateLocation(body);

            if(response.isSuccess()){
                return new ResponseEntity<UpdateLocationResponse>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<UpdateLocationResponse>(response, HttpStatus.BAD_REQUEST);
            }
        }catch(NullUserRequestParameterException e){
            UpdateLocationResponse response = new UpdateLocationResponse(false, e.getMessage(), null);
            return new ResponseEntity<UpdateLocationResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<GetFoundCollectablesResponse> getFoundCollectables(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user's found Collectables", required=true, schema=@Schema()) @Valid @RequestBody GetFoundCollectablesRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetFoundCollectablesResponse>(objectMapper.readValue("{\n  \"collectables\" : [ {\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"type\" : {\n      \"image\" : \"image\",\n      \"set\" : {\n        \"name\" : \"name\",\n        \"description\" : \"description\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n      },\n      \"name\" : \"name\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"rarity\" : \"COMMON\"\n    }\n  }, {\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"type\" : {\n      \"image\" : \"image\",\n      \"set\" : {\n        \"name\" : \"name\",\n        \"description\" : \"description\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n      },\n      \"name\" : \"name\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"rarity\" : \"COMMON\"\n    }\n  } ]\n}", GetFoundCollectablesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetFoundCollectablesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetFoundCollectablesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetFoundGeoCodesResponse> getFoundGeoCodes(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user's found GeoCodes", required=true, schema=@Schema()) @Valid @RequestBody GetFoundGeoCodesRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetFoundGeoCodesResponse>(objectMapper.readValue("{\n  \"geocodes\" : [ {\n    \"difficulty\" : \"EASY\",\n    \"collectables\" : {\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"type\" : {\n        \"image\" : \"image\",\n        \"set\" : {\n          \"name\" : \"name\",\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        },\n        \"name\" : \"name\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"rarity\" : \"COMMON\"\n      }\n    },\n    \"qrCode\" : \"qrCode\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"description\",\n    \"trackables\" : \"trackables\",\n    \"location\" : \"location\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  }, {\n    \"difficulty\" : \"EASY\",\n    \"collectables\" : {\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"type\" : {\n        \"image\" : \"image\",\n        \"set\" : {\n          \"name\" : \"name\",\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        },\n        \"name\" : \"name\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"rarity\" : \"COMMON\"\n      }\n    },\n    \"qrCode\" : \"qrCode\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"description\",\n    \"trackables\" : \"trackables\",\n    \"location\" : \"location\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  } ]\n}", GetFoundGeoCodesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetFoundGeoCodesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetFoundGeoCodesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetOwnedGeoCodesResponse> getOwnedGeoCodes(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user's owned GeoCodes", required=true, schema=@Schema()) @Valid @RequestBody GetOwnedGeoCodesRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetOwnedGeoCodesResponse>(objectMapper.readValue("{\n  \"geocodes\" : [ {\n    \"difficulty\" : \"EASY\",\n    \"collectables\" : {\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"type\" : {\n        \"image\" : \"image\",\n        \"set\" : {\n          \"name\" : \"name\",\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        },\n        \"name\" : \"name\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"rarity\" : \"COMMON\"\n      }\n    },\n    \"qrCode\" : \"qrCode\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"description\",\n    \"trackables\" : \"trackables\",\n    \"location\" : \"location\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  }, {\n    \"difficulty\" : \"EASY\",\n    \"collectables\" : {\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"type\" : {\n        \"image\" : \"image\",\n        \"set\" : {\n          \"name\" : \"name\",\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        },\n        \"name\" : \"name\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"rarity\" : \"COMMON\"\n      }\n    },\n    \"qrCode\" : \"qrCode\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"description\",\n    \"trackables\" : \"trackables\",\n    \"location\" : \"location\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  } ]\n}", GetOwnedGeoCodesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetOwnedGeoCodesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetOwnedGeoCodesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetOwnedGeoCodesResponse> getOwnedGeocodes(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get geocodes belonging to user", required=true, schema=@Schema()) @Valid @RequestBody GetOwnedGeoCodesRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetOwnedGeoCodesResponse>(objectMapper.readValue("{\n  \"geocodes\" : [ {\n    \"difficulty\" : \"EASY\",\n    \"collectables\" : {\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"type\" : {\n        \"image\" : \"image\",\n        \"set\" : {\n          \"name\" : \"name\",\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        },\n        \"name\" : \"name\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"rarity\" : \"COMMON\"\n      }\n    },\n    \"qrCode\" : \"qrCode\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"description\",\n    \"trackables\" : \"trackables\",\n    \"location\" : \"location\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  }, {\n    \"difficulty\" : \"EASY\",\n    \"collectables\" : {\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"type\" : {\n        \"image\" : \"image\",\n        \"set\" : {\n          \"name\" : \"name\",\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        },\n        \"name\" : \"name\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"rarity\" : \"COMMON\"\n      }\n    },\n    \"qrCode\" : \"qrCode\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"description\",\n    \"trackables\" : \"trackables\",\n    \"location\" : \"location\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  } ]\n}", GetOwnedGeoCodesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetOwnedGeoCodesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetOwnedGeoCodesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetUsersResponse> getUsers(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get all users in the system", required=true, schema=@Schema()) @Valid @RequestBody GetUsersRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetUsersResponse>(objectMapper.readValue("{\n  \"users\" : [ {\n    \"currentCollectable\" : {\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"type\" : {\n        \"image\" : \"image\",\n        \"set\" : {\n          \"name\" : \"name\",\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        },\n        \"name\" : \"name\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"rarity\" : \"COMMON\"\n      }\n    },\n    \"foundGeocodes\" : [ {\n      \"difficulty\" : \"EASY\",\n      \"collectables\" : {\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"type\" : {\n          \"image\" : \"image\",\n          \"set\" : {\n            \"name\" : \"name\",\n            \"description\" : \"description\",\n            \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n          },\n          \"name\" : \"name\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n          \"rarity\" : \"COMMON\"\n        }\n      },\n      \"qrCode\" : \"qrCode\",\n      \"hints\" : [ \"hints\", \"hints\" ],\n      \"available\" : true,\n      \"description\" : \"description\",\n      \"trackables\" : \"trackables\",\n      \"location\" : \"location\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n    }, {\n      \"difficulty\" : \"EASY\",\n      \"collectables\" : {\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"type\" : {\n          \"image\" : \"image\",\n          \"set\" : {\n            \"name\" : \"name\",\n            \"description\" : \"description\",\n            \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n          },\n          \"name\" : \"name\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n          \"rarity\" : \"COMMON\"\n        }\n      },\n      \"qrCode\" : \"qrCode\",\n      \"hints\" : [ \"hints\", \"hints\" ],\n      \"available\" : true,\n      \"description\" : \"description\",\n      \"trackables\" : \"trackables\",\n      \"location\" : \"location\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n    } ],\n    \"foundCollectables\" : [ null, null ],\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"ownedGeocodes\" : [ null, null ],\n    \"username\" : \"username\",\n    \"points\" : [ {\n      \"amount\" : 0,\n      \"leaderBoardId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"userId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n    }, {\n      \"amount\" : 0,\n      \"leaderBoardId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"userId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n    } ]\n  }, {\n    \"currentCollectable\" : {\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"type\" : {\n        \"image\" : \"image\",\n        \"set\" : {\n          \"name\" : \"name\",\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        },\n        \"name\" : \"name\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"rarity\" : \"COMMON\"\n      }\n    },\n    \"foundGeocodes\" : [ {\n      \"difficulty\" : \"EASY\",\n      \"collectables\" : {\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"type\" : {\n          \"image\" : \"image\",\n          \"set\" : {\n            \"name\" : \"name\",\n            \"description\" : \"description\",\n            \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n          },\n          \"name\" : \"name\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n          \"rarity\" : \"COMMON\"\n        }\n      },\n      \"qrCode\" : \"qrCode\",\n      \"hints\" : [ \"hints\", \"hints\" ],\n      \"available\" : true,\n      \"description\" : \"description\",\n      \"trackables\" : \"trackables\",\n      \"location\" : \"location\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n    }, {\n      \"difficulty\" : \"EASY\",\n      \"collectables\" : {\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"type\" : {\n          \"image\" : \"image\",\n          \"set\" : {\n            \"name\" : \"name\",\n            \"description\" : \"description\",\n            \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n          },\n          \"name\" : \"name\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n          \"rarity\" : \"COMMON\"\n        }\n      },\n      \"qrCode\" : \"qrCode\",\n      \"hints\" : [ \"hints\", \"hints\" ],\n      \"available\" : true,\n      \"description\" : \"description\",\n      \"trackables\" : \"trackables\",\n      \"location\" : \"location\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n    } ],\n    \"foundCollectables\" : [ null, null ],\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"ownedGeocodes\" : [ null, null ],\n    \"username\" : \"username\",\n    \"points\" : [ {\n      \"amount\" : 0,\n      \"leaderBoardId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"userId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n    }, {\n      \"amount\" : 0,\n      \"leaderBoardId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"userId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n    } ]\n  } ]\n}", GetUsersResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetUsersResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetUsersResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetMyLeaderboardsResponse> getMyLeaderboards(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the name, points and ranking for all of the Leaderboards that a User is on", required=true, schema=@Schema()) @Valid @RequestBody GetMyLeaderboardsRequest body) {
        try{
            GetMyLeaderboardsResponse response = userService.

            if(response.isSuccess()){
                return new ResponseEntity<GetCurrentCollectableResponse>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<GetCurrentCollectableResponse>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (NullUserRequestParameterException e){
            GetCurrentCollectableResponse response = new GetCurrentCollectableResponse(false, e.getMessage(), null);
            return new ResponseEntity<GetCurrentCollectableResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
