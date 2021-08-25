package tech.geocodeapp.geocode.image.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import tech.geocodeapp.geocode.image.model.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Repository("ImageRepository")
public class ImageRepositoryImpl implements ImageRepository {

    private final String imageDirectory;

    public ImageRepositoryImpl(@Value("${image-directory}") String imageDirectory) throws IOException {
        this.imageDirectory = imageDirectory;

        /* Create the directory if it does not exist */
        Files.createDirectories(Paths.get(imageDirectory));
    }

    @Override
    public Image save(Image image) throws IOException {
        ImageIO.write(image.getImageData(), "png", getFileFromUUID(image.getId()));
        return image;
    }

    @Override
    public Image findById(UUID id) throws IOException {
        BufferedImage data = ImageIO.read(getFileFromUUID(id));
        return new Image(id, data);
    }

    @Override
    public byte[] getBytesById(UUID id) throws IOException {
        return Files.readAllBytes(getFileFromUUID(id).toPath());
    }

    private File getFileFromUUID(UUID input) {
        return new File(imageDirectory+"\\"+input.toString()+".png");
    }
}
