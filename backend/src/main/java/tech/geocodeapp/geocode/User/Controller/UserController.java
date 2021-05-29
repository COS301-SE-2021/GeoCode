package tech.geocodeapp.geocode.User.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import tech.geocodeapp.geocode.User.Service.*;

/**
 * This class implements the functionality of the UserAPI interface.
 */
@CrossOrigin("*")
@RestController
public class UserController {//TODO: generate UserApi
    @Autowired
    private UserServiceImpl userService;



}
