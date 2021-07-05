package tech.geocodeapp.geocode.leaderboard.controller;

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
import tech.geocodeapp.geocode.leaderboard.request.GetEventLeaderboardRequest;
import tech.geocodeapp.geocode.leaderboard.response.GetEventLeaderboardResponse;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-05T09:14:58.803Z[GMT]")
@Validated
public interface LeaderboardApi {

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

}

