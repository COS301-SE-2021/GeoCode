package tech.geocodeapp.geocode.image.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.image.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.image.exceptions.NotFoundException;
import tech.geocodeapp.geocode.image.model.Image;
import tech.geocodeapp.geocode.image.repository.ImageRepository;
import tech.geocodeapp.geocode.image.request.CreateImageRequest;
import tech.geocodeapp.geocode.image.request.GetImageRequest;
import tech.geocodeapp.geocode.image.response.CreateImageResponse;
import tech.geocodeapp.geocode.image.response.GetImageResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Validated
@Service( "ImageService" )
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepo;

    /* The maximum width and height that images should be resized to */
    private final int MAX_IMAGE_SIZE = 200;

    public ImageServiceImpl( ImageRepository imageRepo ) {
        this.imageRepo = imageRepo;
    }

    @Override
    public CreateImageResponse createImage( CreateImageRequest request ) throws InvalidRequestException, IOException {
        if ( request == null ) {
            throw new InvalidRequestException( "No request object provided" );
        }
        if ( request.getImageByteStream() == null ) {
            throw new InvalidRequestException( "No image byte stream provided" );
        }

        /* Convert the input stream data to a BufferedImage */
        BufferedImage imageData = ImageIO.read( request.getImageByteStream() );
        if ( imageData == null ) {
            throw new InvalidRequestException( "Invalid image provided" );
        }

        /* Resize the BufferedImage for reduced bandwidth and storage requirements */
        imageData = resizeImage( imageData );

        /* Convert the BufferedImage to a byte array */
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write( imageData, "png", stream );
        byte[] bytes = stream.toByteArray();

        /* Save the image in the repo */
        Image image = new Image( UUID.randomUUID(), bytes );
        this.imageRepo.save( image );

        return new CreateImageResponse( true, "The image was successfully uploaded", image.getId() );
    }

    @Override
    public GetImageResponse getImage( GetImageRequest request ) throws InvalidRequestException, NotFoundException, IOException {
        if ( request == null ) {
            throw new InvalidRequestException( "No request object provided" );
        }
        if ( request.getImageID() == null ) {
            throw new InvalidRequestException("Invalid image ID provided");
        }

        Image image = this.imageRepo.findById( request.getImageID() );
        if ( image == null ) {
            throw new NotFoundException();
        }
        return new GetImageResponse( true, "Image returned", image );
    }

    /********** Helper functions **********/

    private BufferedImage resizeImage( BufferedImage input ) {

        double inputHeight = input.getHeight();
        double inputWidth = input.getWidth();

        if ( ( inputHeight < MAX_IMAGE_SIZE ) && ( inputWidth < MAX_IMAGE_SIZE ) ) {
            /* Image is already small enough - do not resize */
            return input;
        }

        int outputHeight = MAX_IMAGE_SIZE;
        int outputWidth = MAX_IMAGE_SIZE;

        if ( inputHeight > inputWidth ) {
            /* Portrait - make output width smaller */
            outputWidth = (int) ( ( outputHeight / inputHeight ) * inputWidth );

        } else if ( inputHeight < inputWidth ) {
            /* Landscape - make output height smaller */
            outputHeight = (int) ( ( outputWidth / inputWidth ) * inputHeight );
        }

        BufferedImage output = new BufferedImage( outputWidth, outputHeight, input.getType() );

        Graphics2D g2d = output.createGraphics();
        g2d.drawImage( input, 0, 0, outputWidth, outputHeight, null );
        return output;
    }
}