package tech.geocodeapp.geocode.image.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import tech.geocodeapp.geocode.image.exceptions.DirectoryNotFoundException;
import tech.geocodeapp.geocode.image.model.Image;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Repository("ImageRepository")
public class ImageRepositoryImpl implements ImageRepository {

    private final String imageDirectory;

    public ImageRepositoryImpl( @Value("${image-directory}") String imageDirectory ) throws DirectoryNotFoundException {
        this.imageDirectory = imageDirectory;

        /* Create the directory if it does not exist */
        if (Files.exists( Paths.get( imageDirectory ) ) ) {
            throw new DirectoryNotFoundException();
        }
    }

    @Override
    public Image save( Image image ) throws IOException {
        Files.write( getFilePathFromUUID( image.getId() ), image.getBytes() );
        return image;
    }

    @Override
    public Image findById( UUID id ) throws IOException {
        try {
            byte[] bytes = Files.readAllBytes( getFilePathFromUUID( id ) );
            return new Image( id, bytes );
        } catch ( NoSuchFileException e ) {
            return null;
        }
    }

    private Path getFilePathFromUUID( UUID input ) {
        return new File( imageDirectory+"\\"+input.toString()+".png" ).toPath();
    }
}
