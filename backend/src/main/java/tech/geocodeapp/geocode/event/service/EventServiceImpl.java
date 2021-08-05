package tech.geocodeapp.geocode.event.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.event.model.Level;
import tech.geocodeapp.geocode.event.repository.EventRepository;
import tech.geocodeapp.geocode.event.request.*;
import tech.geocodeapp.geocode.event.response.*;
import tech.geocodeapp.geocode.event.exceptions.*;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;

import java.util.*;

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
     * The Event service to access the use cases and
     * Event repository
     */
    @NotNull( message = "GeoCodeService: Event Service Implementation may not be null." )
    private final LeaderboardService leaderboardService;

    /**
     * Overloaded Constructor
     *
     * @param eventRepo the repo the created response attributes should save to
     * @param leaderboardService access to the Leaderboard use cases and repository
     */
    public EventServiceImpl( EventRepository eventRepo,
                             @Qualifier("LeaderboardService") LeaderboardService leaderboardService ) {

        this.eventRepo = eventRepo;
        this.leaderboardService = leaderboardService;
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

        var leaderboard = new ArrayList<Leaderboard>();
        try {

            /*
             * Create the request to the leaderboard service
             * and store the response
             */
            var leaderboardRequest = new tech.geocodeapp.geocode.leaderboard.request.CreateLeaderboardRequest( request.getName() + "Default" );
            var hold = leaderboardService.createLeaderboard( leaderboardRequest ).getLeaderboard();

            leaderboard.add( hold );
        } catch ( NullRequestParameterException e ) {

            return new CreateEventResponse( false );
        }

        var levels = new ArrayList<Level>();

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
        GetEventResponse response;
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

            /* Go through each available Event */
            for ( Event event : temp ) {

                /* Get the Levels for each Event */
                List< Level > levels = event.getLevels();

                /* Go through each found Level to check if the User's ID is present */
                for ( Level level : levels ) {

                    Map< String, UUID > onLevel = level.getOnLevel();
                    if ( onLevel.containsKey( request.getUserID() ) ) {

                        return new GetCurrentEventResponse( true, event );
                    }
                }
            }

        } catch ( EntityNotFoundException error ) {

            /* No Event found so set the response to false */
            return new GetCurrentEventResponse( false );
        }

        return new GetCurrentEventResponse( false );
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

        try {

            /*
             * Query the repository for the Event object
             * and set the response to true with the found Event
             */
            Optional< Event > temp = eventRepo.findById( request.getEventID() );
            if ( temp.isPresent() ) {

                Event currEvent = temp.get();
                if (  temp.get() == null ) {

                    return new NextStageResponse( null );
                }

                /* Get the Levels for each Event */
                List< Level > levels = currEvent.getLevels();

                /* Go through each found Level to check if the User's ID is present */
                for ( Level level : levels ) {

                    /* Check if the current Level contains the User */
                    Map< String, UUID > onLevel = level.getOnLevel();
                    if ( onLevel.containsKey( request.getUserID() ) ) {

                        /* The current Level contains the User so return the GeoCode */
                        return new NextStageResponse( level.getTarget() );
                    }
                }
            }

        } catch ( EntityNotFoundException error ) {

            /* No Event found so set the response to false */
            return new NextStageResponse( null );
        }

        return new NextStageResponse( null );
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

        /* The list of Events within the radius */
        var foundEvents = new ArrayList<Event>();

        /* All the Events in the repository */
        var temp = eventRepo.findAll();

        /* The location and radius the location needs to fall into */
        GeoPoint locate = request.getLocation();
        var radius = request.getRadius();

        /* Go through each Event in the repository */
        for ( Event event : temp ) {

            /* Calculate the range of the radius */
                /* Get the latitude & longitude values for the current Event */
                var latitude = event.getLocation().getLatitude();
                var longitude = event.getLocation().getLongitude();

                /* Set the max value */
                GeoPoint min = new GeoPoint();
                min.setLatitude( latitude - radius );
                min.setLongitude( longitude - radius );

                /* Set the max value */
                GeoPoint max = new GeoPoint();
                min.setLatitude( latitude + radius );
                min.setLongitude( longitude + radius );

            /* Check if the value is within the max radius */
            if ( ( max.getLatitude() >= locate.getLatitude() ) && ( max.getLongitude() >= locate.getLongitude() ) ) {

                /* Check if the value is within the min radius */
                if ( ( min.getLatitude() <= locate.getLatitude() ) && ( min.getLongitude() <= locate.getLongitude() ) ) {

                    /*
                    * The current Event is within the radius
                    * therefore add it to the found list
                    */
                    foundEvents.add( event );
                }
            }
        }

        return new EventsNearMeResponse( foundEvents );
    }

    /**
     * Get all the stored Events in the repository
     *
     * @return the newly created response instance
     */
    @Override
    public GetAllEventsResponse getAllEvents() {

        var temp = eventRepo.findAll();

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

        /* The list of Events within the radius */
        var foundEvents = new ArrayList<Event>();

        /* All the Events in the repository */
        var temp = eventRepo.findAll();

        /* The location to look for */
        GeoPoint locate = request.getLocation();

        /* Go through each Event in the repository */
        for ( Event event : temp ) {

            /* Check if the value is within the max radius */
            if ( locate.equals( event.getLocation() ) ) {

                /*
                 * The current Event is the same
                 * therefore add it to the list
                 */
                foundEvents.add( event );
            }
        }

        return new GetEventsByLocationResponse( foundEvents );
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

        try {

            /*
            * Create the request to the leaderboard service
            * and store the response
            */
            var leaderboardRequest = new tech.geocodeapp.geocode.leaderboard.request.CreateLeaderboardRequest( request.getName() );
            var hold =  leaderboardService.createLeaderboard( leaderboardRequest ).getLeaderboard();

            /* Find the Event object with the given ID */
            var event = eventRepo.findById( request.getEventID() );
            if ( event.isPresent() ) {

                /* Get the actual Event and append to its leaderboards */
                var currEvent = event.get();
                currEvent.addLeaderboardsItem( hold );
            } else {

                /* The object was not found */
                return new CreateLeaderboardResponse( false );
            }

        } catch ( NullRequestParameterException error ) {

            /* An error occurred and the leaderboard could not be created */
            return new CreateLeaderboardResponse( false );
        }

        /* The new leaderboard was successfully made */
        return new CreateLeaderboardResponse( true );
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