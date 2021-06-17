package tech.geocodeapp.geocode.User.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-09T21:02:56.988Z[GMT]")
@RestController
public class UserApiController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<User> blockUser(@Parameter(in = ParameterIn.DEFAULT, description = "Request to block a user", required=true, schema=@Schema()) @Valid @RequestBody BlockUserRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<User>(objectMapper.readValue("{\n  \"currentCollectable\" : {\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"type\" : {\n      \"image\" : \"image\",\n      \"set\" : {\n        \"name\" : \"name\",\n        \"description\" : \"description\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n      },\n      \"name\" : \"name\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"rarity\" : \"COMMON\"\n    }\n  },\n  \"foundGeocodes\" : [ {\n    \"difficulty\" : \"EASY\",\n    \"collectables\" : {\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"type\" : {\n        \"image\" : \"image\",\n        \"set\" : {\n          \"name\" : \"name\",\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        },\n        \"name\" : \"name\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"rarity\" : \"COMMON\"\n      }\n    },\n    \"qrCode\" : \"qrCode\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"description\",\n    \"trackables\" : \"trackables\",\n    \"location\" : \"location\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  }, {\n    \"difficulty\" : \"EASY\",\n    \"collectables\" : {\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"type\" : {\n        \"image\" : \"image\",\n        \"set\" : {\n          \"name\" : \"name\",\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        },\n        \"name\" : \"name\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"rarity\" : \"COMMON\"\n      }\n    },\n    \"qrCode\" : \"qrCode\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"description\",\n    \"trackables\" : \"trackables\",\n    \"location\" : \"location\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  } ],\n  \"foundCollectables\" : [ null, null ],\n  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"ownedGeocodes\" : [ null, null ],\n  \"username\" : \"username\",\n  \"points\" : [ {\n    \"amount\" : 0,\n    \"leaderBoardId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"userId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  }, {\n    \"amount\" : 0,\n    \"leaderBoardId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"userId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  } ]\n}", User.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetCurrentCollectableResponse> getCurrentCollectable(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user's current Collectable", required=true, schema=@Schema()) @Valid @RequestBody GetCurrentCollectableRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<GetCurrentCollectableResponse>(objectMapper.readValue("{\n  \"Collectable\" : {\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"type\" : {\n      \"image\" : \"image\",\n      \"set\" : {\n        \"name\" : \"name\",\n        \"description\" : \"description\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n      },\n      \"name\" : \"name\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"rarity\" : \"COMMON\"\n    }\n  }\n}", GetCurrentCollectableResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetCurrentCollectableResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetCurrentCollectableResponse>(HttpStatus.NOT_IMPLEMENTED);
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

    public ResponseEntity<User> setAdmin(@Parameter(in = ParameterIn.DEFAULT, description = "Request to set a user to be an administrator", required=true, schema=@Schema()) @Valid @RequestBody SetAdminRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<User>(objectMapper.readValue("{\n  \"currentCollectable\" : {\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"type\" : {\n      \"image\" : \"image\",\n      \"set\" : {\n        \"name\" : \"name\",\n        \"description\" : \"description\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n      },\n      \"name\" : \"name\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"rarity\" : \"COMMON\"\n    }\n  },\n  \"foundGeocodes\" : [ {\n    \"difficulty\" : \"EASY\",\n    \"collectables\" : {\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"type\" : {\n        \"image\" : \"image\",\n        \"set\" : {\n          \"name\" : \"name\",\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        },\n        \"name\" : \"name\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"rarity\" : \"COMMON\"\n      }\n    },\n    \"qrCode\" : \"qrCode\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"description\",\n    \"trackables\" : \"trackables\",\n    \"location\" : \"location\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  }, {\n    \"difficulty\" : \"EASY\",\n    \"collectables\" : {\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"type\" : {\n        \"image\" : \"image\",\n        \"set\" : {\n          \"name\" : \"name\",\n          \"description\" : \"description\",\n          \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n        },\n        \"name\" : \"name\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n        \"rarity\" : \"COMMON\"\n      }\n    },\n    \"qrCode\" : \"qrCode\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"description\",\n    \"trackables\" : \"trackables\",\n    \"location\" : \"location\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  } ],\n  \"foundCollectables\" : [ null, null ],\n  \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n  \"ownedGeocodes\" : [ null, null ],\n  \"username\" : \"username\",\n  \"points\" : [ {\n    \"amount\" : 0,\n    \"leaderBoardId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"userId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  }, {\n    \"amount\" : 0,\n    \"leaderBoardId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"userId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n  } ]\n}", User.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SwapCollectableResponse> swapCollectable(@Parameter(in = ParameterIn.DEFAULT, description = "Request to swap the held Collectable", required=true, schema=@Schema()) @Valid @RequestBody SwapCollectableRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SwapCollectableResponse>(objectMapper.readValue("{\n  \"Collectable\" : {\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"type\" : {\n      \"image\" : \"image\",\n      \"set\" : {\n        \"name\" : \"name\",\n        \"description\" : \"description\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n      },\n      \"name\" : \"name\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"rarity\" : \"COMMON\"\n    }\n  }\n}", SwapCollectableResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SwapCollectableResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SwapCollectableResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
