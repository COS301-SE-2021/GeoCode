package tech.geocodeapp.geocode.image.repository;

import tech.geocodeapp.geocode.image.model.Image;

import java.io.IOException;

public interface ImageRepository {

    Image save(Image image) throws IOException;

    Image findByName(String name) throws IOException;

}
