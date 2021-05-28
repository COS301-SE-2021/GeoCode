package tech.geocodeapp.geocode.User.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import tech.geocodeapp.geocode.APIObjectMapper;
import tech.geocodeapp.geocode.User.Service.UserInterfaceServiceImpl.UserServiceImpl;

/**
 * This class implements the functionality of the UserAPI interface.
 */
@CrossOrigin("*")
@RestController
public class UserController implements UserApi {//TODO: generate UserApi
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private APIObjectMapper apiObjectMapper;


}
