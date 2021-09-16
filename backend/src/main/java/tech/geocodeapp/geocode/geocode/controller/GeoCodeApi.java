package tech.geocodeapp.geocode.geocode.controller;

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
import org.springframework.web.bind.annotation.*;
import tech.geocodeapp.geocode.geocode.exceptions.*;
import javax.validation.Valid;

import tech.geocodeapp.geocode.geocode.response.*;
import tech.geocodeapp.geocode.geocode.request.*;


@Validated
public interface GeoCodeApi {

    @Operation( summary = "Creates a new GeoCode", description = "Create GeoCode", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Create GeoCode Response", content = @Content( mediaType = "application/json", schema = @Schema( implementation = CreateGeoCodeResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the GeoCode could not be created", content = @Content( mediaType = "application/json", schema = @Schema( implementation = CreateGeoCodeResponse.class ) ) ) } )
    @PostMapping( value = "/GeoCode/createGeoCode",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< CreateGeoCodeResponse > createGeoCode( @Parameter( in = ParameterIn.DEFAULT, description = "Request to create a new GeoCode", required = true, schema = @Schema() ) @Valid @RequestBody CreateGeoCodeRequest body ) throws InvalidRequestException;

    @Operation( summary = "Updated a GeoCode", description = "Update GeoCode", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Update GeoCode Response", content = @Content( mediaType = "application/json", schema = @Schema( implementation = UpdateGeoCodeResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the GeoCode could not be UPDATED", content = @Content( mediaType = "application/json", schema = @Schema( implementation = UpdateGeoCodeResponse.class ) ) ) } )
    @PostMapping( value = "/GeoCode/updateGeoCode",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< UpdateGeoCodeResponse > updateGeoCode( @Parameter( in = ParameterIn.DEFAULT, description = "Request to create a new GeoCode", required = true, schema = @Schema() ) @Valid @RequestBody UpdateGeoCodeRequest body ) throws InvalidRequestException;

    @Operation( summary = "Get the GeoCode's collectables associated with the given QR Code", description = "Get the GeoCode's collectables associated with the given QR Code", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Returned the associated GeoCode successfully", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetCollectablesInGeoCodeByQRCodeResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the GeoCode's collectables was not found", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetCollectablesInGeoCodeByQRCodeResponse.class ) ) ) } )
    @PostMapping( value = "/GeoCode/getCollectablesInGeoCodeByQRCode",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< GetCollectablesInGeoCodeByQRCodeResponse > getCollectablesInGeoCodeByQRCode( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get a GeoCode's collectables associated with the given QR Code", required = true, schema = @Schema() ) @Valid @RequestBody GetCollectablesInGeoCodeByQRCodeRequest body ) throws InvalidRequestException;


    @Operation( summary = "Get the GeoCode's collectables at or near the given location", description = "Get the GeoCode's collectables at or near the given location", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Return the GeoCodec at or near the given location", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetCollectablesInGeoCodesByLocationResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the GeoCode's collectables was not found", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetCollectablesInGeoCodesByLocationResponse.class ) ) ) } )
    @PostMapping( value = "/GeoCode/getCollectablesInGeoCodeByLocation",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity<GetCollectablesInGeoCodesByLocationResponse> getCollectablesInGeoCodeByLocation(@Parameter( in = ParameterIn.DEFAULT, description = "Request to get a GeoCode's collectables at or near the given location", required = true, schema = @Schema() ) @Valid @RequestBody GetCollectablesInGeoCodesByLocationRequest body ) throws InvalidRequestException;


    @Operation( summary = "Get a specific GeoCode", description = "Get a GeoCode with a specified ID", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Successfully found the GeoCode with the given ID", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetGeoCodeResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Could not find the GeoCode with the given ID", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetGeoCodeResponse.class ) ) ) } )
    @PostMapping( value = "/GeoCode/getGeoCode",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< GetGeoCodeResponse > getGeoCode( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get a stored GeoCode with teh specified ID", required = true, schema = @Schema() ) @Valid @RequestBody GetGeoCodeRequest body ) throws InvalidRequestException;


    @Operation( summary = "Get the GeoCode at or near the given location", description = "Get the GeoCode at or near the given location", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Return the GeoCodec at or near the given location", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetGeoCodesByLocationResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the GeoCode was not found", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetGeoCodesByLocationResponse.class ) ) ) } )
    @PostMapping( value = "/GeoCode/getGeoCodeByLocation",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity<GetGeoCodesByLocationResponse> getGeoCodeByLocation(@Parameter( in = ParameterIn.DEFAULT, description = "Request to get a GeoCode at or near the given location", required = true, schema = @Schema() ) @Valid @RequestBody GetGeoCodesByLocationRequest body ) throws InvalidRequestException;


    @Operation( summary = "Get the GeoCode associated with the given QR Code", description = "Get the GeoCode associated with the given QR Code", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Returned the associated GeoCode successfully", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetGeoCodeByQRCodeResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the GeoCode was not found", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetGeoCodeByQRCodeResponse.class ) ) ) } )
    @PostMapping( value = "/GeoCode/getGeoCodeByQRCode",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< GetGeoCodeByQRCodeResponse > getGeoCodeByQRCode( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get a GeoCode associated with the given QR Code", required = true, schema = @Schema() ) @Valid @RequestBody GetGeoCodeByQRCodeRequest body ) throws InvalidRequestException;


    @Operation( summary = "Get all Collectables for a certain GeoCode", description = "Get a GeoCode's Collectables", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Returned the GeoCode's Collectables successfully", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetCollectablesResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the Collectables for a GeoCode was not found", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetCollectablesResponse.class ) ) ) } )
    @PostMapping( value = "/GeoCode/getCollectables",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< GetCollectablesResponse > getGeoCodeCollectables( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get a GeoCode's Collectables", required = true, schema = @Schema() ) @Valid @RequestBody GetCollectablesRequest body ) throws InvalidRequestException;


    @Operation( summary = "Get all the GeoCodes on the platform", description = "Get all the GeoCodes that are stored on the platform", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Returned all the GeoCodes successfully", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetGeoCodesResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return GeoCodes were not found", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetGeoCodesResponse.class ) ) ) } )
    @GetMapping( value = "/GeoCode/getAllGeoCodes",
            produces = { "application/json", "application/xml" } )
    ResponseEntity< GetGeoCodesResponse > getGeoCodes();


    @Operation( summary = "Get all all the stored GeoCodes by the specified difficulty", description = "Get all GeoCodes by difficulty", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Returned all the GeoCode's with the specified difficulty", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetGeoCodesByDifficultyResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the new Event was not successfully created", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetGeoCodesByDifficultyResponse.class ) ) ) } )
    @PostMapping( value = "/GeoCode/getGeoCodesByDifficulty",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< GetGeoCodesByDifficultyResponse > getGeoCodesByDifficulty( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get all the GeoCodes by the specified difficulty", required = true, schema = @Schema() ) @Valid @RequestBody GetGeoCodesByDifficultyRequest body ) throws InvalidRequestException;


    @Operation( summary = "Get all all the stored GeoCodes by the specified difficulties", description = "Get all GeoCodes by difficulty", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Returned all the GeoCode's with the specified difficulties", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetGeoCodesByDifficultyListResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Could not return GeoCode's with the specified difficulties", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetGeoCodesByDifficultyListResponse.class ) ) ) } )
    @PostMapping( value = "/GeoCode/getGeoCodesByDifficultyList",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< GetGeoCodesByDifficultyListResponse > getGeoCodesByDifficultyList( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get all the GeoCodes by the specified difficulties", required = true, schema = @Schema() ) @Valid @RequestBody GetGeoCodesByDifficultyListRequest body ) throws InvalidRequestException;


    @Operation( summary = "Get the hints for the specified GeoCode", description = "Get the hints for the specified GeoCode to help locate it", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Returned the hints for the GeoCode successfully", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetHintsResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the hints for the GeoCode was not found", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetHintsResponse.class ) ) ) } )
    @PostMapping( value = "/GeoCode/getHints",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< GetHintsResponse > getHints( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get the hints from the specified GeoCode", required = true, schema = @Schema() ) @Valid @RequestBody GetHintsRequest body ) throws InvalidRequestException, RepoException;


    @Operation( summary = "Swap a specific GeoCode's Collectable", description = "Swap a specific GeoCode's Collectable", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Swapped the GeoCode's Collectable successfully", content = @Content( mediaType = "application/json", schema = @Schema( implementation = SwapCollectablesResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the GeoCode did not swap collectables successfully", content = @Content( mediaType = "application/json", schema = @Schema( implementation = SwapCollectablesResponse.class ) ) ) } )
    @PostMapping( value = "/GeoCode/swapCollectables",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< SwapCollectablesResponse > swapCollectables( @Parameter( in = ParameterIn.DEFAULT, description = "Request to swap a GeoCode's Collectables", required = true, schema = @Schema() ) @Valid @RequestBody SwapCollectablesRequest body ) throws InvalidRequestException;


    @Operation( summary = "Update the availability for a certain GeoCode", description = "Update the availability for a certain GeoCode", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Updated the GeoCode's availability successfully", content = @Content( mediaType = "application/json", schema = @Schema( implementation = UpdateAvailabilityResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the GeoCode Availability was not updated successfully", content = @Content( mediaType = "application/json", schema = @Schema( implementation = UpdateAvailabilityResponse.class ) ) ) } )
    @PostMapping( value = "/GeoCode/updateAvailability",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< UpdateAvailabilityResponse > updateAvailability( @Parameter( in = ParameterIn.DEFAULT, description = "Request to update a GeoCode's availability", required = true, schema = @Schema() ) @Valid @RequestBody UpdateAvailabilityRequest body ) throws InvalidRequestException;

}