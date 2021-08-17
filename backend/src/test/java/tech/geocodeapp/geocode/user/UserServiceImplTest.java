package tech.geocodeapp.geocode.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import tech.geocodeapp.geocode.collectable.*;
import tech.geocodeapp.geocode.collectable.model.*;
import tech.geocodeapp.geocode.collectable.service.*;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.GeoCodeMockRepository;
import tech.geocodeapp.geocode.geocode.exceptions.RepoException;
import tech.geocodeapp.geocode.geocode.model.*;
import tech.geocodeapp.geocode.geocode.service.GeoCodeService;
import tech.geocodeapp.geocode.geocode.service.GeoCodeServiceImpl;
import tech.geocodeapp.geocode.leaderboard.LeaderboardMockRepository;
import tech.geocodeapp.geocode.leaderboard.PointMockRepository;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.model.MyLeaderboardDetails;
import tech.geocodeapp.geocode.leaderboard.model.Point;
import tech.geocodeapp.geocode.mission.MissionMockRepository;
import tech.geocodeapp.geocode.mission.model.Mission;
import tech.geocodeapp.geocode.mission.model.MissionType;
import tech.geocodeapp.geocode.mission.service.MissionService;
import tech.geocodeapp.geocode.mission.service.MissionServiceImpl;
import tech.geocodeapp.geocode.user.model.User;
import tech.geocodeapp.geocode.user.service.*;
import tech.geocodeapp.geocode.user.request.*;
import tech.geocodeapp.geocode.user.response.*;

@ExtendWith( MockitoExtension.class )
public class UserServiceImplTest {
    private UserService userService;
    private UserMockRepository userMockRepo;
    private MissionService missionService;

    private User validUser;
    private User invalidUser;

    private GeoCode firstGeoCode;
    private GeoCode secondGeoCode;
    private GeoCode thirdGeoCode;

    private final UUID invalidUserId = UUID.fromString("31d72621-091c-49ad-9c28-8abda8b8f055");
    private final UUID validUserId = UUID.fromString("183e06b6-2130-45e3-8b43-634ccd3e8e6f");
    private final UUID newUserId = UUID.fromString("e03bd781-cca9-43bf-a168-0f0563fca591");

    private final UUID userWithPoints1 = UUID.fromString("f1f1cc86-47f0-4cdd-b313-e9275b9e8925");
    private final UUID userWithPoints2 = UUID.fromString("38437809-528e-464e-a81f-140ad9f50cda");
    private final UUID firstGeoCodeID = UUID.fromString("0998cf20-8256-4529-b144-d3c8aa4f0fb1");
    private final UUID secondGeoCodeID = UUID.fromString("8c3e3a65-118b-47ca-8cca-097134cd00d9");
    private final UUID thirdGeoCodeID = UUID.fromString("7b32fce8-44e4-422b-a80d-521d490e9ee3");

    private final UUID trackableUUID = new UUID(0, 0);

    private final UUID geoCodeWithCollectablesID = UUID.fromString("fd8b4bed-89c9-4f76-872d-9124ca5061f6");
    private final UUID fishCollectableID = UUID.fromString("cfb23fdb-7b9e-4f67-ad67-b2bab0e7541a");
    private final UUID ballCollectableID = UUID.fromString("49b544b6-307c-4905-bc5e-f61c2afdd56b");

    private final UUID invalidGeoCodeID = UUID.fromString("c6dab51d-7b2c-45df-940c-189821a36178");
    private final UUID invalidCollectableID = UUID.fromString("4d2877ee-431e-4a46-b391-c9755291a0f6");

    private final UUID fishCollectableTypeID = UUID.fromString("91216b44-b123-486c-8ba7-1c2da7d0feef");
    private final UUID ballCollectableTypeID = UUID.fromString("f85ebdce-a569-4d47-9274-d4b0245c4713");
    
    private final UUID eggCollectableTypeID = UUID.fromString("650e77b0-ccf4-43ab-9279-864d9c659010");
    private final UUID chocolateBarCollectableTypeID = UUID.fromString("8f9b8919-2c02-4458-9d80-80b06710eb08");
    private final UUID bunnyCollectableTypeID = UUID.fromString("0998cf20-8256-4529-b144-d3c8aa4f0fb1");

    private final UUID swapMissionID = UUID.fromString("1f1eac14-20ef-4f13-9b0d-7101fea996f5");
    private final UUID circumferenceMissionID = UUID.fromString("88705bf1-3f5e-42a8-ae91-f29bbe5c9dd4");

    private final String invalidUserIdMessage = "Invalid User id";
    private final String existingUserIdMessage = "User ID already exists";

    private final String hatfieldEaster = "Hatfield Easter Hunt 2021";
    private final String menloParkChristmas = "Christmas 2021 market";

    private final int user1EasterPoints = 5;
    private final int user2EasterPoints = 5;
    private final int user1ChristmasPoints = 10;
    private final int user2ChristmasPoints = 5;

    private int numberOfOwnedGeoCodesBefore;
    private int numberOfFoundGeoCodesBefore;
    private int numberOfFoundCollectableTypesBefore;

    private CollectableType fishType;

    private GeoCode geoCodeWithCollectables;
    private Collectable fishCollectable;

    private GeoCode invalidGeoCode;
    private CollectableType egg;

    UserServiceImplTest() {

    }

    @BeforeEach
    void setup() {
        CollectableTypeMockRepository collectableTypeMockRepo = new CollectableTypeMockRepository();
        CollectableMockRepository collectableMockRepo = new CollectableMockRepository();
        CollectableSetMockRepository collectableSetMockRepo = new CollectableSetMockRepository();

        LeaderboardMockRepository leaderboardMockRepo = new LeaderboardMockRepository();
        PointMockRepository pointMockRepo = new PointMockRepository();

        GeoCodeMockRepository geoCodeMockRepo = new GeoCodeMockRepository();

        userMockRepo = new UserMockRepository();
        CollectableService collectableService = new CollectableServiceImpl(collectableMockRepo, collectableSetMockRepo, collectableTypeMockRepo, missionService);
        GeoCodeService geoCodeService;

        try {
            geoCodeService = new GeoCodeServiceImpl(geoCodeMockRepo, collectableService, userService, null);
        } catch (RepoException e) {
            e.printStackTrace();
            return;
        }

        MissionMockRepository missionMockRepo = new MissionMockRepository();
        missionService = new MissionServiceImpl(missionMockRepo, collectableService);

        userService = new UserServiceImpl(userMockRepo, collectableMockRepo, new PointMockRepository(), collectableService, missionService);
        userService.setGeoCodeService(geoCodeService);

        //save the valid trackable CollectableType
        CollectableType trackableCollectableType = new CollectableType();
        trackableCollectableType.setId(trackableUUID);
        trackableCollectableType.setRarity(Rarity.COMMON);

        HashMap<String, String> properties = new HashMap<>();
        properties.put("missionType", String.valueOf(MissionType.RANDOM));

        trackableCollectableType.setProperties(properties);

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
        egg = new CollectableType();
        egg.setId(eggCollectableTypeID);
        collectableTypeMockRepo.save(egg);

        CollectableType chocolateBar = new CollectableType();
        chocolateBar.setId(chocolateBarCollectableTypeID);
        collectableTypeMockRepo.save(chocolateBar);

        CollectableType bunny = new CollectableType();
        bunny.setId(bunnyCollectableTypeID);
        collectableTypeMockRepo.save(bunny);

        //invalid User to test if function checks for invalid Users when it should
        invalidUser = new User(invalidUserId);

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
        Mission swapMission = new Mission();
        swapMission.setId(swapMissionID);
        swapMission.setType(MissionType.SWAP);

        Mission circumferenceMission = new Mission();
        circumferenceMission.setId(circumferenceMissionID);
        circumferenceMission.setType(MissionType.DISTANCE);

        validUser.addMissionsItem(swapMission);
        validUser.addMissionsItem(circumferenceMission);

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

        /* create a GeoCode with Collectables for the User to swap out */
        geoCodeWithCollectables = new GeoCode();
        geoCodeWithCollectables.setId(geoCodeWithCollectablesID);

        //save the Collectables
        CollectableSet collectableSet = new CollectableSet("Test Set", "CollectableSet for testing");
        collectableSetMockRepo.save(collectableSet);

        fishType = new CollectableType("fish", "fish_image", Rarity.COMMON, collectableSet, new HashMap<>());
        fishType.setId(fishCollectableTypeID);
        collectableTypeMockRepo.save(fishType);

        fishCollectable = new Collectable(fishType);
        fishCollectable.setId(fishCollectableID);
        collectableMockRepo.save(fishCollectable);

        CollectableType ballType = new CollectableType("ball", "ball_image", Rarity.UNCOMMON, collectableSet, new HashMap<>());
        ballType.setId(ballCollectableTypeID);
        collectableTypeMockRepo.save(ballType);

        Collectable ballCollectable = new Collectable();
        ballCollectable.setId(ballCollectableID);
        collectableMockRepo.save(ballCollectable);

        //add the Collectables to the GeoCode
        geoCodeWithCollectables.addCollectablesItem(fishCollectableID);
        geoCodeWithCollectables.addCollectablesItem(ballCollectableID);

        geoCodeMockRepo.save(geoCodeWithCollectables);

        invalidGeoCode = new GeoCode();
        invalidGeoCode.setId(invalidGeoCodeID);
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
        GetFoundCollectableTypesRequest request = new GetFoundCollectableTypesRequest(null);

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
            GetFoundCollectableTypesRequest request = new GetFoundCollectableTypesRequest(validUserId);

            GetFoundCollectableTypesResponse response = userService.getFoundCollectableTypes(request);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The IDs of the User's found CollectableTypes was successfully returned", response.getMessage());

            List<UUID> foundCollectableTypeIDs = response.getCollectableTypeIDs();
            Assertions.assertNotNull(foundCollectableTypeIDs);
            Assertions.assertEquals(numberOfFoundCollectableTypesBefore, foundCollectableTypeIDs.size());
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
        GetFoundGeoCodesRequest request = new GetFoundGeoCodesRequest(null);

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
            Assertions.assertEquals(numberOfOwnedGeoCodesBefore, ownedGeoCodeIDs.size());
            Assertions.assertTrue(ownedGeoCodeIDs.contains(thirdGeoCodeID));
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void updateLocationTestNullRequest(){
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
    void updateLocationTestNullUser(){
        UpdateLocationRequest request = new UpdateLocationRequest();
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
    void updateLocationTestValidUser() {
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

    @Test
    void AddToOwnedGeoCodesTestNullRequest(){
        try {
            AddToOwnedGeoCodesResponse response = userService.addToOwnedGeoCodes(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The AddToOwnedGeoCodesRequest object passed was NULL", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void AddToOwnedGeoCodesTestNullParameter(){
        AddToOwnedGeoCodesRequest request = new AddToOwnedGeoCodesRequest(null, null);

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
            AddToOwnedGeoCodesRequest request = new AddToOwnedGeoCodesRequest(validUser, thirdGeoCode);
            AddToOwnedGeoCodesResponse response = userService.addToOwnedGeoCodes(request);

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
            AddToOwnedGeoCodesRequest request = new AddToOwnedGeoCodesRequest(validUser, firstGeoCode);
            AddToOwnedGeoCodesResponse response = userService.addToOwnedGeoCodes(request);

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
            AddToFoundGeoCodesResponse response = userService.addToFoundGeoCodes(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The AddToFoundGeoCodesRequest object passed was NULL", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void AddToFoundGeoCodesTestNullParameter(){
        AddToFoundGeoCodesRequest request = new AddToFoundGeoCodesRequest(null, null);

        assertThatThrownBy(() -> userService.addToFoundGeoCodes(request)).isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    void AddToFoundGeoCodesTestNotAddDuplicate(){
        try {
            AddToFoundGeoCodesRequest request = new AddToFoundGeoCodesRequest(validUser, secondGeoCode);
            AddToFoundGeoCodesResponse response = userService.addToFoundGeoCodes(request);

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
            AddToFoundGeoCodesRequest request = new AddToFoundGeoCodesRequest(validUser, thirdGeoCode);
            AddToFoundGeoCodesResponse response = userService.addToFoundGeoCodes(request);

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
            AddToFoundCollectableTypesResponse response = userService.addToFoundCollectableTypes(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The AddToFoundCollectableTypesRequest object passed was NULL", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void AddToFoundCollectableTypesTestNullParameter(){
        AddToFoundCollectableTypesRequest request = new AddToFoundCollectableTypesRequest(null, null);

        assertThatThrownBy(() -> userService.addToFoundCollectableTypes(request)).isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    void AddToFoundCollectableTypesTestNotAddDuplicate(){
        try {
            AddToFoundCollectableTypesRequest request = new AddToFoundCollectableTypesRequest(validUser, egg);
            AddToFoundCollectableTypesResponse response = userService.addToFoundCollectableTypes(request);

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
            AddToFoundCollectableTypesRequest request = new AddToFoundCollectableTypesRequest(validUser, fishType);
            AddToFoundCollectableTypesResponse response = userService.addToFoundCollectableTypes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("CollectableType added to the found CollectableTypes", response.getMessage());

            Assertions.assertEquals(numberOfFoundCollectableTypesBefore+1, validUser.getFoundCollectableTypes().size());
            Assertions.assertTrue(validUser.getFoundCollectableTypes().contains(fishType));
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getUserByIdTestNullRequest(){
        try {
            GetUserByIdResponse response = userService.getUserById(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetUserByIdRequest object passed was NULL", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getUserByIdTestNullUserParameter(){
        GetUserByIdRequest request = new GetUserByIdRequest(null);

        assertThatThrownBy(() -> userService.getUserById(request)).isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    void getUserByIdTestInvalidUserId(){
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
    void getUserByIdTestValidUserId(){
        try {
            GetUserByIdRequest request = new GetUserByIdRequest(validUserId);
            GetUserByIdResponse response = userService.getUserById(request);
            User user = response.getUser();

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
            RegisterNewUserResponse response = userService.registerNewUser(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The RegisterNewUserRequest object passed was NULL", response.getMessage());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void registerNewUserTestNullUserParameter(){
        RegisterNewUserRequest request = new RegisterNewUserRequest(null, "alice");

        assertThatThrownBy(() -> userService.registerNewUser(request)).isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    void registerNewUserTestExistingUserId(){
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
    void registerNewUserTestNewUserId(){
        try {
            String newUsername = "bob";
            RegisterNewUserRequest request = new RegisterNewUserRequest(newUserId, newUsername);
            RegisterNewUserResponse response = userService.registerNewUser(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("New User registered", response.getMessage());

            Optional<User> optionalUser = userMockRepo.findById(newUserId);

            if(optionalUser.isEmpty()){
                Assertions.fail("New User not saved");
            }

            User user = optionalUser.get();

            Assertions.assertEquals(trackableUUID, user.getTrackableObject().getType().getId());
            Assertions.assertEquals(trackableUUID, user.getCurrentCollectable().getType().getId());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    void swapCollectableTestNullRequest(){
        try {
            SwapCollectableResponse response = userService.swapCollectable(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The SwapCollectableRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getCollectable());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void swapCollectableTestNullParameter(){
        SwapCollectableRequest request = new SwapCollectableRequest();
        request.setCollectable(null);

        assertThatThrownBy(() -> userService.swapCollectable(request)).isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    void swapCollectableTestCollectableIsSwapped(){
        try {
            //System.out.println("fishCollectableID: "+fishCollectableID);

            SwapCollectableRequest request = new SwapCollectableRequest(validUser, fishCollectable, geoCodeWithCollectables);
            SwapCollectableResponse response = userService.swapCollectable(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The User's Collectable was swapped with the Collectable in the GeoCode", response.getMessage());

            Collectable collectable = response.getCollectable();
            Assertions.assertNotNull(collectable);

            //test that the User's Collectable is now the fishCollectable
            Assertions.assertEquals(fishCollectableID, validUser.getCurrentCollectable().getId());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getMyMissionsTestNullRequest(){
        try {
            GetMyMissionsResponse response = userService.getMyMissions(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetMyMissionsRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getMissions());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void getMyMissionsTestNullUser(){
        GetMyMissionsRequest request = new GetMyMissionsRequest(null);

        assertThatThrownBy(() -> userService.getMyMissions(request))
                .isInstanceOf(NullRequestParameterException.class);
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
