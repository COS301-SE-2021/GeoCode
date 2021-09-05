package tech.geocodeapp.geocode.image.controller;

import java.util.UUID;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import tech.geocodeapp.geocode.image.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.image.exceptions.NotFoundException;
import tech.geocodeapp.geocode.image.request.CreateImageRequest;
import tech.geocodeapp.geocode.image.request.GetImageRequest;
import tech.geocodeapp.geocode.image.response.CreateImageResponse;
import tech.geocodeapp.geocode.image.response.GetImageResponse;
import tech.geocodeapp.geocode.image.service.ImageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class ImageApiController implements ImageApi {

    private final ImageService imageService;

    public ImageApiController( ImageService imageService ) {
        this.imageService = imageService;
    }

    public ResponseEntity< byte[] > getImage(@Parameter(in = ParameterIn.PATH, description = "ID of the image to retrieve", required=true, schema=@Schema()) @PathVariable("imageID") UUID imageID) {
        try {
            GetImageRequest request = new GetImageRequest( imageID );
            GetImageResponse response = imageService.getImage( request );

            return new ResponseEntity<>( response.getImage().getBytes(), HttpStatus.OK );

        } catch (InvalidRequestException e) {
            return new ResponseEntity<>( e.getStatus() );

        } catch (NotFoundException e) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );

        } catch (IOException e) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    public ResponseEntity< CreateImageResponse > uploadImage( HttpServletRequest httpServletRequest ) {
        try {
            CreateImageRequest request = new CreateImageRequest( httpServletRequest.getInputStream() );
            CreateImageResponse response = imageService.createImage( request );
            return new ResponseEntity<>( response, HttpStatus.OK );

        } catch ( InvalidRequestException e ) {
            CreateImageResponse response = new CreateImageResponse( false, e.getMessage(), null );
            return new ResponseEntity<>( response, e.getStatus() );

        } catch ( IOException e ) {
            CreateImageResponse response = new CreateImageResponse( false, e.getMessage(), null );
            return new ResponseEntity<>( response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

}