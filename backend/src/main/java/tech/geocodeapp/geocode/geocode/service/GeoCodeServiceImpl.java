package tech.geocodeapp.geocode.geocode.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import tech.geocodeapp.geocode.collectable.decorator.CollectableTypeComponent;
import tech.geocodeapp.geocode.collectable.manager.CollectableTypeManager;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.collectable.request.CreateCollectableRequest;
import tech.geocodeapp.geocode.collectable.request.GetCollectableByIDRequest;
import tech.geocodeapp.geocode.collectable.response.CollectableResponse;
import tech.geocodeapp.geocode.collectable.response.CreateCollectableResponse;
import tech.geocodeapp.geocode.collectable.service.CollectableService;

import tech.geocodeapp.geocode.event.exceptions.MismatchedParametersException;
import tech.geocodeapp.geocode.event.exceptions.NotFoundException;
import tech.geocodeapp.geocode.event.service.EventService;

import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.geocode.repository.GeoCodeRepository;
import tech.geocodeapp.geocode.geocode.exceptions.RepoException;
import tech.geocodeapp.geocode.geocode.model.*;
import tech.geocodeapp.geocode.geocode.request.*;
import tech.geocodeapp.geocode.geocode.response.*;

import tech.geocodeapp.geocode.user.request.SwapCollectableRequest;
import tech.geocodeapp.geocode.user.service.UserService;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.security.SecureRandom;
import java.util.*;

import static tech.geocodeapp.geocode.collectable.model.Rarity.*;

/**
 * This class implements the GeoCodeService interface
 */
@Validated
@Service( "GeoCodeService" )
public class GeoCodeServiceImpl implements GeoCodeService {

    /**
     * The repository the GeoCode service reads, writes and manipulates
     */
    @NotNull( message = "GeoCodeService: GeoCode Repository may not be null." )
    private final GeoCodeRepository geoCodeRepo;

    /**
     * The collectable service to access the use cases and
     * collectable repository
     */
    @NotNull( message = "GeoCodeService: Collectable Service Implementation may not be null." )
    private final CollectableService collectableService;

    /**
     * The user service to access the use cases and
     * user repository
     */
    @NotNull( message = "GeoCodeService: User Service Implementation may not be null." )
    private final UserService userService;

    /**
     * The Event service to access the use cases and
     * Event repository
     */
    @NotNull( message = "GeoCodeService: Event Service Implementation may not be null." )
    private final EventService eventService;

    /**
     * The number of collectables to make when creating a new GeoCode
     */
    @Min( 0 )
    private static final int NUM_COLLECTABLES = 5;

    /**
     * The length of the qr code for a new GeoCode,
     * although it is set to 8 it should always be larger than length 4 to avoid duplicates
     */
    @Min( 4 )
    private static final int QR_SIZE = 8;

    /**
     * Overloaded Constructor
     *
     * @param geoCodeRepo the repo the created response attributes should save to
     * @param collectableService access to the collectable use cases and repository
     * @param userService access to the user use cases and repository
     * @param eventService access to the Event use cases and repository
     *
     * @throws RepoException the GeoCode repository was invalid
     */
    public GeoCodeServiceImpl( @Qualifier( "GeoCodeRepository" ) GeoCodeRepository geoCodeRepo,
                               @Qualifier( "CollectableService" ) CollectableService collectableService,
                               @Qualifier( "UserService" ) @Lazy UserService userService,
                               @Qualifier( "EventService" ) EventService eventService ) throws RepoException {

        /* Check if the given repo exists */
        if ( geoCodeRepo != null ) {

            /* The repo exists therefore it can be set for the class */
            this.geoCodeRepo = geoCodeRepo;

            /* The subsystems service implementations  */
            this.collectableService = Objects.requireNonNull( collectableService, "GeoCodeService: Collectable service must not be null." );
            this.userService = userService;
            this.eventService = eventService;
        } else {

            /* The repo does not exist throw an error */
            throw new RepoException();
        }
    }

    /**
     * Once the GeoCode service object has been created
     * insert it into the User and Event subsystem
     *
     * This is to avoid circular dependencies as each subsystem requires one another
     */
    @PostConstruct
    public void init() {

        userService.setGeoCodeService( this );
        eventService.setGeoCodeService( this );
    }

    /**
     * Create a new GeoCode and store it in the GeoCodeRepository
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public CreateGeoCodeResponse createGeoCode( CreateGeoCodeRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( ( request.getLocation() == null ) || ( request.getHints() == null ) ||
                ( request.getDifficulty() == null ) || ( request.getDescription() == null ) ||
                ( request.isAvailable() == null ) ) {

            throw new InvalidRequestException();
        }

        /* Hold the created Collectables */
        List< UUID > collectable = new ArrayList<>();

        /* Get all the stored Collectables */
        var collectableTypes = collectableService.getCollectableTypes();

        /* Create the specified amount of new collectables */
        for ( var x = 0; x < NUM_COLLECTABLES; x++ ) {

            /* Create the response and give it a Collectable type */
            var collectableRequest = new CreateCollectableRequest();

            CollectableTypeComponent typeList;
            if ( collectableTypes != null ) {

                /* Get first stored Collectable type */
                typeList = calculateCollectableType( collectableTypes.getCollectableTypes() );

                if ( typeList != null ) {

                    if ( typeList.getMissionType() == null ) {

                        /* Get and set the collectable request with the type */
                        collectableRequest.setCollectableTypeId( typeList.getId() );
                    } else {

                        /* Create the request with a mission type */
                        collectableRequest = new CreateCollectableRequest( typeList.getId(), true );
                    }
                } else {

                    /* Exception thrown when trying to get Collectable */
                    return new CreateGeoCodeResponse( false );
                }
            } else {

                /* Exception thrown when trying to get Collectable */
                return new CreateGeoCodeResponse( false );
            }

            /* Get the response from the created request */
            CreateCollectableResponse collectableResponse = collectableService.createCollectable( collectableRequest );

            /* Building a collectable from a collectable response */
            var temp = new Collectable();
            temp.setId( collectableResponse.getCollectable().getId() );
            //CollectableTypeComponent type = collectableResponse.getCollectable().getType();

            CollectableTypeManager manager = new CollectableTypeManager();

            // temp.setType( manager.convertToCollectableType( type ) );
            temp.setType( manager.convertToCollectableType( typeList ) );

            /* Adding the created Collectable to the list */
            collectable.add( temp.getId() );
        }

        /* The characters the qrCode must be made up of */
        var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        /* create a StringBuffer of the specified size */
        var qr = new StringBuilder( QR_SIZE );

        /* Generate a random char for the specified size */
        for ( var i = 0; i < QR_SIZE; i++ ) {

            /*
             * Add a Character from chars one by one to the end of qr until it is the correct size
             * */
            qr.append( chars.charAt( ( new SecureRandom() ).nextInt( chars.length() ) ) );
        }

        /*
         * Create the GeoCode object
         * and set its attributes to the given attributes in the request
         */
        var id = UUID.randomUUID();

        /*
         * Get the user who is creating the GeoCode
         */
//        var createdBy = userService.getCurrentUserID();
        UUID createdBy = null;

        /* Create the GeoCode Object */
        var newGeoCode = new GeoCode( id, request.getDifficulty(), request.isAvailable(),
                                      request.getDescription(), request.getHints(), collectable,
                                      qr.toString(), request.getLocation(), createdBy );

        /*
         * Save the newly created GeoCode
         * Validate if the GeoCode was saved properly
         */
        try {

            /* Save the newly created entry to the repository */
            var check = geoCodeRepo.save( newGeoCode );

            /* Check if the Object was saved correctly */
            if ( !newGeoCode.equals( check ) ) {

                /* Saved GeoCode not the same therefore creation failed */
                return new CreateGeoCodeResponse( false );
            }
        } catch ( IllegalArgumentException error ) {

            /* Exception thrown therefore creation failed */
            return new CreateGeoCodeResponse( false );
        }

        /*
         * Create the new response
         * and add the created GeoCode to it
         */
        var response = new CreateGeoCodeResponse();

        /*
         * Check if the object is present in the repo
         * if it does exist set it to null
         * else check if the id is inserted
         */
        var temp = geoCodeRepo.findById( id );
        if ( temp.isPresent() ) {

            /* Check the ID's are identical */
            if ( temp.get().getId().equals( id ) ) {

                /* Set the attributes as the creation was successful */
                response = new CreateGeoCodeResponse( true, id, qr.toString() );
            } else {

                /* An error occurred since the ID's are not identical */
                response.setSuccess( false );
            }
        } else {

            /* Exception thrown therefore creation failed */
            response.setSuccess( false );
        }

        return response;
    }

    /**
     * Get the GeoCode associated with the given ID
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetGeoCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    public GetGeoCodeResponse getGeoCode( GetGeoCodeRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getGeoCodeID() == null ) {

            throw new InvalidRequestException();
        }

        /* check if the Event exists */
        Optional< GeoCode > optionalGeoCode = geoCodeRepo.findById( request.getGeoCodeID() );

        if ( optionalGeoCode.isEmpty() ) {

            return new GetGeoCodeResponse( null );
        } else {

            return new GetGeoCodeResponse( optionalGeoCode.get() );
        }
    }

    /**
     * Get all the stored GeoCodes in the repository
     *
     * @return the newly created response instance
     */
    @Override
    public GetGeoCodesResponse getAllGeoCodes() {

        /* Retrieve all the stored GeoCodes from the repository */
        List< GeoCode > temp = new ArrayList<>( geoCodeRepo.findGeoCode() );

        /* Go through each GeoCode found and hide the sensitive data */
        for ( GeoCode geoCode : temp ) {

            geoCode.setHints( null );
            geoCode.setQrCode( null );
            geoCode.setCollectables( null );
        }

        /* Set the response to the masked GeoCodes and return it */
        return new GetGeoCodesResponse( temp );
    }

    /**
     * Get the stored Collectables inside a GeoCode
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetCollectablesRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetCollectablesResponse getCollectables( GetCollectablesRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getGeoCodeID() == null ) {

            throw new InvalidRequestException();
        }

        /* Find the GeoCode stored in the repository with the given ID */
        Optional< GeoCode > temp = geoCodeRepo.findById( request.getGeoCodeID() );

        /* Convert the optional object to a GeoCode*/
        var hold = new GeoCode();
        if ( temp.isPresent() ) {

            hold = temp.get();
        }

        /*
         * Create the new response and return all the
         * collectable ID's for the found GeoCode
         */
        return new GetCollectablesResponse( new ArrayList<>( hold.getCollectables() ) );
    }

    /**
     * Get all the GeoCodes with a certain level of difficulty
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetGeoCodesByDifficultyRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetGeoCodesByDifficultyResponse getGeoCodesByDifficulty( GetGeoCodesByDifficultyRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getDifficulty() == null ) {

            throw new InvalidRequestException();
        }

        /*
         * Sort through the stored GeoCodes and
         * find all the GeoCodes with the specified difficulty
         */
        List< GeoCode > hold = new ArrayList<>( geoCodeRepo.findGeoCodeWithDifficulty( request.getDifficulty() ) );
//        for ( GeoCode code : geoCodeRepo.findAll() ) {
//
//            /* Check if the current GeoCode has the Difficulty wanted */
//            if ( code.getDifficulty().equals( request.getDifficulty() ) ) {
//
//                /*
//                 * Ensure only the relevant data is shown
//                 */
//                code.setHints( null );
//                code.setQrCode( null );
//                code.setCollectables( null );
//
//                /*
//                 * The current GeoCode has the valid GeoCode
//                 * add it to the list
//                 */
//                hold.add( code );
//            }
//        }

        /*
         * Create the new response
         * and add valid GeoCodes to it
         */
        return new GetGeoCodesByDifficultyResponse( hold );
    }

    /**
     * Get all the GeoCodes with a certain level of difficulty that can be a list of items
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetGeoCodesByDifficultyListRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetGeoCodesByDifficultyListResponse getGeoCodesByDifficultyList( GetGeoCodesByDifficultyListRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getDifficulty() == null ) {

            throw new InvalidRequestException();
        }

        /*
         * Sort through the stored GeoCodes and
         * find all the GeoCodes with the specified difficulty
         */
        List< GeoCode > hold = new ArrayList<>();
        for ( GeoCode code : geoCodeRepo.findAll() ) {

            var temp = request.getDifficulty();
            for ( Difficulty difficulty : temp ) {

                /* Check if the current GeoCode has the Difficulty wanted */
                if ( code.getDifficulty().equals( difficulty ) ) {

                    /*
                     * Ensure only the relevant data is shown
                     */
                    code.setHints( null );
                    code.setQrCode( null );
                    code.setCollectables( null );

                    /*
                     * The current GeoCode has the valid GeoCode
                     * add it to the list
                     */
                    hold.add( code );
                }
            }
        }

        /*
         * Create the new response
         * and add valid GeoCodes to it
         */
        return new GetGeoCodesByDifficultyListResponse( hold );
    }


    /**
     * Get the hints of how to locate a GeoCode in the real world
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetHintsRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Transactional
//    @Override
    public GetHintsResponse getHints( GetHintsRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getGeoCodeID() == null ) {

            throw new InvalidRequestException();
        }

        /* Get the GeoCode in the repository with the specified ID */
        Optional< GeoCode > temp = geoCodeRepo.findById( request.getGeoCodeID() );

        /*
         * Create the new response
         * and add the list of hints to it
         */
        var response = new GetHintsResponse();
        if ( temp.isEmpty() ) {

            /* Specify no hints were found */
            Collection< String > hold = new ArrayList<>();
            hold.add( "No hints available." );
            response.setHints( hold );
        } else {

            response.setHints( temp.get().getHints() );
        }

        return response;
    }

    /**
     * Finds the stored GeoCode associated with the generated QR Code
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetGeoCodeByQRCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetGeoCodeByQRCodeResponse getGeoCodeByQRCode( GetGeoCodeByQRCodeRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getQrCode() == null ) {

            throw new InvalidRequestException();
        }

        /*
         * Get all the stored GeoCodes
         * and find the GeoCode with the specified qrCode
         *
         * ToDo use custom query
         */
        List< GeoCode > temp = geoCodeRepo.findAll();
        var x = 0;
        for ( ; x < temp.size(); x++ ) {

            /* Check if the current GeoCode is the one */
            if ( temp.get( x ).getQrCode().equals( request.getQrCode() ) ) {

                break;
            }
        }

        /*
         * Create the new response
         * and set values to it
         */
        return new GetGeoCodeByQRCodeResponse( temp.get( x ).getId(), temp.get( x ).isAvailable(),
                                               temp.get( x ).getDescription(), temp.get( x ).getLocation(),
                                               temp.get( x ).getDifficulty() );
    }

    /**
     * Finds the stored GeoCode associated with the generated QR Code and returns its Collectables
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetCollectablesInGeoCodeByQRCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetCollectablesInGeoCodeByQRCodeResponse getCollectablesInGeoCodeByQRCode( GetCollectablesInGeoCodeByQRCodeRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( ( request.getQrCode() == null ) || ( request.getGeoCodeID() == null ) ) {

            throw new InvalidRequestException();
        }

        /* Create a response object to hold the attributes */
        GetCollectablesInGeoCodeByQRCodeResponse response = new GetCollectablesInGeoCodeByQRCodeResponse();

        /* Get the GeoCode with the specified ID */
        var temp = geoCodeRepo.findById( request.getGeoCodeID() );
        if ( temp.isPresent() ) {

            /* Check if the found GeoCode has the correct qrCode */
            if ( temp.get().getQrCode().equals( request.getQrCode() ) ) {

                /* Get the collectables from the found GeoCode */
                ArrayList< Collectable > storedCollectable = getCollectable( temp.get() );

                /* Set the response to save the found collectables */
                response.setStoredCollectable( storedCollectable );
            }
        }

        /* return the response */
        return response;
    }

    /**
     * Finds the stored GeoCode associated at the given location
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetGeoCodeByLocationRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetGeoCodeByLocationResponse getGeoCodesByLocation( GetGeoCodeByLocationRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getLocation() == null ) {

            throw new InvalidRequestException();
        }

        /*
         * Get all the stored GeoCodes
         * and find the GeoCode with the specified location
         */
        List< GeoCode > temp = geoCodeRepo.findAll();
        var x = 0;
        for ( ; x < temp.size(); x++ ) {

            if ( temp.get( x ).getLocation().equals( request.getLocation() ) ) {

                break;
            }
        }

        /*
         * Create the new response
         * and set its values
         */
        return new GetGeoCodeByLocationResponse( temp.get( x ).getId(), temp.get( x ).isAvailable(),
                                                 temp.get( x ).getDescription(), temp.get( x ).getLocation(),
                                                 temp.get( x ).getDifficulty() );
    }

    /**
     * Finds the stored GeoCode associated at the given location and returns its Collectables
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified GetCollectablesInGeoCodesByLocationRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetCollectablesInGeoCodesByLocationResponse getCollectablesInGeoCodesByLocation( GetCollectablesInGeoCodesByLocationRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getLocation() == null ) {

            throw new InvalidRequestException();
        }

        /*
         * Get all of the stored GeoCodes
         * and find the GeoCode with the specified location
         */
        List< GeoCode > temp = geoCodeRepo.findAll();
        var x = 0;
        for ( ; x < temp.size(); x++ ) {

            if ( temp.get( x ).getLocation().equals( request.getLocation() ) ) {

                break;
            }
        }

        /* Get the collectables from the found GeoCode */
        ArrayList< Collectable > storedCollectable = getCollectable( temp.get( x ) );

        /*
         * Create the new response
         * and set its values
         */
        return new GetCollectablesInGeoCodesByLocationResponse( storedCollectable );
    }

    /**
     * Swaps a stored Collectable in a GeoCode with the Users GeoCode
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified SwapCollectablesRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public SwapCollectablesResponse swapCollectables( SwapCollectablesRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( ( request.getTargetCollectableID() == null ) || ( request.getTargetGeoCodeID() == null ) ) {

            throw new InvalidRequestException();
        }

        /* Find the target geocode */
        Optional< GeoCode > target = geoCodeRepo.findById( request.getTargetGeoCodeID() );
        if ( target.isEmpty() ) {

            return new SwapCollectablesResponse( false );
        }

        /* Get the stored GeoCode */
        var geocode = target.get();

        /* Validate if the user trying to access the GeoCode created it
         * if the user created the GeoCode do not allow the swap as it will be unfair
         * else continue as the user found the GeoCode fairly
         */
        var userID = userService.getCurrentUserID();
        if ( ( userID == null ) || ( geocode.getCreatedBy().equals( userID ) ) ) {

            return new SwapCollectablesResponse( false );
        }

        /* Find the target collectable in the GeoCode */
        var replaceIndex = -1;
        List< UUID > storedCollectables = new ArrayList<>( geocode.getCollectables() );
        for ( var i = 0; i < storedCollectables.size(); i++ ) {

            /* Check if the current Collectable is the targeted one */
            if ( storedCollectables.get( i ).equals( request.getTargetCollectableID() ) ) {

                replaceIndex = i;
                break;
            }
        }

        /* Validate the Collectable the user selected was found in the GeoCode */
        if ( replaceIndex == -1 ) {

            return new SwapCollectablesResponse( false );
        }

        /* Find all the available Collectables */
        UUID hold = null;
        var geocodeToUser = storedCollectables.get( replaceIndex );
        var temp = collectableService.getCollectables().getCollectables();
        for ( CollectableResponse collectableResponse : temp ) {

            var collectableID = collectableResponse.getId();
            if ( collectableID.equals( geocodeToUser ) ) {

                hold = collectableID;
            }
        }

        /* Validate the Collectable's ID was found */
        if ( hold == null ) {

            return new SwapCollectablesResponse( false );
        }

        /* Perform the swap */
        Collectable userToGeocode;
        try {
            userToGeocode = userService.swapCollectable( new SwapCollectableRequest( userID, hold, geocode.getId() ) ).getCollectable();
        } catch ( NullRequestParameterException error ) {

            /* Validate the Collectable returned */
            return new SwapCollectablesResponse( false );
        }

        userToGeocode.changeLocation( new GeoPoint( geocode.getLocation().getLatitude(), geocode.getLocation().getLongitude() ) );
        storedCollectables.set( replaceIndex, userToGeocode.getId() );
        geocode.setCollectables( storedCollectables );

        /* Update the table to contain the updated collectable */
        geoCodeRepo.save( geocode );

        if ( geocode.getEventID() != null ) {

            try {

                eventService.nextStage( geocode, userID );
            } catch ( NotFoundException | tech.geocodeapp.geocode.event.exceptions.InvalidRequestException | MismatchedParametersException e ) {

                /* A parameter (or the geocode's event ID) is null
                 * The user is not currently targeting this geocode
                 * There is no event matching the geocode's eventID */
                return new SwapCollectablesResponse( false );
            }

        }

        /*
         * Create and return a 'success' response
         */
        return new SwapCollectablesResponse( true );
    }

    /**
     * Updates the availability of a GeoCode
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified UpdateAvailabilityRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public UpdateAvailabilityResponse updateAvailability( UpdateAvailabilityRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( ( request.getGeoCodeID() == null ) || ( request.isIsAvailable() == null ) ) {

            throw new InvalidRequestException();
        }

        /* Find and set the GeoCode to the new Availability */
        Optional< GeoCode > temp = geoCodeRepo.findById( request.getGeoCodeID() );
        temp.ifPresent( geoCode -> geoCode.setAvailable( request.isIsAvailable() ) );

        /* Convert the optional to a GeoCode object */
        var hold = new GeoCode();
        if ( temp.isPresent() ) {

            hold = temp.get();
        } else {

            return new UpdateAvailabilityResponse( false );
        }

        /* Store the updated GeoCode in the repository */
        geoCodeRepo.save( hold );

        /*
         * Create the new response
         * and set the success of the operation
         */
        return new UpdateAvailabilityResponse( true );
    }

    /**
     * ToDo fix this to work with request and responds
     * <p>
     * Helper function that saves the given geocode into the repository
     *
     * @param geocode the GeoCode object to save
     */
    public void saveGeoCode( GeoCode geocode ) {

        geoCodeRepo.save( geocode );
    }

    /*----------- Helper functions -----------*/

    /**
     * Gets the collectables from a specified GeoCode
     *
     * @param temp the GeoCode object to get the collectable's from
     *
     * @return the collectables associated with the specified GeoCode
     */
    private ArrayList< Collectable > getCollectable( GeoCode temp ) {

        /* A list to store all the found collectables */
        ArrayList< Collectable > storedCollectable = new ArrayList<>();

        /* Get the id's of the collectables to find */
        List< UUID > collectableID = new ArrayList<>( temp.getCollectables() );
        for ( UUID uuid : collectableID ) {

            /* Create a request to the User service */
            GetCollectableByIDRequest req = new GetCollectableByIDRequest();
            req.setCollectableID( uuid );

            /* Find the collectable specified in the request and add it to the list */
            try {

                storedCollectable.add( collectableService.getCollectableByID( req ).getCollectable() );
            } catch ( NullRequestParameterException e ) {

                //ToDo remove this stack trace print and throw exception
                e.printStackTrace();
            }
        }

        /* return all the found collectables */
        return storedCollectable;
    }

    /**
     * ToDo make a unit test for this
     *
     * Determines what type of collectable to create
     *
     * NOTE: a collectable of Type Rarity is a user Trackable and will not be considered
     */
    public CollectableTypeComponent calculateCollectableType( List< CollectableTypeComponent > items ) {

        /* The total sample size */
        double total = 1000;

        /*
        * Go through each Collectable Type and assign a probability to it
        * depending on its Rarity
        */
        var probability = new ArrayList< Double >();
        for ( CollectableTypeComponent item : items ) {

            /* Check the rarity of the object to assign a value to it */
            double value = 0;
            switch ( item.getRarity() ) {

                case COMMON:
                    value = 600 / total;
                    break;

                case UNCOMMON:
                    value = 200 / total;
                    break;

                case RARE:
                    value = 150 / total;
                    break;

                case EPIC:
                    value = 40 / total;
                    break;

                case LEGENDARY:
                    value = 10 / total;
                    break;
                default:
                    value = 0;
            }

            /* Add the calculated value to the list */
            probability.add( value );
        }

        /* Create a random number between 0 and 1.0 */
        var random = ( new SecureRandom() ).nextDouble();
        var cumulativeProbability = 0.0;

         while ( true ) {

             /* Go through each entry in the list */
             for ( int x = 0; x < items.size(); x++ ) {

                 /* Make sure probability is still in range */
                 if ( probability.size() > x ) {

                     /* Check the cumulative probability */
                     cumulativeProbability += probability.get( x );
                     if ( random <= cumulativeProbability ) {

                         /* The object to return */
                         var type = items.get( x );

                         /* Ensure the Collectable is not a Users Trackable */
                         if ( type.getRarity().equals( UNIQUE ) ) {

                             /* The Collectable is a User trackable therefore redo the calculation */
                             type = calculateCollectableType( items );
                         }

                         return type;
                     }
                 }
             }
         }
    }

    /*----------- END -----------*/
}