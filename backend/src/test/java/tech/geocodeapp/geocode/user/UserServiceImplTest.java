package tech.geocodeapp.geocode.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import tech.geocodeapp.geocode.collectable.CollectableMockRepository;
import tech.geocodeapp.geocode.collectable.CollectableSetMockRepository;
import tech.geocodeapp.geocode.collectable.CollectableTypeMockRepository;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.collectable.model.CollectableSet;
import tech.geocodeapp.geocode.collectable.model.CollectableType;
import tech.geocodeapp.geocode.collectable.model.Rarity;
import tech.geocodeapp.geocode.collectable.service.CollectableServiceImpl;
import tech.geocodeapp.geocode.general.MockCurrentUserDetails;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.GeoCodeMockRepository;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.leaderboard.LeaderboardMockRepository;
import tech.geocodeapp.geocode.leaderboard.PointMockRepository;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.model.Point;
import tech.geocodeapp.geocode.mission.MissionMockRepository;
import tech.geocodeapp.geocode.mission.model.Mission;
import tech.geocodeapp.geocode.mission.model.MissionType;
import tech.geocodeapp.geocode.mission.service.MissionServiceImpl;
import tech.geocodeapp.geocode.user.model.User;
import tech.geocodeapp.geocode.user.request.*;
import tech.geocodeapp.geocode.user.service.UserService;
import tech.geocodeapp.geocode.user.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith( MockitoExtension.class )
@MockitoSettings(strictness = Strictness.LENIENT) //to avoid MockSecurity error
public class UserServiceImplTest {
    private UserService userService;
    private UserMockRepository userMockRepo;

    private MockCurrentUserDetails mockCurrentUserDetails;

    private User validUser;

    private GeoCode firstGeoCode;
    private GeoCode secondGeoCode;
    private GeoCode thirdGeoCode;

    private final UUID invalidUserId = UUID.fromString("31d72621-091c-49ad-9c28-8abda8b8f055");
    private UUID validUserId;
    private final UUID newUserId = UUID.fromString("e03bd781-cca9-43bf-a168-0f0563fca591");

    private UUID userWithPoints1;
    private UUID userWithPoints2;
    private final UUID firstGeoCodeID = UUID.fromString("0998cf20-8256-4529-b144-d3c8aa4f0fb1");
    private final UUID secondGeoCodeID = UUID.fromString("8c3e3a65-118b-47ca-8cca-097134cd00d9");
    private final UUID thirdGeoCodeID = UUID.fromString("7b32fce8-44e4-422b-a80d-521d490e9ee3");

    private final UUID trackableUUID = new UUID(0, 0);

    private final UUID geoCodeWithCollectablesID = UUID.fromString("fd8b4bed-89c9-4f76-872d-9124ca5061f6");
    private final UUID fishCollectableID = UUID.fromString("cfb23fdb-7b9e-4f67-ad67-b2bab0e7541a");
    private final UUID ballCollectableID = UUID.fromString("49b544b6-307c-4905-bc5e-f61c2afdd56b");

    private final UUID fishCollectableTypeID = UUID.fromString("91216b44-b123-486c-8ba7-1c2da7d0feef");
    private final UUID ballCollectableTypeID = UUID.fromString("f85ebdce-a569-4d47-9274-d4b0245c4713");
    
    private final UUID eggCollectableTypeID = UUID.fromString("650e77b0-ccf4-43ab-9279-864d9c659010");
    private final UUID chocolateBarCollectableTypeID = UUID.fromString("8f9b8919-2c02-4458-9d80-80b06710eb08");
    private final UUID bunnyCollectableTypeID = UUID.fromString("0998cf20-8256-4529-b144-d3c8aa4f0fb1");

    private final UUID swapMissionID = UUID.fromString("1f1eac14-20ef-4f13-9b0d-7101fea996f5");
    private final UUID circumferenceMissionID = UUID.fromString("88705bf1-3f5e-42a8-ae91-f29bbe5c9dd4");

    private final String invalidUserIdMessage = "Invalid User id";

    private final String hatfieldEaster = "Hatfield Easter Hunt 2021";
    private final String menloParkChristmas = "Christmas 2021 market";

    private final int user1EasterPoints = 5;
    private final int user2EasterPoints = 5;
    private final int user1ChristmasPoints = 10;
    private final int user2ChristmasPoints = 5;

    private int numberOfOwnedGeoCodesBefore;
    private int numberOfFoundGeoCodesBefore;
    private int numberOfFoundCollectableTypesBefore;

    private GeoCode geoCodeWithCollectables;
    private Collectable fishCollectable;

    private CollectableType egg;

    UserServiceImplTest() {

    }

    private User getUserByID(UUID userID) {
        try {
            return userService.getUserById(new GetUserByIdRequest(userID)).getUser();
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private UUID registerNewUser(String username){
        try {
            return userService.registerNewUser(new RegisterNewUserRequest(new GeoPoint(0.0, 0.0))).getUser().getId();
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    @BeforeEach
    void setup() {
        var collectableTypeMockRepo = new CollectableTypeMockRepository();
        var collectableMockRepo = new CollectableMockRepository();
        var collectableSetMockRepo = new CollectableSetMockRepository();

        var leaderboardMockRepo = new LeaderboardMockRepository();
        var pointMockRepo = new PointMockRepository();

        var geoCodeMockRepo = new GeoCodeMockRepository();

        var missionMockRepo = new MissionMockRepository();
        var missionService = new MissionServiceImpl(missionMockRepo);

        userMockRepo = new UserMockRepository();
        var collectableService = new CollectableServiceImpl(collectableMockRepo, collectableSetMockRepo, collectableTypeMockRepo, missionService);

        mockCurrentUserDetails = new MockCurrentUserDetails();

        userService = new UserServiceImpl(userMockRepo, collectableMockRepo, new PointMockRepository(), collectableService, missionService, mockCurrentUserDetails);

        //save the valid trackable CollectableType
        var trackableCollectableType = new CollectableType();
        trackableCollectableType.setId(trackableUUID);
        trackableCollectableType.setName("User Trackable");
        trackableCollectableType.setRarity(Rarity.COMMON);

        HashMap<String, String> properties = new HashMap<>();
        properties.put("missionType", String.valueOf(MissionType.RANDOM));
        trackableCollectableType.setProperties(properties);

        collectableTypeMockRepo.save(trackableCollectableType);

        //save the valid user to the MockRepo
        validUserId = registerNewUser("validUser");

        //make 3 CollectableTypes for Easter
        egg = new CollectableType();
        egg.setId(eggCollectableTypeID);
        egg.setName("egg");
        collectableTypeMockRepo.save(egg);

        var chocolateBar = new CollectableType();
        chocolateBar.setId(chocolateBarCollectableTypeID);
        chocolateBar.setName("chocolateBar");
        collectableTypeMockRepo.save(chocolateBar);

        var bunny = new CollectableType();
        bunny.setId(bunnyCollectableTypeID);
        bunny.setName("bunny");
        collectableTypeMockRepo.save(bunny);

        //add to the User's found CollectableTypes
        try{
            var getUserByIdRequest = new GetUserByIdRequest(validUserId);
            var getUserByIdResponse = userService.getUserById(getUserByIdRequest);
            validUser = getUserByIdResponse.getUser();
        }catch(NullRequestParameterException e){
            e.printStackTrace();
            return;
        }

        validUser.addFoundCollectableTypesItem(egg);
        validUser.addFoundCollectableTypesItem(chocolateBar);
        validUser.addFoundCollectableTypesItem(bunny);

        //add 3 GeoCodes, not actual UUIDs -> easier to identify when testing
        firstGeoCode = new GeoCode();
        firstGeoCode.setId(firstGeoCodeID);
        
        secondGeoCode = new GeoCode();
        secondGeoCode.setId(secondGeoCodeID);

        thirdGeoCode = new GeoCode();
        thirdGeoCode.setId(thirdGeoCodeID);

        geoCodeMockRepo.save(firstGeoCode);
        geoCodeMockRepo.save(secondGeoCode);
        geoCodeMockRepo.save(thirdGeoCode);

        //add 1 and 2 to foundGeoCodes
        validUser.addFoundGeocodesItem(firstGeoCode);
        validUser.addFoundGeocodesItem(secondGeoCode);
        
        //add 3 to ownedGeoCodes
        validUser.addOwnedGeocodesItem(thirdGeoCode);

        numberOfOwnedGeoCodesBefore = validUser.getOwnedGeocodes().size();
        numberOfFoundGeoCodesBefore = validUser.getFoundGeocodes().size();
        numberOfFoundCollectableTypesBefore = validUser.getFoundCollectableTypes().size();

        //display the User's currentCollectableID
        //System.out.println("the User's currentCollectableID: "+validUser.getCurrentCollectable().getId());

        //add Missions to the User
        var swapMission = new Mission();
        swapMission.setId(swapMissionID);
        swapMission.setType(MissionType.SWAP);

        var circumferenceMission = new Mission();
        circumferenceMission.setId(circumferenceMissionID);
        circumferenceMission.setType(MissionType.DISTANCE);

        validUser.addMissionsItem(swapMission);
        validUser.addMissionsItem(circumferenceMission);

        //update the User's details
        userMockRepo.save(validUser);

        /* add two Users that will have points */
        userWithPoints1 = registerNewUser("alice");
        userWithPoints2 = registerNewUser("bob");

        /* get the users with points */
        User user1;
        User user2;

        try{
            var getUser1ByIdRequest = new GetUserByIdRequest(userWithPoints1);
            var getUser1ByIdResponse = userService.getUserById(getUser1ByIdRequest);
            user1 = getUser1ByIdResponse.getUser();

            var getUser2ByIdRequest = new GetUserByIdRequest(userWithPoints2);
            var getUser2ByIdResponse = userService.getUserById(getUser2ByIdRequest);
            user2 = getUser2ByIdResponse.getUser();
        }catch(NullRequestParameterException e){
            e.printStackTrace();
            return;
        }

        /* create Leaderboards */
        var easterLeaderboard = new Leaderboard(hatfieldEaster);
        var christmasLeaderboard = new Leaderboard(menloParkChristmas);

        leaderboardMockRepo.save(easterLeaderboard);
        leaderboardMockRepo.save(christmasLeaderboard);

        /* (2) Assign points to the Users that should have Points */
        pointMockRepo.save(new Point(user1EasterPoints, user1, easterLeaderboard));
        pointMockRepo.save(new Point(user2EasterPoints, user2, easterLeaderboard));
        pointMockRepo.save(new Point(user1ChristmasPoints, user1, christmasLeaderboard));
        pointMockRepo.save(new Point(user2ChristmasPoints, user2, christmasLeaderboard));

        /* create a GeoCode with Collectables for the User to swap out */
        geoCodeWithCollectables = new GeoCode();
        geoCodeWithCollectables.setId(geoCodeWithCollectablesID);
        geoCodeWithCollectables.setLocation(new GeoPoint(10.0, 10.0));

        //save the Collectables
        var collectableSet = new CollectableSet("Test Set", "CollectableSet for testing");
        collectableSetMockRepo.save(collectableSet);

        var fishType = new CollectableType("fish", "fish_image", Rarity.COMMON, collectableSet, new HashMap<>());
        fishType.setId(fishCollectableTypeID);
        fishType.setName("fish");
        collectableTypeMockRepo.save(fishType);

        fishCollectable = new Collectable(fishType);
        fishCollectable.setId(fishCollectableID);
        collectableMockRepo.save(fishCollectable);

        var ballType = new CollectableType("ball", "ball_image", Rarity.UNCOMMON, collectableSet, new HashMap<>());
        ballType.setId(ballCollectableTypeID);
        ballType.setName("ball");
        collectableTypeMockRepo.save(ballType);

        var ballCollectable = new Collectable();
        ballCollectable.setId(ballCollectableID);
        collectableMockRepo.save(ballCollectable);

        //add the Collectables to the GeoCode
        geoCodeWithCollectables.addCollectablesItem(fishCollectableID);
        geoCodeWithCollectables.addCollectablesItem(ballCollectableID);

        geoCodeMockRepo.save(geoCodeWithCollectables);
    }

    @Test
    void getCurrentCollectableTestNullRequest() {
        try{
            var response = userService.getCurrentCollectable(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetCurrentCollectableRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getCollectable());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getCurrentCollectableTestNullId(){
           var request = new GetCurrentCollectableRequest();
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
            var request = new GetCurrentCollectableRequest();
            request.setUserID(invalidUserId);//invalid UUID (no user has it)

            var response = userService.getCurrentCollectable(request);

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
            var request = new GetCurrentCollectableRequest();
            request.setUserID(validUserId);

            var response = userService.getCurrentCollectable(request);

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
            var response = userService.getUserTrackable(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetUserTrackableRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getTrackable());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getUserTrackableTestNullId(){
        var request = new GetUserTrackableRequest();
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
            var request = new GetUserTrackableRequest();
            request.setUserID(invalidUserId);

            var response = userService.getUserTrackable(request);

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
            var request = new GetUserTrackableRequest();
            request.setUserID(validUserId);

            var response = userService.getUserTrackable(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The user's Trackable was successfully returned", response.getMessage());

            var trackableObject = response.getTrackable();

            Assertions.assertNotNull(trackableObject);
            Assertions.assertEquals(trackableUUID, trackableObject.getType().getId());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getFoundCollectableTypesTestNullRequest() {
        try{
            var response = userService.getFoundCollectableTypes(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetFoundCollectableTypesRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getCollectableTypeIDs());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getFoundCollectableTypesTestNullId(){
        var request = new GetFoundCollectableTypesRequest(null);

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
            var request = new GetFoundCollectableTypesRequest(invalidUserId);
            var response = userService.getFoundCollectableTypes(request);

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
            var request = new GetFoundCollectableTypesRequest(validUserId);
            var response = userService.getFoundCollectableTypes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The IDs of the User's found CollectableTypes was successfully returned", response.getMessage());

            var foundCollectableTypeIDs = response.getCollectableTypeIDs();

            Assertions.assertNotNull(foundCollectableTypeIDs);
            Assertions.assertEquals(numberOfFoundCollectableTypesBefore, foundCollectableTypeIDs.size());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getFoundGeoCodesTestNullRequest() {
        try{
            var response = userService.getFoundGeoCodes(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetFoundGeoCodesRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getGeocodeIDs());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getFoundGeoCodesTestNullId(){
        var request = new GetFoundGeoCodesRequest(null);

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
            var request = new GetFoundGeoCodesRequest(invalidUserId);
            var response = userService.getFoundGeoCodes(request);

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
            var request = new GetFoundGeoCodesRequest(validUserId);
            var response = userService.getFoundGeoCodes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The IDs of the User's found GeoCodes was successfully returned", response.getMessage());

            var foundGeoCodeIDs = response.getGeocodeIDs();

            Assertions.assertNotNull(foundGeoCodeIDs);
            Assertions.assertEquals(numberOfFoundGeoCodesBefore, foundGeoCodeIDs.size());

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
            var response = userService.getOwnedGeoCodes(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetOwnedGeoCodesRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getGeocodeIDs());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getOwnedGeoCodesTestNullId(){
        var request = new GetOwnedGeoCodesRequest();
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
            var request = new GetOwnedGeoCodesRequest();
            request.setUserID(invalidUserId);

            var response = userService.getOwnedGeoCodes(request);

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
            var request = new GetOwnedGeoCodesRequest();
            request.setUserID(validUserId);

            var response = userService.getOwnedGeoCodes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The IDs of the User's owned GeoCodes was successfully returned", response.getMessage());

            var ownedGeoCodeIDs = response.getGeocodeIDs();

            Assertions.assertNotNull(ownedGeoCodeIDs);
            Assertions.assertEquals(numberOfOwnedGeoCodesBefore, ownedGeoCodeIDs.size());
            Assertions.assertTrue(ownedGeoCodeIDs.contains(thirdGeoCodeID));
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void updateLocationTestNullRequest(){
        try{
            var response = userService.updateLocation(null);

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
    void updateLocationTestNullUser(){
        var request = new UpdateLocationRequest();
        request.setUserID(validUserId);

        assertThatThrownBy(() -> userService.updateLocation(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    void updateLocationTestInvalidUser() {
        try{
            /*
            Create a request object
            and assign values to it
            */
            var request = new UpdateLocationRequest();
            request.setUserID(invalidUserId);
            request.setLocation(new GeoPoint(10.0f, 10.0f));

            var response = userService.updateLocation(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
            Assertions.assertNull(response.getTrackable());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void updateLocationTestValidUser() {
        try{
            /*
             Create a request object
             and assign values to it
           */
            var request = new UpdateLocationRequest();
            request.setUserID(validUserId);

            var location = new GeoPoint(100.0f, 40.0f);
            request.setLocation(location);

            var response = userService.updateLocation(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The trackable object's location was successfully updated", response.getMessage());

            var trackableObject = response.getTrackable();
            Assertions.assertNotNull(trackableObject);

            var pastLocations = new ArrayList<>(trackableObject.getPastLocations());
            Assertions.assertEquals(location, pastLocations.get(pastLocations.size()-1));
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getMyLeaderboardsTestNullRequest(){
        try {
            var response = userService.getMyLeaderboards(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetMyLeaderboardsRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getLeaderboards());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getMyLeaderboardsTestNullUser(){
        var request = new GetMyLeaderboardsRequest();
        request.setUserID(null);

        assertThatThrownBy(() -> userService.getMyLeaderboards(request))
                    .isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    void getMyLeaderboardsTestInvalidUser(){
        var request = new GetMyLeaderboardsRequest();
        request.setUserID(invalidUserId);

        try {
            var response = userService.getMyLeaderboards(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
            Assertions.assertNull(response.getLeaderboards());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getMyLeaderboardsTestUserWithNoPoints(){
        var request = new GetMyLeaderboardsRequest();
        request.setUserID(validUserId);

        try {
            var response = userService.getMyLeaderboards(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The details for the User's Leaderboards were successfully returned", response.getMessage());
            Assertions.assertTrue(response.getLeaderboards().isEmpty());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getMyLeaderboardsTestUserWithPoints1(){
        var request = new GetMyLeaderboardsRequest();
        request.setUserID(userWithPoints1);

        try {
            var response = userService.getMyLeaderboards(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The details for the User's Leaderboards were successfully returned", response.getMessage());

            var leaderboardDetails = response.getLeaderboards();

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
        var request = new GetMyLeaderboardsRequest();
        request.setUserID(userWithPoints2);

        try {
            var response = userService.getMyLeaderboards(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The details for the User's Leaderboards were successfully returned", response.getMessage());

            var leaderboardDetails = response.getLeaderboards();

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

    @Test
    void AddToOwnedGeoCodesTestNullRequest(){
        try {
            var response = userService.addToOwnedGeoCodes(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The AddToOwnedGeoCodesRequest object passed was NULL", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void AddToOwnedGeoCodesTestNullParameter(){
        var request = new AddToOwnedGeoCodesRequest(null, null);

        assertThatThrownBy(() -> userService.addToOwnedGeoCodes(request)).isInstanceOf(NullRequestParameterException.class);
    }

    /**
     * The User class has ownedGeoCodes, foundGeoCodes, foundCollectableTypes as Sets
     * Therefore calling add() will never add a duplicate, but the data type may change to a type that
     * does not have this uniqueness feature. So not adding duplicates is still tested
     */
    @Test
    void AddToOwnedGeoCodesTestNotAddDuplicate(){
        try {
            var request = new AddToOwnedGeoCodesRequest(validUser, thirdGeoCode);
            var response = userService.addToOwnedGeoCodes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("GeoCode added to the owned GeoCodes", response.getMessage());
            Assertions.assertEquals(numberOfOwnedGeoCodesBefore, validUser.getOwnedGeocodes().size());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void AddToOwnedGeoCodesTestAddNew(){
        try {
            var request = new AddToOwnedGeoCodesRequest(validUser, firstGeoCode);
            var response = userService.addToOwnedGeoCodes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("GeoCode added to the owned GeoCodes", response.getMessage());

            Assertions.assertEquals(numberOfOwnedGeoCodesBefore+1, validUser.getOwnedGeocodes().size());
            Assertions.assertTrue(validUser.getOwnedGeocodes().contains(firstGeoCode));
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void AddToFoundGeoCodesTestNullRequest(){
        try {
            var response = userService.addToFoundGeoCodes(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The AddToFoundGeoCodesRequest object passed was NULL", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void AddToFoundGeoCodesTestNullParameter(){
        var request = new AddToFoundGeoCodesRequest(null, null);

        assertThatThrownBy(() -> userService.addToFoundGeoCodes(request)).isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    void AddToFoundGeoCodesTestNotAddDuplicate(){
        try {
            var request = new AddToFoundGeoCodesRequest(validUser, secondGeoCode);
            var response = userService.addToFoundGeoCodes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("GeoCode added to the found GeoCodes", response.getMessage());
            Assertions.assertEquals(numberOfOwnedGeoCodesBefore, validUser.getOwnedGeocodes().size());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void AddToFoundGeoCodesTestAddNew(){
        try {
            var request = new AddToFoundGeoCodesRequest(validUser, thirdGeoCode);
            var response = userService.addToFoundGeoCodes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("GeoCode added to the found GeoCodes", response.getMessage());

            Assertions.assertEquals(numberOfFoundGeoCodesBefore+1, validUser.getFoundGeocodes().size());
            Assertions.assertTrue(validUser.getFoundGeocodes().contains(this.thirdGeoCode));
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void AddToFoundCollectableTypesTestNullRequest(){
        try {
            var response = userService.addToFoundCollectableTypes(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The AddToFoundCollectableTypesRequest object passed was NULL", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void AddToFoundCollectableTypesTestNullParameter(){
        var request = new AddToFoundCollectableTypesRequest(null, null);

        assertThatThrownBy(() -> userService.addToFoundCollectableTypes(request)).isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    void AddToFoundCollectableTypesTestNotAddDuplicate(){
        try {
            var request = new AddToFoundCollectableTypesRequest(validUser, egg);
            var response = userService.addToFoundCollectableTypes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("CollectableType added to the found CollectableTypes", response.getMessage());

            Assertions.assertEquals(numberOfFoundCollectableTypesBefore, validUser.getFoundCollectableTypes().size());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void AddToFoundCollectableTypesTestAddNew(){
        try {
            var wandType = new CollectableType();
            var wandTypeID = UUID.fromString("abad9729-c82f-457c-a42f-418c564dac3f");
            wandType.setId(wandTypeID);

            var request = new AddToFoundCollectableTypesRequest(validUser, wandType);
            var response = userService.addToFoundCollectableTypes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("CollectableType added to the found CollectableTypes", response.getMessage());

            var foundCollectableTypes = validUser.getFoundCollectableTypes();
            
            Assertions.assertEquals(numberOfFoundCollectableTypesBefore+1, foundCollectableTypes.size());
            Assertions.assertTrue(foundCollectableTypes.stream().anyMatch(collectableType -> collectableType.getId().equals(wandType.getId())));
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getUserByIdTestNullRequest(){
        try {
            var response = userService.getUserById(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetUserByIdRequest object passed was NULL", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getUserByIdTestNullUserParameter(){
        var request = new GetUserByIdRequest(null);

        assertThatThrownBy(() -> userService.getUserById(request)).isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    void getUserByIdTestInvalidUserId(){
        try {
            var request = new GetUserByIdRequest(invalidUserId);
            var response = userService.getUserById(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getUserByIdTestValidUserId(){
        try {
            var request = new GetUserByIdRequest(validUserId);
            var response = userService.getUserById(request);
            var user = response.getUser();

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The User was found", response.getMessage());

            Assertions.assertEquals(validUserId, user.getId());
            Assertions.assertEquals("john_smith", user.getUsername());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void registerNewUserTestNullRequest(){
        try {
            var response = userService.registerNewUser(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The RegisterNewUserRequest object passed was NULL", response.getMessage());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void registerNewUserTestNullParameter(){
        var request = new RegisterNewUserRequest(null);

        assertThatThrownBy(() -> userService.registerNewUser(request)).isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    void registerNewUserTestExistingUserId(){
        try {
            var request = new RegisterNewUserRequest(new GeoPoint(0.0, 0.0));
            var response = userService.registerNewUser(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("User ID already exists", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void registerNewUserTestNewUserId(){
        try {
            String newUsername = "bob";
            var request = new RegisterNewUserRequest(new GeoPoint(0.0, 0.0));
            var response = userService.registerNewUser(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("New User registered", response.getMessage());

            var optionalUser = userMockRepo.findById(newUserId);

            if(optionalUser.isEmpty()){
                Assertions.fail("New User not saved");
            }

            var user = optionalUser.get();

            Assertions.assertEquals(trackableUUID, user.getTrackableObject().getType().getId());
            Assertions.assertEquals(trackableUUID, user.getCurrentCollectable().getType().getId());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void swapCollectableTestNullRequest(){
        try {
            var response = userService.swapCollectable(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The SwapCollectableRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getCollectable());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void swapCollectableTestNullParameter(){
        var request = new SwapCollectableRequest();
        request.setCollectable(null);

        assertThatThrownBy(() -> userService.swapCollectable(request)).isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    void swapCollectableTestCollectableIsSwapped(){
        try {
            var request = new SwapCollectableRequest(validUser, fishCollectable, geoCodeWithCollectables);
            var response = userService.swapCollectable(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The User's Collectable was swapped with the Collectable in the GeoCode", response.getMessage());

            var collectable = response.getCollectable();
            Assertions.assertNotNull(collectable);

            //test that the User's Collectable is now the fishCollectable
            Assertions.assertEquals(fishCollectableID, validUser.getCurrentCollectable().getId());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getMyMissionsTestNullRequest(){
        try {
           var response = userService.getMyMissions(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetMyMissionsRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getMissions());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getMyMissionsTestNullUser(){
        var request = new GetMyMissionsRequest(null);

        assertThatThrownBy(() -> userService.getMyMissions(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    void getMyMissionsTestInvalidUser(){
        var request = new GetMyMissionsRequest(invalidUserId);

        try {
            var response = userService.getMyMissions(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
            Assertions.assertNull(response.getMissions());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getMyMissionsTestValidUser(){
        var request = new GetMyMissionsRequest(validUserId);

        try {
            var response = userService.getMyMissions(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("User Missions returned", response.getMessage());

            var missions = response.getMissions();
            Assertions.assertNotNull(missions);

            Assertions.assertTrue(missions.stream().anyMatch(mission -> mission.getId().equals(swapMissionID) && mission.getType().equals(MissionType.SWAP)));
            Assertions.assertTrue(missions.stream().anyMatch(mission -> mission.getId().equals(circumferenceMissionID) && mission.getType().equals(MissionType.DISTANCE)));
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getCurrentUserTest() {
        mockCurrentUserDetails.setID(validUserId);

        var returnedUser = userService.getCurrentUser();
        Assertions.assertEquals(validUser, returnedUser);
    }
}
