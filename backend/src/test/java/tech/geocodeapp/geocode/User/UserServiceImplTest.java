package tech.geocodeapp.geocode.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.geocodeapp.geocode.Collectable.CollectableMockRepository;
import tech.geocodeapp.geocode.GeoCode.GeoCodeMockRepository;
import tech.geocodeapp.geocode.User.Service.UserService;
import tech.geocodeapp.geocode.User.Service.UserServiceImpl;
import tech.geocodeapp.geocode.User.Request.GetCurrentCollectableRequest;
import tech.geocodeapp.geocode.User.Request.GetUserTrackableRequest;
import tech.geocodeapp.geocode.User.Request.UpdateLocationRequest;
import tech.geocodeapp.geocode.User.Response.GetCurrentCollectableResponse;
import tech.geocodeapp.geocode.User.Response.GetUserTrackableResponse;
import tech.geocodeapp.geocode.User.Response.UpdateLocationResponse;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith( MockitoExtension.class )
public class UserServiceImplTest {

    private UserService userService;

    UserServiceImplTest() {

    }

    @BeforeEach
    void setup() {
        userService = new UserServiceImpl( new UserMockRepository(), new CollectableMockRepository(), new GeoCodeMockRepository());
    }

    @Test
    public void getCurrentCollectableTestInvalidUser(){
        /**
         *  Create a request object
         * and assign values to it
         * */
        GetCurrentCollectableRequest request = new GetCurrentCollectableRequest();
        request.setUserID(UUID.fromString("31d72621-091c-49ad-9c28-8abda8b8f055"));

        GetCurrentCollectableResponse response = userService.getCurrentCollectable(request);
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(response.getMessage(), "Invalid user id");
    }

    @Test
    public void getCurrentCollectableTestValidUser(){
        /**
         *  Create a request object
         * and assign values to it
         * */
        GetCurrentCollectableRequest request = new GetCurrentCollectableRequest();
        request.setUserID(UUID.fromString(""));//input once have UUID for a user

        GetCurrentCollectableResponse response = userService.getCurrentCollectable(request);
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    public void getUserTrackableTestInvalidUser(){
        /**
         *  Create a request object
         * and assign values to it
         * */
        GetUserTrackableRequest request = new GetUserTrackableRequest();
        request.setUserID(UUID.fromString("31d72621-091c-49ad-9c28-8abda8b8f055"));

        GetUserTrackableResponse response = userService.getUserTrackable(request);
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(response.getMessage(), "Invalid user id");
    }

    @Test
    public void getUserTrackableTestValidUser(){
        /**
         *  Create a request object
         * and assign values to it
         * */
        GetUserTrackableRequest request = new GetUserTrackableRequest();
        request.setUserID(UUID.fromString(""));

        GetUserTrackableResponse response = userService.getUserTrackable(request);
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    public void UpdateLocationTestInvalidUser(){
        /**
         *  Create a request object
         * and assign values to it
         * */
        UpdateLocationRequest request = new UpdateLocationRequest();
        request.setUserID(UUID.fromString("31d72621-091c-49ad-9c28-8abda8b8f055"));

        UpdateLocationResponse response = userService.updateLocation(request);
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(response.getMessage(), "Invalid user id");
    }

    @Test
    public void UpdateLocationTestValidUser(){
        /**
         *  Create a request object
         * and assign values to it
         * */
        UpdateLocationRequest request = new UpdateLocationRequest();
        request.setUserID(UUID.fromString(""));

        UpdateLocationResponse response = userService.updateLocation(request);
        Assertions.assertTrue(response.isSuccess());
    }
}
