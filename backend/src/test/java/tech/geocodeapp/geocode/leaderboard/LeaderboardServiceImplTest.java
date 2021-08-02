package tech.geocodeapp.geocode.leaderboard;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.request.CreateLeaderboardRequest;
import tech.geocodeapp.geocode.leaderboard.request.CreatePointRequest;
import tech.geocodeapp.geocode.leaderboard.request.DeletePointRequest;
import tech.geocodeapp.geocode.leaderboard.request.UpdatePointRequest;
import tech.geocodeapp.geocode.leaderboard.response.CreateLeaderboardResponse;
import tech.geocodeapp.geocode.leaderboard.response.DeletePointResponse;
import tech.geocodeapp.geocode.leaderboard.response.PointResponse;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardServiceImpl;
import tech.geocodeapp.geocode.user.repository.UserRepository;
import tech.geocodeapp.geocode.user.request.RegisterNewUserRequest;
import tech.geocodeapp.geocode.user.request.UpdateLocationRequest;
import tech.geocodeapp.geocode.user.response.RegisterNewUserResponse;
import tech.geocodeapp.geocode.user.service.UserService;

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
}
