package tech.geocodeapp.geocode.image;

import tech.geocodeapp.geocode.image.model.Image;
import tech.geocodeapp.geocode.image.repository.ImageRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ImageMockRepository implements ImageRepository {

    private Map<UUID, Image> map = new HashMap<>();

    @Override
    public Image save(Image image) throws IOException {
        map.put(image.getId(), image);
        return image;
    }

    @Override
    public Image findById(UUID id) throws IOException {
        return map.get(id);
    }
}
