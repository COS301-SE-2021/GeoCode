package tech.geocodeapp.geocode.geocode.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

import tech.geocodeapp.geocode.geocode.exceptions.*;
import tech.geocodeapp.geocode.geocode.service.GeoCodeService;
import tech.geocodeapp.geocode.geocode.response.*;
import tech.geocodeapp.geocode.geocode.request.*;

@RestController
@Validated
public class GeoCodeApiController implements GeoCodeApi {

    private final GeoCodeService geoCodeService;

    public GeoCodeApiController( GeoCodeService geoCodeService ) {

        this.geoCodeService = geoCodeService;
    }

    public ResponseEntity< CreateGeoCodeResponse > createGeoCode( @Parameter( in = ParameterIn.DEFAULT,
            description = "Request to create a new GeoCode",
            required = true, schema = @Schema() )
                                                                  @Valid @RequestBody CreateGeoCodeRequest body ) throws InvalidRequestException {

        CreateGeoCodeResponse response = geoCodeService.createGeoCode( body );

        if ( ( response != null ) && ( response.isSuccess() != null ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity< UpdateGeoCodeResponse > updateGeoCode( @Parameter( in = ParameterIn.DEFAULT,
            description = "Request to update a GeoCode",
            required = true, schema = @Schema() )
                                                                   @Valid @RequestBody UpdateGeoCodeRequest body ) throws InvalidRequestException {

        UpdateGeoCodeResponse response = geoCodeService.updateGeoCode( body );

        if ( ( response != null ) && ( response.isSuccess() != null ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity< GetGeoCodeByLocationResponse > getGeoCodeByLocation( @Parameter( in = ParameterIn.DEFAULT,
            description = "Request to get a GeoCode at or near the given location",
            required = true, schema = @Schema() )
                                                                                @Valid @RequestBody GetGeoCodeByLocationRequest body ) throws InvalidRequestException {

        GetGeoCodeByLocationResponse response = geoCodeService.getGeoCodesByLocation( body );

        if ( ( response != null ) && ( response.getId() != null ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity< GetGeoCodeByQRCodeResponse > getGeoCodeByQRCode( @Parameter( in = ParameterIn.DEFAULT,
            description = "Request to get a GeoCode's associated with the given QR Code",
            required = true, schema = @Schema() )
                                                                            @Valid @RequestBody GetGeoCodeByQRCodeRequest body ) throws InvalidRequestException {

        GetGeoCodeByQRCodeResponse response = geoCodeService.getGeoCodeByQRCode( body );

        if ( ( response != null ) && ( response.getId() != null ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity< GetCollectablesInGeoCodeByQRCodeResponse > getCollectablesInGeoCodeByQRCode( @Parameter( in = ParameterIn.DEFAULT,
            description = "Request to get a GeoCode's collectables associated with the given QR Code and GeoCode ID",
            required = true, schema = @Schema() )
                                                                                                        @Valid @RequestBody GetCollectablesInGeoCodeByQRCodeRequest body ) throws InvalidRequestException {

        GetCollectablesInGeoCodeByQRCodeResponse response = geoCodeService.getCollectablesInGeoCodeByQRCode( body );

        if ( response != null ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity< GetCollectablesInGeoCodesByLocationResponse > getCollectablesInGeoCodesByLocation( @Parameter( in = ParameterIn.DEFAULT,
            description = "Request to get a GeoCode's collectables at or near the given location", required = true, schema = @Schema() )
                                                                                                              @Valid @RequestBody GetCollectablesInGeoCodesByLocationRequest body ) throws InvalidRequestException {

        GetCollectablesInGeoCodesByLocationResponse response = geoCodeService.getCollectablesInGeoCodesByLocation( body );

        if ( response != null ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity< GetGeoCodeResponse > getGeoCode( @Parameter( in = ParameterIn.DEFAULT,
            description = "Request to get a stored GeoCode with teh specified ID", required = true, schema = @Schema() )
                                                            @Valid @RequestBody GetGeoCodeRequest body ) throws InvalidRequestException {

        GetGeoCodeResponse response = geoCodeService.getGeoCode( body );

        if ( response != null ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }


    public ResponseEntity< GetCollectablesResponse > getGeoCodeCollectables( @Parameter( in = ParameterIn.DEFAULT,
            description = "Request to get a GeoCode's Collectables",
            required = true, schema = @Schema() )
                                                                             @Valid @RequestBody GetCollectablesRequest body ) throws InvalidRequestException {

        GetCollectablesResponse response = geoCodeService.getCollectables( body );

        if ( ( response != null ) && ( response.getCollectables() != null ) && ( !response.getCollectables().isEmpty() ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity< GetGeoCodesResponse > getGeoCodes() {

        GetGeoCodesResponse response = geoCodeService.getAllGeoCodes();

        if ( ( response != null ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity< GetGeoCodesByDifficultyResponse > getGeoCodesByDifficulty( @Parameter( in = ParameterIn.DEFAULT,
            description = "Request to get all the GeoCodes by the specified difficulty",
            required = true, schema = @Schema() )
                                                                                      @Valid @RequestBody GetGeoCodesByDifficultyRequest body ) throws InvalidRequestException {

        GetGeoCodesByDifficultyResponse response = geoCodeService.getGeoCodesByDifficulty( body );

        if ( response != null ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity< GetGeoCodesByDifficultyListResponse > getGeoCodesByDifficultyList( @Parameter( in = ParameterIn.DEFAULT,
            description = "Request to get all the GeoCodes by the specified difficulties", required = true, schema = @Schema() )
                                                                                              @Valid @RequestBody GetGeoCodesByDifficultyListRequest body ) throws InvalidRequestException {

        GetGeoCodesByDifficultyListResponse response = geoCodeService.getGeoCodesByDifficultyList( body );

        if ( response != null ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity< GetHintsResponse > getHints( @Parameter( in = ParameterIn.DEFAULT,
            description = "Request to get the hints from the specified GeoCode",
            required = true, schema = @Schema() )
                                                        @Valid @RequestBody GetHintsRequest body ) throws InvalidRequestException, RepoException {

        GetHintsResponse response = geoCodeService.getHints( body );

        if ( response != null ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity< SwapCollectablesResponse > swapCollectables( @Parameter( in = ParameterIn.DEFAULT,
            description = "Request to swap a GeoCode's Collectables",
            required = true, schema = @Schema() )
                                                                        @Valid @RequestBody SwapCollectablesRequest body ) throws InvalidRequestException {

        SwapCollectablesResponse response = geoCodeService.swapCollectables( body );

        if ( ( response != null ) && ( response.isIsSuccess() != null ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity< UpdateAvailabilityResponse > updateAvailability( @Parameter( in = ParameterIn.DEFAULT,
            description = "Request to update a GeoCode's availability",
            required = true, schema = @Schema() )
                                                                            @Valid @RequestBody UpdateAvailabilityRequest body ) throws InvalidRequestException {

        UpdateAvailabilityResponse response = geoCodeService.updateAvailability( body );

        if (  ( response != null ) && ( response.isIsSuccess() != null ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }

}
