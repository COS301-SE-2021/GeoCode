package tech.geocodeapp.geocode.geocode.service;

import org.springframework.stereotype.Service;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import tech.geocodeapp.geocode.collectable.manager.CollectableTypeManager;
import tech.geocodeapp.geocode.collectable.response.CollectableResponse;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.repository.GeoCodeRepository;
import tech.geocodeapp.geocode.geocode.exceptions.*;
import tech.geocodeapp.geocode.geocode.response.*;
import tech.geocodeapp.geocode.geocode.request.*;
import tech.geocodeapp.geocode.collectable.decorator.CollectableTypeComponent;
import tech.geocodeapp.geocode.collectable.model.*;
import tech.geocodeapp.geocode.collectable.request.CreateCollectableRequest;
import tech.geocodeapp.geocode.collectable.response.CreateCollectableResponse;
import tech.geocodeapp.geocode.collectable.service.*;
import tech.geocodeapp.geocode.user.service.*;

import java.security.SecureRandom;
import java.util.*;

/**
 * This class implements the UserService interface
 */
@Service( "GeoCodeService" )
@Validated
public class GeoCodeServiceImpl implements GeoCodeService {

    /**
     * The repository the GeoCode class interacts with
     */
    @NotNull( message = "GeoCode repository may not be null." )
    private final GeoCodeRepository geoCodeRepo;

    /**
     * The collectable service to access the use cases and
     * collectable repository
     */
    @NotNull( message = "Collectable Service Implementation may not be null." )
    private final CollectableService collectableService;

    /**
     * A handle to the user service
     */
    @NotNull( message = "User Service Implementation may not be null." )
    private final UserService userService;

    /**
     * The number of collectables to make when creating a new GeoCode
     */
    private final static int numCollectables = 5;

    /**
     * The length of the qr code for a new GeoCode
     */
    private final static int qrSize = 8;

    /**
     * Constructor
     *
     * @param geoCodeRepo the repo the created response attributes should save to
     */
    public GeoCodeServiceImpl( GeoCodeRepository geoCodeRepo, CollectableService collectableService, UserService userService ) throws RepoException {


        /* Check if the given repo exists */
        if ( geoCodeRepo != null ) {

            /* The repo exists therefore it can be set for the class */
            this.geoCodeRepo = geoCodeRepo;

            /* The subsystems service implementations  */
            this.collectableService = Objects.requireNonNull( collectableService, "Collectable service must not be null." );
            this.userService = Objects.requireNonNull( userService, "User service must not be null." );
        } else {

            /* The repo does not exist throw an error */
            throw new RepoException();
        }
    }

    /**
     * Create a new GeoCode and store it in the GeoCodeRepository
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly create response instance from the specified CreateGeoCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public CreateGeoCodeResponse createGeoCode( CreateGeoCodeRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( ( request.getLatitude() == null ) || ( request.getLongitude() == null ) ||
                ( request.getHints() == null ) || ( request.getDifficulty() == null ) ||
                ( request.getDescription() == null ) || ( request.isAvailable() == null ) ) {

            throw new InvalidRequestException();
        }

        /* Hold the crated Collectables */
        List< UUID > collectable = new ArrayList<>();

        for ( var x = 0; x < numCollectables; x++ ) {

            /* Create the response and give it a Collectable type */
            var collectableRequest = new CreateCollectableRequest();
            collectableRequest.setCollectableTypeId( UUID.fromString( "333599b9-94c7-403d-8389-83ed48387d13" ) );

            /* Get the response from the created request */
            CreateCollectableResponse collectableResponse = collectableService.createCollectable( collectableRequest );

            /* Building a collectable from a collectable response */
            var temp = new Collectable();
            temp.setId( collectableResponse.getCollectable().getId() );
            CollectableTypeComponent type = collectableResponse.getCollectable().getType();

            CollectableTypeManager manager = new CollectableTypeManager();

            temp.setType( manager.convertToCollectableType( type ) );

            /* Adding the created Collectable to the list */
            collectable.add( temp.getId() );
        }

        /* Try and create the relevant image with the newly create GeoCode instance */
        var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        // create StringBuffer size of AlphaNumericString
        var qr = new StringBuilder( qrSize );

        for ( var i = 0; i < qrSize; i++ ) {

            /* generate a random number between 0 to AlphaNumericString variable length
             * add Character one by one in end of sb */
            qr.append( chars.charAt( (  new SecureRandom() ).nextInt(chars.length() ) ) );
        }

        /*
         * Create the GeoCode object
         * and set its attributes to the given attributes in the request
         */
        var id = UUID.randomUUID();
        var newGeoCode = new GeoCode( id, request.getDifficulty(), request.isAvailable(),
                                      request.getDescription(), request.getHints(), collectable,
                                      qr.toString(), request.getLongitude(), request.getLatitude() );

        /* Save the created GeoCode to the repository */
        geoCodeRepo.save( newGeoCode );

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
        if ( geoCodeRepo.findById( id ).isPresent() ) {

            response.setIsSuccess( geoCodeRepo.findById( id ).get().getId().equals( id ) );
        } else {

            response.setIsSuccess( false );
        }

        return response;
    }

    /**
     * Get all the stored GeoCodes in the Repo
     *
     * @return the newly create response instance from the specified GetAllGeoCodesRequest
     */
    @Override
    public GetGeoCodesResponse getAllGeoCodes() {

        List< GeoCode > temp = geoCodeRepo.findAll();

        for ( GeoCode geoCode : temp ) {

            geoCode.setHints( null );
            geoCode.setQrCode( null );
            geoCode.setCollectables( null );
        }

        return new GetGeoCodesResponse( temp );
    }

    /**
     * Get tje stored Collectables inside of a GeoCode
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly create response instance from the specified GetCollectablesRequest
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

        Optional< GeoCode > temp = geoCodeRepo.findById( request.getGeoCodeID() );

        var hold = new GeoCode();
        if ( temp.isPresent( ) ) {

            hold = temp.get();
        }

        /*
         * Create the new response
         * and set the values
         */
        return new GetCollectablesResponse( new ArrayList<>( hold.getCollectables() ) );
    }

    /**
     * Get all the GeoCodes with a certain level of difficulty
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly create response instance from the specified GetGeoCodesByDifficultyRequest
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
        List<GeoCode> hold = new ArrayList<>();
        for ( GeoCode code: geoCodeRepo.findAll() ) {

            /* Check if the current GeoCode has the Difficulty wanted */
            if ( code.getDifficulty().equals( request.getDifficulty() ) ) {

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

        /*
         * Create the new response
         * and add valid GeoCodes to it
         */
        return new GetGeoCodesByDifficultyResponse( hold );
    }

    /**
     * Get the Hints of how to locate a GeoCode in the real world
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly create response instance from the specified GetHintsRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetHintsResponse getHints( GetHintsRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getGeoCodeID() == null ) {

            throw new InvalidRequestException();
        }

        Optional< GeoCode > temp = geoCodeRepo.findById( request.getGeoCodeID() );

        /*
         * Create the new response
         * and add the list of hints to it
         */
        var response = new GetHintsResponse();
        if ( temp.isEmpty() ) {

            Collection<String> hold = new ArrayList<>();
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
     * @return the newly create response instance from the specified GetGeoCodeByQRCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetGeoCodeByQRCodeResponse getGeocodeByQRCode( GetGeoCodeByQRCodeRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getQrCode() == null ) {

            throw new InvalidRequestException();
        }

        List< GeoCode > temp = geoCodeRepo.findAll();
        var x = 0;
        for ( ; x < temp.size(); x++ ) {

            if ( temp.get( x ).getQrCode().equals( request.getQrCode() ) ) {

                break;
            }
        }

        /*
         * Create the new response
         * and set values to it
         */
        return new GetGeoCodeByQRCodeResponse( temp.get( x ).getId(), temp.get( x ).isAvailable(),
                                               temp.get( x ).getDescription(), temp.get( x ).getLongitude(),
                                               temp.get( x ).getLatitude(), temp.get( x ).getDifficulty() );
    }

    /**
     * Finds the stored GeoCode associated at the given Location
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly create response instance from the specified GetGeoCodeByLocationRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     */
    @Override
    public GetGeoCodeByLocationResponse getGeoCodesByLocation( GetGeoCodeByLocationRequest request ) throws InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( ( request.getLatitude() == null ) || ( request.getLongitude() == null ) ) {

            throw new InvalidRequestException();
        }

        List< GeoCode > temp = geoCodeRepo.findAll();
        var x = 0;
        for ( ; x < temp.size(); x++ ) {

            if ( ( temp.get( x ).getLatitude().equals( request.getLatitude() ) ) && ( temp.get( x ).getLongitude().equals( request.getLongitude() ) ) ) {

                break;
            }
        }

        /*
         * Create the new response
         * and set its values
         */
        return new GetGeoCodeByLocationResponse( temp.get( x ).getId(), temp.get( x ).isAvailable(),
                                                 temp.get( x ).getDescription(), temp.get( x ).getLongitude(),
                                                 temp.get( x ).getLatitude(), temp.get( x ).getDifficulty() );
    }

    /**
     * Swaps a stored Collectable in a GeoCode with the Users GeoCode
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly create response instance from the specified SwapCollectablesRequest
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
            return new SwapCollectablesResponse().isSuccess( false );
        }

        var geocode = target.get();

        /* Find the target collectable */
        var replaceIndex = -1; //the index of the collectable we want to replace in the geocode
        List< UUID > storedCollectables = new ArrayList<>( geocode.getCollectables() );
        for ( var i = 0; i < storedCollectables.size(); i++ ) {

            if ( storedCollectables.get( i ).equals( request.getTargetCollectableID() ) ) {

                replaceIndex = i;
                break;
            }
        }

        if ( replaceIndex == -1 ) {

            return new SwapCollectablesResponse().isSuccess( false );
        }

        var geocodeToUser = storedCollectables.get( replaceIndex );
        var temp = collectableService.getCollectables().getCollectables();
        Collectable hold = null;
        for ( CollectableResponse collectableResponse : temp ) {

            if ( collectableResponse.getId().equals( geocodeToUser ) ) {

                hold = new Collectable();
                hold.setId( collectableResponse.getId() );
                //hold.setType( collectableResponse.getType() );
                //hold.setPastLocations( collectableResponse.getPastLocations() );
            }
        }

        if ( hold == null ) {

            return new SwapCollectablesResponse().isSuccess( false );
        }

        /* Perform the swap */
        var userToGeocode = userService.swapCollectable( hold );
        userToGeocode.changeLocation( geocode.getLatitude() + " " + geocode.getLongitude() );
        storedCollectables.set( replaceIndex, userToGeocode.getId() );
        geocode.setCollectables( storedCollectables );

        /* Update the table to contain the updated collectable */
        geoCodeRepo.save( geocode );

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
     * @return the newly create response instance from the specified UpdateAvailabilityRequest
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

        var hold = new GeoCode();
        if ( temp.isPresent( ) ) {

            hold = temp.get();
        }

        geoCodeRepo.save( hold );
        /*
         * Create the new response
         * and set the success of the operation
         */
        return new UpdateAvailabilityResponse( true );
    }

}