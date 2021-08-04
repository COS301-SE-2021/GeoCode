package tech.geocodeapp.geocode.event.service;

import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import javax.validation.Valid;

import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.event.model.Level;
import tech.geocodeapp.geocode.event.repository.EventRepository;
import tech.geocodeapp.geocode.event.request.*;
import tech.geocodeapp.geocode.event.response.*;
import tech.geocodeapp.geocode.event.exceptions.*;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This class implements the EventService interface
 */
@Validated
@Service( "EventService" )
public class EventServiceImpl implements EventService {

    /**
     * The repository the GeoCode class interacts with
     */
    @NotNull( message = "Events repository may not be null." )
    private final EventRepository eventRepo;

    /**
     * Overloaded Constructor
     *
     * @param eventRepo the repo the created response attributes should save to
     */
    public EventServiceImpl( @Valid EventRepository eventRepo ) {

        this.eventRepo = eventRepo;
    }

    /**
     * Create a new Event object with the given attributes and store it
     * in the EventRepository
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateEventRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public CreateEventResponse createEvent( CreateEventRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( ( request.getDescription() == null ) || ( request.getLocation() == null ) ||
                    ( request.getName() == null ) || ( request.getBeginDate() == null ) ||
                    ( request.getEndDate() == null ) || ( request.getGeoCodesToFind() == null ) ) {

            throw new InvalidRequestException();
        }

        //ToDo create the levels
        //ToDo check how to create the default leaderboard

        var levels = new ArrayList<Level>();

        var leaderboard = new ArrayList<Leaderboard>();

        /* Create the new Event object with the specified attributes */
        var event = new Event( UUID.randomUUID(), request.getName(), request.getDescription(),
                               request.getLocation(), levels, request.getBeginDate(), request.getEndDate(),
                               leaderboard );

        /*
        * Save the newly create Event
        * Validate if the Event was saved properly
        */
        var success = true;
        try {

            eventRepo.save( event );
        } catch ( IllegalArgumentException error ) {

            success = false;
        }

        return new CreateEventResponse( success );
    }

    /**
     * Get a specified Event that is stored in the repository
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetEventRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetEventResponse getEvent( GetEventRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getEventID() == null ) {

            throw new InvalidRequestException();
        }

        /* Create the response to return */
        GetEventResponse response = null;
        try {

            /*
             * Query the repository for the Event object
             * and set the response to true with the found Event
             */
            Optional< Event > temp = eventRepo.findById( request.getEventID() );
            response = temp.map(

                                    /* Indicate the Event was found and return it */
                                    event -> new GetEventResponse( true, event )
                               ).orElseGet(

                                     /* Indicate the Event was not found */
                                   () -> new GetEventResponse( false )
                               );

        } catch ( EntityNotFoundException error ) {

            /* No Event found so set the response to false */
            response = new GetEventResponse( false );
        }

        return response;
    }

    /**
     * Get a specific Event that a User is currently partaking in and the Event stored in the repository
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetCurrentEventRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetCurrentEventResponse getCurrentEvent( GetCurrentEventRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getUserID() == null ) {

            throw new InvalidRequestException();
        }

        /* Create the response to return */
        GetCurrentEventResponse response = null;
        try {

            /*
             * Query the repository for the Event object
             * and set the response to true with the found Event
             */
            List<Event> temp = eventRepo.findAll();
            //response = new GetCurrentEventResponse( true, temp );
        } catch ( EntityNotFoundException error ) {

            /* No Event found so set the response to false */
            response = new GetCurrentEventResponse( false );
        }

        return response;
    }

    /**
     * Get the next GeoCode the User has to find for their current Event
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified NextStageRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public NextStageResponse nextStage( NextStageRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( ( request.getEventID() == null ) || ( request.getUserID() == null ) ) {

            throw new InvalidRequestException();
        }

        NextStageResponse response = null;

        return null;
    }

    /**
     * Retrieve a list of Events around a certain radius of a location
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified EventsNearMeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public EventsNearMeResponse eventsNearMe( EventsNearMeRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getLocation() == null ) {

            throw new InvalidRequestException();
        }

        return null;
    }

    /**
     * Get all the stored Events in the repository
     *
     * @return the newly created response instance
     */
    @Override
    public GetAllEventsResponse getAllEvents() {

        List< Event > temp = eventRepo.findAll();

        return new GetAllEventsResponse( temp );
    }

    /**
     * Change the availability of a specific Event object
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified ChangeAvailabilityRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public ChangeAvailabilityResponse changeAvailability( ChangeAvailabilityRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getEventID() == null ) {

            throw new InvalidRequestException();
        }

        return null;
    }

    /**
     * Get all the Events at a certain location
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetEventsByLocationResponse getEventsByLocation( GetEventsByLocationRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getLocation() == null ) {

            throw new InvalidRequestException();
        }

        return null;
    }

    /**
     * Create a new Leaderboard for an Event
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public CreateLeaderboardResponse createLeaderBoard( CreateLeaderboardRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getName() == null ) {

            throw new InvalidRequestException();
        }

        return null;
    }

    /**
     * Create a new point for a leaderboard
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public CreatePointResponse createPoint( CreatePointRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getAmount() == null ) {

            throw new InvalidRequestException();
        }

        return null;
    }

    /**
     * Create a new TimeTrial for an event, that will be active for a pre-determined
     * amount of time
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public CreateTimeTrialResponse createTimeTrial( CreateTimeTrialRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        }

        return null;
    }

    /**
     * Get the points for a specific event that a user has gotten
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetPointsByUserResponse getPointsByUser( GetPointsByUserRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getUserID() == null ) {

            throw new InvalidRequestException();
        }

        return null;
    }

    /**
     * Get the points for a specific leaderboard
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetPointsByLeaderBoardResponse getPointsByLeaderboard( GetPointsByLeaderBoardRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getLeaderboardID() == null ) {

            throw new InvalidRequestException();
        }

        return null;
    }

    /**
     * Get the points of a specific Event
     *
     * @return the newly created response instance
     */
    @Override
    public GetPointsResponse getPoints() {

        return null;
    }

    /**
     * Get the Leaderboard of a TimeTrial
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetLeaderBoardByTimeTrialResponse getLeaderBoardByTimeTrial( GetLeaderBoardByTimeTrialRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getTimeTrialID() == null ) {

            throw new InvalidRequestException();
        }

        return null;
    }

}