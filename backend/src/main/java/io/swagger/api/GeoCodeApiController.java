package io.swagger.api;

import io.swagger.model.CreateGeoCodeRequest;
import io.swagger.model.CreateGeoCodeResponse;
import io.swagger.model.GetCollectablesRequest;
import io.swagger.model.GetCollectablesResponse;
import io.swagger.model.GetGeoCodeByLocationRequest;
import io.swagger.model.GetGeoCodeByLocationResponse;
import io.swagger.model.GetGeoCodeByQRCodeRequest;
import io.swagger.model.GetGeoCodeByQRCodeResponse;
import io.swagger.model.GetGeoCodesByDifficultyRequest;
import io.swagger.model.GetGeoCodesByDifficultyResponse;
import io.swagger.model.GetGeoCodesResponse;
import io.swagger.model.GetHintsRequest;
import io.swagger.model.GetHintsResponse;
import io.swagger.model.GetTrackablesRequest;
import io.swagger.model.GetTrackablesResponse;
import io.swagger.model.SwapCollectablesRequest;
import io.swagger.model.SwapCollectablesResponse;
import io.swagger.model.UpdateAvailabilityRequest;
import io.swagger.model.UpdateAvailabilityResponse;
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

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-01T21:43:55.444Z[GMT]")
@RestController
public class GeoCodeApiController implements GeoCodeApi {

    private static final Logger log = LoggerFactory.getLogger(GeoCodeApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public GeoCodeApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CreateGeoCodeResponse> createGeoCode(@Parameter(in = ParameterIn.DEFAULT, description = "Request to create a new GeoCode", required=true, schema=@Schema()) @Valid @RequestBody CreateGeoCodeRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CreateGeoCodeResponse>(objectMapper.readValue("{\n  \"geoCode\" : {\n    \"difficulty\" : \"EASY\",\n    \"collectables\" : {\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"type\" : {\n        \"image\" : \"image\",\n        \"set\" : {\n          \"name\" : \"name\",\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        },\n        \"name\" : \"name\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"rarity\" : \"COMMON\"\n      }\n    },\n    \"qrCode\" : \"qrCode\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"description\",\n    \"trackables\" : \"trackables\",\n    \"location\" : \"location\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  }\n}", CreateGeoCodeResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CreateGeoCodeResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CreateGeoCodeResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetGeoCodeByLocationResponse> getGeoCodeByLocation(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a GeoCode at or near the given location", required=true, schema=@Schema()) @Valid @RequestBody GetGeoCodeByLocationRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetGeoCodeByLocationResponse>(objectMapper.readValue("{\n  \"qrCode\" : \"qrCode\",\n  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n}", GetGeoCodeByLocationResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetGeoCodeByLocationResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetGeoCodeByLocationResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetGeoCodeByQRCodeResponse> getGeoCodeByQRCode(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a GeoCode's associated with the given QR Code", required=true, schema=@Schema()) @Valid @RequestBody GetGeoCodeByQRCodeRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetGeoCodeByQRCodeResponse>(objectMapper.readValue("{\n  \"qrCode\" : \"qrCode\",\n  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n}", GetGeoCodeByQRCodeResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetGeoCodeByQRCodeResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetGeoCodeByQRCodeResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetCollectablesResponse> getGeoCodeCollectables(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a GeoCode's Collectables", required=true, schema=@Schema()) @Valid @RequestBody GetCollectablesRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetCollectablesResponse>(objectMapper.readValue("{\n  \"collectables\" : [ {\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"type\" : {\n      \"image\" : \"image\",\n      \"set\" : {\n        \"name\" : \"name\",\n        \"description\" : \"description\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n      },\n      \"name\" : \"name\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"rarity\" : \"COMMON\"\n    }\n  }, {\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"type\" : {\n      \"image\" : \"image\",\n      \"set\" : {\n        \"name\" : \"name\",\n        \"description\" : \"description\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n      },\n      \"name\" : \"name\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"rarity\" : \"COMMON\"\n    }\n  } ]\n}", GetCollectablesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetCollectablesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetCollectablesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetGeoCodesResponse> getGeoCodes() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetGeoCodesResponse>(objectMapper.readValue("{\n  \"geocodes\" : [ {\n    \"difficulty\" : \"EASY\",\n    \"collectables\" : {\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"type\" : {\n        \"image\" : \"image\",\n        \"set\" : {\n          \"name\" : \"name\",\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        },\n        \"name\" : \"name\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"rarity\" : \"COMMON\"\n      }\n    },\n    \"qrCode\" : \"qrCode\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"description\",\n    \"trackables\" : \"trackables\",\n    \"location\" : \"location\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  }, {\n    \"difficulty\" : \"EASY\",\n    \"collectables\" : {\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"type\" : {\n        \"image\" : \"image\",\n        \"set\" : {\n          \"name\" : \"name\",\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        },\n        \"name\" : \"name\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"rarity\" : \"COMMON\"\n      }\n    },\n    \"qrCode\" : \"qrCode\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"description\",\n    \"trackables\" : \"trackables\",\n    \"location\" : \"location\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  } ]\n}", GetGeoCodesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetGeoCodesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetGeoCodesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetGeoCodesByDifficultyResponse> getGeoCodesByDifficulty(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get all the GeoCodes by the specified difficulty", required=true, schema=@Schema()) @Valid @RequestBody GetGeoCodesByDifficultyRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetGeoCodesByDifficultyResponse>(objectMapper.readValue("{\n  \"qrCode\" : \"qrCode\",\n  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n}", GetGeoCodesByDifficultyResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetGeoCodesByDifficultyResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetGeoCodesByDifficultyResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetHintsResponse> getHints(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the hints from the specified GeoCode", required=true, schema=@Schema()) @Valid @RequestBody GetHintsRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetHintsResponse>(objectMapper.readValue("{\n  \"qrCode\" : \"qrCode\",\n  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n}", GetHintsResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetHintsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetHintsResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetTrackablesResponse> getTrackables(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a GeoCode's Trackable", required=true, schema=@Schema()) @Valid @RequestBody GetTrackablesRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetTrackablesResponse>(objectMapper.readValue("{\n  \"qrCode\" : \"qrCode\",\n  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n}", GetTrackablesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetTrackablesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetTrackablesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SwapCollectablesResponse> swapCollectables(@Parameter(in = ParameterIn.DEFAULT, description = "Request to swap a GeoCode's Collectables", required=true, schema=@Schema()) @Valid @RequestBody SwapCollectablesRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SwapCollectablesResponse>(objectMapper.readValue("{\n  \"qrCode\" : \"qrCode\",\n  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n}", SwapCollectablesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SwapCollectablesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SwapCollectablesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<UpdateAvailabilityResponse> updateAvailability(@Parameter(in = ParameterIn.DEFAULT, description = "Request to update a GeoCode's availability", required=true, schema=@Schema()) @Valid @RequestBody UpdateAvailabilityRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<UpdateAvailabilityResponse>(objectMapper.readValue("{\n  \"qrCode\" : \"qrCode\",\n  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n}", UpdateAvailabilityResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<UpdateAvailabilityResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<UpdateAvailabilityResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
