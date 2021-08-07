package tech.geocodeapp.geocode.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.event.model.Level;
import tech.geocodeapp.geocode.event.model.OrderLevels;
import tech.geocodeapp.geocode.event.model.TimeTrial;
import tech.geocodeapp.geocode.event.repository.EventRepository;
import tech.geocodeapp.geocode.event.request.*;
import tech.geocodeapp.geocode.event.response.*;
import tech.geocodeapp.geocode.event.exceptions.*;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.model.Difficulty;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.geocode.request.GetGeoCodeRequest;
import tech.geocodeapp.geocode.geocode.service.GeoCodeService;
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
     * The Leaderboard service to access the use cases and
     * Leaderboard repository
     */
    @NotNull( message = "GeoCodeService: Leaderboard Service Implementation may not be null." )
    private final LeaderboardService leaderboardService;

    /**
     * The GeoCode service to access the use cases and
     * GeoCode repository
     */
    @Autowired
    //@NotNull( message = "GeoCodeService: GeoCode Service Implementation may not be null." )
    private GeoCodeService geoCodeService;

    /**
     * Overloaded Constructor
     *
     * @param eventRepo          the repo the created response attributes should save to
     * @param leaderboardService access to the Leaderboard use cases and repository
     */
    public EventServiceImpl( EventRepository eventRepo,
                             @Qualifier( "LeaderboardService" ) @Lazy LeaderboardService leaderboardService ) throws RepoException {

        if ( eventRepo != null ) {

            /* The repo exists therefore it can be set for the class */
            this.eventRepo = eventRepo;

            this.leaderboardService = Objects.requireNonNull( leaderboardService, "EventService: Leaderboard service must not be null." );
        } else {

            /* The repo does not exist throw an error */
            throw new RepoException();
        }
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

        /* Hold the created leaderboards */
        var leaderboard = new ArrayList< Leaderboard >();
        try {

            /*
             * Create the request to the leaderboard service
             * and store the response
             */
            var leaderboardRequest = new tech.geocodeapp.geocode.leaderboard.request.CreateLeaderboardRequest( request.getName() + " - Default" );
            var hold = leaderboardService.createLeaderboard( leaderboardRequest ).getLeaderboard();

            leaderboard.add( hold );
        } catch ( NullRequestParameterException e ) {

            return new CreateEventResponse( false );
        }

        /* Hold each created Level object */
        var levels = new ArrayList< Level >();

        /* Store the list of GeoCode UUIDs to create a Level on */
        List< UUID > geoCodes = request.getGeoCodesToFind();

        /*
         * Determine which order to set the GeoCodes in
         * the default is OrderLevels.GIVEN
         */
        if ( request.getOrderBy().equals( OrderLevels.DIFFICULTY ) ) {

            /* Set the list to go from easiest to most difficult on finding the GeoCode */
            geoCodes = sortByDifficulty( geoCodes );
        } else if ( request.getOrderBy().equals( OrderLevels.DISTANCE ) ) {

            /* Set the list to go from least to most distance with where the GeoCode is located */
            geoCodes = sortByDistance( geoCodes );
        }

        /* Check if the GeoCodes are still valid after sorting */
        if ( geoCodes == null ) {

            /* THe GeoCodes are no longer valid so stop */
            return new CreateEventResponse( false );
        }

        /* Go through each UUID */
        for ( UUID geoCode : geoCodes ) {

            /*
             * Create the Level with a random UUID
             * and add it to the list
             */
            levels.add( new Level( geoCode ) );
        }

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

            /* Save the newly created entry to the repository */
            var check = eventRepo.save( event );

            /* Check if the Object was saved correctly */
            if ( !event.equals( check ) ) {

                success = false;
            }
        } catch ( IllegalArgumentException error ) {

            success = false;
        }

        return new CreateEventResponse( success );
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
        } else if ( ( request.getTimeLimit() != 0.0 ) || ( request.getDescription() == null ) ||
                ( request.getLocation() == null ) || ( request.getName() == null ) ||
                ( request.getBeginDate() == null ) || ( request.getEndDate() == null ) ||
                ( request.getGeoCodesToFind() == null ) ) {

            throw new InvalidRequestException();
        }

        /* Hold the created leaderboards */
        var leaderboard = new ArrayList< Leaderboard >();
        try {

            /*
             * Create the request to the leaderboard service
             * and store the response
             */
            var leaderboardRequest = new tech.geocodeapp.geocode.leaderboard.request.CreateLeaderboardRequest( request.getName() + " - Default" );
            var hold = leaderboardService.createLeaderboard( leaderboardRequest ).getLeaderboard();

            leaderboard.add( hold );
        } catch ( NullRequestParameterException e ) {

            return new CreateTimeTrialResponse( false );
        }

        /* Hold each created Level object */
        var levels = new ArrayList< Level >();

        /* Store the list of GeoCOde UUIDs to create a Level on */
        List< UUID > geoCodes = request.getGeoCodesToFind();

        /* Go through each UUID */
        for ( UUID geoCode : geoCodes ) {

            /*
             * Create the Level with a random UUID
             * and add it to the list
             */
            levels.add( new Level( geoCode ) );
        }

        /* Create the new Event object with the specified attributes */
        var timeTrial = new TimeTrial( UUID.randomUUID(), request.getName(), request.getDescription(),
                                       request.getLocation(), levels, request.getBeginDate(), request.getEndDate(),
                                       leaderboard, request.getTimeLimit() );

        /*
         * Save the newly create Event
         * Validate if the Event was saved properly
         */
        var success = true;
        try {

            /* Save the newly created entry to the repository */
            var check = eventRepo.save( timeTrial );

            /* Check if the Object was saved correctly */
            if ( !timeTrial.equals( check ) ) {

                success = false;
            }
        } catch ( IllegalArgumentException error ) {

            success = false;
        }

        return new CreateTimeTrialResponse( success );
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

        try {

            /*
             * Query the repository for the Event object
             * and set the response to true with the found Event
             */
            List< Event > temp = eventRepo.findAll();

            /* Go through each available Event */
            for ( Event event : temp ) {

                /* Get the Levels for each Event */
                List< Level > levels = event.getLevels();

                /* Go through each found Level to check if the User's ID is present */
                for ( Level level : levels ) {

                    Map< String, UUID > onLevel = level.getOnLevel();
                    if ( onLevel.containsValue( request.getUserID() ) ) {

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

                /* Get the Levels for each Event */
                List< Level > levels = currEvent.getLevels();

                /* Go through each found Level to check if the User's ID is present */
                for ( Level level : levels ) {

                    /* Check if the current Level contains the User */
                    Map< String, UUID > onLevel = level.getOnLevel();
                    if ( onLevel.containsValue( request.getUserID() ) ) {

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
        var foundEvents = new ArrayList< Event >();

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
        var foundEvents = new ArrayList< Event >();

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
            var hold = leaderboardService.createLeaderboard( leaderboardRequest ).getLeaderboard();

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

    /*---------- Post Construct GeoCode service ----------*/

    /**
     * Post construct the GeoCode service, this avoids a circular dependency
     *
     * @param geoCodeService the service to be set
     */
    public void setGeoCodeService( GeoCodeService geoCodeService ) {

        this.geoCodeService = geoCodeService;
    }

    /*-------------------------------------*/


    /*---------- Helper Functions for creating an Event ----------*/

    /**
     * Gets a list of GeoCode ID's and sorts them according to their Difficulty
     *
     * @param geoCodes the list of GeoCode ID's to sort
     *
     * @return the sorted GeoCode ID's in order of Difficulty
     */
    private List< UUID > sortByDifficulty( List< UUID > geoCodes ) {

        List< UUID > hold = null;

        if ( geoCodes != null ) {

            /* Hold all the found GeoCode Objects */
            List< GeoCode > temp = new ArrayList<>();

            /*
             * Go through each GeoCode ID
             * and get the GeoCode object
             */
            for ( UUID geoCode : geoCodes ) {

                try {

                    /*
                     * Call the GeoCode service to get the GeoCode Object
                     * add the found object to the list
                     * */
                    var found = geoCodeService.getGeoCode( new GetGeoCodeRequest( geoCode ) ).getFoundGeoCode();
                    temp.add( found );
                } catch ( tech.geocodeapp.geocode.geocode.exceptions.InvalidRequestException e ) {

                    return null;
                }
            }

            /* Apply Bubble sorting algorithm to get the objects in the correct order */

            /* Size of the list to sort */
            int n = temp.size();

            for ( int i = 0; i < n - 1; i++ ) {

                for ( int j = 0; j < n - i - 1; j++ ) {

                    /* Hold the index of Difficulties */
                    var checkOne = getDifficultyIndex( temp.get( j ).getDifficulty() );
                    var checkTwo = getDifficultyIndex( temp.get( j + 1 ).getDifficulty() );

                    /* Check if the two positions needs to be swapped */
                    if ( checkOne > checkTwo ) {

                        /* Perform the swap */
                        var tempSwap = temp.get( j );
                        temp.set( j, temp.get( j + 1 ) );
                        temp.set( j + 1, tempSwap );
                    }
                }
            }

            hold = new ArrayList<>();

            /* Add the sorted GeoCode Objects ID's to the return list */
            for ( GeoCode geoCode : temp ) {

                /* Get the ID from the current object */
                var geoCodeID = geoCode.getId();

                /* Valid the ID */
                if ( geoCodeID != null ) {

                    hold.add( geoCodeID );
                }
            }

        }

        return hold;
    }

    /**
     * Determines the index of importance of a Difficulty
     *
     * @param difficulty the Difficulty to search for
     *
     * @return the index of the Difficulty in the List
     */
    private int getDifficultyIndex( Difficulty difficulty ) {

        /* Current index */
        int x = 0;

        /* Get the order of difficulties */
        List< Difficulty > difficultyOrder = Difficulty.getDifficultyOrder();

        /* Go through each object in the list */
        for ( ; x < difficultyOrder.size(); x++ ) {

            /* Check if the current object matches the required one */
            if ( difficulty.equals( difficultyOrder.get( x ) ) ) {

                /* The current object is the index wanted */
                return x;
            }
        }

        return x;
    }


    /**
     * Gets a list of GeoCode ID's and sorts them according to their distance from one another
     *
     * @param geoCodes the list of GeoCode ID's to sort
     *
     * @return the sorted GeoCode ID's in order of distance
     */
    private List< UUID > sortByDistance( List< UUID > geoCodes ) {

        /* Holds the new order of the GeoCodes */
        List< UUID > hold = null;

        if ( geoCodes != null ) {

            /*

                Use this to calculate the distance of each GeoPoint to One Another


                 // The math module contains a function
                // named toRadians which converts from
                // degrees to radians.
                double lon1 = Math.toRadians(lon1);
                double lon2 = Math.toRadians(lon2);
                double lat1 = Math.toRadians(lat1);
                double lat2 = Math.toRadians(lat2);

                // Haversine formula
                double dlon = lon2 - lon1;
                double dlat = lat2 - lat1;
                double a = Math.pow(Math.sin(dlat / 2), 2)
                         + Math.cos(lat1) * Math.cos(lat2)
                         * Math.pow(Math.sin(dlon / 2),2);

                double c = 2 * Math.asin(Math.sqrt(a));

                // Radius of earth in kilometers. Use 3956
                // for miles
                double r = 6371;

                // calculate the result
                return(c * r);

            */

        }

        return hold;
    }

    /*-------------------------------------*/


}