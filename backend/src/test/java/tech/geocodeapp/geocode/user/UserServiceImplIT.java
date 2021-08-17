package tech.geocodeapp.geocode.user;

import java.util.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.transaction.annotation.Transactional;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.leaderboard.model.MyLeaderboardDetails;
import tech.geocodeapp.geocode.mission.model.Mission;
import tech.geocodeapp.geocode.mission.model.MissionType;
import tech.geocodeapp.geocode.user.model.User;
import tech.geocodeapp.geocode.user.service.*;
import tech.geocodeapp.geocode.user.request.*;
import tech.geocodeapp.geocode.user.response.*;

@SpringBootTest
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class UserServiceImplIT {
    @Autowired
    private UserService userService;

    private final UUID invalidUserId = UUID.fromString("31d72621-091c-49ad-9c28-8abda8b8f055");
    private final UUID validUserId = UUID.fromString("183e06b6-2130-45e3-8b43-634ccd3e8e6f");
    private final UUID newUserId = UUID.fromString("e03bd781-cca9-43bf-a168-0f0563fca591");

    private final UUID noPointsUserId = UUID.fromString("cdc0f9a0-65da-43c6-8d17-505d61c27965");
    private final UUID userWithPoints1 = UUID.fromString("a98e8a41-0d6f-454f-a5d9-df809d2c1040");
    private final UUID userWithPoints2 = UUID.fromString("960b6fd8-7283-43e8-9e18-2e6bef38fbb8");

    private final String invalidUserIdMessage = "Invalid User id";
    private final String invalidGeoCodeIdMessage = "Invalid GeoCode id";
    private final String invalidCollectableTypeIDMessage = "Invalid CollectableType ID";

    private final UUID firstGeoCodeID = UUID.fromString("537689d1-a0d8-4740-bec6-6a40bb69748e");
    private final UUID secondGeoCodeID = UUID.fromString("5d709c49-326b-470a-8d9d-e7f7bf77ef6e");
    private final UUID thirdGeoCodeID = UUID.fromString("92e3e6d5-5457-48f7-adb1-7c2f67ee836b");
    private final UUID trackableUUID = new UUID(0, 0);

    private final String firstEvent = "Port Elizabeth Beach Hop - Default";
    private final String secondEvent = "Port Elizabeth High School Open Days - Default";

    private final int user1FirstEventPoints = 5;
    private final int user2FirstEventPoints = 5;
    private final int user1SecondEventPoints = 10;
    private final int user2SecondEventPoints = 5;

    private int numberOfOwnedGeoCodesBefore;
    private int numberOfFoundGeoCodesBefore;
    private int numberOfFoundCollectableTypesBefore;

    private final UUID invalidGeoCodeID = UUID.fromString("c6dab51d-7b2c-45df-940c-189821a36178");
    private final UUID invalidCollectableID = UUID.fromString("4d2877ee-431e-4a46-b391-c9755291a0f6");
    private final UUID invalidCollectableTypeID = UUID.fromString("1c39987b-f7b6-478f-b99c-2c57928481af");

    private final UUID noPointsFirstOwnedGeoCodeID = UUID.fromString("c8c60a6d-9bfd-4a1c-a864-6140935a4296");
    private final UUID noPointsNewOwnedGeoCodeID = UUID.fromString("1589fcd5-aac5-4434-a435-d4d03df05703");

    private final UUID noPointsFirstFoundGeoCodeID = UUID.fromString("13edfce8-5ecd-4cce-ad05-9037df0cbb04");
    private final UUID noPointsNewFoundGeoCodeID = UUID.fromString("74358369-c953-4862-b3b4-7ca4b78a4c83");

    private final UUID testCollectableTypeID = UUID.fromString("c65410e3-54e0-4958-9ebb-12560a86ae16");
    private final UUID testCollectableType1ID = UUID.fromString("5350f61f-1052-42d0-8dea-9a7580cfd908");
    private final UUID testCollectableType2ID = UUID.fromString("cad9d680-6e0e-4c9f-9d05-45fb4b430fdd");
    private final UUID noPointsNewFoundCollectableTypeID = UUID.fromString("08603f0d-3262-4b71-a50b-4a4605f36bea");

    private final UUID collectableInFirstGeoCodeID = UUID.fromString("bf98dbf9-90b5-43ab-91b3-396d8f6ff216");

    private final UUID swapMissionID = UUID.fromString("4507f78a-2c0c-4073-9af0-7f50ffe2fa0f");
    private final UUID circumferenceMissionID = UUID.fromString("46e8e512-68ca-40d7-89ce-11ae91c58bbc");

    private final String existingUserIdMessage = "User ID already exists";

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
        }catch (NullRequestParameterException e){
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
        }catch (NullRequestParameterException e){
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
        }catch (NullRequestParameterException e){
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
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getFoundCollectableTypesTestInvalidUser() {
        try{
            /*
            Create a request object
            and assign values to it
            */
            GetFoundCollectableTypesRequest request = new GetFoundCollectableTypesRequest(invalidUserId);

            GetFoundCollectableTypesResponse response = userService.getFoundCollectableTypes(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
            Assertions.assertNull(response.getCollectableTypeIDs());
        }catch (NullRequestParameterException e){
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
            GetFoundCollectableTypesRequest request = new GetFoundCollectableTypesRequest(noPointsUserId);

            GetFoundCollectableTypesResponse response = userService.getFoundCollectableTypes(request);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The IDs of the User's found CollectableTypes was successfully returned", response.getMessage());

            List<UUID> foundCollectableTypeIDs = response.getCollectableTypeIDs();
            Assertions.assertNotNull(foundCollectableTypeIDs);
            Assertions.assertEquals(2, foundCollectableTypeIDs.size());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getFoundGeoCodesTestInvalidUser() {
        try{
            /*
            Create a request object
            and assign values to it
            */
            GetFoundGeoCodesRequest request = new GetFoundGeoCodesRequest(invalidUserId);

            GetFoundGeoCodesResponse response = userService.getFoundGeoCodes(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
            Assertions.assertNull(response.getGeocodeIDs());
        }catch (NullRequestParameterException e){
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
            GetFoundGeoCodesRequest request = new GetFoundGeoCodesRequest(validUserId);

            GetFoundGeoCodesResponse response = userService.getFoundGeoCodes(request);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The IDs of the User's found GeoCodes was successfully returned", response.getMessage());

            List<UUID> foundGeoCodeIDs = response.getGeocodeIDs();
            Assertions.assertNotNull(foundGeoCodeIDs);

            //HashSet will cause order to not necessarily be order added in
            Assertions.assertTrue(foundGeoCodeIDs.contains(firstGeoCodeID));
            Assertions.assertTrue(foundGeoCodeIDs.contains(secondGeoCodeID));
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
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
        }catch (NullRequestParameterException e){
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
        }catch (NullRequestParameterException e){
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
            request.setLocation(new GeoPoint(10.0f, 10.0f));

            UpdateLocationResponse response = userService.updateLocation(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
            Assertions.assertNull(response.getTrackable());
        }catch (NullRequestParameterException e){
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

            GeoPoint location = new GeoPoint(100.0f, 40.0f);
            request.setLocation(location);

            UpdateLocationResponse response = userService.updateLocation(request);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The trackable object's location was successfully updated", response.getMessage());

            Collectable trackableObject = response.getTrackable();
            Assertions.assertNotNull(trackableObject);

            List<GeoPoint> pastLocations = new ArrayList<>(trackableObject.getPastLocations());
            Assertions.assertEquals(location, pastLocations.get(pastLocations.size()-1));
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getMyLeaderboardsTestInvalidUser(){
        GetMyLeaderboardsRequest request = new GetMyLeaderboardsRequest();
        request.setUserID(invalidUserId);

        try {
            GetMyLeaderboardsResponse response = userService.getMyLeaderboards(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
            Assertions.assertNull(response.getLeaderboards());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getMyLeaderboardsTestUserWithNoPoints(){
        GetMyLeaderboardsRequest request = new GetMyLeaderboardsRequest();
        request.setUserID(noPointsUserId);

        try {
            GetMyLeaderboardsResponse response = userService.getMyLeaderboards(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The details for the User's Leaderboards were successfully returned", response.getMessage());

            System.out.println("printing the details for the leaderboards:");

            for(MyLeaderboardDetails details : response.getLeaderboards()){
                System.out.println(details.getName()+", "+details.getPoints()+", "+details.getRank());
            }

            Assertions.assertTrue(response.getLeaderboards().isEmpty());

        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getMyLeaderboardsTestUserWithPoints1(){
        GetMyLeaderboardsRequest request = new GetMyLeaderboardsRequest();
        request.setUserID(userWithPoints1);

        try {
            GetMyLeaderboardsResponse response = userService.getMyLeaderboards(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The details for the User's Leaderboards were successfully returned", response.getMessage());

            List<MyLeaderboardDetails> leaderboardDetails = response.getLeaderboards();

            /* check the user has points */
            Assertions.assertFalse(leaderboardDetails.isEmpty());

            /* check that the correct details are returned */

            //user1 ranked 1st for First event
            Assertions.assertTrue(leaderboardDetails.stream().anyMatch(details ->
                    details.getName().equals(firstEvent) &&
                            details.getPoints() == user1FirstEventPoints &&
                            details.getRank() == 1
            ));

            //user1 ranked 1st for Second event
            Assertions.assertTrue(leaderboardDetails.stream().anyMatch(details ->
                    details.getName().equals(secondEvent) &&
                            details.getPoints() == user1SecondEventPoints &&
                            details.getRank() == 1
            ));
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getMyLeaderboardsTestUserWithPoints2(){
        GetMyLeaderboardsRequest request = new GetMyLeaderboardsRequest();
        request.setUserID(userWithPoints2);

        try {
            GetMyLeaderboardsResponse response = userService.getMyLeaderboards(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The details for the User's Leaderboards were successfully returned", response.getMessage());

            List<MyLeaderboardDetails> leaderboardDetails = response.getLeaderboards();

            /* check the user has points */
            Assertions.assertFalse(leaderboardDetails.isEmpty());

            /* check that the correct details are returned */

            //user2 ranked 1st for First event
            Assertions.assertTrue(leaderboardDetails.stream().anyMatch(details ->
                    details.getName().equals(firstEvent) &&
                            details.getPoints() == user2FirstEventPoints &&
                            details.getRank() == 1
            ));

            //user1 ranked 2nd for Second event
            Assertions.assertTrue(leaderboardDetails.stream().anyMatch(details ->
                    details.getName().equals(secondEvent) &&
                            details.getPoints() == user2SecondEventPoints &&
                            details.getRank() == 2
            ));
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void AddToOwnedGeoCodesTestInvalidUserID(){
        try {
            AddToOwnedGeoCodesRequest request = new AddToOwnedGeoCodesRequest(invalidUserId, firstGeoCodeID);
            AddToOwnedGeoCodesResponse response = userService.addToOwnedGeoCodes(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void AddToOwnedGeoCodesTestInvalidGeoCodeID(){
        try {
            AddToOwnedGeoCodesRequest request = new AddToOwnedGeoCodesRequest(validUserId, invalidGeoCodeID);
            AddToOwnedGeoCodesResponse response = userService.addToOwnedGeoCodes(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidGeoCodeIdMessage, response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void AddToOwnedGeoCodesTestNotAddDuplicate(){
        try {
            AddToOwnedGeoCodesRequest request = new AddToOwnedGeoCodesRequest(noPointsUserId, noPointsFirstOwnedGeoCodeID);
            AddToOwnedGeoCodesResponse response = userService.addToOwnedGeoCodes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("GeoCode added to the owned GeoCodes", response.getMessage());

            GetOwnedGeoCodesRequest getOwnedGeoCodesRequest = new GetOwnedGeoCodesRequest(noPointsUserId);
            GetOwnedGeoCodesResponse getOwnedGeoCodesResponse = userService.getOwnedGeoCodes(getOwnedGeoCodesRequest);

            Assertions.assertTrue(getOwnedGeoCodesResponse.isSuccess());

            List<UUID> ownedGeoCodeIDs = getOwnedGeoCodesResponse.getGeocodeIDs();

            Assertions.assertEquals(2, ownedGeoCodeIDs.size());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void AddToOwnedGeoCodesTestAddNew(){
        try {
            //check number of owned GeoCodes before adding
            GetOwnedGeoCodesRequest getOwnedGeoCodesRequest = new GetOwnedGeoCodesRequest(noPointsUserId);
            GetOwnedGeoCodesResponse getOwnedGeoCodesResponse = userService.getOwnedGeoCodes(getOwnedGeoCodesRequest);

            Assertions.assertTrue(getOwnedGeoCodesResponse.isSuccess());

            List<UUID> ownedGeoCodeIDs = getOwnedGeoCodesResponse.getGeocodeIDs();
            Assertions.assertEquals(2, ownedGeoCodeIDs.size());

            //add a new owned GeoCode
            AddToOwnedGeoCodesRequest request = new AddToOwnedGeoCodesRequest(noPointsUserId, noPointsNewOwnedGeoCodeID);
            AddToOwnedGeoCodesResponse response = userService.addToOwnedGeoCodes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("GeoCode added to the owned GeoCodes", response.getMessage());

            //check that the number of owned GeoCodes has increased by 1
            getOwnedGeoCodesResponse = userService.getOwnedGeoCodes(getOwnedGeoCodesRequest);

            Assertions.assertTrue(getOwnedGeoCodesResponse.isSuccess());

            ownedGeoCodeIDs = getOwnedGeoCodesResponse.getGeocodeIDs();
            Assertions.assertEquals(3, ownedGeoCodeIDs.size());
            Assertions.assertTrue(ownedGeoCodeIDs.contains(noPointsNewOwnedGeoCodeID));
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void AddToFoundGeoCodesTestInvalidUserID(){
        try {
            AddToFoundGeoCodesRequest request = new AddToFoundGeoCodesRequest(invalidUserId, firstGeoCodeID);
            AddToFoundGeoCodesResponse response = userService.addToFoundGeoCodes(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void AddToFoundGeoCodesTestInvalidGeoCodeID(){
        try {
            AddToFoundGeoCodesRequest request = new AddToFoundGeoCodesRequest(validUserId, invalidGeoCodeID);
            AddToFoundGeoCodesResponse response = userService.addToFoundGeoCodes(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidGeoCodeIdMessage, response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void AddToFoundGeoCodesTestNotAddDuplicate(){
        try {
            AddToFoundGeoCodesRequest request = new AddToFoundGeoCodesRequest(noPointsUserId, noPointsFirstFoundGeoCodeID);
            AddToFoundGeoCodesResponse response = userService.addToFoundGeoCodes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("GeoCode added to the found GeoCodes", response.getMessage());

            GetFoundGeoCodesRequest getFoundGeoCodesRequest = new GetFoundGeoCodesRequest(noPointsUserId);
            GetFoundGeoCodesResponse getFoundGeoCodesResponse = userService.getFoundGeoCodes(getFoundGeoCodesRequest);

            Assertions.assertTrue(getFoundGeoCodesResponse.isSuccess());

            List<UUID> foundGeoCodeIDs = getFoundGeoCodesResponse.getGeocodeIDs();

            Assertions.assertEquals(2, foundGeoCodeIDs.size());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void AddToFoundGeoCodesTestAddNew(){
        try {
            //check number of found GeoCodes before adding
            GetFoundGeoCodesRequest getFoundGeoCodesRequest = new GetFoundGeoCodesRequest(noPointsUserId);
            GetFoundGeoCodesResponse getFoundGeoCodesResponse = userService.getFoundGeoCodes(getFoundGeoCodesRequest);

            Assertions.assertTrue(getFoundGeoCodesResponse.isSuccess());

            List<UUID> foundGeoCodeIDs = getFoundGeoCodesResponse.getGeocodeIDs();
            Assertions.assertEquals(2, foundGeoCodeIDs.size());

            //add a new found GeoCode
            AddToFoundGeoCodesRequest request = new AddToFoundGeoCodesRequest(noPointsUserId, noPointsNewFoundGeoCodeID);
            AddToFoundGeoCodesResponse response = userService.addToFoundGeoCodes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("GeoCode added to the found GeoCodes", response.getMessage());

            //check that the number of owned GeoCodes has increased by 1
            getFoundGeoCodesResponse = userService.getFoundGeoCodes(getFoundGeoCodesRequest);

            Assertions.assertTrue(getFoundGeoCodesResponse.isSuccess());

            foundGeoCodeIDs = getFoundGeoCodesResponse.getGeocodeIDs();
            Assertions.assertEquals(3, foundGeoCodeIDs.size());
            Assertions.assertTrue(foundGeoCodeIDs.contains(noPointsNewFoundGeoCodeID));
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void AddToFoundCollectableTypesTestInvalidUserID(){
        try {
            AddToFoundCollectableTypesRequest request = new AddToFoundCollectableTypesRequest(invalidUserId, testCollectableTypeID);
            AddToFoundCollectableTypesResponse response = userService.addToFoundCollectableTypes(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void AddToFoundCollectableTypesTestInvalidCollectableTypeID(){
        try {
            AddToFoundCollectableTypesRequest request = new AddToFoundCollectableTypesRequest(validUserId, invalidCollectableTypeID);
            AddToFoundCollectableTypesResponse response = userService.addToFoundCollectableTypes(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidCollectableTypeIDMessage, response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void AddToFoundCollectableTypesTestNotAddDuplicate(){
        try {
            AddToFoundCollectableTypesRequest request = new AddToFoundCollectableTypesRequest(validUserId, testCollectableType1ID);
            AddToFoundCollectableTypesResponse response = userService.addToFoundCollectableTypes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("CollectableType added to the found CollectableTypes", response.getMessage());

            GetFoundCollectableTypesRequest getFoundCollectableTypesRequest = new GetFoundCollectableTypesRequest(noPointsUserId);
            GetFoundCollectableTypesResponse getFoundCollectableTypesResponse = userService.getFoundCollectableTypes(getFoundCollectableTypesRequest);

            Assertions.assertTrue(getFoundCollectableTypesResponse.isSuccess());

            List<UUID> foundCollectableTypeIDs = getFoundCollectableTypesResponse.getCollectableTypeIDs();

            Assertions.assertEquals(2, foundCollectableTypeIDs.size());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void AddToFoundCollectableTypesTestAddNew(){
        try{
            //check number of found CollectableTypes before adding
            GetFoundCollectableTypesRequest getFoundCollectableTypesRequest = new GetFoundCollectableTypesRequest(noPointsUserId);
            GetFoundCollectableTypesResponse getFoundCollectableTypesResponse = userService.getFoundCollectableTypes(getFoundCollectableTypesRequest);

            Assertions.assertTrue(getFoundCollectableTypesResponse.isSuccess());

            List<UUID> foundCollectableTypeIDs = getFoundCollectableTypesResponse.getCollectableTypeIDs();
            Assertions.assertEquals(2, foundCollectableTypeIDs.size());

            //add a new found CollectableType
            AddToFoundCollectableTypesRequest request = new AddToFoundCollectableTypesRequest(noPointsUserId, noPointsNewFoundCollectableTypeID);
            AddToFoundCollectableTypesResponse response = userService.addToFoundCollectableTypes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("CollectableType added to the found CollectableTypes", response.getMessage());
    
            //check that the number of owned CollectableTypes has increased by 1
            getFoundCollectableTypesResponse = userService.getFoundCollectableTypes(getFoundCollectableTypesRequest);

            Assertions.assertTrue(getFoundCollectableTypesResponse.isSuccess());
    
            foundCollectableTypeIDs = getFoundCollectableTypesResponse.getCollectableTypeIDs();
            Assertions.assertEquals(3, foundCollectableTypeIDs.size());
            Assertions.assertTrue(foundCollectableTypeIDs.contains(noPointsNewFoundCollectableTypeID));
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserByIdTestInvalidUserId(){
        try {
            GetUserByIdRequest request = new GetUserByIdRequest(invalidUserId);
            GetUserByIdResponse response = userService.getUserById(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserByIdTestValidUserId(){
        try {
            GetUserByIdRequest request = new GetUserByIdRequest(validUserId);
            GetUserByIdResponse response = userService.getUserById(request);
            User user = response.getUser();

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The User was found", response.getMessage());

            Assertions.assertEquals(validUserId, user.getId());
            Assertions.assertEquals("michael", user.getUsername());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registerNewUserTestExistingUserId(){
        try {
            RegisterNewUserRequest request = new RegisterNewUserRequest(validUserId, "john");
            RegisterNewUserResponse response = userService.registerNewUser(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(existingUserIdMessage, response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void registerNewUserTestNewUserId(){
        try {
            String newUsername = "bob";
            RegisterNewUserRequest request = new RegisterNewUserRequest(newUserId, newUsername);
            RegisterNewUserResponse response = userService.registerNewUser(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("New User registered", response.getMessage());

            GetUserByIdRequest getUserByIdRequest = new GetUserByIdRequest(newUserId);
            GetUserByIdResponse getUserByIdResponse = userService.getUserById(getUserByIdRequest);

            Assertions.assertTrue(getUserByIdResponse.isSuccess());

            User user = getUserByIdResponse.getUser();

            Assertions.assertEquals(trackableUUID, user.getTrackableObject().getType().getId());
            Assertions.assertEquals(trackableUUID, user.getCurrentCollectable().getType().getId());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void swapCollectableTestCollectableInvalidUserID(){
        try {
            SwapCollectableRequest request = new SwapCollectableRequest(invalidUserId, collectableInFirstGeoCodeID, firstGeoCodeID);
            SwapCollectableResponse response = userService.swapCollectable(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());

            Collectable collectable = response.getCollectable();
            Assertions.assertNull(collectable);
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void swapCollectableTestCollectableInvalidGeoCodeID(){
        try {
            SwapCollectableRequest request = new SwapCollectableRequest(validUserId, collectableInFirstGeoCodeID, invalidGeoCodeID);
            SwapCollectableResponse response = userService.swapCollectable(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Invalid ID given for the GeoCode", response.getMessage());

            Collectable collectable = response.getCollectable();
            Assertions.assertNull(collectable);
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void swapCollectableTestCollectableInvalidCollectableID(){
        try {
            SwapCollectableRequest request = new SwapCollectableRequest(validUserId, invalidCollectableID, firstGeoCodeID);
            SwapCollectableResponse response = userService.swapCollectable(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Invalid ID given for the Collectable", response.getMessage());

            Collectable collectable = response.getCollectable();
            Assertions.assertNull(collectable);
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Transactional
    public void swapCollectableTestCollectableIsSwapped(){
        try {
            SwapCollectableRequest request = new SwapCollectableRequest(validUserId, collectableInFirstGeoCodeID, firstGeoCodeID);
            SwapCollectableResponse response = userService.swapCollectable(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The User's Collectable was swapped with the Collectable in the GeoCode", response.getMessage());

            Collectable collectable = response.getCollectable();
            Assertions.assertNotNull(collectable);

            System.out.println("collectable that comes out: "+collectable.getId());

            GetCurrentCollectableRequest getCurrentCollectableRequest = new GetCurrentCollectableRequest();
            getCurrentCollectableRequest.setUserID(validUserId);

            GetCurrentCollectableResponse getCurrentCollectableResponse = userService.getCurrentCollectable(getCurrentCollectableRequest);

            Assertions.assertTrue(getCurrentCollectableResponse.isSuccess());

            //test that the User's Collectable is now the swapped out Collectable
            Collectable currentCollectable = getCurrentCollectableResponse.getCollectable();
            Assertions.assertEquals(collectableInFirstGeoCodeID, currentCollectable.getId());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getMyMissionsTestInvalidUser(){
        GetMyMissionsRequest request = new GetMyMissionsRequest(invalidUserId);

        try {
            GetMyMissionsResponse response = userService.getMyMissions(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
            Assertions.assertNull(response.getMissions());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Transactional
    void getMyMissionsTestValidUser(){
        GetMyMissionsRequest request = new GetMyMissionsRequest(validUserId);

        try {
            GetMyMissionsResponse response = userService.getMyMissions(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("User Missions returned", response.getMessage());

            Set<Mission> missions = response.getMissions();
            Assertions.assertNotNull(missions);

            Assertions.assertTrue(missions.stream().anyMatch(mission -> mission.getId().equals(swapMissionID) && mission.getType().equals(MissionType.SWAP)));
            Assertions.assertTrue(missions.stream().anyMatch(mission -> mission.getId().equals(circumferenceMissionID) && mission.getType().equals(MissionType.DISTANCE)));
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }
}
