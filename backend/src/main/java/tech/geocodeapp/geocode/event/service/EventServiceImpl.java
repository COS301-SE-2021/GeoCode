package tech.geocodeapp.geocode.event.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

import tech.geocodeapp.geocode.event.model.*;
import tech.geocodeapp.geocode.event.repository.EventRepository;
import tech.geocodeapp.geocode.event.repository.UserEventStatusRepository;
import tech.geocodeapp.geocode.event.request.*;
import tech.geocodeapp.geocode.event.response.*;
import tech.geocodeapp.geocode.event.exceptions.*;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.model.Difficulty;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.geocode.request.GetGeoCodeRequest;
import tech.geocodeapp.geocode.geocode.response.GetGeoCodeResponse;
import tech.geocodeapp.geocode.geocode.service.GeoCodeService;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;
import tech.geocodeapp.geocode.user.service.UserService;

import java.util.*;

/**
 * This class implements the EventService interface
 */
@Validated
@Service( "EventService" )
public class EventServiceImpl implements EventService {

    /**
     * The repository the Event class interacts with
     */
    @NotNull( message = "Events repository may not be null." )
    private final EventRepository eventRepo;

    /**
     * The repository the Event class interacts with
     */
    @NotNull( message = "TimeLog repository may not be null." )
    private final UserEventStatusRepository userEventStatusRepo;


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
    @NotNull(message = "GeoCode Service Implementation may not be null.")
    private GeoCodeService geoCodeService;

    /**
     * The User service to access the current user
     */
    @NotNull(message = "User Service Implementation may not be null.")
    private UserService userService;

    /**
     * Overloaded Constructor
     *
     * @param eventRepo          the repo the created response attributes should save to
     * @param leaderboardService access to the Leaderboard use cases and repository
     */
    public EventServiceImpl( EventRepository eventRepo, UserEventStatusRepository userEventStatusRepo,
                             @Qualifier( "LeaderboardService" ) @Lazy LeaderboardService leaderboardService ) throws RepoException {

        if ( ( eventRepo != null ) && ( userEventStatusRepo != null ) ) {

            /* The repo exists therefore it can be set for the class */
            this.eventRepo = eventRepo;
            this.userEventStatusRepo = userEventStatusRepo;

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
                ( request.getEndDate() == null ) || ( request.getGeoCodesToFind() == null ) ||
                ( request.getOrderBy() == null ) ) {

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
            e.printStackTrace();
            return new CreateEventResponse( false );
        }

        /* Store the list of GeoCode UUIDs to create a Level on */
        List< UUID > geoCodeIDs = request.getGeoCodesToFind();

        UUID eventID = UUID.randomUUID();

        List<GeoCode> geoCodes = new ArrayList<>();
        /*
         * Go through each GeoCode ID
         * and get the GeoCode object
         */
        for ( UUID id : geoCodeIDs ) {
            try {
                /*
                 * Call the GeoCode service to get the GeoCode Object
                 * add the found object to the list
                 * */
                var found = geoCodeService.getGeoCode( new GetGeoCodeRequest( id ) ).getFoundGeoCode();
                geoCodes.add( found );

                /* Set the geocode's event ID */
                if (found.getEventID() != null) {
                    System.out.println("Attempted to link a geocode that is already linked to an event");
                    return new CreateEventResponse(false);
                }
                found.setEventID(eventID);
                geoCodeService.saveGeoCode(found);
            } catch ( tech.geocodeapp.geocode.geocode.exceptions.InvalidRequestException e ) {
                System.out.println("Failed to find geocode with id "+id.toString());
                return new CreateEventResponse(false);
            }
        }

        /*
         * Determine which order to set the GeoCodes in
         * the default is OrderLevels.GIVEN
         */
        if ( request.getOrderBy().equals( OrderLevels.DIFFICULTY ) ) {

            /* Set the list to go from easiest to most difficult on finding the GeoCode */
            geoCodeIDs = sortByDifficulty( geoCodes );
        } else if ( request.getOrderBy().equals( OrderLevels.DISTANCE ) ) {

            /* Set the list to go from least to most distance with where the GeoCode is located */
            geoCodeIDs = sortByDistance( geoCodes );
        }

        /* Check if the GeoCodes are still valid after sorting */
        if ( geoCodeIDs == null ) {

            /* The GeoCodes are no longer valid so stop */
            System.out.println("Sorting failed");
            return new CreateEventResponse( false );
        }

        /* Create the new Event object with the specified attributes */
        var event = new Event(eventID, request.getName(), request.getDescription(),
                request.getLocation(), geoCodeIDs, request.getBeginDate(), request.getEndDate(),
                leaderboard, new HashMap<String, String>() {
        });

        /* Check the availability of the Event */
        if ( request.isAvailable() == null ) {

            /* None is set so make it default available */
            event.setAvailable( true );
        } else {

            /* Set to the given */
            event.setAvailable( request.isAvailable() );
        }

        /*
         * Save the newly create Event
         * Validate if the Event was saved properly
         */
        var success = true;
        try {

            /* Save the newly created entry to the repository */
            System.out.println(event);
            eventRepo.save( event );
        } catch ( IllegalArgumentException error ) {
            error.printStackTrace();
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
     * Get the current status of a User participating in an event, as well as the target GeoCode that they need to locate
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetCurrentEventStatusResponse
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetCurrentEventStatusResponse getCurrentEventStatus(GetCurrentEventStatusRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( ( request.getEventID() == null ) || ( request.getUserID() == null ) ) {

            throw new InvalidRequestException();
        }

        /* Get the UserEventStatus object from the repository */
        UserEventStatus status = userEventStatusRepo.findStatusByEventIDAndUserID(request.getEventID(), request.getUserID());
        if (status == null) {
            /* User is not in the event. Enter the event with nextStage */
            NextStageRequest nextStageRequest = new NextStageRequest(request.getEventID(), request.getUserID());
            NextStageResponse nextStageResponse = nextStage(nextStageRequest);
            /* Returns a new status object with the geocodeID set to the first geocode */
            status = nextStageResponse.getStatus();
        }

        UUID geocodeID = status.getGeocodeID();
        if (geocodeID != null) {
            try {
                GetGeoCodeResponse getGeoCodeResponse = geoCodeService.getGeoCode( new GetGeoCodeRequest( geocodeID ) );
                return new GetCurrentEventStatusResponse( true, status, getGeoCodeResponse.getFoundGeoCode() );

            } catch (tech.geocodeapp.geocode.geocode.exceptions.InvalidRequestException e) {
                e.printStackTrace();
            }

        } else {
            return new GetCurrentEventStatusResponse( true, status, null );
        }

        return new GetCurrentEventStatusResponse( false );
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
        System.out.println("Moving to next stage");
        /* Validate the request */
        if ( request == null ) {
            throw new InvalidRequestException( true );
        } else if ( ( request.getEventID() == null ) || ( request.getUserID() == null ) ) {
            throw new InvalidRequestException();
        }

        System.out.println("Request valid");
        System.out.println(request);

        /* Find the event with the provided id */
        Optional< Event > temp = eventRepo.findById( request.getEventID() );
        System.out.println(temp);
        if ( temp.isPresent() ) {

            Event event = temp.get();
            System.out.println(event);

            /* Get the ProgressLog object from the repository */
            UserEventStatus log = userEventStatusRepo.findStatusByEventIDAndUserID(request.getEventID(), request.getUserID());
            System.out.println(log);
            if (log != null) {
                if (log.getGeocodeID() == null) {
                    /* User has already finished this event */
                    System.out.println("User has finished");
                    return new NextStageResponse(null);
                }

                /* Find the user's current geocode in the list */
                List<UUID> ids = event.getGeocodeIDs();
                for (int i = 0; i < ids.size(); i++) {
                    System.out.println(ids.get(i));
                    if (ids.get(i).equals(log.getGeocodeID())) {
                        System.out.println("match");
                        /* Set the next geocode id */
                        UUID nextGeocodeID = null;
                        if (i < ids.size() - 1) {
                            /* This is not the last geocode */
                            nextGeocodeID = ids.get(i + 1);
                        }
                        /* Else the nextGeocodeID will be null, which represents the end of the event */
                        log.setGeocodeID(nextGeocodeID);
                        userEventStatusRepo.save(log);
                        System.out.println("Moving to next stage:");
                        System.out.println(nextGeocodeID);
                        return new NextStageResponse(log);
                    } else {
                        System.out.println("no match");
                    }
                }

            } else {
                /* User is not on any geocode, start at the first one by creating a new ProgressLog */
                UUID nextGeocodeID = event.getGeocodeIDs().get(0);
                Map<String, String> eventDetails = new HashMap<String, String>();
                log = new UserEventStatus(UUID.randomUUID(), event.getId(), request.getUserID(), nextGeocodeID, eventDetails);
                userEventStatusRepo.save(log);
                System.out.println("Starting first stage:");
                System.out.println(nextGeocodeID);

                return new NextStageResponse(log);
            }
        }

        /* This should only be hit if:
         - the no event exists with the given ID, or
         - the user's current geocode ID no longer exists in the event */
        System.out.println("end null");
        return new NextStageResponse(null);
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
        var radius = request.getRadius()/111; // 111 km = 1 degree

        /* Go through each Event in the repository */
        for ( Event event : temp ) {

            /* Calculate the distance from the given point to the Event using the distance formula */
            /* This is not the most accurate method, but should be close as long as the radius is small */
            var latitudeDifference = Math.pow(locate.getLatitude() - event.getLocation().getLatitude(), 2);
            var longitudeDifference = Math.pow(locate.getLongitude() - event.getLocation().getLongitude(), 2);
            var distance = Math.sqrt(longitudeDifference+latitudeDifference);

            /* Check if the event is close enough to the given point */
            if ( distance <= radius ) {
                foundEvents.add( event );
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

        /*

            ToDo maybe this should be called check Availability instead where it checks the date to the current

         */

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

    /*---------- Post Construct services ----------*/

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
    private List< UUID > sortByDifficulty( List< GeoCode > geoCodes ) {

        List< UUID > hold = null;

        if ( geoCodes != null ) {

            /* Apply Bubble sorting algorithm to get the objects in the correct order */

            /* Size of the list to sort */
            int n = geoCodes.size();

            for ( int i = 0; i < n - 1; i++ ) {

                for ( int j = 0; j < n - i - 1; j++ ) {

                    /* Hold the index of Difficulties */
                    var checkOne = getDifficultyIndex( geoCodes.get( j ).getDifficulty() );
                    var checkTwo = getDifficultyIndex( geoCodes.get( j + 1 ).getDifficulty() );

                    /* Check if the two positions needs to be swapped */
                    if ( checkOne > checkTwo ) {

                        /* Perform the swap */
                        var tempSwap = geoCodes.get( j );
                        geoCodes.set( j, geoCodes.get( j + 1 ) );
                        geoCodes.set( j + 1, tempSwap );
                    }
                }
            }

            hold = new ArrayList<>();

            /* Add the sorted GeoCode Objects ID's to the return list */
            for ( GeoCode geoCode : geoCodes ) {

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
    private List< UUID > sortByDistance( List< GeoCode > geoCodes ) {

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