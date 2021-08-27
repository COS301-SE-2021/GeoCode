package tech.geocodeapp.geocode.event.controller;

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

import tech.geocodeapp.geocode.event.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.event.response.*;
import tech.geocodeapp.geocode.event.request.*;

import javax.validation.Valid;

@Validated
public interface EventApi {

    @Operation( summary = "Changes an Event's availability", description = "Changes the specified Event's availability to the given availability", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "Event" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Returns the update of the availability is successfully", content = @Content( mediaType = "application/json", schema = @Schema( implementation = ChangeAvailabilityResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Unable to find an Event with the given id", content = @Content( mediaType = "application/json", schema = @Schema( implementation = ChangeAvailabilityResponse.class ) ) ) } )
    @PostMapping( value = "/Event/changeAvailability",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< ChangeAvailabilityResponse > changeAvailability( @Parameter( in = ParameterIn.DEFAULT, description = "Request to update the availability of an Event", required = true, schema = @Schema() ) @Valid @RequestBody ChangeAvailabilityRequest body );


    @Operation( summary = "Create a new Event", description = "Create a new Event in the system with the specified attributes", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "Event" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Return the new Event was successfully created", content = @Content( mediaType = "application/json", schema = @Schema( implementation = CreateEventResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the new Event was not successfully created", content = @Content( mediaType = "application/json", schema = @Schema( implementation = CreateEventResponse.class ) ) ) } )
    @PostMapping( value = "/Event/createEvent",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< CreateEventResponse > createEvent( @Parameter( in = ParameterIn.DEFAULT, description = "Request to create an Event", required = true, schema = @Schema() ) @Valid @RequestBody CreateEventRequest body ) throws InvalidRequestException;


    @Operation( summary = "Create a new Leaderboard for an Event", description = "Create a new Leaderboard for an Event", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "Event" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Return the Leaderboard was successfully created", content = @Content( mediaType = "application/json", schema = @Schema( implementation = CreateLeaderboardResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the Leaderboard was not successfully created", content = @Content( mediaType = "application/json", schema = @Schema( implementation = CreateLeaderboardResponse.class ) ) ) } )
    @PostMapping( value = "/Event/createLeaderBoard",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< CreateLeaderboardResponse > createLeaderBoard( @Parameter( in = ParameterIn.DEFAULT, description = "Request to create a new Leaderboard for an Event", required = true, schema = @Schema() ) @Valid @RequestBody CreateLeaderboardRequest body );


    @Operation(summary = "Get the current status of a User participating in an event, as well as the target GeoCode that they need to locate", description = "Get the current status of a User participating in an event, as well as the target GeoCode that they need to locate", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Event" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The user's event status was successfully found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetCurrentEventStatusResponse.class))),

            @ApiResponse(responseCode = "401", description = "Invalid JWT token"),

            @ApiResponse(responseCode = "404", description = "The user's event status was not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetCurrentEventStatusResponse.class))) })
    @PostMapping(value = "/Event/getCurrentEventStatus",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity<GetCurrentEventStatusResponse> getCurrentEventStatus(@Parameter(in = ParameterIn.DEFAULT, description = "Get the current status of a User participating in an event, as well as the target GeoCode that they need to locate", required=true, schema=@Schema()) @Valid @RequestBody GetCurrentEventStatusRequest body) throws InvalidRequestException;


    @Operation( summary = "Returns all the Events", description = "Returns all the Events in the system", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "Event" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Successfully returned all the Event's", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetAllEventsResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Unable to find any Events", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetAllEventsResponse.class ) ) ) } )
    @GetMapping( value = "/Event/getAllEvents",
            produces = { "application/json", "application/xml" } )
    ResponseEntity< GetAllEventsResponse > getAllEvents();


    @Operation(summary = "Returns all the Events that a User is participating in", description = "Returns all the Events that a User is participating in", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Event" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The events were successfully found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetEnteredEventsResponse.class))),

            @ApiResponse(responseCode = "401", description = "Invalid JWT token"),

            @ApiResponse(responseCode = "404", description = "The user was not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetEnteredEventsResponse.class))) })
    @PostMapping(value = "/Event/getEnteredEvents",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity<GetEnteredEventsResponse> getEnteredEvents(@Parameter(in = ParameterIn.DEFAULT, description = "Returns all the Events that a User is participating in", required=true, schema=@Schema()) @Valid @RequestBody GetEnteredEventsRequest body) throws InvalidRequestException;


    @Operation( summary = "Retrieve a list of Events around a certain radius of a location", description = "Retrieve a list of Events around a certain radius of a location", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "Event" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Return the found Events", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetEventsByLocationResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the new Event was not found", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetEventsByLocationResponse.class ) ) ) } )
    @PostMapping( value = "/Event/getEventsNearMe",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< EventsNearMeResponse > getEventsNearMe( @Parameter( in = ParameterIn.DEFAULT, description = "Retrieve a list of Events around a certain radius of a location", required = true, schema = @Schema() ) @Valid @RequestBody EventsNearMeRequest body ) throws InvalidRequestException;

    @Operation(summary = "Get a specified Event that is stored in the repository", description = "Get a specified Event that is stored in the repository", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Event" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return the Event was successfully found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetEventResponse.class))),

            @ApiResponse(responseCode = "401", description = "Invalid JWT token"),

            @ApiResponse(responseCode = "404", description = "Return the specified Event was not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetEventResponse.class))) })
    @PostMapping(value = "/Event/getEvent",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity<GetEventResponse> getEvent(@Parameter(in = ParameterIn.DEFAULT, description = "Get a specified Event that is stored in the repository", required=true, schema=@Schema()) @Valid @RequestBody GetEventRequest body) throws InvalidRequestException;
}

