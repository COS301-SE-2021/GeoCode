package tech.geocodeapp.geocode.image;

import tech.geocodeapp.geocode.image.model.Image;
import tech.geocodeapp.geocode.image.repository.ImageRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageMockRepository implements ImageRepository {

    private Map<String, Image> map = new HashMap<>();

    @Override
    public Image save(Image image) throws IOException {
        map.put(image.getFileName(), image);
        return image;
    }

    @Override
    public Image findByName(String fileName) throws IOException {
        return map.get(fileName);
    }
}
