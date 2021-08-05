package tech.geocodeapp.geocode.leaderboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tech.geocodeapp.geocode.leaderboard.request.*;
import tech.geocodeapp.geocode.leaderboard.response.*;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-05T10:13:51.670Z[GMT]")
@RestController
public class LeaderboardApiController implements LeaderboardApi {

    private static final Logger log = LoggerFactory.getLogger(LeaderboardApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public LeaderboardApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CreateLeaderboardResponse> createLeaderboard(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateLeaderboardRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CreateLeaderboardResponse>(objectMapper.readValue("{\n  \"leaderboard\" : {\n    \"name\" : \"name\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"event\" : {\n      \"beginDate\" : \"2017-01-01T00:00:00.000+00:00\",\n      \"endDate\" : \"2017-01-01T00:00:00.000+00:00\",\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"location\" : {\n        \"latitude\" : 25.7545,\n        \"longitude\" : 28.2314\n      },\n      \"id\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n      \"leaderboards\" : [ null, null ],\n      \"levels\" : [ {\n        \"onLevel\" : {\n          \"key\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n        },\n        \"target\" : {\n          \"difficulty\" : \"EASY\",\n          \"collectables\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n          \"qrCode\" : \"qrCode\",\n          \"hints\" : [ \"hints\", \"hints\" ],\n          \"available\" : true,\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        }\n      }, {\n        \"onLevel\" : {\n          \"key\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n        },\n        \"target\" : {\n          \"difficulty\" : \"EASY\",\n          \"collectables\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n          \"qrCode\" : \"qrCode\",\n          \"hints\" : [ \"hints\", \"hints\" ],\n          \"available\" : true,\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        }\n      } ]\n    }\n  },\n  \"success\" : true,\n  \"message\" : \"The Leaderboard was successfully created\"\n}", CreateLeaderboardResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CreateLeaderboardResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CreateLeaderboardResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PointResponse> createPoint(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreatePointRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PointResponse>(objectMapper.readValue("{\n  \"point\" : {\n    \"leaderBoard\" : {\n      \"name\" : \"name\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"event\" : {\n        \"beginDate\" : \"2017-01-01T00:00:00.000+00:00\",\n        \"endDate\" : \"2017-01-01T00:00:00.000+00:00\",\n        \"name\" : \"name\",\n        \"description\" : \"description\",\n        \"location\" : {\n          \"latitude\" : 25.7545,\n          \"longitude\" : 28.2314\n        },\n        \"id\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n        \"leaderboards\" : [ null, null ],\n        \"levels\" : [ {\n          \"onLevel\" : {\n            \"key\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n          },\n          \"target\" : {\n            \"difficulty\" : \"EASY\",\n            \"collectables\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n            \"qrCode\" : \"qrCode\",\n            \"hints\" : [ \"hints\", \"hints\" ],\n            \"available\" : true,\n            \"description\" : \"description\",\n            \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n          }\n        }, {\n          \"onLevel\" : {\n            \"key\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n          },\n          \"target\" : {\n            \"difficulty\" : \"EASY\",\n            \"collectables\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n            \"qrCode\" : \"qrCode\",\n            \"hints\" : [ \"hints\", \"hints\" ],\n            \"available\" : true,\n            \"description\" : \"description\",\n            \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n          }\n        } ]\n      }\n    },\n    \"amount\" : 0,\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  }\n}", PointResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PointResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PointResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<DeletePointResponse> deletePoint(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody DeletePointRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<DeletePointResponse>(objectMapper.readValue("{\n  \"success\" : true,\n  \"message\" : \"Successfully deleted point\"\n}", DeletePointResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<DeletePointResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<DeletePointResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetEventLeaderboardResponse> getEventLeaderboard(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a subset of the Event's Leaderboard details", required=true, schema=@Schema()) @Valid @RequestBody GetEventLeaderboardRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetEventLeaderboardResponse>(objectMapper.readValue("{\n  \"leaderboard\" : [ {\n    \"rank\" : 5,\n    \"username\" : \"john_smith\",\n    \"points\" : 15\n  }, {\n    \"rank\" : 5,\n    \"username\" : \"john_smith\",\n    \"points\" : 15\n  } ],\n  \"success\" : true,\n  \"message\" : \"The details for the Event's Leaderboard were successfully returned\"\n}", GetEventLeaderboardResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetEventLeaderboardResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetEventLeaderboardResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetLeaderboardByIDResponse> getLeaderboardByID(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a Leaderboard", required=true, schema=@Schema()) @Valid @RequestBody GetLeaderboardByIDRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetLeaderboardByIDResponse>(objectMapper.readValue("{\n  \"leaderboard\" : {\n    \"name\" : \"name\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"event\" : {\n      \"beginDate\" : \"2017-01-01T00:00:00.000+00:00\",\n      \"endDate\" : \"2017-01-01T00:00:00.000+00:00\",\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"location\" : {\n        \"latitude\" : 25.7545,\n        \"longitude\" : 28.2314\n      },\n      \"id\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n      \"leaderboards\" : [ null, null ],\n      \"levels\" : [ {\n        \"onLevel\" : {\n          \"key\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n        },\n        \"target\" : {\n          \"difficulty\" : \"EASY\",\n          \"collectables\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n          \"qrCode\" : \"qrCode\",\n          \"hints\" : [ \"hints\", \"hints\" ],\n          \"available\" : true,\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        }\n      }, {\n        \"onLevel\" : {\n          \"key\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n        },\n        \"target\" : {\n          \"difficulty\" : \"EASY\",\n          \"collectables\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n          \"qrCode\" : \"qrCode\",\n          \"hints\" : [ \"hints\", \"hints\" ],\n          \"available\" : true,\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        }\n      } ]\n    }\n  }\n}", GetLeaderboardByIDResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetLeaderboardByIDResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetLeaderboardByIDResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PointResponse> getPointForUser(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a subset of the Event's Leaderboard details", required=true, schema=@Schema()) @Valid @RequestBody GetPointForUserRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PointResponse>(objectMapper.readValue("{\n  \"point\" : {\n    \"leaderBoard\" : {\n      \"name\" : \"name\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"event\" : {\n        \"beginDate\" : \"2017-01-01T00:00:00.000+00:00\",\n        \"endDate\" : \"2017-01-01T00:00:00.000+00:00\",\n        \"name\" : \"name\",\n        \"description\" : \"description\",\n        \"location\" : {\n          \"latitude\" : 25.7545,\n          \"longitude\" : 28.2314\n        },\n        \"id\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n        \"leaderboards\" : [ null, null ],\n        \"levels\" : [ {\n          \"onLevel\" : {\n            \"key\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n          },\n          \"target\" : {\n            \"difficulty\" : \"EASY\",\n            \"collectables\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n            \"qrCode\" : \"qrCode\",\n            \"hints\" : [ \"hints\", \"hints\" ],\n            \"available\" : true,\n            \"description\" : \"description\",\n            \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n          }\n        }, {\n          \"onLevel\" : {\n            \"key\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n          },\n          \"target\" : {\n            \"difficulty\" : \"EASY\",\n            \"collectables\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n            \"qrCode\" : \"qrCode\",\n            \"hints\" : [ \"hints\", \"hints\" ],\n            \"available\" : true,\n            \"description\" : \"description\",\n            \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n          }\n        } ]\n      }\n    },\n    \"amount\" : 0,\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  }\n}", PointResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PointResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PointResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PointResponse> updatePoint(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody UpdatePointRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PointResponse>(objectMapper.readValue("{\n  \"point\" : {\n    \"leaderBoard\" : {\n      \"name\" : \"name\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"event\" : {\n        \"beginDate\" : \"2017-01-01T00:00:00.000+00:00\",\n        \"endDate\" : \"2017-01-01T00:00:00.000+00:00\",\n        \"name\" : \"name\",\n        \"description\" : \"description\",\n        \"location\" : {\n          \"latitude\" : 25.7545,\n          \"longitude\" : 28.2314\n        },\n        \"id\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n        \"leaderboards\" : [ null, null ],\n        \"levels\" : [ {\n          \"onLevel\" : {\n            \"key\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n          },\n          \"target\" : {\n            \"difficulty\" : \"EASY\",\n            \"collectables\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n            \"qrCode\" : \"qrCode\",\n            \"hints\" : [ \"hints\", \"hints\" ],\n            \"available\" : true,\n            \"description\" : \"description\",\n            \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n          }\n        }, {\n          \"onLevel\" : {\n            \"key\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n          },\n          \"target\" : {\n            \"difficulty\" : \"EASY\",\n            \"collectables\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n            \"qrCode\" : \"qrCode\",\n            \"hints\" : [ \"hints\", \"hints\" ],\n            \"available\" : true,\n            \"description\" : \"description\",\n            \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n          }\n        } ]\n      }\n    },\n    \"amount\" : 0,\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  }\n}", PointResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PointResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PointResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
