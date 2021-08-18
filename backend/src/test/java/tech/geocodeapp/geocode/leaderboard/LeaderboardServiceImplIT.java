package tech.geocodeapp.geocode.leaderboard;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.geocodeapp.geocode.GeoCodeApplication;
import tech.geocodeapp.geocode.event.service.EventService;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.repository.LeaderboardRepository;
import tech.geocodeapp.geocode.leaderboard.repository.PointRepository;
import tech.geocodeapp.geocode.leaderboard.request.*;
import tech.geocodeapp.geocode.leaderboard.response.CreateLeaderboardResponse;
import tech.geocodeapp.geocode.leaderboard.response.DeletePointResponse;
import tech.geocodeapp.geocode.leaderboard.response.GetEventLeaderboardResponse;
import tech.geocodeapp.geocode.leaderboard.response.PointResponse;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardServiceImpl;
import tech.geocodeapp.geocode.user.request.GetUserByIdRequest;
import tech.geocodeapp.geocode.user.request.RegisterNewUserRequest;
import tech.geocodeapp.geocode.user.response.RegisterNewUserResponse;
import tech.geocodeapp.geocode.user.service.UserService;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class LeaderboardServiceImplIT {
    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    private LeaderboardService leaderboardService;

    private final String menloParkChristmas = "Christmas 2021 market";

    @BeforeEach
    void setUp(){
        leaderboardService = new LeaderboardServiceImpl(leaderboardRepository, pointRepository, eventService, userService);
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
    public void createLeaderboardTestNew(){
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

    @Test
    public void createPointTestNullAllParameters() {
        CreatePointRequest request = new CreatePointRequest(null, null, null);

        assertThatThrownBy(() -> leaderboardService.createPoint(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

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

    @Test
    public void deletePointTestNullPointId() {
        DeletePointRequest request = new DeletePointRequest(null);
        assertThatThrownBy(() -> leaderboardService.deletePoint(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

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

    @Test
    public void updatePointTestNullPointId() {
        UpdatePointRequest request = new UpdatePointRequest(null, null, null, null);
        assertThatThrownBy(() -> leaderboardService.updatePoint(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

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
            Assertions.assertEquals(updatedLeaderboardResponse.getLeaderboard().getId(), response.getPoint().getLeaderBoard().getId());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

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
            Assertions.assertEquals(updatedUserId, response.getPoint().getUser().getId());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

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
            Assertions.assertEquals(updatedUserId, response.getPoint().getUser().getId());
            Assertions.assertEquals(3, response.getPoint().getAmount());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

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
            Assertions.assertEquals(updatedLeaderboardResponse.getLeaderboard().getId(), response.getPoint().getLeaderBoard().getId());
            Assertions.assertEquals(4, response.getPoint().getAmount());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

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
            Assertions.assertEquals(updatedUserId, response.getPoint().getUser().getId());
            Assertions.assertEquals(updatedLeaderboardResponse.getLeaderboard().getId(), response.getPoint().getLeaderBoard().getId());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

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
            Assertions.assertEquals(updatedUserId, response.getPoint().getUser().getId());
            Assertions.assertEquals(updatedLeaderboardResponse.getLeaderboard().getId(), response.getPoint().getLeaderBoard().getId());
            Assertions.assertEquals(2, response.getPoint().getAmount());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

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

    @Test
    public void getEventLeaderboardTestNullRequestParameters() {
        GetEventLeaderboardRequest request = new GetEventLeaderboardRequest(null, null, null);
        assertThatThrownBy(() -> leaderboardService.getEventLeaderboard(request))
                .isInstanceOf(NullRequestParameterException.class);
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
}
