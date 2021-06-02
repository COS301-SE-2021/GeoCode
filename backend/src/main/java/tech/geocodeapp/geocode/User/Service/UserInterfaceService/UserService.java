package tech.geocodeapp.geocode.User.Service.UserInterfaceService;

import org.springframework.stereotype.Service;

/**
 * This interface is for the User subsystem
 */
@Service
public interface UserService {
    //TODO: add once use cases finalised, Register shown as an example
    //U1.1 Register
    RegisterUserResponse Register(RegisterUserRequest request);
}
