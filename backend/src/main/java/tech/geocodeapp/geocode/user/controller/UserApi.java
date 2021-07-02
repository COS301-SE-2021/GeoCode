package tech.geocodeapp.geocode.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tech.geocodeapp.geocode.user.request.*;
import tech.geocodeapp.geocode.user.response.*;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-09T21:02:56.988Z[GMT]")
@Validated
@CrossOrigin(origins = "${web_referrer}", maxAge = 3600)
public interface UserApi {

    @Operation(summary = "Get the Collectable the User is currently holding", description = "Get the user's current Collectable", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Create GeoCode Response", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetCurrentCollectableResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/User/getCurrentCollectable",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetCurrentCollectableResponse> getCurrentCollectable(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user's current Collectable", required = true, schema = @Schema()) @Valid @RequestBody GetCurrentCollectableRequest body);

    @Operation(summary = "Gets the user's trackable", description = "Get the given user's trackable", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The user's trackable was successfully returned", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetUserTrackableResponse.class))),

            @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/User/getUserTrackable",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" },
            method = RequestMethod.POST)
    ResponseEntity<GetUserTrackableResponse> getUserTrackable(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user's trackable", required=true, schema=@Schema()) @RequestBody GetUserTrackableRequest body);

    @Operation(summary = "Update the location of the user's trackable", description = "Update the location of the user's trackable when they place it", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The user's trackable had it's location successfully updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateLocationResponse.class))),

            @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/User/updateLocation",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" },
            method = RequestMethod.POST)
    ResponseEntity<UpdateLocationResponse> updateLocation(@Parameter(in = ParameterIn.DEFAULT, description = "Request to update the location of the user's trackable", required=true, schema=@Schema()) @Valid @RequestBody UpdateLocationRequest body);

    @Operation(summary = "Gets the Collectables that the user has ever found", description = "Get a user's found Collectables", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully returned the user's found collectables", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetFoundCollectablesResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/User/getFoundCollectables",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetFoundCollectablesResponse> getFoundCollectables(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user's found Collectables", required = true, schema = @Schema()) @Valid @RequestBody GetFoundCollectablesRequest body);


    @Operation(summary = "Gets the GeoCodes that the user has ever found", description = "Gets the user's found GeoCodes", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully returned all owned GeoCodes for the user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetFoundGeoCodesResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/User/getFoundGeoCodes",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetFoundGeoCodesResponse> getFoundGeoCodes(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user's found GeoCodes", required = true, schema = @Schema()) @Valid @RequestBody GetFoundGeoCodesRequest body);


    @Operation(summary = "Gets the user's owned GeoCodes", description = "Get's the user's owned GeoCodes", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully returned all owned GeoCodes for the user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetOwnedGeoCodesResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/User/getOwnedGeoCodes",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetOwnedGeoCodesResponse> getOwnedGeoCodes(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user's owned GeoCodes", required = true, schema = @Schema()) @Valid @RequestBody GetOwnedGeoCodesRequest body);


    @Operation(summary = "Get all geocodes associated with a user", description = "Get My Geocodes", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully gotten geocodes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetOwnedGeoCodesResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/User/getOwnedGeocodes",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetOwnedGeoCodesResponse> getOwnedGeocodes(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get geocodes belonging to user", required = true, schema = @Schema()) @Valid @RequestBody GetOwnedGeoCodesRequest body);


    @Operation(summary = "Get all of the users in the system", description = "Get all of the users", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully returned all of the users", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetUsersResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/User/getUsers",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetUsersResponse> getUsers(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get all users in the system", required = true, schema = @Schema()) @Valid @RequestBody GetUsersRequest body);
}
