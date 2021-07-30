package tech.geocodeapp.geocode.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import tech.geocodeapp.geocode.collectable.*;
import tech.geocodeapp.geocode.collectable.model.*;
import tech.geocodeapp.geocode.collectable.service.*;
import tech.geocodeapp.geocode.event.service.EventService;
import tech.geocodeapp.geocode.geocode.model.*;
import tech.geocodeapp.geocode.leaderboard.PointMockRepository;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;
import tech.geocodeapp.geocode.user.exception.NullUserRequestParameterException;
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
    private final String easterEventDescription = "Easter egg hunt in Hatfield";

    private final String menloParkChristmas = "Christmas 2021 market";
    private final String christmasEventDescription = "Christmas market in Menlo Park";

    @Mock( name = "collectableServiceImpl" )
    CollectableService collectableService;

    @Mock( name = "leaderboardServiceImpl" )
    LeaderboardService leaderboardService;

    @Mock( name = "eventService" )
    EventService eventService;

    UserServiceImplTest() {

    }

    @BeforeEach
    void setup() {
        CollectableTypeMockRepository collectableTypeMockRepo = new CollectableTypeMockRepository();
        CollectableMockRepository collectableMockRepo = new CollectableMockRepository();
        CollectableSetMockRepository collectableSetMockRepo = new CollectableSetMockRepository();

        UserMockRepository userMockRepo = new UserMockRepository();
        CollectableService collectableService = new CollectableServiceImpl(collectableMockRepo, collectableSetMockRepo, collectableTypeMockRepo);
        userService = new UserServiceImpl(userMockRepo, new CollectableMockRepository(), new PointMockRepository(),collectableService, leaderboardService);

        //save the valid trackable CollectableType
        CollectableType trackableCollectableType = new CollectableType();
        trackableCollectableType.setId(trackableUUID);
        collectableTypeMockRepo.save(trackableCollectableType);

        //save the valid user to the MockRepo
        RegisterNewUserRequest registerNewUserRequest = new RegisterNewUserRequest(validUserId, "john_smith");

        try {
            userService.registerNewUser(registerNewUserRequest);
        } catch (NullUserRequestParameterException e) {
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
        }catch(NullUserRequestParameterException e){
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
        } catch (NullUserRequestParameterException e) {
            e.printStackTrace();
            return;
        }

        /* create Leaderboards */
        /*Leaderboard easterLeaderboard = createEasterEventResponse.getEvent().getLeaderboard();
        Leaderboard christmasLeaderboard = createChristmasEventResponse.getEvent().getLeaderboard();

        /* (2) Assign points to the Users that should have Points /
        CreatePointRequest createPoint1Request = new CreatePointRequest(5, userWithPoints1, easterLeaderboard.getId());
        CreatePointRequest createPoint2Request = new CreatePointRequest(5, userWithPoints2, easterLeaderboard.getId());
        CreatePointRequest createPoint3Request = new CreatePointRequest(10, userWithPoints1, christmasLeaderboard.getId());
        CreatePointRequest createPoint4Request = new CreatePointRequest(5, userWithPoints2, christmasLeaderboard.getId());*/
    }

    @Test
    void getCurrentCollectableTestNullRequest() {
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
    void getCurrentCollectableTestNullId(){
           GetCurrentCollectableRequest request = new GetCurrentCollectableRequest();
           request.setUserID(null);

           assertThatThrownBy(() -> userService.getCurrentCollectable(request))
                .isInstanceOf(NullUserRequestParameterException.class);
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
        }catch (NullUserRequestParameterException e){
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
        }catch (NullUserRequestParameterException e){
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
        }catch (NullUserRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getUserTrackableTestNullId(){
        GetUserTrackableRequest request = new GetUserTrackableRequest();
        request.setUserID(null);

        assertThatThrownBy(() -> userService.getUserTrackable(request))
                .isInstanceOf(NullUserRequestParameterException.class);
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
        }catch (NullUserRequestParameterException e){
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
        }catch (NullUserRequestParameterException e){
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

    @Test
    void getMyLeaderboardsTestNullRequest(){
        try {
            GetMyLeaderboardsResponse response = userService.getMyLeaderboards(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetMyLeaderboardsRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getLeaderboards());
        } catch (NullUserRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getMyLeaderboardsTestNullUserID(){
        GetMyLeaderboardsRequest request = new GetMyLeaderboardsRequest();
        request.setUserID(null);

        assertThatThrownBy(() -> userService.getMyLeaderboards(request))
                    .isInstanceOf(NullUserRequestParameterException.class);
    }

    @Test
    void getMyLeaderboardsTestInvalidUserID(){
        GetMyLeaderboardsRequest request = new GetMyLeaderboardsRequest();
        request.setUserID(invalidUserId);

        try {
            GetMyLeaderboardsResponse response = userService.getMyLeaderboards(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
            Assertions.assertNull(response.getLeaderboards());
        } catch (NullUserRequestParameterException e) {
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
        } catch (NullUserRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getMyLeaderboardsTestUserWithPoints(){
        GetMyLeaderboardsRequest request = new GetMyLeaderboardsRequest();
        request.setUserID(userWithPoints1);

        try {
            GetMyLeaderboardsResponse response = userService.getMyLeaderboards(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The details for the User's Leaderboards were successfully returned", response.getMessage());
            Assertions.assertFalse(response.getLeaderboards().isEmpty());
        } catch (NullUserRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }
}
