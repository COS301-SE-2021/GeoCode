package tech.geocodeapp.geocode.geocode.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import tech.geocodeapp.geocode.collectable.decorator.CollectableTypeComponent;
import tech.geocodeapp.geocode.collectable.manager.CollectableTypeManager;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.collectable.model.Rarity;
import tech.geocodeapp.geocode.collectable.request.CreateCollectableRequest;
import tech.geocodeapp.geocode.collectable.request.GetCollectableByIDRequest;
import tech.geocodeapp.geocode.collectable.response.CreateCollectableResponse;
import tech.geocodeapp.geocode.collectable.service.CollectableService;

import tech.geocodeapp.geocode.event.exceptions.MismatchedParametersException;
import tech.geocodeapp.geocode.event.exceptions.NotFoundException;
import tech.geocodeapp.geocode.event.service.EventService;

import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.general.security.CurrentUserDetails;
import tech.geocodeapp.geocode.geocode.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.geocode.repository.GeoCodeRepository;
import tech.geocodeapp.geocode.geocode.exceptions.RepoException;
import tech.geocodeapp.geocode.geocode.model.*;
import tech.geocodeapp.geocode.geocode.request.*;
import tech.geocodeapp.geocode.geocode.response.*;

import tech.geocodeapp.geocode.user.request.AddToOwnedGeoCodesRequest;
import tech.geocodeapp.geocode.user.request.GetUserByIdRequest;
import tech.geocodeapp.geocode.user.request.SwapCollectableRequest;
import tech.geocodeapp.geocode.user.service.UserService;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.security.SecureRandom;
import java.util.*;

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

    private String invalidGeoCodeIdMessage = "Invalid GeoCode ID";

    /**
     * Overloaded Constructor
     *
     * @param geoCodeRepo        the repo the created response attributes should save to
     * @param collectableService access to the collectable use cases and repository
     * @param userService        access to the user use cases and repository
     * @param eventService       access to the Event use cases and repository
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
     * <p>
     * This is to avoid circular dependencies as each subsystem requires one another
     */
    @PostConstruct
    public void init() {

        //userService.setGeoCodeService( this );
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
        List<UUID> collectables = new ArrayList<>();

        var eventComponent = request.getEventComponent();

        /* Blockly Event GeoCodes have no Collectables added to them.
        * So only Collectables if the current GeoCode is not part of a
        * Blockly Event
        * */
        if( eventComponent == null || !eventComponent.isBlocklyEvent() ) {
            /* Get all the stored Collectables */
            var collectableTypes = collectableService.getCollectableTypes();

            /* Create the specified amount of new collectables */
            for (var x = 0; x < NUM_COLLECTABLES; x++) {

                /* Create the response and give it a Collectable type */
                var collectableRequest = new CreateCollectableRequest();

                CollectableTypeComponent typeList;
                if (collectableTypes != null) {

                    /* Get first stored Collectable type */
                    typeList = calculateCollectableType(collectableTypes.getCollectableTypes());

                    /* Check if the Collectable Type was found */
                    if (typeList != null) {

                        /* Get and set the collectable request with the type and location */
                        collectableRequest.setCollectableTypeId(typeList.getId());
                        collectableRequest.setLocation(request.getLocation());

                        /* Set createMission to true if the collectable type has a mission type */
                        collectableRequest.setCreateMission(typeList.getMissionType() != null);
                    } else {

                        /* Exception thrown when trying to get Collectable */
                        return new CreateGeoCodeResponse(false, "Collectable Type not found");
                    }
                } else {

                    /* Exception thrown when trying to get Collectable */
                    return new CreateGeoCodeResponse(false, "Collectable Types not found");
                }

                CreateCollectableResponse collectableResponse;
                try {

                    /* Get the response from the created request */
                    collectableResponse = collectableService.createCollectable(collectableRequest);
                } catch (NullRequestParameterException e) {

                    /* Exception thrown therefore creation failed */
                    return new CreateGeoCodeResponse(false, e.getMessage());
                }

                /* Building a collectable from a collectable response */
                var temp = new Collectable();

                temp.setId(collectableResponse.getCollectable().getId());

                CollectableTypeManager manager = new CollectableTypeManager();

                // temp.setType( manager.convertToCollectableType( type ) );
                temp.setType(manager.convertToCollectableType(typeList));

                /* Adding the created Collectable to the list */
                collectables.add(temp.getId());
            }
        }

        /* The characters the qrCode must be made up of */
        /* Avoid l, 1, 0 and O as they are ambiguous */
        var chars = "23456789ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";

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
         * and set its attributes to the given attributes in the request.
         * ID is optionally passed
         */
        var id = request.getId() != null ? request.getId() : UUID.randomUUID();

        /*
         * Get the user who is creating the GeoCode
         */
        var createdBy = userService.getCurrentUser();

        /* Create the GeoCode Object */
        var newGeoCode = new GeoCode( id, request.getDifficulty(), request.isAvailable(),
                                      request.getDescription(), request.getHints(), collectables,
                                      qr.toString(), request.getLocation(), createdBy.getId() );

        /* set the EventID if the EventComponent was provided */
        if( eventComponent != null ){
            newGeoCode.setEventID( eventComponent.getID() );
        }

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
                return new CreateGeoCodeResponse( false, "The GeoCode did not save properly" );
            }

            /* Update the geocode now that it has been saved */
            newGeoCode = check;

            /*
             * Add the GeoCode to the list of GeoCodes that the user has created
             */
            try {
                AddToOwnedGeoCodesRequest ownedGeoCodesRequest = new AddToOwnedGeoCodesRequest(createdBy, check);
                userService.addToOwnedGeoCodes(ownedGeoCodesRequest);
            } catch (NullRequestParameterException e) {

                return new CreateGeoCodeResponse(false, e.getMessage());
            }
        } catch ( IllegalArgumentException error ) {

            /* Exception thrown therefore creation failed */
            return new CreateGeoCodeResponse( false, error.getMessage() );
        }

        /*
         * Create the new response
         * and add the created GeoCode to it
         */
        return new CreateGeoCodeResponse( true, "GeoCode created", newGeoCode );
    }

    /**
     * Update a stored GeoCode
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly created response instance from the specified CreateGeoCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public UpdateGeoCodeResponse updateGeoCode( UpdateGeoCodeRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getGeoCodeID() == null ) {

            /* No GeoCode */
            throw new InvalidRequestException();
        } else if ( ( request.getHints() == null ) && ( request.getDifficulty() == null ) &&
                ( request.getDescription() == null ) && ( request.isAvailable() == null ) ) {

            /* No attribute specified to update */
            throw new InvalidRequestException();
        }

        /* Get the GeoCode to update */
        GeoCode updateGeoCode = null;
        var geoCode = geoCodeRepo.findById( request.getGeoCodeID() );
        if ( geoCode.isPresent() ) {

            updateGeoCode = geoCode.get();
        }

        if ( updateGeoCode == null ) {

            return new UpdateGeoCodeResponse( false, "The GeoCode was not found" );
        }

        /* Determine which attribute to update and update it */
        if( request.getHints() != null ) {

            updateGeoCode.setHints( request.getHints() );
        }
        if ( ( request.getDifficulty() != null ) && ( updateGeoCode.getEventID() == null ) ) {

            updateGeoCode.setDifficulty( request.getDifficulty() );
        }
        if ( request.getDescription() != null ) {

            updateGeoCode.setDescription( request.getDescription() );
        }
        if ( request.isAvailable() != null ) {

            updateGeoCode.setAvailable( request.isAvailable() );
        }

        var checkGeoCode = geoCodeRepo.save( updateGeoCode );
        if ( !checkGeoCode.getId().equals( request.getGeoCodeID() ) ) {

            return new UpdateGeoCodeResponse( false, "The GeoCode could not be update" );
        }

        /* Return the GeoCode was successfully updated */
        return new UpdateGeoCodeResponse( true, "GeoCode updated" );
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
            GeoCode geocode = temp.get();

            /* Check if the found GeoCode has the correct qrCode */
            if ( geocode.getQrCode().equals( request.getQrCode() ) ) {

                /* Get the collectables from the found GeoCode */
                ArrayList< Collectable > storedCollectable = getCollectable( geocode );

                /* Set the response to save the found collectables */
                response.setStoredCollectable( storedCollectable );

                /* if this geocode has an event ID and no collectables, it is in a Blockly Event. Move to the next stage */
                if ( geocode.getEventID() != null && storedCollectable != null && storedCollectable.isEmpty() ) {
                    try {
                        eventService.nextStage( geocode, CurrentUserDetails.getID() );

                    } catch (tech.geocodeapp.geocode.event.exceptions.InvalidRequestException | NotFoundException | MismatchedParametersException e) {
                        response.setStoredCollectable(null);
                    }
                }
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
    public GetGeoCodesByLocationResponse getGeoCodesByLocation(GetGeoCodesByLocationRequest request ) throws InvalidRequestException {

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
        return new GetGeoCodesByLocationResponse( temp.get( x ).getId(), temp.get( x ).isAvailable(),
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
    public GetCollectablesInGeoCodesByLocationResponse getCollectablesInGeoCodesByLocation(GetCollectablesInGeoCodesByLocationRequest request ) throws InvalidRequestException {

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

        /* Get the collectables from the found GeoCode */
        ArrayList< Collectable > storedCollectables = getCollectable( temp.get( x ) );

        /*
         * Create the new response
         * and set its values
         */
        return new GetCollectablesInGeoCodesByLocationResponse( storedCollectables );
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

            return new SwapCollectablesResponse( false, invalidGeoCodeIdMessage );
        }

        /* Get the stored GeoCode */
        var geocode = target.get();

        /* Validate if the user trying to access the GeoCode created it
         * if the user created the GeoCode do not allow the swap as it will be unfair
         * else continue as the user found the GeoCode fairly
         */
        var user = userService.getCurrentUser();

        if ( user == null ) {
            return new SwapCollectablesResponse( false,  "No user is logged in");
        }
        var userID = user.getId();

        if( ( geocode.getCreatedBy().equals( userID ) ) ){
            return new SwapCollectablesResponse(false, "User tried to swap a Collectable out of a GeoCode that they created");
        }

        /* Find the target collectable in the GeoCode */

        //check if the targetCollectableID is invalid
        if ( !geocode.getCollectables().contains( request.getTargetCollectableID() ) ) {
            return new SwapCollectablesResponse( false, "Target Collectable is not in the target GeoCode" );
        }

        /* Get the Collectable that must be swapped out and given to the User */
        var geocodeToUserID = request.getTargetCollectableID();
        Collectable geocodeToUser;

        var getCollectableByIdRequest = new GetCollectableByIDRequest( geocodeToUserID );

        try {
            var getCollectableByIdResponse = collectableService.getCollectableByID( getCollectableByIdRequest );

            /* Validate the Collectable's ID was found */
            if ( !getCollectableByIdResponse.isSuccess() ) {
                return new SwapCollectablesResponse( false, getCollectableByIdResponse.getMessage() );
            }

            geocodeToUser = getCollectableByIdResponse.getCollectable();
        } catch ( NullRequestParameterException e ) {

            return new SwapCollectablesResponse( false, e.getMessage() );
        }

        /* Perform the swap */
        Collectable userToGeocode;

        try {
            //pass fresh objects to the swapCollectable
            var userObj = userService.getUserById(new GetUserByIdRequest(user.getId())).getUser();
            var geocodeToUserObj = collectableService.getCollectableByID(new GetCollectableByIDRequest(geocodeToUser.getId())).getCollectable();
            var geocodeObj = geoCodeRepo.findById(geocode.getId()).get();

            userToGeocode = userService.swapCollectable( new SwapCollectableRequest( userObj, geocodeToUserObj, geocodeObj ) ).getCollectable();
        } catch ( NullRequestParameterException error ) {

            /* Validate the Collectable returned */
            return new SwapCollectablesResponse( false, error.getMessage() );
        }

        //change the location of the Collectable going into the GeoCode
        userToGeocode.changeLocation( new GeoPoint( geocode.getLocation().getLatitude(), geocode.getLocation().getLongitude() ) );

        //swap in the Collectable that is the User's old current Collectable
        var storedCollectables = new ArrayList<>( geocode.getCollectables() );
        var replaceIndex = storedCollectables.indexOf( geocodeToUserID );

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
                return new SwapCollectablesResponse( false, e.getMessage() );
            }

        }

        /*
         * Create and return a 'success' response
         */
        return new SwapCollectablesResponse( true, "Collectable successfully swapped" );
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

                /* An exception was thrown */
                return null;
            }
        }

        /* return all the found collectables */
        return storedCollectable;
    }

    /**
     * Determines what type of collectable to create
     * <p>
     * NOTE: a collectable type with an all-zero ID is a user Trackable and will not be considered
     */
    public CollectableTypeComponent calculateCollectableType( List< CollectableTypeComponent > items ) {

        var noTypesAvailable = ( items.isEmpty() );
        var onlyUserTrackable = ( items.size() == 1 && items.get(0).getId().equals( new UUID( 0, 0 ) ) );
        if ( noTypesAvailable || onlyUserTrackable ) {
            /* No eligible items */
            return null;
        }

        /* Create a random number between 0 and 1.0 */
        var random = Math.random();
        var cumulativeProbability = 0.0;

        try {

            /* Go through each rarity */
            for ( Rarity rarity : Rarity.values() ) {

                /* Check the cumulative probability */
                cumulativeProbability += rarity.getProbability();
                if ( random <= cumulativeProbability ) {

                    /* The current rarity has been selected */

                    /* Find items from the list that have the selected rarity */
                    var filtered = new ArrayList< CollectableTypeComponent >();
                    for ( CollectableTypeComponent item : items ) {

                        if ( rarity.equals( item.getRarity() ) ) {

                            filtered.add( item );
                        }
                    }

                    if ( filtered.size() == 0 ) {

                        /* No items of the selected rarity were found. Try again to generate an item */
                        return calculateCollectableType( items );
                    }

                    /* The index of the object to return */
                    var randomIndex = ( int ) ( ( Math.random() ) * filtered.size() );
                    var type = filtered.get( randomIndex );

                    /* Ensure the Collectable is not a Users Trackable */
                    if ( type.getId().equals( new UUID( 0, 0 ) ) ) {

                        /* The Collectable is a User trackable therefore redo the calculation */
                        return calculateCollectableType( items );
                    }

                    return type;
                }
            }

            /* If we fail to find a value, try again */
            return calculateCollectableType( items );

        } catch ( StackOverflowError e ) {

            /* If we generate a stack overflow from the recursion when retrying, return null */
            return null;
        }
    }
    /*----------- END -----------*/

}