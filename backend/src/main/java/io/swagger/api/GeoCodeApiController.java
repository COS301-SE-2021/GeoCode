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
import org.springframework.beans.factory.annotation.Autowired;
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
import tech.geocodeapp.geocode.Collectable.Response.CreateCollectableResponse;
import tech.geocodeapp.geocode.GeoCode.Exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.GeoCode.Exceptions.QRCodeException;
import tech.geocodeapp.geocode.GeoCode.Exceptions.RepoException;
import tech.geocodeapp.geocode.GeoCode.Service.GeoCodeService;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-02T03:21:48.298Z[GMT]")
@RestController
public class GeoCodeApiController implements GeoCodeApi {

    private static final Logger log = LoggerFactory.getLogger(GeoCodeApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private GeoCodeService geoCodeService;

    @org.springframework.beans.factory.annotation.Autowired
    public GeoCodeApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CreateGeoCodeResponse> createGeoCode(@Parameter(in = ParameterIn.DEFAULT, description = "Request to create a new GeoCode", required=true, schema=@Schema()) @Valid @RequestBody CreateGeoCodeRequest body) throws InvalidRequestException, QRCodeException, RepoException {

        CreateGeoCodeResponse response = geoCodeService.createGeoCode( body );

        if ( ( response.getGeoCode() != null ) && ( !response.getGeoCode().getQrCode().isEmpty() ) ) {

            return new ResponseEntity<CreateGeoCodeResponse>(response, HttpStatus.OK);
        }else{

            return new ResponseEntity<CreateGeoCodeResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<GetGeoCodeByLocationResponse> getGeoCodeByLocation(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a GeoCode at or near the given location", required=true, schema=@Schema()) @Valid @RequestBody GetGeoCodeByLocationRequest body) throws InvalidRequestException, RepoException {

        GetGeoCodeByLocationResponse response = geoCodeService.getGeoCodesByLocation( body );

        if ( ( response.getQrCode() != null ) || ( !response.getQrCode().isEmpty() ) ) {

            return new ResponseEntity<GetGeoCodeByLocationResponse>(response, HttpStatus.OK);
        }else{

            return new ResponseEntity<GetGeoCodeByLocationResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<GetGeoCodeByQRCodeResponse> getGeoCodeByQRCode(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a GeoCode's associated with the given QR Code", required=true, schema=@Schema()) @Valid @RequestBody GetGeoCodeByQRCodeRequest body) throws InvalidRequestException, RepoException {

        GetGeoCodeByQRCodeResponse response = geoCodeService.getGeocodeByQRCode( body );

        if ( ( response.getQrCode() != null ) || ( !response.getQrCode().isEmpty() ) ) {

            return new ResponseEntity<GetGeoCodeByQRCodeResponse>(response, HttpStatus.OK);
        }else{

            return new ResponseEntity<GetGeoCodeByQRCodeResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<GetCollectablesResponse> getGeoCodeCollectables(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a GeoCode's Collectables", required=true, schema=@Schema()) @Valid @RequestBody GetCollectablesRequest body) throws InvalidRequestException, RepoException {

        GetCollectablesResponse response = geoCodeService.getCollectables( body );

        if ( ( response.getCollectables() != null ) || ( !response.getCollectables().isEmpty() ) ) {

            return new ResponseEntity<GetCollectablesResponse>(response, HttpStatus.OK);
        }else{

            return new ResponseEntity<GetCollectablesResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<GetGeoCodesResponse> getGeoCodes() throws RepoException {

        GetGeoCodesResponse response = geoCodeService.getAllGeoCodes();

        if ( ( response.getGeocodes() != null ) || ( !response.getGeocodes().isEmpty() ) ) {

            return new ResponseEntity<GetGeoCodesResponse>(response, HttpStatus.OK);
        }else{

            return new ResponseEntity<GetGeoCodesResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<GetGeoCodesByDifficultyResponse> getGeoCodesByDifficulty(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get all the GeoCodes by the specified difficulty", required=true, schema=@Schema()) @Valid @RequestBody GetGeoCodesByDifficultyRequest body) throws InvalidRequestException, RepoException {

        GetGeoCodesByDifficultyResponse response = geoCodeService.getGeoCodesByDifficulty( body );

        if ( ( response.getGeocodes() != null ) || ( !response.getGeocodes().isEmpty() ) ) {

            return new ResponseEntity<GetGeoCodesByDifficultyResponse>(response, HttpStatus.OK);
        }else{

            return new ResponseEntity<GetGeoCodesByDifficultyResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<GetHintsResponse> getHints(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the hints from the specified GeoCode", required=true, schema=@Schema()) @Valid @RequestBody GetHintsRequest body) throws InvalidRequestException, RepoException {

        GetHintsResponse response = geoCodeService.getHints( body );

        if ( ( response.getHints() != null ) || ( !response.getHints().isEmpty() ) ) {

            return new ResponseEntity<GetHintsResponse>(response, HttpStatus.OK);
        }else{

            return new ResponseEntity<GetHintsResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<GetTrackablesResponse> getTrackables(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a GeoCode's Trackable", required=true, schema=@Schema()) @Valid @RequestBody GetTrackablesRequest body) throws InvalidRequestException, RepoException {

        GetTrackablesResponse response = geoCodeService.getTrackables( body );

        if ( ( response.getQrCode() != null ) || ( !response.getQrCode().isEmpty() ) ) {

            return new ResponseEntity<GetTrackablesResponse>(response, HttpStatus.OK);
        }else{

            return new ResponseEntity<GetTrackablesResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<SwapCollectablesResponse> swapCollectables(@Parameter(in = ParameterIn.DEFAULT, description = "Request to swap a GeoCode's Collectables", required=true, schema=@Schema()) @Valid @RequestBody SwapCollectablesRequest body) throws InvalidRequestException, RepoException {

        SwapCollectablesResponse response = geoCodeService.swapCollectables( body );

        if ( response.isSuccess() != null ) {

            return new ResponseEntity<SwapCollectablesResponse>(response, HttpStatus.OK);
        }else{

            return new ResponseEntity<SwapCollectablesResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<UpdateAvailabilityResponse> updateAvailability(@Parameter(in = ParameterIn.DEFAULT, description = "Request to update a GeoCode's availability", required=true, schema=@Schema()) @Valid @RequestBody UpdateAvailabilityRequest body) throws InvalidRequestException, RepoException {

        UpdateAvailabilityResponse response = geoCodeService.updateAvailability( body );

        if ( response.isSuccess() != null ) {

            return new ResponseEntity<UpdateAvailabilityResponse>(response, HttpStatus.OK);
        }else{

            return new ResponseEntity<UpdateAvailabilityResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
