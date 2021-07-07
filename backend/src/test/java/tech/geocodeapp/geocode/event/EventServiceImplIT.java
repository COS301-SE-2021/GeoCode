package tech.geocodeapp.geocode.event;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import tech.geocodeapp.geocode.GeoCodeApplication;


@SpringBootTest( classes = GeoCodeApplication.class,
                 webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
class EventServiceImplIT {


}
