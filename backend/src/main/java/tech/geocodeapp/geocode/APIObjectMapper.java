package tech.geocodeapp.geocode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.User.Service.UserServiceImpl;

/** This class handles mappings between API request objects and system objects
 *  called by the subsystems.
 */
@Service
public class APIObjectMapper {
    @Autowired
    public UserServiceImpl userService;


}
