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
import tech.geocodeapp.geocode.event.request.GetCurrentEventStatusRequest;
import tech.geocodeapp.geocode.event.service.EventServiceImpl;
import tech.geocodeapp.geocode.general.MockCurrentUserDetails;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.general.response.Response;
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
import tech.geocodeapp.geocode.user.service.UserServiceImpl;

import java.time.LocalDate;
import java.util.*;

@SpringBootTest
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class UserServiceImplIT {
    @Autowired
    private UserService userService;

//    @Autowired
//    private MockCurrentUserDetails mockCurrentUserDetails;

    @Autowired
    CollectableServiceImpl collectableService;

    @Autowired
    GeoCodeServiceImpl geoCodeService;

    @Autowired
    MissionServiceImpl missionService;

    @Autowired
    EventServiceImpl eventService;

    private final UUID invalidUserId = UUID.randomUUID();
    private UUID validUserId;
    private final UUID newUserId = UUID.randomUUID();
    private UUID userWithPoints1ID;
    private UUID userWithPoints2ID;

    private User userWithPoints1;
    private User userWithPoints2;

    private final String invalidUserIdMessage = "Invalid User id";

    private UUID firstGeoCodeID;
    private UUID secondGeoCodeID;
    private UUID thirdGeoCodeID;

    private final UUID trackableTypeUUID = new UUID(0, 0);

    private final String successGetMyLeaderboardsMessage = "The details for the User's Leaderboards were successfully returned";

    private UUID christmasSetId;

    private final String openDayEventName = "Open Day 2021";
    private final String winterSchoolEventName = "Winter School 2021";

    private List<UUID> firstCollectables;
    private List<UUID> secondCollectables;

    private UUID firstFoundCollectableID;
    private UUID secondFoundCollectableID;
    private UUID firstFoundCollectableTypeID;
    private UUID secondFoundCollectableTypeID;

    private CollectableType firstFoundCollectableType;
    private CollectableType secondFoundCollectableType;

    private boolean openDayEvent;
    private UUID openDayEventID;

    private boolean winterSchoolEvent;
    private UUID winterSchoolEventID;

    private UUID winterSchoolGeoCode1ID;
    private UUID winterSchoolGeoCode2ID;
    private UUID winterSchoolGeoCode3ID;

    private CreateGeoCodeRequest createFirstGeoCodeRequest;
    private CreateGeoCodeRequest createSecondGeoCodeRequest;

    private CreateGeoCodeRequest createWinterSchoolGeoCode1Request;
    private CreateGeoCodeRequest createWinterSchoolGeoCode2Request;
    private CreateGeoCodeRequest createWinterSchoolGeoCode3Request;

    public UserServiceImplIT() {

    }

//    private void setUser(UUID userID){
//        mockCurrentUserDetails.setID(userID);
//    }
//
//    private void setUser(UUID userID, String username, boolean isAdmin){
//        mockCurrentUserDetails.setID(userID);
//        mockCurrentUserDetails.setUsername(username);
//        mockCurrentUserDetails.setAdmin(isAdmin);
//    }

    /**
     * Mocks the User logging in
     * @param userID The id of the User to be set in setCurrentUserID
     */
    private void setUser(UUID userID, String username, boolean isAdmin){
        MockSecurity.setup();
        MockSecurity.setCurrentUserID(userID);

        MockSecurity.setCurrentUsername(username);
        MockSecurity.setCurrentUserIsAdmin(isAdmin);
    }

    private UUID handleUserLogin(String username){
        return handleLogin(UUID.randomUUID(), username, false);
    }

    private UUID handleAdminLogin(String username){
        return handleLogin(UUID.randomUUID(), username, true);
    }

    private UUID handleLogin(UUID userID, String username, boolean isAdmin){
        try {
            setUser(userID, username, isAdmin);
            var response = userService.handleLogin(new HandleLoginRequest(new GeoPoint(0.0, 0.0)));

            Assertions.assertEquals("New User registered", response.getMessage());
            Assertions.assertTrue(response.isSuccess());

            return userID;
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    UUID createCollectableSet(String name, String description){
        var createCollectableSetRequest = new CreateCollectableSetRequest(name, description);

        try {
            var createCollectableSetResponse = collectableService.createCollectableSet(createCollectableSetRequest);
            return createCollectableSetResponse.getCollectableSet().getId();
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private UUID createCollectableType(String name, String image, Rarity rarity, UUID setId, HashMap<String, String> properties) {
        var createCollectableTypeRequest = new CreateCollectableTypeRequest(name, image, rarity, setId, properties);

        try {
            var createCollectableTypeResponse = collectableService.createCollectableType(createCollectableTypeRequest);
            Assertions.assertTrue(createCollectableTypeResponse.isSuccess());

            return createCollectableTypeResponse.getCollectableType().getId();
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    void createCollectable(UUID typeID, boolean createMission, GeoPoint location){
        var createCollectableRequest = new CreateCollectableRequest();
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

        var id = createCollectableResponse.getCollectable().getId();

        try {
            collectableService.getCollectableByID(new GetCollectableByIDRequest(id));
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
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

    private UUID createEvent(String name, String description, GeoPoint location,
                             LocalDate beginDate, LocalDate endDate,
                             List<CreateGeoCodeRequest> createGeoCodesToFind, OrderLevels orderBy, Map<String, String> properties) throws tech.geocodeapp.geocode.event.exceptions.InvalidRequestException {

        var createEventRequest = new CreateEventRequest(name, description, location,
                beginDate, endDate,
                createGeoCodesToFind, orderBy, properties);

        var createEventResponse = eventService.createEvent(createEventRequest);

        Assertions.assertEquals("Event created", createEventResponse.getMessage());
        Assertions.assertTrue(createEventResponse.isSuccess());
        Assertions.assertNotNull(createEventResponse.getEventID());
        return createEventResponse.getEventID();
    }

    private CollectableType getCollectableTypeFromCollectableID(UUID collectableID) {
        var getCollectableByIDRequest = new GetCollectableByIDRequest(collectableID);
        GetCollectableByIDResponse getCollectableByIDResponse = null;

        try {
            getCollectableByIDResponse = collectableService.getCollectableByID(getCollectableByIDRequest);
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }

        Assertions.assertNotNull(getCollectableByIDResponse);
        return getCollectableByIDResponse.getCollectable().getType();
    }

    private void swapCollectables(UUID geoCodeID, UUID collectableID) {
        var swapCollectablesRequest = new SwapCollectablesRequest(geoCodeID, collectableID);

        try {
            geoCodeService.swapCollectables(swapCollectablesRequest);
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }
    }

    private List<UUID> getCollectables(UUID geoCodeID) {
        try {
            return geoCodeService.getCollectables(new GetCollectablesRequest(geoCodeID)).getCollectables();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
            return null;
        }
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

        Assertions.assertNotNull(createGeoCodeResponse);
        return createGeoCodeResponse.getGeoCodeID();
    }

    /**
     * Actually creates the first 2 GeoCodes - for the tests where they are not part of an Event
     * so the GeoCodes are created, but for an Event the requests are made so that the GeoCodes are
     * only created when Event.createEvent creates them
     */
    private void addFirstTwoGeoCodes() throws NullRequestParameterException, InvalidRequestException {
        validUserId = handleUserLogin("validUser");;

        firstGeoCodeID = createGeoCode("1", new GeoPoint(10.0, 10.0), Difficulty.HARD);
        firstCollectables = getCollectables(firstGeoCodeID);

        secondGeoCodeID = createGeoCode("2", new GeoPoint(10.0, 10.0), Difficulty.INSANE);
        secondCollectables = getCollectables(secondGeoCodeID);
    }

    /**
     * Create the CreateGeoCodeRequest objects
     * so the GeoCodes are created, but for an Event the requests are made so that the GeoCodes are
     * only created when Event.createEvent creates them
     */
    private void addOpenDayGeoCodes() throws NullRequestParameterException, InvalidRequestException {
        validUserId = handleUserLogin("validUser");;

        firstGeoCodeID = UUID.randomUUID();
        secondGeoCodeID = UUID.randomUUID();

        System.out.println("firstGeoCodeID: "+firstGeoCodeID);
        System.out.println("secondGeoCodeID: "+secondGeoCodeID);

        createFirstGeoCodeRequest = new CreateGeoCodeRequest(firstGeoCodeID, "1", new GeoPoint(10.0, 10.0), new ArrayList<>(),
                Difficulty.HARD, true);

        createSecondGeoCodeRequest = new CreateGeoCodeRequest(secondGeoCodeID, "2", new GeoPoint(10.0, 10.0), new ArrayList<>(),
                Difficulty.INSANE, true);
    }

    /**
     * Create the CreateGeoCodeRequest objects for the WinterSchool Event
     */
    private void addWinterSchoolGeoCodes(){
        winterSchoolGeoCode1ID = UUID.randomUUID();
        createWinterSchoolGeoCode1Request = new CreateGeoCodeRequest(winterSchoolGeoCode1ID, "winterSchool1", new GeoPoint(0.0, 0.0),
                new ArrayList<>(), Difficulty.EASY, true);

        winterSchoolGeoCode2ID = UUID.randomUUID();
        createWinterSchoolGeoCode2Request = new CreateGeoCodeRequest(winterSchoolGeoCode2ID, "winterSchool2", new GeoPoint(0.0, 0.0),
                new ArrayList<>(), Difficulty.INSANE, true);

        winterSchoolGeoCode3ID = UUID.randomUUID();
        createWinterSchoolGeoCode3Request = new CreateGeoCodeRequest(winterSchoolGeoCode3ID, "winterSchool3", new GeoPoint(0.0, 0.0),
                new ArrayList<>(), Difficulty.HARD, true);
    }

    private void createCollectableTypes(){
        //create the CollectableSet to hold the "User Trackable" type
        christmasSetId = createCollectableSet("Christmas Set", "Christmas 2021 Collectables");

        //create the CollectableTypes so that the GeoCodes have CollectableTypes to be populated with
        var santaProperties = new HashMap<String, String>();
        santaProperties.put("missionType", String.valueOf(MissionType.SWAP));
        createCollectableType("Santa", "img_santa", Rarity.COMMON, christmasSetId, santaProperties);

        var penguinProperties = new HashMap<String, String>();
        penguinProperties.put("missionType", String.valueOf(MissionType.GEOCODE));
        createCollectableType("Penguin", "img_penguin", Rarity.EPIC, christmasSetId, penguinProperties);

        var bearProperties = new HashMap<String, String>();
        bearProperties.put("missionType", String.valueOf(MissionType.DISTANCE));
        createCollectableType("Bear", "img_bear", Rarity.COMMON, christmasSetId, bearProperties);
    }

    private User getUserByID(UUID userID) {
        try {
            return userService.getUserById(new GetUserByIdRequest(userID)).getUser();
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void joinEvent(UUID eventID, UUID userID){
        Assertions.assertNotNull(eventID);
        Assertions.assertNotNull(userID);

        /*
        getTheEventStatus to make sure the EventStatus is saved so that when calling swapCollectables
        the User is participating in the Event
        */
        try {
            var getCurrentEventStatusRequest = new GetCurrentEventStatusRequest(eventID, userID);
            var getCurrentEventStatusResponse = eventService.getCurrentEventStatus(getCurrentEventStatusRequest);

            Assertions.assertTrue(getCurrentEventStatusResponse.isSuccess());
            Assertions.assertEquals("Status returned", getCurrentEventStatusResponse.getMessage());
        } catch (tech.geocodeapp.geocode.event.exceptions.InvalidRequestException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lets the Users join the Events
     * @param userID The userID
     */
    private void joinEvents(UUID userID){
        if(openDayEvent){
            joinEvent(openDayEventID, userID);
        }

        if(winterSchoolEvent){
            joinEvent(winterSchoolEventID, userID);
        }
    }

    private void addFoundGeoCodesForUser1(){
        userWithPoints1ID = handleUserLogin("validUser");;

        joinEvents(userWithPoints1ID);

        //this User finds 2 GeoCodes
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
    }

    private void addFoundGeoCodesForUser2() {
        userWithPoints2ID = handleUserLogin("userWithPoints2");;

        joinEvents(userWithPoints2ID);

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
        openDayEvent = true;

        //create an Event's GeoCodes
        addOpenDayGeoCodes();

        List<CreateGeoCodeRequest> createOpenDayGeoCodeRequests = new ArrayList<>();
        createOpenDayGeoCodeRequests.add(createFirstGeoCodeRequest);
        createOpenDayGeoCodeRequests.add(createSecondGeoCodeRequest);

        try {
            openDayEventID = createEvent(openDayEventName, openDayEventName, new GeoPoint(0.0, 0.0),
                    LocalDate.now(), LocalDate.now().plusDays(1), createOpenDayGeoCodeRequests, OrderLevels.GIVEN, new HashMap<>());
        } catch (tech.geocodeapp.geocode.event.exceptions.InvalidRequestException e) {
            e.printStackTrace();
            return;
        }

        /* get the Collectables for the 2 GeoCodes in the Event
        * must do this after the event is created since the GeoCodes are only created
        * in Event.createEvent
         */
        firstCollectables = getCollectables(firstGeoCodeID);
        secondCollectables = getCollectables(secondGeoCodeID);
    }

    private void addFoundGeoCodesForUser1WinterSchool() {
        setUser(userWithPoints1ID, "userWithPoints1", false);
        joinEvents(userWithPoints1ID);

        //this User finds 2 GeoCodes
        swapCollectablesAtPosition(winterSchoolGeoCode1ID, 1);
        swapCollectablesAtPosition(winterSchoolGeoCode3ID, 1);
    }

    private void addFoundGeoCodesForUser2WinterSchool() {
        setUser(userWithPoints2ID, "userWithPoints2", false);
        joinEvents(userWithPoints2ID);

        //this User finds all 3 GeoCodes
        swapCollectablesAtPosition(winterSchoolGeoCode1ID, 1);
        swapCollectablesAtPosition(winterSchoolGeoCode3ID, 1);
        swapCollectablesAtPosition(winterSchoolGeoCode2ID, 1);
    }

    private void swapCollectablesAtPosition(UUID geocodeID, int position) {
        var collectables = getCollectables(geocodeID);

        Assertions.assertNotNull(collectables);
        swapCollectables(geocodeID, collectables.get(position-1));
    }

    private void createWinterSchoolEvent(){
        winterSchoolEvent = true;

        //create GeoCodes
        setUser(validUserId, "validUser", false);

        addWinterSchoolGeoCodes();

        //create the Event
        List<CreateGeoCodeRequest> winterSchoolCreateGeoCodeRequests = new ArrayList<>();
        winterSchoolCreateGeoCodeRequests.add(createWinterSchoolGeoCode1Request);
        winterSchoolCreateGeoCodeRequests.add(createWinterSchoolGeoCode2Request);
        winterSchoolCreateGeoCodeRequests.add(createWinterSchoolGeoCode3Request);

        try {
            winterSchoolEventID = createEvent(winterSchoolEventName, winterSchoolEventName, new GeoPoint(0.0, 0.0),
                    LocalDate.now(), LocalDate.now().plusDays(3), winterSchoolCreateGeoCodeRequests, OrderLevels.DIFFICULTY, new HashMap<>());
        } catch (tech.geocodeapp.geocode.event.exceptions.InvalidRequestException e) {
            e.printStackTrace();
        }
    }

    /**
     * checks if the details for a Collectable's Mission are correct
     * @param missionID id of the Mission that the Collectable holds
     * @param collectableType the type of Collectable that the Collectable in question is in
     * @param missions the User's Missions
     */
    private void checkCollectableMission(UUID missionID, CollectableType collectableType, Set<Mission> missions) {
        var shouldHaveMission = collectableType.getProperties().containsKey("missionType");

        //only check details if the Collectable actually has a Mission
        if(missionID != null){
            if(!shouldHaveMission){
                Assertions.fail("Mission ID present for a Collectable that should not have a Mission");
                return;
            }

            var missionType = MissionType.fromValue(collectableType.getProperties().get("missionType"));

            for(var mission : missions){
                System.out.println(mission.getType());
            }

            //check the details
            Assertions.assertTrue(missions.stream().anyMatch(mission ->
                    mission != null &&
                    mission.getId().equals(missionID) &&
                    mission.getType().equals(missionType)
            ));
        }else if(shouldHaveMission){
            Assertions.fail("Mission ID not present for a Collectable that should have a Mission");
        }
    }

    /**
     * checks that the Leaderboard details returned by User.getMyLeaderboardDetails are correct
     * @param user The User
     * @param eventNames The names of the Events
     * @param correctRankings The rankings that the User should be given based off timing and GeoCode difficulty
     * (whichever is relevant to the Event)
     */
    private void checkUserLeaderboardDetails(User user, List<String> eventNames, List<Integer> correctRankings){
        var userID = user.getId();
        setUser(userID, user.getUsername(), false);

        var getMyLeaderboardsRequest = new GetMyLeaderboardsRequest(userID);

        try {
            var getMyLeaderboardsResponse = userService.getMyLeaderboards(getMyLeaderboardsRequest);

            Assertions.assertTrue(getMyLeaderboardsResponse.isSuccess());
            Assertions.assertEquals(successGetMyLeaderboardsMessage, getMyLeaderboardsResponse.getMessage());

            var userLeaderboardDetails = getMyLeaderboardsResponse.getLeaderboards();

            /* check the user has points */
            Assertions.assertFalse(userLeaderboardDetails.isEmpty());

            /* check that the correct details are returned */
            for(var i = 0; i < eventNames.size(); ++i){
                var finalI = i;

                Assertions.assertTrue(userLeaderboardDetails.stream().anyMatch(details ->
                        details.getName().equals(eventNames.get(finalI)) &&
                        details.getRank() == correctRankings.get(finalI)
                ));
            }
        } catch (NullRequestParameterException e) {
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
    public void getCurrentCollectableTestValidUser() {
        validUserId = handleUserLogin("validUser");;

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
    public void getUserTrackableTestInvalidUser() {
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
    public void getUserTrackableTestValidUser() {
        validUserId = handleUserLogin("validUser");;

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
    @Transactional
    void getFoundCollectableTypesTestValidUser() {
        try{
            addFirstTwoGeoCodes();
            addFoundGeoCodesForUser1();

            /*
             Create a request object
             and assign values to it
           */
            setUser(userWithPoints1ID, "userWithPoints1", false);

            var request = new GetFoundCollectableTypesRequest(userWithPoints1ID);

            var response = userService.getFoundCollectableTypes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The IDs of the User's found CollectableTypes was successfully returned", response.getMessage());

            var foundCollectableTypeIDs = response.getCollectableTypeIDs();

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
        validUserId = handleUserLogin("validUser");;

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
    @Transactional
    void getFoundGeoCodesTestValidUser() {
        try{
            addFirstTwoGeoCodes();
            addFoundGeoCodesForUser1();

            /*
             Create a request object
             and assign values to it
           */
            setUser(userWithPoints1ID, "userWithPoints1", false);

            var request = new GetFoundGeoCodesRequest(userWithPoints1ID);

            var response = userService.getFoundGeoCodes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The IDs of the User's found GeoCodes was successfully returned", response.getMessage());

            var foundGeoCodeIDs = response.getGeocodeIDs();

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
    @Transactional
    void getOwnedGeoCodesTestValidUser() {
        try{
            addFirstTwoGeoCodes();

            /*
             Create a request object
             and assign values to it
           */
            setUser(validUserId, "validUser", false);

            var request = new GetOwnedGeoCodesRequest();
            request.setUserID(validUserId);

            var response = userService.getOwnedGeoCodes(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The IDs of the User's owned GeoCodes was successfully returned", response.getMessage());

            var ownedGeoCodeIDs = response.getGeocodeIDs();

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
    @Transactional
    public void updateLocationTestValidUser() {
        validUserId = handleUserLogin("validUser");;

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

            List<GeoPoint> pastLocations = new ArrayList<>(trackableObject.getPastLocations());

            Assertions.assertEquals(location, pastLocations.get(pastLocations.size()-1));
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Transactional
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
    @Transactional
    void getMyLeaderboardsTestUserWithNoPoints(){
        UUID noPointsUserId = handleUserLogin("noPointsUserId");

        var request = new GetMyLeaderboardsRequest();
        request.setUserID(noPointsUserId);

        try {
            var response = userService.getMyLeaderboards(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals(successGetMyLeaderboardsMessage, response.getMessage());
            Assertions.assertTrue(response.getLeaderboards().isEmpty());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Transactional
    public void getMyLeaderboardsTestUsersWithPoints() throws NullRequestParameterException, InvalidRequestException {
        createCollectableTypes();

        createOpenDayEvent();
        addFoundGeoCodesForUser1();
        addFoundGeoCodesForUser2();

        createWinterSchoolEvent();
        addFoundGeoCodesForUser1WinterSchool();
        addFoundGeoCodesForUser2WinterSchool();

        //user1
        var eventNames = new ArrayList<>(Arrays.asList(
           openDayEventName,
           winterSchoolEventName
        ));

        var user1CorrectRankings = new ArrayList<>(Arrays.asList(
                1,
                2
        ));

        checkUserLeaderboardDetails(userWithPoints1, eventNames, user1CorrectRankings);

        var user2CorrectRankings = new ArrayList<>(Arrays.asList(
                2,
                1
        ));

        //user2
        checkUserLeaderboardDetails(userWithPoints2, eventNames, user2CorrectRankings);
    }

    @Test
    @Transactional
    public void AddToOwnedGeoCodesTestNotAddDuplicate() throws InvalidRequestException {
        try {
            addFirstTwoGeoCodes();
            
            var response = addToOwnedGeoCodes(validUserId, secondGeoCodeID);

            Assertions.assertNotNull(response);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("GeoCode added to the owned GeoCodes", response.getMessage());

            var getOwnedGeoCodesResponse = getOwnedGeoCodes(validUserId);

            Assertions.assertNotNull(getOwnedGeoCodesResponse);
            Assertions.assertTrue(getOwnedGeoCodesResponse.isSuccess());

            var ownedGeoCodeIDs = getOwnedGeoCodesResponse.getGeocodeIDs();
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

            var response = addToOwnedGeoCodes(validUserId, thirdGeoCodeID);

            Assertions.assertNotNull(response);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("GeoCode added to the owned GeoCodes", response.getMessage());

            //check that the number of owned GeoCodes has increased by 1
            var getOwnedGeoCodesResponse = getOwnedGeoCodes(validUserId);

            Assertions.assertNotNull(getOwnedGeoCodesResponse);
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

            var getFoundGeoCodesResponse = getFoundGeoCodes(userWithPoints1ID);

            Assertions.assertNotNull(getFoundGeoCodesResponse);

            var foundGeoCodeIDs = getFoundGeoCodesResponse.getGeocodeIDs();

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
        setUser(validUserId, "validUser", false);

        thirdGeoCodeID = createGeoCode("3", new GeoPoint(0.0, 0.0), Difficulty.EASY);

        var thirdCollectables = getCollectables(thirdGeoCodeID);

        setUser(userWithPoints1ID, "userWithPoints1", false);

        Assertions.assertNotNull(thirdCollectables);

        var collectableID = thirdCollectables.get(0);
        swapCollectables(thirdGeoCodeID, collectableID);

        //check that the number of owned GeoCodes has increased by 1
        var getFoundGeoCodesResponse = getFoundGeoCodes(userWithPoints1ID);

        Assertions.assertNotNull(getFoundGeoCodesResponse);
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

        var newCollectableTypeID = createCollectableType("new type", "img_new_type", Rarity.COMMON, christmasSetId, new HashMap<>());
        createCollectable(newCollectableTypeID, false, new GeoPoint(0.0, 0.0));

        var response = addToFoundCollectableTypes(userWithPoints1ID, firstFoundCollectableTypeID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals("CollectableType added to the found CollectableTypes", response.getMessage());

        var getFoundCollectableTypesResponse = getFoundCollectableTypes(userWithPoints1);

        Assertions.assertNotNull(getFoundCollectableTypesResponse);
        Assertions.assertTrue(getFoundCollectableTypesResponse.isSuccess());

        var foundCollectableTypeIDs = getFoundCollectableTypesResponse.getCollectableTypeIDs();

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

        //find the third (or second if the first 2 types are the same) ID that we have not found yet
        for(var type: collectableTypes){
            if(!type.getId().equals(firstFoundCollectableTypeID) && !type.getId().equals(secondFoundCollectableTypeID)){
                thirdCollectableTypeID = type.getId();
                break;
            }
        }

        var addToFoundCollectableTypesResponse = addToFoundCollectableTypes(userWithPoints1ID, thirdCollectableTypeID);

        Assertions.assertNotNull(addToFoundCollectableTypesResponse);
        Assertions.assertTrue(addToFoundCollectableTypesResponse.isSuccess());
        Assertions.assertEquals("CollectableType added to the found CollectableTypes", addToFoundCollectableTypesResponse.getMessage());

        //check that the number of owned CollectableTypes has increased by 1
        var getFoundCollectableTypesResponse = getFoundCollectableTypes(userWithPoints1);

        Assertions.assertNotNull(getFoundCollectableTypesResponse);
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
            var request = new GetUserByIdRequest(invalidUserId);
            var response = userService.getUserById(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals(invalidUserIdMessage, response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void getUserByIdTestValidUserId(){
        validUserId = handleUserLogin("validUser");;

        try {
            var request = new GetUserByIdRequest(validUserId);
            var response = userService.getUserById(request);
            var user = response.getUser();

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
    public void handleLoginTestExistingUserId(){
        validUserId = handleUserLogin("validUser");;

        try {
            var request = new HandleLoginRequest(new GeoPoint(0.0, 0.0));
            var response = userService.handleLogin(request);

            Assertions.assertFalse(response.isSuccess());

            var existingUserIdMessage = "User ID already exists";

            Assertions.assertEquals(existingUserIdMessage, response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void handleLoginTestNewUserId(){
        try {
            var newUsername = "bob";
            var request = new HandleLoginRequest(new GeoPoint(0.0, 0.0));
            var response = userService.handleLogin(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("New User registered", response.getMessage());

            var getUserByIdRequest = new GetUserByIdRequest(newUserId);
            var getUserByIdResponse = userService.getUserById(getUserByIdRequest);

            Assertions.assertTrue(getUserByIdResponse.isSuccess());

            var user = getUserByIdResponse.getUser();

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
            var newCurrentCollectableID = firstCollectables.get(2);
            var response = swapCollectable(validUserId, newCurrentCollectableID, firstGeoCodeID);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The User's Collectable was swapped with the Collectable in the GeoCode", response.getMessage());

            var collectable = response.getCollectable();
            Assertions.assertNotNull(collectable);

            var getCurrentCollectableRequest = new GetCurrentCollectableRequest();
            getCurrentCollectableRequest.setUserID(validUserId);

            var getCurrentCollectableResponse = userService.getCurrentCollectable(getCurrentCollectableRequest);

            Assertions.assertTrue(getCurrentCollectableResponse.isSuccess());

            //test that the User's Collectable is now the swapped out Collectable
            var currentCollectable = getCurrentCollectableResponse.getCollectable();

            Assertions.assertEquals(newCurrentCollectableID, currentCollectable.getId());
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Transactional
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
    @Transactional
    void getMyMissionsTestValidUser() throws NullRequestParameterException, InvalidRequestException {
        createCollectableTypes();
        addFirstTwoGeoCodes();
        addFoundGeoCodesForUser1();

        setUser(userWithPoints1ID, "userWithPoints1", false);
        var request = new GetMyMissionsRequest(userWithPoints1ID);

        try {
            var response = userService.getMyMissions(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("User Missions returned", response.getMessage());

            var missions = response.getMissions();

            Assertions.assertNotNull(missions);

            var firstFoundCollectable = getCollectableByID(firstFoundCollectableID);
            var secondFoundCollectable = getCollectableByID(secondFoundCollectableID);

            //check each found Collectable's Mission
            checkCollectableMission(firstFoundCollectable.getMissionID(), firstFoundCollectableType, missions);
            checkCollectableMission(secondFoundCollectable.getMissionID(), secondFoundCollectableType, missions);
        } catch (NullRequestParameterException e) {
            Assertions.fail(e.getMessage());
        }
    }
}
