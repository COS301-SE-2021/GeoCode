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
import tech.geocodeapp.geocode.mission.model.Mission;
import tech.geocodeapp.geocode.mission.model.MissionType;
import tech.geocodeapp.geocode.mission.request.GetMissionByIdRequest;
import tech.geocodeapp.geocode.mission.service.MissionServiceImpl;
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
    MissionServiceImpl missionService;

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
    private User userWithPoints1;
    private User userWithPoints2;

    private final String invalidUserIdMessage = "Invalid User id";
    private final String invalidGeoCodeIdMessage = "Invalid GeoCode id";
    private final String invalidCollectableTypeIDMessage = "Invalid CollectableType ID";

    private UUID firstGeoCodeID;
    private UUID secondGeoCodeID;
    private UUID thirdGeoCodeID;

    private UUID trackableTypeUUID = new UUID(0, 0);

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
    private UUID penguinCollectableTypeID;
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

    private UUID firstFoundCollectableID;
    private UUID secondFoundCollectableID;
    private UUID firstFoundCollectableTypeID;
    private UUID secondFoundCollectableTypeID;

    private CollectableType firstFoundCollectableType;
    private CollectableType secondFoundCollectableType;

    private List<UUID> openDayGeoCodeIDs;

    private UUID winterSchoolGeoCode1ID;
    private UUID winterSchoolGeoCode2ID;
    private UUID winterSchoolGeoCode3ID;

    private List<UUID> winterSchoolGeoCodeIDs;
    private String successGetMyLeaderboardsMessage = "The details for the User's Leaderboards were successfully returned";

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
            Assertions.assertTrue(createCollectableTypeResponse.isSuccess());

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

    private AddToOwnedGeoCodesResponse addToOwnedGeoCodes(UUID userID, UUID geocodeID) {//***here
        try {
            var user = getUserByID(userID);
            GeoCode geocode = null;

            try {
                geocode = getGeoCodeByID(geocodeID);
            } catch (InvalidRequestException e) {
                e.printStackTrace();
            }

            return userService.addToOwnedGeoCodes(new AddToOwnedGeoCodesRequest(user, geocode));
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

    private void createEvent(String name, String description, GeoPoint location,
                             LocalDate beginDate, LocalDate endDate,
                             List<UUID> geoCodesToFind, OrderLevels orderBy, Map<String, String> properties) throws tech.geocodeapp.geocode.event.exceptions.InvalidRequestException {
        //admin user (cannot participate)
        //setUser(validUserId);

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
        var geocode = getGeoCodeByID(geoCodeID);
//        Collectable collectable;
//
//        try {
//            collectable = getCollectableByID(collectableID);
//        } catch (NullRequestParameterException e) {
//            e.printStackTrace();
//        }

        System.out.println("swapping from "+geocode.getDescription()+" taking out "+collectableID);

        geoCodeService.swapCollectables(new SwapCollectablesRequest(geoCodeID, collectableID));
    }

    private List<UUID> getCollectables(UUID geoCodeID) throws InvalidRequestException {
        return geoCodeService.getCollectables(new GetCollectablesRequest(geoCodeID)).getCollectables();
    }

    /**
     * Mocks the User logging in
     * @param userID
     */
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

    private void addFirstTwoGeoCodes() throws NullRequestParameterException, InvalidRequestException {
        validUser = registerNewUser(validUserId, "validUser");

        firstGeoCodeID = createGeoCode("1", new GeoPoint(10.0, 10.0), Difficulty.HARD);
        firstCollectables = getCollectables(firstGeoCodeID);

        secondGeoCodeID = createGeoCode("2", new GeoPoint(10.0, 10.0), Difficulty.INSANE);
        secondCollectables = getCollectables(secondGeoCodeID);

        //get the GeoCode objects
        firstGeoCode = getGeoCodeByID(firstGeoCodeID);
        secondGeoCode = getGeoCodeByID(secondGeoCodeID);

        //update validUser
        validUser = getUserByID(validUserId);
    }

    private void createCollectableTypes(){
        //create the CollectableSet to hold the "User Trackable" type
        christmasSetId = createCollectableSet("Christmas Set", "Christmas 2021 Collectables");

        //create the CollectableTypes so that the GeoCodes have CollectableTypes to be populated with
        HashMap<String, String> santaProperties = new HashMap<>();
        santaProperties.put("missionType", String.valueOf(MissionType.SWAP));
        santaCollectableTypeID = createCollectableType("Santa", "img_santa", Rarity.COMMON, christmasSetId, santaProperties);

        HashMap<String, String> penguinProperties = new HashMap<>();
        penguinProperties.put("missionType", String.valueOf(MissionType.GEOCODE));
        penguinCollectableTypeID = createCollectableType("Penguin", "img_penguin", Rarity.EPIC, christmasSetId, penguinProperties);

        HashMap<String, String> bearProperties = new HashMap<>();
        bearProperties.put("missionType", String.valueOf(MissionType.DISTANCE));
        bearCollectableTypeID = createCollectableType("Bear", "img_bear", Rarity.COMMON, christmasSetId, bearProperties);

        System.out.println("santaCollectableTypeID: "+santaCollectableTypeID);
        System.out.println("penguinCollectbaleTypeID: "+ penguinCollectableTypeID);
        System.out.println("bearCollectableTypeID: "+bearCollectableTypeID);
    }

    private User getUserByID(UUID userID) {
        try {
            return userService.getUserById(new GetUserByIdRequest(userID)).getUser();
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void addFoundGeoCodesForUser1(){
        userWithPoints1 = registerNewUser(userWithPoints1ID, "userWithPoints1");

        //this User finds 2 GeoCodes
        try {
            //user wants the first Collectable in both
            firstFoundCollectableID = firstCollectables.get(0);
            firstFoundCollectableType = getCollectableTypeFromCollectableID(firstFoundCollectableID);
            firstFoundCollectableTypeID = firstFoundCollectableType.getId();
            swapCollectables(firstGeoCodeID, firstFoundCollectableID);

            secondFoundCollectableID = secondCollectables.get(0);
            secondFoundCollectableType = getCollectableTypeFromCollectableID(secondFoundCollectableID);
            secondFoundCollectableTypeID = secondFoundCollectableType.getId();
            swapCollectables(secondGeoCodeID, secondFoundCollectableID);

            userWithPoints1 = getUserByID(userWithPoints1ID);
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }
    }

    private void addFoundGeoCodesForUser2() throws InvalidRequestException {
        userWithPoints2 = registerNewUser(userWithPoints2ID, "userWithPoints2");

        //this User finds 1 GeoCode
        swapCollectables(firstGeoCodeID, firstCollectables.get(1));

        userWithPoints2 = getUserByID(userWithPoints2ID);
    }

    private GetFoundCollectableTypesResponse getFoundCollectableTypes(User user){
        try {
            return userService.getFoundCollectableTypes(new GetFoundCollectableTypesRequest(user.getId()));
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }


    private AddToFoundCollectableTypesResponse addToFoundCollectableTypes(UUID userID, UUID collectableTypeID){
        try {
            var user = userService.getUserById(new GetUserByIdRequest(userID)).getUser();
            var collectableType = getCollectableTypeByID(collectableTypeID);

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
            var user = getUserByID(userID);
            GeoCode geocode = null;

            try {
                geocode = getGeoCodeByID(geocodeID);
            } catch (InvalidRequestException e) {
                e.printStackTrace();
            }

            return userService.addToFoundGeoCodes(new AddToFoundGeoCodesRequest(user, geocode));
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Mission getMissionByID(UUID missionID) {
        try {
            return missionService.getMissionById(new GetMissionByIdRequest(missionID)).getMission();
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private AddToMyMissionsResponse addToMyMissions(UUID userID, UUID missionID){
        var user = getUserByID(userID);
        var mission = getMissionByID(missionID);

        try {
            return userService.addToMyMissions(new AddToMyMissionsRequest(user, mission));
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private GeoCode getGeoCodeByID(UUID id) throws InvalidRequestException {
        var request = new GetGeoCodeRequest(id);
        return geoCodeService.getGeoCode(request).getFoundGeoCode();
    }

    private Collectable getCollectableByID(UUID id) throws NullRequestParameterException {
        var response = collectableService.getCollectableByID(new GetCollectableByIDRequest(id));
        return response.getCollectable();
    }

    private CollectableType getCollectableTypeByID(UUID collectableTypeID){
        try {
            return collectableService.getCollectableTypeByID(new GetCollectableTypeByIDRequest(collectableTypeID)).getCollectableType();
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private SwapCollectableResponse swapCollectable(UUID userID, UUID collectableID, UUID geocodeID) throws NullRequestParameterException, InvalidRequestException {
        var user = getUserByID(userID);
        var collectable = getCollectableByID(collectableID);
        var geocode = getGeoCodeByID(geocodeID);

        return userService.swapCollectable(new SwapCollectableRequest(user, collectable, geocode));
    }

    private void createOpenDayEvent() throws NullRequestParameterException, InvalidRequestException {
        //create an Event's GeoCodes
        addFirstTwoGeoCodes();

        openDayGeoCodeIDs = new ArrayList<>();
        openDayGeoCodeIDs.add(firstGeoCodeID);
        openDayGeoCodeIDs.add(secondGeoCodeID);

        try {
            createEvent(openDayEventName, openDayEventName, new GeoPoint(0.0, 0.0),
                    LocalDate.now(), LocalDate.now().plusDays(1), openDayGeoCodeIDs, OrderLevels.GIVEN, new HashMap<>());
        } catch (tech.geocodeapp.geocode.event.exceptions.InvalidRequestException e) {
            e.printStackTrace();
        }
    }

    private void addFoundGeoCodesForUser1WinterSchool() throws InvalidRequestException {
        setUser(userWithPoints1ID);

        //this User finds 2 GeoCodes
        swapCollectablesAtPosition(winterSchoolGeoCode1ID, 1);
        swapCollectablesAtPosition(winterSchoolGeoCode3ID, 1);
    }

    private void addFoundGeoCodesForUser2WinterSchool() throws InvalidRequestException {
        setUser(userWithPoints2ID);

        //this User finds all 3 GeoCodes
        swapCollectablesAtPosition(winterSchoolGeoCode1ID, 1);
        swapCollectablesAtPosition(winterSchoolGeoCode3ID, 1);
        swapCollectablesAtPosition(winterSchoolGeoCode2ID, 1);
    }

    private void swapCollectablesAtPosition(UUID geocodeID, int position) throws InvalidRequestException {
        swapCollectables(geocodeID, getCollectables(geocodeID).get(position-1));
    }

    private void createWinterSchoolEvent(){
        //create GeoCodes
        setUser(validUserId);

        winterSchoolGeoCode1ID = createGeoCode("winterSchool1", new GeoPoint(0.0, 0.0), Difficulty.EASY);
        winterSchoolGeoCode2ID = createGeoCode("winterSchool2", new GeoPoint(0.0, 0.0), Difficulty.INSANE);
        winterSchoolGeoCode3ID = createGeoCode("winterSchool3", new GeoPoint(0.0, 0.0), Difficulty.HARD);

        //create the Event
        winterSchoolGeoCodeIDs = new ArrayList<>();
        winterSchoolGeoCodeIDs.add(winterSchoolGeoCode1ID);
        winterSchoolGeoCodeIDs.add(winterSchoolGeoCode2ID);
        winterSchoolGeoCodeIDs.add(winterSchoolGeoCode3ID);

        try {
            createEvent(winterSchoolEventName, winterSchoolEventName, new GeoPoint(0.0, 0.0),
                    LocalDate.now(), LocalDate.now().plusDays(3), winterSchoolGeoCodeIDs, OrderLevels.DIFFICULTY, new HashMap<>());
        } catch (tech.geocodeapp.geocode.event.exceptions.InvalidRequestException e) {
            e.printStackTrace();
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
            addFirstTwoGeoCodes();
            addFoundGeoCodesForUser1();

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

            Assertions.assertTrue(foundCollectableTypeIDs.contains(firstFoundCollectableTypeID));
            Assertions.assertTrue(foundCollectableTypeIDs.contains(secondFoundCollectableTypeID));
        }catch (NullRequestParameterException | InvalidRequestException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Transactional
    void getFoundGeoCodesTestInvalidUser() {
        validUser = registerNewUser(validUserId, "validUser");

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
            addFirstTwoGeoCodes();
            addFoundGeoCodesForUser1();

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
        }catch (NullRequestParameterException | InvalidRequestException e){
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
            addFirstTwoGeoCodes();

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
            Assertions.assertTrue(ownedGeoCodeIDs.contains(firstGeoCodeID));
            Assertions.assertTrue(ownedGeoCodeIDs.contains(secondGeoCodeID));
        }catch (NullRequestParameterException | InvalidRequestException e){
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
        User noPointsUser = registerNewUser(noPointsUserId, "noPointsUser");

        GetMyLeaderboardsRequest request = new GetMyLeaderboardsRequest();
        request.setUserID(noPointsUserId);

        try {
            GetMyLeaderboardsResponse response = userService.getMyLeaderboards(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals(successGetMyLeaderboardsMessage, response.getMessage());
            Assertions.assertTrue(response.getLeaderboards().isEmpty());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Transactional
    public void AddToOwnedGeoCodesTestNotAddDuplicate() throws InvalidRequestException {
        try {
            addFirstTwoGeoCodes();
            
            var response = addToOwnedGeoCodes(validUserId, secondGeoCodeID);

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
            addFirstTwoGeoCodes();

            //create the third GeoCode
            thirdGeoCodeID = createGeoCode("3", new GeoPoint(15.0, 15.0), Difficulty.EASY);
            thirdGeoCode = getGeoCodeByID(thirdGeoCodeID);

            var response = addToOwnedGeoCodes(validUserId, thirdGeoCodeID);

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
            addFirstTwoGeoCodes();
            addFoundGeoCodesForUser1();

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
    public void AddToFoundGeoCodesTestAddNew() throws NullRequestParameterException, InvalidRequestException {
        addFirstTwoGeoCodes();
        addFoundGeoCodesForUser1();

        //set to the admin user to create this GeoCode
        setUser(validUserId);

        thirdGeoCodeID = createGeoCode("3", new GeoPoint(0.0, 0.0), Difficulty.EASY);
        thirdGeoCode = getGeoCodeByID(thirdGeoCodeID);
        thirdCollectables = getCollectables(thirdGeoCodeID);

        setUser(userWithPoints1ID);
        swapCollectables(thirdGeoCodeID, thirdCollectables.get(0));

        //check that the number of owned GeoCodes has increased by 1
        var getFoundGeoCodesResponse = getFoundGeoCodes(userWithPoints1ID);

        Assertions.assertTrue(getFoundGeoCodesResponse.isSuccess());

        var foundGeoCodeIDs = getFoundGeoCodesResponse.getGeocodeIDs();
        Assertions.assertEquals(3, foundGeoCodeIDs.size());
        Assertions.assertTrue(foundGeoCodeIDs.contains(thirdGeoCodeID));
    }

    @Test
    @Transactional
    public void AddToFoundCollectableTypesTestNotAddDuplicate() throws NullRequestParameterException, InvalidRequestException {
        addFirstTwoGeoCodes();
        addFoundGeoCodesForUser1();

        //create the CollectableSet to hold the "User Trackable" type
        christmasSetId = createCollectableSet("Christmas Set", "Christmas 2021 Collectables");

        newCollectableTypeID = createCollectableType("new type", "img_new_type", Rarity.COMMON, christmasSetId, new HashMap<>());
        newCollectable = createCollectable(newCollectableTypeID, false, new GeoPoint(0.0, 0.0));

        var response = addToFoundCollectableTypes(userWithPoints1ID, firstFoundCollectableTypeID);

        Assertions.assertEquals("CollectableType added to the found CollectableTypes", response.getMessage());
        var getFoundCollectableTypesResponse = getFoundCollectableTypes(userWithPoints1);
        Assertions.assertTrue(getFoundCollectableTypesResponse.isSuccess());

        List<UUID> foundCollectableTypeIDs = getFoundCollectableTypesResponse.getCollectableTypeIDs();
        Assertions.assertEquals(2, foundCollectableTypeIDs.size());
    }

    @Test
    @Transactional
    public void AddToFoundCollectableTypesTestAddNew() throws NullRequestParameterException, InvalidRequestException {
        addFirstTwoGeoCodes();
        addFoundGeoCodesForUser1();

        //check number of found CollectableTypes before adding
        UUID thirdCollectableTypeID = null;

        var collectableTypes = collectableService.getCollectableTypes().getCollectableTypes();

        //find the third (or second if the first 2 types are the same) ID that we have no found yet
        for(var type: collectableTypes){
            if(!type.getId().equals(firstFoundCollectableTypeID) && !type.getId().equals(secondFoundCollectableTypeID)){
                thirdCollectableTypeID = type.getId();
                break;
            }
        }

        System.out.println("third type id:"+thirdCollectableTypeID);

        var addToFoundCollectableTypesResponse = addToFoundCollectableTypes(userWithPoints1ID, thirdCollectableTypeID);
        Assertions.assertTrue(addToFoundCollectableTypesResponse.isSuccess());
        Assertions.assertEquals("CollectableType added to the found CollectableTypes", addToFoundCollectableTypesResponse.getMessage());

        //check that the number of owned CollectableTypes has increased by 1
        var getFoundCollectableTypesResponse = getFoundCollectableTypes(userWithPoints1);
        Assertions.assertTrue(getFoundCollectableTypesResponse.isSuccess());

        var foundCollectableTypeIDs = getFoundCollectableTypesResponse.getCollectableTypeIDs();

        int numberOfFoundCollectableTypesBefore;

        if(firstFoundCollectableTypeID.equals(secondFoundCollectableTypeID)){
            numberOfFoundCollectableTypesBefore = 1;
        }else{
            numberOfFoundCollectableTypesBefore = 2;
        }

        Assertions.assertEquals(numberOfFoundCollectableTypesBefore+1, foundCollectableTypeIDs.size());
        Assertions.assertTrue(foundCollectableTypeIDs.contains(thirdCollectableTypeID));
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
        validUser = registerNewUser(validUserId, "validUser");

        try {
            GetUserByIdRequest request = new GetUserByIdRequest(validUserId);
            GetUserByIdResponse response = userService.getUserById(request);
            User user = response.getUser();

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The User was found", response.getMessage());

            Assertions.assertEquals(validUserId, user.getId());
            Assertions.assertEquals("validUser", user.getUsername());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void registerNewUserTestExistingUserId(){
        validUser = registerNewUser(validUserId, "validUser");

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
    public void swapCollectableTestCollectableIsSwapped() throws NullRequestParameterException, InvalidRequestException {
        addFirstTwoGeoCodes();

        try {
            //get the 3rd Collectable in the first GeoCode
            UUID newCurrentCollectableID = firstCollectables.get(2);
            var response = swapCollectable(validUserId, newCurrentCollectableID, firstGeoCodeID);

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
            Assertions.assertEquals(newCurrentCollectableID, currentCollectable.getId());
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
    void getMyMissionsTestValidUser() throws NullRequestParameterException, InvalidRequestException {
        createCollectableTypes();
        addFirstTwoGeoCodes();
        addFoundGeoCodesForUser1();

        Collectable firstFoundCollectable = getCollectableByID(firstFoundCollectableID);
        Mission firstMission = getMissionByID(firstFoundCollectable.getMissionID());

        Collectable secondFoundCollectable = getCollectableByID(secondFoundCollectableID);
        Mission secondMission = getMissionByID(secondFoundCollectable.getMissionID());

        setUser(userWithPoints1ID);
        GetMyMissionsRequest request = new GetMyMissionsRequest(userWithPoints1ID);

        try {
            GetMyMissionsResponse response = userService.getMyMissions(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("User Missions returned", response.getMessage());

            Set<Mission> missions = response.getMissions();
            Assertions.assertNotNull(missions);

            for(var mission: missions){
                System.out.println(mission.getType());
            }

            Assertions.assertTrue(missions.stream().anyMatch(mission -> {
                assert firstMission != null;
                return mission.getId().equals(firstMission.getId()) && mission.getType().equals(firstMission.getType());
            }));

            Assertions.assertTrue(missions.stream().anyMatch(mission -> {
                assert secondMission != null;
                return mission.getId().equals(secondMission.getId()) && mission.getType().equals(secondMission.getType());
            }));
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }
}
