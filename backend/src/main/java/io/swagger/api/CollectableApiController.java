package io.swagger.api;

import io.swagger.model.Collectable;
import io.swagger.model.CollectableSet;
import io.swagger.model.CollectableType;
import io.swagger.model.CreateCollectableRequest;
import io.swagger.model.CreateCollectableSetRequest;
import io.swagger.model.CreateCollectableTypeRequest;
import io.swagger.model.GetCollectableByTypeRequest;
import io.swagger.model.GetCollectableSetsResponse;
import io.swagger.model.GetCollectableTypeByRarityRequest;
import io.swagger.model.GetCollectableTypeBySetRequest;
import io.swagger.model.GetCollectableTypesResponse;
import io.swagger.model.GetCollectablesResponse;
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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-01T18:53:58.734Z[GMT]")
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
                return new ResponseEntity<Collectable>(objectMapper.readValue("{\n  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"type\" : {\n    \"image\" : \"image\",\n    \"set\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n    },\n    \"name\" : \"name\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"rarity\" : \"COMMON\"\n  }\n}", Collectable.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Collectable>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Collectable>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CollectableSet> createCollectableSet(@Parameter(in = ParameterIn.DEFAULT, description = "Request to create a new Collectable Set", required=true, schema=@Schema()) @Valid @RequestBody CreateCollectableSetRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CollectableSet>(objectMapper.readValue("{\n  \"name\" : \"name\",\n  \"description\" : \"description\",\n  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n}", CollectableSet.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CollectableSet>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CollectableSet>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CollectableType> createCollectableType(@Parameter(in = ParameterIn.DEFAULT, description = "Request to create a new Collectable Type", required=true, schema=@Schema()) @Valid @RequestBody CreateCollectableTypeRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CollectableType>(objectMapper.readValue("{\n  \"image\" : \"image\",\n  \"set\" : {\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  },\n  \"name\" : \"name\",\n  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"rarity\" : \"COMMON\"\n}", CollectableType.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CollectableType>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CollectableType>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetCollectablesResponse> getCollectableByType(@Parameter(in = ParameterIn.DEFAULT, description = "Request Collectables by type", required=true, schema=@Schema()) @Valid @RequestBody GetCollectableByTypeRequest body) {
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

    public ResponseEntity<GetCollectableSetsResponse> getCollectableSets() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetCollectableSetsResponse>(objectMapper.readValue("{\n  \"collectableSets\" : [ {\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  }, {\n    \"name\" : \"name\",\n    \"description\" : \"description\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  } ]\n}", GetCollectableSetsResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetCollectableSetsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetCollectableSetsResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetCollectableTypesResponse> getCollectableTypeByRarity(@Parameter(in = ParameterIn.DEFAULT, description = "Request Collectable Types by rarity", required=true, schema=@Schema()) @Valid @RequestBody GetCollectableTypeByRarityRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetCollectableTypesResponse>(objectMapper.readValue("{\n  \"collectableTypes\" : [ {\n    \"image\" : \"image\",\n    \"set\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n    },\n    \"name\" : \"name\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"rarity\" : \"COMMON\"\n  }, {\n    \"image\" : \"image\",\n    \"set\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n    },\n    \"name\" : \"name\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"rarity\" : \"COMMON\"\n  } ]\n}", GetCollectableTypesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetCollectableTypesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetCollectableTypesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetCollectableTypesResponse> getCollectableTypeBySet(@Parameter(in = ParameterIn.DEFAULT, description = "Request Collectable Types by set", required=true, schema=@Schema()) @Valid @RequestBody GetCollectableTypeBySetRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetCollectableTypesResponse>(objectMapper.readValue("{\n  \"collectableTypes\" : [ {\n    \"image\" : \"image\",\n    \"set\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n    },\n    \"name\" : \"name\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"rarity\" : \"COMMON\"\n  }, {\n    \"image\" : \"image\",\n    \"set\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n    },\n    \"name\" : \"name\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"rarity\" : \"COMMON\"\n  } ]\n}", GetCollectableTypesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetCollectableTypesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetCollectableTypesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetCollectableTypesResponse> getCollectableTypes() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetCollectableTypesResponse>(objectMapper.readValue("{\n  \"collectableTypes\" : [ {\n    \"image\" : \"image\",\n    \"set\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n    },\n    \"name\" : \"name\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"rarity\" : \"COMMON\"\n  }, {\n    \"image\" : \"image\",\n    \"set\" : {\n      \"name\" : \"name\",\n      \"description\" : \"description\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n    },\n    \"name\" : \"name\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"rarity\" : \"COMMON\"\n  } ]\n}", GetCollectableTypesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetCollectableTypesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetCollectableTypesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetCollectablesResponse> getCollectables() {
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

}
