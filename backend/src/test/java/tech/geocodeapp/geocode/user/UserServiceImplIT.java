package tech.geocodeapp.geocode.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.user.exception.NullUserRequestParameterException;
import tech.geocodeapp.geocode.user.service.*;
import tech.geocodeapp.geocode.user.request.*;
import tech.geocodeapp.geocode.user.response.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class UserServiceImplIT {
    @Autowired
    private UserService userService;

    private final UUID invalidUserId = UUID.fromString("31d72621-091c-49ad-9c28-8abda8b8f055");
    private final UUID validUserId = UUID.fromString("183e06b6-2130-45e3-8b43-634ccd3e8e6f");

    private final String invalidUserIdMessage = "Invalid user id";
    private final UUID firstGeoCodeID = UUID.fromString("537689d1-a0d8-4740-bec6-6a40bb69748e");
    private final UUID secondGeoCodeID = UUID.fromString("5d709c49-326b-470a-8d9d-e7f7bf77ef6e");
    private final UUID thirdGeoCodeID = UUID.fromString("92e3e6d5-5457-48f7-adb1-7c2f67ee836b");

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
    }//

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
    }//

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

    @Test
    public void getFoundCollectableTypesTestNullRequest(){
        try{
            GetFoundCollectableTypesResponse response = userService.getFoundCollectableTypes(null);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetFoundCollectableTypesRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getCollectableTypeIDs());
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getFoundCollectableTypesTestNullId(){
        GetFoundCollectableTypesRequest request = new GetFoundCollectableTypesRequest();
        request.setUserID(null);

        assertThatThrownBy(() -> userService.getFoundCollectableTypes(request))
                .isInstanceOf(NullUserRequestParameterException.class);
    }

    @Test
    void getFoundCollectableTypesTestInvalidUser() {
        try{
            /*
            Create a request object
            and assign values to it
            */
            GetFoundCollectableTypesRequest request = new GetFoundCollectableTypesRequest();
            request.setUserID(invalidUserId);

            GetFoundCollectableTypesResponse response = userService.getFoundCollectableTypes(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
            Assertions.assertNull(response.getCollectableTypeIDs());
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getFoundCollectableTypesTestValidUser() {
        try{
            /*
             Create a request object
             and assign values to it
           */
            GetFoundCollectableTypesRequest request = new GetFoundCollectableTypesRequest();
            request.setUserID(validUserId);

            GetFoundCollectableTypesResponse response = userService.getFoundCollectableTypes(request);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The IDs of the User's found CollectableTypes was successfully returned", response.getMessage());

            List<UUID> foundCollectableTypeIDs = response.getCollectableTypeIDs();
            Assertions.assertNotNull(foundCollectableTypeIDs);
            Assertions.assertEquals(3, foundCollectableTypeIDs.size());
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getFoundGeoCodesTestNullRequest() {
        try{
            GetFoundGeoCodesResponse response = userService.getFoundGeoCodes(null);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetFoundGeoCodesRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getGeocodeIDs());
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getFoundGeoCodesTestNullId(){
        GetFoundGeoCodesRequest request = new GetFoundGeoCodesRequest();
        request.setUserID(null);

        assertThatThrownBy(() -> userService.getFoundGeoCodes(request))
                .isInstanceOf(NullUserRequestParameterException.class);
    }

    @Test
    void getFoundGeoCodesTestInvalidUser() {
        try{
            /*
            Create a request object
            and assign values to it
            */
            GetFoundGeoCodesRequest request = new GetFoundGeoCodesRequest();
            request.setUserID(invalidUserId);

            GetFoundGeoCodesResponse response = userService.getFoundGeoCodes(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
            Assertions.assertNull(response.getGeocodeIDs());
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getFoundGeoCodesTestValidUser() {
        try{
            /*
             Create a request object
             and assign values to it
           */
            GetFoundGeoCodesRequest request = new GetFoundGeoCodesRequest();
            request.setUserID(validUserId);

            GetFoundGeoCodesResponse response = userService.getFoundGeoCodes(request);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The IDs of the User's found GeoCodes was successfully returned", response.getMessage());

            List<UUID> foundGeoCodeIDs = response.getGeocodeIDs();
            Assertions.assertNotNull(foundGeoCodeIDs);
            Assertions.assertEquals(2, foundGeoCodeIDs.size());

            //HashSet will cause order to not necessarily be order added in
            Assertions.assertTrue(foundGeoCodeIDs.contains(firstGeoCodeID));
            Assertions.assertTrue(foundGeoCodeIDs.contains(secondGeoCodeID));
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getOwnedGeoCodesTestNullRequest() {
        try{
            GetOwnedGeoCodesResponse response = userService.getOwnedGeoCodes(null);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetOwnedGeoCodesRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getGeocodeIDs());
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getOwnedGeoCodesTestNullId(){
        GetOwnedGeoCodesRequest request = new GetOwnedGeoCodesRequest();
        request.setUserID(null);

        assertThatThrownBy(() -> userService.getOwnedGeoCodes(request))
                .isInstanceOf(NullUserRequestParameterException.class);
    }

    @Test
    void getOwnedGeoCodesTestInvalidUser() {
        try{
            /*
            Create a request object
            and assign values to it
            */
            GetOwnedGeoCodesRequest request = new GetOwnedGeoCodesRequest();
            request.setUserID(invalidUserId);

            GetOwnedGeoCodesResponse response = userService.getOwnedGeoCodes(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
            Assertions.assertNull(response.getGeocodeIDs());
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getOwnedGeoCodesTestValidUser() {
        try{
            /*
             Create a request object
             and assign values to it
           */
            GetOwnedGeoCodesRequest request = new GetOwnedGeoCodesRequest();
            request.setUserID(validUserId);

            GetOwnedGeoCodesResponse response = userService.getOwnedGeoCodes(request);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The IDs of the User's owned GeoCodes was successfully returned", response.getMessage());

            List<UUID> ownedGeoCodeIDs = response.getGeocodeIDs();
            Assertions.assertNotNull(ownedGeoCodeIDs);
            Assertions.assertEquals(1, ownedGeoCodeIDs.size());
            Assertions.assertEquals(thirdGeoCodeID, ownedGeoCodeIDs.get(0));
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }
}
