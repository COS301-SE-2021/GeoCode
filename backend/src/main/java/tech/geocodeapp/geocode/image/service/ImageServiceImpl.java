package tech.geocodeapp.geocode.image.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.image.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.image.exceptions.NotFoundException;
import tech.geocodeapp.geocode.image.model.Image;
import tech.geocodeapp.geocode.image.model.ImageFormat;
import tech.geocodeapp.geocode.image.repository.ImageRepository;
import tech.geocodeapp.geocode.image.request.CreateImageRequest;
import tech.geocodeapp.geocode.image.request.GetImageRequest;
import tech.geocodeapp.geocode.image.response.CreateImageResponse;
import tech.geocodeapp.geocode.image.response.GetImageResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Validated
@Service( "ImageService" )
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepo;

    /* The maximum file size of images that can be uploaded */
    private final int MAX_IMAGE_FILE_SIZE = 1048576; // 1024 KiB (kebibytes)

    /* The maximum width and height that images should be resized to */
    private final int MAX_IMAGE_DIMENSION = 200; // 200 pixels

    public ImageServiceImpl( ImageRepository imageRepo ) {
        this.imageRepo = imageRepo;
    }

    @Override
    public CreateImageResponse createImage( CreateImageRequest request ) throws InvalidRequestException, IOException {
        if ( request == null ) {
            throw new InvalidRequestException( "No request object provided", HttpStatus.BAD_REQUEST );
        }
        if ( request.getImageByteStream() == null ) {
            throw new InvalidRequestException( "No image byte stream provided", HttpStatus.BAD_REQUEST );
        }

        InputStream inputStream = request.getImageByteStream();

        /* Read the input stream data into a byte array, up to the maximum file size */
        byte[] bytes = inputStream.readNBytes(MAX_IMAGE_FILE_SIZE);

        /* Get the next byte (for data validation) and then close the input stream */
        int nextByte = inputStream.read();
        inputStream.close();

        if (bytes.length == 0) {
            /* No data in byte array */
            throw new InvalidRequestException( "No file was supplied with the request", HttpStatus.BAD_REQUEST );

        } else if (nextByte != -1) {
            /* Input stream still contained data after reading */
            throw new InvalidRequestException( "The supplied file is larger than 1024 kibibytes", HttpStatus.PAYLOAD_TOO_LARGE );
        }

        ImageFormat outputFormat = ImageFormat.fromBytes( bytes );
        if (outputFormat == null) {
            throw new InvalidRequestException( "The supplied file has an invalid MIME type", HttpStatus.UNSUPPORTED_MEDIA_TYPE );

        } else if (outputFormat == ImageFormat.PNG) {
            BufferedImage imageData = ImageIO.read( new ByteArrayInputStream( bytes ) );
            if ( imageData == null ) {
                /* This should never be hit, because the file has already been identified as an image */
                throw new InvalidRequestException( "The supplied file could not be read as an image", HttpStatus.UNPROCESSABLE_ENTITY );
            }

            /* Resize the BufferedImage for reduced bandwidth and storage requirements */
            imageData = resizeImage( imageData );

            /* Convert the BufferedImage to a PNG in a byte array */
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write( imageData, "png", outputStream );
            bytes = outputStream.toByteArray();
        }

        /* Save the image in the repo */
        String fileName = UUID.randomUUID()+"."+outputFormat;
        Image image = new Image( fileName, bytes, outputFormat );
        this.imageRepo.save( image );

        return new CreateImageResponse( true, "The image was successfully saved", image.getFileName() );
    }

    @Override
    public GetImageResponse getImage( GetImageRequest request ) throws InvalidRequestException, NotFoundException, IOException {
        if ( request == null ) {
            throw new InvalidRequestException( "No request object provided", HttpStatus.BAD_REQUEST );
        }
        if ( request.getFileName() == null ) {
            throw new InvalidRequestException( "No file name provided", HttpStatus.BAD_REQUEST );
        }

        Image image = this.imageRepo.findByName( request.getFileName() );
        if ( image == null ) {
            throw new NotFoundException();
        }

        return new GetImageResponse( true, "Image returned successfully", image );
    }

    /********** Helper functions **********/

    private BufferedImage resizeImage( BufferedImage input ) {

        double inputHeight = input.getHeight();
        double inputWidth = input.getWidth();

        if ( ( inputHeight < MAX_IMAGE_DIMENSION) && ( inputWidth < MAX_IMAGE_DIMENSION) ) {
            /* Image is already small enough - do not resize */
            return input;
        }

        int outputHeight = MAX_IMAGE_DIMENSION;
        int outputWidth = MAX_IMAGE_DIMENSION;

        if ( inputHeight > inputWidth ) {
            /* Portrait - make output width smaller */
            outputWidth = (int) ( ( outputHeight / inputHeight ) * inputWidth );

        } else if ( inputHeight < inputWidth ) {
            /* Landscape - make output height smaller */
            outputHeight = (int) ( ( outputWidth / inputWidth ) * inputHeight );
        }

        BufferedImage output = new BufferedImage( outputWidth, outputHeight, BufferedImage.TYPE_INT_ARGB );

        Graphics2D g2d = output.createGraphics();
        g2d.drawImage( input, 0, 0, outputWidth, outputHeight, null );
        return output;
    }
}