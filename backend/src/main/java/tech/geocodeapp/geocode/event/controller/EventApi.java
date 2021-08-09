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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import tech.geocodeapp.geocode.event.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.event.response.*;
import tech.geocodeapp.geocode.event.request.*;

import javax.validation.Valid;

@Validated
@CrossOrigin(origins = "${web_referrer}", maxAge = 3600)
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


    @Operation( summary = "Create a new Point for an Event", description = "Create a new Point with specific values for an Event", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "Event" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Return the new new Point for the Event was successfully created", content = @Content( mediaType = "application/json", schema = @Schema( implementation = CreatePointResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the new new Point for the Event was not successfully created", content = @Content( mediaType = "application/json", schema = @Schema( implementation = CreatePointResponse.class ) ) ) } )
    @PostMapping( value = "/Event/createPoint",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< CreatePointResponse > createPoint( @Parameter( in = ParameterIn.DEFAULT, description = "Request to create a new Point for an Event", required = true, schema = @Schema() ) @Valid @RequestBody CreatePointRequest body );


    @Operation( summary = "Create a new Time Trial for an Event", description = "Create a new Time Trial with a specific timeframe for an Event", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "Event" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Return the Time Trial for the Event was successfully created", content = @Content( mediaType = "application/json", schema = @Schema( implementation = CreateTimeTrialResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the Time Trial for the Event was not successfully created", content = @Content( mediaType = "application/json", schema = @Schema( implementation = CreateTimeTrialResponse.class ) ) ) } )
    @PostMapping( value = "/Event/createTimeTrial",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< CreateTimeTrialResponse > createTimeTrial( @Parameter( in = ParameterIn.DEFAULT, description = "Request to create a new Time Trial for an Event", required = true, schema = @Schema() ) @Valid @RequestBody CreateTimeTrialRequest body );


    @Operation( summary = "Returns all the Events", description = "Returns all the Events in the system", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "Event" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Successfully returned all the Event's", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetAllEventsResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Unable to find any Events", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetAllEventsResponse.class ) ) ) } )
    @GetMapping( value = "/Event/getAllEvents",
            produces = { "application/json", "application/xml" } )
    ResponseEntity< GetAllEventsResponse > getAllEvents();


    @Operation( summary = "Get an Event by its location", description = "Get an Event by its location from the given co-ordinates", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "Event" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Return the found Event", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetEventsByLocationResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the new Event was not found", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetEventsByLocationResponse.class ) ) ) } )
    @PostMapping( value = "/Event/getEventsByLocation",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< GetEventsByLocationResponse > getEventsByLocation( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get an Event by its location", required = true, schema = @Schema() ) @Valid @RequestBody GetEventsByLocationRequest body ) throws InvalidRequestException;


    @Operation( summary = "Get the Leaderboard for a TimeTrial", description = "Get the Leaderboard for a TimeTrial", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "Event" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Successfully return the Leaderboard", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetLeaderBoardByTimeTrialResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the Leaderboard was not successfully found", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetLeaderBoardByTimeTrialResponse.class ) ) ) } )
    @PostMapping( value = "/Event/getLeaderBoardByTimeTrial",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< GetLeaderBoardByTimeTrialResponse > getLeaderBoardByTimeTrial( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get the Leaderboard for a TimeTrial", required = true, schema = @Schema() ) @Valid @RequestBody GetLeaderBoardByTimeTrialRequest body );


    @Operation( summary = "Get the Points for an Event", description = "Get the Points for an Event", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "Event" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Return the Points for an Event", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetPointsResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return that the Points for an Event could not be found", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetPointsResponse.class ) ) ) } )
    @GetMapping( value = "/Event/getPoints",
            produces = { "application/json", "application/xml" } )
    ResponseEntity< GetPointsResponse > getPoints();


    @Operation( summary = "Get Points for a Leaderboard for an Event", description = "Get Points for a Leaderboard for an Event", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "Event" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Return the Points of a Leaderboard within an Event successfully", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetPointsByLeaderBoardResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the Points for a Leaderboard in an Event was not successfully found", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetPointsByLeaderBoardResponse.class ) ) ) } )
    @PostMapping( value = "/Event/getPointsByLeaderBoard",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< GetPointsByLeaderBoardResponse > getPointsByLeaderBoard( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get Points for a Leaderboard of the specified Event", required = true, schema = @Schema() ) @Valid @RequestBody GetPointsByLeaderBoardRequest body );


    @Operation( summary = "Get the points for the specified Event", description = "Get the points for the specified Event", security = {
            @SecurityRequirement( name = "bearerAuth" ) }, tags = { "Event" } )
    @ApiResponses( value = {
            @ApiResponse( responseCode = "200", description = "Return the Points for an Event successfully", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetPointsByUserResponse.class ) ) ),

            @ApiResponse( responseCode = "401", description = "Invalid JWT token" ),

            @ApiResponse( responseCode = "404", description = "Return the Points for an Event could not be returned", content = @Content( mediaType = "application/json", schema = @Schema( implementation = GetPointsByUserResponse.class ) ) ) } )
    @PostMapping( value = "/Event/getPointsByUser",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" } )
    ResponseEntity< GetPointsByUserResponse > getPointsByUser( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get the Points for an Event", required = true, schema = @Schema() ) @Valid @RequestBody GetPointsByUserRequest body );


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

}

