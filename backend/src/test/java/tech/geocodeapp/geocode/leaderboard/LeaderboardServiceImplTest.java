package tech.geocodeapp.geocode.leaderboard;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.request.*;
import tech.geocodeapp.geocode.leaderboard.response.CreateLeaderboardResponse;
import tech.geocodeapp.geocode.leaderboard.response.DeletePointResponse;
import tech.geocodeapp.geocode.leaderboard.response.GetEventLeaderboardResponse;
import tech.geocodeapp.geocode.leaderboard.response.PointResponse;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardServiceImpl;
import tech.geocodeapp.geocode.user.repository.UserRepository;
import tech.geocodeapp.geocode.user.request.GetUserByIdRequest;
import tech.geocodeapp.geocode.user.request.RegisterNewUserRequest;
import tech.geocodeapp.geocode.user.request.UpdateLocationRequest;
import tech.geocodeapp.geocode.user.response.RegisterNewUserResponse;
import tech.geocodeapp.geocode.user.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith( MockitoExtension.class )
public class LeaderboardServiceImplTest {
    private LeaderboardService leaderboardService;
    private UserService userService;
    private UserRepository userRepository;
    private LeaderboardMockRepository leaderboardMockRepo;
    private PointMockRepository pointMockRepository;

    private final String hatfieldEaster = "Hatfield Easter Hunt 2021";
    private final String menloParkChristmas = "Christmas 2021 market";

    @BeforeEach
    void setup() {
        leaderboardMockRepo = new LeaderboardMockRepository();
        userRepository = new UserMockRepository();
        userService = new UserMockService(userRepository);
        pointMockRepository = new PointMockRepository();

        leaderboardService = new LeaderboardServiceImpl(leaderboardMockRepo, pointMockRepository, null, userService);

        /* create a Leaderboard so that can test for uniqueness of names */
        Leaderboard leaderboard1 = new Leaderboard(hatfieldEaster);
        leaderboardMockRepo.save(leaderboard1);
    }

    @AfterEach
    void clear() {
        userRepository.deleteAll();
        leaderboardMockRepo.deleteAll();
        pointMockRepository.deleteAll();
    }

    @Test
    public void createLeaderboardTestNullRequest(){
        try{
            CreateLeaderboardResponse response = leaderboardService.createLeaderboard(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The CreateLeaderboardRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getLeaderboard());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void createLeaderboardTestNullName(){
        CreateLeaderboardRequest request = new CreateLeaderboardRequest(null);

        assertThatThrownBy(() -> leaderboardService.createLeaderboard(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    public void createLeaderboardTestExistingName(){
        CreateLeaderboardRequest request = new CreateLeaderboardRequest(hatfieldEaster);

        try {
            CreateLeaderboardResponse response = leaderboardService.createLeaderboard(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("A Leaderboard already exists with that name", response.getMessage());
            Assertions.assertNull(response.getLeaderboard());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createLeaderboardTestNewName(){
        CreateLeaderboardRequest request = new CreateLeaderboardRequest(menloParkChristmas);

        try {
            CreateLeaderboardResponse response = leaderboardService.createLeaderboard(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The Leaderboard was successfully created", response.getMessage());

            Leaderboard christmasLeaderboard = response.getLeaderboard();

            Assertions.assertNotNull(christmasLeaderboard);
            Assertions.assertEquals(menloParkChristmas, christmasLeaderboard.getName());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test to see that when a null request is received it is handled correctly
     */
    @Test
    public void createPointTestNullRequest() {
        try {
            PointResponse response = leaderboardService.createPoint(null);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The CreatePointRequest passed was NULL", response.getMessage());
            Assertions.assertNull(response.getPoint());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that the correct exception is thrown when the amount parameter is null
     */
    @Test
    public void createPointTestNullAmount() {
        CreatePointRequest request = new CreatePointRequest(null, UUID.randomUUID(), UUID.randomUUID());

        assertThatThrownBy(() -> leaderboardService.createPoint(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    /**
     * Test that the correct exception is thrown when the userId parameter is null
     */
    @Test
    public void createPointTestNullUserId() {
        CreatePointRequest request = new CreatePointRequest(1,null, UUID.randomUUID());

        assertThatThrownBy(() -> leaderboardService.createPoint(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    /**
     * Test that the correct exception is thrown when the leaderboardId parameter is null
     */
    @Test
    public void createPointTestNullLeaderboardId() {
        CreatePointRequest request = new CreatePointRequest(1, UUID.randomUUID(), null);

        assertThatThrownBy(() -> leaderboardService.createPoint(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    /**
     * Test that the correct exception is thrown when all parameters are null
     */
    @Test
    public void createPointTestNullAllParameters() {
        CreatePointRequest request = new CreatePointRequest(null, null, null);

        assertThatThrownBy(() -> leaderboardService.createPoint(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    /**
     * Test that the response object is created correctly when an invalid leaderboardId is provided
     */
    @Test
    public void createPointTestInvalidLeaderboardId() {
        CreatePointRequest request = new CreatePointRequest(1, UUID.randomUUID(), UUID.randomUUID());
        try {
            PointResponse response = leaderboardService.createPoint(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Invalid leaderboard Id provided", response.getMessage());
            Assertions.assertNull(response.getPoint());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that the correct response object is created when an invalid userId is provided
     */
    @Test
    public void createPointTestInvalidUserId() {
        //create a leaderboard to ensure a valid id for leaderboardId parameter
        CreateLeaderboardRequest leaderboardRequest = new CreateLeaderboardRequest("test");
        try {
            CreateLeaderboardResponse leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);
            CreatePointRequest request = new CreatePointRequest(1, UUID.randomUUID(), leaderboardResponse.getLeaderboard().getId());
            PointResponse response = leaderboardService.createPoint(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Invalid user Id provided", response.getMessage());
            Assertions.assertNull(response.getPoint());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that a point is created correctly when valid parameters are provided
     */
    @Test
    public void createPointTestValidRequestParameters() {
        CreateLeaderboardRequest leaderboardRequest = new CreateLeaderboardRequest("testValid");
        try {
            CreateLeaderboardResponse leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);
            UUID userId = UUID.randomUUID();
            RegisterNewUserRequest userRequest = new RegisterNewUserRequest(userId, "Test user");
            RegisterNewUserResponse userResponse = userService.registerNewUser(userRequest);
            CreatePointRequest request = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            PointResponse response = leaderboardService.createPoint(request);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The Point was successfully created.", response.getMessage());
            Assertions.assertNotNull(response.getPoint());
            Assertions.assertEquals(1, response.getPoint().getAmount());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that the correct response is returned when the request is null
     */
    @Test
    public void deletePointTestNullRequest() {
        try {
            DeletePointResponse response = leaderboardService.deletePoint(null);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The DeletePointRequest passed was NULL", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that the correct exception is thrown when the request has a null pointId parameter
     */
    @Test
    public void deletePointTestNullPointId() {
        DeletePointRequest request = new DeletePointRequest(null);
        assertThatThrownBy(() -> leaderboardService.deletePoint(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    /**
     * Test that the correct response is returned when an invalid pointId is sent
     */
    @Test
    public void deletePointTestInvalidPointId() {
        DeletePointRequest request = new DeletePointRequest(UUID.randomUUID());
        try {
            DeletePointResponse response = leaderboardService.deletePoint(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("No Point with the given Id exists", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that when give a valid pointId that it is deleted and the response is properly returned
     */
    @Test
    public void deletePointTestValidPointId() {
        CreateLeaderboardRequest leaderboardRequest = new CreateLeaderboardRequest("test delete");
        try {
            CreateLeaderboardResponse leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);
            UUID userId = UUID.randomUUID();
            RegisterNewUserRequest userRequest = new RegisterNewUserRequest(userId, "delete point");
            RegisterNewUserResponse userResponse = userService.registerNewUser(userRequest);
            CreatePointRequest createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            PointResponse createPointResponse = leaderboardService.createPoint(createPointRequest);
            DeletePointRequest request = new DeletePointRequest(createPointResponse.getPoint().getId());
            DeletePointResponse response = leaderboardService.deletePoint(request);
            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Successfully deleted the provided point", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test to check that a null request is correctly handled
     */
    @Test
    public void updatePointTestNullRequest() {
        try {
            PointResponse response = leaderboardService.updatePoint(null);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The UpdatePointRequest passed was NULL", response.getMessage());
            Assertions.assertNull(response.getPoint());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that a request with a null value for pointId throws the correct exception
     */
    @Test
    public void updatePointTestNullPointId() {
        UpdatePointRequest request = new UpdatePointRequest(null, null, null, null);
        assertThatThrownBy(() -> leaderboardService.updatePoint(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    /**
     * Test that the correct response is given when all optional parameters are null
     */
    @Test
    public void updatePointTestAllOptionalParametersNull() {
        UpdatePointRequest request = new UpdatePointRequest(UUID.randomUUID(), null, null, null);
        try {
            PointResponse response = leaderboardService.updatePoint(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Please provide a value for at least one optional value to update a Point", response.getMessage());
            Assertions.assertNull(response.getPoint());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check that the method correctly handles being given a pointId that does not exist
     */
    @Test
    public void updatePointTestInvalidPointId() {
        UpdatePointRequest request  = new UpdatePointRequest(UUID.randomUUID(), 1, null, null);
        try {
            PointResponse response = leaderboardService.updatePoint(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("No point with the provided Id exists", response.getMessage());
            Assertions.assertNull(response.getPoint());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that the amount of a point is updated correctly when it is the only field to be updated
     */
    @Test
    public void updatePointTestOnlyAmountUpdated() {
        CreateLeaderboardRequest leaderboardRequest = new CreateLeaderboardRequest("update amount");
        try {
            CreateLeaderboardResponse leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);
            UUID userId = UUID.randomUUID();
            RegisterNewUserRequest userRequest = new RegisterNewUserRequest(userId, "Test user");
            RegisterNewUserResponse userResponse = userService.registerNewUser(userRequest);
            CreatePointRequest createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            PointResponse createPointResponse = leaderboardService.createPoint(createPointRequest);
            UpdatePointRequest request = new UpdatePointRequest(createPointResponse.getPoint().getId(), 2, null, null);
            PointResponse response = leaderboardService.updatePoint(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Updated point successfully", response.getMessage());
            Assertions.assertNotNull(response.getPoint());
            Assertions.assertEquals(2, response.getPoint().getAmount());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that the correct response is given when a leaderboardId to update to is invalid
     */
    @Test
    public void updatePointTestInvalidLeaderboardId() {
        CreateLeaderboardRequest leaderboardRequest = new CreateLeaderboardRequest("update leaderboard invalid");
        try {
            CreateLeaderboardResponse leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);
            UUID userId = UUID.randomUUID();
            RegisterNewUserRequest userRequest = new RegisterNewUserRequest(userId, "Test user");
            RegisterNewUserResponse userResponse = userService.registerNewUser(userRequest);
            CreatePointRequest createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            PointResponse createPointResponse = leaderboardService.createPoint(createPointRequest);
            UpdatePointRequest request = new UpdatePointRequest(createPointResponse.getPoint().getId(), null, null, UUID.randomUUID());
            PointResponse response = leaderboardService.updatePoint(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Invalid LeaderboardId to update to was provided", response.getMessage());
            Assertions.assertNull(response.getPoint());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests if a point is correctly updated when only a valid leaderboardId is provided to update it with
     */
    @Test
    public void updatePointTestOnlyLeaderboardId() {
        CreateLeaderboardRequest leaderboardRequest = new CreateLeaderboardRequest("test");
        try {
            CreateLeaderboardResponse leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);
            UUID userId = UUID.randomUUID();
            RegisterNewUserRequest userRequest = new RegisterNewUserRequest(userId, "Test user");
            RegisterNewUserResponse userResponse = userService.registerNewUser(userRequest);
            CreatePointRequest createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            PointResponse createPointResponse = leaderboardService.createPoint(createPointRequest);

            CreateLeaderboardRequest updatedLeaderboardRequest = new CreateLeaderboardRequest("updated leaderboard");
            CreateLeaderboardResponse updatedLeaderboardResponse = leaderboardService.createLeaderboard(updatedLeaderboardRequest);
            UpdatePointRequest request = new UpdatePointRequest(createPointResponse.getPoint().getId(), null, null, updatedLeaderboardResponse.getLeaderboard().getId());
            PointResponse response = leaderboardService.updatePoint(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Updated point successfully", response.getMessage());
            Assertions.assertNotNull(response.getPoint());
            Assertions.assertEquals(updatedLeaderboardResponse.getLeaderboard(), response.getPoint().getLeaderBoard());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that the correct response is given when an invalid userId is provided
     */
    @Test
    public void updatePointTestInvalidUserId() {
        CreateLeaderboardRequest leaderboardRequest = new CreateLeaderboardRequest("test");
        try {
            CreateLeaderboardResponse leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);
            UUID userId = UUID.randomUUID();
            RegisterNewUserRequest userRequest = new RegisterNewUserRequest(userId, "test user");
            RegisterNewUserResponse userResponse = userService.registerNewUser(userRequest);
            CreatePointRequest createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            PointResponse pointResponse = leaderboardService.createPoint(createPointRequest);

            UpdatePointRequest request = new UpdatePointRequest(pointResponse.getPoint().getId(), null, UUID.randomUUID(), null);
            PointResponse response = leaderboardService.updatePoint(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Invalid UserId to update to was provided", response.getMessage());
            Assertions.assertNull(response.getPoint());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests if a point is correctly updated when only a valid userId is provided to update it with
     */
    @Test
    public void updatePointTestOnlyUserId() {
        CreateLeaderboardRequest leaderboardRequest = new CreateLeaderboardRequest("test");
        try {
            CreateLeaderboardResponse leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);
            UUID userId = UUID.randomUUID();
            RegisterNewUserRequest userRequest = new RegisterNewUserRequest(userId, "test user");
            userService.registerNewUser(userRequest);
            CreatePointRequest createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            PointResponse pointResponse = leaderboardService.createPoint(createPointRequest);

            UUID updatedUserId = UUID.randomUUID();
            RegisterNewUserRequest updatedUserRequest = new RegisterNewUserRequest(updatedUserId, "updated user");
            userService.registerNewUser(updatedUserRequest);
            UpdatePointRequest request = new UpdatePointRequest(pointResponse.getPoint().getId(), null, updatedUserId, null);
            PointResponse response = leaderboardService.updatePoint(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Updated point successfully", response.getMessage());
            Assertions.assertNotNull(response.getPoint());
            Assertions.assertEquals(userService.getUserById(new GetUserByIdRequest(updatedUserId)).getUser(), response.getPoint().getUser());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that when given a valid userId and amount that a point is correctly updated with both
     */
    @Test
    public void updatePointTestValidAmountAndUserId() {
        CreateLeaderboardRequest leaderboardRequest = new CreateLeaderboardRequest("test");
        try {
            CreateLeaderboardResponse leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);
            UUID userId = UUID.randomUUID();
            RegisterNewUserRequest userRequest = new RegisterNewUserRequest(userId, "test user");
            userService.registerNewUser(userRequest);
            CreatePointRequest createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            PointResponse pointResponse = leaderboardService.createPoint(createPointRequest);

            UUID updatedUserId = UUID.randomUUID();
            RegisterNewUserRequest updatedUserRequest = new RegisterNewUserRequest(updatedUserId, "updated user");
            userService.registerNewUser(updatedUserRequest);
            UpdatePointRequest request = new UpdatePointRequest(pointResponse.getPoint().getId(), 3, updatedUserId, null);
            PointResponse response = leaderboardService.updatePoint(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Updated point successfully", response.getMessage());
            Assertions.assertNotNull(response.getPoint());
            Assertions.assertEquals(userService.getUserById(new GetUserByIdRequest(updatedUserId)).getUser(), response.getPoint().getUser());
            Assertions.assertEquals(3, response.getPoint().getAmount());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that when given a valid leaderboardId and amount that a point is correctly updated with both
     */
    @Test
    public void updatePointTestValidAmountAndLeaderboardId() {
        CreateLeaderboardRequest leaderboardRequest = new CreateLeaderboardRequest("test");
        try {
            CreateLeaderboardResponse leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);
            UUID userId = UUID.randomUUID();
            RegisterNewUserRequest userRequest = new RegisterNewUserRequest(userId, "Test user");
            RegisterNewUserResponse userResponse = userService.registerNewUser(userRequest);
            CreatePointRequest createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            PointResponse createPointResponse = leaderboardService.createPoint(createPointRequest);

            CreateLeaderboardRequest updatedLeaderboardRequest = new CreateLeaderboardRequest("updated leaderboard");
            CreateLeaderboardResponse updatedLeaderboardResponse = leaderboardService.createLeaderboard(updatedLeaderboardRequest);
            UpdatePointRequest request = new UpdatePointRequest(createPointResponse.getPoint().getId(), 4, null, updatedLeaderboardResponse.getLeaderboard().getId());
            PointResponse response = leaderboardService.updatePoint(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Updated point successfully", response.getMessage());
            Assertions.assertNotNull(response.getPoint());
            Assertions.assertEquals(updatedLeaderboardResponse.getLeaderboard(), response.getPoint().getLeaderBoard());
            Assertions.assertEquals(4, response.getPoint().getAmount());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that when given a valid leaderboardId and userId that a point is correctly updated with both
     */
    @Test
    public void updatePointTestValidUserIdAndLeaderboardId() {
        CreateLeaderboardRequest leaderboardRequest = new CreateLeaderboardRequest("test");
        try {
            CreateLeaderboardResponse leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);
            UUID userId = UUID.randomUUID();
            RegisterNewUserRequest userRequest = new RegisterNewUserRequest(userId, "test user");
            userService.registerNewUser(userRequest);
            CreatePointRequest createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            PointResponse pointResponse = leaderboardService.createPoint(createPointRequest);

            UUID updatedUserId = UUID.randomUUID();
            RegisterNewUserRequest updatedUserRequest = new RegisterNewUserRequest(updatedUserId, "updated user");
            userService.registerNewUser(updatedUserRequest);

            CreateLeaderboardRequest updatedLeaderboardRequest = new CreateLeaderboardRequest("updated leaderboard");
            CreateLeaderboardResponse updatedLeaderboardResponse = leaderboardService.createLeaderboard(updatedLeaderboardRequest);

            UpdatePointRequest request = new UpdatePointRequest(pointResponse.getPoint().getId(), null, updatedUserId, updatedLeaderboardResponse.getLeaderboard().getId());
            PointResponse response = leaderboardService.updatePoint(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Updated point successfully", response.getMessage());
            Assertions.assertNotNull(response.getPoint());
            Assertions.assertEquals(userService.getUserById(new GetUserByIdRequest(updatedUserId)).getUser(), response.getPoint().getUser());
            Assertions.assertEquals(updatedLeaderboardResponse.getLeaderboard(), response.getPoint().getLeaderBoard());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that when provided with a valid leaderboardId, userId and amount that a point is correctly updated with them
     */
    @Test
    public void updatePointTestAllOptionalFieldsValid() {
        CreateLeaderboardRequest leaderboardRequest = new CreateLeaderboardRequest("test");
        try {
            CreateLeaderboardResponse leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);
            UUID userId = UUID.randomUUID();
            RegisterNewUserRequest userRequest = new RegisterNewUserRequest(userId, "test user");
            userService.registerNewUser(userRequest);
            CreatePointRequest createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            PointResponse pointResponse = leaderboardService.createPoint(createPointRequest);

            UUID updatedUserId = UUID.randomUUID();
            RegisterNewUserRequest updatedUserRequest = new RegisterNewUserRequest(updatedUserId, "updated user");
            userService.registerNewUser(updatedUserRequest);

            CreateLeaderboardRequest updatedLeaderboardRequest = new CreateLeaderboardRequest("updated leaderboard");
            CreateLeaderboardResponse updatedLeaderboardResponse = leaderboardService.createLeaderboard(updatedLeaderboardRequest);

            UpdatePointRequest request = new UpdatePointRequest(pointResponse.getPoint().getId(), 2, updatedUserId, updatedLeaderboardResponse.getLeaderboard().getId());
            PointResponse response = leaderboardService.updatePoint(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Updated point successfully", response.getMessage());
            Assertions.assertNotNull(response.getPoint());
            Assertions.assertEquals(userService.getUserById(new GetUserByIdRequest(updatedUserId)).getUser(), response.getPoint().getUser());
            Assertions.assertEquals(updatedLeaderboardResponse.getLeaderboard(), response.getPoint().getLeaderBoard());
            Assertions.assertEquals(2, response.getPoint().getAmount());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests that a null request is handled correctly
     */
    @Test
    public void getEventLeaderboardTestNullRequest() {
        try {
            GetEventLeaderboardResponse response = leaderboardService.getEventLeaderboard(null);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetEventLeaderboardRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getLeaderboard());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that the correct exception is thrown when all request parameters are set to null
     */
    @Test
    public void getEventLeaderboardTestNullRequestParameters() {
        GetEventLeaderboardRequest request = new GetEventLeaderboardRequest(null, null, null);
        assertThatThrownBy(() -> leaderboardService.getEventLeaderboard(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    /**
     * Test that when the leaderboardId provided in the request is null that the correct exception is thrown
     */
    @Test
    public void getEventLeaderboardTestNullLeaderboardId() {
        GetEventLeaderboardRequest request = new GetEventLeaderboardRequest(null, 1, 2);
        assertThatThrownBy(() -> leaderboardService.getEventLeaderboard(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    /**
     * Test that when the starting position provided in the request is null that the correct exception is thrown
     */
    @Test
    public void getEventLeaderboardTestNullStarting() {
        GetEventLeaderboardRequest request = new GetEventLeaderboardRequest(UUID.randomUUID(), null, 4);
        assertThatThrownBy(() -> leaderboardService.getEventLeaderboard(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    /**
     * Test that when the count provided in the request is null that the correct exception is thrown
     */
    @Test
    public void getEventLeaderboardTestNullCount() {
        GetEventLeaderboardRequest request = new GetEventLeaderboardRequest(UUID.randomUUID(), 1, null);
        assertThatThrownBy(() -> leaderboardService.getEventLeaderboard(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    /**
     * Test that if the starting position given in the request is below the minimum value allowed of 1 that the correct response object is returned
     */
    @Test
    public void getEventLeaderboardTestStartingLowerThanMinimumValue() {
        GetEventLeaderboardRequest request = new GetEventLeaderboardRequest(UUID.randomUUID(), 0, 2);
        try {
            GetEventLeaderboardResponse response = leaderboardService.getEventLeaderboard(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Starting is lower than the minimum value allowed", response.getMessage());
            Assertions.assertTrue(response.getLeaderboard().isEmpty());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that if the count provided in the request is lower than the minimum allowed value that the correct response is returned
     */
    @Test
    public void getEventLeaderboardTestCountLowerThanMinimumValue() {
        GetEventLeaderboardRequest request = new GetEventLeaderboardRequest(UUID.randomUUID(), 1, 0);
        try {
            GetEventLeaderboardResponse response = leaderboardService.getEventLeaderboard(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Count is lower than the minimum value allowed", response.getMessage());
            Assertions.assertTrue(response.getLeaderboard().isEmpty());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getEventLeaderboardTestInvalidLeaderboardId() {
        GetEventLeaderboardRequest request = new GetEventLeaderboardRequest(UUID.randomUUID(), 1, 1);
        try {
            GetEventLeaderboardResponse response = leaderboardService.getEventLeaderboard(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("No leaderboard exists for the provided leaderboardId", response.getMessage());
            Assertions.assertTrue(response.getLeaderboard().isEmpty());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that the correct response is returned when the starting parameter of a request is greater than the total
     * number of points in the leaderboard
     */
    @Test
    public void getEventLeaderboardTestStartingGreaterThanPointsInLeaderboard() {
        CreateLeaderboardRequest leaderboardRequest = new CreateLeaderboardRequest("test");
        try {
            CreateLeaderboardResponse leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);
            UUID userId = UUID.randomUUID();
            RegisterNewUserRequest userRequest = new RegisterNewUserRequest(userId, "Test user");
            RegisterNewUserResponse userResponse = userService.registerNewUser(userRequest);
            CreatePointRequest pointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            leaderboardService.createPoint(pointRequest);

            GetEventLeaderboardRequest request = new GetEventLeaderboardRequest(leaderboardResponse.getLeaderboard().getId(), 2, 1);
            GetEventLeaderboardResponse response = leaderboardService.getEventLeaderboard(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Starting is greater than the number of points in the leaderboard", response.getMessage());
            Assertions.assertTrue(response.getLeaderboard().isEmpty());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that when starting starts at the first point in a leaderboard and count is equal to the number of points
     * in the leaderboard that all points are correctly included in the response and are correctly ranked
     */
    @Test
    public void getEventLeaderboardTestResponseWithAllPointsInLeaderboard() {
        CreateLeaderboardRequest leaderboardRequest = new CreateLeaderboardRequest("test");
        try {
            CreateLeaderboardResponse leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);

            //Create three users to use
            List<UUID> userIds = new ArrayList<UUID>();
            for (int i = 0; i < 3; i++) {
                userIds.add(UUID.randomUUID());
            }
            for (int i = 0; i < 3; i++) {
                RegisterNewUserRequest userRequest = new RegisterNewUserRequest(userIds.get(i), "test user");
                userService.registerNewUser(userRequest);
            }

            //create 3 points to rank
            List<PointResponse> pointResponses = new ArrayList<PointResponse>();
            CreatePointRequest pointRequest = new CreatePointRequest(5, userIds.get(0), leaderboardResponse.getLeaderboard().getId());
            pointResponses.add(leaderboardService.createPoint(pointRequest));
            pointRequest.setAmount(10);
            pointRequest.setUserId(userIds.get(1));
            pointResponses.add(leaderboardService.createPoint(pointRequest));
            pointRequest.setAmount(6);
            pointRequest.setUserId(userIds.get(2));
            pointResponses.add(leaderboardService.createPoint(pointRequest));

            GetEventLeaderboardRequest request = new GetEventLeaderboardRequest(leaderboardResponse.getLeaderboard().getId(), 1, 3);
            GetEventLeaderboardResponse response = leaderboardService.getEventLeaderboard(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Successfully found points for event", response.getMessage());
            Assertions.assertFalse(response.getLeaderboard().isEmpty());

            //test that each value in the list is in the correct place with the correct value of points and rank
            Assertions.assertEquals(10, response.getLeaderboard().get(0).getPoints());
            Assertions.assertEquals(1, response.getLeaderboard().get(0).getRank());
            Assertions.assertEquals(6, response.getLeaderboard().get(1).getPoints());
            Assertions.assertEquals(2, response.getLeaderboard().get(1).getRank());
            Assertions.assertEquals(5, response.getLeaderboard().get(2).getPoints());
            Assertions.assertEquals(3, response.getLeaderboard().get(2).getRank());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test that when the count given is larger than the number of points left that it correctly only returns the points
     * that exist before up to then
     */
    @Test
    public void getEventLeaderboardTestCountGreaterThanNumberOfPointsLeft() {
        CreateLeaderboardRequest leaderboardRequest = new CreateLeaderboardRequest("test");
        try {
            CreateLeaderboardResponse leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);

            //Create three users to use
            List<UUID> userIds = new ArrayList<UUID>();
            for (int i = 0; i < 3; i++) {
                userIds.add(UUID.randomUUID());
            }
            for (int i = 0; i < 3; i++) {
                RegisterNewUserRequest userRequest = new RegisterNewUserRequest(userIds.get(i), "test user");
                userService.registerNewUser(userRequest);
            }

            //create 3 points to rank
            List<PointResponse> pointResponses = new ArrayList<PointResponse>();
            CreatePointRequest pointRequest = new CreatePointRequest(5, userIds.get(0), leaderboardResponse.getLeaderboard().getId());
            pointResponses.add(leaderboardService.createPoint(pointRequest));
            pointRequest.setAmount(10);
            pointRequest.setUserId(userIds.get(1));
            pointResponses.add(leaderboardService.createPoint(pointRequest));
            pointRequest.setAmount(6);
            pointRequest.setUserId(userIds.get(2));
            pointResponses.add(leaderboardService.createPoint(pointRequest));

            GetEventLeaderboardRequest request = new GetEventLeaderboardRequest(leaderboardResponse.getLeaderboard().getId(), 2, 3);
            GetEventLeaderboardResponse response = leaderboardService.getEventLeaderboard(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Successfully found points for event", response.getMessage());
            Assertions.assertFalse(response.getLeaderboard().isEmpty());
            Assertions.assertEquals(2, response.getLeaderboard().size());

            //test that each value in the list is in the correct place with the correct value of points and rank
            Assertions.assertEquals(6, response.getLeaderboard().get(0).getPoints());
            Assertions.assertEquals(2, response.getLeaderboard().get(0).getRank());
            Assertions.assertEquals(5, response.getLeaderboard().get(1).getPoints());
            Assertions.assertEquals(3, response.getLeaderboard().get(1).getRank());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }
}
