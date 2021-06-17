package tech.geocodeapp.geocode.GeoCode.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import io.swagger.model.*;
import tech.geocodeapp.geocode.GeoCode.Exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.GeoCode.Exceptions.QRCodeException;
import tech.geocodeapp.geocode.GeoCode.Exceptions.RepoException;
import tech.geocodeapp.geocode.GeoCode.Repository.GeoCodeRepository;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


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
    public GeoCodeServiceImpl( GeoCodeRepository geoCodeRepo ) {

        this.geoCodeRepo = geoCodeRepo;
    }

    /**
     * Create a new GeoCode and store it in the GeoCodeRepository
     *
     * @param request the attributes the response should be created from
     *
     * @return the newly create response instance from the specified CreateGeoCodeRequest
     *
     * @throws InvalidRequestException the provided request was invalid and resulted in an error being thrown
     * @throws QRCodeException an error occurred when attempting to create the QR Image
     */
    @Override
    public CreateGeoCodeResponse createGeoCode( CreateGeoCodeRequest request ) throws InvalidRequestException, QRCodeException, RepoException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( "The given request is empty." );
        } else if ( ( request.getLocation() == null ) || ( request.getHints() == null ) ||
                    ( request.getDifficulty() == null ) || ( request.getDescription() == null ) ||
                    ( request.isAvailable() == null ) ) {

            throw new InvalidRequestException( "The given request is missing parameter/s." );
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
        newGeoCode.setLocation( request.getLocation() );

        Collectable collectable = new Collectable(new CollectableType("name", "imageURL", Rarity.COMMON, new CollectableSet("setName", "description"), null ) );
        newGeoCode.setCollectables( collectable );

        /* Try and create the relevant image with the newly create GeoCode instance */
        try {

            /*
             * Create the image with the specified name
             * and set the GeoCode to the create QR Code
             */
            newGeoCode.setQrCode( createQR( "QRCode" ) );
        } catch ( IOException | WriterException e ) {

            throw new QRCodeException( "The QR Code could not be created." );
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
        response.setGeoCode( newGeoCode );

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
    public GetGeoCodesResponse getAllGeoCodes( ) throws RepoException {

        /* Validate the repo */
        if ( geoCodeRepo == null ) {

            throw new RepoException( "The GeoCode Repository is empty." );
        }

        return new GetGeoCodesResponse().geocodes(geoCodeRepo.findAll());
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

            throw new InvalidRequestException( "The given request is empty." );
        } /*else if ( ( request().getID != null ) || ( request.getDescription() == null ) ) {

            throw new InvalidRequestException( "The given request is missing parameter/s." );
        }*/

        /* Validate the repo */
        if ( geoCodeRepo == null ) {

            throw new RepoException( "The GeoCode Repository is empty." );
        }

        /*
         * Create the new response
         *
         */
        GetCollectablesResponse response = new GetCollectablesResponse();


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

            throw new InvalidRequestException( "The given request is empty." );
        } else if ( request.getDifficulty() == null ) {

            throw new InvalidRequestException( "The given request is missing parameter/s." );
        }

        /* Validate the repo */
        if ( geoCodeRepo == null ) {

            throw new RepoException( "The GeoCode Repository is empty." );
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

            throw new InvalidRequestException( "The given request is empty." );
        } else if ( ( request.getGeoCode() == null ) || ( request.getGeoCode().getHints() == null ) ) {

            throw new InvalidRequestException( "The given request is missing parameter/s." );
        }

        /* Validate the repo */
        if ( geoCodeRepo == null ) {

            throw new RepoException( "The GeoCode Repository is empty." );
        }

        /*
         * Create the new response
         * and add the list of hints to it
         */
        GetHintsResponse response = new GetHintsResponse();
        response.setHints( request.getGeoCode().getHints() );

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

            throw new InvalidRequestException( "The given request is empty." );
        } else if ( ( request.getDifficulty() != null ) || ( request.getDescription() == null ) ) {

            throw new InvalidRequestException( "The given request is missing parameter/s." );
        }

        /* Validate the repo */
        if ( geoCodeRepo == null ) {

            throw new RepoException( "The GeoCode Repository is empty." );
        }

        /*
         * Create the new response
         *
         */
        GetGeoCodeByQRCodeResponse response = new GetGeoCodeByQRCodeResponse();


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

            throw new InvalidRequestException( "The given request is empty." );
        } else if ( ( request.getDifficulty() != null ) || ( request.getDescription() == null ) ) {

            throw new InvalidRequestException( "The given request is missing parameter/s." );
        }

        /* Validate the repo */
        if ( geoCodeRepo == null ) {

            throw new RepoException( "The GeoCode Repository is empty." );
        }

        /*
         * Create the new response
         *
         */
        GetGeoCodeByLocationResponse response = new GetGeoCodeByLocationResponse();


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
    public GetTrackablesResponse getTrackables( GetTrackablesRequest request ) throws InvalidRequestException, RepoException {

        /* Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( "The given request is empty." );
        } else if ( ( request.getDifficulty() != null ) || ( request.getDescription() == null ) ) {

            throw new InvalidRequestException( "The given request is missing parameter/s." );
        }

        /* Validate the repo */
        if ( geoCodeRepo == null ) {

            throw new RepoException( "The GeoCode Repository is empty." );
        }

        /*
         * Create the new response
         *
         */
        GetTrackablesResponse response = new GetTrackablesResponse();


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

            throw new InvalidRequestException( "The given request is empty." );
        } else if ( ( request.getCollectable() != null ) || ( request.getGeoCode() == null ) ) {

            throw new InvalidRequestException( "The given request is missing parameter/s." );
        }

        /* Validate the repo */
        if ( geoCodeRepo == null ) {

            throw new RepoException( "The GeoCode Repository is empty." );
        }

        /*
         * Create the new response
         */
        SwapCollectablesResponse response = new SwapCollectablesResponse();
        response.setSuccess( true );

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

            throw new InvalidRequestException( "The given request is empty." );
        } else if ( ( request.getGeoCode() == null ) || ( request.isAvailable() == null ) ) {

            throw new InvalidRequestException( "The given request is missing parameter/s." );
        }

        /* Validate the repo */
        if ( geoCodeRepo == null ) {

            throw new RepoException( "The GeoCode Repository is empty." );
        }

        request.getGeoCode().setAvailable( request.isAvailable() );
        /*
         * Create the new response
         * and set the success of the operation
         */
        UpdateAvailabilityResponse response = new UpdateAvailabilityResponse();
        response.setSuccess( true );

        return response;
    }


    ////////////////Helper functions////////////////

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