package tech.geocodeapp.geocode.user;

import java.util.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.transaction.annotation.Transactional;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.collectable.model.CollectableType;
import tech.geocodeapp.geocode.collectable.model.Rarity;
import tech.geocodeapp.geocode.collectable.request.*;
import tech.geocodeapp.geocode.collectable.response.CreateCollectableResponse;
import tech.geocodeapp.geocode.collectable.response.CreateCollectableSetResponse;
import tech.geocodeapp.geocode.collectable.response.CreateCollectableTypeResponse;
import tech.geocodeapp.geocode.collectable.response.GetCollectableByIDResponse;
import tech.geocodeapp.geocode.collectable.service.CollectableServiceImpl;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.geocode.model.Difficulty;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.geocode.request.CreateGeoCodeRequest;
import tech.geocodeapp.geocode.geocode.request.GetCollectablesRequest;
import tech.geocodeapp.geocode.geocode.request.SwapCollectablesRequest;
import tech.geocodeapp.geocode.geocode.service.GeoCodeServiceImpl;
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

    @Autowired
    CollectableServiceImpl collectableService;

    @Autowired
    GeoCodeServiceImpl geoCodeService;

    private final UUID invalidUserId = UUID.fromString("31d72621-091c-49ad-9c28-8abda8b8f055");
    private final UUID validUserId = UUID.fromString("f479228d-8a4a-4b90-ba86-abccadec5085");
    private final UUID newUserId = UUID.fromString("f0fa8ba1-081d-4896-b23e-430f8f064a9a");

    private final UUID noPointsUserId = UUID.fromString("bed08b80-08ce-46c8-9948-af43b5d989d8");
    private UUID userWithPoints1 = UUID.fromString("70c4512e-2969-42a9-a03a-0d8480079ddf");
    private UUID userWithPoints2 = UUID.fromString("458198a1-a26f-4ff0-8710-b340d1f77300");

    private User validUser;
    private User invalidUser;
    private User noPointsUser;

    private final String invalidUserIdMessage = "Invalid User id";
    private final String invalidGeoCodeIdMessage = "Invalid GeoCode id";
    private final String invalidCollectableTypeIDMessage = "Invalid CollectableType ID";

    private UUID firstGeoCodeID;
    private UUID secondGeoCodeID;
    private UUID thirdGeoCodeID;

    private UUID trackableTypeUUID = new UUID(0, 0);

    private final String firstEvent = "Port Elizabeth Beach Hop - Default";
    private final String secondEvent = "Port Elizabeth High School Open Days - Default";

    private final int user1FirstEventPoints = 5;
    private final int user2FirstEventPoints = 5;
    private final int user1SecondEventPoints = 10;
    private final int user2SecondEventPoints = 5;

    private int numberOfOwnedGeoCodesBefore;
    private int numberOfFoundGeoCodesBefore;
    private int numberOfFoundCollectableTypesBefore;

    private final UUID invalidGeoCodeID = UUID.fromString("0c4f80cf-e643-46ba-84c6-2578873d9fd8");
    private final UUID invalidCollectableID = UUID.fromString("80c582d7-b6cf-4d8f-9006-12876b7d219d");
    private final UUID invalidCollectableTypeID = UUID.fromString("dfe3f784-ab11-4a2e-91eb-e74b96df6bb9");

    private final UUID noPointsFirstOwnedGeoCodeID = UUID.fromString("9225d9be-70ce-460b-aaea-5408bb2262fd");
    private final UUID noPointsNewOwnedGeoCodeID = UUID.fromString("533f5896-19e4-474c-b433-78902033d475");

    private final UUID noPointsFirstFoundGeoCodeID = UUID.fromString("b7f0d71a-c13f-43cc-ac8b-ef7ed6a3c394");
    private final UUID noPointsNewFoundGeoCodeID = UUID.fromString("6df8adef-a5cc-4586-b8c4-0e9201fa6150");

    private final UUID testCollectableTypeID = UUID.fromString("11a9e458-dd30-48b3-9839-02614191994e");
    private final UUID testCollectableType1ID = UUID.fromString("180fbfa0-10c1-4ee8-9599-becd9e5805d2");
    private final UUID testCollectableType2ID = UUID.fromString("5b6f838a-0383-46aa-9a41-25df370a2353");
    private final UUID noPointsNewFoundCollectableTypeID = UUID.fromString("bd5d2476-7de7-4307-9081-2e17d5d50cca");

    private final UUID collectableInFirstGeoCodeID = UUID.fromString("af42eecd-f4c8-4ba7-909d-c68c69f79421");

    private final UUID swapMissionID = UUID.fromString("c665e67f-8fef-4568-8607-d4bbd4f72543");
    private final UUID circumferenceMissionID = UUID.fromString("e1927f0f-825e-4040-9fbb-fa22197ab0f5");

    private final String existingUserIdMessage = "User ID already exists";

    private UUID trackableSetId;

    private UUID collectableID1;
    private UUID collectableID2;
    private UUID collectableTypeID1;
    private UUID collectableTypeID2;

    User registerNewUser(UUID userID, String username){
        RegisterNewUserRequest request = new RegisterNewUserRequest(userID, username);
        RegisterNewUserResponse response;

        try {
            response = userService.registerNewUser(request);

            if(!response.isSuccess()){
                System.out.println("user registration failed:"+response.getMessage());
            }

            Assertions.assertTrue(response.isSuccess());

            return response.getUser();
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    UUID createCollectableSet(String name, String description){
        CreateCollectableSetRequest createCollectableSetRequest = new CreateCollectableSetRequest(name, description);

        try {
            CreateCollectableSetResponse createCollectableSetResponse = collectableService.createCollectableSet(createCollectableSetRequest);
            return createCollectableSetResponse.getCollectableSet().getId();
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    UUID createCollectableType(String name, String image, Rarity rarity, UUID setId, HashMap<String, String> properties) {
        CreateCollectableTypeRequest createCollectableTypeRequest = new CreateCollectableTypeRequest(name, image, rarity, setId, properties);

        try {
            CreateCollectableTypeResponse createCollectableTypeResponse = collectableService.createCollectableType(createCollectableTypeRequest);
            return createCollectableTypeResponse.getCollectableType().getId();
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    Collectable createCollectable(UUID typeID, boolean createMission, GeoPoint location){
        CreateCollectableRequest createCollectableRequest = new CreateCollectableRequest();
        createCollectableRequest.setCollectableTypeId(typeID);
        createCollectableRequest.setCreateMission(createMission);
        createCollectableRequest.setLocation(location);

        CreateCollectableResponse createCollectableResponse = null;

        try {
            createCollectableResponse = collectableService.createCollectable(createCollectableRequest);
            Assertions.assertTrue(createCollectableResponse.isSuccess());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
        }

        UUID id = createCollectableResponse.getCollectable().getId();

        try {
            return collectableService.getCollectableByID(new GetCollectableByIDRequest(id)).getCollectable();
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    @BeforeEach
    @Transactional
    void setup() throws InvalidRequestException, NullRequestParameterException {
        //mock the SecurityContext
        setUser(validUserId);

        //create the CollectableSet to hold the "User Trackable" type
        trackableSetId = createCollectableSet("User Trackable", "Contains the standard User Trackable");

        //create the Trackable CollectableType
        HashMap<String, String> trackableProperties = new HashMap<>();
        trackableProperties.put("missionType", String.valueOf(MissionType.RANDOM));
        UUID tempID = createCollectableType("User Trackable", "img_trackable", Rarity.COMMON, trackableSetId, trackableProperties);
        System.out.println("tempID:"+tempID);

        //create invalid User object
        invalidUser = new User(invalidUserId);

        //register the valid Users
        validUser = registerNewUser(validUserId, "validUser");
        noPointsUser = registerNewUser(noPointsUserId, "noPointsUserId");
        userWithPoints1 = registerNewUser(userWithPoints1, "userWithPoints1").getId();
        userWithPoints2 = registerNewUser(userWithPoints2, "userWithPoints2").getId();

        //create GeoCodes
        List<String> hints = new ArrayList<>();
        hints.add("Behind the climbing wall");

        firstGeoCodeID = geoCodeService.createGeoCode(new CreateGeoCodeRequest("firstGeoCode", new GeoPoint(10.0, 10.0), hints, Difficulty.EASY, true)).getGeoCodeID();
        secondGeoCodeID = geoCodeService.createGeoCode(new CreateGeoCodeRequest("secondGeoCode", new GeoPoint(10.0, 10.0), hints, Difficulty.MEDIUM, true)).getGeoCodeID();
        thirdGeoCodeID = geoCodeService.createGeoCode(new CreateGeoCodeRequest("thirdGeoCode", new GeoPoint(10.0, 10.0), hints, Difficulty.HARD, true)).getGeoCodeID();

        var firstCollectables = getCollectables(firstGeoCodeID);
        var secondCollectables = getCollectables(secondGeoCodeID);
        var thirdCollectables = getCollectables(thirdGeoCodeID);

        //update missions for the User by swapping Collectables out of GeoCodes
        //apply to the noPointsUser
        setUser(validUserId);

        collectableID1 = firstCollectables.get(0);
        collectableTypeID1 = getCollectableTypeID(collectableID1);
        swapCollectables(firstGeoCodeID, collectableID1);
        
        collectableID2 = secondCollectables.get(3);
        collectableTypeID2 = getCollectableTypeID(collectableID2);
        swapCollectables(secondGeoCodeID, collectableID2);

        //here
        System.out.println("firstGeoCodeID:"+firstGeoCodeID);
        System.out.println("secondGeoCodeID:"+secondGeoCodeID);

        //give userWithPoints1 and userWithPoints2 points

    }

    private UUID getCollectableTypeID(UUID collectableID) throws NullRequestParameterException {
        GetCollectableByIDRequest getCollectableByIDRequest = new GetCollectableByIDRequest(collectableID);
        GetCollectableByIDResponse getCollectableByIDResponse = collectableService.getCollectableByID(getCollectableByIDRequest);

        return getCollectableByIDResponse.getCollectable().getType().getId();
    }

    private void swapCollectables(UUID geoCodeID, UUID collectableID) throws InvalidRequestException {
        geoCodeService.swapCollectables(new SwapCollectablesRequest(geoCodeID, collectableID));
    }

    private List<UUID> getCollectables(UUID geoCodeID) throws InvalidRequestException {
        return geoCodeService.getCollectables(new GetCollectablesRequest(geoCodeID)).getCollectables();
    }

    private void setUser(UUID userID){
        MockSecurity.setup();
        MockSecurity.setCurrentUserID(userID);
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
            setUser(validUserId);

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
            setUser(validUserId);

            GetUserTrackableRequest request = new GetUserTrackableRequest();
            request.setUserID(validUserId);

            GetUserTrackableResponse response = userService.getUserTrackable(request);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The user's Trackable was successfully returned", response.getMessage());

            Collectable trackableObject = response.getTrackable();
            Assertions.assertNotNull(trackableObject);
            Assertions.assertEquals(trackableTypeUUID, trackableObject.getType().getId());
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
    @Transactional
    void getFoundCollectableTypesTestValidUser() {
        try{
            /*
             Create a request object
             and assign values to it
           */
            setUser(noPointsUserId);

            GetFoundCollectableTypesRequest request = new GetFoundCollectableTypesRequest(noPointsUserId);

            GetFoundCollectableTypesResponse response = userService.getFoundCollectableTypes(request);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The IDs of the User's found CollectableTypes was successfully returned", response.getMessage());

            List<UUID> foundCollectableTypeIDs = response.getCollectableTypeIDs();
            Assertions.assertNotNull(foundCollectableTypeIDs);

            Assertions.assertTrue(foundCollectableTypeIDs.contains(collectableTypeID1));
            Assertions.assertTrue(foundCollectableTypeIDs.contains(collectableTypeID2));
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Transactional
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
    @Transactional
    void getFoundGeoCodesTestValidUser() {
        try{
            /*
             Create a request object
             and assign values to it
           */
            setUser(validUserId);

            GetFoundGeoCodesRequest request = new GetFoundGeoCodesRequest(validUserId);

            GetFoundGeoCodesResponse response = userService.getFoundGeoCodes(request);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The IDs of the User's found GeoCodes was successfully returned", response.getMessage());

            List<UUID> foundGeoCodeIDs = response.getGeocodeIDs();
            Assertions.assertNotNull(foundGeoCodeIDs);

//            for(var id: foundGeoCodeIDs){
//                System.out.println("id = "+id);
//            }

            //HashSet will cause order to not necessarily be order added in
            Assertions.assertTrue(foundGeoCodeIDs.contains(firstGeoCodeID));
            Assertions.assertTrue(foundGeoCodeIDs.contains(secondGeoCodeID));
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Transactional
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
    @Transactional
    void getOwnedGeoCodesTestValidUser() {
        try{
            /*
             Create a request object
             and assign values to it
           */
            setUser(validUserId);

            GetOwnedGeoCodesRequest request = new GetOwnedGeoCodesRequest();
            request.setUserID(validUserId);

            GetOwnedGeoCodesResponse response = userService.getOwnedGeoCodes(request);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The IDs of the User's owned GeoCodes was successfully returned", response.getMessage());

            List<UUID> ownedGeoCodeIDs = response.getGeocodeIDs();
            Assertions.assertNotNull(ownedGeoCodeIDs);
            Assertions.assertEquals(2, ownedGeoCodeIDs.size());
            Assertions.assertEquals(firstGeoCodeID, ownedGeoCodeIDs.get(0));
            Assertions.assertEquals(secondGeoCodeID, ownedGeoCodeIDs.get(1));
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Transactional
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
    @Transactional
    public void updateLocationTestValidUser() {
        try{
            /*
             Create a request object
             and assign values to it
           */
            setUser(validUserId);

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
    @Transactional
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
    @Transactional
    void getMyLeaderboardsTestUserWithNoPoints(){
        setUser(noPointsUserId);

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
    @Transactional
    void getMyLeaderboardsTestUserWithPoints1(){
        setUser(userWithPoints1);

        GetMyLeaderboardsRequest request = new GetMyLeaderboardsRequest();
        request.setUserID(userWithPoints1);

        try {
            GetMyLeaderboardsResponse response = userService.getMyLeaderboards(request);

            Assertions.assertEquals("The details for the User's Leaderboards were successfully returned", response.getMessage());
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
    @Transactional
    void getMyLeaderboardsTestUserWithPoints2(){
        setUser(userWithPoints2);

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
    @Transactional
    public void AddToOwnedGeoCodesTestNotAddDuplicate(){
        try {
            setUser(noPointsUserId);

            GeoCode noPointsFirstOwnedGeoCode = new GeoCode();
            noPointsFirstOwnedGeoCode.setId(noPointsFirstOwnedGeoCodeID);

            AddToOwnedGeoCodesRequest request = new AddToOwnedGeoCodesRequest(noPointsUser, noPointsFirstOwnedGeoCode);
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
        setUser(noPointsUserId);

        try {
            //check number of owned GeoCodes before adding
            GetOwnedGeoCodesRequest getOwnedGeoCodesRequest = new GetOwnedGeoCodesRequest(noPointsUserId);
            GetOwnedGeoCodesResponse getOwnedGeoCodesResponse = userService.getOwnedGeoCodes(getOwnedGeoCodesRequest);

            Assertions.assertTrue(getOwnedGeoCodesResponse.isSuccess());

            List<UUID> ownedGeoCodeIDs = getOwnedGeoCodesResponse.getGeocodeIDs();
            Assertions.assertEquals(2, ownedGeoCodeIDs.size());

            //add a new owned GeoCode
            GeoCode noPointsNewOwnedGeoCode = new GeoCode();
            noPointsNewOwnedGeoCode.setId(noPointsNewOwnedGeoCodeID);

            AddToOwnedGeoCodesRequest request = new AddToOwnedGeoCodesRequest(noPointsUser, noPointsNewOwnedGeoCode);
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
    @Transactional
    public void AddToFoundGeoCodesTestNotAddDuplicate(){
        setUser(noPointsUserId);

        try {
            GeoCode noPointsFirstFoundGeoCode = new GeoCode();
            noPointsFirstFoundGeoCode.setId(noPointsFirstFoundGeoCodeID);

            AddToFoundGeoCodesRequest request = new AddToFoundGeoCodesRequest(noPointsUser, noPointsFirstFoundGeoCode);
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
        setUser(noPointsUserId);

        try {
            //check number of found GeoCodes before adding
            GetFoundGeoCodesRequest getFoundGeoCodesRequest = new GetFoundGeoCodesRequest(noPointsUserId);
            GetFoundGeoCodesResponse getFoundGeoCodesResponse = userService.getFoundGeoCodes(getFoundGeoCodesRequest);

            Assertions.assertTrue(getFoundGeoCodesResponse.isSuccess());

            List<UUID> foundGeoCodeIDs = getFoundGeoCodesResponse.getGeocodeIDs();
            Assertions.assertEquals(2, foundGeoCodeIDs.size());

            //add a new found GeoCode
            GeoCode noPointsNewFoundGeoCode = new GeoCode();
            noPointsNewFoundGeoCode.setId(noPointsFirstFoundGeoCodeID);

            AddToFoundGeoCodesRequest request = new AddToFoundGeoCodesRequest(noPointsUser, noPointsNewFoundGeoCode);
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
    @Transactional
    public void AddToFoundCollectableTypesTestNotAddDuplicate(){
        setUser(noPointsUserId);

        try {
            CollectableType testCollectableType1 = new CollectableType();
            testCollectableType1.setId(testCollectableType1ID);

            AddToFoundCollectableTypesRequest request = new AddToFoundCollectableTypesRequest(noPointsUser, testCollectableType1);
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
        setUser(noPointsUserId);

        try{
            //check number of found CollectableTypes before adding
            GetFoundCollectableTypesRequest getFoundCollectableTypesRequest = new GetFoundCollectableTypesRequest(noPointsUserId);
            GetFoundCollectableTypesResponse getFoundCollectableTypesResponse = userService.getFoundCollectableTypes(getFoundCollectableTypesRequest);

            Assertions.assertTrue(getFoundCollectableTypesResponse.isSuccess());

            List<UUID> foundCollectableTypeIDs = getFoundCollectableTypesResponse.getCollectableTypeIDs();
            Assertions.assertEquals(2, foundCollectableTypeIDs.size());

            //add a new found CollectableType
            CollectableType noPointsNewFoundCollectableType = new CollectableType();
            noPointsNewFoundCollectableType.setId(noPointsNewFoundCollectableTypeID);

            AddToFoundCollectableTypesRequest request = new AddToFoundCollectableTypesRequest(noPointsUser, noPointsNewFoundCollectableType);
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
    @Transactional
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
    @Transactional
    public void getUserByIdTestValidUserId(){
        setUser(validUserId);

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
    @Transactional
    public void registerNewUserTestExistingUserId(){
        setUser(validUserId);

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

            Assertions.assertEquals(trackableTypeUUID, user.getTrackableObject().getType().getId());
            Assertions.assertEquals(trackableTypeUUID, user.getCurrentCollectable().getType().getId());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void swapCollectableTestCollectableInvalidUserID(){
        try {
            Collectable collectableInFirstGeoCode = new Collectable();
            collectableInFirstGeoCode.setId(collectableInFirstGeoCodeID);

            GeoCode firstGeoCode = new GeoCode();
            firstGeoCode.setId(firstGeoCodeID);

            SwapCollectableRequest request = new SwapCollectableRequest(invalidUser, collectableInFirstGeoCode, firstGeoCode);
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
    @Transactional
    public void swapCollectableTestCollectableInvalidGeoCodeID(){
        setUser(validUserId);

        try {
            Collectable collectableInFirstGeoCode = new Collectable();
            collectableInFirstGeoCode.setId(collectableInFirstGeoCodeID);

            GeoCode invalidGeoCode = new GeoCode();
            invalidGeoCode.setId(invalidGeoCodeID);

            SwapCollectableRequest request = new SwapCollectableRequest(validUser, collectableInFirstGeoCode, invalidGeoCode);
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
    @Transactional
    public void swapCollectableTestCollectableInvalidCollectableID(){
        setUser(validUserId);

        try {
            Collectable invalidCollectable = new Collectable();
            invalidCollectable.setId(invalidCollectableID);

            GeoCode firstGeoCode = new GeoCode();
            firstGeoCode.setId(firstGeoCodeID);

            SwapCollectableRequest request = new SwapCollectableRequest(validUser, invalidCollectable, firstGeoCode);
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
        setUser(validUserId);

        try {
            Collectable collectableInFirstGeoCode = new Collectable();
            collectableInFirstGeoCode.setId(collectableInFirstGeoCodeID);

            GeoCode firstGeoCode = new GeoCode();
            firstGeoCode.setId(firstGeoCodeID);

            SwapCollectableRequest request = new SwapCollectableRequest(validUser, collectableInFirstGeoCode, firstGeoCode);
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
    @Transactional
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
        setUser(validUserId);

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
