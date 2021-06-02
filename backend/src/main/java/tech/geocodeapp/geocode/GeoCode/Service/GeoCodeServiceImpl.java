package tech.geocodeapp.geocode.GeoCode.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

import io.swagger.model.*;
import tech.geocodeapp.geocode.GeoCode.Exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.GeoCode.Exceptions.QRCodeException;
import tech.geocodeapp.geocode.GeoCode.Exceptions.RepoException;
import tech.geocodeapp.geocode.GeoCode.Repository.GeoCodeRepository;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ListIterator;


/**
 * This class implements the UserService interface
 */
@Service( "GeoCodeService" )
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

        /** Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( "The given request is empty." );
        } else if ( ( request.getLocation() == null ) || ( request.getHints() == null ) ||
                    ( request.getDifficulty() == null ) || ( request.getDescription() == null ) ||
                    ( request.isAvailable() == null ) || ( request.getId() == null ) ) {

            throw new InvalidRequestException( "The given request is missing parameter/s." );
        }

        /**
         * Create the GeoCode object
         * and set its attributes to the given attributes in the request
         */
        GeoCode newGeoCode = new GeoCode();
        newGeoCode.setId( request.getId() );
        newGeoCode.setAvailable( request.isAvailable() );
        newGeoCode.setDescription( request.getDescription() );
        newGeoCode.setDifficulty( request.getDifficulty() );
        newGeoCode.setHints( request.getHints() );
        newGeoCode.setLocation( request.getLocation() );

        /** Try and create the relevant image with the newly create GeoCode instance */
        try {

            /**
             * Create the image with the specified name
             * and set the GeoCode to the create QR Code
             * */
            newGeoCode.setQrCode( createQR( "QRCode" ) );
        } catch ( IOException | WriterException e ) {

            throw new QRCodeException( "The QR Code could not be created." );
        }

        /**
         * Check the repo exists before trying to access it
         */
        if ( geoCodeRepo != null ) {

            /** Save the created GeoCode to the repository */
            geoCodeRepo.save( newGeoCode );
        } else {

            throw new RepoException( "Could not save to the repository." );
        }

        /**
         * Create the new response
         *  and add the created GeoCode to it
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
    public GetGeoCodesResponse getAllGeoCode( ) throws RepoException {

        /** Validate the repo */
        if ( geoCodeRepo == null ) {

            throw new RepoException( "The GeoCode Repository is empty." );
        }

        /*ListIterator< GeoCode > it;
        ArrayList< GeoCode > allGeoCodes = new ArrayList<>();

        while ( it.hasNext() ) {

            allGeoCodes.add( ( GeoCode ) it );
            it.next();
        }*/

        return new GetGeoCodesResponse();
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

        /** The file path the image should be created in */
        String path = "src/main/java/tech/geocodeapp/geocode/GeoCode/QRImages/"+ imageName + ".jpg";

        /** Create the QR Code and link it to the specified website */
        BitMatrix matrix = new MultiFormatWriter().encode( "https://www.example.com/", BarcodeFormat.QR_CODE, 400, 400 );

        /** Create the image and store it in the given path */
        MatrixToImageWriter.writeToPath( matrix, "jpg", Paths.get( path ) );

        return "AAAA";
    }

}