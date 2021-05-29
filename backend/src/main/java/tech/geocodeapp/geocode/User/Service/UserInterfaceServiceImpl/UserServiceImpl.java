package tech.geocodeapp.geocode.User.Service.UserInterfaceServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.geocodeapp.geocode.User.Repository.UserRepository;
import tech.geocodeapp.geocode.User.Service.UserInterfaceService.UserService;

import javax.transaction.Transactional;

/**
 * This class implements the UserService interface
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl() {

    }

    @Transactional
    public RegisterUserResponse Register(RegisterUserRequest request){
        if (request == null) {
            return new RegisterUserResponse();//TODO: pass parameters once class done
        }

        return null;
    }
}
