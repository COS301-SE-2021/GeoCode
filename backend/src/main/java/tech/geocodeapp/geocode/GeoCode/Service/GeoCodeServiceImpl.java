package tech.geocodeapp.geocode.GeoCode.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

import tech.geocodeapp.geocode.Collectable.Model.*;
import tech.geocodeapp.geocode.GeoCode.Model.GeoCode;
import tech.geocodeapp.geocode.GeoCode.Repository.GeoCodeRepository;
import tech.geocodeapp.geocode.GeoCode.Exceptions.*;
import tech.geocodeapp.geocode.GeoCode.Response.*;
import tech.geocodeapp.geocode.GeoCode.Request.*;
import tech.geocodeapp.geocode.GeoCode.Response.GetCollectablesResponse;
import tech.geocodeapp.geocode.Trackable.Request.*;
import tech.geocodeapp.geocode.Trackable.Response.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * This class implements the UserService interface
 */
@Service( "GeoCodeService" )
//@RequiredArgsConstructor
public class GeoCodeServiceImpl implements GeoCodeService {

    /**
     * The repository the GeoCode class interacts with
     */
    private final GeoCodeRepository geoCodeRepo;

    /**
     * Constructor
     *
     * @param geoCodeRepo the repo the created response attributes should save to
     */
    public GeoCodeServiceImpl( GeoCodeRepository geoCodeRepo ) throws RepoException {

        /* Check if the given repo exists */
        if ( geoCodeRepo != null ) {

            /* The repo exists therefore it can be set for the class */
            this.geoCodeRepo = geoCodeRepo;
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
     * @throws RepoException an error occurred when trying to access the repo
     */
    @Override
    public CreateGeoCodeResponse createGeoCode( CreateGeoCodeRequest request ) throws InvalidRequestException, RepoException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( ( request.getLatitude() == null ) || ( request.getLongitude() == null ) ||
                    ( request.getHints() == null ) || ( request.getDifficulty() == null ) ||
                    ( request.getDescription() == null ) || ( request.isAvailable() == null ) ) {

            throw new InvalidRequestException();
        }

        /*
         * Create the GeoCode object
         * and set its attributes to the given attributes in the request
         */
        GeoCode newGeoCode = new GeoCode();
        newGeoCode.setAvailable( request.isAvailable() );
        newGeoCode.setDescription( request.getDescription() );
        newGeoCode.setDifficulty( request.getDifficulty() );
        newGeoCode.setHints( request.getHints() );
        newGeoCode.setLatitude( request.getLatitude() );
        newGeoCode.setLongitude( request.getLongitude() );
        UUID id = UUID.randomUUID();
        newGeoCode.setId( id );

        List <Collectable > collectable = new ArrayList<>();

        for ( int x = 0; x < 5; x++ ) {

            collectable.add( new Collectable( new CollectableType( "name", "imageURL", Rarity.COMMON, new CollectableSet( "setName", "description" ), null ) ) );
        }

        newGeoCode.setCollectables( collectable );

        /* Try and create the relevant image with the newly create GeoCode instance */
        try {

            int size = 8;
            String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";


            // create StringBuffer size of AlphaNumericString
            StringBuilder QR = new StringBuilder( size );

            for ( int i = 0; i < size; i++ ) {

                /* generate a random number between 0 to AlphaNumericString variable length */
                int index = (  new SecureRandom() ).nextInt(chars.length() );

                /* add Character one by one in end of sb */
                QR.append( chars.charAt( index ) );
            }

            newGeoCode.setQrCode( QR.toString() );
        } catch ( Exception e ) {

            e.printStackTrace();
        }

        /*
         * Check the repo exists before trying to access it
         */
        if ( geoCodeRepo != null ) {

            /* Save the created GeoCode to the repository */
            geoCodeRepo.save( newGeoCode );
        } else {

            throw new RepoException( "Could not save to the repository." );
        }

        /*
         * Create the new response
         * and add the created GeoCode to it
         */
        CreateGeoCodeResponse response = new CreateGeoCodeResponse();

        /*if ( ( geoCodeRepo.findById( id ).get().getId().equals( id ) ) ) {

            response.setIsSuccess( true );
        } else {

            response.setIsSuccess( false );
        }*/
        response.setIsSuccess( true );

        return response;
    }

    /**
     * Get all the stored GeoCodes in the Repo
     *
     * @return the newly create response instance from the specified GetAllGeoCodesRequest
     *
     * @throws RepoException there was an issue accessing the repository
     */
    @Override
    public GetGeoCodesResponse getAllGeoCodes() throws RepoException {

        /* Validate the repo */
        checkRepo();

        List< GeoCode > temp = geoCodeRepo.findAll();

        for ( GeoCode geoCode : temp ) {

            geoCode.setLatitude( null );
            geoCode.setLongitude( null );
            geoCode.setHints( null );
            geoCode.setQrCode( null );
            geoCode.setCollectables( null );
        }

        GetGeoCodesResponse response = new GetGeoCodesResponse();
        response.setGeocodes( temp );

        return response;
    }

    /**
     * Get tje stored Collectables inside of a GeoCode
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly create response instance from the specified GetCollectablesRequest
     */
    @Override
    public GetCollectablesResponse getCollectables( GetCollectablesRequest request ) throws InvalidRequestException, RepoException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getGeoCodeID() == null ) {

            throw new InvalidRequestException();
        }

        /* Validate the repo */
        checkRepo();

        Optional< GeoCode > temp = geoCodeRepo.findById( request.getGeoCodeID() );

        GeoCode hold = new GeoCode();
        if ( temp.isPresent( ) ) {

            hold = temp.get();
        }

        /*
         * Create the new response
         * and set the values
         */
        GetCollectablesResponse response = new GetCollectablesResponse();
        response.setCollectables( hold.getCollectables() );

        return response;
    }

    /**
     * Get all the GeoCodes with a certain level of difficulty
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly create response instance from the specified GetGeoCodesByDifficultyRequest
     */
    @Override
    public GetGeoCodesByDifficultyResponse getGeoCodesByDifficulty( GetGeoCodesByDifficultyRequest request ) throws InvalidRequestException, RepoException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getDifficulty() == null ) {

            throw new InvalidRequestException();
        }

        /* Validate the repo */
        checkRepo();

        /*
         * Sort through the stored GeoCodes and
         * find all the GeoCodes with the specified difficulty
         */
        List<GeoCode> hold = new ArrayList<>();
        for ( GeoCode code: geoCodeRepo.findAll() ) {

            /* Check if the current GeoCode has the Difficulty wanted */
            if ( code.getDifficulty().equals( request.getDifficulty() ) ) {

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
        GetGeoCodesByDifficultyResponse response = new GetGeoCodesByDifficultyResponse();
        response.setGeocodes( hold );

        return response;
    }

    /**
     * Get the Hints of how to locate a GeoCode in the real world
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly create response instance from the specified GetHintsRequest
     */
    @Override
    public GetHintsResponse getHints( GetHintsRequest request ) throws InvalidRequestException, RepoException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getGeoCodeID() == null ) {

            throw new InvalidRequestException();
        }

        /* Validate the repo */
        checkRepo();

        Optional< GeoCode > temp = geoCodeRepo.findById( request.getGeoCodeID() );

        /*
         * Create the new response
         * and add the list of hints to it
         */
        GetHintsResponse response = new GetHintsResponse();
        if ( temp.isEmpty() ) {

            List<String> hold = new ArrayList<>();
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
     */
    @Override
    public GetGeoCodeByQRCodeResponse getGeocodeByQRCode( GetGeoCodeByQRCodeRequest request ) throws InvalidRequestException, RepoException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( request.getQrCode() == null ) {

            throw new InvalidRequestException();
        }

        /* Validate the repo */
        checkRepo();

        List< GeoCode > temp = geoCodeRepo.findAll();
        int x = 0;
        for ( x = 0; x < temp.size(); x++ ) {

            if ( temp.get( x ).getQrCode().equals( request.getQrCode() ) ) {

                break;
            }
        }

        /*
         * Create the new response
         *
         */
        GetGeoCodeByQRCodeResponse response = new GetGeoCodeByQRCodeResponse();
        response.setDescription( temp.get( x ).getDescription() );
        response.setDifficulty( temp.get( x ).getDifficulty() );
        response.setId( temp.get( x ).getId() );
        response.setLatitude( temp.get( x ).getLatitude() );
        response.setLongitude( temp.get( x ).getLongitude() );
        response.setAvailable( temp.get( x ).isAvailable() );

        return response;
    }

    /**
     * Finds the stored GeoCode associated at the given Location
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly create response instance from the specified GetGeoCodeByLocationRequest
     */
    @Override
    public GetGeoCodeByLocationResponse getGeoCodesByLocation( GetGeoCodeByLocationRequest request ) throws InvalidRequestException, RepoException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( ( request.getLatitude() == null ) || ( request.getLongitude() == null ) ) {

            throw new InvalidRequestException();
        }

        /* Validate the repo */
        checkRepo();

        List< GeoCode > temp = geoCodeRepo.findAll();
        int x = 0;
        for ( x = 0; x < temp.size(); x++ ) {

            if ( ( temp.get( x ).getLatitude().equals( request.getLatitude() ) ) && ( temp.get( x ).getLongitude().equals( request.getLongitude() ) ) ) {

                break;
            }
        }

        /*
         * Create the new response
         *
         */
        GetGeoCodeByLocationResponse response = new GetGeoCodeByLocationResponse();
            response.setDescription( temp.get( x ).getDescription() );
            response.setDifficulty( temp.get( x ).getDifficulty() );
            response.setId( temp.get( x ).getId() );
            response.setLatitude( temp.get( x ).getLatitude() );
            response.setLongitude( temp.get( x ).getLongitude() );
            response.setAvailable( temp.get( x ).isAvailable() );

        return response;
    }

    /**
     *  Gets the Trackables stored in the GeoCode
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly create response instance from the specified GetTrackablesRequest
     */
    @Override
    public GetTrackablesResponse getTrackables(GetTrackablesRequest request ) throws InvalidRequestException, RepoException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( ( request.getDifficulty() != null ) || ( request.getDescription() == null ) ) {

            throw new InvalidRequestException();
        }

        /* Validate the repo */
        checkRepo();

        /*
         * Create the new response
         *
         */
        GetTrackablesResponse response = new GetTrackablesResponse();
        response.setQrCode( "test" );

        return response;
    }

    /**
     * Swaps a stored Collectable in a GeoCode with the Users GeoCode
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly create response instance from the specified SwapCollectablesRequest
     */
    @Override
    public SwapCollectablesResponse swapCollectables( SwapCollectablesRequest request ) throws InvalidRequestException, RepoException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( ( request.getTargetCollectableID() == null ) || ( request.getTargetGeoCodeID() == null ) || ( request.getGeoCodeID() == null ) ) {

            throw new InvalidRequestException();
        }

        /* Validate the repo */
        checkRepo();

        /*
         * Create the new response
         */
        SwapCollectablesResponse response = new SwapCollectablesResponse();
        response.setIsSuccess( true );

        return response;
    }

    /**
     * Updates the availability of a GeoCode
     *lombok
     * @param request the attributes the response should be created from
     *
     * @return the newly create response instance from the specified UpdateAvailabilityRequest
     */
    @Override
    public UpdateAvailabilityResponse updateAvailability( UpdateAvailabilityRequest request ) throws RepoException, InvalidRequestException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( true );
        } else if ( ( request.getGeoCodeID() == null ) || ( request.isIsAvailable() == null ) ) {

            throw new InvalidRequestException();
        }

        /* Validate the repo */
        checkRepo();

        /* Find and set the GeoCode to the new Availability */
        Optional< GeoCode > temp = geoCodeRepo.findById( request.getGeoCodeID() );
        temp.ifPresent( geoCode -> geoCode.setAvailable( request.isIsAvailable() ) );

        GeoCode hold = new GeoCode();
        if ( temp.isPresent( ) ) {

            hold = temp.get();
        }

        geoCodeRepo.save( hold );
        /*
         * Create the new response
         * and set the success of the operation
         */
        UpdateAvailabilityResponse response = new UpdateAvailabilityResponse();
        response.setIsSuccess( true );

        return response;
    }


    ////////////////Helper functions////////////////

    private void checkRepo() throws RepoException {

        if ( geoCodeRepo == null ) {

            throw new RepoException();
        }
    }

    /**
     * This helper function helps create the QR Code image and stores
     * it in the QRImages folder.
     *
     * @param imageName the name to label the jpg image to
     *
     * @return the unique Identifier to indicate the GeoCode
     *
     * @throws IOException the file path or image name given was invalid
     * @throws WriterException the image could not be created
     */
    public String createQR( String imageName ) throws IOException, WriterException {

        /* The file path the image should be created in */
        String path = "src/main/java/tech/geocodeapp/geocode/GeoCode/QRImages/"+ imageName + ".jpg";

        /* Create the QR Code and link it to the specified website */
        BitMatrix matrix = new MultiFormatWriter().encode( "https://www.example.com/", BarcodeFormat.QR_CODE, 400, 400 );

        /* Create the image and store it in the given path */
        MatrixToImageWriter.writeToPath( matrix, "jpg", Paths.get( path ) );

        return "AAAA";
    }

}