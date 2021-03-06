package tech.geocodeapp.geocode.event.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import tech.geocodeapp.geocode.event.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.event.response.*;
import tech.geocodeapp.geocode.event.request.*;
import tech.geocodeapp.geocode.event.service.EventService;

import javax.validation.Valid;

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

    @Override
    public ResponseEntity<GetCurrentEventStatusResponse> getCurrentEventStatus(GetCurrentEventStatusRequest body ) throws InvalidRequestException {

        GetCurrentEventStatusResponse response = eventService.getCurrentEventStatus( body );

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

    @Override
    public ResponseEntity<GetEnteredEventsResponse> getEnteredEvents(GetEnteredEventsRequest body ) throws InvalidRequestException {

        GetEnteredEventsResponse response = eventService.getEnteredEvents( body );

        if ( ( response != null ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
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

    public ResponseEntity<GetBlocksResponse> getBlocks(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the User's blocks for a Blockly Event", required=true, schema=@Schema()) @Valid @RequestBody GetBlocksRequest body) throws InvalidRequestException {
        GetBlocksResponse response = eventService.getBlocks( body );

        if ( ( response != null ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity<CheckOutputResponse> checkOutput(@Parameter(in = ParameterIn.DEFAULT, description = "Request to check the output values for a Blockly Event", required=true, schema=@Schema()) @Valid @RequestBody CheckOutputRequest body) throws InvalidRequestException {
        CheckOutputResponse response = eventService.checkOutput( body );

        if ( ( response != null ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    public ResponseEntity<GetInputsResponse> getInputs(@Parameter(in = ParameterIn.DEFAULT, description = "Request to get the input values for a Blockly Event", required=true, schema=@Schema()) @Valid @RequestBody GetInputsRequest body) throws InvalidRequestException {
        GetInputsResponse response = eventService.getInputs( body );

        if ( ( response != null ) ) {

            return new ResponseEntity<>( response, HttpStatus.OK );
        } else {

            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }
}
