package tech.geocodeapp.geocode.image;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.geocodeapp.geocode.image.service.ImageService;
import tech.geocodeapp.geocode.image.service.ImageServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ImageServiceImplTest {

    private ImageService imageService;
    private ImageMockRepository imageRepo;

    @BeforeEach
    void setup() {
        imageRepo = new ImageMockRepository();
        imageService = new ImageServiceImpl(imageRepo);
    }
}