package tech.geocodeapp.geocode.geocode.controller;

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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import tech.geocodeapp.geocode.geocode.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.geocode.service.GeoCodeService;
import tech.geocodeapp.geocode.geocode.response.*;
import tech.geocodeapp.geocode.geocode.request.*;

import java.io.IOException;

@RestController
public class GeoCodeApiControllerV2 implements GeoCodeApi {

    private static final Logger log = LoggerFactory.getLogger( GeoCodeApiControllerV2.class );

    private ObjectMapper objectMapper;

    private HttpServletRequest request;

    private final GeoCodeService geoCodeService;

    public GeoCodeApiControllerV2( GeoCodeService geoCodeService ) {

        this.geoCodeService = geoCodeService;
    }
    
    @Autowired
    public GeoCodeApiControllerV2( ObjectMapper objectMapper, HttpServletRequest request, GeoCodeService geoCodeService ) {

        this.objectMapper = objectMapper;
        this.request = request;
        this.geoCodeService = geoCodeService;
    }

    public ResponseEntity< CreateGeoCodeResponse > createGeoCode( @Parameter( in = ParameterIn.DEFAULT,
            description = "Request to create a new GeoCode", required = true, schema = @Schema() )
                                                                  @Valid @RequestBody CreateGeoCodeRequest body ) {

        String accept = request.getHeader( "Accept" );
        if ( accept != null && accept.contains( "application/json" ) ) {

            try {

                CreateGeoCodeResponse response = geoCodeService.createGeoCode( body );

                if ( ( response != null ) && ( response.isIsSuccess() != null ) ) {

                    return new ResponseEntity<>( response, HttpStatus.OK );
                } else {

                    return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
                }
                //return new ResponseEntity<>( objectMapper.readValue( "{\n  \"success\" : true\n}", CreateGeoCodeResponse.class ), HttpStatus.NOT_IMPLEMENTED );
            } catch ( InvalidRequestException e ) {

                log.error( "Couldn't serialize response for content type application/json", e );
                return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
            }
        }

        return new ResponseEntity<>( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< GetCollectablesInGeoCodeByQRCodeResponse > getCollectablesInGeoCodeByQRCode( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get a GeoCode's collectables associated with the given QR Code", required = true, schema = @Schema() ) @Valid @RequestBody GetCollectablesInGeoCodeByQRCodeRequest body ) {

        String accept = request.getHeader( "Accept" );
        if ( accept != null && accept.contains( "application/json" ) ) {
            try {
                return new ResponseEntity<>( objectMapper.readValue( "{\n  \"storedCollectable\" : [ {\n    \"pastLocations\" : [ \"pastLocations\", \"pastLocations\" ],\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"type\" : {\n      \"area\" : \"area\",\n      \"trackable\" : true,\n      \"expiraryDate\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"set\" : {\n        \"name\" : \"name\",\n        \"description\" : \"description\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n      },\n      \"name\" : \"name\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"rarity\" : \"COMMON\"\n    }\n  }, {\n    \"pastLocations\" : [ \"pastLocations\", \"pastLocations\" ],\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"type\" : {\n      \"area\" : \"area\",\n      \"trackable\" : true,\n      \"expiraryDate\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"set\" : {\n        \"name\" : \"name\",\n        \"description\" : \"description\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n      },\n      \"name\" : \"name\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"rarity\" : \"COMMON\"\n    }\n  } ]\n}", GetCollectablesInGeoCodeByQRCodeResponse.class ), HttpStatus.NOT_IMPLEMENTED );
            } catch ( IOException e ) {
                log.error( "Couldn't serialize response for content type application/json", e );
                return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
            }
        }

        return new ResponseEntity<>( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< GetCollectablesInGeoCodesByLocationResponse > getCollectablesInGeoCodesByLocation( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get a GeoCode's collectables at or near the given location", required = true, schema = @Schema() ) @Valid @RequestBody GetCollectablesInGeoCodesByLocationRequest body ) {

        String accept = request.getHeader( "Accept" );
        if ( accept != null && accept.contains( "application/json" ) ) {
            try {
                return new ResponseEntity< GetCollectablesInGeoCodesByLocationResponse >( objectMapper.readValue( "{\n  \"storedCollectable\" : [ {\n    \"pastLocations\" : [ \"pastLocations\", \"pastLocations\" ],\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"type\" : {\n      \"area\" : \"area\",\n      \"trackable\" : true,\n      \"expiraryDate\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"set\" : {\n        \"name\" : \"name\",\n        \"description\" : \"description\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n      },\n      \"name\" : \"name\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"rarity\" : \"COMMON\"\n    }\n  }, {\n    \"pastLocations\" : [ \"pastLocations\", \"pastLocations\" ],\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"type\" : {\n      \"area\" : \"area\",\n      \"trackable\" : true,\n      \"expiraryDate\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"set\" : {\n        \"name\" : \"name\",\n        \"description\" : \"description\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n      },\n      \"name\" : \"name\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"rarity\" : \"COMMON\"\n    }\n  } ]\n}", GetCollectablesInGeoCodesByLocationResponse.class ), HttpStatus.NOT_IMPLEMENTED );
            } catch ( IOException e ) {
                log.error( "Couldn't serialize response for content type application/json", e );
                return new ResponseEntity< GetCollectablesInGeoCodesByLocationResponse >( HttpStatus.INTERNAL_SERVER_ERROR );
            }
        }

        return new ResponseEntity< GetCollectablesInGeoCodesByLocationResponse >( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< GetGeoCodeResponse > getGeoCode( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get a stored GeoCode with teh specified ID", required = true, schema = @Schema() ) @Valid @RequestBody GetGeoCodeRequest body ) {

        String accept = request.getHeader( "Accept" );
        if ( accept != null && accept.contains( "application/json" ) ) {
            try {
                return new ResponseEntity< GetGeoCodeResponse >( objectMapper.readValue( "{\n  \"foundGeoCode\" : {\n    \"difficulty\" : \"EASY\",\n    \"eventID\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n    \"collectables\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n    \"qrCode\" : \"1qwer3d4\",\n    \"createdBy\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"This describes a GeoCode\",\n    \"id\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n  }\n}", GetGeoCodeResponse.class ), HttpStatus.NOT_IMPLEMENTED );
            } catch ( IOException e ) {
                log.error( "Couldn't serialize response for content type application/json", e );
                return new ResponseEntity< GetGeoCodeResponse >( HttpStatus.INTERNAL_SERVER_ERROR );
            }
        }

        return new ResponseEntity< GetGeoCodeResponse >( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< GetGeoCodeByLocationResponse > getGeoCodeByLocation( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get a GeoCode at or near the given location", required = true, schema = @Schema() ) @Valid @RequestBody GetGeoCodeByLocationRequest body ) {

        String accept = request.getHeader( "Accept" );
        if ( accept != null && accept.contains( "application/json" ) ) {
            try {
                return new ResponseEntity< GetGeoCodeByLocationResponse >( objectMapper.readValue( "{\n  \"difficulty\" : \"EASY\",\n  \"available\" : true,\n  \"description\" : \"This describes the GeoCode to be created\",\n  \"location\" : {\n    \"latitude\" : 25.7545,\n    \"longitude\" : 28.2314\n  },\n  \"id\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n}", GetGeoCodeByLocationResponse.class ), HttpStatus.NOT_IMPLEMENTED );
            } catch ( IOException e ) {
                log.error( "Couldn't serialize response for content type application/json", e );
                return new ResponseEntity< GetGeoCodeByLocationResponse >( HttpStatus.INTERNAL_SERVER_ERROR );
            }
        }

        return new ResponseEntity< GetGeoCodeByLocationResponse >( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< GetGeoCodeByQRCodeResponse > getGeoCodeByQRCode( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get a GeoCode associated with the given QR Code", required = true, schema = @Schema() ) @Valid @RequestBody GetGeoCodeByQRCodeRequest body ) {

        String accept = request.getHeader( "Accept" );
        if ( accept != null && accept.contains( "application/json" ) ) {
            try {
                return new ResponseEntity< GetGeoCodeByQRCodeResponse >( objectMapper.readValue( "{\n  \"difficulty\" : \"EASY\",\n  \"available\" : true,\n  \"description\" : \"This describes the GeoCode to be created\",\n  \"location\" : {\n    \"latitude\" : 25.7545,\n    \"longitude\" : 28.2314\n  },\n  \"id\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n}", GetGeoCodeByQRCodeResponse.class ), HttpStatus.NOT_IMPLEMENTED );
            } catch ( IOException e ) {
                log.error( "Couldn't serialize response for content type application/json", e );
                return new ResponseEntity< GetGeoCodeByQRCodeResponse >( HttpStatus.INTERNAL_SERVER_ERROR );
            }
        }

        return new ResponseEntity< GetGeoCodeByQRCodeResponse >( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< GetCollectablesResponse > getGeoCodeCollectables( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get a GeoCode's Collectables", required = true, schema = @Schema() ) @Valid @RequestBody GetCollectablesRequest body ) {

        String accept = request.getHeader( "Accept" );
        if ( accept != null && accept.contains( "application/json" ) ) {
            try {
                return new ResponseEntity< GetCollectablesResponse >( objectMapper.readValue( "{\n  \"collectables\" : [ {\n    \"pastLocations\" : [ \"pastLocations\", \"pastLocations\" ],\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"type\" : {\n      \"area\" : \"area\",\n      \"trackable\" : true,\n      \"expiraryDate\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"set\" : {\n        \"name\" : \"name\",\n        \"description\" : \"description\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n      },\n      \"name\" : \"name\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"rarity\" : \"COMMON\"\n    }\n  }, {\n    \"pastLocations\" : [ \"pastLocations\", \"pastLocations\" ],\n    \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n    \"type\" : {\n      \"area\" : \"area\",\n      \"trackable\" : true,\n      \"expiraryDate\" : \"2000-01-23T04:56:07.000+00:00\",\n      \"set\" : {\n        \"name\" : \"name\",\n        \"description\" : \"description\",\n        \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\"\n      },\n      \"name\" : \"name\",\n      \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",\n      \"rarity\" : \"COMMON\"\n    }\n  } ]\n}", GetCollectablesResponse.class ), HttpStatus.NOT_IMPLEMENTED );
            } catch ( IOException e ) {
                log.error( "Couldn't serialize response for content type application/json", e );
                return new ResponseEntity< GetCollectablesResponse >( HttpStatus.INTERNAL_SERVER_ERROR );
            }
        }

        return new ResponseEntity< GetCollectablesResponse >( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< GetGeoCodesResponse > getGeoCodes() {

        String accept = request.getHeader( "Accept" );
        if ( accept != null && accept.contains( "application/json" ) ) {
            try {
                return new ResponseEntity< GetGeoCodesResponse >( objectMapper.readValue( "{\n  \"geocodes\" : [ {\n    \"difficulty\" : \"EASY\",\n    \"eventID\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n    \"collectables\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n    \"qrCode\" : \"1qwer3d4\",\n    \"createdBy\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"This describes a GeoCode\",\n    \"id\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n  }, {\n    \"difficulty\" : \"EASY\",\n    \"eventID\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n    \"collectables\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n    \"qrCode\" : \"1qwer3d4\",\n    \"createdBy\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"This describes a GeoCode\",\n    \"id\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n  } ]\n}", GetGeoCodesResponse.class ), HttpStatus.NOT_IMPLEMENTED );
            } catch ( IOException e ) {
                log.error( "Couldn't serialize response for content type application/json", e );
                return new ResponseEntity< GetGeoCodesResponse >( HttpStatus.INTERNAL_SERVER_ERROR );
            }
        }

        return new ResponseEntity< GetGeoCodesResponse >( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< GetGeoCodesByDifficultyResponse > getGeoCodesByDifficulty( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get all the GeoCodes by the specified difficulty", required = true, schema = @Schema() ) @Valid @RequestBody GetGeoCodesByDifficultyRequest body ) {

        String accept = request.getHeader( "Accept" );
        if ( accept != null && accept.contains( "application/json" ) ) {
            try {
                return new ResponseEntity< GetGeoCodesByDifficultyResponse >( objectMapper.readValue( "{\n  \"geocodes\" : [ {\n    \"difficulty\" : \"EASY\",\n    \"eventID\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n    \"collectables\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n    \"qrCode\" : \"1qwer3d4\",\n    \"createdBy\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"This describes a GeoCode\",\n    \"id\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n  }, {\n    \"difficulty\" : \"EASY\",\n    \"eventID\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n    \"collectables\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n    \"qrCode\" : \"1qwer3d4\",\n    \"createdBy\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"This describes a GeoCode\",\n    \"id\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n  } ]\n}", GetGeoCodesByDifficultyResponse.class ), HttpStatus.NOT_IMPLEMENTED );
            } catch ( IOException e ) {
                log.error( "Couldn't serialize response for content type application/json", e );
                return new ResponseEntity< GetGeoCodesByDifficultyResponse >( HttpStatus.INTERNAL_SERVER_ERROR );
            }
        }

        return new ResponseEntity< GetGeoCodesByDifficultyResponse >( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< GetGeoCodesByDifficultyListResponse > getGeoCodesByDifficultyList( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get all the GeoCodes by the specified difficulties", required = true, schema = @Schema() ) @Valid @RequestBody GetGeoCodesByDifficultyListRequest body ) {

        String accept = request.getHeader( "Accept" );
        if ( accept != null && accept.contains( "application/json" ) ) {
            try {
                return new ResponseEntity< GetGeoCodesByDifficultyListResponse >( objectMapper.readValue( "{\n  \"geocodes\" : [ {\n    \"difficulty\" : \"EASY\",\n    \"eventID\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n    \"collectables\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n    \"qrCode\" : \"1qwer3d4\",\n    \"createdBy\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"This describes a GeoCode\",\n    \"id\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n  }, {\n    \"difficulty\" : \"EASY\",\n    \"eventID\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n    \"collectables\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ],\n    \"qrCode\" : \"1qwer3d4\",\n    \"createdBy\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\",\n    \"hints\" : [ \"hints\", \"hints\" ],\n    \"available\" : true,\n    \"description\" : \"This describes a GeoCode\",\n    \"id\" : \"054463f2-2f7c-4864-8130-68e5aa79ee7f\"\n  } ]\n}", GetGeoCodesByDifficultyListResponse.class ), HttpStatus.NOT_IMPLEMENTED );
            } catch ( IOException e ) {
                log.error( "Couldn't serialize response for content type application/json", e );
                return new ResponseEntity< GetGeoCodesByDifficultyListResponse >( HttpStatus.INTERNAL_SERVER_ERROR );
            }
        }

        return new ResponseEntity< GetGeoCodesByDifficultyListResponse >( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< GetHintsResponse > getHints( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get the hints from the specified GeoCode", required = true, schema = @Schema() ) @Valid @RequestBody GetHintsRequest body ) {

        String accept = request.getHeader( "Accept" );
        if ( accept != null && accept.contains( "application/json" ) ) {
            try {
                return new ResponseEntity< GetHintsResponse >( objectMapper.readValue( "{\n  \"hints\" : [ \"hints\", \"hints\" ]\n}", GetHintsResponse.class ), HttpStatus.NOT_IMPLEMENTED );
            } catch ( IOException e ) {
                log.error( "Couldn't serialize response for content type application/json", e );
                return new ResponseEntity< GetHintsResponse >( HttpStatus.INTERNAL_SERVER_ERROR );
            }
        }

        return new ResponseEntity< GetHintsResponse >( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< SwapCollectablesResponse > swapCollectables( @Parameter( in = ParameterIn.DEFAULT, description = "Request to swap a GeoCode's Collectables", required = true, schema = @Schema() ) @Valid @RequestBody SwapCollectablesRequest body ) {

        String accept = request.getHeader( "Accept" );
        if ( accept != null && accept.contains( "application/json" ) ) {
            try {
                return new ResponseEntity< SwapCollectablesResponse >( objectMapper.readValue( "{\n  \"success\" : true\n}", SwapCollectablesResponse.class ), HttpStatus.NOT_IMPLEMENTED );
            } catch ( IOException e ) {
                log.error( "Couldn't serialize response for content type application/json", e );
                return new ResponseEntity< SwapCollectablesResponse >( HttpStatus.INTERNAL_SERVER_ERROR );
            }
        }

        return new ResponseEntity< SwapCollectablesResponse >( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< UpdateAvailabilityResponse > updateAvailability( @Parameter( in = ParameterIn.DEFAULT, description = "Request to update a GeoCode's availability", required = true, schema = @Schema() ) @Valid @RequestBody UpdateAvailabilityRequest body ) {

        String accept = request.getHeader( "Accept" );
        if ( accept != null && accept.contains( "application/json" ) ) {
            try {
                return new ResponseEntity< UpdateAvailabilityResponse >( objectMapper.readValue( "{\n  \"success\" : true\n}", UpdateAvailabilityResponse.class ), HttpStatus.NOT_IMPLEMENTED );
            } catch ( IOException e ) {
                log.error( "Couldn't serialize response for content type application/json", e );
                return new ResponseEntity< UpdateAvailabilityResponse >( HttpStatus.INTERNAL_SERVER_ERROR );
            }
        }

        return new ResponseEntity< UpdateAvailabilityResponse >( HttpStatus.NOT_IMPLEMENTED );
    }

}
