package tech.geocodeapp.geocode.GeoCode.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import io.swagger.model.GetGeoCodesRequest;
import io.swagger.model.GetGeoCodesResponse;
import org.springframework.stereotype.Service;

import tech.geocodeapp.geocode.GeoCode.Exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.GeoCode.Exceptions.QRCodeException;
import tech.geocodeapp.geocode.GeoCode.Repository.GeoCodeRepository;
import tech.geocodeapp.geocode.GeoCode.Request.CreateGeoCodeRequest;
import tech.geocodeapp.geocode.GeoCode.Response.CreateGeoCodeResponse;

import java.io.IOException;
import java.nio.file.Paths;


/**
 * This class implements the UserService interface
 */
@Service
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
     */
    @Override
    public CreateGeoCodeResponse createGeoCode( CreateGeoCodeRequest request ) throws InvalidRequestException, QRCodeException {

        /** Validate the request */
        if ( request == null ) {

            throw new InvalidRequestException( "An error occurred." );
        }

        /** Try and create the relevant image with the newly create GeoCode instance */
        try {

            /** Create the image with the specified name */
            createQR( "QRCode" );
        } catch ( IOException | WriterException e ) {

            throw new QRCodeException( "The QR Code could not be created." );
        }

        return new CreateGeoCodeResponse();
    }

    @Override
    public GetGeoCodesResponse getGeoCodes(GetGeoCodesRequest request) {

        return new GetGeoCodesResponse();
    }

    /**
     * This helper function helps create the QR Code image and stores
     * it in the QRImages folder.
     *
     * @param imageName the name to label the jpg image to
     *
     * @throws IOException the file path or image name given was invalid
     * @throws WriterException the image could not be created
     */
    public void createQR( String imageName ) throws IOException, WriterException {

        /** The file path the image should be created in */
        String path = "src/main/java/tech/geocodeapp/geocode/GeoCode/QRImages/"+ imageName + ".jpg";

        /** Create the QR Code and link it to the specified website */
        BitMatrix matrix = new MultiFormatWriter().encode( "https://www.example.com/", BarcodeFormat.QR_CODE, 400, 400 );

        /** Create the image and store it in the given path */
        MatrixToImageWriter.writeToPath( matrix, "jpg", Paths.get( path ) );
    }

}