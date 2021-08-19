package tech.geocodeapp.geocode.user;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.collectable.model.CollectableType;
import tech.geocodeapp.geocode.collectable.model.Rarity;
import tech.geocodeapp.geocode.collectable.request.*;
import tech.geocodeapp.geocode.collectable.response.*;
import tech.geocodeapp.geocode.collectable.service.CollectableServiceImpl;
import tech.geocodeapp.geocode.event.model.OrderLevels;
import tech.geocodeapp.geocode.event.request.CreateEventRequest;
import tech.geocodeapp.geocode.event.service.EventServiceImpl;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.geocode.model.Difficulty;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.geocode.request.CreateGeoCodeRequest;
import tech.geocodeapp.geocode.geocode.request.GetCollectablesRequest;
import tech.geocodeapp.geocode.geocode.request.GetGeoCodeRequest;
import tech.geocodeapp.geocode.geocode.request.SwapCollectablesRequest;
import tech.geocodeapp.geocode.geocode.response.CreateGeoCodeResponse;
import tech.geocodeapp.geocode.geocode.service.GeoCodeServiceImpl;
import tech.geocodeapp.geocode.leaderboard.model.MyLeaderboardDetails;
import tech.geocodeapp.geocode.mission.model.Mission;
import tech.geocodeapp.geocode.mission.model.MissionType;
import tech.geocodeapp.geocode.user.model.User;
import tech.geocodeapp.geocode.user.request.*;
import tech.geocodeapp.geocode.user.response.*;
import tech.geocodeapp.geocode.user.service.UserService;

import java.time.LocalDate;
import java.util.*;

@SpringBootTest
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class UserServiceImplIT {
    @Autowired
    private UserService userService;

    @Autowired
    CollectableServiceImpl collectableService;

    @Autowired
    GeoCodeServiceImpl geoCodeService;

    @Autowired
    EventServiceImpl eventService;

    private final UUID invalidUserId = UUID.randomUUID();
    private final UUID validUserId = UUID.randomUUID();
    private final UUID newUserId = UUID.randomUUID();
    private final UUID noPointsUserId = UUID.randomUUID();
    private final UUID userWithPoints1ID = UUID.randomUUID();
    private final UUID userWithPoints2ID = UUID.randomUUID();

    private User validUser;
    private User invalidUser;
    private User noPointsUser;
    private User userWithPoints1;
    private User userWithPoints2;

    private final String invalidUserIdMessage = "Invalid User id";
    private final String invalidGeoCodeIdMessage = "Invalid GeoCode id";
    private final String invalidCollectableTypeIDMessage = "Invalid CollectableType ID";

    private UUID firstGeoCodeID;
    private UUID secondGeoCodeID;
    private UUID thirdGeoCodeID;

    private UUID trackableTypeUUID = new UUID(0, 0);

    private int numberOfOwnedGeoCodesBefore;
    private int numberOfFoundGeoCodesBefore;
    private int numberOfFoundCollectableTypesBefore;

    private final UUID invalidGeoCodeID = UUID.randomUUID();
    private final UUID invalidCollectableID = UUID.randomUUID();
    private final UUID invalidCollectableTypeID = UUID.randomUUID();

    private final UUID noPointsFirstOwnedGeoCodeID = UUID.randomUUID();
    private final UUID noPointsNewOwnedGeoCodeID = UUID.randomUUID();

    private final UUID noPointsFirstFoundGeoCodeID = UUID.randomUUID();
    private final UUID noPointsNewFoundGeoCodeID = UUID.randomUUID();

    private UUID newCollectableTypeID = UUID.randomUUID();
    private final UUID testCollectableType1ID = UUID.randomUUID();
    private final UUID testCollectableType2ID = UUID.randomUUID();
    private final UUID noPointsNewFoundCollectableTypeID = UUID.randomUUID();

    private final UUID collectableInFirstGeoCodeID = UUID.randomUUID();

    private final UUID swapMissionID = UUID.randomUUID();
    private final UUID circumferenceMissionID = UUID.randomUUID();

    private final String existingUserIdMessage = "User ID already exists";

    private UUID christmasSetId;

    private UUID collectableID1;
    private UUID collectableID2;
    private UUID collectableTypeID1;
    private UUID collectableTypeID2;
    private UUID fourthGeoCodeID;
    private UUID fifthGeoCodeID;

    private UUID santaCollectableTypeID;
    private UUID penguinCollectbaleTypeID;
    private UUID bearCollectableTypeID;

    private String openDayEventName = "Open Day 2021";
    private String winterSchoolEventName = "Winter School 2021";

    private GeoCode fourthGeoCode;
    private List<UUID> firstCollectables;
    private List<UUID> secondCollectables;
    private List<UUID> thirdCollectables;

    private GeoCode firstGeoCode;
    private GeoCode secondGeoCode;
    private GeoCode thirdGeoCode;

    private Collectable newCollectable;
    private UUID firstFoundColletableID;
    private UUID secondFoundCollectableID;
    private UUID firstFoundCollectableTypeID;
    private UUID secondFoundColletableTypeID;
    private CollectableType firstFoundCollectableType;
    private CollectableType secondFoundColletableType;

    User registerNewUser(UUID userID, String username){
        RegisterNewUserRequest request = new RegisterNewUserRequest(userID, username);
        RegisterNewUserResponse response;

        try {
            response = userService.registerNewUser(request);
            Assertions.assertTrue(response.isSuccess());

            //so do not have to remember to do this each time
            setUser(userID);

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

    private AddToOwnedGeoCodesResponse addToOwnedGeoCodes(User userID, GeoCode geocodeID) {
        try {
            return userService.addToOwnedGeoCodes(new AddToOwnedGeoCodesRequest(userID, geocodeID));
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private GetOwnedGeoCodesResponse getOwnedGeoCodes(UUID userID) {
        var request = new GetOwnedGeoCodesRequest(userID);

        try {
            return userService.getOwnedGeoCodes(request);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }

        return null;
    }

    //@BeforeEach
    @Transactional
    void setup() {
        //create invalid User object
        invalidUser = new User(invalidUserId);

        //register the valid Users
        validUser = registerNewUser(validUserId, "validUser");
        noPointsUser = registerNewUser(noPointsUserId, "noPointsUserId");
        userWithPoints1 = registerNewUser(userWithPoints1ID, "userWithPoints1");
        userWithPoints2 = registerNewUser(userWithPoints2ID, "userWithPoints2");

        //mock the SecurityContext
        MockSecurity.setup();
        setUser(validUserId);

        //create the CollectableSet to hold the "User Trackable" type
        christmasSetId = createCollectableSet("Christmas Set", "Christmas 2021 Collectables");

        //create the CollectableTypes
        HashMap<String, String> santaProperties = new HashMap<>();
        santaProperties.put("missionType", String.valueOf(MissionType.SWAP));
        santaCollectableTypeID = createCollectableType("Santa", "img_santa", Rarity.COMMON, christmasSetId, santaProperties);

        HashMap<String, String> penguinProperties = new HashMap<>();
        penguinProperties.put("missionType", String.valueOf(MissionType.GEOCODE));
        penguinCollectbaleTypeID = createCollectableType("Penguin", "img_penguin", Rarity.EPIC, christmasSetId, penguinProperties);

        HashMap<String, String> bearProperties = new HashMap<>();
        bearProperties.put("missionType", String.valueOf(MissionType.DISTANCE));
        bearCollectableTypeID = createCollectableType("Bear", "img_bear", Rarity.COMMON, christmasSetId, bearProperties);

        //create GeoCodes
        List<String> hints = new ArrayList<>();
        hints.add("Behind the climbing wall");

        try {
            firstGeoCodeID = geoCodeService.createGeoCode(new CreateGeoCodeRequest("1", new GeoPoint(10.0, 10.0), hints, Difficulty.EASY, true)).getGeoCodeID();
            firstGeoCode = geoCodeService.getGeoCode(new GetGeoCodeRequest(firstGeoCodeID)).getFoundGeoCode();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }

        try {
            secondGeoCodeID = geoCodeService.createGeoCode(new CreateGeoCodeRequest("2", new GeoPoint(10.0, 10.0), hints, Difficulty.MEDIUM, true)).getGeoCodeID();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }

        try {
            thirdGeoCodeID = geoCodeService.createGeoCode(new CreateGeoCodeRequest("3", new GeoPoint(10.0, 10.0), hints, Difficulty.HARD, true)).getGeoCodeID();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }

        //noPointsUser creates GeoCodes (i.e owns them)
        setUser(noPointsUserId);

        try {
            var response = geoCodeService.createGeoCode(new CreateGeoCodeRequest("4", new GeoPoint(10.0, 10.0), hints, Difficulty.HARD, true));
            fourthGeoCodeID = response.getGeoCodeID();
            System.out.println("4: "+response.isSuccess());
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }

        try {
            var response = geoCodeService.createGeoCode(new CreateGeoCodeRequest("5", new GeoPoint(10.0, 10.0), hints, Difficulty.INSANE, true));
            fifthGeoCodeID = response.getGeoCodeID();
            System.out.println("5: "+response.isSuccess());
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("firstGeoCodeID:"+firstGeoCodeID);
        System.out.println("secondGeoCodeID:"+secondGeoCodeID);
        System.out.println("thirdGeoCodeID:"+thirdGeoCodeID);
        System.out.println("fourthGeoCodeID:"+fourthGeoCodeID);
        System.out.println("fifthGeoCodeID:"+fifthGeoCodeID);
        System.out.println();

        try {
            firstCollectables = getCollectables(firstGeoCodeID);
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }
        try {
            secondCollectables = getCollectables(secondGeoCodeID);
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }
        try {
            thirdCollectables = getCollectables(thirdGeoCodeID);
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }

        //create Events for userWithPoints1 and userWithPoints2 to participate in
        List<UUID> openDayGeoCodeIDs = new ArrayList<>();
        openDayGeoCodeIDs.add(firstGeoCodeID);
        openDayGeoCodeIDs.add(secondGeoCodeID);

        try {
            createEvent(openDayEventName, "Open Day for UP 2021", new GeoPoint(0.0, 0.0),
                    LocalDate.now(), LocalDate.now().plusDays(1), openDayGeoCodeIDs, OrderLevels.GIVEN, new HashMap<>());
        } catch (tech.geocodeapp.geocode.event.exceptions.InvalidRequestException e) {
            e.printStackTrace();
        }

        List<UUID> winterSchoolGeoCodeIDs = new ArrayList<>();
        winterSchoolGeoCodeIDs.add(thirdGeoCodeID);
        winterSchoolGeoCodeIDs.add(fourthGeoCodeID);
        winterSchoolGeoCodeIDs.add(fifthGeoCodeID);

        try {
            createEvent(winterSchoolEventName, "Winter School for Maths and Physics", new GeoPoint(0.0, 0.0),
                    LocalDate.now(), LocalDate.now().plusDays(2), winterSchoolGeoCodeIDs, OrderLevels.DIFFICULTY, new HashMap<>());
        } catch (tech.geocodeapp.geocode.event.exceptions.InvalidRequestException e) {
            e.printStackTrace();
        }

        /* update missions and points for the User by swapping Collectables out of GeoCodes
        give userWithPoints1 and userWithPoints2 points */
        setUser(userWithPoints1ID);

        collectableID1 = firstCollectables.get(0);
        /*try {
            collectableTypeID1 = getCollectableTypeID(collectableID1);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }*/
        try {
            swapCollectables(firstGeoCodeID, collectableID1);
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }

        collectableID2 = secondCollectables.get(3);

        /*try {
            collectableTypeID2 = getCollectableTypeID(collectableID2);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }*/

        try {
            swapCollectables(secondGeoCodeID, collectableID2);
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }

        setUser(userWithPoints2ID);
        try {
            swapCollectables(firstGeoCodeID, firstCollectables.get(1));
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }
        try {
            swapCollectables(secondGeoCodeID, secondCollectables.get(0));
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }
    }

    private void createEvent(String name, String description, GeoPoint location,
                             LocalDate beginDate, LocalDate endDate,
                             List<UUID> geoCodesToFind, OrderLevels orderBy, Map<String, String> properties) throws tech.geocodeapp.geocode.event.exceptions.InvalidRequestException {

        var createEventRequest = new CreateEventRequest(name, description, location,
                beginDate, endDate,
                geoCodesToFind, orderBy, properties);

        eventService.createEvent(createEventRequest);
    }

    private CollectableType getCollectableTypeFromCollectableID(UUID collectableID) {
        GetCollectableByIDRequest getCollectableByIDRequest = new GetCollectableByIDRequest(collectableID);
        GetCollectableByIDResponse getCollectableByIDResponse = null;

        try {
            getCollectableByIDResponse = collectableService.getCollectableByID(getCollectableByIDRequest);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }

        return getCollectableByIDResponse.getCollectable().getType();
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

    private UUID createGeoCode(String description, GeoPoint location, Difficulty difficulty) {
        List<String> hints = new ArrayList<>();
        hints.add("Hint 1");

        CreateGeoCodeResponse createGeoCodeResponse = null;
        try {
            createGeoCodeResponse = geoCodeService.createGeoCode(new CreateGeoCodeRequest(description, location, hints, difficulty, true));
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }

        return createGeoCodeResponse.getGeoCodeID();
    }

    private void firstTwoGeoCodes() throws NullRequestParameterException, InvalidRequestException {
        validUser = registerNewUser(validUserId, "validUser");

        firstGeoCodeID = createGeoCode("1", new GeoPoint(10.0, 10.0), Difficulty.HARD);
        firstCollectables = getCollectables(firstGeoCodeID);

        secondGeoCodeID = createGeoCode("2", new GeoPoint(10.0, 10.0), Difficulty.INSANE);
        secondCollectables = getCollectables(secondGeoCodeID);

        //get the GeoCode objects
        firstGeoCode = getGeoCodeByID(firstGeoCodeID);
        secondGeoCode = getGeoCodeByID(secondGeoCodeID);

        //update validUSer
        validUser = userService.getUserById(new GetUserByIdRequest(validUserId)).getUser();
    }

    private void addFoundGeoCodes(){
        userWithPoints1 = registerNewUser(userWithPoints1ID, "userWithPoints1");

        //this User finds 2 GeoCodes
        try {
            //user wants the first Collectable in both
            firstFoundColletableID = firstCollectables.get(0);
            firstFoundCollectableType = getCollectableTypeFromCollectableID(firstFoundColletableID);
            swapCollectables(firstGeoCodeID, firstFoundColletableID);

            secondFoundCollectableID = secondCollectables.get(0);
            secondFoundColletableType = getCollectableTypeFromCollectableID(secondFoundCollectableID);
            swapCollectables(secondGeoCodeID, secondFoundCollectableID);
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }
    }

    private GetFoundCollectableTypesResponse getFoundCollectableTypes(User user){
        try {
            return userService.getFoundCollectableTypes(new GetFoundCollectableTypesRequest(user.getId()));
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }


    private AddToFoundCollectableTypesResponse addToFoundCollectableType(User user, CollectableType collectableType){
        try {
            return userService.addToFoundCollectableTypes(new AddToFoundCollectableTypesRequest(user, collectableType));
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private GetFoundGeoCodesResponse getFoundGeoCodes(UUID userID){
        try {
            var response = userService.getFoundGeoCodes(new GetFoundGeoCodesRequest(userID));

            Assertions.assertTrue(response.isSuccess());

            return response;
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private AddToFoundGeoCodesResponse addToFoundGeoCodes(UUID userID, UUID geocodeID){
        try {
            var response = userService.addToFoundGeoCodes(new AddToFoundGeoCodesRequest(userID, geocodeID));

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("GeoCode added to the found GeoCodes", response.getMessage());

            return response;
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    private GeoCode getGeoCodeByID(UUID id) throws InvalidRequestException {
        var request = new GetGeoCodeRequest(id);
        return geoCodeService.getGeoCode(request).getFoundGeoCode();
    }

    private GetCollectableTypeByIDResponse getCollectableTypeByID(UUID collectableTypeID){
        try {
            return collectableService.getCollectableTypeByID(new GetCollectableTypeByIDRequest(collectableTypeID));
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
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
        validUser = registerNewUser(validUserId, "validUser");

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
        validUser = registerNewUser(validUserId, "validUser");

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
            setUser(userWithPoints1ID);

            GetFoundCollectableTypesRequest request = new GetFoundCollectableTypesRequest(userWithPoints1ID);

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
            setUser(userWithPoints1ID);

            GetFoundGeoCodesRequest request = new GetFoundGeoCodesRequest(userWithPoints1ID);

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

            Assertions.assertEquals(3, ownedGeoCodeIDs.size());
            Assertions.assertTrue(ownedGeoCodeIDs.contains(firstGeoCodeID));
            Assertions.assertTrue(ownedGeoCodeIDs.contains(secondGeoCodeID));
            Assertions.assertTrue(ownedGeoCodeIDs.contains(thirdGeoCodeID));
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
        validUser = registerNewUser(validUserId, "validUser");

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
            Assertions.assertTrue(response.getLeaderboards().isEmpty());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Transactional
    void getMyLeaderboardsTestUserWithPoints1(){
        setUser(userWithPoints1ID);

        GetMyLeaderboardsRequest request = new GetMyLeaderboardsRequest();
        request.setUserID(userWithPoints1ID);

        try {
            GetMyLeaderboardsResponse response = userService.getMyLeaderboards(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The details for the User's Leaderboards were successfully returned", response.getMessage());

            List<MyLeaderboardDetails> leaderboardDetails = response.getLeaderboards();

            /* check the user has points */
            Assertions.assertFalse(leaderboardDetails.isEmpty());

            /* check that the correct details are returned */

            //user1 ranked 1st for Open Day event
            Assertions.assertTrue(leaderboardDetails.stream().anyMatch(details ->
                    details.getName().equals(openDayEventName) && details.getRank() == 1
            ));

            //user1 ranked 1st for Winter School event
            Assertions.assertTrue(leaderboardDetails.stream().anyMatch(details ->
                    details.getName().equals(winterSchoolEventName) && details.getRank() == 1
            ));
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Transactional
    void getMyLeaderboardsTestUserWithPoints2(){
        setUser(userWithPoints2ID);

        GetMyLeaderboardsRequest request = new GetMyLeaderboardsRequest();
        request.setUserID(userWithPoints2ID);

        try {
            GetMyLeaderboardsResponse response = userService.getMyLeaderboards(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The details for the User's Leaderboards were successfully returned", response.getMessage());

            List<MyLeaderboardDetails> leaderboardDetails = response.getLeaderboards();

            /* check the user has points */
            Assertions.assertFalse(leaderboardDetails.isEmpty());

            /* check that the correct details are returned */

            //user2 ranked 1st for Open Day event
            Assertions.assertTrue(leaderboardDetails.stream().anyMatch(details ->
                    details.getName().equals(openDayEventName) && details.getRank() == 1
            ));

            //user1 ranked 2nd for Winter School event
            Assertions.assertTrue(leaderboardDetails.stream().anyMatch(details ->
                    details.getName().equals(winterSchoolEventName) && details.getRank() == 2
            ));
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Transactional
    public void AddToOwnedGeoCodesTestNotAddDuplicate() throws InvalidRequestException {
        try {
            firstTwoGeoCodes();
            
            var response = addToOwnedGeoCodes(validUser, secondGeoCode);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("GeoCode added to the owned GeoCodes", response.getMessage());

            var getOwnedGeoCodesResponse = getOwnedGeoCodes(validUserId);

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
            firstTwoGeoCodes();

            //create the third GeoCode
            thirdGeoCodeID = createGeoCode("3", new GeoPoint(15.0, 15.0), Difficulty.EASY);
            thirdGeoCode = getGeoCodeByID(thirdGeoCodeID);

            var response = addToOwnedGeoCodes(validUser, thirdGeoCode);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("GeoCode added to the owned GeoCodes", response.getMessage());

            //check that the number of owned GeoCodes has increased by 1
            var getOwnedGeoCodesResponse = getOwnedGeoCodes(validUserId);

            Assertions.assertTrue(getOwnedGeoCodesResponse.isSuccess());

            var ownedGeoCodeIDs = getOwnedGeoCodesResponse.getGeocodeIDs();
            Assertions.assertEquals(3, ownedGeoCodeIDs.size());
            Assertions.assertTrue(ownedGeoCodeIDs.contains(thirdGeoCodeID));
        } catch (NullRequestParameterException | InvalidRequestException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void AddToFoundGeoCodesTestNotAddDuplicate(){
        try {
            firstTwoGeoCodes();
            addFoundGeoCodes();

            var addToFoundGeoCodesResponse = addToFoundGeoCodes(userWithPoints1ID, firstGeoCodeID);
            var getFoundGeoCodesResponse = getFoundGeoCodes(userWithPoints1ID);

            List<UUID> foundGeoCodeIDs = getFoundGeoCodesResponse.getGeocodeIDs();
            Assertions.assertEquals(2, foundGeoCodeIDs.size());
        } catch (NullRequestParameterException | InvalidRequestException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void AddToFoundGeoCodesTestAddNew(){
        setUser(userWithPoints1ID);

        try {
            //check number of found GeoCodes before adding
            GetFoundGeoCodesRequest getFoundGeoCodesRequest = new GetFoundGeoCodesRequest(userWithPoints1ID);
            GetFoundGeoCodesResponse getFoundGeoCodesResponse = userService.getFoundGeoCodes(getFoundGeoCodesRequest);

            Assertions.assertTrue(getFoundGeoCodesResponse.isSuccess());

            List<UUID> foundGeoCodeIDs = getFoundGeoCodesResponse.getGeocodeIDs();
            Assertions.assertEquals(2, foundGeoCodeIDs.size());

            AddToFoundGeoCodesRequest request = new AddToFoundGeoCodesRequest(userWithPoints1ID, thirdGeoCodeID);
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
        addFoundGeoCodes();
        newCollectableTypeID = createCollectableType("new type", "img_new_type", Rarity.COMMON, christmasSetId, new HashMap<>());
        newCollectable = createCollectable(newCollectableTypeID, false, new GeoPoint(0.0, 0.0));

        var response = addToFoundCollectableType(userWithPoints1, firstFoundCollectableType);

        Assertions.assertEquals("CollectableType added to the found CollectableTypes", response.getMessage());
        var getFoundCollectableTypesResponse = getFoundCollectableTypes(userWithPoints1);

        Assertions.assertTrue(getFoundCollectableTypesResponse.isSuccess());

        List<UUID> foundCollectableTypeIDs = getFoundCollectableTypesResponse.getCollectableTypeIDs();
        Assertions.assertEquals(2, foundCollectableTypeIDs.size());
    }

    @Test
    @Transactional
    public void AddToFoundCollectableTypesTestAddNew(){
        setUser(userWithPoints1ID);

        try{
            //check number of found CollectableTypes before adding
            GetFoundCollectableTypesRequest getFoundCollectableTypesRequest = new GetFoundCollectableTypesRequest(userWithPoints1ID);
            GetFoundCollectableTypesResponse getFoundCollectableTypesResponse = userService.getFoundCollectableTypes(getFoundCollectableTypesRequest);

            Assertions.assertTrue(getFoundCollectableTypesResponse.isSuccess());

            List<UUID> foundCollectableTypeIDs = getFoundCollectableTypesResponse.getCollectableTypeIDs();
            Assertions.assertEquals(2, foundCollectableTypeIDs.size());

            UUID thirdCollectableTypeID = null;

            var collectableTypes = collectableService.getCollectableTypes().getCollectableTypes();

            //find the third (or second if the first 2 types are the same) ID that we have no found yet
            for(var type: collectableTypes){
                if(!type.getId().equals(collectableTypeID1) && !type.getId().equals(collectableTypeID2)){
                    thirdCollectableTypeID = type.getId();
                    break;
                }
            }

            var thirdCollectableType = getCollectableTypeByID(thirdCollectableTypeID);

            AddToFoundCollectableTypesRequest request = new AddToFoundCollectableTypesRequest(userWithPoints1, thirdCollectableType);
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
    public void swapCollectableTestCollectableIsSwapped(){
        setUser(validUserId);

        try {
            SwapCollectableRequest request = new SwapCollectableRequest(validUser, getCollectableByID(firstCollectables.get(2)), firstGeoCode);
            SwapCollectableResponse response = userService.swapCollectable(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The User's Collectable was swapped with the Collectable in the GeoCode", response.getMessage());

            Collectable collectable = response.getCollectable();
            Assertions.assertNotNull(collectable);

            //System.out.println("collectable that comes out: "+collectable.getId());

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

    private Collectable getCollectableByID(UUID id) throws NullRequestParameterException {
        var response = collectableService.getCollectableByID(new GetCollectableByIDRequest(id));
        return response.getCollectable();
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
