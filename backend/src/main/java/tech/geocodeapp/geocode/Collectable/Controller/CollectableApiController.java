package tech.geocodeapp.geocode.collectable.controller;

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
import tech.geocodeapp.geocode.collectable.request.*;
import tech.geocodeapp.geocode.collectable.response.*;
import tech.geocodeapp.geocode.collectable.service.CollectableServiceImpl;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-02T03:21:48.298Z[GMT]")
@RestController
public class CollectableApiController implements CollectableApi {

    private static final Logger log = LoggerFactory.getLogger(CollectableApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private CollectableServiceImpl collectableService;

    @org.springframework.beans.factory.annotation.Autowired
    public CollectableApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;

    }

    public ResponseEntity<CreateCollectableResponse> createCollectable(@Parameter(in = ParameterIn.DEFAULT, description = "Request to create a new Collectable", required=true, schema=@Schema()) @Valid @RequestBody CreateCollectableRequest body) {
        CreateCollectableResponse response = collectableService.createCollectable(body);

        if(response.isSuccess()){
            return new ResponseEntity<CreateCollectableResponse>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<CreateCollectableResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<CreateCollectableSetResponse> createCollectableSet(@Parameter(in = ParameterIn.DEFAULT, description = "Request to create a new Collectable Set", required=true, schema=@Schema()) @Valid @RequestBody CreateCollectableSetRequest body) {
        CreateCollectableSetResponse response = collectableService.createCollectableSet(body);

        if(response.isSuccess()){
            return new ResponseEntity<CreateCollectableSetResponse>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<CreateCollectableSetResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<CreateCollectableTypeResponse> createCollectableType(@Parameter(in = ParameterIn.DEFAULT, description = "Request to create a new Collectable Type", required=true, schema=@Schema()) @Valid @RequestBody CreateCollectableTypeRequest body) {
        CreateCollectableTypeResponse response = collectableService.createCollectableType(body);

        if(response.isSuccess()){
            return new ResponseEntity<CreateCollectableTypeResponse>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<CreateCollectableTypeResponse>(response, HttpStatus.BAD_REQUEST);
        }
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
        GetCollectableSetsResponse response = collectableService.getCollectableSets();

        if(!response.getCollectableSets().isEmpty()){
            return new ResponseEntity<GetCollectableSetsResponse>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<GetCollectableSetsResponse>(response, HttpStatus.NOT_FOUND);
        }
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

    public ResponseEntity<GetCollectableTypesResponse> getCollectableTypesBySet(@Parameter(in = ParameterIn.DEFAULT, description = "Request Collectable Types by set", required=true, schema=@Schema()) @Valid @RequestBody GetCollectableTypesBySetRequest body) {
        GetCollectableTypesResponse response = collectableService.getCollectableTypesBySet(body);

        if(!response.getCollectableTypes().isEmpty()){
            return new ResponseEntity<GetCollectableTypesResponse>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<GetCollectableTypesResponse>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<GetCollectableTypesResponse> getCollectableTypes() {
        GetCollectableTypesResponse response = collectableService.getCollectableTypes();

        if(!response.getCollectableTypes().isEmpty()){
            return new ResponseEntity<GetCollectableTypesResponse>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<GetCollectableTypesResponse>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<GetCollectablesResponse> getCollectables() {
        GetCollectablesResponse response = collectableService.getCollectables();

        if(!response.getCollectables().isEmpty()){
            return new ResponseEntity<GetCollectablesResponse>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<GetCollectablesResponse>(response, HttpStatus.NOT_FOUND);
        }
    }
}