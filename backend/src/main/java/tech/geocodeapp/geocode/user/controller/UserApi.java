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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.user.request.*;
import tech.geocodeapp.geocode.user.response.*;

import javax.validation.Valid;

@Validated
public interface UserApi {

    @Operation(summary = "Get the User with the specified ID", description = "Get the User with the specified ID", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create GeoCode Response", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetUserByIdResponse.class))),

            @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/User/getUserById",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" },
            method = RequestMethod.POST)
    ResponseEntity<GetUserByIdResponse> getUserById(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user", required=true, schema=@Schema()) @Valid @RequestBody GetUserByIdRequest body);

    @Operation(summary = "Get the Collectable the User is currently holding", description = "Get the user's current Collectable", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create GeoCode Response", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetCurrentCollectableResponse.class))),

            @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/User/getCurrentCollectable",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" },
            method = RequestMethod.POST)
    ResponseEntity<GetCurrentCollectableResponse> getCurrentCollectable(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user's current Collectable", required=true, schema=@Schema()) @Valid @RequestBody GetCurrentCollectableRequest body);

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

    @Operation(summary = "Gets the Collectable Types that the user has ever found", description = "Get a user's found Collectable Types", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned the user's found collectables", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetFoundCollectableTypesResponse.class))),

            @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/User/getFoundCollectableTypes",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" },
            method = RequestMethod.POST)
    ResponseEntity<GetFoundCollectableTypesResponse> getFoundCollectableTypes(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the IDs of the user's found Collectable Types", required=true, schema=@Schema()) @Valid @RequestBody GetFoundCollectableTypesRequest body);


    @Operation(summary = "Gets the GeoCodes that the user has ever found", description = "Gets the user's found GeoCodes", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned all owned GeoCodes for the user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetFoundGeoCodesResponse.class))),

            @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/User/getFoundGeoCodes",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" },
            method = RequestMethod.POST)
    ResponseEntity<GetFoundGeoCodesResponse> getFoundGeoCodes(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user's found GeoCodes", required=true, schema=@Schema()) @Valid @RequestBody GetFoundGeoCodesRequest body);


    @Operation(summary = "Gets the user's owned GeoCodes", description = "Get's the user's owned GeoCodes", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned all owned GeoCodes for the user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetOwnedGeoCodesResponse.class))),

            @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/User/getOwnedGeoCodes",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" },
            method = RequestMethod.POST)
    ResponseEntity<GetOwnedGeoCodesResponse> getOwnedGeoCodes(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the user's owned GeoCodes", required=true, schema=@Schema()) @Valid @RequestBody GetOwnedGeoCodesRequest body);

    @Operation(summary = "Gets the User's Leaderboard rankings", description = "Gets all the points and ranking for the Leaderboards that the given User is on", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "getMyLeaderboards Response", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetMyLeaderboardsResponse.class))),

            @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/User/getMyLeaderboards",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" },
            method = RequestMethod.POST)
    ResponseEntity<GetMyLeaderboardsResponse> getMyLeaderboards(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the name, points and ranking for all of the Leaderboards that a User is on", required=true, schema=@Schema()) @Valid @RequestBody GetMyLeaderboardsRequest body);

    @Operation(summary = "Gets the Missions for a User", description = "Gets the Missions that a User has been involved in the past", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The User's Missions were returned", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetMyMissionsResponse.class))),

            @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/User/getMyMissions",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" },
            method = RequestMethod.POST)
    ResponseEntity<GetMyMissionsResponse> getMyMissions(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the User's Missions", required=true, schema=@Schema()) @Valid @RequestBody GetMyMissionsRequest body);

    @Operation(summary = "Handles login of Users", description = "Handles login of Users", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Response for whether the User registration was successful", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))),

            @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/User/handleLogin",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" },
            method = RequestMethod.POST)
    ResponseEntity<Response> handleLogin(@Parameter(in = ParameterIn.DEFAULT, description = "Handles login of Users", required=true, schema=@Schema()) @Valid @RequestBody HandleLoginRequest body);

}

