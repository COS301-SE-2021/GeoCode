package tech.geocodeapp.geocode.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.geocodeapp.geocode.Collectable.CollectableMockRepository;
import tech.geocodeapp.geocode.Collectable.CollectableTypeMockRepository;
import tech.geocodeapp.geocode.Collectable.Model.CollectableType;
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

@ExtendWith( MockitoExtension.class )
public class UserServiceImplTest {
    private UserService userService;

    UserServiceImplTest() {

    }

    @BeforeEach
    void setup() {
        CollectableTypeMockRepository collectableTypeMockRepo = new CollectableTypeMockRepository();
        userService = new UserServiceImpl( new UserMockRepository(), new CollectableMockRepository(), collectableTypeMockRepo, new GeoCodeMockRepository());

        //save the valid trackable CollectableType
        CollectableType trackableCollectableType = new CollectableType();
        trackableCollectableType.setId(UUID.fromString("0855b7da-bdad-44b7-9c22-18fe266ceaf3"));
        collectableTypeMockRepo.save(trackableCollectableType);

        //save the valid user to the MockRepo
        userService.registerNewUser(UUID.fromString("183e06b6-2130-45e3-8b43-634ccd3e8e6f"), "john_smith");
    }

    @Test
    public void getCurrentCollectableTestInvalidUser(){
        /*
           Create a request object
          and assign values to it
          */
        GetCurrentCollectableRequest request = new GetCurrentCollectableRequest();
        request.setUserID(UUID.fromString("31d72621-091c-49ad-9c28-8abda8b8f055"));//invalid UUID (no user has it)

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
        request.setUserID(UUID.fromString("183e06b6-2130-45e3-8b43-634ccd3e8e6f"));

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
        request.setUserID(UUID.fromString("31d72621-091c-49ad-9c28-8abda8b8f055"));

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
        request.setUserID(UUID.fromString("183e06b6-2130-45e3-8b43-634ccd3e8e6f"));

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
        request.setUserID(UUID.fromString("31d72621-091c-49ad-9c28-8abda8b8f055"));

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
        request.setUserID(UUID.fromString("183e06b6-2130-45e3-8b43-634ccd3e8e6f"));

        UpdateLocationResponse response = userService.updateLocation(request);
        Assertions.assertTrue(response.isSuccess());
    }
}
