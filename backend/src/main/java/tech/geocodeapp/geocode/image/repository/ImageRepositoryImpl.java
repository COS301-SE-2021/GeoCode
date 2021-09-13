package tech.geocodeapp.geocode.image.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import tech.geocodeapp.geocode.image.model.Image;
import tech.geocodeapp.geocode.image.model.ImageFormat;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Repository("ImageRepository")
public class ImageRepositoryImpl implements ImageRepository {

    private final Logger log = LoggerFactory.getLogger(ImageRepositoryImpl.class);
    private final String imageDirectory;

    public ImageRepositoryImpl( @Value("${image-directory}") String imageDirectory ) throws IOException {
        this.imageDirectory = imageDirectory;

        /* Create the directory if it does not exist */
        Files.createDirectories( Paths.get( imageDirectory ) );
        log.info( "Using image directory \""+imageDirectory+"\"" );
    }

    @Override
    public Image save( Image image ) throws IOException {
        Files.write( getFilePathFromName( image.getFileName() ), image.getBytes() );
        return image;
    }

    @Override
    public Image findByName( String name ) throws IOException {
        try {
            byte[] bytes = Files.readAllBytes( getFilePathFromName( name ) );
            return new Image( name, bytes );
        } catch ( NoSuchFileException e ) {
            return null;
        }
    }

    private Path getFilePathFromName( String input ) {
        return new File( imageDirectory+"\\"+input ).toPath();
    }
}
