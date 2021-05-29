package tech.geocodeapp.geocode.User.Service;

import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.User.Request.RegisterUserRequest;
import tech.geocodeapp.geocode.User.Response.RegisterUserResponse;

/**
 * This interface is for the User subsystem
 */
@Service
public interface UserService {
    //TODO: add once use cases finalised, Register shown as an example
    //U1.1 Register
    RegisterUserResponse Register(RegisterUserRequest request);
}
