package tech.geocodeapp.geocode.leaderboard.controller;

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
import tech.geocodeapp.geocode.leaderboard.request.*;
import tech.geocodeapp.geocode.leaderboard.response.*;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-05T10:13:51.670Z[GMT]")
@Validated
@CrossOrigin(origins = "${web_referrer}", maxAge = 3600)
public interface LeaderboardApi {

    @Operation(summary = "Create a new Leaderboard", description = "Creates a new Leaderboard with the provided name", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Leaderboard" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully created the Leaderboard", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateLeaderboardResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/Leaderboard/createLeaderboard",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<CreateLeaderboardResponse> createLeaderboard(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateLeaderboardRequest body);


    @Operation(summary = "Create a new point object", description = "Creates a new point object for a provided user and leaderboard", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Leaderboard" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully created a new point", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PointResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/Leaderboard/createPoint",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<PointResponse> createPoint(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreatePointRequest body);


    @Operation(summary = "Delete a point", description = "Deletes a point based on a provided id", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Leaderboard" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully deleted a point", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeletePointResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/Leaderboard/deletePoint",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<DeletePointResponse> deletePoint(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody DeletePointRequest body);


    @Operation(summary = "Get an Event's Leaderboard details", description = "Returns the Event's Leaderboard details from a specific rank down for a specified number of users", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Leaderboard" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully returned the Event's Leaderboard details", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetEventLeaderboardResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token"),
        
        @ApiResponse(responseCode = "404", description = "Unable to find CollectableTypes of given set", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetEventLeaderboardResponse.class))) })
    @RequestMapping(value = "/Leaderboard/getEventLeaderboard",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetEventLeaderboardResponse> getEventLeaderboard(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a subset of the Event's Leaderboard details", required=true, schema=@Schema()) @Valid @RequestBody GetEventLeaderboardRequest body);


    @Operation(summary = "Get a Leaderboard", description = "Get a Leaderboard by its ID", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Leaderboard" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully returned the Leaderboard", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetLeaderboardByIDResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token"),
        
        @ApiResponse(responseCode = "404", description = "Unable to find the Leaderboard", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetLeaderboardByIDResponse.class))) })
    @RequestMapping(value = "/Leaderboard/getLeaderboardByID",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetLeaderboardByIDResponse> getLeaderboardByID(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a Leaderboard", required=true, schema=@Schema()) @Valid @RequestBody GetLeaderboardByIDRequest body);


    @Operation(summary = "Get the User's points for a certain Leaderboard", description = "Get the User's points for a certain Leaderboard", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Leaderboard" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully returned the User's Point", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PointResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token"),
        
        @ApiResponse(responseCode = "404", description = "Unable to find a Point for the User", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PointResponse.class))) })
    @RequestMapping(value = "/Leaderboard/getPointForUser",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<PointResponse> getPointForUser(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get a subset of the Event's Leaderboard details", required=true, schema=@Schema()) @Valid @RequestBody GetPointForUserRequest body);


    @Operation(summary = "Updates a point", description = "Updates the fields of a point with values provided for the given id", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Leaderboard" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully Updated point", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PointResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/Leaderboard/updatePoint",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<PointResponse> updatePoint(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody UpdatePointRequest body);

}

