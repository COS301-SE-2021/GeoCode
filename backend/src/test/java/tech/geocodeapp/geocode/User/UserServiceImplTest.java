package tech.geocodeapp.geocode.User;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import tech.geocodeapp.geocode.Collectable.CollectableMockRepository;
import tech.geocodeapp.geocode.Collectable.CollectableTypeMockRepository;
import tech.geocodeapp.geocode.Collectable.Model.Collectable;
import tech.geocodeapp.geocode.Collectable.Model.CollectableType;
import tech.geocodeapp.geocode.GeoCode.GeoCodeMockRepository;
import tech.geocodeapp.geocode.User.Exception.NullUserRequestParameterException;
import tech.geocodeapp.geocode.User.Service.UserService;
import tech.geocodeapp.geocode.User.Service.UserServiceImpl;
import tech.geocodeapp.geocode.User.Request.GetCurrentCollectableRequest;
import tech.geocodeapp.geocode.User.Request.GetUserTrackableRequest;
import tech.geocodeapp.geocode.User.Request.UpdateLocationRequest;
import tech.geocodeapp.geocode.User.Response.GetCurrentCollectableResponse;
import tech.geocodeapp.geocode.User.Response.GetUserTrackableResponse;
import tech.geocodeapp.geocode.User.Response.UpdateLocationResponse;

@ExtendWith( MockitoExtension.class )
public class UserServiceImplTest {
    private UserService userService;

    private final UUID invalidUserId = UUID.fromString("31d72621-091c-49ad-9c28-8abda8b8f055");
    private final UUID validUserId = UUID.fromString("183e06b6-2130-45e3-8b43-634ccd3e8e6f");
    private final String invalidUserIdMessage = "Invalid user id";
    private GetCurrentCollectableResponse getCurrentCollectableResponse;
    private GetUserTrackableResponse getUserTrackableResponse;
    private UpdateLocationResponse updateLocationResponse;

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
        userService.registerNewUser(validUserId, "john_smith");
    }

    @Test
    public void getCurrentCollectableTestNullRequest() {
        try{
            GetCurrentCollectableResponse response = userService.getCurrentCollectable(null);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetCurrentCollectableRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getCollectable());
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void getCurrentCollectableTestNullId(){
           GetCurrentCollectableRequest request = new GetCurrentCollectableRequest();
           request.setUserID(null);

           //GetCurrentCollectableResponse response = userService.getCurrentCollectable(request);
           assertThatThrownBy(() -> getCurrentCollectableResponse = userService.getCurrentCollectable(request))
                .isInstanceOf(NullUserRequestParameterException.class);
    }

    @Test
    public void getCurrentCollectableTestInvalidUser() {
        try{
            /*
            Create a request object
            and assign values to it
            */
            GetCurrentCollectableRequest request = new GetCurrentCollectableRequest();
            request.setUserID(invalidUserId);//invalid UUID (no user has it)

            GetCurrentCollectableResponse response = userService.getCurrentCollectable(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
            Assertions.assertNull(response.getCollectable());
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void getCurrentCollectableTestValidUser() {
        try{
            /*
            Create a request object
            and assign values to it
            */
            GetCurrentCollectableRequest request = new GetCurrentCollectableRequest();
            request.setUserID(validUserId);

            GetCurrentCollectableResponse response = userService.getCurrentCollectable(request);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The user's Collectable was successfully returned", response.getMessage());
            Assertions.assertNotNull(response.getCollectable());
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void getUserTrackableTestNullRequest() {
        try{
            GetUserTrackableResponse response = userService.getUserTrackable(null);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetUserTrackableRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getTrackable());
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void getUserTrackableTestNullId(){
        GetUserTrackableRequest request = new GetUserTrackableRequest();
        request.setUserID(null);

        assertThatThrownBy(() -> getUserTrackableResponse = userService.getUserTrackable(request))
                .isInstanceOf(NullUserRequestParameterException.class);
    }

    @Test
    public void getUserTrackableTestInvalidUser() {
        try{
            /*
            Create a request object
            and assign values to it
            */
            GetUserTrackableRequest request = new GetUserTrackableRequest();
            request.setUserID(invalidUserId);

            GetUserTrackableResponse response = userService.getUserTrackable(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
            Assertions.assertNull(response.getTrackable());
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void getUserTrackableTestValidUser() {
        try{
            /*
            Create a request object
            and assign values to it
            */
            GetUserTrackableRequest request = new GetUserTrackableRequest();
            request.setUserID(validUserId);

            GetUserTrackableResponse response = userService.getUserTrackable(request);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The user's Trackable was successfully returned", response.getMessage());

            Collectable trackableObject = response.getTrackable();
            Assertions.assertNotNull(trackableObject);
            Assertions.assertEquals(UUID.fromString("0855b7da-bdad-44b7-9c22-18fe266ceaf3"), trackableObject.getType().getId());
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void updateLocationTestNullRequest() {
        try{
            UpdateLocationResponse response = userService.updateLocation(null);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The UpdateLocationRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getTrackable());
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void updateLocationTestNullId(){
        UpdateLocationRequest request = new UpdateLocationRequest();
        request.setUserID(null);

        assertThatThrownBy(() -> updateLocationResponse = userService.updateLocation(request))
                .isInstanceOf(NullUserRequestParameterException.class);
    }

    @Test
    public void updateLocationTestInvalidUser() {
        try{
            /*
            Create a request object
            and assign values to it
            */
            UpdateLocationRequest request = new UpdateLocationRequest();
            request.setUserID(invalidUserId);

            UpdateLocationResponse response = userService.updateLocation(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
            Assertions.assertNull(response.getTrackable());
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void updateLocationTestValidUser() {
        try{
            /*
             Create a request object
             and assign values to it
           */
            UpdateLocationRequest request = new UpdateLocationRequest();
            request.setUserID(validUserId);
            String location = "x:100,y:40";
            request.setLocation(location);

            UpdateLocationResponse response = userService.updateLocation(request);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The trackable object's location was successfully updated", response.getMessage());

            Collectable trackableObject = response.getTrackable();
            Assertions.assertNotNull(trackableObject);

            List<String> pastLocations = new ArrayList<>(trackableObject.getPastLocations());
            Assertions.assertEquals(location, pastLocations.get(pastLocations.size()-1));
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }
}
