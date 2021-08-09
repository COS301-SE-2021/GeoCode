package tech.geocodeapp.geocode.event.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import tech.geocodeapp.geocode.event.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.event.response.*;
import tech.geocodeapp.geocode.event.request.*;
import tech.geocodeapp.geocode.event.service.EventService;
import tech.geocodeapp.geocode.geocode.response.GetGeoCodeByQRCodeResponse;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@Validated
public class EventApiController implements EventApi {

    private final EventService eventService;

    public EventApiController( EventService service ) {
        this.eventService = service;
    }

    public ResponseEntity< ChangeAvailabilityResponse > changeAvailability( @Parameter( in = ParameterIn.DEFAULT, description = "Request to update the availability of an Event", required = true, schema = @Schema() ) @Valid @RequestBody ChangeAvailabilityRequest body ) {

        return new ResponseEntity<>( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< CreateEventResponse > createEvent( @Parameter( in = ParameterIn.DEFAULT, description = "Request to create an Event", required = true, schema = @Schema() ) @Valid @RequestBody CreateEventRequest body ) throws InvalidRequestException {

        CreateEventResponse response = eventService.createEvent(body);

        if ( ( response != null ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity< CreateLeaderboardResponse > createLeaderBoard( @Parameter( in = ParameterIn.DEFAULT, description = "Request to create a new Leaderboard for an Event", required = true, schema = @Schema() ) @Valid @RequestBody CreateLeaderboardRequest body ) {

        return new ResponseEntity<>( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< CreatePointResponse > createPoint( @Parameter( in = ParameterIn.DEFAULT, description = "Request to create a new Point for an Event", required = true, schema = @Schema() ) @Valid @RequestBody CreatePointRequest body ) {

        return new ResponseEntity<>( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< CreateTimeTrialResponse > createTimeTrial( @Parameter( in = ParameterIn.DEFAULT, description = "Request to create a new Time Trial for an Event", required = true, schema = @Schema() ) @Valid @RequestBody CreateTimeTrialRequest body ) {

        return new ResponseEntity<>( HttpStatus.NOT_IMPLEMENTED );
    }

    @Override
    public ResponseEntity< GetCurrentEventResponse > getCurrentEvent( GetCurrentEventRequest body ) throws InvalidRequestException {

        GetCurrentEventResponse response = eventService.getCurrentEvent( body );

        if ( ( response != null ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    @Override
    public ResponseEntity< GetCurrentEventLevelResponse > getCurrentEventLevel( GetCurrentEventLevelRequest body ) throws InvalidRequestException {

        GetCurrentEventLevelResponse response = eventService.getCurrentEventLevel( body );

        if ( ( response != null ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity< GetAllEventsResponse > getAllEvents() {
      
        GetAllEventsResponse response = eventService.getAllEvents();

        if ( ( response != null ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity< GetEventsByLocationResponse > getEventsByLocation( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get an Event by its location", required = true, schema = @Schema() ) @Valid @RequestBody GetEventsByLocationRequest body ) throws InvalidRequestException {
      
        GetEventsByLocationResponse response = eventService.getEventsByLocation(body);

        if ( ( response != null ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity< GetLeaderBoardByTimeTrialResponse > getLeaderBoardByTimeTrial( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get the Leaderboard for a TimeTrial", required = true, schema = @Schema() ) @Valid @RequestBody GetLeaderBoardByTimeTrialRequest body ) {

        return new ResponseEntity<>( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< GetPointsResponse > getPoints() {

        return new ResponseEntity<>( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< GetPointsByLeaderBoardResponse > getPointsByLeaderBoard( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get Points for a Leaderboard of the specified Event", required = true, schema = @Schema() ) @Valid @RequestBody GetPointsByLeaderBoardRequest body ) {

        return new ResponseEntity<>( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< GetPointsByUserResponse > getPointsByUser( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get the Points for an Event", required = true, schema = @Schema() ) @Valid @RequestBody GetPointsByUserRequest body ) {
        
        return new ResponseEntity<>( HttpStatus.NOT_IMPLEMENTED );
    }

    public ResponseEntity< EventsNearMeResponse > getEventsNearMe( @Parameter( in = ParameterIn.DEFAULT, description = "Request to get an Event by its location", required = true, schema = @Schema() ) @Valid @RequestBody EventsNearMeRequest body ) throws InvalidRequestException {

        EventsNearMeResponse response = eventService.eventsNearMe(body);

        if ( ( response != null ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity< GetEventResponse > getEvent( GetEventRequest body ) throws InvalidRequestException {

        GetEventResponse response = eventService.getEvent( body );

        if ( ( response != null ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

}
