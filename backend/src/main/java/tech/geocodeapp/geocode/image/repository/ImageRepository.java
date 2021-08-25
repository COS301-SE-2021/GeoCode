package tech.geocodeapp.geocode.image.repository;

import tech.geocodeapp.geocode.image.model.Image;

import java.io.IOException;
import java.util.UUID;

public interface ImageRepository {

    Image save(Image image) throws IOException;

    Image findById(UUID id) throws IOException;

    byte[] getBytesById(UUID id) throws IOException;

}
