package tech.geocodeapp.geocode.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import tech.geocodeapp.geocode.collectable.*;
import tech.geocodeapp.geocode.collectable.model.*;
import tech.geocodeapp.geocode.collectable.service.*;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.model.*;
import tech.geocodeapp.geocode.leaderboard.LeaderboardMockRepository;
import tech.geocodeapp.geocode.leaderboard.PointMockRepository;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.model.MyLeaderboardDetails;
import tech.geocodeapp.geocode.leaderboard.model.Point;
import tech.geocodeapp.geocode.user.model.User;
import tech.geocodeapp.geocode.user.service.*;
import tech.geocodeapp.geocode.user.request.*;
import tech.geocodeapp.geocode.user.response.*;

@ExtendWith( MockitoExtension.class )
public class UserServiceImplTest {
    private UserService userService;

    private final UUID invalidUserId = UUID.fromString("31d72621-091c-49ad-9c28-8abda8b8f055");
    private final UUID validUserId = UUID.fromString("183e06b6-2130-45e3-8b43-634ccd3e8e6f");
    private final UUID userWithPoints1 = UUID.fromString("f1f1cc86-47f0-4cdd-b313-e9275b9e8925");
    private final UUID userWithPoints2 = UUID.fromString("38437809-528e-464e-a81f-140ad9f50cda");
    private final UUID firstGeoCodeID = UUID.fromString("0998cf20-8256-4529-b144-d3c8aa4f0fb1");
    private final UUID secondGeoCodeID = UUID.fromString("8c3e3a65-118b-47ca-8cca-097134cd00d9");
    private final UUID thirdGeoCodeID = UUID.fromString("7b32fce8-44e4-422b-a80d-521d490e9ee3");
    private final UUID trackableUUID = UUID.fromString("0855b7da-bdad-44b7-9c22-18fe266ceaf3");

    private final String invalidUserIdMessage = "Invalid user id";

    private final String hatfieldEaster = "Hatfield Easter Hunt 2021";
    private final String menloParkChristmas = "Christmas 2021 market";

    private final int user1EasterPoints = 5;
    private final int user2EasterPoints = 5;
    private final int user1ChristmasPoints = 10;
    private final int user2ChristmasPoints = 5;

    UserServiceImplTest() {

    }

    @BeforeEach
    void setup() {
        CollectableTypeMockRepository collectableTypeMockRepo = new CollectableTypeMockRepository();
        CollectableMockRepository collectableMockRepo = new CollectableMockRepository();
        CollectableSetMockRepository collectableSetMockRepo = new CollectableSetMockRepository();

        LeaderboardMockRepository leaderboardMockRepo = new LeaderboardMockRepository();
        PointMockRepository pointMockRepo = new PointMockRepository();

        UserMockRepository userMockRepo = new UserMockRepository();
        CollectableService collectableService = new CollectableServiceImpl(collectableMockRepo, collectableSetMockRepo, collectableTypeMockRepo);
        userService = new UserServiceImpl(userMockRepo, new CollectableMockRepository(), new PointMockRepository(),collectableService, null, null);

        //save the valid trackable CollectableType
        CollectableType trackableCollectableType = new CollectableType();
        trackableCollectableType.setId(trackableUUID);
        collectableTypeMockRepo.save(trackableCollectableType);

        //save the valid user to the MockRepo
        RegisterNewUserRequest registerNewUserRequest = new RegisterNewUserRequest(validUserId, "john_smith");

        try {
            userService.registerNewUser(registerNewUserRequest);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return;
        }

        //make 3 CollectableTypes for Easter
        CollectableType egg = new CollectableType();
        egg.setId(UUID.fromString("650e77b0-ccf4-43ab-9279-864d9c659010"));
        collectableTypeMockRepo.save(egg);

        CollectableType chocolateBar = new CollectableType();
        chocolateBar.setId(UUID.fromString("8f9b8919-2c02-4458-9d80-80b06710eb08"));
        collectableTypeMockRepo.save(chocolateBar);

        CollectableType bunny = new CollectableType();
        bunny.setId(UUID.fromString("0998cf20-8256-4529-b144-d3c8aa4f0fb1"));
        collectableTypeMockRepo.save(bunny);

        User validUser;

        //add to the User's found CollectableTypes
        try{
            GetUserByIdRequest getUserByIdRequest = new GetUserByIdRequest(validUserId);
            GetUserByIdResponse getUserByIdResponse = userService.getUserById(getUserByIdRequest);
            validUser = getUserByIdResponse.getUser();
        }catch(NullRequestParameterException e){
            e.printStackTrace();
            return;
        }

        validUser.addFoundCollectableTypesItem(egg);
        validUser.addFoundCollectableTypesItem(chocolateBar);
        validUser.addFoundCollectableTypesItem(bunny);

        //add 3 GeoCodes, not actual UUIDs -> easier to identify when testing
        GeoCode geoCode1 = new GeoCode();
        geoCode1.setId(firstGeoCodeID);
        
        GeoCode geoCode2 = new GeoCode();
        geoCode2.setId(secondGeoCodeID);

        GeoCode geoCode3 = new GeoCode();
        geoCode3.setId(thirdGeoCodeID);
        
        //add 1 and 2 to foundGeoCodes
        validUser.addFoundGeocodesItem(geoCode1);
        validUser.addFoundGeocodesItem(geoCode2);
        
        //add 3 to ownedGeoCodes
        validUser.addOwnedGeocodesItem(geoCode3);

        //update the User's details
        userMockRepo.save(validUser);

        /* add two Users that will have points */
        RegisterNewUserRequest registerNewUserRequest1 = new RegisterNewUserRequest(userWithPoints1, "alice");
        RegisterNewUserRequest registerNewUserRequest2 = new RegisterNewUserRequest(userWithPoints2, "bob");

        try {
            userService.registerNewUser(registerNewUserRequest1);
            userService.registerNewUser(registerNewUserRequest2);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return;
        }

        /* get the users with points */
        User user1;
        User user2;

        try{
            GetUserByIdRequest getUser1ByIdRequest = new GetUserByIdRequest(userWithPoints1);
            GetUserByIdResponse getUser1ByIdResponse = userService.getUserById(getUser1ByIdRequest);
            user1 = getUser1ByIdResponse.getUser();

            GetUserByIdRequest getUser2ByIdRequest = new GetUserByIdRequest(userWithPoints2);
            GetUserByIdResponse getUser2ByIdResponse = userService.getUserById(getUser2ByIdRequest);
            user2 = getUser2ByIdResponse.getUser();
        }catch(NullRequestParameterException e){
            e.printStackTrace();
            return;
        }

        /* create Leaderboards */
        Leaderboard easterLeaderboard = new Leaderboard(hatfieldEaster);
        Leaderboard christmasLeaderboard = new Leaderboard(menloParkChristmas);

        leaderboardMockRepo.save(easterLeaderboard);
        leaderboardMockRepo.save(christmasLeaderboard);

        /* (2) Assign points to the Users that should have Points */
        pointMockRepo.save(new Point(user1EasterPoints, user1, easterLeaderboard));
        pointMockRepo.save(new Point(user2EasterPoints, user2, easterLeaderboard));
        pointMockRepo.save(new Point(user1ChristmasPoints, user1, christmasLeaderboard));
        pointMockRepo.save(new Point(user2ChristmasPoints, user2, christmasLeaderboard));
    }

    @Test
    void getCurrentCollectableTestNullRequest() {
        try{
            GetCurrentCollectableResponse response = userService.getCurrentCollectable(null);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetCurrentCollectableRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getCollectable());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getCurrentCollectableTestNullId(){
           GetCurrentCollectableRequest request = new GetCurrentCollectableRequest();
           request.setUserID(null);

           assertThatThrownBy(() -> userService.getCurrentCollectable(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    void getCurrentCollectableTestInvalidUser() {
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
    void getCurrentCollectableTestValidUser() {
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
    void getUserTrackableTestNullRequest() {
        try{
            GetUserTrackableResponse response = userService.getUserTrackable(null);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetUserTrackableRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getTrackable());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getUserTrackableTestNullId(){
        GetUserTrackableRequest request = new GetUserTrackableRequest();
        request.setUserID(null);

        assertThatThrownBy(() -> userService.getUserTrackable(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    void getUserTrackableTestInvalidUser() {
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
    void getUserTrackableTestValidUser() {
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
            Assertions.assertEquals(trackableUUID, trackableObject.getType().getId());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getFoundCollectableTypesTestNullRequest() {
        try{
            GetFoundCollectableTypesResponse response = userService.getFoundCollectableTypes(null);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetFoundCollectableTypesRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getCollectableTypeIDs());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getFoundCollectableTypesTestNullId(){
        GetFoundCollectableTypesRequest request = new GetFoundCollectableTypesRequest();
        request.setUserID(null);

        assertThatThrownBy(() -> userService.getFoundCollectableTypes(request))
                .isInstanceOf(NullRequestParameterException.class);
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
            GetFoundCollectableTypesRequest request = new GetFoundCollectableTypesRequest();
            request.setUserID(validUserId);

            GetFoundCollectableTypesResponse response = userService.getFoundCollectableTypes(request);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The IDs of the User's found CollectableTypes was successfully returned", response.getMessage());

            List<UUID> foundCollectableTypeIDs = response.getCollectableTypeIDs();
            Assertions.assertNotNull(foundCollectableTypeIDs);
            Assertions.assertEquals(3, foundCollectableTypeIDs.size());
        }catch (NullRequestParameterException e){
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
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getFoundGeoCodesTestNullId(){
        GetFoundGeoCodesRequest request = new GetFoundGeoCodesRequest();
        request.setUserID(null);

        assertThatThrownBy(() -> userService.getFoundGeoCodes(request))
                .isInstanceOf(NullRequestParameterException.class);
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
        }catch (NullRequestParameterException e){
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
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getOwnedGeoCodesTestNullId(){
        GetOwnedGeoCodesRequest request = new GetOwnedGeoCodesRequest();
        request.setUserID(null);

        assertThatThrownBy(() -> userService.getOwnedGeoCodes(request))
                .isInstanceOf(NullRequestParameterException.class);
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
    public void updateLocationTestNullRequest(){
        try{
            UpdateLocationResponse response = userService.updateLocation(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The UpdateLocationRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getTrackable());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    /**
     * Test for when userID parameter is NULL.
     *
     * A NullRequestParameterException will be returned no matter which parameter is NULL,
     * since all fields are checked.
     */
    @Test
    public void updateLocationTestNullUser(){
        UpdateLocationRequest request = new UpdateLocationRequest();
        request.setUserID(validUserId);

        assertThatThrownBy(() -> userService.updateLocation(request))
                .isInstanceOf(NullRequestParameterException.class);
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
    void getMyLeaderboardsTestNullRequest(){
        try {
            GetMyLeaderboardsResponse response = userService.getMyLeaderboards(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetMyLeaderboardsRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getLeaderboards());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getMyLeaderboardsTestNullUser(){
        GetMyLeaderboardsRequest request = new GetMyLeaderboardsRequest();
        request.setUserID(null);

        assertThatThrownBy(() -> userService.getMyLeaderboards(request))
                    .isInstanceOf(NullRequestParameterException.class);
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
        request.setUserID(validUserId);

        try {
            GetMyLeaderboardsResponse response = userService.getMyLeaderboards(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The details for the User's Leaderboards were successfully returned", response.getMessage());
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

            //user1 ranked 1st for Easter event
            Assertions.assertTrue(leaderboardDetails.stream().anyMatch(details ->
                    details.getName().equals(hatfieldEaster) &&
                    details.getPoints() == user1EasterPoints &&
                    details.getRank() == 1
            ));

            //user1 ranked 1st for Christmas event
            Assertions.assertTrue(leaderboardDetails.stream().anyMatch(details ->
                    details.getName().equals(menloParkChristmas) &&
                    details.getPoints() == user1ChristmasPoints &&
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

            //user2 ranked 1st for Easter event
            Assertions.assertTrue(leaderboardDetails.stream().anyMatch(details ->
                    details.getName().equals(hatfieldEaster) &&
                    details.getPoints() == user2EasterPoints &&
                    details.getRank() == 1
            ));

            //user1 ranked 2nd for Christmas event
            Assertions.assertTrue(leaderboardDetails.stream().anyMatch(details ->
                    details.getName().equals(menloParkChristmas) &&
                            details.getPoints() == user2ChristmasPoints &&
                            details.getRank() == 2
            ));
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }
}
