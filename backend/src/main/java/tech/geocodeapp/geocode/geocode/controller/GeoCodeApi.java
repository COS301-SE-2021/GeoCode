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
@CrossOrigin(origins = "${web_referrer}", maxAge = 3600)
public interface GeoCodeApi {

    @Operation( summary = "Creates a new GeoCode", description = "Create GeoCode", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Create GeoCode Response", content = @Content( mediaType = "application/json", schema = @Schema( implementation = CreateGeoCodeResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ) } )
    @PostMapping( value = "/GeoCode/createGeoCode",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< CreateGeoCodeResponse > createGeoCode( @Parameter( in = ParameterIn.DEFAULT, description = "Request to create a new GeoCode", required = true, schema = @Schema() ) @Valid @RequestBody CreateGeoCodeRequest body ) throws InvalidRequestException, QRCodeException, RepoException;


    @Operation( summary = "Get the GeoCode at or near the given location", description = "Get the GeoCode at or near the given location", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Return the GeoCodec at or near the given location", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetGeoCodeByLocationResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ) } )
    @PostMapping( value = "/GeoCode/getGeoCodeByLocation",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< GetGeoCodeByLocationResponse > getGeoCodeByLocation( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get a GeoCode at or near the given location", required = true, schema = @Schema() ) @Valid @RequestBody GetGeoCodeByLocationRequest body ) throws InvalidRequestException, RepoException;


    @Operation( summary = "Get the GeoCode associated with the given QR Code", description = "Get the GeoCode associated with the given QR Code", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Returned the associated GeoCode successfully", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetGeoCodeByQRCodeResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ) } )
    @PostMapping( value = "/GeoCode/getGeoCodeByQRCode",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< GetGeoCodeByQRCodeResponse > getGeoCodeByQRCode( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get a GeoCode's associated with the given QR Code", required = true, schema = @Schema() ) @Valid @RequestBody GetGeoCodeByQRCodeRequest body ) throws InvalidRequestException, RepoException;


    @Operation( summary = "Get all Collectables for a certain GeoCode", description = "Get a GeoCode's Collectables", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Returned the GeoCode's Collectables successfully", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetCollectablesResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ) } )
    @PostMapping( value = "/GeoCode/getCollectables",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< GetCollectablesResponse > getGeoCodeCollectables( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get a GeoCode's Collectables", required = true, schema = @Schema() ) @Valid @RequestBody GetCollectablesRequest body ) throws InvalidRequestException, RepoException;


    @Operation( summary = "Get all the GeoCodes on the platform", description = "Get all the GeoCodes that are stored on the platform", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Returned all the GeoCodes successfully", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetGeoCodesResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ) } )
    @GetMapping( value = "/GeoCode/getGeoCodes",
            produces = { "application/json", "application/xml" } )
    ResponseEntity< GetGeoCodesResponse > getGeoCodes() throws RepoException;


    @Operation( summary = "Get all all the stored GeoCodes by the specified difficulty", description = "Get all GeoCodes by difficulty", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Returned all the GeoCode's with the specified difficulty", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetGeoCodesByDifficultyResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ) } )
    @PostMapping( value = "/GeoCode/getGeoCodesByDifficulty",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< GetGeoCodesByDifficultyResponse > getGeoCodesByDifficulty( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get all the GeoCodes by the specified difficulty", required = true, schema = @Schema() ) @Valid @RequestBody GetGeoCodesByDifficultyRequest body ) throws InvalidRequestException, RepoException;


    @Operation( summary = "Get the hints for the specified GeoCode", description = "Get the hints for the specified GeoCode to help locate it", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Returned the hints for the GeoCode successfully", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetHintsResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ) } )
    @PostMapping( value = "/GeoCode/getHints",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< GetHintsResponse > getHints( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get the hints from the specified GeoCode", required = true, schema = @Schema() ) @Valid @RequestBody GetHintsRequest body ) throws InvalidRequestException, RepoException;

    @Operation( summary = "Swap a specific GeoCode's Collectable", description = "Swap a specific GeoCode's Collectable", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Swapped the GeoCode's Collectable successfully", content = @Content( mediaType = "application/json", schema = @Schema( implementation = SwapCollectablesResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ) } )
    @PostMapping( value = "/GeoCode/swapCollectables",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< SwapCollectablesResponse > swapCollectables( @Parameter( in = ParameterIn.DEFAULT, description = "Request to swap a GeoCode's Collectables", required = true, schema = @Schema() ) @Valid @RequestBody SwapCollectablesRequest body ) throws InvalidRequestException, RepoException;


    @Operation( summary = "Update the availability for a certain GeoCode", description = "Update the availability for a certain GeoCode", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "GeoCode" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Updated the GeoCode's availability successfully", content = @Content( mediaType = "application/json", schema = @Schema( implementation = UpdateAvailabilityResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ) } )
    @PostMapping( value = "/GeoCode/updateAvailability",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< UpdateAvailabilityResponse > updateAvailability( @Parameter( in = ParameterIn.DEFAULT, description = "Request to update a GeoCode's availability", required = true, schema = @Schema() ) @Valid @RequestBody UpdateAvailabilityRequest body ) throws InvalidRequestException, RepoException;

}