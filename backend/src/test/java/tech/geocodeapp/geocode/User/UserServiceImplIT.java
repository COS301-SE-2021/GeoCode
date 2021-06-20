package tech.geocodeapp.geocode.User;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tech.geocodeapp.geocode.User.Request.GetCurrentCollectableRequest;
import tech.geocodeapp.geocode.User.Request.GetUserTrackableRequest;
import tech.geocodeapp.geocode.User.Request.UpdateLocationRequest;
import tech.geocodeapp.geocode.User.Response.GetCurrentCollectableResponse;
import tech.geocodeapp.geocode.User.Response.GetUserTrackableResponse;
import tech.geocodeapp.geocode.User.Response.UpdateLocationResponse;
import tech.geocodeapp.geocode.User.Service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplIT {
    @Autowired
    private UserService userService;
    private final UUID invalidUserId = UUID.fromString("31d72621-091c-49ad-9c28-8abda8b8f055");
    private final UUID validUserId = UUID.fromString("183e06b6-2130-45e3-8b43-634ccd3e8e6f");

    @Test
    public void getCurrentCollectableTestInvalidUser(){
        /*
           Create a request object
          and assign values to it
          */
        GetCurrentCollectableRequest request = new GetCurrentCollectableRequest();
        request.setUserID(invalidUserId);//invalid UUID (no user has it)

        GetCurrentCollectableResponse response = userService.getCurrentCollectable(request);
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals("Invalid user id", response.getMessage());
    }

    @Test
    public void getCurrentCollectableTestValidUser(){
        /*
           Create a request object
          and assign values to it
          */
        GetCurrentCollectableRequest request = new GetCurrentCollectableRequest();
        request.setUserID(validUserId);

        GetCurrentCollectableResponse response = userService.getCurrentCollectable(request);
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    public void getUserTrackableTestInvalidUser(){
        /*
           Create a request object
          and assign values to it
          */
        GetUserTrackableRequest request = new GetUserTrackableRequest();
        request.setUserID(invalidUserId);

        GetUserTrackableResponse response = userService.getUserTrackable(request);
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals("Invalid user id", response.getMessage());
    }

    @Test
    public void getUserTrackableTestValidUser(){
        /*
           Create a request object
          and assign values to it
          */
        GetUserTrackableRequest request = new GetUserTrackableRequest();
        request.setUserID(validUserId);

        GetUserTrackableResponse response = userService.getUserTrackable(request);
        Assertions.assertTrue(response.isSuccess());
    }

    @Test
    public void UpdateLocationTestInvalidUser(){
        /*
           Create a request object
          and assign values to it
          */
        UpdateLocationRequest request = new UpdateLocationRequest();
        request.setUserID(invalidUserId);

        UpdateLocationResponse response = userService.updateLocation(request);
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals("Invalid user id", response.getMessage());
    }

    @Test
    public void UpdateLocationTestValidUser(){
        /*
           Create a request object
          and assign values to it
          */
        UpdateLocationRequest request = new UpdateLocationRequest();
        request.setUserID(validUserId);

        UpdateLocationResponse response = userService.updateLocation(request);
        Assertions.assertTrue(response.isSuccess());
    }
}
