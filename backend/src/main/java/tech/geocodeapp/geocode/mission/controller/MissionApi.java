package tech.geocodeapp.geocode.mission.controller;

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
import tech.geocodeapp.geocode.mission.request.*;
import tech.geocodeapp.geocode.mission.response.*;

import javax.validation.Valid;

@Validated
public interface MissionApi {

    @Operation(summary = "Gets a Mission", description = "Gets a Mission specified by the given ID", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Mission" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Mission returned", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetMissionByIdResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/Mission/getMissionById",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetMissionByIdResponse> getMissionById(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody GetMissionByIdRequest body);


    @Operation(summary = "Gets the progress for a Mission", description = "Gets the progress for a Mission specified by the given ID", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Mission" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Mission returned", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetProgressResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/Mission/getProgress",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetProgressResponse> getProgress(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody GetProgressRequest body);

    @Operation(summary = "Creates a Mission", description = "Creates the Mission for a Collectable that has the Mission Type of the Collectable's Collectable Type", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Mission" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mission returned", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateMissionResponse.class))),

            @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/Mission/createMission",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" },
            method = RequestMethod.POST)
    ResponseEntity<CreateMissionResponse> createMission(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateMissionRequest body);
}

