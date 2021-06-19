package tech.geocodeapp.geocode.GeoCode;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import tech.geocodeapp.geocode.GeoCode.Exceptions.RepoException;
import tech.geocodeapp.geocode.GeoCode.Repository.GeoCodeRepository;
import tech.geocodeapp.geocode.GeoCode.Service.GeoCodeService;
import tech.geocodeapp.geocode.GeoCode.Service.GeoCodeServiceImpl;


public class GeoCodeServiceImplIT {


    /**
     * The service for the GeoCode subsystem
     *
     * This is used to access the different use cases
     * needed for functionality
     */
    GeoCodeService geoCodeService;

    @Autowired
    GeoCodeRepository repo;
    /**
     * Default Constructor
     */
    GeoCodeServiceImplIT() {

    }

    /**
     * Create the GeoCodeServiceImpl with the relevant repositories.
     *
     * This is done to ensure a repository with no data is created each time
     * and the service implementation contains fresh code that has not been affected
     * by some other test or data.
     */
    @BeforeEach
    void setup() throws RepoException {

        /* Clear all the data the could be left over in the repo */
        repo.deleteAll();

        /* Make a new instantiation */
        geoCodeService = new GeoCodeServiceImpl( repo );
    }

}
