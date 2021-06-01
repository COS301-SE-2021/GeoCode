package io.swagger.api;

import io.swagger.model.Collectable;
import io.swagger.model.CreateCollectableRequest;
import io.swagger.model.GeoCodeCollectablesResponse;
import io.swagger.model.GetGeoCodeCollectablesRequest;
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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-01T08:10:17.138Z[GMT]")
@RestController
public class CollectableApiController implements CollectableApi {

    private static final Logger log = LoggerFactory.getLogger(CollectableApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CollectableApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Collectable> createCollectable(@Parameter(in = ParameterIn.DEFAULT, description = "Request to create a new Collectable", required=true, schema=@Schema()) @Valid @RequestBody CreateCollectableRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Collectable>(objectMapper.readValue("{\n  \"image\" : \"image\",\n  \"name\" : \"name\",\n  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"rarity\" : \"COMMON\"\n}", Collectable.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Collectable>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Collectable>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GeoCodeCollectablesResponse> getGeoCodeCollectables(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a GeoCode's Collectables", required=true, schema=@Schema()) @Valid @RequestBody GetGeoCodeCollectablesRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GeoCodeCollectablesResponse>(objectMapper.readValue("{\n  \"collectables\" : [ {\n    \"image\" : \"image\",\n    \"name\" : \"name\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"rarity\" : \"COMMON\"\n  }, {\n    \"image\" : \"image\",\n    \"name\" : \"name\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"rarity\" : \"COMMON\"\n  } ]\n}", GeoCodeCollectablesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GeoCodeCollectablesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GeoCodeCollectablesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
