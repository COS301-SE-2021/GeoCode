package tech.geocodeapp.geocode.GeoCode.Controller;

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
import io.swagger.model.GetGeoCodesRequest;
import io.swagger.model.GetGeoCodesResponse;
import io.swagger.model.GetHintsRequest;
import io.swagger.model.GetHintsResponse;
import io.swagger.model.GetTrackablesRequest;
import io.swagger.model.GetTrackablesResponse;
import io.swagger.model.SwapCollectablesRequest;
import io.swagger.model.SwapCollectablesResponse;
import io.swagger.model.UpdateAvailabilityRequest;
import io.swagger.model.UpdateAvailabilityResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-01T18:53:58.734Z[GMT]")
@Validated
public interface GeoCodeApi {

    @Operation(summary = "Creates a new GeoCode", description = "Create GeoCode", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "GeoCode" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Create GeoCode Response", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateGeoCodeResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/GeoCode/createGeoCode",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<CreateGeoCodeResponse> createGeoCode(@Parameter(in = ParameterIn.DEFAULT, description = "Request to create a new GeoCode", required=true, schema=@Schema()) @Valid @RequestBody CreateGeoCodeRequest body);


    @Operation(summary = "Get the GeoCode at or near the given location", description = "Get the GeoCode at or near the given location", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "GeoCode" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Return the GeoCodec at or near the given location", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetGeoCodeByLocationResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/GeoCode/getGeoCodeByLocation",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetGeoCodeByLocationResponse> getGeoCodeByLocation(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a GeoCode at or near the given location", required=true, schema=@Schema()) @Valid @RequestBody GetGeoCodeByLocationRequest body);


    @Operation(summary = "Get the GeoCode associated with the given QR Code", description = "Get the GeoCode associated with the given QR Code", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "GeoCode" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Returned the associated GeoCode successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetGeoCodeByQRCodeResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/GeoCode/getGeoCodeByQRCode",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetGeoCodeByQRCodeResponse> getGeoCodeByQRCode(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a GeoCode's associated with the given QR Code", required=true, schema=@Schema()) @Valid @RequestBody GetGeoCodeByQRCodeRequest body);


    @Operation(summary = "Get all Collectables for a certain GeoCode", description = "Get a GeoCode's Collectables", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "GeoCode" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Returned the GeoCode's Collectables successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetCollectablesResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/GeoCode/getCollectables",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetCollectablesResponse> getGeoCodeCollectables(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a GeoCode's Collectables", required=true, schema=@Schema()) @Valid @RequestBody GetCollectablesRequest body);


    @Operation(summary = "Get all the GeoCode's on the platform", description = "Get all the GeoCode's that is stored on the platform", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "GeoCode" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Returned all the GeoCode's successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetGeoCodesResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/GeoCode/getGeoCodes",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetGeoCodesResponse> getGeoCodes(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get all the GeoCode's", required=true, schema=@Schema()) @Valid @RequestBody GetGeoCodesRequest body);


    @Operation(summary = "Get all all the stored GeoCodes by the specified difficulty", description = "Get all GeoCodes by difficulty", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "GeoCode" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Returned all the GeoCode's with the specified difficulty", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetGeoCodesByDifficultyResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/GeoCode/getGeoCodesByDifficulty",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetGeoCodesByDifficultyResponse> getGeoCodesByDifficulty(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get all the GeoCodes by the specified difficulty", required=true, schema=@Schema()) @Valid @RequestBody GetGeoCodesByDifficultyRequest body);


    @Operation(summary = "Get the hints for the specified GeoCode", description = "Get the hints for the specified GeoCode to help locate it", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "GeoCode" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Returned the hints for the GeoCode successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetHintsResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/GeoCode/getHints",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetHintsResponse> getHints(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the hints from the specified GeoCode", required=true, schema=@Schema()) @Valid @RequestBody GetHintsRequest body);


    @Operation(summary = "Get the Trackable for a certain GeoCode", description = "Get a GeoCode's Trackable", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "GeoCode" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Returned the GeoCode's Trackable successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetTrackablesResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/GeoCode/getTrackables",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetTrackablesResponse> getTrackables(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a GeoCode's Trackable", required=true, schema=@Schema()) @Valid @RequestBody GetTrackablesRequest body);


    @Operation(summary = "Swap a specific GeoCode's Collectable", description = "Swap a specific GeoCode's Collectable", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "GeoCode" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Swapped the GeoCode's Collectable successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SwapCollectablesResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/GeoCode/swapCollectables",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<SwapCollectablesResponse> swapCollectables(@Parameter(in = ParameterIn.DEFAULT, description = "Request to swap a GeoCode's Collectables", required=true, schema=@Schema()) @Valid @RequestBody SwapCollectablesRequest body);


    @Operation(summary = "Update the availability for a certain GeoCode", description = "Update the availability for a certain GeoCode", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "GeoCode" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Updated the GeoCode's availability successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateAvailabilityResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/GeoCode/updateAvailability",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<UpdateAvailabilityResponse> updateAvailability(@Parameter(in = ParameterIn.DEFAULT, description = "Request to update a GeoCode's availability", required=true, schema=@Schema()) @Valid @RequestBody UpdateAvailabilityRequest body);

}

