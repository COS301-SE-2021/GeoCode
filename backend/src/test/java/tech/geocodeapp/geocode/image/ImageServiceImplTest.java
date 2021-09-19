package tech.geocodeapp.geocode.image;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.geocodeapp.geocode.image.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.image.exceptions.NotFoundException;
import tech.geocodeapp.geocode.image.model.Image;
import tech.geocodeapp.geocode.image.request.CreateImageRequest;
import tech.geocodeapp.geocode.image.request.GetImageRequest;
import tech.geocodeapp.geocode.image.response.CreateImageResponse;
import tech.geocodeapp.geocode.image.response.GetImageResponse;
import tech.geocodeapp.geocode.image.service.ImageService;
import tech.geocodeapp.geocode.image.service.ImageServiceImpl;

import java.io.*;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class ImageServiceImplTest {

    private ImageService imageService;
    private ImageMockRepository imageRepo;

    private final int MAX_IMAGE_FILE_SIZE = 1048576;

    private final String LARGE_INPUT_IMAGE_LOCATION = "src/test/resources/test-images/geocode-logo-large.png";
    private final String RESIZED_INPUT_IMAGE_LOCATION = "src/test/resources/test-images/geocode-logo-resized.png";
    private final String INVALID_IMAGE_LOCATION = "src/test/java/tech/geocodeapp/geocode/image/ImageServiceImplTest.java";

    @BeforeEach
    void setup() {
        imageRepo = new ImageMockRepository();
        imageService = new ImageServiceImpl(imageRepo);
    }

    @Test
    void createImageTestNullRequest() {
        assertThatThrownBy( () -> imageService.createImage( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( "No request object provided" );
    }

    @Test
    void createImageTestNullByteStream() {
        CreateImageRequest request = new CreateImageRequest( null );
        assertThatThrownBy( () -> imageService.createImage( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( "No image byte stream provided" );
    }

    @Test
    void createImageTestEmptyByteStream() {
        InputStream stream = new ByteArrayInputStream( new byte[0] );
        CreateImageRequest request = new CreateImageRequest( stream );
        assertThatThrownBy( () -> imageService.createImage( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( "No file was supplied with the request" );
    }

    @Test
    void createImageTestByteStreamTooLarge() {
        InputStream stream = new ByteArrayInputStream( new byte[ MAX_IMAGE_FILE_SIZE + 1 ] );
        CreateImageRequest request = new CreateImageRequest( stream );
        assertThatThrownBy( () -> imageService.createImage( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( "The supplied file is larger than 1024 kibibytes" );
    }

    @Test
    void createImageTestInvalidFileType() throws FileNotFoundException {
        File inputFile = new File( INVALID_IMAGE_LOCATION );
        InputStream inputStream = new FileInputStream( inputFile );

        CreateImageRequest request = new CreateImageRequest( inputStream );

        assertThatThrownBy( () -> imageService.createImage( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( "The supplied file has an invalid MIME type" );
    }

    @Test
    void createImageTestValidFileResize() throws IOException, InvalidRequestException {
        File inputFile = new File( LARGE_INPUT_IMAGE_LOCATION );
        InputStream inputStream = new FileInputStream( inputFile );

        CreateImageRequest request = new CreateImageRequest( inputStream );
        CreateImageResponse response = imageService.createImage( request );

        Image createdImage = imageRepo.findByName(response.getFileName());

        File exampleOutputFile = new File( RESIZED_INPUT_IMAGE_LOCATION );
        byte[] exampleOutputBytes = Files.readAllBytes( exampleOutputFile.toPath() );

        Assertions.assertArrayEquals( exampleOutputBytes, createdImage.getBytes() );
    }

    @Test
    void createImageTestValidFileNoResize() throws IOException, InvalidRequestException {
        File inputFile = new File( RESIZED_INPUT_IMAGE_LOCATION );
        byte[] inputBytes = Files.readAllBytes( inputFile.toPath() );

        CreateImageRequest request = new CreateImageRequest( new ByteArrayInputStream( inputBytes ) );
        CreateImageResponse response = imageService.createImage( request );

        Image createdImage = imageRepo.findByName(response.getFileName());

        Assertions.assertArrayEquals( inputBytes, createdImage.getBytes() );
    }

    @Test
    void getImageTestNullRequest() {
        assertThatThrownBy( () -> imageService.getImage( null ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( "No request object provided" );
    }

    @Test
    void getImageTestNullImageID() {
        GetImageRequest request = new GetImageRequest( null );
        assertThatThrownBy( () -> imageService.getImage( request ) )
                .isInstanceOf( InvalidRequestException.class )
                .hasMessageContaining( "No file name provided" );
    }

    @Test
    void getImageTestNotFound() {
        /* repo is empty */
        GetImageRequest request = new GetImageRequest( "random.png" );

        assertThatThrownBy( () -> imageService.getImage( request ) )
                .isInstanceOf( NotFoundException.class );
    }

    @Test
    void getImageTestFound() throws IOException, NotFoundException, InvalidRequestException {
        /* Place file in repo */
        File inputFile = new File( RESIZED_INPUT_IMAGE_LOCATION );
        byte[] inputBytes = Files.readAllBytes( inputFile.toPath() );
        Image inputImage = new Image( "random.png", inputBytes );
        imageRepo.save(inputImage);

        GetImageRequest request = new GetImageRequest( inputImage.getFileName() );
        GetImageResponse response = imageService.getImage( request );

        Assertions.assertArrayEquals( inputBytes, response.getImage().getBytes() );
    }
}